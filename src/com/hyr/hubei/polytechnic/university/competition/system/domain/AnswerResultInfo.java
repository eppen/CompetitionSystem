package com.hyr.hubei.polytechnic.university.competition.system.domain;

/**
 * @category 编译执行返回结果
 * 
 * @author huangyueran
 *
 */
public class AnswerResultInfo {
	private int exitVal; // 编译执行信息
	private String answerOutput; // 程序执行输出结果
	private long execTime; // 执行时间
	private long execMemo; // 执行内存

	public int getExitVal() {
		return exitVal;
	}

	public void setExitVal(int exitVal) {
		this.exitVal = exitVal;
	}

	public String getAnswerOutput() {
		return answerOutput;
	}

	public void setAnswerOutput(String answerOutput) {
		this.answerOutput = answerOutput;
	}

	public long getExecTime() {
		return execTime;
	}

	public void setExecTime(long execTime) {
		this.execTime = execTime;
	}

	public long getExecMemo() {
		return execMemo;
	}

	public void setExecMemo(long execMemo) {
		this.execMemo = execMemo;
	}

	@Override
	public String toString() {
		return "AnswerResultInfo [exitVal=" + exitVal + ", answerOutput=" + answerOutput + ", execTime=" + execTime
				+ ", execMemo=" + execMemo + "]";
	}

}
