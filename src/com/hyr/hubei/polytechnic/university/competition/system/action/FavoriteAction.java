package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Favorite;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;

/**
 * 
 * @author Administrator
 * @category 用户登录登出
 */
@Controller
@Scope("prototype")
public class FavoriteAction extends ModelDrivenBaseAction<Favorite> {

	private Long topicId; // 传递的主题Id

	/**
	 * 收藏/取消收藏
	 * 
	 * @return
	 * @throws AppException
	 */
	public String updateFavoriteStatus() throws AppException {
		Favorite favorite = favoriteService.getByTopicIdAndUserId(topicId, getCurrentUser().getId());
		favorite.setStatus(model.getStatus());

		// 如果是收藏 更新收藏时间
		if (model.getStatus() == 1) {
			favorite.setFavoriteTime(new Date());
		}

		favoriteService.update(favorite);

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
