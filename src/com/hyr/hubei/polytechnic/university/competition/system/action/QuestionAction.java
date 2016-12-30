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
import com.hyr.hubei.polytechnic.university.competition.system.domain.Question;
import com.hyr.hubei.polytechnic.university.competition.system.domain.QuestionSet;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.hyr.hubei.polytechnic.university.competition.system.utils.HtmlReplaceUtil;
import com.hyr.hubei.polytechnic.university.competition.system.utils.QueryHelper;
import com.hyr.hubei.polytechnic.university.competition.system.utils.XMLUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author huangyueran
 * @category 试题Action
 */
@Controller
@Scope("prototype")
public class QuestionAction extends ModelDrivenBaseAction<Question> {

	private static final int BUFFER_SIZE = 16 * 1024;
	private List<File> myFile = new ArrayList<File>();
	private List<String> contentType = new ArrayList<String>();
	private List<String> fileName = new ArrayList<String>(); // 文件名
	private List<String> imageFileName = new ArrayList<String>();
	private String caption;
	private Long questionSetId;
	private Long scopeId; // 创建的试题所属试题集ID
	private String titleSearch; // 根据标题搜索内容

	// 创建答案需要的参数
	private String[] inputlist; // 输入答案
	private String[] answerlist; // 输出答案
	private Integer[] fractionlist; // 此项得分

	/**
	 * 竞赛系统 试题列表展示页面
	 */
	public String toQuestionListUI() throws AppException {
		// 试题集
		QuestionSet questionSet = questionSetService.getById(questionSetId);
		ActionContext.getContext().getValueStack().push(questionSetId);
		ActionContext.getContext().getValueStack().push(questionSet);
		// 准备分页的数据 （最终版）
		if (titleSearch == null || titleSearch.equals("")) {
			new QueryHelper(Question.class, "q")//
					.addWhereAndCondition("q.scope=?", questionSet)//
					.preparePageBean(questionService, pageNum);
		} else {
			new QueryHelper(Question.class, "q")//
					.addWhereAndCondition("q.title LIKE '%" + titleSearch + "%'")
					.addWhereAndCondition("q.scope=?", questionSet)//
					.preparePageBean(questionService, pageNum);
		}

		return "toQuestionList";
	}

	/**
	 * 竞赛系统 试题详情展示页面
	 */
	public String toQuestionShowUI() throws AppException {
		// 准备试题详细信息数据
		Question question = questionService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(question);

		return "toQuestionShowUI";
	}

	/* =============================管理功能==================================== */

	/**
	 * 试题管理列表页面
	 */
	public String toQuestionManageListUI() throws AppException {
		// 试题集
		QuestionSet questionSet = questionSetService.getById(questionSetId);
		ActionContext.getContext().getValueStack().push(questionSetId);
		// 准备分页的数据 （最终版）
		if (titleSearch == null || titleSearch.equals("")) {
			new QueryHelper(Question.class, "q")//
					.addWhereAndCondition("q.scope=?", questionSet)//
					.preparePageBean(questionService, pageNum);
		} else {
			new QueryHelper(Question.class, "q")//
					.addWhereAndCondition("q.title LIKE '%" + titleSearch + "%'")
					.addWhereAndCondition("q.scope=?", questionSet)//
					.preparePageBean(questionService, pageNum);
		}

		return "toQuestionManageListUI";
	}

