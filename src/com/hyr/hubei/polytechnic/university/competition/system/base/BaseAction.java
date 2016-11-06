package com.hyr.hubei.polytechnic.university.competition.system.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.service.PrivilegeService;
import com.hyr.hubei.polytechnic.university.competition.system.service.RoleService;
import com.hyr.hubei.polytechnic.university.competition.system.service.UserService;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	protected int pageNum = 1; // 页面获取的当前页 默认为第一页。

	// ===================== 声明Service ====================
	@Resource(name = "userServiceImpl")
	protected UserService userService;
	@Resource(name = "privilegeServiceImpl")
	protected PrivilegeService privilegeService;
	@Resource(name = "roleServiceImpl")
	protected RoleService roleService;

	// ========================== 工具方法 ==========================

	/**
	 * 获取当前登录的用户
	 */
	public User getCurrentUser() throws AppException {
		return (User) ActionContext.getContext().getSession().get("user");
	}

	/**
	 * 获取客户户的IP地址
	 * 
	 * @return
	 */
	public String getRequestIP() throws AppException {
		return ServletActionContext.getRequest().getRemoteAddr();
	}

	/**
	 * 保存上传的文件，并返回在服务器端真实的存储路径
	 * 
	 * @param upload
	 * @return
	 */
	protected String saveUploadFile(File upload) {
		// >> 1, 得到在保存的文件路径的真实地址
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload_files");
		String datePath = sdf.format(new Date());

		// >> 2, 如果文件夹不存在，就创建
		File dir = new File(basePath + datePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String path = basePath + datePath + UUID.randomUUID().toString() + System.currentTimeMillis(); // 注意同名的问题，可以使用uuid做为文件名
		File destFile = new File(path);

		// >> 3, 移动文件
		upload.renameTo(destFile);
		return path;
	}

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum
	 *            the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
