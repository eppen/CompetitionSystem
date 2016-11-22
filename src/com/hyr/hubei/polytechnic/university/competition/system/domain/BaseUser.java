package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 用户基础 用于用户继承
 * @author 黄跃然
 */
public class BaseUser {
	/** id主键 */
	protected Long id;

	/** 用户姓名 */
	protected String name = "学生";

	/** 用户登录名 */
	protected String username;

	/** 用户密码 MD5加密 */
	protected String password;

	/** 用户性别 */
	protected String gender; // 性别
	/** 用户邮箱 */
	protected String email = "";

	/** 用户QQ */
	protected String qq = "";

	/** 用户手机号 */
	protected String telephone = "";

	/** 用户班级 */
	protected String classes = "";

	/** 用户头像 */
	protected byte[] pic;

	/** 用户积分 */
	protected Integer score = 0;

	/** 用户最近消息数 */
	protected Integer replysCount = 0;

	/** 用户最近访客数 */
	protected Integer visitorsCount = 0;

	/** 用户提交的答案 */
	protected Set<TestAnswer> answers;

	/** 是否被禁言 0未禁言 1已禁言 默认值0 */
	protected Integer isBan = 0;

	/** 是否被删除 0未删除 1已杀出 默认值0 */
	protected Integer isDelete = 0;

	/** 创建日期 */
	protected Date createTime;

	/** 出生日期 */
	protected Date birthday;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
