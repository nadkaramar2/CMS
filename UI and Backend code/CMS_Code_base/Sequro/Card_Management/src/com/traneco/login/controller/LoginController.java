/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to display login page and user credential validation
 * 
 *@History
 *===============================================================================================================================================
 *     @Version         @Date         	@Author                 @Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to display login page and user credential validation
 *===============================================================================================================================================
 *
 */

package com.traneco.login.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.traneco.accountmanagement.services.AccountManagementService;
import com.traneco.clientSearch.model.SearchClientCardRequest;
import com.traneco.common.CommonConstant;
import com.traneco.common.SessionDTO;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.institution.services.InstitutionService;
import com.traneco.login.model.CustomUser;
import com.traneco.login.model.LoginUser;
import com.traneco.login.model.ResetPassword;
import com.traneco.login.services.CustomUserService;
import com.traneco.login.validator.LoginUserValidator;
import com.traneco.user.model.UserBean;
import com.traneco.user.model.UserPasswords;
import com.traneco.user.services.UserService;

@Controller
@SessionAttributes("sessionDTO")
public class LoginController {

	@Autowired
	private CustomUserService userService;

	@Autowired
	Environment env;

	@Autowired
	LoginUser loginUser;

	@Autowired
	UserService userSevice;

	@Autowired
	LoginUserValidator loginUserValidator;

	@Autowired
	ConfigurationService configurationService;

	/**
	 * Object of InstituionService class
	 */
	@Autowired
	InstitutionService institutionService;

	@Autowired
	SearchClientCardRequest searchClientCardRequest;

	@Autowired
	SessionDTO sessionDTO;
	
	@Autowired
	LoggerUtil log;

	@Autowired
	AccountManagementService accountManagementService;
	
	private String className = this.getClass().getSimpleName();
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	/**
	 * This method is used to setup user session after successful authentication.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(HttpServletRequest request, Model model) 
	{
		CustomUser user = (CustomUser) request.getSession().getAttribute("user");
		sessionDTO.setDisplayName(user.getFirstName() + " " + user.getLastName());
		sessionDTO.setEmailID(user.getEmail());
		sessionDTO.setMobileNo(user.getMobileNo());
		sessionDTO.setParticipantid(user.getParticipantid());
		sessionDTO.setParticipantDesc(user.getParticipantDesc());
		sessionDTO.setRoleID(String.valueOf(user.getRoleID()));
		sessionDTO.setIpAddress(Utils.getClientIpAddr(request));
		sessionDTO.setLoginID(user.getUsername());
		sessionDTO.setUserID(user.getUserid());
		sessionDTO.setSensitiveFlag(user.getSensitive_date());
		sessionDTO.setCustomerID("");
		searchClientCardRequest.setStrCustomerID("");

		UserBean userBean = new UserBean();
		userBean.setStrUserName(user.getUsername());
		userBean.setStrMobileNo(user.getMobileNo());
		userBean.setStrEmailID(user.getEmail());
		/*
		 * userBean = userSevice.getUserDetailsBasesdOnMobileNoEmailId(userBean); if
		 * ("N".equalsIgnoreCase(userBean.getIsPasswordReset())) { return
		 * "redirect:resetPassword"; }
		 */
		// Added by Prashant to identify application for nigeria client on 09-May-2023
		// Start
		Map map = new HashMap();
		sessionDTO.setApplicationName("NIGERIA");
		map.put("appl_name", "NIGERIA");
		// String map1 = accountManagementService.setApplicationName(map);
		model.addAttribute("appl_name", "NIGERIA");

		// Added by Prashant to identify application for nigeria client on 09-May-2023
		// End

