package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.util.List;
import java.util.Set;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 用户 学生/管理员
 * @author 黄跃然
 */
public class User extends BaseUser {

	/** 用户访客记录 */
	private List<Visitor> visitors;

	public List<Visitor> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<Visitor> visitors) {
		this.visitors = visitors;
	}

}
