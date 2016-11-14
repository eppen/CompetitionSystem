package com.hyr.hubei.polytechnic.university.competition.system.service;

import java.util.List;

import javax.transaction.Transactional;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupport;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Favorite;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

/**
 * @author huangyueran
 * @category 收藏Service接口
 */
@Transactional
public interface FavoriteService extends DaoSupport<Favorite> {

	/**
	 * 根据用户id和主题id查询用户对应主题的收藏记录
	 * 
	 * @param topicId
	 * @param userId
	 * @return
	 * @throws AppException
	 */
	Favorite getByTopicIdAndUserId(Long topicId, Long userId) throws AppException;

	/**
	 * 根据用户id查询主题 用户已收藏记录
	 * 
	 * @param id
	 * @return
	 * @throws AppException
	 */
	List<Favorite> getByUserId(Long userId) throws AppException;

}
