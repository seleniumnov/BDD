package com.framework.utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger
{
	private static String logFilePath = null;
	private static BufferedWriter out = null;
	private static String logFileName = null;

	public static void initializeLogger(String filePath, String fileName) {
		try {
			logFilePath = filePath;
			logFileName = fileName;
			File newFile = new File(logFilePath + logFileName);
			newFile.createNewFile();
			newFile.setWritable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String GetLogFilePath(){
		return logFilePath + logFileName;
	}

	public static void logTestInfo(String text) {	
		try {
			Date currentTimeStamp = new Date();
			SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy, HH:mm:ss");
			System.out.println(text);
			out = new BufferedWriter(new FileWriter(logFilePath + logFileName, true));
			out.write("\r\n" + "[" + format.format(currentTimeStamp) + "]" + "[INFO]: ");	
			out.write(text);		
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}