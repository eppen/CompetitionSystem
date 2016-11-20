package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Role;
import com.hyr.hubei.polytechnic.university.competition.system.service.RoleService;

/**
 * @author huangyueran
 * @category 岗位Service实现
 */
@Service("roleServiceImpl")
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService {

}