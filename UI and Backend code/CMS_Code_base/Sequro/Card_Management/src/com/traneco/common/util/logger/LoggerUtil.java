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

package com.traneco.common.util.logger;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.traneco.common.SessionDTO;
import com.traneco.common.logger.model.UserLoggingModel;

@Component
public class LoggerUtil 
{
	@Autowired
	SessionDTO sessionDTO;

	private static Logger logger = Logger.getLogger(LoggerUtil.class.getPackage().getName());
	private static LoggerUtil instance = null;

	public static LoggerUtil getInstance() 
	{
		if (instance == null) 
		{
			instance = new LoggerUtil();
		}
		return instance;
	}

	public void doLog(int type, String className, String methodName, String description) 
	{
	   String logFlag = UserLoggingModel.userLogFlag;
	   System.out.println("User Logging flag:::::"+logFlag);
	   if ("Y".equalsIgnoreCase(logFlag))
	   {		
		String userid = String.valueOf(sessionDTO.getUserID());
		String instID = String.valueOf(sessionDTO.getParticipantid());
		String ipAddress = String.valueOf(sessionDTO.getIpAddress());
		StringBuffer strMessage = new StringBuffer();
		strMessage.append(" CMS PORTAL LOG  : ");
		strMessage.append("Class :");
		strMessage.append(className).append("|| ");
		strMessage.append("Method :");
		strMessage.append(methodName).append(" || ");
		strMessage.append(description).append(" || ");
		strMessage.append(" InstID :");
		strMessage.append(instID).append(" || ");
		strMessage.append(" User Name :");
		strMessage.append(userid).append(" || ");
		strMessage.append(" IP:");
		strMessage.append(ipAddress).append(" || ");
		if (type == Constants.LTI)
			logger.info(strMessage.toString());
		else if (type == Constants.LTW)
			logger.warn(strMessage.toString());
		else if (type == Constants.LTE)
			logger.error(strMessage.toString());
		else if (type == Constants.LTD)
			logger.debug(strMessage.toString());
		}
	}

	public static String getExStackTrace(Exception e) 
	{
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

}
