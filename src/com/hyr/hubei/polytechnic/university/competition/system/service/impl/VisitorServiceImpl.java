package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Visitor;
import com.hyr.hubei.polytechnic.university.competition.system.service.VisitorService;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

/**
 * @author huangyueran
 * @category 访客Service实现
 */
@Service("visitorServiceImpl")
@Transactional
public class VisitorServiceImpl extends DaoSupportImpl<Visitor> implements VisitorService {

	/**
	 * 根据用户id和访客id找到访客记录
	 * 
	 * @param id
	 * @param id2
	 * @return
	 * @throws AppException
	 */
	public Visitor getByUserIdAndVisitorId(Long userId, Long visitorId) throws AppException {
		System.out.println("userId:"+userId);
		System.out.println("VisitorId:"+visitorId); 
		return (Visitor) getSession().createQuery("FROM Visitor v WHERE v.userself.id=? AND v.visitors.id=?")
				.setParameter(0, userId).setParameter(1, visitorId).uniqueResult();
	}

	/**
	 * 根据访问时间 返回18条最新的访客记录
	 * 
	 * @param id
	 * @return
	 * @throws AppException
	 */
	public List<Visitor> getVisitorOrderByVisitorTimeByUserId(Long userId) throws AppException {
		return getSession().createQuery("FROM Visitor v WHERE v.userself.id=? ORDER BY v.visitorTime")
				.setParameter(0, userId).setFirstResult(0).setMaxResults(18).list();
	}

}