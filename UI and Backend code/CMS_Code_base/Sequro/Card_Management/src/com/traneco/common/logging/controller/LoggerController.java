package com.traneco.common.logging.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.traneco.common.ResponseBean;
import com.traneco.common.logger.model.UserLoggingModel;
import com.traneco.common.logging.services.LoggingService;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.user.model.UserBean;
import com.traneco.user.services.UserService;

@RestController
public class LoggerController 
{
	private static final Logger logger = Logger.getLogger(LoggerController.class);
	
	@Autowired
	LoggingService userLoggingService;
	
	@Autowired
	LoggerUtil loggerUtil; 
	
	@Autowired
	UserService userService;
	
	
	@ResponseBody
	@RequestMapping(value = "/startLogging", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean startLogging(Model model, UserLoggingModel userLoggingModel) 
	{
		ResponseBean responseBean = new ResponseBean();
		try
		{
			UserLoggingModel.userLogFlag = "Y"; 
			userLoggingModel = userLoggingService.getLoggingStatus(userLoggingModel);
			
			UserBean userBean = new UserBean();
			userBean.setStrUserID(userLoggingModel.getUserid());
			userBean = userService.getUserNameBasedOnUserId(userBean);
			
			if (userBean != null && userBean.getStrUserName() != null)
			{
				loggerUtil.doLog(4, Utils.getLocalDate(), Utils.getLocalTime(), "Logging Started By::"+userBean.getStrUserName());
				logger.info("Logging Started By::"+userBean.getStrUserName());
				responseBean.setResponseCode("00");
				responseBean.setResponseDesc("success");
				responseBean.setMessage("Logging Started !");
			
			}
			else 
			{
				responseBean.setResponseCode("99");
				responseBean.setResponseDesc("failed");
				responseBean.setMessage("Failed to Started !");
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return responseBean;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/stopLogging", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean stopLogging(Model model, UserLoggingModel userLoggingModel) 
	{
		ResponseBean responseBean = new ResponseBean();
		try
		{
			UserLoggingModel.userLogFlag = "N";
			userLoggingModel = userLoggingService.getLoggingStatus(userLoggingModel);
			
			UserBean userBean = new UserBean();
			userBean.setStrUserID(userLoggingModel.getUserid());
			userBean = userService.getUserNameBasedOnUserId(userBean);
			
			if (userBean != null && userBean.getStrUserName() != null)
			{
				loggerUtil.doLog(4, Utils.getLocalDate(), Utils.getLocalTime(), "Logging Stopped By::"+userBean.getStrUserName());
				System.out.println("LoggerController[Logging Stopped By]"+userBean.getStrUserName());
				logger.info("Logging Stopped By::"+userBean.getStrUserName());
				responseBean.setResponseCode("00");
				responseBean.setResponseDesc("success");
				responseBean.setMessage("Logging Stopped !");
			}
			else 
			{
				responseBean.setResponseCode("99");
				responseBean.setResponseDesc("failed");
				responseBean.setMessage("Failed to Stopped !");
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return responseBean;
	}
	
	
}
