package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Topic;
import com.hyr.hubei.polytechnic.university.competition.system.service.TopicService;

/**
 * @author huangyueran
 * @category 主题Service实现
 */
@Service("topicServiceImpl")
@Transactional
public class TopicServiceImpl extends DaoSupportImpl<Topic> implements TopicService {

}
