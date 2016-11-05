package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.util.Date;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 访客记录
 * @author 黄跃然
 */
public class Visitor {
	/** id主键 */
	private Long id;

	/** 用户自身 */
	private User userself;

	/** 访问用户 */
	private User visitors;

	/** 访问时间 */
	private Date visitorTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserself() {
		return userself;
	}

	public void setUserself(User userself) {
		this.userself = userself;
	}

	public User getVisitors() {
		return visitors;
	}

	public void setVisitors(User visitors) {
		this.visitors = visitors;
	}

	public Date getVisitorTime() {
		return visitorTime;
	}

	public void setVisitorTime(Date visitorTime) {
		this.visitorTime = visitorTime;
	}

}
