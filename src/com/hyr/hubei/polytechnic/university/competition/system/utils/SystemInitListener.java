package com.hyr.hubei.polytechnic.university.competition.system.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SystemInitListener implements ServletContextListener {

	private Log log = LogFactory.getLog(SystemInitListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.info("====== 启动监听器销毁 ======");

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.info("====== 启动监听器执行 ======");

	}

}