	/**
	 * 创建试题页面 管理
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toCreateQuestionUI() throws AppException {
		// 准备试题集数据
		List<QuestionSet> questionSets = questionSetService.findAll();
		ActionContext.getContext().put("questionSets", questionSets);

		return "toCreateQuestionUI";
	}

	/**
	 * 创建试题 管理
	 * 
	 * @return
	 * @throws AppException
	 */
	public String createQuestion() throws AppException {
		
		// 准备试题所属试题集数据
		QuestionSet questionSet = questionSetService.getById(scopeId);
		// 设置属性
		Question question = new Question();
		question.setCareful(HtmlReplaceUtil.getTextFromHtml(model.getCareful()));
		question.setCue(HtmlReplaceUtil.getTextFromHtml(model.getCue()));
		question.setDescription(HtmlReplaceUtil.getTextFromHtml(model.getDescription()));
		question.setContent(model.getContent());
		question.setInputFormat(HtmlReplaceUtil.getTextFromHtml(model.getInputFormat()));
		question.setLanguage(model.getLanguage());
		question.setMemory(model.getMemory());
		question.setOutputFormat(HtmlReplaceUtil.getTextFromHtml(model.getOutputFormat()));
		question.setRuntime(model.getRuntime());
		question.setSampleInput(HtmlReplaceUtil.getTextFromHtml(model.getSampleInput()));
		question.setSampleOutput(HtmlReplaceUtil.getTextFromHtml(model.getSampleOutput()));
		question.setScope(questionSet);
		question.setScores(100);
		question.setTitle(HtmlReplaceUtil.getTextFromHtml(model.getTitle()));
		question.setType(model.getType());

		// 将创建好的数据进行保存
		ActionContext.getContext().getSession().put("newquestion1", question);

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
				question.setImagePath1("UploadImages" + "/" + dateNowStr + "/" + imageFileName);
				break;
			case 1:
				question.setImagePath2("UploadImages" + "/" + dateNowStr + "/" + imageFileName);
				break;
			case 2:
				question.setImagePath3("UploadImages" + "/" + dateNowStr + "/" + imageFileName);
				break;
			case 3:
				question.setImagePath4("UploadImages" + "/" + dateNowStr + "/" + imageFileName);
				break;
			case 4:
				question.setImagePath5("UploadImages" + "/" + dateNowStr + "/" + imageFileName);
				break;

			default:
				break;
			}
		}

		// 创建试题后 前往创建答案页面
		if (question.getType() == 1) {
			return "toCreateQuestionAnswerAUI";
		} else if (question.getType() == 2) {
			return "toCreateQuestionAnswerBUI";
		} else if (question.getType() == 3) {
			return "toCreateQuestionAnswerCUI";
		}

		// TODO 统一异常处理
		return "error";
	}

	/**
	 * 创建答案 管理
	 * 
	 * @return
	 * @throws AppException
	 */
	public String createQuestionAnswer() throws AppException {
		// 取出上个页面设置的question数据
		Question question = (Question) ActionContext.getContext().getSession().get("newquestion1");

		// 存入所属questionSet的id 便于保存成功后进行跳转
		Long questionSetId = question.getScope().getId();

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
			if (question.getType() == 1) {
				xml = XMLUtils.createTypeA(answerlist, fractionlist);
			} else if (question.getType() == 2) {
				xml = XMLUtils.createTypeB(inputlist, answerlist, fractionlist);
			} else if (question.getType() == 3) {
				xml = XMLUtils.createTypeC(inputlist, fractionlist);
			}

			if (xml != null || !xml.equals("")) {
				question.setAnswersXml(xml);
				question.setScores(totalScore);
				question.setCreateTime(new Date());
			} else {
				return "error";
			}

		} else {
			// 否则跳转到错误页面
			return "error";
		}

		// 保存到数据库
		questionService.save(question);

		// 试题集的试题总数字段加1
		QuestionSet questionSet = questionSetService.getById(questionSetId);
		questionSet.setQuestionCount(questionSet.getQuestionCount() + 1);
		questionSet.setUpdateTime(new Date());
		questionSetService.update(questionSet);

		return "toQuestionManageList";
	}

	/**
	 * 删除试题 管理
	 * 
	 * @return
	 * @throws AppException
	 */
	public String deleteQuestion() throws AppException {
		// 找到试题的试题集 删除后 实体数目字段减1
		Question question = questionService.getById(model.getId());
		QuestionSet questionSet = question.getScope();
		questionSet.setQuestionCount(questionSet.getQuestionCount() - 1);

		questionService.delete(model.getId());
		questionSetService.update(questionSet);

		return "toQuestionManageList";
	}

	/**
	 * 修改试题页面 管理
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUpdateQuestionUI() throws AppException {
		// 准备试题数据
		Question question = questionService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(question);

		// 准备试题集数据
		List<QuestionSet> questionSets = questionSetService.findAll();
		ActionContext.getContext().put("questionSets", questionSets);

		return "toUpdateQuestionUI";
	}

	/**
	 * 修改试题 管理
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updateQuestion() throws AppException {
		Question question = questionService.getById(model.getId());

		Long oldQSId = question.getScope().getId();
		Long newQSId = model.getScope().getId();

		// 填写数据
		question.setCareful(HtmlReplaceUtil.getTextFromHtml(model.getCareful()));
		question.setCue(HtmlReplaceUtil.getTextFromHtml(model.getCue()));
		question.setDescription(HtmlReplaceUtil.getTextFromHtml(model.getDescription()));
		question.setContent(model.getContent());
		question.setInputFormat(HtmlReplaceUtil.getTextFromHtml(model.getInputFormat()));
		question.setLanguage(model.getLanguage());
		question.setMemory(model.getMemory());
		question.setOutputFormat(HtmlReplaceUtil.getTextFromHtml(model.getOutputFormat()));
		question.setRuntime(model.getRuntime());
		question.setSampleInput(HtmlReplaceUtil.getTextFromHtml(model.getSampleInput()));
		question.setSampleOutput(HtmlReplaceUtil.getTextFromHtml(model.getSampleOutput()));
		question.setScope(model.getScope());
		question.setScores(100);
		question.setTitle(HtmlReplaceUtil.getTextFromHtml(model.getTitle()));
		question.setType(model.getType());

		// TODO 存储修复后的答案

		questionService.update(question);
		// 更新试题集相关字段
		QuestionSet newQS = questionSetService.getById(newQSId);
		QuestionSet oldQS = questionSetService.getById(oldQSId);
		newQS.setQuestionCount(newQS.getQuestionCount() + 1);
		oldQS.setQuestionCount(oldQS.getQuestionCount() - 1);

		questionSetService.update(newQS);
		questionSetService.update(oldQS);

		return "toQuestionManageList";
	}

	public Long getQuestionSetId() {
		return questionSetId;
	}

	public void setQuestionSetId(Long questionSetId) {
		this.questionSetId = questionSetId;
	}

	public Long getScopeId() {
		return scopeId;
	}

	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
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

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

	public List<String> getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(List<String> imageFileName) {
		this.imageFileName = imageFileName;
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
