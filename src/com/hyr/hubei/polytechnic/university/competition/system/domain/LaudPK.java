package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.io.Serializable;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 点赞 关联主键
 * @author 黄跃然
 */
public class LaudPK implements Serializable {
	/** 点赞用户id */
	private Long userId;

	/** 点赞主题id */
	private Long topicId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	@Override
	public int hashCode() {
		return topicId.hashCode() + userId.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LaudPK) {
			LaudPK laudPK = (LaudPK) obj;
			if (laudPK.getTopicId().equals(topicId) && laudPK.getUserId().equals(userId)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
