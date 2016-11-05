package com.hyr.hubei.polytechnic.university.competition.system.domain;

/**
 * 2016-11-4 21:25:37
 * 
 * @category 回复
 * @author 黄跃然
 */
public class Reply extends Article {
	/** 回复所属主题 */
	private Topic topic;

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

}
