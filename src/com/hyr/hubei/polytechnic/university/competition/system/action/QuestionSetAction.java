package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.QuestionSet;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Reply;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.hyr.hubei.polytechnic.university.competition.system.utils.QueryHelper;

/**
 * 
 * @author huangyueran
 * @category 试题集Action
 */
@Controller
@Scope("prototype")
public class QuestionSetAction extends ModelDrivenBaseAction<QuestionSet> {
	
	/**
	 * 试题集展示页面
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

		return "toQuestionSetListUI"; 
	}
	
}
