package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.sql.Blob;
import java.util.Date;
import java.util.List;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 主题
 * @author 黄跃然
 */
public class Topic extends Article {

	/** 普通帖 */
	public static final int TYPE_NORMAL = 0;

	/** 精华帖 */
	public static final int TYPE_BEST = 1;

	/** 置顶帖 */
	public static final int TYPE_TOP = 2;

	/** 主题类型 0新增题目 1知识讨论 默认为1 */
	private Integer type = 1;

	/** 帖子类型 0普通 1精华 2置顶 */
	private Integer classify = 0;

	/** 试题标题 */
	private String title;

	/** 试题类型 0无类型 1结果填空 2程序设计 3代码填空。 要和试题表中对应一致。默认为0 */
	private Integer questionType = 0;

	/** 主题内容------作者想所的话 */
	private String topicContent;

	/** 试题内容 */
	private String content;

	/** 试题描述 */
	private String description;

	/** 试题名称 */
	private String questionTitle;

	/** 编程语言 */
	private String language;

	/** 属于试题集 */
	private QuestionSet scope;

	/** 输入格式 */
	private String inputFormat;

	/** 输出格式 */
	private String outputFormat;

	/** 样例输入 */
	private String sampleInput;

	/** 样例输出 */
	private String sampleOutput;

	/** 注意 */
	private String careful;

	/** 提示 */
	private String cue;

	/** 试题图片信息 */
	private List<Blob> images;

	/** 耗时要求 */
	private double runtime;

	/** 内存要求 */
	private double memory;

	/** 总分 */
	private double scores;

	/** 测试的答案样例，生成xml文件进行存储 */
	private String answersXml;

	/** 主题下的回复 */
	private List<Reply> replies;

	/** 主题的总回复数 默认为0 */
	private int replyCount = 0;

	/** 最新的回复记录 */
	private Reply lastReply;

	/** 最近更新时间，以最后一次回复的时间为准 */
	private Date lastUpdateTime;

	/** 点赞数 默认为0 */
	private Integer laudCount = 0;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public QuestionSet getScope() {
		return scope;
	}

	public void setScope(QuestionSet scope) {
		this.scope = scope;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInputFormat() {
		return inputFormat;
	}

	public void setInputFormat(String inputFormat) {
		this.inputFormat = inputFormat;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public String getSampleInput() {
		return sampleInput;
	}

	public void setSampleInput(String sampleInput) {
		this.sampleInput = sampleInput;
	}

	public String getSampleOutput() {
		return sampleOutput;
	}

	public void setSampleOutput(String sampleOutput) {
		this.sampleOutput = sampleOutput;
	}

	public String getCareful() {
		return careful;
	}

	public void setCareful(String careful) {
		this.careful = careful;
	}

	public String getCue() {
		return cue;
	}

	public void setCue(String cue) {
		this.cue = cue;
	}

	public List<Blob> getImages() {
		return images;
	}

	public void setImages(List<Blob> images) {
		this.images = images;
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

	public double getScores() {
		return scores;
	}

	public void setScores(double scores) {
		this.scores = scores;
	}

	public String getAnswersXml() {
		return answersXml;
	}

	public void setAnswersXml(String answersXml) {
		this.answersXml = answersXml;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Reply> replies) {
		this.replies = replies;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public Reply getLastReply() {
		return lastReply;
	}

	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getLaudCount() {
		return laudCount;
	}

	public void setLaudCount(Integer laudCount) {
		this.laudCount = laudCount;
	}

	public String getTopicContent() {
		return topicContent;
	}

	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}

	public Integer getClassify() {
		return classify;
	}

	public void setClassify(Integer classify) {
		this.classify = classify;
	}


}
