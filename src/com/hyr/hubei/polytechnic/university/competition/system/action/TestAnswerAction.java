package com.hyr.hubei.polytechnic.university.competition.system.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Question;
import com.hyr.hubei.polytechnic.university.competition.system.domain.TestAnswer;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.hyr.hubei.polytechnic.university.competition.system.utils.XMLUtils;
import com.opensymphony.xwork2.ActionContext;
import com.sun.xml.internal.ws.util.xml.XmlUtil;

/**
 * 
 * @author huangyueran
 * @category 试题提交答案Action
 */
@Controller
@Scope("prototype")
public class TestAnswerAction extends ModelDrivenBaseAction<TestAnswer> {

	private Long questionId; // 提交答案所属试题的ID

	/**
	 * 提交答案页面
	 */
	public String toSubmitAnswerUI() throws AppException {
		// 准备回显数据
		Question question = questionService.getById(questionId);
		ActionContext.getContext().getValueStack().push(question);

		return "toSubmitAnswerUI";
	}

	/**
	 * 提交答案
	 */
	public String submitAnswer() throws AppException {
		// TODO 提交答案 进入评测
		// TODO 返回到评测列表
		return "toSubmitAnswerUI";
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

}
