package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Question;
import com.hyr.hubei.polytechnic.university.competition.system.domain.QuestionSet;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Reply;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.hyr.hubei.polytechnic.university.competition.system.utils.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author huangyueran
 * @category 试题集Action
 */
@Controller
@Scope("prototype")
public class QuestionSetAction extends ModelDrivenBaseAction<QuestionSet> {

	/**
	 * 试题集管理页面
	 */
	public String toQuestionSetManageListUI() throws AppException {
		// 准备试题集回显数据

		// 准备分页的数据 （最终版）
		new QueryHelper(QuestionSet.class, "q")//
				.preparePageBean(questionSetService, pageNum);

		return "toQuestionSetManageListUI";
	}
	
	/**
	 * 竞赛系统 试题集展示页面
	 */
	public String toQuestionSetListUI() throws AppException {
		// 准备试题集回显数据

		// 准备分页的数据 （最终版）
		new QueryHelper(QuestionSet.class, "q")//
				.preparePageBean(questionSetService, pageNum);

		return "toQuestionSetList";
	}

	/**
	 * 创建试题集页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toCreateQuestionSetUI() throws AppException {

		return "toCreateQuestionSetUI";
	}

	/**
	 * 创建试题集
	 * 
	 * @return
	 * @throws AppException
	 */
	public String createQuestionSet() throws AppException {
		// 准备创建的数据
		QuestionSet questionSet = new QuestionSet();

		questionSet.setDescription(model.getDescription());
		questionSet.setTitle(model.getTitle());
		questionSet.setUpdateTime(new Date());

		questionSetService.save(questionSet);

		return "toQuestionSetManageListUI";
	}

	/**
	 * 删除试题集
	 * 
	 * @return
	 * @throws AppException
	 */
	public String deleteQuestionSet() throws AppException {
		questionSetService.delete(model.getId());

		return "toQuestionSetManageList";
	} 

	/**
	 * 修改试题集页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUpdateQuestionSetUI() throws AppException {
		// 准备回显数据
		QuestionSet questionSet = questionSetService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(questionSet);

		return "toUpdateQuestionSetUI"; 
	}

	/**
	 * 修改试题集
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updateQuestion() throws AppException {
		QuestionSet questionSet = questionSetService.getById(model.getId());
		questionSet.setDescription(model.getDescription());
		questionSet.setTitle(model.getTitle());

		questionSetService.update(questionSet);

		return "toQuestionSetManageList"; 
	}

}
