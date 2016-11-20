package com.hyr.hubei.polytechnic.university.competition.system.domain;

/**
 * @category 题目类型B的结果类型 程序设计
 * @author huangyueran
 * 
 */
public class ResultB {
	private String i;
	private String o;
	private int s;

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return "ResultB [i=" + i + ", o=" + o + ", s=" + s + "]";
	}

}
