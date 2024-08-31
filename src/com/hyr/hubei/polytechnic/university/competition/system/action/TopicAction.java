package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.cxf.Configuration;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Favorite;
import com.hyr.hubei.polytechnic.university.competition.system.domain.FavoritePK;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Laud;
import com.hyr.hubei.polytechnic.university.competition.system.domain.LaudPK;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Question;
import com.hyr.hubei.polytechnic.university.competition.system.domain.QuestionSet;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Reply;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Topic;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.hyr.hubei.polytechnic.university.competition.system.utils.HtmlReplaceUtil;
import com.hyr.hubei.polytechnic.university.competition.system.utils.QueryHelper;
import com.hyr.hubei.polytechnic.university.competition.system.utils.XMLUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author Administrator
 * @category 用户登录登出
 */
@Controller
@Scope("prototype")
public class TopicAction extends ModelDrivenBaseAction<Topic> {

	private static final int BUFFER_SIZE = 16 * 1024;
	private List<File> myFile = new ArrayList<File>();
	private List<String> contentType = new ArrayList<String>();
	private List<String> fileName = new ArrayList<String>(); // 文件名
	private List<String> imageFileName = new ArrayList<String>();
	private String caption;

	/**
	 * 0 表示全部主题<br>
	 * 1 表示全部精华贴
	 */
	private int viewType;

	/**
	 * 0 表示默认排序(所有置顶帖在前面，并按最后更新时间降序排列)<br>
	 * 1 表示只按最后更新时间排序<br>
	 * 2 表示只按主题发表时间排序<br>
	 * 3 表示只按回复数量排序
	 */
	private int orderBy;

	/**
	 * true表示升序<br>
	 * false表示降序
	 */
	private boolean asc;

	private String titleSearch; // 根据标题搜索内容
	private int typeId; // 更新主题类型字段
	private Long topicId;

	// 创建答案需要的参数
	private String[] inputlist; // 输入答案
	private String[] answerlist; // 输出答案
	private Integer[] fractionlist; // 此项得分

	/**
	 * 竞赛系统 主题列表展示页面
	 */
	public String toTopicListUI() throws AppException {
		// 准备分页的数据 （最终版）
		if (titleSearch == null || titleSearch.equals("")) {
			new QueryHelper(Topic.class, "t")//
					.addWhereAndCondition((viewType == 1), "t.classify=?", Topic.TYPE_BEST) // 1
					.addWhereAndCondition((viewType == 2), "t.classify=?", Topic.TYPE_TOP) // 2
																							// 表示只看精华帖
					.addOrderByProperty((orderBy == 1), "t.lastUpdateTime", asc) // 1
																					// 表示只按最后更新时间排序
					.addOrderByProperty((orderBy == 2), "t.postTime", asc) // 表示只按主题发表时间排序
					.addOrderByProperty((orderBy == 3), "t.replyCount", asc) // 表示只按回复数量排序
					.addOrderByProperty((orderBy == 0), "(CASE t.classify WHEN 2 THEN 2 ELSE 0 END)", false)//
					.addOrderByProperty((orderBy == 0), "t.lastUpdateTime", false)//
					.preparePageBean(topicService, pageNum);
		} else {
			System.out.println("内容" + titleSearch);
			new QueryHelper(Topic.class, "t")//
					.addWhereAndCondition((viewType == 1), "t.classify=?", Topic.TYPE_BEST) // 1
					.addWhereAndCondition((viewType == 2), "t.classify=?", Topic.TYPE_TOP) // 2
																							// 表示只看精华帖
					.addOrderByProperty((orderBy == 1), "t.lastUpdateTime", asc) // 1
																					// 表示只按最后更新时间排序
					.addOrderByProperty((orderBy == 2), "t.postTime", asc) // 表示只按主题发表时间排序
					.addOrderByProperty((orderBy == 3), "t.replyCount", asc) // 表示只按回复数量排序
					.addWhereAndCondition("t.title LIKE '%" + titleSearch + "%'")
					.addOrderByProperty((orderBy == 0), "(CASE t.classify WHEN 2 THEN 2 ELSE 0 END)", false)//
					.addOrderByProperty((orderBy == 0), "t.lastUpdateTime", false)//
					.preparePageBean(topicService, pageNum);
		}

		// 禁言:不能创建主题、不能发表评论。
		User user = userService.getById(getCurrentUser().getId());
		boolean isBan = false;
		if (user.getIsBan() == 1) {
			isBan = true;
		}
		ActionContext.getContext().getSession().put("isBan", isBan);

		return "toTopicListUI";
	}

