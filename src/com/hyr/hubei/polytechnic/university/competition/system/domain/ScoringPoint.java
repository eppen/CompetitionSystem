package com.hyr.hubei.polytechnic.university.competition.system.domain;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 评测项
 * @author 黄跃然
 */
public class ScoringPoint {
	/** id主键 */
	private Long id;

	/** TODO 结果 正确 错误... */
	private String result;

	/** 得分 */
	private double score;

	/** CPU使用 */
	private double runtime;

	/** 内存使用 */
	private double memory;

	/** 所属提交答案 */
	private TestAnswer testAnswer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
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

	public TestAnswer getTestAnswer() {
		return testAnswer;
	}

	public void setTestAnswer(TestAnswer testAnswer) {
		this.testAnswer = testAnswer;
	}

	@Override
	public String toString() {
		return "ScoringPoint [id=" + id + ", result=" + result + ", score=" + score + ", runtime=" + runtime
				+ ", memory=" + memory + ", testAnswer=" + testAnswer + "]";
	}

}
