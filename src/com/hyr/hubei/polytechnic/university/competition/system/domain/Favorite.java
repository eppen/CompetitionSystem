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

}
