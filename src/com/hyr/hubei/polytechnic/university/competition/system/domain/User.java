package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.util.Collection;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 用户 学生/管理员
 * @author 黄跃然
 */
public class User extends BaseUser {

	/** 用户访客记录 */
	private Set<Visitor> visitors;

	/** 角色 普通用户 顶级管理员 次级管理员 */
	private Role role;

	public Set<Visitor> getVisitors() {
		return visitors;
	}

	public void setVisitors(Set<Visitor> visitors) {
		this.visitors = visitors;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	// =================================

	/**
	 * 判断当前用户是否有指定名称的权限
	 * 
	 * @param privName
	 *            权限的名称
	 */
	public boolean hasPrivilegeByName(String privName) {
		// 如果是超级管理员，就有所有的权限
		if (hasAdminPrivilege()) {
			return true;
		}
		// 如果是普通用户，就需要判断权限了

		for (Privilege p : role.getPrivileges()) {
			if (p.getName().equals(privName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断当前用户是否有指定URL的权限
	 * 
	 * @param privUrl
	 *            权限的URL
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String privUrl) {
		// 如果是超级管理员，就有所有的权限
		if (hasAdminPrivilege()) {
			return true;
		}

		// a, 去掉后面的参数字符串（如果有）
		int pos = privUrl.indexOf("?");
		if (pos > -1) {
			privUrl = privUrl.substring(0, pos);
		}
		// // b, 去掉后面的UI后缀（如果有）
		// if (privUrl.endsWith("UI")) {
		// privUrl = privUrl.substring(0, privUrl.length() - 2);
		// }

		// 如果是普通用户/次级管理员，就需要判断权限了
		// a, 如果这个URL是不需要控制的功能（登录后就能直接使用的），这是应直接返回true
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext.getContext().getApplication()
				.get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privUrl)) {
			System.out.println("不在权限管理范围内");
			return true;
		}
		// b, 如果这个URL是需要控制的功能（登录后还得有对应的权限才能使用的），这是应判断权限
		else {
			for (Privilege p : role.getPrivileges()) {
				if (privUrl.equals(p.getUrl())) {
					return true;
				}
			}

			return false;
		}
	}

	/**
	 * 判断当前用户是否是超级管理员
	 */
	public boolean hasAdminPrivilege() {
		return role.getName().equals("顶级管理员");
		// return "admin".equals(username);
	}

	/**
	 * 判断当前用户是否是次级管理员
	 */
	public boolean hasAdminChildPrivilege() {
		return role.getName().equals("次级管理员");
		// return "admin".equals(username);
	}

}
