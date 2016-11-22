package com.hyr.hubei.polytechnic.university.competition.system.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hyr.hubei.polytechnic.university.competition.system.base.ModelDrivenBaseAction;
import com.hyr.hubei.polytechnic.university.competition.system.domain.AnswerResultInfo;
import com.hyr.hubei.polytechnic.university.competition.system.domain.CompileInfo;
import com.hyr.hubei.polytechnic.university.competition.system.domain.Question;
import com.hyr.hubei.polytechnic.university.competition.system.domain.ResultB;
import com.hyr.hubei.polytechnic.university.competition.system.domain.ScoringPoint;
import com.hyr.hubei.polytechnic.university.competition.system.domain.TestAnswer;
import com.hyr.hubei.polytechnic.university.competition.system.domain.User;
import com.hyr.hubei.polytechnic.university.competition.system.service.QuestionSetService;
import com.hyr.hubei.polytechnic.university.competition.system.service.UserService;
import com.hyr.hubei.polytechnic.university.competition.system.utils.AppException;
import com.hyr.hubei.polytechnic.university.competition.system.utils.CompeteMainClassUtils;
import com.hyr.hubei.polytechnic.university.competition.system.utils.ResolveXMLUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author huangyueran
 * @category 检测答案答案Action
 */
@Controller
@Scope("prototype")
public class ScoringPointAction extends ModelDrivenBaseAction<TestAnswer> implements SessionAware, RequestAware {

	private Long questionId; // 提交答案所属试题的ID

	protected Map<String, Object> session;
	protected Map<String, Object> request;

	@Resource(name = "userServiceImpl")
	protected UserService userService;
	@Resource(name = "questionSetServiceImpl")
	protected QuestionSetService questionSetService;

	/**
	 * 提交答案
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public String submitAnswer() throws AppException, IOException, InterruptedException {
		// 提交答案 进入评测
		final Question question = questionService.getById(questionId); // 答案所属试题
		// 创建评测记录
		final TestAnswer answer = new TestAnswer();
		answer.setAnswer(model.getAnswer());
		answer.setLanguage(question.getLanguage());
		answer.setQuestion(question);
		answer.setQuestionTitle(question.getTitle());
		answer.setStudent(userService.getById(getCurrentUser().getId()));
		answer.setSubmitTime(new Date());
		answer.setResult("等待评测");
		testAnswerService.save(answer);

		/*
		 * //TODO 开始检测答案 // 在服务器下查找/创建 用户的文件夹 CompeteMainClassUtils
		 * competeMainClassUtils = new CompeteMainClassUtils(); String
		 * rootUploadPath=ServletActionContext.getServletContext().getRealPath(
		 * "/WEB-INF/")+"\\upload"+"\\";
		 * 
		 * // 创建路径(存放的文件夹) 用户名 String filePath=getCurrentUser().getUsername();
		 * File file = new File(rootUploadPath+filePath); if(!file.exists()){ //
		 * 不存在 则创建 file.mkdirs(); }
		 * 
		 * // 创建java文件 并将数据写入文件中 用户名+题目id+时间>>目录名 存放Main.java String
		 * fielDir=getCurrentUser().getUsername()+questionId+System.
		 * currentTimeMillis(); File file2=new
		 * File(rootUploadPath+filePath+"\\"+fielDir); if(!file2.exists()){
		 * file2.mkdirs(); } File mainJAVAFile =new
		 * File(rootUploadPath+filePath+"\\"+fielDir+"\\Main.java");
		 * if(!mainJAVAFile.exists()){ mainJAVAFile.createNewFile(); }
		 * System.out.println(mainJAVAFile.getPath());
		 * competeMainClassUtils.writeFileContent(mainJAVAFile.getPath(),answer.
		 * getAnswer());
		 * 
		 * int v = competeMainClassUtils.compileProgram(mainJAVAFile.getPath(),
		 * "Main"); System.out.println("编译结果"+v);
		 */

		// TODO 开始检测答案 =========================================================
		// 在服务器下查找/创建 用户的文件夹
		CompeteMainClassUtils competeMainClassUtils = new CompeteMainClassUtils();
		String rootUploadPath = "C:\\competeFile" + "\\upload" + "\\";

		// 创建路径(存放的文件夹) 用户名
		String filePath = null;
		filePath = getCurrentUser().getUsername();

		File file = new File(rootUploadPath + filePath);
		if (!file.exists()) {
			// 不存在 则创建
			file.mkdirs();
		}

