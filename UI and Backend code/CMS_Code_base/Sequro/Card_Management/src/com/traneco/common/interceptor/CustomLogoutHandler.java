/**
*@author  mithape
 *@version 1.0
 *@purpose This class is used to handle logout user action and current session assign null.
 * 
 *@History
 *===============================================================================================================================================
 *     @Version         @Date         	@Author                 @Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to handle logout user action and current session assign null.
 *===============================================================================================================================================
 *
 *===============================================================================================================================================
 *[Changed this on 25-08-2023]
 *Instead of Invalidating the session and setting it to null
 *Removing the authentication Object and Session
 *by clearing the Security Context 
 *
 *Note - After Logging out the User is getting redirected to the default path i.e "/"
 *	 even after configuring in the authorizeRequest 
 *===============================================================================================================================================
 *
 */

package com.traneco.common.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.traneco.common.logging.services.LoggingService;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.login.model.CustomUser;
import com.traneco.login.model.UserLoginLog;
import com.traneco.user.services.UserService;


@Component
public class CustomLogoutHandler extends SimpleUrlLogoutSuccessHandler implements LogoutHandler
{
	private String className = this.getClass().getSimpleName();
	
	private static final Logger logger = Logger.getLogger(CustomLogoutHandler.class);
    
    @Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
    
    @Autowired
    UserService userService;
    
    @Autowired
	LoggerUtil log;
    
    @Autowired
    LoggingService userLoggingService;
    	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException 
	{
//		HttpSession session = request.getSession();
//		session.invalidate();
//		session = null;
		super.handle(request, response, authentication);
	}
	
	//added by ankit for logout
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	            throws IOException, ServletException {
	        
	    	SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
	    	securityContextLogoutHandler.logout(request, null, null);
//	    	HttpSession session = request.getSession();
//		session.invalidate();
	        super.onLogoutSuccess(request, response, authentication);
	}
	//added by ankit for logout

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	{
		HttpSession session = request.getSession(false);
		
		try 
		{
			  if(session != null)
			    {
			    CustomUser customerUser = (CustomUser) authentication.getPrincipal();
			    updateLogoutTime(customerUser);
			    session.invalidate();
			    session = null;
			    SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
		            securityContextLogoutHandler.logout(request, null, null);
			   }
			  else 
			  {
				  CustomUser customerUser = (CustomUser) authentication.getPrincipal();
				  updateLogoutTime(customerUser);
			  }
			  
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//Added  By Prashant Tayde
	private void updateLogoutTime(CustomUser customerUser) 
	{
		try 
		{
			if(customerUser != null && customerUser.getUsername() != null)
			{
				UserLoginLog userLoginLog = new UserLoginLog();
				userLoginLog.setUserId(customerUser.getUserid());
				userLoginLog.setParticipantId(Integer.parseInt(customerUser.getParticipantid()));
				userLoginLog.setUsername(customerUser.getUsername());
				UserLoginLog userLoginData = userService.getUserLoginData(userLoginLog);
				if (userLoginData != null && userLoginData.getLoginDateTime() != null)
				{
					customerUser.setLoginDateTime(userLoginData.getLoginDateTime());
					int updateLogoutTime = userService.updateLogoutTime(userLoginData);
					System.out.println("CustomLogoutHandler.updateLogoutTime()"+updateLogoutTime);
					log.doLog(4, className, "User logout Successfully", "User Logout Time ");
					logger.info("User logged out Successfully:::::"+userLoginData.getUsername());
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
