package com.traneco.user.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.traneco.cmsaudit.services.AuditService;
import com.traneco.common.KeyValueBean;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.QueryConstant;
import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.util.AESEncDec;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.login.model.CustomUser;
import com.traneco.login.model.UserLoginLog;
import com.traneco.role.model.Cfg_Role;
import com.traneco.role.model.UserRoleMappingTemp;
import com.traneco.user.model.UserBean;
import com.traneco.user.services.PasswordGenerationService;
import com.traneco.user.services.UserService;

@Repository
public class UserDaoImpl implements UserDao 
{
	private String className = this.getClass().getSimpleName();
	
	String respCode = null;
	
	@Autowired
	JdbcTemplate jdbcCMSTemplate;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	@Autowired
	Environment env;

	@Autowired
	SessionDTO sessionDTO;

	@Autowired
	LoggerUtil log;

	@Autowired
	PasswordGenerationService passwordGenerationService;
	
	@Autowired
	AuditService auditService;
	
	@Autowired
	ConfigurationService configurationService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AESEncDec aESEncDec;

	
	@Override
	public String addUser(UserBean userBean) 
	{
		try 
		{
			String methodName = "addUser";

			log.doLog(2, className, methodName, "RequestParam :" + userBean.toString());

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withProcedureName(QueryConstant.SP_USER_MANAGEMENT);

			Map<String, Object> inParamMap = new HashMap<String, Object>();

			inParamMap.put("P_Action", TranecoStatusCode.REQTYPE_ADD);
			inParamMap.put("P_ParticipantId", sessionDTO.getParticipantid());
			inParamMap.put("P_UserTempID", null);
			inParamMap.put("P_UserID", null);
			inParamMap.put("P_RoleID", userBean.getStrRoleID());
			inParamMap.put("P_UserAccessTypeId", userBean.getStrUserAccessType().equals("DB") ? "1" : "2");
			inParamMap.put("P_FirstName", userBean.getStrFirstName());
			inParamMap.put("P_LastName", userBean.getStrLastName());
			inParamMap.put("P_UserName", userBean.getStrUserName());
			inParamMap.put("P_MobileNumber", userBean.getStrMobileNo());
			inParamMap.put("P_EmailID", userBean.getStrEmailID());
			inParamMap.put("P_SecretQuestionID", userBean.getStrSecQueID());
			inParamMap.put("P_SecretQuestionAnswer", userBean.getStrSecQueAns());
			inParamMap.put("P_UserPass", null);
			inParamMap.put("P_SensitiveData", userBean.getStrSensitiveFlag().equals("Y") ? "1" : "0");
			inParamMap.put("P_UserStatusID", 1);
			inParamMap.put("P_Remark", null);
			inParamMap.put("P_CreatedBy", sessionDTO.getUserID());
			inParamMap.put("P_ApprovedBy", null);
			inParamMap.put("P_ApprovalStatusID", null);
			inParamMap.put("P_UserStatusId", null);
			inParamMap.put("P_GroupID", userBean.getStrGroupID());
			inParamMap.put("plain_password", null);
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);

			log.doLog(2, className, methodName, "StoreProcedure Calling : " + QueryConstant.SP_USER_MANAGEMENT);

			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);