	/**
	 * 竞赛系统 显示单个主题
	 */
	public String toTopicShowUI() throws AppException {
		// 准备回显的数据
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		// 收藏状态
		boolean isFavorite = false;
		Favorite favorite = favoriteService.getByTopicIdAndUserId(topic.getId(), getCurrentUser().getId());
		// 判断是否为空
		if (favorite == null) {
			// 未收藏 创建收藏记录 并 设置isFavorite为false
			Favorite createfavorite = new Favorite();
			FavoritePK favoritePK = new FavoritePK();
			favoritePK.setTopicId(topic.getId());
			favoritePK.setUserId(getCurrentUser().getId());
			createfavorite.setFavoritePK(favoritePK);
			createfavorite.setFavoriteTime(new Date());
			createfavorite.setStatus(0);

			isFavorite = false;
			favoriteService.save(createfavorite);
		} else {
			if (favorite.getStatus() == 1) {
				// 如果为1 已收藏
				isFavorite = true;
			} else {
				// 未收藏
				isFavorite = false;
			}
		}
		ActionContext.getContext().put("isFavorite", isFavorite);

		// 点赞状态
		boolean isLaud = false;
		Laud laud = laudService.getByTopicIdAndUserId(topic.getId(), getCurrentUser().getId());

		// 判断是否为空
		if (laud == null) {
			// 未点赞 创建点赞记录 并设置isLaud为false
			Laud createLaud = new Laud();
			LaudPK laudPK = new LaudPK();
			laudPK.setTopicId(topic.getId());
			laudPK.setUserId(getCurrentUser().getId());
			createLaud.setLaudPK(laudPK);
			createLaud.setLaudTime(new Date());
			createLaud.setStatus(0);

			isLaud = false;
			laudService.save(createLaud);
		} else {
			if (laud.getStatus() == 1) {
				// 如果为1 已收藏
				isLaud = true;
			} else {
				// 未收藏
				isLaud = false;
			}
		}
		ActionContext.getContext().put("isLaud", isLaud);

		// 准备分页的数据 （最终版）
		new QueryHelper(Reply.class, "r")//
				.addWhereAndCondition("r.topic=?", topic)//
				.addOrderByProperty("r.postTime", true)//
				.preparePageBean(replyService, pageNum);

		// 禁言:不能创建主题、不能发表评论。
		User user = userService.getById(getCurrentUser().getId());
		boolean isBan = false;
		if (user.getIsBan() == 1) {
			isBan = true;
		}
		ActionContext.getContext().getSession().put("isBan", isBan);

		// TODO 判断主题类型 进入不同的展示页面
		if (topic.getType() == 0) {
			return "toTopicShowUI";
		} else if (topic.getType() == 1) {
			return "toTopicNormalShowUI";
		} else {
			return "error";
		}

	}

