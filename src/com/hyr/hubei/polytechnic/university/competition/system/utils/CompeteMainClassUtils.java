package com.hyr.hubei.polytechnic.university.competition.system.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import com.hyr.hubei.polytechnic.university.competition.system.domain.AnswerResultInfo;
import com.hyr.hubei.polytechnic.university.competition.system.domain.CompileInfo;

/**
 * @category JAVA文件编译工具类 根据JAVA文件进行编译>>生成class文件>>运行
 * @author huangyueran
 *
 */
public class CompeteMainClassUtils {

	double studentScore;
	Process childProcess;
	int execMemo = -1; // 运行内存
	int execTime = -1; // 运行时间
	String filepath = "C:\\competeFile\\"; // 文件保存目录
	String binPath = "E:\\javaSteup\\jdk1.6.0_39\\bin"; // 配置jdk环境

	/**
	 * 1.取得所有的学生ID,姓名的dataTable 2.通过ID号得到学生答题卡上的所有的答题dataTable
	 * 3.获得相应题号的mainClass,Program 4.编译成*.class 4.1. 找到测试数据的输入到程序的数据 4.2
	 * 找到测试数据的程序输出的数据。 5.执行此文件 6.输入 7.获得输出 8.比对结果 9.将信息保存，分数保存。
	 */

	public CompeteMainClassUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompeteMainClassUtils(double studentScore, Process childProcess, int execMemo, int execTime, String filepath,
			String binPath) {
		super();
		this.studentScore = studentScore;
		this.childProcess = childProcess;
		this.execMemo = execMemo;
		this.execTime = execTime;
		this.filepath = filepath;
		this.binPath = binPath;
	}

	// 编译
	// compileProgram(filepath, "T1");
	// 执行输入输出
	// execProgram("T1", filepath, 555+"");

	public CompileInfo compileProgram(String pathString, String fileName) throws IOException, InterruptedException {
		CompileInfo compileInfo = new CompileInfo();
		File file = new File(fileName + ".class");
		if (file.exists()) {
			file.delete();
		}

		String execFileString = "javac -encoding UTF-8 " + pathString + fileName + ".java"; // javac
																							// 不指定编码
																							// 会使用系统默认的编码进行编译
																							// 会编译报错
		System.out.println("cmd:" + execFileString);
		Process compileProcess = Runtime.getRuntime().exec(execFileString);
		InputStream inputStream = compileProcess.getErrorStream();

		InputStreamReader isr = new InputStreamReader(inputStream, "gbk");
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		String competeInfo = null;
		while ((competeInfo = br.readLine()) != null)
			sb.append(competeInfo);
		Thread killChildProcess = new KillProcessThread(30000, null, compileProcess);
		killChildProcess.start();

		int exitVal = compileProcess.waitFor();
		killChildProcess.interrupt();

		// 截取编译信息字符串
		String cStr = sb.toString();
		String jieguo;
		if (cStr.indexOf("Main") != -1) {
			jieguo = cStr.substring(cStr.indexOf("Main"));
		} else {
			jieguo = cStr;
		}

		compileInfo.setCompileInfo(jieguo); // 设置编译信息
		compileInfo.setExitVal(exitVal);// 设置编译结果状态
		return compileInfo;
	}

	public Process execProgramAndReturnProcess(String programName, String classPath) throws IOException {
		return Runtime.getRuntime().exec("java -classpath " + classPath + " " + programName);
	}

	/**
	 * 
	 * @param programName
	 *            执行程序名
	 * @param classPath
	 *            执行路径
	 * @param inputDataToProcessString
	 *            输入数据
	 * @return AnswerResultInfo 返回一个bean 存放需要的dto数据
	 */
	public AnswerResultInfo execProgram(String programName, String classPath, String inputDataToProcessString)
			throws IOException, InterruptedException, NoSuchFieldException, SecurityException, IllegalArgumentException,
			IllegalAccessException {
		AnswerResultInfo answerResultInfo = new AnswerResultInfo();
		final TestMemory testMemory = new TestMemory();
		testMemory.start();

		// 执行程序
		final Process childProcess = execProgramAndReturnProcess(programName, classPath);

		Thread killChildProcess = new KillProcessThread(10000, testMemory, childProcess);
		killChildProcess.start();

		// 把数据压入程序中，提供输入数据。
		OutputStream outputStream = childProcess.getOutputStream();
		final InputStream inputStream = childProcess.getInputStream();

		outputStream.write((inputDataToProcessString + " ").getBytes());
		outputStream.flush();

		// 输入线程开始
		Thread outThread = new Thread();
		outThread.start();
		outThread.join();

		// 获得执行时间
		long timeFore = System.currentTimeMillis();

		// 执行
		int exitVal = childProcess.waitFor(); // 执行结果状态 0正常运行 1运行错误

		long timeAfer = System.currentTimeMillis();
		testMemory.stopThread(true);
		childProcess.destroy();
		killChildProcess.interrupt();

		long execTime = timeAfer - timeFore;

		/** ================== 减去业务层和逻辑层的消耗时间 假设为20ms ================== */
		execTime = execTime - 20;  // 这个要根据具体的测试计算 得出 目前还没有经过测试计算 

		// System.out.println("執行時間：" + execTime + "ms");

		long execMemo = testMemory.getRemSize();
		// System.out.println("執行内存：" + execMemo + "KB"); // TODO 这里填写的是假数据

		outputStream.close();

		// 得到学生程序的输出数据。
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader reader = new BufferedReader(inputStreamReader);

		String s;
		StringBuffer studentProgramOutput = new StringBuffer();
		while ((s = reader.readLine()) != null) {
			if (s.length() != 0) {
				studentProgramOutput.append(s);
			}
		}

		inputStream.close();
		inputStreamReader.close();

		answerResultInfo.setExitVal(exitVal);
		answerResultInfo.setAnswerOutput(studentProgramOutput.toString());
		answerResultInfo.setExecTime(execTime);
		answerResultInfo.setExecMemo(execMemo);

		System.out.println(answerResultInfo);
		return answerResultInfo;

		// 编译异常和运行异常的处理
	}

	/**
	 * 向文件中写入内容
	 * 
	 * @param filepath
	 *            文件路径与名称
	 * @param newstr
	 *            写入的内容
	 * @return
	 * @throws IOException
	 */
	public boolean writeFileContent(String filepath, String newstr) throws IOException {
		Boolean bool = false;
		String filein = newstr + "\r\n";// 新写入的行，换行
		String temp = "";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		PrintWriter pw = null;
		try {
			File file = new File(filepath);// 文件路径(包括文件名称)
			// 将文件读入输入流
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buffer = new StringBuffer();

			// 文件原有内容
			for (int i = 0; (temp = br.readLine()) != null; i++) {
				buffer.append(temp);
				// 行与行之间的分隔符 相当于“\n”
				buffer = buffer.append(System.getProperty("line.separator"));
			}
			buffer.append(filein);

			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buffer.toString().toCharArray());
			pw.flush();
			bool = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 不要忘记关闭
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return bool;
	}

	/**
	 * @category 递归删除文件夹
	 * @param file
	 */
	public void deleteFile(File file) {
		if (file.exists()) {// 判断文件是否存在
			if (file.isFile()) {// 判断是否是文件
				file.delete();// 删除文件
			} else if (file.isDirectory()) {// 否则如果它是一个目录
				File[] files = file.listFiles();// 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) {// 遍历目录下所有的文件
					this.deleteFile(files[i]);// 把每个文件用这个方法进行迭代
				}
				file.delete();// 删除文件夹
			}
		} else {
			System.out.println("所删除的文件不存在");
		}
	}
}
