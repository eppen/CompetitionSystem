package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.util.Date;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 试题
 * @author 黄跃然
 */
public class Question {
	/** 试题id主键 */
	private Long id;

	/** 试题类型 0无类型 1结果填空 2程序设定 3代码填空。 要和创建主题表中对应一致 */
	private Integer type = 0;

	/** 语言 JAVA */
	private String language;

	/** 所属试题集 */
	private QuestionSet scope;

	/** 试题标题 */
	private String title;

	/** 试题描述 */
	private String description;

	/** 试题内容 */
	private String content;

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

	/** 耗时要求 */
	private double runtime;

	/** 内存要求 */
	private double memory;

	/** 创建时间 */
	private Date createTime;

	/** 试题分数 */
	private double scores;

	/** 试题答案 存放xml格式文件 */
	private String answersXml;

	/** 是否删除 0未删除 1已删除 默认值0 */
	private Integer isdel = 0;

	/** 图片上传地址 */
	private String imagePath1;
	private String imagePath2;
	private String imagePath3;
	private String imagePath4;
	private String imagePath5;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImagePath1() {
		return imagePath1;
	}

	public void setImagePath1(String imagePath1) {
		this.imagePath1 = imagePath1;
	}

	public String getImagePath2() {
		return imagePath2;
	}

	public void setImagePath2(String imagePath2) {
		this.imagePath2 = imagePath2;
	}

	public String getImagePath3() {
		return imagePath3;
	}

	public void setImagePath3(String imagePath3) {
		this.imagePath3 = imagePath3;
	}

	public String getImagePath4() {
		return imagePath4;
	}

	public void setImagePath4(String imagePath4) {
		this.imagePath4 = imagePath4;
	}

	public String getImagePath5() {
		return imagePath5;
	}

	public void setImagePath5(String imagePath5) {
		this.imagePath5 = imagePath5;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", type=" + type + ", language=" + language + ", scope=" + scope + ", title="
				+ title + ", description=" + description + ", content=" + content + ", inputFormat=" + inputFormat
				+ ", outputFormat=" + outputFormat + ", sampleInput=" + sampleInput + ", sampleOutput=" + sampleOutput
				+ ", careful=" + careful + ", cue=" + cue + ", runtime=" + runtime + ", memory=" + memory
				+ ", createTime=" + createTime + ", scores=" + scores + ", answersXml=" + answersXml + ", isdel="
				+ isdel + ", imagePath1=" + imagePath1 + ", imagePath2=" + imagePath2 + ", imagePath3=" + imagePath3
				+ ", imagePath4=" + imagePath4 + ", imagePath5=" + imagePath5 + "]";
	}

}
