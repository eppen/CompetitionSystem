package com.hyr.hubei.polytechnic.university.competition.system.domain;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 权限表 存放url 对用户访问url进行权限的验证
 * @author 黄跃然
 */
public class Privilege {
	/** */
	private Long id;

	/** */
	private String url;

	/** */
	private String name;

	public Privilege() {
	}

	public Privilege(String url, String name) {
		this.url = url;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
