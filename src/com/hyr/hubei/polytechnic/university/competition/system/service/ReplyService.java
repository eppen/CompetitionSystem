package com.hyr.hubei.polytechnic.university.competition.system.service;

import java.util.List;

import com.hyr.hubei.polytechnic.university.competition.system.base.DaoSupport;
import com.hyr.hubei.polytechnic.university.competition.system.domain.PageBean;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Reply;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Topic;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

/**
 * @author huangyueran
 * @category 回复Service接口
 */
public interface ReplyService extends DaoSupport<Reply> {

	/**
	 * 查询指定主题中的回复列表。排序：最新的排到最后
	 * 
	 * @param topic
	 * @return
	 * @throws AppException
	 */
	List<Reply> findByTopic(Topic topic) throws AppException;

	/**
	 * 查询分页的回复列表数据
	 * 
	 * @param pageNum
	 * @param topic
	 * @return
	 * @throws AppException
	 */
	@Deprecated
	PageBean getPageBeanByTopic(int pageNum, Topic topic) throws AppException;

	/**
	 * 根据用户删除用户所有回复
	 * 
	 * @param id
	 * @throws AppException
	 */
	void deleteReplyByUser(User user) throws AppException;

}