		// 创建java文件 并将数据写入文件中 用户名+题目id+时间>>目录名 存放Main.java
		String fielDir = null;
		fielDir = getCurrentUser().getUsername() + questionId + System.currentTimeMillis();

		File file2 = new File(rootUploadPath + filePath + "\\" + fielDir);
		if (!file2.exists()) {
			file2.mkdirs();
		}
		File mainJAVAFile = new File(rootUploadPath + filePath + "\\" + fielDir + "\\Main.java");
		if (!mainJAVAFile.exists()) {
			mainJAVAFile.createNewFile();
		}

		competeMainClassUtils.writeFileContent(mainJAVAFile.getPath(), answer.getAnswer());

		CompileInfo compileInfo = competeMainClassUtils.compileProgram(file2.getPath() + "\\", "Main"); // 编译结果信息

		// 测试答案
		// 获取type 根据type进行不同的xml解析

		// competeMainClassUtils.compileProgram(filePath, "");
		// 根据用户输入的答案 使用 IO流 创建java文件
		List<ResultB> parserXmlB = new ResolveXMLUtils().parserXmlB(question.getAnswersXml());
		TestAnswer testAnswer = testAnswerService.getById(answer.getId());
		testAnswer.setResult("正确");

		for (ResultB r : parserXmlB) {
			try {
				AnswerResultInfo resultInfo = competeMainClassUtils.execProgram("Main", file2.getPath() + "\\",
						r.getI());
				System.out.println(resultInfo);
				ScoringPoint scoringPoint = new ScoringPoint();
				if (resultInfo.getExitVal() == 0) {
					// 编译执行正常

					if (resultInfo.getExecTime() <= question.getRuntime() && resultInfo.getAnswerOutput() == r.getO()) {
						// 答案正确
						scoringPoint.setScore(r.getS());
						testAnswer.setScores(testAnswer.getScores() + (int) scoringPoint.getScore());
					} else {
						// TODO 内存是否超限的判断
						// ...

						if (resultInfo.getExecTime() > question.getRuntime()) {
							// 超时
							testAnswer.setResult("运行超时");
							scoringPoint.setResult("运行超时");
						} else if (!resultInfo.getAnswerOutput().equals(r.getO())) {
							// 结果错误
							testAnswer.setResult("错误");
							scoringPoint.setResult("错误");
						}
						// 答案不正确
						scoringPoint.setScore(0);
						testAnswer.setScores(testAnswer.getScores() + (int) scoringPoint.getScore());
					}
					// TODO 内存是否超出的判断

				} else {
					// 编译执行不正常
					File fileFlag = new File(rootUploadPath + filePath + "\\" + fielDir + "\\Main.class");
					if (!fileFlag.exists()) {
						// 编译异常
						scoringPoint.setResult("编译异常");
						testAnswer.setCompileInfo(compileInfo.getCompileInfo()); // 如果编译出错
																					// 保存编译出错信息
						testAnswer.setResult("编译异常");
					} else {
						// 执行异常
						scoringPoint.setResult("运行错误");
						testAnswer.setResult("运行错误");
					}
					scoringPoint.setScore(0);
					testAnswer.setScores(testAnswer.getScores() + (int) scoringPoint.getScore());
				}

				scoringPoint.setMemory(resultInfo.getExecMemo());
				scoringPoint.setRuntime(resultInfo.getExecTime());
				scoringPoint.setTestAnswer(testAnswer);

				// 填入最大的CPU时间
				if (resultInfo.getExecTime() > testAnswer.getRuntime()) {
					testAnswer.setRuntime(resultInfo.getExecTime());
				}
				// TODO 填入最大的内存消耗
				testAnswer.setMemory(resultInfo.getExecMemo());

				scoringPointService.save(scoringPoint);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
					| IOException | InterruptedException | AppException e) {
				// TODO 异常处理
				testAnswer.setScores(0);
				testAnswer.setCompileInfo("运行错误");
				testAnswer.setResult("运行错误");

				// 删除文件
				competeMainClassUtils.deleteFile(file2);

				testAnswerService.update(testAnswer);
				System.out.println(e);
				e.printStackTrace();
			}

		} // for循环结束

		testAnswer.setCodeLength(mainJAVAFile.length());
		testAnswerService.update(testAnswer);

		// 删除文件
		competeMainClassUtils.deleteFile(file2);

		// =========================================================
		// TODO 返回到评测列表
		return "toAnswerList";
	}

	@Override
	public User getCurrentUser() throws AppException {
		return (User) session.get("user");
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

}
