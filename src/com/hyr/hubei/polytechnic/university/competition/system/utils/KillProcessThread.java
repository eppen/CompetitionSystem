package com.hyr.hubei.polytechnic.university.competition.system.utils;

import java.lang.reflect.Field;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT;

/**
 * @category 杀死进程工具类
 * @author huangyueran
 *
 */
public class KillProcessThread extends Thread {

	int delay = 0;
	TestMemory testMemory;
	Process childProcess;

	public KillProcessThread(int delay, TestMemory testMemory, Process childProcess) {
		this.delay = delay;
		this.testMemory = testMemory;
		this.childProcess = childProcess;
	}

	public void run() {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {

		}
		if (testMemory != null)
			testMemory.stopThread(true);
		// killProcessTree(childProcess); // 杀死进程树 cmd.exe和创建的进程
		childProcess.destroy();
	}

	/**
	 * 杀死进程数
	 * 
	 * @param process
	 */
	private void killProcessTree(Process process) {
		try {
			Field f = process.getClass().getDeclaredField("handle");
			f.setAccessible(true);
			long handl = f.getLong(process);
			Kernel32 kernel = Kernel32.INSTANCE;
			WinNT.HANDLE handle = new WinNT.HANDLE();
			handle.setPointer(Pointer.createConstant(handl));
			int ret = kernel.GetProcessId(handle);
			Long PID = Long.valueOf(ret);
			String cmd = getKillProcessTreeCmd(PID);
			Runtime rt = Runtime.getRuntime();
			Process killPrcess = rt.exec(cmd);
			killPrcess.waitFor();
			killPrcess.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getProcessListCmd(Long Pid) {
		String result = "";
		result = "cmd.exe /c tasklist | findstr " + Pid;
		return result;
	}

	private String getKillProcessTreeCmd(Long Pid) {
		String result = "";
		if (Pid != null)
			result = "cmd.exe /c taskkill /PID " + Pid + " /F /T ";
		return result;
	}
}