	/**
	 * 创建主题页面 >>新增题目 需要参加题目
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toCreateTopicUI() throws AppException {
		// 判断用户是否有权限 isBan
		User user = userService.getById(getCurrentUser().getId());
		if (user.getIsBan() == 1) {
			// 跳转到没有权限页面
			return "noPrivilegeUI";
		}

		// 准备回显的数据
		List<QuestionSet> questionSets = questionSetService.findAll();
		ActionContext.getContext().put("questionSets", questionSets);

		return "toCreateTopicUI";
	}

	/**
	 * 创建普通主题页面 >>知识讨论 没有题目的创建
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toCreateNormalTopicUI() throws AppException {
		// 判断用户是否有权限 isBan
		User user = userService.getById(getCurrentUser().getId());
		if (user.getIsBan() == 0) {
			return "toCreateNormalTopicUI";
		} else {
			// 跳转到没有权限页面
			return "noPrivilegeUI";
		}

	}

	/**
	 * 创建主题
	 * 
	 * @return
	 * @throws AppException
	 */
	public String createTopic() throws AppException {
		// 判断用户是否有权限 isBan
		User user = userService.getById(getCurrentUser().getId());
		if (user.getIsBan() == 1) {
			// 跳转到没有权限页面
			return "noPrivilegeUI";
		}

		Topic topic = new Topic();
		topic.setAuthor(getCurrentUser());
		topic.setCareful(model.getCareful()); 
		topic.setContent(model.getContent());
		topic.setCue(model.getCue());
		topic.setDescription(model.getDescription());
		topic.setInputFormat(model.getInputFormat());
		topic.setIpAddr(getRequestIP()); // 得到用户IP地址
		topic.setLanguage(model.getLanguage());
		topic.setMemory(model.getMemory());
		topic.setOutputFormat(model.getOutputFormat());
		topic.setQuestionTitle(model.getQuestionTitle() + "");
		topic.setQuestionType(model.getQuestionType());
		topic.setRuntime(model.getRuntime());
		topic.setSampleInput(model.getSampleInput());
		topic.setSampleOutput(model.getSampleOutput());
		topic.setScores(model.getScores());
		if (model.getType() == 0) {
			topic.setTitle("【新增题目】" + model.getTitle());
		} else if (model.getType() == 1) {
			topic.setTitle("【知识讨论】" + model.getTitle());
		}
		topic.setTopicContent(model.getTopicContent());
		topic.setType(model.getType());
		topic.setClassify(0);
		
		topic.setAuthor(getCurrentUser());
		topic.setCareful(HtmlReplaceUtil.getTextFromHtml(model.getCareful()+""));
		topic.setContent(model.getContent());
		topic.setCue(HtmlReplaceUtil.getTextFromHtml(model.getCue()+""));
		topic.setDescription(HtmlReplaceUtil.getTextFromHtml(model.getDescription()+""));
		topic.setInputFormat(HtmlReplaceUtil.getTextFromHtml(model.getInputFormat()+""));
		topic.setIpAddr(getRequestIP()); // 得到用户IP地址
		topic.setLanguage(model.getLanguage());
		topic.setMemory(model.getMemory());
		topic.setOutputFormat(HtmlReplaceUtil.getTextFromHtml(model.getOutputFormat()+""));
		topic.setQuestionTitle(HtmlReplaceUtil.getTextFromHtml(model.getQuestionTitle()+"") + "");
		topic.setQuestionType(model.getQuestionType());
		topic.setRuntime(model.getRuntime());
		topic.setSampleInput(HtmlReplaceUtil.getTextFromHtml(model.getSampleInput()+""));
		topic.setSampleOutput(HtmlReplaceUtil.getTextFromHtml(model.getSampleOutput()+""));
		topic.setScores(model.getScores());
		if (model.getType() == 0) {
			topic.setTitle("【新增题目】" + HtmlReplaceUtil.getTextFromHtml(model.getTitle()));
		} else if (model.getType() == 1) {
			topic.setTitle("【知识讨论】" + HtmlReplaceUtil.getTextFromHtml(model.getTitle()));
		}
		topic.setTopicContent(model.getTopicContent());
		topic.setType(model.getType());
		topic.setClassify(0);

		// 上传图片文件
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(new Date());
		String dirStr = ServletActionContext.getServletContext() // 得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
				.getRealPath("UploadImages") + "/" + dateNowStr + "/";
		File file = new File(dirStr);
		if (!file.isDirectory() || !file.exists()) {
			file.mkdirs();
		}

		if (myFile == null)
			return "input"; // 返回上传页面

		// 图片的备份 在服务器外的文件夹中进行备份
		File fileBak = new File(Configuration.getFileCachePath() + "/UploadImages/" + dateNowStr);
		if (!fileBak.isDirectory() || !file.exists()) {
			fileBak.mkdirs();
		}

		// 图片上传
		for (int i = 0; i < myFile.size(); i++) {
			// 将上次的图片文件名改为UUID 保存两份
			UUID uuid = UUID.randomUUID();
			String imageFileName = "[" + uuid.toString() + ".tmp]";
			File imageFile = new File(dirStr + imageFileName);
			File imageFileBak = new File(fileBak.getPath() + '/' + imageFileName);
			copy(myFile.get(i), imageFile); // 把图片写入到上面设置的路径里
			// 备份
			copy(myFile.get(i), imageFileBak);
			// 将图片url存入数据库
			switch (i) {
			case 0:
				topic.setImagePath1("UploadImages" + "/" + dateNowStr + "/" + imageFileName);
				break;
			case 1:
				topic.setImagePath2("UploadImages" + "/" + dateNowStr + "/" + imageFileName);
				break;
			case 2:
				topic.setImagePath3("UploadImages" + "/" + dateNowStr + "/" + imageFileName);
				break;
			case 3:
				topic.setImagePath4("UploadImages" + "/" + dateNowStr + "/" + imageFileName);
				break;
			case 4:
				topic.setImagePath5("UploadImages" + "/" + dateNowStr + "/" + imageFileName);
				break;

			default:
				break;
			}
		}

		// 创建试题后 前往创建答案页面
		if (topic.getType() == 0) {
			topic.setScope(questionSetService.getById(model.getScope().getId()));
			// 如果主题是新增题目 还需要创建答案

			// 将创建好的数据进行保存
			ActionContext.getContext().getSession().put("newtopic1", topic);

			if (topic.getQuestionType() == 1) {
				return "toCreateTopicAnswerAUI";
			} else if (topic.getQuestionType() == 2) {
				return "toCreateTopicAnswerBUI";
			} else if (topic.getQuestionType() == 3) {
				return "toCreateTopicAnswerCUI";
			}
		} else if (topic.getType() == 1) {
			// 如果是知识讨论 直接保存主题
			topic.setPostTime(new Date());
			topic.setLastUpdateTime(topic.getPostTime());
			topicService.save(topic);
			// 跳转到交流中心主页
			return "toTopicList";
		}

		// TODO 统一异常处理
		return "error";
	}

