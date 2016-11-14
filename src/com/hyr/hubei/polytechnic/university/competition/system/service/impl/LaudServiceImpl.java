package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Laud;
import com.hyr.hubei.polytechnic.university.competition.system.service.LaudService;

/**
 * @author Administrator
 * @category 点赞Service实现
 */
@Service("laudServiceImpl")
@Transactional
public class LaudServiceImpl extends DaoSupportImpl<Laud> implements LaudService {

}