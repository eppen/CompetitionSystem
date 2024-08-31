package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.domain.ScoringPoint;
import com.hyr.hubei.polytechnic.university.competition.system.service.ScoringPointService;

/**
 * @author huangyueran
 * @category 答案评测点Service实现
 */
@Service("scoringPointServiceImpl")
@Transactional
public class ScoringPointServiceImpl extends DaoSupportImpl<ScoringPoint> implements ScoringPointService {

}