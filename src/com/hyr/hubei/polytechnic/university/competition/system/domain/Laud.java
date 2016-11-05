package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.util.Date;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 点赞
 * @author 黄跃然
 */
public class Laud {
	/** 点赞的关联主键 userId+topicId */
	private LaudPK laudPK;

	/** 点赞时间 */
	private Date laudTime;

	/** 点赞状态 0用户未点赞 1用户已点赞 默认值0 */
	private Integer status;

	public LaudPK getLaudPK() {
		return laudPK;
	}

	public void setLaudPK(LaudPK laudPK) {
		this.laudPK = laudPK;
	}

	public Date getLaudTime() {
		return laudTime;
	}

	public void setLaudTime(Date laudTime) {
		this.laudTime = laudTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
