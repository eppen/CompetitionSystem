package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 试题集
 * @author 黄跃然
 */
public class QuestionSet {
	/** id主键 */
	private Long id;

	/** 试题集名称 */
	private String title;

	/** 试题集描述 */
	private String description;

	/** 试题集的实体数 */
	private Integer questionCount = 0;

	/** 试题集关联的试题 */
	private List<Question> questions;

	/** 主题关联的试题 */
	private Set<Topic> topics;

	/** 更新时间 以最后添加的试题时间为准 */
	private Date updateTime;

	/** 是否被删除 0未删除 1已删除 默认值0。如果删除试题集，需要将一系列的试题和评测答案都删除. */
	private Integer isdel = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

}
