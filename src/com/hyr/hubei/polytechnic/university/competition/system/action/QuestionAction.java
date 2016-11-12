package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Question;
import com.hyr.hubei.polytechnic.university.competition.system.domain.QuestionSet;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
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
					.addWhereCondition("q.scope=?", questionSet)//
					.preparePageBean(questionService, pageNum);
		} else {
			new QueryHelper(Question.class, "q")//
					.addWhereCondition("q.title LIKE '%" + titleSearch + "%'")
					.addWhereCondition("q.scope=?", questionSet)//
					.preparePageBean(questionService, pageNum);
		}

		return "toQuestionList";
	}

	/**
	 * 竞赛系统 试题详情展示页面
	 */
	public String toQuestionShowUI() throws AppException {
		// 试题集

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
					.addWhereCondition("q.scope=?", questionSet)//
					.preparePageBean(questionService, pageNum);
		} else {
			new QueryHelper(Question.class, "q")//
					.addWhereCondition("q.title LIKE '%" + titleSearch + "%'")
					.addWhereCondition("q.scope=?", questionSet)//
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
		question.setCareful(model.getCareful());
		question.setCue(model.getCue());
		question.setDescription(model.getDescription());
		question.setContent(model.getContent());
		question.setInputFormat(model.getInputFormat());
		question.setLanguage(model.getLanguage());
		question.setMemory(model.getMemory());
		question.setOutputFormat(model.getOutputFormat());
		question.setRuntime(model.getRuntime());
		question.setSampleInput(model.getSampleInput());
		question.setSampleOutput(model.getSampleOutput());
		question.setScope(questionSet);
		question.setScores(100);
		question.setTitle(model.getTitle());
		question.setType(model.getType());

		// 将创建好的数据进行保存
		ActionContext.getContext().getSession().put("newquestion1", question);

		// TODO 图片的上传 暂不处理

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
		question.setCareful(model.getCareful());
		question.setCue(model.getCue());
		question.setDescription(model.getDescription());
		question.setContent(model.getContent());
		question.setInputFormat(model.getInputFormat());
		question.setLanguage(model.getLanguage());
		question.setMemory(model.getMemory());
		question.setOutputFormat(model.getOutputFormat());
		question.setRuntime(model.getRuntime());
		question.setSampleInput(model.getSampleInput());
		question.setSampleOutput(model.getSampleOutput());
		question.setScope(model.getScope());
		question.setScores(100);
		question.setTitle(model.getTitle());
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
}
