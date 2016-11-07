package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.domain.QuestionSet;
import com.hyr.hubei.polytechnic.university.competition.system.service.QuestionSetService;

/**
 * @author huangyueran
 * @category 试题集Service实现
 */
@Service("questionSetServiceImpl")
@Transactional
public class QuestionSetServiceImpl extends DaoSupportImpl<QuestionSet> implements QuestionSetService {

}