			simpleJdbcCallResult.forEach((k, v) -> 
			{
				if ("p_responsecode".equalsIgnoreCase(k)) 
				{
					respCode = String.valueOf(v);
				}
			});
			log.doLog(2, className, methodName, "ResponseCode StoreProcedure " + respCode);
			return respCode;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/* FOr Changing User Management Through Code(Add User)
	@Override
	public String addUser(UserBean userBean)
	{
		if (Optional.ofNullable(userBean).isPresent() && userBean.getStrRequestID().equalsIgnoreCase(TranecoStatusCode.REQTYPE_ADD))
		{
			boolean isUserExist = userService.checkUserExistinBothUserDetailandTemp(userBean);
			if (isUserExist == false)
			{
				UserBean userDetailsTemp = new UserBean();
				userDetailsTemp = userService.getUserDetailsTemp();
				if (userDetailsTemp != null && Optional.ofNullable(userDetailsTemp.getStrUserID()).isPresent())
				{
					userBean.setStrUserID(String.valueOf(Integer.parseInt(userDetailsTemp.getStrUserID()) + 1));
					userBean.setStrUserTypeId(3);
					userBean.setUserFullName(userBean.getStrFirstName() + " " + userBean.getStrLastName());
					userBean.setStrUserStatusID(String.valueOf(1));
					userBean.setApprovalStatusId(3);
					int count = userService.addUserDetailsTemp(userBean);
					if (count > 0)
					{
						Cfg_Role cfgRole = new Cfg_Role();
						cfgRole.setGroupId(Integer.parseInt(userBean.getStrGroupID()));
						cfgRole = userService.getLastCfgRoleRecord(cfgRole);
						if (cfgRole != null && String.valueOf(cfgRole.getId()) != null)
					  {
						
							UserRoleMappingTemp userRoleMappingTemp = new UserRoleMappingTemp();
							userRoleMappingTemp = userService.getUserRoleMappingTempLastInsert();
							if (userRoleMappingTemp != null && Optional.ofNullable(userRoleMappingTemp.getUserRoleId()).isPresent())
							{
								userBean.setStrRoleID(String.valueOf(cfgRole.getRoleId()));
								userBean.setUserRoleId((userRoleMappingTemp.getUserRoleId()) + 1);
								int addUserRoleMappingTemp = userService.addUserRoleMappingTemp(userBean);
								if (addUserRoleMappingTemp > 0)
								{
									userBean.setResponseCode("00");
									userBean.setStatus("Success");
									userBean.setMessage("User Added Successfully !....");
								}
								else
								{
									userBean.setResponseCode("01");
								}
							}else 
							{
								userBean.setResponseCode("01");
							}
							
					}else {
						userBean.setResponseCode("01");
					}
						
				}else {
					userBean.setResponseCode("01");
				}
			}	
				else 
			{
				userBean.setResponseCode("02");
			}
		  }
			else 
			{
				userBean.setResponseCode("02");
			}
		}
		else 
		{
			//Reject
		}
		return userBean.getResponseCode();
		
	}
	*/
	
	
	@Override
	public String approvalPendingList(String userID, String approvalID, String remark, UserBean userBean) 
	{
		String methodName = "approvalPendingList";
		String[] data = userID.split("\\|");

		log.doLog(2, className, methodName, "RequestParam : userID " + userID);

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(QueryConstant.SP_USER_MANAGEMENT);
		Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("P_Action", TranecoStatusCode.REQTYPE_APPROVE);
		inParamMap.put("P_ParticipantId", sessionDTO.getParticipantid());
		inParamMap.put("P_UserTempID", data[0]);
		inParamMap.put("P_UserID", data[1]);
		inParamMap.put("P_RoleID", null);
		inParamMap.put("P_UserAccessTypeId", null);
		inParamMap.put("P_FirstName", null);
		inParamMap.put("P_LastName", null);
		inParamMap.put("P_UserName", null);
		inParamMap.put("P_MobileNumber", null);
		inParamMap.put("P_EmailID", null);
		inParamMap.put("P_SecretQuestionID", null);
		inParamMap.put("P_SecretQuestionAnswer", null);
		// inParamMap.put("P_UserPass", env.getProperty("imps.superadmin.password"));
		
		//String encryptedPassword = passwordGenerationService.getEncryptedPassword();
		//String generateSecurePassword = aESEncDec.encrypt(encDecUtils.getSecretKeys(), encryptedPassword);
		String plainPassword = passwordGenerationService.getEncryptedPassword();
		String salt = env.getProperty("imps.user.login.salt.password");
		String generateSecurePassword = Utils.generateSecurePassword(plainPassword, salt);
		inParamMap.put("P_UserPass", generateSecurePassword);
		
		inParamMap.put("P_SensitiveData", null);
		inParamMap.put("P_Remark", remark);
		inParamMap.put("P_CreatedBy", null);
		inParamMap.put("P_ApprovedBy", sessionDTO.getUserID());
		inParamMap.put("P_ApprovalStatusID", approvalID);
		inParamMap.put("P_UserStatusId", null);
		inParamMap.put("P_GroupID", data[4]);
		
		String plainPasswordForEnc = plainPassword;
		try 
		{
			String encPassword;
			encPassword = aESEncDec.encrypt(plainPasswordForEnc);
			inParamMap.put("plain_password", encPassword);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		log.doLog(2, className, methodName, "StoreProcedure Calling : " + QueryConstant.SP_USER_MANAGEMENT);

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		simpleJdbcCallResult.forEach((k, v) -> 
		{
			if ("p_responsecode".equalsIgnoreCase(k)) 
			{
				respCode = String.valueOf(v);
			}
		});
		log.doLog(2, className, methodName, "ResponseCode StoreProcedure " + respCode);
		return respCode;
	}
	
	
	/* For Changing Approving User Management Through Code 
	@Override
	public String approvalPendingList(String userID, String approvalID, String remark, UserBean userBean)
	{
		//String methodName = "approvalPendingList";
		String[] data = userID.split("\\|");
		try 
		{
			if (Optional.ofNullable(userBean).isPresent() && userBean.getStrRequestID().equalsIgnoreCase(TranecoStatusCode.REQTYPE_APPROVE))
			{
				if (Integer.parseInt(approvalID) == 1)
				{
					String plainPassword = passwordGenerationService.getEncryptedPassword();
					String salt = env.getProperty("imps.user.login.salt.password");
					String generateSecurePassword = Utils.generateSecurePassword(plainPassword, salt);
					String encPassword = aESEncDec.encrypt(plainPassword);
					
					userBean.setStrPassword(generateSecurePassword);
					userBean.setPlainPassword(encPassword);
					userBean.setStrRemark(remark);
					userBean.setStrParticipantID(sessionDTO.getParticipantid());
					userBean.setStrUserTempID(data[0]);
					userBean.setStrUserID(data[1]);
					boolean UserExist = userService.checkUserExistBasedOnUserId(userBean);
					if (UserExist == false)
					{
						userBean = userService.getUserDetailsBasesdOnMobileNoEmailId(userBean);
						userBean = userService.getUserDetailOnUserName(userBean);
						int count = userService.addUserDetails(userBean);
						if (count > 0)
						{
							int addUserRoleMapping = userService.addUserRoleMapping(userBean);
							if (addUserRoleMapping > 0)
							{
								userBean.setResponseCode("00");
							}
						}
					}
					else 
					{
						userBean.setResponseCode("02");
						userBean.setStatus("failed");
						userBean.setMessage("User Already Exist !...");
					}
				}
				else 
				{
					//Reject
					userBean.setResponseCode("02");
				}
			}
			else 
			{
				//Reject
				userBean.setResponseCode("02");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return userBean.getResponseCode();
		
	}
	
	*/
	

	@Override
	public int checkApprovalPendingCount(String userID) {
		int count = (int) jdbcTemplate.queryForObject(QueryConstant.CHECK_APPROVAL_PENDING_STATUS_USER,
				new Object[] { userID }, Integer.class);

		return count;
	}

	@Override
	public String editUser(UserBean userBean) throws NoSuchAlgorithmException 
	{
		String methodName = "editUser";

		log.doLog(2, className, methodName, "RequestParam : " + userBean.toString());

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(QueryConstant.SP_USER_MANAGEMENT);
		Map<String, Object> inParamMap = new HashMap<String, Object>();

		inParamMap.put("P_Action", TranecoStatusCode.REQTYPE_EDIT);
		inParamMap.put("P_ParticipantId", sessionDTO.getParticipantid());
		inParamMap.put("P_UserTempID", null);
		inParamMap.put("P_UserID", userBean.getStrUserID());
		inParamMap.put("P_RoleID", userBean.getStrRoleID());
		inParamMap.put("P_UserAccessTypeId", userBean.getStrUserAccessType().equals("DB") ? "1" : "2");
		inParamMap.put("P_FirstName", userBean.getStrFirstName());
		inParamMap.put("P_LastName", userBean.getStrLastName());
		inParamMap.put("P_UserName", userBean.getStrUserName());
		inParamMap.put("P_MobileNumber", userBean.getStrMobileNo());
		inParamMap.put("P_EmailID", userBean.getStrEmailID());
		inParamMap.put("P_SecretQuestionID", userBean.getStrSecQueID());
		inParamMap.put("P_SecretQuestionAnswer", userBean.getStrSecQueAns());
		inParamMap.put("P_UserPass", Utils.generateSecurePassword(userBean.getStrPassword(),
				env.getProperty("imps.user.login.salt.password")));
		inParamMap.put("P_SensitiveData", userBean.getStrSensitiveFlag().equals("Y") ? "1" : "0");
		inParamMap.put("P_UserStatusID", userBean.getStrUserStatusID());
		inParamMap.put("P_Remark", null);
		inParamMap.put("P_CreatedBy", sessionDTO.getUserID());
		inParamMap.put("P_ApprovedBy", sessionDTO.getUserID());
		inParamMap.put("P_ApprovalStatusID", null);
		/* inParamMap.put("P_UserStatusId", userBean.getUserStatusID()); */
		inParamMap.put("P_GroupID", userBean.getStrGroupID());
		
		//Added by Prashant T 29Dec 2023
		inParamMap.put("plain_password", userBean.getStrPassword());
		//inParamMap.put("isPasswordReset", "N");
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		log.doLog(2, className, methodName, "StoreProcedure Calling : " + QueryConstant.SP_USER_MANAGEMENT);

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);

		simpleJdbcCallResult.forEach((k, v) -> 
		{
			if ("p_responsecode".equalsIgnoreCase(k)) 
			{
				respCode = String.valueOf(v);
			}
		});
		log.doLog(2, className, methodName, "ResponseCode StoreProcedure " + respCode);

		return respCode;
	}

