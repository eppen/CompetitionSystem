package com.hyr.hubei.polytechnic.university.competition.system.utils;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hyr.hubei.polytechnic.university.competition.system.domain.Privilege;
import com.hyr.hubei.polytechnic.university.competition.system.service.PrivilegeService;

public class SystemInitListener implements ServletContextListener {

	private Log log = LogFactory.getLog(SystemInitListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.info("====== 启动监听器销毁 ======");

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		log.info("====== 启动监听器执行 ======");

		ServletContext application = sce.getServletContext();

		// 从Spring的容器中取出PrivilegeService的对象实例.
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application); // 获取Spring的监听器中创建的Spring容器对象
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean("privilegeServiceImpl");

		// 1，查询所有顶级的权限列表并放到application作用域中
		List<Privilege> topPrivilegeList;

		// 2，查询出所有的权限的URL集合并放到application作用域中
		List<String> allPrivilegeUrls;
		try {
			allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
			application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
			System.out.println("====== allPrivilegeUrls已经放到application作用域中了！ ======");
			log.info("====== allPrivilegeUrls已经放到application作用域中了！ ======");
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
