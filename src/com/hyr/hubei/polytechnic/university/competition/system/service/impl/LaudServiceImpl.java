package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Favorite;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Laud;
import com.hyr.hubei.polytechnic.university.competition.system.service.LaudService;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

/**
 * @author huangyueran
 * @category 点赞Service实现
 */
@Service("laudServiceImpl")
@Transactional
public class LaudServiceImpl extends DaoSupportImpl<Laud> implements LaudService {

	/**
	 * 根据用户id和主题id查询用户对应主题的收藏记录
	 */
	public Laud getByTopicIdAndUserId(Long topicId, Long userId) throws AppException {
		return (Laud) getSession().createQuery("FROM Laud l WHERE l.laudPK.userId= ? AND l.laudPK.topicId= ? ")
				.setParameter(0, userId).setParameter(1, topicId).uniqueResult(); 
	}
}