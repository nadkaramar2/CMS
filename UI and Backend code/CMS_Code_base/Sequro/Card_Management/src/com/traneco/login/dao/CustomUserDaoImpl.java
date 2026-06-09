/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to define user and role menu mapping and perform database business logic.
 * 
 *@History
 *=============================================================================================================================================
 *    @Version         @Date         	@Author                 @Purpose	
 *=============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to define user and role menu mapping and perform database business logic.
 *=============================================================================================================================================
 *
 */

package com.traneco.login.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.traneco.cmsaudit.services.AuditService;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.QueryConstant;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.login.model.CustomUser;
import com.traneco.login.model.Menu;
import com.traneco.login.model.ParentMenu;
import com.traneco.login.model.Permission;
import com.traneco.login.model.Role;
import com.traneco.login.model.SubParentMenu;
import com.traneco.login.model.UserLoginLog;
import com.traneco.user.model.UserBean;
import com.traneco.user.model.UserPasswords;
import com.traneco.user.services.UserService;

@Repository
public class CustomUserDaoImpl implements CustomUserDao 
{

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	JdbcTemplate jdbcCMSTemplate;

	@Autowired
	SessionDTO sessionDTO;

	@Autowired
	LoggerUtil log;

	@Autowired
	AuditService auditService;

	@Autowired
	UserService userService;

	@Autowired
	ConfigurationService configurationService;

	private String className = this.getClass().getSimpleName();
	
	

