package com.hyr.hubei.polytechnic.university.competition.system.installer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hyr.hubei.polytechnic.university.competition.system.domain.Privilege;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Role;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;

/**
 * 安装程序（初始化数据）
 * 
 * @author tyg
 * 
 */
@Component
public class Installer {
	// UPDATE hyr_oa_reply SET deleted = 0;

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		// 1，创建角色
		Role role1 = new Role();
		role1.setName("超级管理员");
		role1.setDescription("超级管理员");
		Role role2 = new Role();
		role2.setName("管理员");
		role2.setDescription("管理员");
		Role role3 = new Role();
		role3.setName("普通用户");
		role3.setDescription("普通用户");

		// =======================================================================
		// 2，权限数据
		Set<Privilege> privileges1 = new HashSet<Privilege>(); // 超级管理员权限
		Set<Privilege> privileges2 = new HashSet<Privilege>(); // 管理员权限
		Set<Privilege> privileges3 = new HashSet<Privilege>(); // 普通用户权限

		// =====普通用户======
		privileges3.add(new Privilege("/homeAction_toIndex", "首页"));
		privileges3.add(new Privilege("/homeAction_toHelpUI", "帮助文档"));
		privileges3.add(new Privilege("/homeAction_toSystemNoticeUI", "系统公告"));
		privileges3.add(new Privilege("/questionSetAction_toQuestionSetListUI", "进入系统"));
		privileges3.add(new Privilege("/testAnswerAction_toAnswerListUI", "评测状态"));
		privileges3.add(new Privilege("/testAnswerAction_toAnswerInfoUI", "评测详情"));
		privileges3.add(new Privilege("/homeAction_toExchangeCentreUI", "交流中心"));
		privileges3.add(new Privilege("/homeAction_toContactInfoUI", "联系方式"));
		privileges3.add(new Privilege("/questionAction_toQuestionListUI", "试题集查看"));
		privileges3.add(new Privilege("/questionAction_toQuestionShowUI", "试题查看"));
		privileges3.add(new Privilege("/testAnswerAction_toSubmitAnswerUI", "提交答案页面"));
		privileges3.add(new Privilege("/scoringPointAction_submitAnswer", "提交答案"));
		privileges3.add(new Privilege("/topicAction_toTopicListUI", "主题列表"));
		privileges3.add(new Privilege("/topicAction_toTopicShowUI", "主题详情"));
		privileges3.add(new Privilege("/testAnswerAction_toQuestionSubmitRecordUI", "提交记录"));

		privileges3.add(new Privilege("/userAction_toUserCenterUI", "个人信息"));
		privileges3.add(new Privilege("/userAction_toUpdateUserUI", "更新用户页面"));
		privileges3.add(new Privilege("/userAction_updateUser", "更新用户"));
		privileges3.add(new Privilege("/userAction_toUpdateUserPasswordUI", "修改密码页面"));
		privileges3.add(new Privilege("/userAction_updateUserPassword", "修改密码"));
		privileges3.add(new Privilege("/userAction_toUserAlertsUI", "用户消息提醒"));
		privileges3.add(new Privilege("/userAction_toUserTopicListUI", "用户主题页面"));
		privileges3.add(new Privilege("/userAction_toUserFavoriteUI", "用户收藏页面"));
		privileges3.add(new Privilege("/visitorAction_toUserVisitorUI", "访客记录页面"));
		privileges3.add(new Privilege("/userAction_toUserInfoUI", "用户信息"));
		privileges3.add(new Privilege("/laudAction_updateFavoriteStatus", "点赞"));
		privileges3.add(new Privilege("/favoriteAction_updateFavoriteStatus", "收藏主题"));
		privileges3.add(new Privilege("/topicAction_toUpdateTopicUI", "编辑主题页面"));
		privileges3.add(new Privilege("/topicAction_updateTopic", "编辑主题"));
		privileges3.add(new Privilege("/topicAction_updateTopicAnswer", "修改答案"));
		privileges3.add(new Privilege("/topicAction_updateTopicType", "修改主题类型"));
		privileges3.add(new Privilege("/topicAction_addTiQuestion", "加入题库"));
		privileges3.add(new Privilege("/topicAction_toCreateTopicUI", "创建新主题页面"));
		privileges3.add(new Privilege("/topicAction_createTopic", "创建主题"));
		privileges3.add(new Privilege("/topicAction_createTopicAnswer", "创建主题答案"));

