package com.hyr.hubei.polytechnic.university.competition.system.service;

import javax.transaction.Transactional;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupport;
import com.hyr.hubei.polytechnic.university.competition.system.domain.ScoringPoint;
import com.hyr.hubei.polytechnic.university.competition.system.domain.TestAnswer;

/**
 * @author huangyueran
 * @category 评测点Service接口
 */
@Transactional
public interface ScoringPointService extends DaoSupport<ScoringPoint> {

}
