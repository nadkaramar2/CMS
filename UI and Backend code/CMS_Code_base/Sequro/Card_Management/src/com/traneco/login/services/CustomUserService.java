/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to define user and role menu mapping services.
 * 
 * History
 *=============================================================================================================================================
 *    Version         Date         		Author                     Purpose	
 *=============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to define user and role menu mapping services.
 *=============================================================================================================================================
 *
 */

package com.traneco.login.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.traneco.login.model.CustomUser;
import com.traneco.login.model.Menu;
import com.traneco.login.model.ParentMenu;
import com.traneco.login.model.SubParentMenu;
import com.traneco.login.model.UserLoginLog;
import com.traneco.user.model.UserPasswords;

@Service
public interface CustomUserService {
	public CustomUser loadUserByUsername(String username);
	public List<Menu> menuList(int roleid,int instid);
	public List<SubParentMenu> subParentMenuList(int roleid,int instid);
	public List<ParentMenu> ParentmenuList(int roleid,int instid);
	public List<Menu> menuidList(int roleid,int instid);
	public int updateLoginAttempt(int updateValue,int userid);
	public int updateLogin(int userid,String username,String institutionid,int isSuccesful);
	public int updateSuccessLogin(int userid,String institutionid);
	public int getFailedLoginAttempts(int userid);

	
	public int updateResetPassword(String confirmPassword, String resetPasswordFlag,String username, int userid);
	public List<UserPasswords> getUserPasswordsData(String username);
	
	public int updateResetPasswordFlag(int updateValue, String resetPasswordFlag, String username, int userid);
	public int updatePasswordRestFlag(CustomUser user);
	
	public List<UserLoginLog> getLastThreeFailedAttempts(CustomUser user);
	public int updateForgotPswdFailedAttempt(int value,String username, int userid);
	//public int updateLoginFailedAttemptCount(String username, int userid);
	
	
}
