package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Visitor;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author huangyueran
 * @category 访客Action
 */
@Controller
@Scope("prototype")
public class VisitorAction extends ModelDrivenBaseAction<Visitor> {

	/**
	 * 访客列表页面
	 */
	public String toUserVisitorUI() throws AppException {
		// 准备访客列表数据
		List<Visitor> visitors = visitorService.getVisitorOrderByVisitorTimeByUserId(getCurrentUser().getId()); // 只返回10条记录

		// 访问后 将用户访客题型数 清0
		User user = userService.getById(getCurrentUser().getId());
		user.setVisitorsCount(0);
		userService.update(user);

		ActionContext.getContext().put("visitors", visitors);

		return "toUserVisitorUI"; 
	}

}
