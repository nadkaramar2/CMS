/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to validate request user login is valid or not.
 * 
 *@History
 *===============================================================================================================================================
 *     Version          Date            Author                  Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to validate request user login is valid or not.
 *===============================================================================================================================================
 *
 */

package com.traneco.login.authentication;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.traneco.common.CommonConstant;
import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.logging.services.LoggingService;
import com.traneco.common.util.AesUtil;
import com.traneco.common.util.EncryptDecryptUtil;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.login.model.CustomUser;
import com.traneco.login.model.LoginUser;
import com.traneco.login.model.UserLoginLog;
import com.traneco.login.services.CustomUserService;
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider
{
	private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);
	
    @Autowired
    private CustomUserService userService;

    @Autowired
    Environment env;

    @Autowired
    LoginUser loginUser;
    
    @Autowired
	LoggerUtil log;
    
    @Autowired
    EncryptDecryptUtil encryptDecryptUtil;
    
    @Autowired
    LoggingService userLoggingService;
    
    
    private String className = this.getClass().getSimpleName();
    
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
    String methodName = "authenticate";
	String username = authentication.getName();
	String password = (String) authentication.getCredentials();
	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	String instID = attr.getRequest().getParameter("institutionID");
	Collection<? extends GrantedAuthority> authorities = null;
	CustomUser user = null;
	int iterationCount = TranecoStatusCode.iterationCount;
	int keySize = TranecoStatusCode.keySize;
	int failedLoginAttemptCount = 0;
	int updateCount, loginupdate = 0;
	String saltKey = TranecoStatusCode.saltKey;
	String iv = TranecoStatusCode.iv;
	AesUtil aesUtil = new AesUtil(keySize, iterationCount);
	String decPass = aesUtil.decrypt(saltKey, iv, TranecoStatusCode.passPhrase, password);
	String salt = env.getProperty("imps.user.login.salt.password");
	String encPass = Utils.generateSecurePassword(decPass, salt);
	
	/*
	 * if(logger.isDebugEnabled()) { logger.debug("CMS Application is executed!"); }
	 */

	if (!TranecoStatusCode.SUPERADMIN.equals(username))
	{
	    user = userService.loadUserByUsername(username);
	    if (user == null || !user.getUsername().equalsIgnoreCase(username))
	    {
	    	log.doLog(4, className, methodName, "Entered Wrong Username :"+username);
	    	logger.error("Entered Wrong Username", new Exception("Invalid Login By"+username));
	    	System.out.println("Username Not Found"+username);
	    	throw new BadCredentialsException("Username not found.");
	    }
	    else if (!encPass.equals(user.getPassword()))
	    {
	     
		failedLoginAttemptCount = userService.getFailedLoginAttempts(user.getUserid());
		if (failedLoginAttemptCount > 2)
		{
			List<UserLoginLog> usrLoginLog = userService.getLastThreeFailedAttempts(user);
			if (usrLoginLog != null && usrLoginLog.size() > 0)
			{
				for (int i = 0; i < usrLoginLog.size() - 1; i++) 
				{
					   Date date1 = usrLoginLog.get(1).getLoginDateTime();
					   Date date2 = usrLoginLog.get(2).getLoginDateTime();

					   long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
					   long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillies);
					   if ((diffInMinutes <= 10)) 
					   {
						   user.setResetPasswordFlag(CommonConstant.PASSWORD_RESET_FLAG);
						   int updatePasswordRestFlag = userService.updatePasswordRestFlag(user);
						   System.out.println("CustomAuthenticationProvider.authenticate()"+updatePasswordRestFlag);
						   log.doLog(4, className, methodName, "Login Attempts Exceeded 3 Times. Account Locked :"+username);
						   logger.error("Login Attempts Exceeded 3 Times. Account Locked :"+username, new Exception("Account Locked By"+username));
						   attr.getRequest().getSession().setAttribute("error", new LockedException("Login Attempts Exceeded 3 times.Account has Been Locked. Please Contact Admin."));
						   throw new LockedException("Login Attempts Exceeded 3 times.Account has Been Locked. Please Contact Admin.");
					   }
					   else 
					   {
						   log.doLog(4, className, methodName, "Invalid Password Enter By:"+username);
						   logger.error("Entered Wrong Password", new Exception("Invalid Login"+username));
						   throw new BadCredentialsException("Invalid Username & Password.");
					   }
					}
			}else 
			{
				if (!encPass.equals(user.getPassword()) && failedLoginAttemptCount > 2)
				{
					 loginupdate = userService.updateLogin(user.getUserid(),user.getUsername(), user.getParticipantid(), 0);
					 log.doLog(4, className, methodName, "Login Attempts Exceeded 3 Times. Account Locked :"+username);
					 log.doLog(4, Utils.getLocalDate(), Utils.getLocalTime(), "Your Account Is Locked :"+username);
					 logger.error("Login Attempts Exceeded 3 Times. Account Locked::"+username, new Exception("Account Locked By"+username));
					 attr.getRequest().getSession().setAttribute("error", new LockedException("Login Attempts Exceeded 3 times.Account has Been Locked. Please Contact Admin."));
					 throw new LockedException("Login Attempts Exceeded 3 times.Account has Been Locked. Please Contact Admin.");	
				}
				else 
				{
					log.doLog(4, className, methodName, "Entered Wrong Password."+username);
					logger.error("Entered Wrong Password", new Exception("Invalid Login"+username));
					System.out.println("Invalid Username & Password."+username);
					throw new BadCredentialsException("Invalid Username & Password.");
				}
			}
		}
		else
		{
		    updateCount = userService.updateLoginAttempt(failedLoginAttemptCount + 1, user.getUserid());
		    loginupdate = userService.updateLogin(user.getUserid(),user.getUsername(), user.getParticipantid(), 0);
		    log.doLog(4, className, methodName, "Entered Wrong Password :"+username);
		    logger.error("Entered Wrong Password", new Exception("Invalid Login"));
		    System.out.println("Username Not Found:::::"+username);
		    throw new BadCredentialsException("Invalid Username & Password.");
		}
	   }
	    
	    else if (!instID.equals(user.getParticipantid()))
	    {
	    log.doLog(4, className, methodName, "Username not found :"+username);
	    logger.error("Username not found ::::"+username, new Exception("Invalid Login"+username));
	    System.out.println("Username Not Found:::::"+username);
	    throw new BadCredentialsException("Invalid Username & Password.");
	    }
	    else if (user.getUsername().equalsIgnoreCase(username) && encPass.equals(user.getPassword())
		    && instID.equals(user.getParticipantid()))
	    {
	    	failedLoginAttemptCount = userService.getFailedLoginAttempts(user.getUserid());
	    	if(failedLoginAttemptCount > 2)
	    	{
	    		loginupdate = userService.updateLogin(user.getUserid(),user.getUsername(), user.getParticipantid(), 0);
	    		log.doLog(4, className, methodName, "Login Attempts Exceeded 3 times.Account has Been Locked. Please Contact Admin :"+username);
	    		log.doLog(4, Utils.getLocalDate(), Utils.getLocalTime(), "Your Account Is Locked :"+username);
	    		logger.error("Login Attempts Exceeded 3 times.Account has Been Locked. Please Contact Admin :"+username, new Exception("Account Locked"));
	    		attr.getRequest().getSession().setAttribute("error", new LockedException("Login Attempts Exceeded 3 times.Account has Been Locked. Please Contact Admin."));
				throw new LockedException("Login Attempts Exceeded 3 times.Account has Been Locked. Please Contact Admin.");
	    	}
	    	else 
	    	{
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	Date lastPasswordChangeDate = user.getLast_password_changed_date();
		    	Date currentDate = new Date();
		    	
		    	String formattedDate = sdf.format(currentDate);
		    	long daysBetween = 0;
		    	Date currentFormattedDate;
		    	try
		    	{
		    		currentFormattedDate = sdf.parse(formattedDate);
		    		LocalDate localDate1 = currentFormattedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    		LocalDate localDate2 = lastPasswordChangeDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    		daysBetween = ChronoUnit.DAYS.between(localDate2, localDate1);
		    	}
		    	catch(Exception e)
		    	{
		    		e.printStackTrace();
		    	}
				if ((daysBetween >= 90)) 
				{
					user.setResetPasswordFlag(CommonConstant.PASSWORD_RESET_FLAG);
					int updatePasswordRestFlag = userService.updatePasswordRestFlag(user);
					System.out.println("USER FLAG UPDATE:::"+updatePasswordRestFlag);
					log.doLog(4, className, methodName, "Password Expired For User :"+username);
					logger.error("Password Expired For User :"+user.getUserid(), new Exception("Password Expired"));
					attr.getRequest().getSession().setAttribute("error", new LockedException("Password Expired.Please Contact Admin."));
				    throw new LockedException("Password Expired.Please Contact Admin.");
				} 
				else 
		         {
		        	 	updateCount = userService.updateLoginAttempt(0, user.getUserid());
						loginupdate = userService.updateLogin(user.getUserid(),user.getUsername(), user.getParticipantid(), 1);
						log.doLog(4, className, methodName, "User Login Successfully :"+username);
						logger.info("User Logged in Successfully:::::"+username);
						user.setLoginFlag(true);
						authorities = user.getAuthorities();
		         }
	    	}
	
	    }
	}
	else
	{
	    if (!env.getProperty("imps.superadmin.user").equalsIgnoreCase(username))
	    {
	    log.doLog(4, className, methodName, "Username not found :"+username);	
	    logger.error("Username not found ::::", new Exception("Invalid Login"));
	    throw new BadCredentialsException("Username not found.");
	    }
	    else if (!encPass.equals(env.getProperty("imps.superadmin.password")))
	    {
	    log.doLog(4, className, methodName, "Username not found :"+username);
	    logger.error("Username not found ::::", new Exception("Invalid Login"));
	    throw new BadCredentialsException("Invalid Username & Password.");
	    }
	    else if (env.getProperty("imps.superadmin.user").equalsIgnoreCase(username)
		    && encPass.equals(env.getProperty("imps.superadmin.password")))
	    {
		user = new CustomUser();
		user.setUsername(username);
		log.doLog(4, className, methodName, "User Log In Successfully :"+username);
		logger.info("User Logged in Successfully:::::"+username);
		user.setLoginFlag(true);
	    }
	}
	return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }


	public boolean supports(Class<?> arg0)
    {
	return true;
    }
}
