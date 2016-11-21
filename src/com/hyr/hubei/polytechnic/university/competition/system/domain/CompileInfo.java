package com.hyr.hubei.polytechnic.university.competition.system.domain;

/**
 * @category 返回编译结果
 * 
 * @author huangyueran
 *
 */
public class CompileInfo {
	private int exitVal; // 编译结果状态
	private String compileInfo; // 编译信息

	public int getExitVal() {
		return exitVal;
	}

	public void setExitVal(int exitVal) {
		this.exitVal = exitVal;
	}

	public String getCompileInfo() {
		return compileInfo;
	}

	public void setCompileInfo(String compileInfo) {
		this.compileInfo = compileInfo;
	}

	@Override
	public String toString() {
		return "CompileInfo [exitVal=" + exitVal + ", compileInfo=" + compileInfo + "]";
	}

}
