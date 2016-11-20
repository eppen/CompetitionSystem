package com.hyr.hubei.polytechnic.university.competition.system.service;

import java.util.List;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupport;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

public interface UserService extends DaoSupport<User> {

	/**
	 * 验证用户名与密码，如果正确就返回这个用户，否则返回空
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 * @throws AppException
	 */
	User findByLoginNameAndPassword(String loginName, String password) throws AppException;

	/**
	 * 找到所有未删除的用户
	 * 
	 * @throws AppException
	 */
	List<User> findAllActivityUser() throws AppException;

}
