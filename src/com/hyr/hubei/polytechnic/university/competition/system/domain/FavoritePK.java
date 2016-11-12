package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.io.Serializable;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 收藏的联合主键
 * @author 黄跃然
 */
public class FavoritePK implements Serializable {
	/** 收藏用户id */
	private Long userId;

	/** 收藏主题id */
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
		if (obj instanceof FavoritePK) {
			FavoritePK favoritePK = (FavoritePK) obj;
			if (favoritePK.getTopicId().equals(topicId) && favoritePK.getUserId().equals(userId)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