		model.addAttribute("cardTypeList", configurationService.getCardType());
		model.addAttribute("clientBean", new SearchClientCardRequest());
		return "home";
	}

	@RequestMapping(value = { "/", "/loginForm" }, method = RequestMethod.GET)
	public String loginForm(Model model, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "expired", required = false) String expired, LoginUser login,
			HttpServletRequest request) 
	{
		String methodName = "loginForm";
		model.addAttribute("login", new LoginUser());

		if (error != null)
			model.addAttribute("error", env.getProperty("imps.user.login.fail"));
		if (logout != null) 
		{
			log.doLog(4, className, methodName, "User has been Logout :"+login.getUsername());
			String participantId = sessionDTO.getParticipantid();
			String loginId = sessionDTO.getLoginID();
			model.addAttribute("msg", env.getProperty("imps.user.login.success"));
//			HttpSession session = request.getSession();
//			session.invalidate();
//			session = null;
		}

		if (expired != null) 
		{
			log.doLog(4, className, methodName, "User Session Expired :"+login.getUsername());
			model.addAttribute("msg", "Session Expired!! Please Login Again");
//			HttpSession session = request.getSession();
//			session.invalidate();
//			session = null;
		}

		model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));

		request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", "");

		model.addAttribute("instList", institutionService.getInstitutionList());
		/*
		 * if(request.getParameter("error") instanceof LockedException) {
		 * model.addAttribute("error", e.getMessage()); }
		 */
		return "login";

	}

	@SuppressWarnings("unused")
	private String getErrorMessage(HttpServletRequest request, String key) {

		String error = "";

		if (request.getSession().getAttribute(key) != null
				&& request.getSession().getAttribute(key) instanceof Exception) {
			Exception exception = (Exception) request.getSession().getAttribute(key);

			if (exception instanceof BadCredentialsException) {
				error = "Invalid username and password!";
			} else if (exception instanceof LockedException) {
				error = exception.getMessage();
			} else if (request.getSession().getAttribute("InvalidSessionError") != null) {
				error = request.getSession().getAttribute("InvalidSessionError").toString();
			} else if (exception instanceof SessionAuthenticationException) {
				error = exception.getMessage();
				error = "User Session Is Active! " + "Please wait for Some Time and Login Again";
			} else {
				error = "Invalid username and password!";
			}
		}
		return error;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accesssDenied() {
		return "403";
	}

	//Added By prashant Tayde for Reset Password Functionality
	@RequestMapping(value = {"/resetPassword"}, method = RequestMethod.GET)
	public String getResetPassword(Model model, ResetPassword resetPassword) 
	{
		model.addAttribute("resetPassword", resetPassword);
		return "resetPassword";

	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(Model model, ResetPassword resetPassword, Authentication authentication) 
	{
		try 
		{
			String methodName = "resetPassword";
			CustomUser user = null;
			int failedResetPasswordAttemptCount = 0;
			int updatePassword, updateOldNewPasswords = 0;
			String username = authentication.getName();
			String salt = env.getProperty("imps.user.login.salt.password");
			String oldencPass = Utils.generateSecurePassword(resetPassword.getOldpassword(), salt);
			user = userService.loadUserByUsername(username);
			
			if (user == null || !user.getPassword().equals(oldencPass))
			{
				model.addAttribute("error", "Old Password Not Matched.");
				log.doLog(4, className, methodName, "Old Password Not Matched. :"+user.getUsername());
				logger.error("Old Password Not Matched.", new Exception("Exception"));
				model.addAttribute("resetPassword", resetPassword);
				return "resetPassword";
			}
			// Validate new password
			if (resetPassword.getNewpassword().trim() != null && (resetPassword.getNewpassword().trim().length() > 8) && 
					(resetPassword.getNewpassword().trim().length() < 20))
			{
				boolean validateNewPass = Utils.isValid(resetPassword.getNewpassword());
				
				if (!validateNewPass)
				{
					model.addAttribute("error", " New Password Pattern is inValid.");
					log.doLog(4, className, methodName, "New Password Pattern is inValid. :"+user.getUsername());
					logger.error("New Password Pattern is inValid.", new Exception("Exception"));
					model.addAttribute("resetPassword", resetPassword);
					return "resetPassword";
				}
			}
			 // Validate confirm new password
			if (resetPassword.getConfirmnewpassword().trim() != null && (resetPassword.getConfirmnewpassword().trim().length() > 8) && 
					(resetPassword.getConfirmnewpassword().trim().length() < 20))
			{
				boolean validateCfmmNewPass = Utils.isValid(resetPassword.getConfirmnewpassword());
				if (!validateCfmmNewPass)
				{
					model.addAttribute("error", " Confirm Password Pattern is inValid.");
					log.doLog(4, className, methodName, "Confirm Password Pattern is inValid. :"+user.getUsername());
					logger.error("Confirm Password Pattern is inValid.", new Exception("Exception"));
					model.addAttribute("resetPassword", resetPassword);
					return "resetPassword";
				}
			}
			
			// Check if new password and confirm password match
			String newEncPass = Utils.generateSecurePassword(resetPassword.getNewpassword(), salt);
			String confNewEncPass = Utils.generateSecurePassword(resetPassword.getConfirmnewpassword(), salt);
			if (!newEncPass.equals(confNewEncPass)) 
			{
				model.addAttribute("error", "New Password & Confirm Password not Matched.");
				log.doLog(4, className, methodName, "New Password & Confirm Password not Matched. :"+user.getUsername());
				logger.error("New Password & Confirm Password not Matched.", new Exception("Exception"));
				model.addAttribute("resetPassword", resetPassword);
				return "resetPassword";
			}
			// Get user passwords data
			List<UserPasswords> resetPasswords = userService.getUserPasswordsData(username);
			if (resetPasswords != null && resetPasswords.size() > 0) 
			{
				//Get Last 5 old Passwords
				Optional<UserPasswords> resetPasswordsllist = resetPasswords.stream()
						.filter(userPasswords -> userPasswords.getNew_password().equals(confNewEncPass)).findAny();
				if (resetPasswordsllist.isPresent()) 
				{
					failedResetPasswordAttemptCount = userService.updateForgotPswdFailedAttempt(failedResetPasswordAttemptCount + 1,username,user.getUserid());
					model.addAttribute("msg", "New Password is used already");
					logger.error("New Password is used already.", new Exception("Exception"));
					model.addAttribute("resetPassword", resetPassword);
					return "resetPassword";
				}
				else 
				{
					user.setResetPasswordFlag(CommonConstant.PASSWORD_FLAG);
					updatePassword = userService.updateResetPassword(confNewEncPass, user.getResetPasswordFlag(), username.trim(),user.getUserid());
					updateOldNewPasswords = userSevice.addOldNewPassword(user,confNewEncPass);
					if (updateOldNewPasswords > 0) 
					{
						model.addAttribute("msg", "Password Reset Successfully.Please Login Again");
						logger.info("Password Reset Successfully.Please Login Again:::::"+user.getUsername());
						return "resetPassword";
					}
				}
			} 
			else 
			{
				user.setResetPasswordFlag(CommonConstant.PASSWORD_FLAG);
				updatePassword = userService.updateResetPassword(confNewEncPass, user.getResetPasswordFlag(), username,user.getUserid());
				updateOldNewPasswords = userSevice.addOldNewPassword(user, confNewEncPass);
				if (updateOldNewPasswords > 0) 
				{
					model.addAttribute("msg", "Password Reset Successfully.Please Login Again");
					logger.info("Password Reset Successfully.Please Login Again:::::"+user.getUsername());
				}
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	
		return "resetPassword";

	}
	//Ended By prashant Tayde for Reset Password Functionality
	
	@RequestMapping(value = "/sessionExpire", method = RequestMethod.GET)
	public String sessionExpire() {
		return "sessionExpire";
	}

	
}