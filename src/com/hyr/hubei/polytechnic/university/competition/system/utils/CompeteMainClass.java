package com.hyr.hubei.polytechnic.university.competition.system.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class CompeteMainClass {

	String studentProgramResult;
	double studentScore;
	static Process childProcess;
	int execMemo = -1;
	int execTime = -1;
	final static String filepath = "C:\\competeFile\\";
	final static String binPath = "E:\\javaSteup\\jdk1.6.0_39\\bin"; // 配置jdk环境

	/*
	 * 1.取得所有的学生ID,姓名的dataTable 2.通过ID号得到学生答题卡上的所有的答题dataTable
	 * 3.获得相应题号的mainClass,Program 4.编译成*.class 4.1. 找到测试数据的输入到程序的数据 4.2
	 * 找到测试数据的程序输出的数据。 5.执行此文件 6.输入 7.获得输出 8.比对结果 9.将信息保存，分数保存。
	 */

	public static void main(String[] args) throws IOException, InterruptedException, NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		// 编译
		compileProgram(filepath, "Main");
		// 执行输入输出
		execProgram("Main", filepath, 555+""); 
	}

	public static void compileProgram(String pathString, String fileName) throws IOException, InterruptedException {
		File file = new File(fileName + ".class");
		if (file.exists()) {
			file.delete();
		}

		String execFileString = "javac " + pathString + fileName + ".java";
		Process compileProcess = Runtime.getRuntime().exec(execFileString);

		Thread killChildProcess = new KillProcessThread(30000, null, compileProcess);
		killChildProcess.start();

		compileProcess.waitFor();
		killChildProcess.interrupt();

		System.out.println(file.getName());
	}

	public static Process execProgramAndReturnProcess(String programName, String classPath) throws IOException {
		return Runtime.getRuntime().exec("java -classpath " + classPath + " " + programName);
	}

	/**
	 * 
	 * @param programName 执行程序名
	 * @param classPath 执行路径
	 * @param inputDataToProcessString 输入数据
	 * @param bean 返回一个bean 存放需要的dto数据
	 */
	public static void execProgram(String programName, String classPath,String inputDataToProcessString) throws IOException, InterruptedException,
			NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final TestMemory testMemory = new TestMemory();
		testMemory.start();

		// 执行程序
		final Process childProcess = execProgramAndReturnProcess(programName, classPath);

		Thread killChildProcess = new KillProcessThread(10000, testMemory, childProcess);
		killChildProcess.start(); 

		// 把数据压入程序中，提供输入数据。
		OutputStream outputStream = childProcess.getOutputStream();
		final InputStream inputStream = childProcess.getInputStream();
		
		outputStream.write((inputDataToProcessString+" ").getBytes());   
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
		System.out.println("執行時間：" + execTime + "ms");

		long execMemo = testMemory.getRemSize();
		System.out.println("執行内存：" + execMemo + "KB");

		outputStream.close();

		// 得到学生程序的输出数据。
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
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

		// 编译异常和运行异常的处理
		System.out.println("执行状态:" + exitVal + "\n程序输出结果：" + studentProgramOutput.toString());
	}

}
