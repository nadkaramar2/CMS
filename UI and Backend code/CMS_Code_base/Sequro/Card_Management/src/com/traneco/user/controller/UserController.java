
/**
 * @author  mithape
 * @version 1.0
 * @purpose This class is used to display and perform user operation.
 *
 * @History
 * ===============================================================================================================================================
 *     @Version         @Date           @Author                 @Purpose
 * ===============================================================================================================================================
 *     1.0                      15-01-18        Mayur I                 This class is used to display and perform user operation.
 * ===============================================================================================================================================
 *
 */
package com.traneco.user.controller;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.traneco.common.CommonConstant;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.util.AESEncDec;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.user.model.UserBean;
import com.traneco.user.services.PasswordGenerationService;
import com.traneco.user.services.UserService;
import com.traneco.user.validator.MailService;
import com.traneco.user.validator.UserValidator;

@Controller
public class UserController 
{
	private String className = this.getClass().getSimpleName();
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	Environment env;
	@Autowired
	UserService userService;
	@Autowired
	SessionDTO sessionDTO;
	@Autowired
	LoggerUtil log;
	@Autowired
	UserValidator userValidator;

	@Autowired
	MailService mailService;
	
	@Autowired
	AESEncDec aESEncDec;
	
	
	@Autowired
	PasswordGenerationService passwordGenerationService;

	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(Model model, UserBean userBean, BindingResult result) {

		String respCode = "";
		String methodName = "addUser";
		try 
		{
			log.doLog(4, className, methodName, "Start add User Method");
			logger.info("Add User Method Started........!");
			userBean.setStrRequestID(TranecoStatusCode.REQTYPE_ADD);
			userValidator.validate(userBean, result);
			
			String Username = sessionDTO.getLoginID();
			System.out.println("user Name :::"+Username);
			
			model.addAttribute("secQuestList", userService.getSecretQuestionList());
			/* model.addAttribute("roleList", userService.getRoleList()); */
			model.addAttribute("groupList", userService.getGroupList(sessionDTO.getParticipantid()));

			// Added by Prashant Tayde 11 Sept 2023 --- for Validating Duplicate User
			boolean isUserExist = userService.checkUserExist(userBean);
			if (isUserExist) {
				model.addAttribute("error", "Username Already Exist");
				model.addAttribute("secQuestList", userService.getSecretQuestionList());
				model.addAttribute("groupList", userService.getGroupList(sessionDTO.getParticipantid()));
				return "addUserForm";
			}
			// End By Prashant Tayde 11 Sept 2023 -- for Validating Existing User
			if (result.hasErrors()) {
				model.addAttribute("display", "none");
				System.out.println("UserController.addUser()" + model);
			} 
			else 
			{
				respCode = userService.addUser(userBean);
				model.addAttribute("display", "block");
				System.out.println("UserController.addUser()" + model);
				if(respCode.equalsIgnoreCase("00"))
				{
					log.doLog(4, Utils.getLocalDate(), Utils.getLocalTime(),"New User Added By ::::{"+sessionDTO.getLoginID()+"}[Username-]" +userBean.getStrUserName());
					logger.info("New User Added By {"+sessionDTO.getLoginID()+"}. Username::{"+userBean.getStrUserName()+"}");

				}
				switch (respCode) 
				{
				case TranecoStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", env.getProperty("imps.addUserForm.sucess"));
					userBean = null;

					break;

				case TranecoStatusCode.STATUS_SQL_EXCEPTION:
					model.addAttribute("exception", env.getProperty("imps.addUserForm.exception"));

					break;

				case TranecoStatusCode.STATUS_DUPLICATE:
					model.addAttribute("error", env.getProperty("imps.addUserForm.error"));

					break;

				default:
					break;
				}
			}
			log.doLog(4, className, methodName, "End add User Method");
			logger.info("End add User Method........");
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.addUserForm.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addUserForm";
	}

	@RequestMapping(value = "/addUserForm", method = RequestMethod.GET)
	public String addUserForm(Model model, UserBean userBean) {
		model.addAttribute("secQuestList", userService.getSecretQuestionList());
		/* model.addAttribute("roleList", userService.getRoleList()); */
		model.addAttribute("groupList", userService.getGroupList(sessionDTO.getParticipantid()));
		return "addUserForm";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Model model, UserBean userBean) {
		model.addAttribute("roleList", userService.getRoleList());
		model.addAttribute("user", userService.getUserProfileDetails());
		return "profile";
	}

	@RequestMapping(value = "/approveUser", method = RequestMethod.POST)
	public String approveUser(Model model, UserBean userBean, BindingResult result) {
		String respCode = "";
		String methodName = "approveUser";
		try 
		{
			log.doLog(4, className, methodName, "Start add User Method");
			userBean.setStrRequestID(TranecoStatusCode.REQTYPE_APPROVE);
			userValidator.validate(userBean, result);

			if (result.hasErrors()) 
			{
				model.addAttribute("error", " ");
				model.addAttribute("display", "block");
			}
			else 
			{
				String[] data = userBean.getStrRequestData();
				for (int i = 0; i < data.length; i++) 
				{
					respCode = userService.approvalPendingList(data[i], userBean.getStrRequestBtn(),
							userBean.getStrRemark(), userBean);
					model.addAttribute("display", "block");

					switch (respCode) 
					{
					case TranecoStatusCode.STATUS_SUCCESS:
						model.addAttribute("success",
								MessageFormat.format(env.getProperty("imps.approvalUserForm.sucess"),
										"1".equals(userBean.getStrRequestBtn()) ? "Approved" : "Reject"));
						if (respCode.equalsIgnoreCase("00") || respCode == "00")
						{
							String userData = data[0];
							String[] data1 = userData.split("\\|");
							userBean.setStrUserName(data1[2].trim());
							userBean.setStrEmailID(data1[3].trim());
							userBean = userService.getUserDetailsBasesdOnMobileNoEmailId(userBean);
							if (userBean.getStrEmailID().trim() != null && userBean.getPlainPassword().trim() != null)
							{
								String encPassword = userBean.getPlainPassword();
								String decrypt = aESEncDec.decrypt(encPassword);
								userBean.setPlainPassword(decrypt);
								String mailer = mailService.sendEmailCustomer(userBean);
								System.out.println(mailer);
							}
							else 
							{
								//model.addAttribute("exception", "Mail Id Not Found");
								System.out.println("User Not Found:::::::");
							}
							
						}
						break;

					case TranecoStatusCode.STATUS_SQL_EXCEPTION:
						model.addAttribute("exception", env.getProperty("imps.approvalUserForm.exception"));

						break;

					case TranecoStatusCode.STATUS_DUPLICATE:
						model.addAttribute("error", env.getProperty("imps.approvalUserForm.error"));

						break;

					default:
						break;
					}
				}
			}
			model.addAttribute("userList", userService.getApprovalPendingList());
			log.doLog(4, className, methodName, "End add User Method");
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.approvalUserForm.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return "approveUserForm";
	}

	@RequestMapping(value = "/approveUserForm", method = RequestMethod.GET)
	public String approveUserForm(Model model, UserBean userBean) {
		String methodName = "approveUserForm";
		try 
		{
			log.doLog(4, className, methodName, "Start add User Method");
			model.addAttribute("userList", userService.getApprovalPendingList());
			log.doLog(4, className, methodName, "End add User Method");
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.getApprovalUserForm.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return "approveUserForm";
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(Model model, UserBean userBean, BindingResult result) {
		String methodName = "editUser";
		String respCode = "";
		try {
			log.doLog(4, className, methodName, sessionDTO.getDisplayName());
			userBean.setStrRequestID(TranecoStatusCode.REQTYPE_EDIT);

			userValidator.validate(userBean, result);
			model.addAttribute("groupList", userService.getGroupList(sessionDTO.getParticipantid()));
			if (result.hasErrors()) 
			{
				model.addAttribute("error", " ");
				model.addAttribute("display", "block");

				return "editUserDetails";
			} 
			else 
			{
				respCode = userService.editUser(userBean);
				// int passswordUpdate = userService.updateUserPassword(userBean);
				// System.out.println("Password Updated"+passswordUpdate);
				model.addAttribute("display", "block");
				
				switch (respCode) 
				{
				case TranecoStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", env.getProperty("imps.editUserForm.sucess"));
					
					if (respCode.equalsIgnoreCase("00"))
					{
						String userPassword = userBean.getStrPassword();
						String encUsrPasswd = aESEncDec.encrypt(userPassword);
						userBean.setPlainPassword(encUsrPasswd);
						userBean.setIsPasswordReset(CommonConstant.PASSWORD_RESET_FLAG);
						userBean.setForgotPasswordValidationFailedAttempt(0);
						userBean.setLoginFailedAttemptsCount(0);
						int passswordUpdate = userService.updateUserPlainPassword(userBean);
						System.out.println("Plain Password :::"+passswordUpdate);
					}
					
					break;

				case TranecoStatusCode.STATUS_SQL_EXCEPTION:
					model.addAttribute("exception", env.getProperty("imps.editUserForm.exception"));

					break;

				case TranecoStatusCode.STATUS_DUPLICATE:
					model.addAttribute("error", env.getProperty("imps.editUserForm.error"));

					break;

				default:
					break;
				}
			}

			model.addAttribute("secQuestList", userService.getSecretQuestionList());
			model.addAttribute("roleList", userService.getRoleList());
			model.addAttribute("editUserForm", userService.getUserList());
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return "editUserForm";
	}

	@RequestMapping(value = "/editUserDetails", method = RequestMethod.POST)
	public String editUserDetails(Model model, UserBean userBean) 
	{
		String methodName = "editUserDetails";
		try 
		{
			log.doLog(4, className, methodName, sessionDTO.getDisplayName());

			int count = userService.checkApprovalPendingCount(userBean.getStrUserID());
			model.addAttribute("groupList", userService.getGroupList(sessionDTO.getParticipantid()));
			if (count > 0) 
			{
				model.addAttribute("display", "block");
				model.addAttribute("flag", false);
				model.addAttribute("error", env.getProperty("imps.editUserForm.error"));
			}
			else 
			{
				model.addAttribute("display", "block");
				model.addAttribute("flag", true);
			}
			model.addAttribute("userBean", userService.getUserDetails(userBean.getStrUserID()));
			model.addAttribute("secQuestList", userService.getSecretQuestionList());
			model.addAttribute("roleList", userService.getRoleList());
			// model.addAttribute("usrStatusList", userService.getUserStatusList());
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return "editUserDetails";
	}

	@RequestMapping(value = "/editUserForm", method = RequestMethod.GET)
	public String editUserForm(Model model, UserBean userBean) {
		String methodName = "editUserForm";
		try {
			log.doLog(4, className, methodName, sessionDTO.getDisplayName());
			model.addAttribute("editUserForm", userService.getUserList());
			model.addAttribute("groupList", userService.getGroupList(sessionDTO.getParticipantid()));
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return "editUserForm";
	}

	@RequestMapping(value = "/viewUserForm", method = RequestMethod.GET)
	public String viewUserForm(Model model, UserBean userBean) {
		String methodName = "viewUserForm";

		try {
			log.doLog(4, className, methodName, sessionDTO.getDisplayName());
			model.addAttribute("viewUserList", userService.getUserList());
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}

		return "viewUserForm";
	}

	@RequestMapping(value = "getUserIdData", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String getUserIdData(@RequestBody String userID) throws Exception {
		try {
			return Utils.objectToJson(userService.getUserIdDetails(userID.replace("=", "")));
		} catch (Exception e) {
			log.doLog(3, className, "getUserIdData", LoggerUtil.getExStackTrace(e));
			return null;
		}

	}

}

//~ Formatted by Jindent --- http://www.jindent.com
