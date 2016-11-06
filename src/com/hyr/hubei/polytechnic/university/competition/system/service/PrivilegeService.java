package com.hyr.hubei.polytechnic.university.competition.system.service;

import java.util.List;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupport;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Privilege;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

/**
 * @author Administrator
 * @category 权限Service接口
 */
public interface PrivilegeService extends DaoSupport<Privilege> {

	/**
	 * 获取所有权限的URL
	 * 
	 * @return
	 * @throws AppException
	 */
	List<String> getAllPrivilegeUrls() throws AppException;

}
