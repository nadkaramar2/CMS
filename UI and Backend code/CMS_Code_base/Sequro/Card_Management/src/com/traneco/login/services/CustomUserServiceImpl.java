/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to define user and role menu mapping services and perform database business logic.
 * 
 * History
 *=============================================================================================================================================
 *    Version         Date         		Author                     Purpose	
 *=============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to define user and role menu mapping services and perform database business logic.
 *=============================================================================================================================================
 *
 */

package com.traneco.login.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.traneco.login.dao.CustomUserDao;
import com.traneco.login.model.CustomUser;
import com.traneco.login.model.Menu;
import com.traneco.login.model.ParentMenu;
import com.traneco.login.model.SubParentMenu;
import com.traneco.login.model.UserLoginLog;
import com.traneco.user.model.UserBean;
import com.traneco.user.model.UserPasswords;

@Service("customUserService")
public class CustomUserServiceImpl implements UserDetailsService, CustomUserService 
{
	@Autowired
	private CustomUserDao userDao;

	@ModelAttribute("userDetails")
	public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		return userDao.loadUserByUsername(username);
	}

	@Override
	public List<Menu> menuList(int roleid, int instid) 
	{
		return userDao.menuList(roleid, instid);
	}

	@Override
	public List<SubParentMenu> subParentMenuList(int roleid, int instid) {
		return userDao.subParentMenuList(roleid, instid);
	}

	@Override
	public List<ParentMenu> ParentmenuList(int roleid, int instid) {
		return userDao.ParentmenuList(roleid, instid);
	}

	@Override
	public List<Menu> menuidList(int roleid, int instid) {
		return userDao.menuidList(roleid, instid);
	}

	@Override
	public int updateLoginAttempt(int updateValue, int userid) 
	{
		return userDao.updateLoginAttempt(updateValue, userid);
	}

	@Override
	public int getFailedLoginAttempts(int userid) {
		return userDao.getFailedLoginAttempts(userid);
	}

	@Override
	public int updateLogin(int userid, String username, String institutionid, int isSuccesful) {
		return userDao.updateLogin(userid, username,institutionid, isSuccesful);
	}

	@Override
	public int updateSuccessLogin(int userid, String institutionid) {
		return userDao.updateSuccessLogin(userid, institutionid);
	}

	@Override
	public int updateResetPassword(String confirmPassword, String resetPasswordFlag, String username, int userid) 
	{
		return userDao.updateResetPassword(confirmPassword,resetPasswordFlag,username,userid);
	}

	@Override
	public List<UserPasswords> getUserPasswordsData(String username) 
	{
		return userDao.getUserPasswordsData(username);
	}

	public UserBean getUserDetails(String username) {
		
		return userDao.getUserDetails(username);
	}

	@Override
	public int updateResetPasswordFlag(int updateValue, String resetPasswordFlag, String username, int userid) {
		
		return userDao.updateResetPasswordFlag(updateValue,resetPasswordFlag,username,userid);
	}

	@Override
	public int updatePasswordRestFlag(CustomUser user) 
	{
		return userDao.updatePasswordRestFlag(user);
	}

	@Override
	public List<UserLoginLog> getLastThreeFailedAttempts(CustomUser user) 
	{
		return userDao.getLastThreeFailedAttempts(user);
	}

	@Override
	public int updateForgotPswdFailedAttempt(int value, String username, int userid) {
		
		return userDao.updateForgotPswdFailedAttempt(value,username,userid);
	}

	
}