	/**
	 * 创建主题答案
	 * 
	 * @return
	 * @throws AppException
	 */
	public String createTopicAnswer() throws AppException {
		// 判断用户是否有权限 isBan
		User user = userService.getById(getCurrentUser().getId());
		if (user.getIsBan() == 1) {
			// 跳转到没有权限页面
			return "noPrivilegeUI";
		}

		// 取出上个页面设置的topic数据
		Topic topic = (Topic) ActionContext.getContext().getSession().get("newtopic1");
		// 总分
		int totalScore = 0;
		if (fractionlist != null) {
			for (Integer i : fractionlist) {
				totalScore += i;
				System.out.println("=" + i);
			}
		}

		// 判断是否满分是100分
		if (totalScore == 100) {
			// TODO 生成xml答案
			String xml = null;
			if (topic.getQuestionType() == 1) {
				xml = XMLUtils.createTypeA(inputlist, fractionlist);
			} else if (topic.getQuestionType() == 2) {
				xml = XMLUtils.createTypeB(inputlist, answerlist, fractionlist);
			} else if (topic.getQuestionType() == 3) {
				xml = XMLUtils.createTypeC(answerlist, fractionlist);
			}

			if (xml != null || !xml.equals("")) {
				topic.setAnswersXml(xml);
				topic.setScores(totalScore);
				topic.setPostTime(new Date());
				topic.setLastUpdateTime(topic.getPostTime());
			} else {
				return "error";
			}

		} else {
			// 否则跳转到错误页面
			return "error";
		}

		// 保存到数据库
		topicService.save(topic);

		// 跳转到交流中心主页
		return "toTopicList";
	}

