package com.hyr.hubei.polytechnic.university.competition.system.service;

import javax.transaction.Transactional;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupport;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Role;

/**
 * @author huangyueran
 * @category 岗位Service接口
 */
@Transactional
public interface RoleService extends DaoSupport<Role>
{

}
