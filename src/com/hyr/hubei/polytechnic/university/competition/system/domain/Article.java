package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.util.Date;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 文章 回复和主题的父类
 * @author 黄跃然
 */
public class Article {
	/** id主键 */
	private Long id;

	/** 作者 */
	private User author;

	/** 发表时间 */
	private Date postTime;

	/** ip地址 */
	private String ipAddr;

	/** 是否删除 0未删除 1已删除 默认值0 */
	private Integer isdel=0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	
}