	/**
	 * 编辑主题页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUpdateTopicUI() throws AppException {
		// 准备回显的数据
		List<QuestionSet> questionSets = questionSetService.findAll();
		ActionContext.getContext().put("questionSets", questionSets); // 试题集

		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(topic); // 试题

		// 判断登录用户是否是作者
		if (topic.getAuthor().getId() != getCurrentUser().getId()) {
			// 跳转到没有权限页面
			return "noPrivilegeUI";
		}

		// TODO 统一异常处理
		// 新增题目0 知识讨论1
		if (topic.getType() == 0) {
			return "toUpdateTopicAUI";
		} else {
			return "error";
		}
	}

	/**
	 * 编辑主题
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updateTopic() throws AppException {
		Topic topic = topicService.getById(model.getId());

		// 判断登录用户是否是作者
		if (topic.getAuthor().getId() != getCurrentUser().getId()) {
			// 跳转到没有权限页面
			return "noPrivilegeUI";
		}

		topic.setCareful(HtmlReplaceUtil.getTextFromHtml(model.getCareful()+""));
		topic.setContent(model.getContent());
		topic.setCue(HtmlReplaceUtil.getTextFromHtml(model.getCue()+""));
		topic.setDescription(HtmlReplaceUtil.getTextFromHtml(model.getDescription()+""));
		topic.setInputFormat(HtmlReplaceUtil.getTextFromHtml(model.getInputFormat()+""));
		topic.setIpAddr(getRequestIP()); // 得到用户IP地址
		topic.setLanguage(model.getLanguage());
		topic.setMemory(model.getMemory());
		topic.setOutputFormat(HtmlReplaceUtil.getTextFromHtml(model.getOutputFormat()+""));
		topic.setQuestionType(model.getQuestionType());
		topic.setRuntime(model.getRuntime());
		topic.setSampleInput(HtmlReplaceUtil.getTextFromHtml(model.getSampleInput()+""));
		topic.setSampleOutput(HtmlReplaceUtil.getTextFromHtml(model.getSampleOutput()+""));
		topic.setTopicContent(model.getTopicContent());
		topic.setQuestionTitle(HtmlReplaceUtil.getTextFromHtml(model.getQuestionTitle()+""));

		// TODO 图片的上传 暂不处理

		// 创建试题后 前往创建答案页面
		if (topic.getType() == 0) {
			topic.setScope(questionSetService.getById(model.getScope().getId()));
			// 如果主题是新增题目 还需要创建答案

			// 将创建好的数据进行保存
			ActionContext.getContext().getSession().put("newtopic1", topic);

			// 修改为 只有程序设计类型的题目 type=2
			if (topic.getQuestionType() == 2) {
				return "toUpdateTopicAnswerBUI";
			} else {
				return "error";
			}
		} else {
			return "error";
		}

	}

	/**
	 * 编辑主题答案 重新创建答案
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updateTopicAnswer() throws AppException {
		// 取出上个页面设置的topic数据
		Topic topic = (Topic) ActionContext.getContext().getSession().get("newtopic1");

		// 判断登录用户是否是作者
		if (topic.getAuthor().getId() != getCurrentUser().getId()) {
			// 跳转到没有权限页面
			return "noPrivilegeUI";
		}

		// 总分
		int totalScore = 0;
		if (fractionlist != null) {
			for (Integer i : fractionlist) {
				totalScore += i;
			}
		}

		// 判断是否满分是100分
		if (totalScore == 100) {
			// TODO 生成xml答案
			String xml = null;
			if (topic.getQuestionType() == 1) {
				xml = XMLUtils.createTypeA(inputlist, fractionlist);
			} else if (topic.getQuestionType() == 2) {
				xml = XMLUtils.createTypeB(inputlist, answerlist, fractionlist);
			} else if (topic.getQuestionType() == 3) {
				xml = XMLUtils.createTypeC(answerlist, fractionlist);
			}

			if (xml != null || !xml.equals("")) {
				topic.setAnswersXml(xml);
				topic.setScores(totalScore);

				// 修改 无需修改时间 会导致逻辑错误
				// topic.setPostTime(new Date());
				// topic.setLastUpdateTime(topic.getPostTime());
			} else {
				return "error";
			}

		} else {
			// 否则跳转到错误页面
			return "error";
		}

		// 更新修改后的主题 到数据库
		topicService.update(topic);

		// 跳转到主题展示页面
		ActionContext.getContext().put("topicId", topic.getId());
		return "toTopicShow";
	}

	/** 更新主题类型 0普通 1精华 2置顶 */
	public String updateTopicType() throws AppException {
		Topic topic = topicService.getById(topicId);

		topic.setClassify(typeId);

		topicService.update(topic);

		return "toTopicShow";
	}

