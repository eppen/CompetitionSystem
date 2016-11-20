package com.hyr.hubei.polytechnic.university.competition.system.service;

import javax.transaction.Transactional;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupport;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Favorite;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Laud;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Role;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

/**
 * @author huangyueran
 * @category 点赞Service接口
 */
@Transactional
public interface LaudService extends DaoSupport<Laud> {
	/**
	 * 根据用户id和主题id查询用户对应主题的收藏记录
	 * 
	 * @param topicId
	 * @param userId
	 * @return
	 * @throws AppException
	 */
	Laud getByTopicIdAndUserId(Long topicId, Long userId) throws AppException;
}
