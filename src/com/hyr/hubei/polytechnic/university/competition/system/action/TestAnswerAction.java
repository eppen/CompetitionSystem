package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Question;
import com.hyr.hubei.polytechnic.university.competition.system.domain.TestAnswer;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.hyr.hubei.polytechnic.university.competition.system.utils.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author huangyueran
 * @category 试题提交答案Action
 */
@Controller
@Scope("prototype")
public class TestAnswerAction extends ModelDrivenBaseAction<TestAnswer> {

	private Long questionId; // 提交答案所属试题的ID
	private String titleNameSearch; // 根据试题名 搜索字段
	private String resultSearch = "评测结果"; // 根据结果 搜索字段

	/**
	 * 提交答案页面
	 */
	public String toSubmitAnswerUI() throws AppException {
		// 准备回显数据
		Question question = questionService.getById(questionId);
		ActionContext.getContext().getValueStack().push(question);

		// 生成随机数 防止缓存 无效
		// Random random = new Random();
		// double randomTemp = random.nextDouble();
		// ActionContext.getContext().getSession().put("randomTemp", randomTemp);

		return "toSubmitAnswerUI";
	}

	/**
	 * 评测状态
	 */
	public String toAnswerListUI() throws AppException {

		ActionContext.getContext().getValueStack().push(resultSearch);
		System.out.println(resultSearch);

		// 准备回显数据 返回当前登录用户的答案信息分页数据
		if (titleNameSearch == null || titleNameSearch.equals("")) {
			if (resultSearch.equals("评测结果")) {
				// 默认搜索
				new QueryHelper(TestAnswer.class, "t")//
						.addWhereAndCondition("t.student.id=?", getCurrentUser().getId())//
						.addOrderByProperty("t.submitTime", false) //
						.preparePageBean(testAnswerService, pageNum);
			} else {
				// 条件搜索
				new QueryHelper(TestAnswer.class, "t")//
						.addWhereAndCondition("t.student.id=?", getCurrentUser().getId())//
						.addWhereAndCondition("t.result=?", resultSearch)//
						.addOrderByProperty("t.submitTime", false) //
						.preparePageBean(testAnswerService, pageNum);
			}
		} else {
			if (resultSearch.equals("评测结果")) {
				// 默认搜索
				new QueryHelper(TestAnswer.class, "t")//
						.addWhereAndCondition("t.student.id=?", getCurrentUser().getId())//
						.addWhereAndCondition("t.question.title LIKE '%" + titleNameSearch + "%'")//
						.addOrderByProperty("t.submitTime", false) //
						.preparePageBean(testAnswerService, pageNum);
			} else {
				// 条件搜索
				new QueryHelper(TestAnswer.class, "t")//
						.addWhereAndCondition("t.student.id=?", getCurrentUser().getId())//
						.addWhereAndCondition("t.result=?", resultSearch)//
						.addWhereAndCondition("t.question.title LIKE '%" + titleNameSearch + "%'")//
						.addOrderByProperty("t.submitTime", false) //
						.preparePageBean(testAnswerService, pageNum);
			}

		}
		return "toAnswerListUI";
	}

	/**
	 * 试题提交记录页面
	 */
	public String toQuestionSubmitRecordUI() throws AppException {
		ActionContext.getContext().put("question", questionService.getById(questionId));
		// 准备数据
		ActionContext.getContext().getValueStack().push(resultSearch);
		System.out.println(resultSearch);

		// 准备回显数据 返回当前登录用户的答案信息分页数据
		if (titleNameSearch == null || titleNameSearch.equals("")) {
			if (resultSearch.equals("评测结果")) {
				// 默认搜索
				new QueryHelper(TestAnswer.class, "t")//
						.addWhereAndCondition("t.student.id=?", getCurrentUser().getId())//
						.addWhereAndCondition("t.question.id=?", questionId)//
						.addOrderByProperty("t.submitTime", false) //
						.preparePageBean(testAnswerService, pageNum);
			} else {
				// 条件搜索
				new QueryHelper(TestAnswer.class, "t")//
						.addWhereAndCondition("t.student.id=?", getCurrentUser().getId())//
						.addWhereAndCondition("t.result=?", resultSearch)//
						.addWhereAndCondition("t.question.id=?", questionId)//
						.addOrderByProperty("t.submitTime", false) //
						.preparePageBean(testAnswerService, pageNum);
			}
		} else {
			if (resultSearch.equals("评测结果")) {
				// 默认搜索
				new QueryHelper(TestAnswer.class, "t")//
						.addWhereAndCondition("t.student.id=?", getCurrentUser().getId())//
						.addWhereAndCondition("t.question.id=?", questionId)//
						.addWhereAndCondition("t.question.title LIKE '%" + titleNameSearch + "%'")//
						.addOrderByProperty("t.submitTime", false) //
						.preparePageBean(testAnswerService, pageNum);
			} else {
				// 条件搜索
				new QueryHelper(TestAnswer.class, "t")//
						.addWhereAndCondition("t.student.id=?", getCurrentUser().getId())//
						.addWhereAndCondition("t.question.id=?", questionId)//
						.addWhereAndCondition("t.result=?", resultSearch)//
						.addWhereAndCondition("t.question.title LIKE '%" + titleNameSearch + "%'")//
						.addOrderByProperty("t.submitTime", false) //
						.preparePageBean(testAnswerService, pageNum);
			}

		}

		return "toQuestionSubmitRecordUI";
	}

	/**
	 * 评测详情
	 */
	public String toAnswerInfoUI() throws AppException {
		// 准备数据 准备详情信息 以及评测点数据
		TestAnswer testAnswer = testAnswerService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(testAnswer);
		System.out.println(testAnswer.getScoringPoints());
		return "toAnswerInfoUI";
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getTitleNameSearch() {
		return titleNameSearch;
	}

	public void setTitleNameSearch(String titleNameSearch) {
		this.titleNameSearch = titleNameSearch;
	}

	public String getResultSearch() {
		return resultSearch;
	}

	public void setResultSearch(String resultSearch) {
		this.resultSearch = resultSearch;
	}

}
