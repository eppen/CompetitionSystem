package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Favorite;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Question;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Reply;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Role;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Topic;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Visitor;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.hyr.hubei.polytechnic.university.competition.system.utils.QueryHelper;
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
	private Long roleId; // 修改用户权限(角色)传入的id
	private String usernameSearch; // 根据用户名搜索用户 传入的参数

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
		// 展示用户评论 按照评论时间
		new QueryHelper(Reply.class, "r")//
				.addWhereAndCondition("r.topic.author.id=?", getCurrentUser().getId())//
				.addOrderByProperty("r.postTime", false)//
				.preparePageBean(replyService, pageNum);

		// 将用户replysCount字段清0
		User user = userService.getById(getCurrentUser().getId());
		user.setReplysCount(0);
		userService.update(user);
		ActionContext.getContext().getSession().put("userReplysCount", user.getReplysCount());
		// 跳转到用户提醒展示页面
		return "toUserAlertsUI";
	}

	/**
	 * 用户主题列表页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUserTopicListUI() throws AppException {
		// 展示用户主题
		new QueryHelper(Topic.class, "t")//
				.addWhereAndCondition("t.author.id=?", getCurrentUser().getId())//
				.preparePageBean(topicService, pageNum);

		return "toUserTopicListUI";
	}

	/**
	 * 用户收藏页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUserFavoriteUI() throws AppException {
		// 展示用户收藏的主题

		// 获取用户已收藏的主题
		List<Favorite> favorites = favoriteService.getByUserId(getCurrentUser().getId());

		QueryHelper queryHelper = new QueryHelper(Topic.class, "t");//
		for (Favorite f : favorites) {
			queryHelper.addWhereORCondition("t.id = ?", f.getFavoritePK().getTopicId());//
		}
		// .addWhereCondition("t.id IN ?",array)//
		queryHelper.preparePageBean(topicService, pageNum);
		return "toUserFavoriteUI";
	}

	/**
	 * 访问用户信息
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUserInfoUI() throws AppException {
		// 准备数据
		User user = userService.getById(model.getId()); // 要访问用户的ID
		User A = userService.getById(getCurrentUser().getId()); // 当前登录用户

		ActionContext.getContext().getValueStack().push(user); // 页面展示的用户数据

		if (user.getId() == A.getId()) {
			// 自己访问自己 不需要增加记录
			return "toUserInfoUI";
		}
		// 根据访客id和被访客id查询给被访问的用户增加访客记录
		Visitor visitor = visitorService.getByUserIdAndVisitorId(model.getId(), getCurrentUser().getId());
		System.out.println("visitor========" + visitor);
		if (visitor != null) {
			// 如果不为Null 修改访问时间。 访客页面根据访问时间排序
			visitor.setVisitorTime(new Date());
			visitorService.update(visitor);
		} else {
			// 先判断是否为Null 为Null进行创建
			Visitor newVisitor = new Visitor();
			newVisitor.setUserself(user); // 被访问的用户
			newVisitor.setVisitors(A); // 当前登录的用户
			newVisitor.setVisitorTime(new Date());
			visitorService.save(newVisitor);
		}

		// 用户访客记录+1
		user.setVisitorsCount(A.getVisitorsCount() + 1);
		userService.update(user);

		return "toUserInfoUI";
	}

	/** ========================管理员功能================================== */

	/**
	 * 用户管理页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toUserManageListUI() throws AppException {
		// 准备用户列表数据 查询所有未删除的用户
		if (usernameSearch == null || usernameSearch.equals("")) {
			new QueryHelper(User.class, "u")//
					.addWhereAndCondition("u.isDelete = ?", 0)//
					.preparePageBean(userService, pageNum);
		} else {
			new QueryHelper(User.class, "u")//
					.addWhereAndCondition("u.username LIKE '%" + usernameSearch + "%'")
					.addWhereAndCondition("u.isDelete = ?", 0)//
					.preparePageBean(userService, pageNum);
		}

		return "toUserManageListUI";
	}

	/**
	 * 用户禁言和解封
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updateUserIsBan() throws AppException {
		// 根据传入的id修改指定用户isBan属性
		User user = userService.getById(model.getId());
		user.setIsBan(model.getIsBan());

		userService.update(user);
		return "toUserManageList";
	}

	/**
	 * 用户重置密码
	 * 
	 * @return
	 * @throws AppException
	 */
	public String resetUserPassword() throws AppException {
		// 根据传入的id修改用户密码
		User user = userService.getById(model.getId());

		// MD5加密 默认密码为666666
		String md5Password = DigestUtils.md5Hex("666666");
		user.setPassword(md5Password);

		userService.update(user);
		return "toUserManageList";
	}

	/**
	 * 用户角色设置 设置次级管理员/普通用户
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updateUserRole() throws AppException {
		// 根据传入的id修改用户权限(角色)
		User user = userService.getById(model.getId());
		// 1超级管理员 2管理员 3普通用户
		Role role = roleService.getById(roleId);
		user.setRole(role);

		userService.update(user);
		return "toUserManageList";
	}

	/**
	 * 添加用户页面
	 * 
	 * @return
	 * @throws AppException
	 */
	public String toCreateUserUI() throws AppException {
		// 显示回显数据

		// 只显示非超级管理员的角色
		Long[] ids = { (long) 2, (long) 3 };
		List<Role> roles = roleService.getByIds(ids);
		System.out.println("=========" + roles.size());
		ActionContext.getContext().put("roles", roles);

		return "toCreateUserUI";
	}

	/**
	 * 添加用户
	 * 
	 * @return
	 * @throws AppException
	 */
	public String createUser() throws AppException {
		// 创建用户
		User user = new User();
		user.setUsername(model.getUsername());
		user.setCreateTime(new Date());
		user.setGender("男");
		if (model.getName() != null || !model.getName().equals("")) {
			user.setName(model.getName());
		} else {
			user.setName("学生");
		}

		// 用户权限
		Role role = roleService.getById(roleId);
		user.setRole(role);

		// MD5加密 默认密码为666666
		String md5Password = DigestUtils.md5Hex("666666");
		user.setPassword(md5Password);

		userService.save(user);
		return "toUserManageList";
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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUsernameSearch() {
		return usernameSearch;
	}

	public void setUsernameSearch(String usernameSearch) {
		this.usernameSearch = usernameSearch;
	}

}
