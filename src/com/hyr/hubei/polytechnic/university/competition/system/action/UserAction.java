package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Role;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends ModelDrivenBaseAction<User> {

	private static final long serialVersionUID = 8096925977637826950L;

	public UserAction() throws AppException {
		super();
	}

	/**
	 * 列表
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUserCenterUI() throws AppException {
		User user = userService.getById(getCurrentUser().getId());
		ActionContext.getContext().put("user", user);
		return "toUserCenterUI";
	}

	/**
	 * 用户信息修改页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUpdateUserUI() throws AppException {
		// 准备数据，回显用户信息
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);

		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "editUI";
	}

	/**
	 * 用户信息修改
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updateUser() throws AppException {
		// 1.从数据库中取出原对象
		User user = userService.getById(model.getId());
		// 2.设置要修改的属性
		user.setClasses(model.getClasses());
		user.setEmail(model.getEmail());
		user.setGender(model.getGender());
		user.setName(model.getName());
		user.setQq(model.getQq());

		Role role = roleService.getById(model.getRole().getId());
		user.setRole(role);

		// 3. 更新到数据库
		userService.update(user);

		return "toList";
	}

	/**
	 * 修改密码页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUpdateUserPasswordUI() throws AppException {

		return "toUserCenterUI";
	}

	/**
	 * 用户提醒页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUserAlertsUI() throws AppException {

		return "toUserCenterUI";
	}

	/**
	 * 用户主题列表页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUserTopicListUI() throws AppException {

		return "toUserCenterUI";
	}

	/**
	 * 用户收藏页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUserFavoriteUI() throws AppException {

		return "toUserCenterUI";
	}

	/**
	 * 用户访客记录
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUservisitorListUI() throws AppException {

		return "toUserCenterUI";
	}

	/** ========================管理员功能================================== */

}
