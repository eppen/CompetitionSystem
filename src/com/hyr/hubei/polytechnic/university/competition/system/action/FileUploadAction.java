package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class FileUploadAction extends ActionSupport {
	private static final long serialVersionUID = -6727274988289588722L;
	private static final int BUFFER_SIZE = 16 * 1024;
	private List<File> myFile = new ArrayList<File>();
	private List<String> contentType = new ArrayList<String>();
	private List<String> fileName = new ArrayList<String>(); // 文件名
	private List<String> imageFileName = new ArrayList<String>();
	private String caption;

	private static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	@Override
	public String execute() {
		File file = new File(ServletActionContext.getServletContext() // 得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
				.getRealPath("UploadImages") + "/");
		if (!file.isDirectory() || !file.exists()) {
			file.mkdirs();
		}

		if (myFile == null)
			return INPUT;
		for (int i = 0; i < myFile.size(); i++) {
			// TODO 将上次的图片文件名改为UUID 保存两份
			// imageFileName.add(new Date().getTime() +
			// getExtention(this.getMyFileFileName().get(i)));
			imageFileName.add(new Date().getTime() + myFile.get(i).getName());
			File imageFile = new File(ServletActionContext.getServletContext() // 得到图片保存的位置(根据root来得到图片保存的路径在tomcat下的该工程里)
					.getRealPath("UploadImages") + "/" + imageFileName);
			copy(myFile.get(i), imageFile); // 把图片写入到上面设置的路径里

		}
		return SUCCESS;
	}

	public List<File> getMyFile() {
		return myFile;
	}

	public void setMyFile(List<File> myFile) {
		this.myFile = myFile;
	}

	public List<String> getContentType() {
		return contentType;
	}

	public void setContentType(List<String> contentType) {
		this.contentType = contentType;
	}

	public List<String> getMyFileFileName() {
		return fileName;
	}

	public void setMyFileFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	public List<String> getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(List<String> imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public static int getBufferSize() {
		return BUFFER_SIZE;
	}

}
