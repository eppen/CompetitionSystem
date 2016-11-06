package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Privilege;
import com.hyr.hubei.polytechnic.university.competition.system.service.PrivilegeService;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

/**
 * @author Administrator
 * @category 权限Service实现
 */
@Service("privilegeServiceImpl")
@Transactional
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService {

	public List<String> getAllPrivilegeUrls() throws AppException {
		Session session = getSession();
		return session
				.createQuery(//
						"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
	}

}
