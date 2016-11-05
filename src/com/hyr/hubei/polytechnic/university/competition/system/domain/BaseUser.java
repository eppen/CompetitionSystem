package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 用户基础 用于用户继承
 * @author 黄跃然
 */
public class BaseUser {
	/** id主键 */
	private Long id;

	/** 用户类型 0普通用户 1顶级管理员 2次级管理员 */
	private Integer isAdmin = 0;

	/** 用户姓名 */
	private String name;

	/** 用户登录名 */
	private String username;

	/** 用户密码 MD5加密 */
	private String password;

	/** 用户出生日期 */
	private Date birthday;

	/** 用户性别 0女 1男 */
	private Integer sex = 0;

	/** 用户邮箱 */
	private String email;

	/** 用户QQ */
	private String qq;

	/** 用户手机号 */
	private String telephone;

	/** 用户班级 */
	private String classes;

	/** 用户头像 */
	private byte[] pic;

	/** 用户积分 */
	private Integer score = 0;

	/** 用户最近消息数 */
	private Integer replysCount = 0;

	/** 用户最近访客数 */
	private Integer visitorsCount = 0;

	/** 用户提交的答案 */
	private Set<TestAnswer> answers;

	/** 是否被禁言 0未禁言 1已禁言 默认值0 */
	private Integer isBan = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getReplysCount() {
		return replysCount;
	}

	public void setReplysCount(Integer replysCount) {
		this.replysCount = replysCount;
	}

	public Integer getVisitorsCount() {
		return visitorsCount;
	}

	public void setVisitorsCount(Integer visitorsCount) {
		this.visitorsCount = visitorsCount;
	}

	public byte[] getPic() {
		return pic;
	}

	public void setPic(byte[] pic) {
		this.pic = pic;
	}

	public Set<TestAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<TestAnswer> answers) {
		this.answers = answers;
	}

	public Integer getIsBan() {
		return isBan;
	}

	public void setIsBan(Integer isBan) {
		this.isBan = isBan;
	}

}
