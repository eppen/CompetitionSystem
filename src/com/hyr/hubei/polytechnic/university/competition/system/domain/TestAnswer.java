package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 提交答案
 * @author 黄跃然
 */
public class TestAnswer {
	/** id主键 */
	private Long id;

	/** 答案所属学生 */
	private User student;

	/** 答案所属试题 */
	private Question question;

	/** 结果:等待评测 正确 错误 运行超时 编译错误 运行错误 */
	private String result = "等待评测";

	/** 得分 */
	private int scores = 0;

	/** 提交时间 */
	private Date submitTime;

	/** 关联的评测点 */
	private Set<ScoringPoint> scoringPoints;

	/** CPU使用 */
	private double runtime = -1;

	/** 内存使用 */
	private double memory = -1;

	/** 试题标题 用于展示，避免查询浪费内存 */
	private String questionTitle;

	/** 编程语言 */
	private String language = "JAVA";

	/** 提交的文本答案 */
	private String answer = "";

	/** 编译信息 */
	private String compileInfo;

	/** 代码长度 */
	private long codeLength;

	/** 是否删除。用户自身无法删除，如果管理员删除了试题或者试题集，才进行删除。 0未删除 1已删除 默认值0 */
	private Integer isdel = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getStudent() {
		return student;
	}

	public void setStudent(User student) {
		this.student = student;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Set<ScoringPoint> getScoringPoints() {
		return scoringPoints;
	}

	public void setScoringPoints(Set<ScoringPoint> scoringPoints) {
		this.scoringPoints = scoringPoints;
	}

	public double getRuntime() {
		return runtime;
	}

	public void setRuntime(double runtime) {
		this.runtime = runtime;
	}

	public double getMemory() {
		return memory;
	}

	public void setMemory(double memory) {
		this.memory = memory;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getCompileInfo() {
		return compileInfo;
	}

	public void setCompileInfo(String compileInfo) {
		this.compileInfo = compileInfo;
	}

	public long getCodeLength() {
		return codeLength;
	}

	public void setCodeLength(long codeLength) {
		this.codeLength = codeLength;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

}
