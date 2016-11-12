package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.QuestionSet;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Reply;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Topic;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
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
					.addWhereCondition((viewType == 1), "t.classify=?", Topic.TYPE_BEST) // 1 
					.addWhereCondition((viewType == 2), "t.classify=?", Topic.TYPE_TOP) // 2
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
					.addWhereCondition((viewType == 1), "t.classify=?", Topic.TYPE_BEST) // 1
					.addWhereCondition((viewType == 2), "t.classify=?", Topic.TYPE_TOP) // 2 
																						// 表示只看精华帖
					.addOrderByProperty((orderBy == 1), "t.lastUpdateTime", asc) // 1
																					// 表示只按最后更新时间排序
					.addOrderByProperty((orderBy == 2), "t.postTime", asc) // 表示只按主题发表时间排序
					.addOrderByProperty((orderBy == 3), "t.replyCount", asc) // 表示只按回复数量排序
					.addWhereCondition("t.title LIKE '%" + titleSearch + "%'")
					.addOrderByProperty((orderBy == 0), "(CASE t.classify WHEN 2 THEN 2 ELSE 0 END)", false)//
					.addOrderByProperty((orderBy == 0), "t.lastUpdateTime", false)//   
					.preparePageBean(topicService, pageNum);
		}
		return "toTopicListUI";
	}

	/**
	 * 竞赛系统 显示单个主题
	 */
	public String toTopicShowUI() throws AppException {
		// 准备回显的数据
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);

		// 准备分页的数据 （最终版）
		new QueryHelper(Reply.class, "r")//
				.addWhereCondition("r.topic=?", topic)//
				.addOrderByProperty("r.postTime", true)//
				.preparePageBean(replyService, pageNum);

		// TODO 判断主题类型 进入不同的展示页面

		return "toTopicShowUI";
	}

	/**
	 * 创建主题页面 >>新增题目 需要参加题目
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toCreateTopicUI() throws AppException {
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
		return "toCreateNormalTopicUI";
	}

	/**
	 * 创建主题
	 * 
	 * @return
	 * @throws AppException
	 */
	public String createTopic() throws AppException {
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
		topic.setQuestionTitle(model.getQuestionTitle());
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
		
		// TODO 图片的上传 暂不处理

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

	/** 更新主题类型 0普通 1精华 2置顶 */
	public String updateTopicType() throws AppException {
		Topic topic = topicService.getById(topicId);
		topic.setClassify(typeId);

		topicService.update(topic);

		return "toTopicShow";
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

}