		// =====管理员======
		privileges2.add(new Privilege("/homeAction_toIndex", "首页"));
		privileges2.add(new Privilege("/homeAction_toHelpUI", "帮助文档"));
		privileges2.add(new Privilege("/homeAction_toSystemNoticeUI", "系统公告"));
		privileges2.add(new Privilege("/questionSetAction_toQuestionSetListUI", "进入系统"));
		privileges2.add(new Privilege("/testAnswerAction_toAnswerListUI", "评测状态"));
		privileges2.add(new Privilege("/testAnswerAction_toAnswerInfoUI", "评测详情"));
		privileges2.add(new Privilege("/homeAction_toExchangeCentreUI", "交流中心"));
		privileges2.add(new Privilege("/homeAction_toContactInfoUI", "联系方式"));
		privileges2.add(new Privilege("/questionAction_toQuestionListUI", "试题集查看"));
		privileges2.add(new Privilege("/questionAction_toQuestionShowUI", "试题查看"));
		privileges2.add(new Privilege("/testAnswerAction_toSubmitAnswerUI", "提交答案页面"));
		privileges2.add(new Privilege("/scoringPointAction_submitAnswer", "提交答案"));
		privileges2.add(new Privilege("/topicAction_toTopicListUI", "主题列表"));
		privileges2.add(new Privilege("/topicAction_toTopicShowUI", "主题详情"));
		privileges2.add(new Privilege("/testAnswerAction_toQuestionSubmitRecordUI", "提交记录"));

		privileges2.add(new Privilege("/userAction_toUserCenterUI", "个人信息"));
		privileges2.add(new Privilege("/userAction_toUpdateUserUI", "更新用户页面"));
		privileges2.add(new Privilege("/userAction_updateUser", "更新用户"));
		privileges2.add(new Privilege("/userAction_toUpdateUserPasswordUI", "修改密码页面"));
		privileges2.add(new Privilege("/userAction_updateUserPassword", "修改密码"));
		privileges2.add(new Privilege("/userAction_toUserAlertsUI", "用户消息提醒"));
		privileges2.add(new Privilege("/userAction_toUserTopicListUI", "用户主题页面"));
		privileges2.add(new Privilege("/userAction_toUserFavoriteUI", "用户收藏页面"));
		privileges2.add(new Privilege("/visitorAction_toUserVisitorUI", "访客记录页面"));
		privileges2.add(new Privilege("/userAction_toUserInfoUI", "用户信息"));
		privileges2.add(new Privilege("/laudAction_updateFavoriteStatus", "点赞"));
		privileges2.add(new Privilege("/favoriteAction_updateFavoriteStatus", "收藏主题"));
		privileges2.add(new Privilege("/topicAction_toUpdateTopicUI", "编辑主题页面"));
		privileges2.add(new Privilege("/topicAction_updateTopic", "编辑主题"));
		privileges2.add(new Privilege("/topicAction_updateTopicAnswer", "修改答案"));
		privileges2.add(new Privilege("/topicAction_updateTopicType", "修改主题类型"));
		privileges2.add(new Privilege("/topicAction_addTiQuestion", "加入题库"));
		privileges2.add(new Privilege("/topicAction_toCreateTopicUI", "创建新主题页面"));
		privileges2.add(new Privilege("/topicAction_createTopic", "创建主题"));
		privileges2.add(new Privilege("/topicAction_createTopicAnswer", "创建主题答案"));
		privileges2.add(new Privilege("/userAction_toUserManageListUI", "用户管理页面"));
		privileges2.add(new Privilege("/userAction_updateUserIsBan", "用户禁言设置"));
		privileges2.add(new Privilege("/questionSetAction_toQuestionSetManageListUI", "试题集管理页面"));
		privileges2.add(new Privilege("/questionSetAction_toUpdateQuestionSetUI", "试题集修改页面"));
		privileges2.add(new Privilege("/questionSetAction_updateQuestion", "修改试题集"));
		privileges2.add(new Privilege("/questionSetAction_deleteQuestionSet", "删除试题集"));
		privileges2.add(new Privilege("/questionAction_toQuestionManageListUI", "试题管理页面"));
		privileges2.add(new Privilege("/questionAction_toUpdateQuestionUI", "试题修改页面"));
		privileges2.add(new Privilege("/questionAction_updateQuestion", "试题修改"));
		privileges2.add(new Privilege("/questionAction_deleteQuestion", "试题删除"));
		privileges2.add(new Privilege("/questionSetAction_toCreateQuestionSetUI", "创建试题集页面"));
		privileges2.add(new Privilege("/questionSetAction_createQuestionSet", "创建试题集"));
		privileges2.add(new Privilege("/questionAction_toCreateQuestionUI", "创建试题页面"));
		privileges2.add(new Privilege("/questionAction_createQuestion", "创建试题"));
		privileges2.add(new Privilege("/questionAction_createQuestionAnswer", "创建答案"));

		// =====超级管理员======
		privileges1.add(new Privilege("/homeAction_toIndex", "首页"));
		privileges1.add(new Privilege("/homeAction_toHelpUI", "帮助文档"));
		privileges1.add(new Privilege("/homeAction_toSystemNoticeUI", "系统公告"));
		privileges1.add(new Privilege("/questionSetAction_toQuestionSetListUI", "进入系统"));
		privileges1.add(new Privilege("/testAnswerAction_toAnswerListUI", "评测状态"));
		privileges1.add(new Privilege("/testAnswerAction_toAnswerInfoUI", "评测详情"));
		privileges1.add(new Privilege("/homeAction_toExchangeCentreUI", "交流中心"));
		privileges1.add(new Privilege("/homeAction_toContactInfoUI", "联系方式"));
		privileges1.add(new Privilege("/questionAction_toQuestionListUI", "试题集查看"));
		privileges1.add(new Privilege("/questionAction_toQuestionShowUI", "试题查看"));
		privileges1.add(new Privilege("/testAnswerAction_toSubmitAnswerUI", "提交答案页面"));
		privileges1.add(new Privilege("/scoringPointAction_submitAnswer", "提交答案"));
		privileges1.add(new Privilege("/topicAction_toTopicListUI", "主题列表"));
		privileges1.add(new Privilege("/topicAction_toTopicShowUI", "主题详情"));
		privileges1.add(new Privilege("/testAnswerAction_toQuestionSubmitRecordUI", "提交记录"));