	@ModelAttribute("userDetails")
	public CustomUser loadUserByUsername(final String username) 
	{
		CustomUser user = null;
		String methodName = "loadUserByUsername";

		List<Permission> permission = null;
		try {
			log.doLog(2, className, methodName, "RequestParam : userName " + username);
			permission = new ArrayList<Permission>();
			user = jdbcTemplate.queryForObject(QueryConstant.LOAD_By_USER, new Object[] { username },
					new BeanPropertyRowMapper<CustomUser>(CustomUser.class));

			List<Map<String, Object>> permissionsList = jdbcTemplate.queryForList(QueryConstant.LOAD_ROLE_PERMISSION,
					new Object[] { user.getGroupID() });
			Role r = new Role();
			Permission permissionObj;
			for (Map<String, Object> map : permissionsList) 
			{
				permissionObj = new Permission();
				permissionObj.setName(String.valueOf(map.get("roleaction")));
				permission.add(permissionObj);
			}

			log.doLog(2, className, methodName, "ResponseCode " + user.toString());
			if (user.getRole() != null) 
			{
				r.setName(user.getRole() == null ? "" : user.getRole());
				r.setPermissions(permission);
				List<Role> roles = new ArrayList<Role>();
				roles.add(r);
				user.setAuthorities(roles);
				return user;
			} 
			else 
			{
				return null;
			}
		} 
		catch (EmptyResultDataAccessException e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Menu> menuList(int roleid, int instid) {
		return jdbcTemplate.query(QueryConstant.Load_MENU_LIST, new Object[] { roleid, instid },
				new BeanPropertyRowMapper<Menu>(Menu.class));
	}

	@Override
	public List<SubParentMenu> subParentMenuList(int roleid, int instid) {
		return jdbcTemplate.query(QueryConstant.LOAD_SUBPARENT_MENU_LIST, new Object[] { roleid, instid },
				new BeanPropertyRowMapper<SubParentMenu>(SubParentMenu.class));
	}

	@Override
	public List<ParentMenu> ParentmenuList(int roleid, int instid) {
		return jdbcTemplate.query(QueryConstant.LOAD_PARENT_MENU_LIST, new Object[] { roleid, instid },
				new BeanPropertyRowMapper<ParentMenu>(ParentMenu.class));
	}

	@Override
	public List<Menu> menuidList(int roleid, int instid) {
		return jdbcTemplate.query(QueryConstant.LOAD_MENUID, new Object[] { roleid, instid },
				new BeanPropertyRowMapper<Menu>(Menu.class));
	}

	@Override
	public int updateLoginAttempt(int updateValue, int userid) 
	{
		String methodName = "loadUserByUsername";
		log.doLog(2, className, methodName, "RequestParam : UserID " + userid + " updateValue " + updateValue);
		int updateCount = 0;
		updateCount = jdbcTemplate.update(QueryConstant.UPDATE_USERLOGIN_ATTEMPT, new Object[] { updateValue, userid });
		log.doLog(2, className, methodName, "ResponseParam : updateCount " + updateCount);
		return updateCount;
	}

	@Override
	public int getFailedLoginAttempts(int userid) {
		String methodName = "getFailedLoginAttempts";
		log.doLog(2, className, methodName, "RequestParam : UserID " + userid);
		int failedLoginAttemptCount = 0;
		failedLoginAttemptCount = (Integer) jdbcTemplate.queryForObject(QueryConstant.GET_USERLOGIN_ATTEMPT,
				new Object[] { userid }, Integer.class);
		log.doLog(2, className, methodName, "ResponseParam : failedLoginAttemptCount " + failedLoginAttemptCount);
		return failedLoginAttemptCount;
	}

	@Override
	public int updateLogin(int userid,String username, String institutionid, int isSuccesful) {
		String methodName = "updateLogin";
		log.doLog(2, className, methodName,
				"RequestParam : UserID " + userid + " USERNAME "+username+ " institutionid " + institutionid + " isSuccesful " + isSuccesful);
		int updateCount = 0;
		updateCount = jdbcTemplate.update(QueryConstant.UPDATE_USERLOGIN,
				new Object[] { institutionid, userid,username, Utils.generateDate(), " ", isSuccesful });
		log.doLog(2, className, methodName, "ResponseParam : updateCount " + updateCount);
		return updateCount;
	}

	@Override
	public int updateSuccessLogin(int userid, String institutionid) 
	{
		String methodName = "updateLogin";
		log.doLog(2, className, methodName, "RequestParam : UserID " + userid + " institutionid " + institutionid);
		try 
		{
			UserBean userBean = new UserBean();
			userBean.setStrUserID(String.valueOf(userid));
			userBean.setStrParticipantID(institutionid);
			userBean = userService.getOldUserDetailsBasedOnParticipantIdOrUserId(userBean);
			if (userBean != null) 
			{
				UserBean userBean1 = new UserBean();
				userBean1.setLastSuccessfulLogon(Utils.generateDate());
				sessionDTO.setParticipantid(userBean.getStrParticipantID());
				sessionDTO.setLoginID(userBean.getStrUserName());
				configurationService.updateAuditData(userBean1, userBean, "user_details");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		log.doLog(2, className, methodName, "RequestParam : UserID " + userid + " institutionid " + institutionid);
		int updateCount = 0;
		updateCount = jdbcTemplate.update(QueryConstant.UPDATE_SUCESS_USERLOGIN,
				new Object[] { Utils.generateDate(), institutionid, userid });
		log.doLog(2, className, methodName, "ResponseParam : updateCount " + updateCount);

		return updateCount;
	}

	@Override
	public int updateResetPassword(String confirmPassword, String resetPasswordFlag, String username,
			int userid) 
		{
		String methodName = "resetPassword";
		try 
		{
			UserBean userBean = new UserBean();
			userBean.setStrUserID(String.valueOf(userid));
			userBean.setStrUserName(username);
			userBean = userService.getOldUserDetailsBasedOnUserNameUserId(userBean);
			if (userBean != null) 
			{
				UserBean userBean1 = new UserBean();
				userBean1.setLastPasswordChange(Utils.getCurrentDate());
				userBean1.setStrPassword(confirmPassword);
				userBean1.setIsPasswordReset(resetPasswordFlag);
				sessionDTO.setUserID(userid);
				configurationService.updateAuditData(userBean1, userBean, methodName);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		log.doLog(2, className, methodName,
				"RequestParam : UserID " + userid + " confirmPassword " + confirmPassword);
		int updateCount = 0;
		updateCount = jdbcTemplate.update(QueryConstant.UPDATE_RESETPASSWORD_USER_DETAILS,
				new Object[] { new Date(), confirmPassword, resetPasswordFlag, username.trim(), userid });
		log.doLog(2, className, methodName, "ResponseParam : updateCount " + updateCount);

		return updateCount;

	}

	@Override
	public List<UserPasswords> getUserPasswordsData(String username) 
	{
		return jdbcTemplate.query(QueryConstant.RESET_PASSWORDS, new Object[] { username },
				new BeanPropertyRowMapper<UserPasswords>(UserPasswords.class));

	}

	@Override
	public UserBean getUserDetails(String username) 
	{
		UserBean userBean = null;
		userBean = (UserBean) jdbcTemplate.queryForObject(QueryConstant.USER_DETAILS, new Object[] { username },
				new BeanPropertyRowMapper<UserBean>(UserBean.class));
		return userBean;
	}

	@Override
	public int updateResetPasswordFlag(int updateValue, String resetPasswordFlag, String username, int userid) 
	{
		String methodName = "resetPasswordFlag";
		log.doLog(2, className, methodName,
				"RequestParam : UserID " + userid);
		int updateCount = 0;
		updateCount = jdbcTemplate.update(QueryConstant.UPDATE_RESETPASSWORD_FLAG_FOR_USER,
				new Object[] { updateValue, new Date(),resetPasswordFlag, username, userid });
		log.doLog(2, className, methodName, "ResponseParam : updateCount " + updateCount);
		return updateCount;
	}

	@Override
	public int updatePasswordRestFlag(CustomUser user) 
	{
		String methodName = "updatePasswordFalg";
		log.doLog(2, className, methodName,"RequestParam : user " + user);
		int updateCount = 0;
		updateCount = jdbcTemplate.update(QueryConstant.UPDATE_USER_FLAG,
				new Object[] { user.getResetPasswordFlag(),user.getUsername(), user.getMobileNo(),user.getEmail() });
		return updateCount;
	}

	@Override
	public List<UserLoginLog> getLastThreeFailedAttempts(CustomUser user) 
	{
		return jdbcTemplate.query(QueryConstant.LAST_THREE_FAILED_ATTEMPT, new Object[] { user.getParticipantid(),user.getUserid() },
				new BeanPropertyRowMapper<UserLoginLog>(UserLoginLog.class));
	}

	@Override
	public int updateForgotPswdFailedAttempt(int value, String username, int userid) 
	{
		String methodName = "updateForgotPswdFailedAttempt";
		log.doLog(2, className, methodName, "RequestParam : UserID " + userid);
		int updateCount = 0;
		updateCount = jdbcTemplate.update(QueryConstant.UPDATE_RESETPASSWORD_FLAG_FOR_USER,
				new Object[] { value,new Date(),"N", username, userid });
		log.doLog(2, className, methodName, "ResponseParam : updateCount " + updateCount);
		return updateCount;
	}

	

}
