/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to print all request parameters in logger file.
 * 
 *@History
 *===============================================================================================================================================
 *     @Version         @Date         	@Author                 @Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to print all request parameters in logger file.
 *===============================================================================================================================================
 *
 */

package com.TranEco.cardManagement.utils.logger;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoggerUtil
{
	private static Logger logger = Logger.getLogger(LoggerUtil.class.getPackage().getName());
	private static LoggerUtil instance = null;

	public static LoggerUtil getInstance() {
		if (instance == null) {
			instance = new LoggerUtil();
		}
		return instance;
	}

	public void doLog(int type, String className, String methodName, String description) 
	{	
		StringBuffer strMessage = new StringBuffer();
		strMessage.append("Class :");
		strMessage.append(className).append("|| ");
		strMessage.append("Method :");
		strMessage.append(methodName).append(" || ");
		strMessage.append(description).append(" || ");
		if (type == Constants.LTI)
			logger.info(strMessage.toString());
		else if (type == Constants.LTW) 
			logger.warn(strMessage.toString());
		else if (type == Constants.LTE) 
			logger.error(strMessage.toString());
		else if (type == Constants.LTD) 
			logger.debug(strMessage.toString());
		
	}

	public static String getExStackTrace(Exception e) 
	{
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
	
}