		privileges1.add(new Privilege("/userAction_toUserCenterUI", "个人信息"));
		privileges1.add(new Privilege("/userAction_toUpdateUserUI", "更新用户页面"));
		privileges1.add(new Privilege("/userAction_updateUser", "更新用户"));
		privileges1.add(new Privilege("/userAction_toUpdateUserPasswordUI", "修改密码页面"));
		privileges1.add(new Privilege("/userAction_updateUserPassword", "修改密码"));
		privileges1.add(new Privilege("/userAction_toUserAlertsUI", "用户消息提醒"));
		privileges1.add(new Privilege("/userAction_toUserTopicListUI", "用户主题页面"));
		privileges1.add(new Privilege("/userAction_toUserFavoriteUI", "用户收藏页面"));
		privileges1.add(new Privilege("/visitorAction_toUserVisitorUI", "访客记录页面"));
		privileges1.add(new Privilege("/userAction_toUserInfoUI", "用户信息"));
		privileges1.add(new Privilege("/laudAction_updateFavoriteStatus", "点赞"));
		privileges1.add(new Privilege("/favoriteAction_updateFavoriteStatus", "收藏主题"));
		privileges1.add(new Privilege("/topicAction_toUpdateTopicUI", "编辑主题页面"));
		privileges1.add(new Privilege("/topicAction_updateTopic", "编辑主题"));
		privileges1.add(new Privilege("/topicAction_updateTopicAnswer", "修改答案"));
		privileges1.add(new Privilege("/topicAction_updateTopicType", "修改主题类型"));
		privileges1.add(new Privilege("/topicAction_addTiQuestion", "加入题库"));
		privileges1.add(new Privilege("/topicAction_toCreateTopicUI", "创建新主题页面"));
		privileges1.add(new Privilege("/topicAction_createTopic", "创建主题"));
		privileges1.add(new Privilege("/topicAction_createTopicAnswer", "创建主题答案"));
		privileges1.add(new Privilege("/userAction_toUserManageListUI", "用户管理页面"));
		privileges1.add(new Privilege("/userAction_updateUserIsBan", "用户禁言设置"));
		privileges1.add(new Privilege("/questionSetAction_toQuestionSetManageListUI", "试题集管理页面"));
		privileges1.add(new Privilege("/questionSetAction_toUpdateQuestionSetUI", "试题集修改页面"));
		privileges1.add(new Privilege("/questionSetAction_updateQuestion", "修改试题集"));
		privileges1.add(new Privilege("/questionSetAction_deleteQuestionSet", "删除试题集"));
		privileges1.add(new Privilege("/questionAction_toQuestionManageListUI", "试题管理页面"));
		privileges1.add(new Privilege("/questionAction_toUpdateQuestionUI", "试题修改页面"));
		privileges1.add(new Privilege("/questionAction_updateQuestion", "试题修改"));
		privileges1.add(new Privilege("/questionAction_deleteQuestion", "试题删除"));
		privileges1.add(new Privilege("/questionSetAction_toCreateQuestionSetUI", "创建试题集页面"));
		privileges1.add(new Privilege("/questionSetAction_createQuestionSet", "创建试题集"));
		privileges1.add(new Privilege("/questionAction_toCreateQuestionUI", "创建试题页面"));
		privileges1.add(new Privilege("/questionAction_createQuestion", "创建试题"));
		privileges1.add(new Privilege("/questionAction_createQuestionAnswer", "创建答案"));

		privileges1.add(new Privilege("/userAction_toCreateUserUI", "新建用户页面"));
		privileges1.add(new Privilege("/userAction_resetUserPassword", "重置密码"));
		privileges1.add(new Privilege("/userAction_updateUserRole", "修改用户权限"));
		privileges1.add(new Privilege("/userAction_createUser", "新建用户"));

		role3.setPrivileges(privileges3);
		role2.setPrivileges(privileges2);
		role1.setPrivileges(privileges1);

		session.save(role1);
		session.save(role2);
		session.save(role3);
		// =======================================================================
		// 3，超级管理员
		User user = new User();
		user.setUsername("admin");
		user.setName("超级管理员");
		user.setPassword(DigestUtils.md5Hex("admin")); // 密码要使用MD5摘要
		user.setRole(role1);
		user.setGender("女");
		user.setCreateTime(new Date());

		session.save(user);
		// =======================================================================
	}

	public static void main(String[] args) {
		System.out.println("正在初始化数据...");

		// 一定要从Spring容器中取出对象
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Installer installer = (Installer) ac.getBean("installer");
		// 执行安装
		installer.install();

		System.out.println("初始化数据完毕！");
	}

}