	/** 删除主题 */
	public String deleteTopic() throws AppException {
		// 根据id删除主题
		topicService.delete(model.getId());

		// 跳转到主题展示列表页面
		return "toTopicList";
	}

	/** 加入题库 */
	public String addTiQuestion() throws AppException {
		Topic topic = topicService.getById(model.getId());
		Question question = new Question(); // 创建的题目

		question.setAnswersXml(topic.getAnswersXml());
		question.setCareful(topic.getCareful());
		question.setContent(topic.getContent());
		question.setCreateTime(new Date());
		question.setCue(topic.getCue());
		question.setDescription(topic.getDescription());
		question.setInputFormat(topic.getInputFormat());
		question.setLanguage(topic.getLanguage());
		question.setMemory(topic.getMemory());
		question.setOutputFormat(topic.getOutputFormat());
		question.setRuntime(topic.getRuntime());
		question.setSampleInput(topic.getSampleInput());
		question.setSampleOutput(topic.getSampleOutput());
		question.setScope(topic.getScope());
		question.setTitle(topic.getQuestionTitle());
		question.setType(topic.getQuestionType());
		question.setImagePath1(topic.getImagePath1());
		question.setImagePath2(topic.getImagePath2());
		question.setImagePath3(topic.getImagePath3());
		question.setImagePath4(topic.getImagePath4());
		question.setImagePath5(topic.getImagePath5());

		// 添加题目到数据库
		questionService.save(question);
		
		// 更新试题集中试题总数
		question.getScope().setQuestionCount(question.getScope().getQuestionCount()+1);
		questionSetService.update(question.getScope());

		// questionSetId topicId
		ActionContext.getContext().put("questionSetId", question.getScope().getId());
		ActionContext.getContext().put("questionId", question.getId());

		// 跳转到创建的题目页面
		return "toQuestionShow";
	}

	public String[] getInputlist() {
		return inputlist;
	}

	public void setInputlist(String[] inputlist) {
		this.inputlist = inputlist;
	}

	public String[] getAnswerlist() {
		return answerlist;
	}

	public void setAnswerlist(String[] answerlist) {
		this.answerlist = answerlist;
	}

	public Integer[] getFractionlist() {
		return fractionlist;
	}

	public void setFractionlist(Integer[] fractionlist) {
		this.fractionlist = fractionlist;
	}

	public String getTitleSearch() {
		return titleSearch;
	}

	public void setTitleSearch(String titleSearch) {
		this.titleSearch = titleSearch;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public List<File> getMyFile() {
		return myFile;
	}

	public void setMyFile(List<File> myFile) {
		this.myFile = myFile;
	}

	public List<String> getContentType() {
		return contentType;
	}

	public void setContentType(List<String> contentType) {
		this.contentType = contentType;
	}

	public List<String> getFileName() {
		return fileName;
	}

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	public List<String> getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(List<String> imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

}
