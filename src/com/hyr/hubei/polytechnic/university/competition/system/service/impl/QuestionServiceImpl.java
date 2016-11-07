package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Question;
import com.hyr.hubei.polytechnic.university.competition.system.service.QuestionService;

/**
 * @author huangyueran
 * @category 试题Service实现
 */
@Service("questionServiceImpl")
@Transactional
public class QuestionServiceImpl extends DaoSupportImpl<Question> implements QuestionService {

}
