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

	/**
	 * 试题列表页面
	 */
	public String toQuestionListUI() throws AppException {
		// 试题集
		QuestionSet questionSet = questionSetService.getById(questionSetId);

		// 准备分页的数据 （最终版）
		new QueryHelper(Question.class, "q")//
				.addWhereCondition("q.scope=?", questionSet)//
				.preparePageBean(questionService, pageNum);

		return "toQuestionList";
	}

	/**
	 * 创建试题页面
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
	 * 创建试题
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
		question.setCreateTime(new Date());
		question.setCue(model.getCue());
		question.setDescription(model.getDescription());
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

		System.out.println(question);

		// 将创建好的数据进行保存
		ActionContext.getContext().getSession().put("newquestion1", question);

		// TODO 图片的上传 暂不处理

		// 创建试题后 前往创建答案页面
		if (question.getType() == 0) {
			return "toCreateQuestionAnswerAUI"; 
		} else if (question.getType() == 1) {
			return "toCreateQuestionAnswerBUI";
		} else if (question.getType() == 2) {
			return "toCreateQuestionAnswerCUI";
		}
		
		// TODO 统一异常处理
		return "error";
	}

	/**
	 * 创建答案
	 * 
	 * @return
	 * @throws AppException
	 */
	public String createQuestionAnswer() throws AppException {

		return "createQuestionAnswer";
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

}
