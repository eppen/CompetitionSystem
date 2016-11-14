package com.hyr.hubei.polytechnic.university.competition.system.domain;

import java.util.Date;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 收藏
 * @author 黄跃然
 */
public class Favorite {
	/** 收藏表的关联主键 userId+topicId */
	private FavoritePK favoritePK;

	/** 收藏时间 */
	private Date favoriteTime;

	/** 收藏状态 0用户未收藏 1用户已收藏 默认值0 */
	private Integer status = 0;

	public FavoritePK getFavoritePK() {
		return favoritePK;
	}

	public void setFavoritePK(FavoritePK favoritePK) {
		this.favoritePK = favoritePK;
	}

	public Date getFavoriteTime() {
		return favoriteTime;
	}

	public void setFavoriteTime(Date favoriteTime) {
		this.favoriteTime = favoriteTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