	@Override
	public List<UserBean> getApprovalPendingList() {
		List<UserBean> userList = new ArrayList<UserBean>();

		userList = jdbcTemplate.query(QueryConstant.LOAD_APPROVAL_PENDING_LIST_USER,
				// new Object[] { sessionDTO.getParticipantid(), sessionDTO.getUserID() },
				new Object[] { sessionDTO.getParticipantid(), sessionDTO.getLoginID() },
				new BeanPropertyRowMapper<UserBean>(UserBean.class));

		return userList;
	}

	@Override
	public List<KeyValueBean> getRoleList() {
		List<KeyValueBean> roleList = new ArrayList<KeyValueBean>();

		roleList = jdbcTemplate.query(QueryConstant.GET_ROLE_LIST, new Object[] { sessionDTO.getParticipantid() },
				new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));

		return roleList;
	}

	@Override
	public List<KeyValueBean> getSecretQuestionList() {
		List<KeyValueBean> secretQuestList = new ArrayList<KeyValueBean>();

		secretQuestList = jdbcTemplate.query(QueryConstant.GET_SECRET_QUESTION_LIST, new Object[] {},
				new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));

		return secretQuestList;
	}

	@Override
	public UserBean getUserDetails(String userID) {
		@SuppressWarnings("unused")
		UserBean userBean = null;

		userBean = (UserBean) jdbcTemplate.queryForObject(QueryConstant.GET_USER_DETAILS_BY_USERID,
				new Object[] { sessionDTO.getParticipantid(), userID },
				new BeanPropertyRowMapper<UserBean>(UserBean.class));

		return userBean;
	}

	@Override
	public List<UserBean> getUserList() {
		List<UserBean> userList = new ArrayList<UserBean>();

		userList = jdbcTemplate.query(QueryConstant.GET_USER_LIST, new Object[] { sessionDTO.getParticipantid() },
				new BeanPropertyRowMapper<UserBean>(UserBean.class));

		return userList;
	}

	@Override
	public List<KeyValueBean> getUserStatusList() {
		List<KeyValueBean> userList = new ArrayList<KeyValueBean>();

		userList = jdbcTemplate.query(QueryConstant.GET_USER_STATUS,
				new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));

		return userList;
	}

	@Override
	public UserBean getUserIdDetails(String userID) {
		@SuppressWarnings("unused")
		UserBean userBean = null;

		userBean = (UserBean) jdbcTemplate.queryForObject(QueryConstant.GET_USER_DETAILS_BY_USERID_API,
				new Object[] { sessionDTO.getParticipantid(), userID },
				new BeanPropertyRowMapper<UserBean>(UserBean.class));

		return userBean;
	}

	@Override
	public UserBean getUserProfile() {
		UserBean userBean = null;

		userBean = (UserBean) jdbcTemplate.queryForObject(QueryConstant.GET_USER_DETAILS_BY_USERID,
				new Object[] { sessionDTO.getParticipantid(), sessionDTO.getUserID() },
				new BeanPropertyRowMapper<UserBean>(UserBean.class));
		return userBean;

	}

	@Override
	public List<KeyValueBean> getGroupList(String partID) {
		List<KeyValueBean> groupList = jdbcTemplate.query(QueryConstant.GET_GROUP_LIST, new Object[] { partID },
				new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));
		return groupList;
	}

	@Override
	public boolean checkUserExist(UserBean userBean) 
	{
		Integer count = jdbcTemplate.queryForObject("Select count(*) from user_details where user_name = ? ",
				new Object[] { userBean.getStrUserName() }, (Integer.class));

		if (count > 0) 
		{
			return true;
		}
		return false;
	}

	@Override
	public int addUserRoleMapping(UserBean userBean) 
	{
		int count = this.jdbcTemplate.update(
				"INSERT INTO user_role_mapping(user_id,role_id,participant_id,user_status,created_by,created_date,group_id) VALUES (?,?,?,?,?,?,?)",
				new Object[] {userBean.getStrUserID(), userBean.getStrRoleID(), sessionDTO.getParticipantid(), userBean.getStrUserStatusID(),
						userBean.getStrCreatedBy(), new Date(), userBean.getStrGroupID() });
		return count;
	}

	@Override
	public UserBean getUserDetailsBasesdOnMobileNoEmailId(UserBean userBean) 
	{
		userBean = (UserBean) jdbcTemplate.queryForObject(QueryConstant.GET_USER_DETAILS_BY_MOBILENO_EMAIL,
				new Object[] { userBean.getStrUserName(), userBean.getStrEmailID() },
				new BeanPropertyRowMapper<UserBean>(UserBean.class));
		return userBean;

	}

	@Override
	public int updateUserPassword(UserBean userBean) 
	{
		try 
		{
			UserBean userBean1 = userBean;
			userBean = userService.getOldUserDetailsBasedOnUsrNameEmailId(userBean);
			if (userBean.getStrEmailID() != null)
			{
				configurationService.updateAuditData(userBean1, userBean, "user_details");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcTemplate.update(QueryConstant.UPDATE_USER_PASS, new Object[] { userBean.getStrPassword(),
				userBean.getStrPassword(), userBean.getStrUserName(), userBean.getStrEmailID() });
		return count;

	}

	@Override
	public int insertPasswordResetOnUser(UserBean userBean) 
	{
		int count = this.jdbcTemplate.update(QueryConstant.IS_PASSWORD_RESET,
				new Object[] { userBean.getStrUserName(), userBean.getStrEmailID(), userBean.getIsPasswordReset(), new Date() });
		return count;

	}

	@Override
	public int storePlainPassword(String plainPassword) {
		int count = this.jdbcTemplate.update(QueryConstant.PLAIN_PASSWORD_USERDETAILS, new Object[] { plainPassword });
		return count;
	}

	@Override
	public int addOldNewPassword(CustomUser user, String confNewEncPass) 
	{
		user.setNew_password(confNewEncPass);
		user.setLast_password_changed_date(new Date());
		int count = this.jdbcTemplate.update(QueryConstant.ADD_OLD_NEW_PASSWORDS, new Object[] { user.getUserid(),
				user.getUsername(), user.getEmail(),user.getPassword(), user.getNew_password(), user.getLast_password_changed_date() });
		return count;

	}

	@Override
	public int insertAuditRecord(UserBean userBean, String tableName) 
	{
		int count = this.jdbcTemplate.update(
				"INSERT INTO cms_audit (participant_id,table_name,column_name,new_field,old_field,dml_action,created_date,created_by,approved_by) VALUES (?,?,?,?,?,?,?,?,?)",
				new Object[] { sessionDTO.getParticipantid(),tableName, userBean.getStrUserName(), null, null, userBean.getStrRequestID(),new Timestamp(System.currentTimeMillis()), sessionDTO.getLoginID(),null});
		return count;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public UserBean getOldUserDetails(UserBean userBean) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strId,user_id AS strUserID,participant_id AS strParticipantID,user_name AS strUserName,mobile_number AS strMobileNo,email_id AS strEmailID, "
					+ "last_successful_logon AS lastSuccessfulLogon,login_failed_attemps_count AS loginFailedAttemptsCount,forgot_password_validation_failed_attempt AS forgotPasswordValidationFailedAttempt, "
					+ "last_password_change_datetime AS lastPasswordChange,user_password AS strPassword,user_status AS strUserStatusID,created_by AS strCreatedBy,plain_password AS plainPassword,isPasswordReset AS isPasswordReset "
					+ " FROM user_details "
					+ "WHERE user_id = "+userBean.getStrUserID()+" ";
			
			List<UserBean> userBeans = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {}, 
					(RowMapper) new BeanPropertyRowMapper(UserBean.class));
			
			System.out.println("userBeans:::" +userBeans);
			
			if (userBeans !=null && userBeans.size() > 0) 
			{
				return userBeans.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserBean getOldUseRoleMappingDetails(UserBean userBean) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strId,user_id AS strUserID,role_id AS strRoleID, participant_id AS strParticipantID,user_status AS strUserStatusID, "
					+ "created_by AS strCreatedBy "
					+ " FROM user_role_mapping "
					+ "WHERE id = "+userBean.getStrId()+" OR user_id = "+userBean.getStrUserID()+" ";
			
			List<UserBean> userBeans = this.jdbcCMSTemplate.query(sqlQuery,new Object[] {}, 
					(RowMapper<UserBean>) new BeanPropertyRowMapper<UserBean>(UserBean.class));
			
			System.out.println("userBeans:::" +userBeans);
			
			if (userBeans !=null && userBeans.size() > 0) 
			{
				return userBeans.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserBean getOldUserDetailsTemp(UserBean userBean) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strId,user_id AS strUserID,participant_id AS strParticipantID,user_name AS strUserName,mobile_number AS strMobileNo,email_id AS strEmailID, "
					+ "last_successful_logon AS lastSuccessfulLogon,login_failed_attemps_count AS loginFailedAttemptsCount,forgot_password_validation_failed_attempt AS forgotPasswordValidationFailedAttempt, "
					+ "last_password_change_datetime AS lastPasswordChange,user_password AS strPassword,user_status AS strUserStatusID,created_by AS strCreatedBy,plain_password AS plainPassword,isPasswordReset AS isPasswordReset"
					+ " FROM user_details_temp"
					+ "WHERE id = "+userBean.getStrId()+" OR user_id = "+userBean.getStrUserID()+" ";
			List<UserBean> userBeans = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {}, 
					(RowMapper<UserBean>) new BeanPropertyRowMapper<UserBean>(UserBean.class));
			
			System.out.println("userBeans:::" +userBeans);
			
			if (userBeans !=null && userBeans.size() > 0) 
			{
				return userBeans.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserBean getOldUserRoleMappingTemp(UserBean userBean) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strId,user_id AS strUserID,role_id AS strRoleID, participant_id AS strParticipantID,user_status AS strUserStatusID, "
					+ "created_by AS strCreatedBy,  "
					+ " FROM user_role_mapping_temp "
					+ "WHERE id = "+userBean.getStrId()+" OR user_id = "+userBean.getStrUserID()+" ";
			List<UserBean> userBeans = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {}, 
					(RowMapper<UserBean>) new BeanPropertyRowMapper<UserBean>(UserBean.class));
			
			System.out.println("userBeans:::" +userBeans);
			
			if (userBeans !=null && userBeans.size() > 0) 
			{
				return userBeans.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserBean getOldUserDetailsBasedOnUsrNameEmailId(UserBean userBean) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strId,user_id AS strUserID,participant_id AS strParticipantID,user_name AS strUserName,mobile_number AS strMobileNo,email_id AS strEmailID, "
					+ "last_successful_logon AS lastSuccessfulLogon,login_failed_attemps_count AS loginFailedAttemptsCount,forgot_password_validation_failed_attempt AS forgotPasswordValidationFailedAttempt, "
					+ "last_password_change_datetime AS lastPasswordChange,user_password AS strPassword,user_status AS strUserStatusID,created_by AS strCreatedBy,plain_password AS plainPassword,isPasswordReset AS isPasswordReset "
					+ " FROM user_details "
					+ "WHERE user_name = "+userBean.getStrUserName()+" AND "+userBean.getStrEmailID()+" ";
			
			List<UserBean> userBeans = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {}, 
					(RowMapper<UserBean>) new BeanPropertyRowMapper<UserBean>(UserBean.class));
			if (userBeans !=null && userBeans.size() > 0) 
			{
				return userBeans.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserBean getOldUserDetailsBasedOnUserId(UserBean userBean) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strId,user_id AS strUserID,participant_id AS strParticipantID,user_name AS strUserName,mobile_number AS strMobileNo,email_id AS strEmailID, "
					+ "login_failed_attemps_count AS loginFailedAttemptsCount,forgot_password_validation_failed_attempt AS forgotPasswordValidationFailedAttempt, "
					+ "last_password_change_datetime AS lastPasswordChange,user_password AS strPassword,user_status AS strUserStatusID,created_by AS strCreatedBy,plain_password AS plainPassword,isPasswordReset AS isPasswordReset "
					+ " FROM user_details "
					+ "WHERE user_id = "+userBean.getStrUserID()+" ";
			
			List<UserBean> userBeans = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {}, 
					(RowMapper<UserBean>) new BeanPropertyRowMapper<UserBean>(UserBean.class));
			if (userBeans !=null && userBeans.size() > 0) 
			{
				return userBeans.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserBean getOldUserDetailsBasedOnParticipantIdOrUserId(UserBean userBean) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strId,user_id AS strUserID,participant_id AS strParticipantID,user_name AS strUserName,mobile_number AS strMobileNo,email_id AS strEmailID, "
					+ "last_successful_logon AS lastSuccessfulLogon,login_failed_attemps_count AS loginFailedAttemptsCount,forgot_password_validation_failed_attempt AS forgotPasswordValidationFailedAttempt, "
					+ "last_password_change_datetime AS lastPasswordChange,user_password AS strPassword,user_status AS strUserStatusID,created_by AS strCreatedBy,plain_password AS plainPassword,isPasswordReset AS isPasswordReset "
					+ " FROM user_details "
					+ "WHERE participant_id = "+userBean.getStrParticipantID()+" AND user_id = "+userBean.getStrUserID()+" ";
			
			List<UserBean> userBeans = this.jdbcTemplate.query(sqlQuery,
					new Object[] {}, 
					(RowMapper<UserBean>) new BeanPropertyRowMapper<UserBean>(UserBean.class));
			if (userBeans !=null && userBeans.size() > 0) 
			{
				return userBeans.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public UserBean getOldUserDetailsBasedOnUserNameUserId(UserBean userBean) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strId,user_id AS strUserID,participant_id AS strParticipantID,user_name AS strUserName,mobile_number AS strMobileNo,email_id AS strEmailID, "
					+ "last_successful_logon AS lastSuccessfulLogon,login_failed_attemps_count AS loginFailedAttemptsCount,forgot_password_validation_failed_attempt AS forgotPasswordValidationFailedAttempt, "
					+ "last_password_change_datetime AS lastPasswordChange,user_password AS strPassword,user_status AS strUserStatusID,created_by AS strCreatedBy,plain_password AS plainPassword,isPasswordReset AS isPasswordReset "
					+ " FROM user_details "
					+ "WHERE user_name = "+userBean.getStrUserName()+" AND  user_id = "+userBean.getStrUserID()+" ";
			
			List<UserBean> userBeans = this.jdbcTemplate.query(sqlQuery,
					new Object[] {}, 
					(RowMapper<UserBean>) new BeanPropertyRowMapper<UserBean>(UserBean.class));
			if (userBeans !=null && userBeans.size() > 0) 
			{
				return userBeans.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateUserPlainPassword(UserBean userBean) 
	{
		int count = this.jdbcTemplate.update(QueryConstant.UPDATE_USER_PLAIN_PASS, new Object[] {userBean.getForgotPasswordValidationFailedAttempt(), userBean.getLoginFailedAttemptsCount(), 
				new Date(),
				userBean.getPlainPassword(),
				userBean.getIsPasswordReset(), userBean.getStrUserName(), userBean.getStrMobileNo(), userBean.getStrEmailID() });
		return count;
	
	}

	@Override
	public int updateLogoutTime(UserLoginLog userLoginLog) 
	{
		int count = jdbcTemplate.update("UPDATE user_login_log "
				+ "SET logout_datetime = ? "
				+ "WHERE user_id = ? AND username = ?  AND login_datetime = ? ",
				new Object[]
				{
					Utils.generateDate(),
					userLoginLog.getUserId(),
					userLoginLog.getUsername(),
					userLoginLog.getLoginDateTime()
					
				});
		return count;
	}

	@Override
	public UserLoginLog getUserLoginData(UserLoginLog userLoginLog) {
		try 
		{
			String sqlQuery = "SELECT id AS id,participant_id AS participantId,user_id AS userId,username AS username,login_datetime AS loginDateTime,logout_datetime AS logoutDateTime,is_successful AS isSuccessful"
					+ " FROM user_login_log WHERE user_id = '"+userLoginLog.getUserId()+"' AND username = '"+userLoginLog.getUsername()+"' "
					+ " ORDER BY login_datetime Desc LIMIT 1 ";
			List<UserLoginLog> userLoginLogs = this.jdbcTemplate.query(sqlQuery,
					new Object[] {}, 
					(RowMapper<UserLoginLog>) new BeanPropertyRowMapper<UserLoginLog>(UserLoginLog.class));
			if (userLoginLogs !=null && userLoginLogs.size() > 0) 
			{
				return userLoginLogs.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserBean getUserNameBasedOnUserId(UserBean userBean) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strId,user_id AS strUserID,participant_id AS strParticipantID,user_name AS strUserName,mobile_number AS strMobileNo,email_id AS strEmailID, "
					+ "last_successful_logon AS lastSuccessfulLogon,login_failed_attemps_count AS loginFailedAttemptsCount,forgot_password_validation_failed_attempt AS forgotPasswordValidationFailedAttempt, "
					+ "last_password_change_datetime AS lastPasswordChange,user_password AS strPassword,user_status AS strUserStatusID,created_by AS strCreatedBy,plain_password AS plainPassword,isPasswordReset AS isPasswordReset "
					+ " FROM user_details "
					+ "WHERE user_id = ? ";
			
			List<UserBean> userBeans = this.jdbcTemplate.query(sqlQuery,
					new Object[] {userBean.getStrUserID()}, 
					(RowMapper<UserBean>) new BeanPropertyRowMapper<UserBean>(UserBean.class));
			if (userBeans !=null && userBeans.size() > 0) 
			{
				return userBeans.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public boolean checkUserExistinBothUserDetailandTemp(UserBean userBean) 
	{
		Integer count = jdbcTemplate.queryForObject("SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 "
				+ " END AS count "
				+ "FROM user_details_temp td "
				+ "INNER JOIN user_details d ON td.participant_id = d.participant_id "
				+ "WHERE td.user_name = ? ",
				new Object[] { userBean.getStrUserName() }, (Integer.class));

		if (count > 0) 
		{
			return true;
		}
		return false;
	}



	@Override
	public UserBean getUserDetailsTemp() 
	{
		try 
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT id AS strId, user_id AS strUserID,user_type_id AS strUserAccessType,participant_id AS strParticipantID, ");
			sqlQuery.append("user_access_type_id AS strUserAccessType,user_name AS strUserName,mobile_number AS strMobileNo,email_id AS strEmailID ");
			sqlQuery.append("FROM user_details_temp ORDER BY user_id DESC LIMIT 1 ");
			List<UserBean> userDetailsTemp = this.jdbcTemplate.query(sqlQuery.toString(), new Object[] {}, (RowMapper<UserBean>) new BeanPropertyRowMapper<UserBean>(UserBean.class));
			
			if (userDetailsTemp !=null && userDetailsTemp.size() > 0) 
			{
				return userDetailsTemp.get(0);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public int addUserDetailsTemp(UserBean userBean) 
	{
		try
		{
			int count = this.jdbcTemplate.update("INSERT INTO `user_details_temp`(user_id,user_type_id,participant_id,user_access_type_id,user_full_name,user_name,mobile_number,email_id,secret_question_id,secret_question_answer,user_status_id,sensitive_date,created_by,approval_status_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] {userBean.getStrUserID(), userBean.getStrUserTypeId(),
							sessionDTO.getParticipantid(), userBean.getStrUserAccessType().equals("DB") ? "1" : "2",
							userBean.getUserFullName(),userBean.getStrUserName(),
							userBean.getStrMobileNo(),userBean.getStrEmailID(),
							userBean.getStrSecQueID(),userBean.getStrSecQueAns(),
							userBean.getStrUserStatusID(),
							userBean.getStrSensitiveFlag().equals("DB") ? "1" : "2",
							sessionDTO.getUserID(),userBean.getApprovalStatusId()});
			return count;
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return 0;
	}


	@Override
	public Cfg_Role getLastCfgRoleRecord(Cfg_Role cfgRole) 
	{
		try 
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT id AS id, role_id AS roleId ");
			sqlQuery.append(" From cfg_role_temp WHERE group_id = '"+cfgRole.getGroupId()+"' ");
			List<Cfg_Role> cfgRoleLastRecord = this.jdbcTemplate.query(sqlQuery.toString(), new Object[] {}, (RowMapper<Cfg_Role>) new BeanPropertyRowMapper<Cfg_Role>(Cfg_Role.class));
			
			if (cfgRoleLastRecord != null && cfgRoleLastRecord.size() > 0) 
			{
				return cfgRoleLastRecord.get(0);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int addUserRoleMappingTemp(UserBean userBean) 
	{
		try
		{
			int count = this.jdbcTemplate.update("INSERT INTO `user_role_mapping_temp`(user_role_id,user_id,role_id,participant_id,user_status,created_by,created_date,approval_status_id,group_id) VALUES (?,?,?,?,?,?,?,?,?)",
					new Object[] { 
							userBean.getUserRoleId(),
							userBean.getStrUserID(),userBean.getStrRoleID(),
							sessionDTO.getParticipantid(),1,
							sessionDTO.getUserID(),Utils.generateDate(),3, userBean.getStrGroupID()});
			return count;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}



	@SuppressWarnings("unchecked")
	@Override
	public UserRoleMappingTemp getUserRoleMappingTempLastInsert() 
	{
		try 
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT id AS id, user_role_id AS userRoleId,user_id AS userId,role_id AS roleId ");
             sqlQuery.append(" From user_role_mapping_temp ORDER BY user_role_id DESC LIMIT 1 ");
			
			List<UserRoleMappingTemp> userRoleMappingTemp = this.jdbcTemplate.query(sqlQuery.toString(), new Object[] {}, (RowMapper) new BeanPropertyRowMapper(UserRoleMappingTemp.class));
			
			if (userRoleMappingTemp != null && userRoleMappingTemp.size() > 0) 
			{
				return userRoleMappingTemp.get(0);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean checkUserExistBasedOnUserId(UserBean userBean) 
	{
		Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*)  "
				+ " AS count "
				+ "FROM user_details ud "
				+ "WHERE  participant_id = '"+userBean.getStrParticipantID()+"' "
				+ "AND ud.user_id = '"+userBean.getStrUserID()+"' ",
				new Object[] { }, (Integer.class));

		if (count > 0) 
		{
			return true;
		}
		return false;
	}


	@Override
	public int addUserDetails(UserBean userBean) 
	{
		try
		{
			int count = this.jdbcTemplate.update("INSERT INTO `user_details`(user_id,"
					+ "user_type_id,participant_id,user_access_type_id,user_full_name,"
					+ "user_name,mobile_number,email_id,secret_question_id,"
					+ "secret_question_answer,user_password,sensitive_pwd,"
					+ "user_status_id,sensitive_date,user_status,"
					+ "created_by,approval_status_id,plain_password) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] {userBean.getStrUserID(),3,
							userBean.getStrParticipantID(), userBean.getStrUserAccessType(),
							userBean.getUserFullName(),userBean.getStrUserName(),
							userBean.getStrMobileNo(),userBean.getStrEmailID(),
							userBean.getStrSecQueID(),userBean.getStrSecQueAns(),
							userBean.getStrPassword(),userBean.getStrPassword(),1,
							userBean.getStrSensitiveFlag(),1,
							userBean.getStrCreatedBy(),sessionDTO.getUserID(),userBean.getPlainPassword()});
			return count;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public UserBean getUserDetailOnUserName(UserBean userBean) 
	{
		return null;
	}

}
