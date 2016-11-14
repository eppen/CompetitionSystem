package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Favorite;
import com.hyr.hubei.polytechnic.university.competition.system.service.FavoriteService;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

/**
 * @author Administrator
 * @category 收藏Service实现
 */
@Service("favoriteServiceImpl")
@Transactional
public class FavoriteServiceImpl extends DaoSupportImpl<Favorite> implements FavoriteService {

	/**
	 * 根据用户id和主题id查询用户对应主题的收藏记录
	 */
	public Favorite getByTopicIdAndUserId(Long topicId, Long userId) throws AppException {
		return (Favorite) getSession()
				.createQuery("FROM Favorite f WHERE f.favoritePK.userId= ? AND f.favoritePK.topicId= ? ")
				.setParameter(0, userId).setParameter(1, topicId).uniqueResult();

	}

	/**
	 * 根据用户id查询主题 用户已收藏记录
	 * 
	 * @param id
	 * @return
	 * @throws AppException
	 */
	@SuppressWarnings("unchecked")
	public List<Favorite> getByUserId(Long userId) throws AppException {
		return getSession().createQuery("FROM Favorite f WHERE f.favoritePK.userId= ? And f.status = 1 ").setParameter(0, userId) 
				.list(); 
	}
}