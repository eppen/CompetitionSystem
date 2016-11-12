package com.hyr.hubei.polytechnic.university.competition.system.domain;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 学生和试题的关系状态 此功能已删除
 * @author 黄跃然 单独维护，每次做题、查看题目时，更新此表。
 *         用户进入系统，查看某一题，判断是否存在记录，如果存在直接修改。如果不存在，则创建一条记录. 状态为未打开。
 */
@Deprecated
public class Studennt_Question_Status {

	/** id主键 */
	private Long id;

	/** 所属试题 */
	private Question question;

	/** 所属学生 */
	private User student;

	/** 状态==== 未创建：未打开 未答题：已打开 正确：正确 错误：错误。 */
	private String status;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
