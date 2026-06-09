package com.traneco.common.util.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.traneco.common.logger.model.UserLoggingModel;
import com.traneco.common.util.Utils;

public class SequroLogger
{
	private static String FILE_NAME;
	//private static final String FILE_PATH = "/usr/app/log/";
	
	//private static final String FILE_PATH = "/usr/app/Paths/Sequro/ams/log/";
	private static final String FILE_PATH = "D:\\Sequro\\winservice\\cardManagement\\log\\";
	
	//public static String FILE_PATH;
	
	private Class<?> cls;
	
	private static SequroLogger instance;
	
	private SequroLogger(Class<?> cls) 
	{
		this.cls = cls;
	}
	
	public static SequroLogger getInstance(Class<?> clsInfo) 
	{
		instance = new SequroLogger(clsInfo);
		return instance;
	}
	
	public Class<?> getCls() {
		return cls;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

	public void writeInfoLog(Object msg) 
	{
		writeLogs(msg, cls, "[Info]");
	}
	public void writeExceptionLog(Object msg) 
	{
		writeLogs(msg, cls, "[Exception]");
	}
	private void writeLogs(Object msg, Class<?> cls, String msgType) 
	{
		try
		{
			String logFlag = UserLoggingModel.userLogFlag;
			if ("Y".equalsIgnoreCase(logFlag))
			{
			boolean isFolderExist =	createFolderIfNotExist();
			if (isFolderExist)
			{
				File file =  createNewFile();
				
				Object obj = cls.newInstance();
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
				
				LocalDateTime curreDateTime = LocalDateTime.now();
				String format = curreDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
				
				writer.append(format);
				writer.append(" ");
				writer.append(msgType.toUpperCase());
				writer.append(" [");
				writer.append(obj.getClass().getName()+"]  [");
				writer.append(obj.getClass().getSimpleName()+".java] - ");
				writer.append(msg+"\n");
				writer.close();
			}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private void checkFileSize() 
	{
		boolean isRequiredToCreateNewFile = false;
		try 
		{
			File file = new File(FILE_PATH + FILE_NAME);
			if (file.exists()) 
			{
				long bytes = file.length();
				long kilobytes = (bytes / 1024);
	            long megabytes = (kilobytes / 1024);
	            if (10 < megabytes)
	            {
	            	isRequiredToCreateNewFile = true;
	            }
			}
			else
			{
				isRequiredToCreateNewFile = true;
			}
			if (isRequiredToCreateNewFile)
			{
				setNewFile();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setNewFile() 
	{
		String strFileName = "cms-logger_"+Utils.getLocalDate() + Utils.getLocalTime();
		FILE_NAME = strFileName+".log";
	}
	
	private boolean createFolderIfNotExist() 
	{
		try 
		{
			File file = new File(FILE_PATH);
			if(!file.exists())
			{
				file.mkdirs();
			}
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void deleteExistingLogFile() 
	{
		try 
		{
			File file =	new File(FILE_PATH);
			if (file.exists()) 
			{
				File[] files = file.listFiles();
				
				if (files.length == 0)
				{
					return;
				}
				
				for (File f:files) 
				{
					if (f.isFile() && f.exists())
					{
						f.delete();
					}
					else
					{
						//System.out.println("cant delete a file due to open or error");
					}
				}
			}
			else
			{
				//System.out.println("Folder not exist");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	private File createNewFile() 
	{
		File f = new File(FILE_PATH);
		try 
		{
			String fileName = getExistingFile(f);
			if(fileName != null && fileName.trim().length() > 0 ) 
			{
				File existFile = new File(FILE_PATH + fileName);
				return existFile;
			}
			
			String strFileName = "cms-logger_"+new Date().getTime()+".log";
			
			f = new File(FILE_PATH + strFileName);
			if(f.createNewFile()) 
			{
				System.out.println("File created: " + f.getName());
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return f;
	}
	private String getExistingFile(File f) 
	{
		String fileName = "";
		try 
		{
			fileName = getExistingFileName(f);
			if (fileName != null && fileName.trim().length() > 0) 
			{
				long fileSize = getFileSize(fileName);				
				boolean fileSizeMore = isFileSizeMoreThan10MB(fileSize);				
				if (fileSizeMore) 
				{
					File file = new File(FILE_PATH + fileName);
					if(file != null) 
					{
						file.delete();
						fileName = "";
					}
				}
			}			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return fileName;
	}
	private String getExistingFileName(File file) 
	{
		String fileName = "";
		try 
		{
			if(file.exists()) 
			{
				File[] files = file.listFiles();
				if (files.length > 0)
				{
					for (File f:files) 
					{
						fileName = f.getName();
						break;
					}
				}
			}
			else
			{
				System.out.println("File not exist");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return fileName;
	}
	private long getFileSize(String fileName) 
	{
		try 
		{
			File file = new File(FILE_PATH + fileName);
			if(file != null) 
			{
				return file.length();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0l;
	}
	private boolean isFileSizeMoreThan10MB(long fileSize) 
	{
		boolean isFileSizeMoreThan10MB = false;
		try 
		{
			double kilobytes = (fileSize / 1024);
			double megabytes = (kilobytes / 1024);
            
            int mb = (int) Math.rint(megabytes);
            
            if (10 < mb)
            {
            	isFileSizeMoreThan10MB = true;
            }
		}
		catch (Exception e) {
		}
		return isFileSizeMoreThan10MB;
	}
}
