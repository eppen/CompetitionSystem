package com.hyr.hubei.polytechnic.university.competition.system.service;

import java.util.List;

import javax.transaction.Transactional;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupport;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Favorite;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Laud;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Role;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Visitor;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

/**
 * @author huangyueran
 * @category 访客Service接口
 */
@Transactional
public interface VisitorService extends DaoSupport<Visitor> {

	/**
	 * 根据用户id和访客id找到访客记录
	 * 
	 * @param id
	 * @param id2
	 * @return
	 * @throws AppException
	 */
	Visitor getByUserIdAndVisitorId(Long userId, Long visitorId) throws AppException;

	/**
	 * 根据访问时间 返回18条最新的访客记录
	 * 
	 * @param id
	 * @return
	 * @throws AppException
	 */
	List<Visitor> getVisitorOrderByVisitorTimeByUserId(Long userId) throws AppException;

}
