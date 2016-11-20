package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Reply;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Topic;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author huangyueran
 * @category 评论Action
 */
@Controller
@Scope("prototype")
public class ReplyAction extends ModelDrivenBaseAction<Reply> {
	private Long topicId;

	/** 发表回复 */
	public String add() throws AppException {
		// 封装对象
		Reply reply = new Reply();
		Topic topic = topicService.getById(topicId);
		reply.setContent(model.getContent());
		reply.setTopic(topic);
		reply.setAuthor(getCurrentUser()); // 当前登录的用户
		reply.setIpAddr(getRequestIP()); // 客户端的IP地址
		reply.setPostTime(new Date());

		// 更新相关主题字段 replyCount+1
		topic.setReplyCount(topic.getReplyCount() + 1);
		// 更新主题最新评论
		topic.setLastReply(reply);
		topic.setLastUpdateTime(reply.getPostTime());
		// 如果不是本人回复本人 通知用户 更新用户消息字段 replysCount +1
		if (topic.getAuthor().getId() != getCurrentUser().getId()) {
			topic.getAuthor().setReplysCount(topic.getAuthor().getReplysCount() + 1);

		}
		
		// 调用业务方法
		replyService.save(reply);

		topicService.update(topic);
		return "toTopicShow";
	}

	/** 删除评论 */
	/**
	 * @return
	 * @throws AppException
	 */
	public String deleteReply() throws AppException {
		// 根据id删除评论

		Reply reply = replyService.getById(model.getId());
		Topic topic = reply.getTopic();

		// 主题评论数-1
		topic.setReplyCount(topic.getReplyCount() - 1);
		topic.setLastReply(null);

		topicService.update(topic);

		replyService.delete(model.getId());

		// 跳转到主题详情页面
		return "toTopicShow";
	}

	/** 发表评论页面 取消 暂不做 */

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
