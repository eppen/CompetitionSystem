package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.domain.TestAnswer;
import com.hyr.hubei.polytechnic.university.competition.system.service.TestAnswerService;

/**
 * @author huangyueran
 * @category 提交答案Service实现
 */
@Service("testAnswerServiceImpl")
@Transactional
public class TestAnswerServiceImpl extends DaoSupportImpl<TestAnswer> implements TestAnswerService {

}