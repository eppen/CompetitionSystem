package com.hyr.hubei.polytechnic.university.competition.system.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport {

	private static final long serialVersionUID = 9099279900946747196L;

	public HomeAction() throws AppException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 首页
	 */
	public String toIndex() throws Exception {
		return "toIndex";
	}

	/**
	 * 帮助页面
	 */
	public String toHelpUI() throws Exception {
		return "toHelpUI";
	}

	/**
	 * 系统公告页面
	 */
	public String toSystemNoticeUI() throws Exception {
		return "toSystemNoticeUI";
	}

	/**
	 * 进入系统页面
	 */
	public String toCompetitionSystemUI() throws Exception {
		return "toCompetitionSystemUI";
	}

	/**
	 * 交流中心页面
	 */
	public String toExchangeCentreUI() throws Exception {
		return "toExchangeCentreUI";
	}

	/**
	 * 联系方式页面
	 */
	public String toContactInfoUI() throws Exception {
		return "toContactInfoUI";
	}

}
