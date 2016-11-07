package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Role;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author huangyueran
 * @category 用户Action
 */
@Controller
@Scope("prototype")
public class UserAction extends ModelDrivenBaseAction<User> {

	private String oldPassword; // 更改密码 输入的原始密码
	private String password1;
	private String password2;

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

		return "toShowUserUI";
	}

	/**
	 * 修改密码页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUpdateUserPasswordUI() throws AppException {

		return "toUpdateUserPasswordUI";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updateUserPassword() throws AppException {
		User user = userService.getById(model.getId());
		String oldpassword2 = DigestUtils.md5Hex(oldPassword);
		System.out.println(password1 + "====" + password2);
		if (oldPassword != null && user.getPassword().equals(oldpassword2)) {
			System.out.println("===============1");
			// 相等 原始密码输入成功
			if (password1 != null && password2 != null && password1.equals(password2)) {
				System.out.println("======================2");
				user.setPassword(DigestUtils.md5Hex(password1));
			}

			ActionContext.getContext().getSession().put("user", user);
			userService.update(user);

			return "toShowUserUI";
		} else {
			// 输入失败 添加错误信息 返回原始页面
			// 如果验证失败，就转回登录页面并提示错误消息
			addFieldError("updatepassworderror", "密码出入错误!");
			return "toUpdateUserPasswordUI";
		}

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

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	/** ========================管理员功能================================== */

}
