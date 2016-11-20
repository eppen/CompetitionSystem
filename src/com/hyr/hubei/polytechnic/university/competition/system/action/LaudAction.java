package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Favorite;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Laud;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Topic;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author Administrator
 * @category 点赞Action
 */
@Controller
@Scope("prototype")
public class LaudAction extends ModelDrivenBaseAction<Favorite> {

	private Long topicId; // 传递的主题Id

	/**
	 * 点赞/取消点赞
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updateFavoriteStatus() throws AppException {
		Laud laud = laudService.getByTopicIdAndUserId(topicId, getCurrentUser().getId());
		Topic topic = topicService.getById(topicId);
		
		// 如果是点赞 更新点赞时间 
		// 验证前端字段和数据库字段是否匹配正确 前端 && 数据库
		if (model.getStatus() == 1 && laud.getStatus() == 0) {
			laud.setLaudTime(new Date());
			// 主题laudCount字段+1
			topic.setLaudCount(topic.getLaudCount() + 1);
		} else if (model.getStatus() == 0 && laud.getStatus() == 1) {
			// 主题laudCount字段-1
			topic.setLaudCount(topic.getLaudCount() - 1);  
		}else {
			return "error";
		}

		laud.setStatus(model.getStatus());
		
		laudService.update(laud);
		topicService.update(topic);

		// 返回到主题页面
		return "toTopicShow";
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

}
