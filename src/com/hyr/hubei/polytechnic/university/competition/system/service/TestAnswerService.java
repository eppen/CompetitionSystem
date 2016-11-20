package com.hyr.hubei.polytechnic.university.competition.system.service;

import javax.transaction.Transactional;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupport;
import com.hyr.hubei.polytechnic.university.competition.system.domain.TestAnswer;

/**
 * @author huangyueran
 * @category 提交答案Service接口
 */
@Transactional
public interface TestAnswerService extends DaoSupport<TestAnswer> {
	
}
