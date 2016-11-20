package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.util.Set;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 角色(用户类型) 1超级管理员 2管理员 3普通用户
 * @author 黄跃然
 */
public class Role {
	/** */
	private Long id;

	/** */
	private String name;

	/** */
	private String description;

	/** */
	private Set<User> users;

	/** */
	private Set<Privilege> privileges;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

}
