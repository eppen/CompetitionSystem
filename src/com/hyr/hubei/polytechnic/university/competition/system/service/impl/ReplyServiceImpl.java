package com.hyr.hubei.polytechnic.university.competition.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupportImpl;
import com.hyr.hubei.polytechnic.university.competition.system.cxf.Configuration;
import com.hyr.hubei.polytechnic.university.competition.system.domain.PageBean;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Reply;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Topic;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.service.ReplyService;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

/**
 * 
 * @author huangyueran
 * @category 回复Service实现
 */
@Transactional
@Service("replyServiceImpl")
@SuppressWarnings("unchecked")
public class ReplyServiceImpl extends DaoSupportImpl<Reply> implements ReplyService {

	/**
	 * 查询指定主题中的回复列表。排序：最新的排到最后
	 * 
	 * @param topic
	 * @return
	 * @throws AppException
	 */
	public List<Reply> findByTopic(Topic topic) throws AppException {
		return getSession().createQuery("FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC").setParameter(0, topic)
				.list();
	}

	/**
	 * 查询分页的回复列表数据
	 * 
	 * @param pageNum
	 * @param topic
	 * @return
	 * @throws AppException
	 */
	public PageBean getPageBeanByTopic(int pageNum, Topic topic) throws AppException {
		// 获取每页显示的记录数目
		int pageSize = Configuration.getPageSize();

		List list = getSession().createQuery("FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")
				.setParameter(0, topic).setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();

		Long count = (Long) getSession().createQuery("SELECT COUNT(*) FROM Reply r WHERE r.topic=? ")
				.setParameter(0, topic).uniqueResult();

		return new PageBean(pageNum, pageSize, list, count.intValue());
	}

	/**
	 * 根据用户ID删除用户所有回复
	 * 
	 * @param id
	 * @throws AppException
	 */
	public void deleteReplyByUser(User user) throws AppException {
		getSession().createQuery("DELETE Reply r WHERE r.author=? ").setParameter(0, user).executeUpdate();

	}

}
