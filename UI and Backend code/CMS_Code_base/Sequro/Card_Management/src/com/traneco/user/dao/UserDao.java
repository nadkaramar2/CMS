package com.traneco.user.dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.traneco.common.KeyValueBean;
import com.traneco.login.model.CustomUser;
import com.traneco.login.model.UserLoginLog;
import com.traneco.role.model.Cfg_Role;
import com.traneco.role.model.UserRoleMappingTemp;
import com.traneco.user.model.UserBean;

public interface UserDao 
{
    public String addUser(UserBean userBean);

    public String approvalPendingList(String userID, String approvalID, String remark, UserBean userBean);

    public int checkApprovalPendingCount(String userID);

    public String editUser(UserBean userBean) throws NoSuchAlgorithmException;

    public List<UserBean> getApprovalPendingList();

    public List<KeyValueBean> getRoleList();

    public List<KeyValueBean> getSecretQuestionList();

    public UserBean getUserDetails(String userID);
    
    public UserBean getUserProfile();

    public List<UserBean> getUserList();
    
    public UserBean getUserIdDetails(String userID);

    public List<KeyValueBean> getUserStatusList();
    
    public List<KeyValueBean> getGroupList(String partID);

	boolean checkUserExist(UserBean userBean);

	int addUserRoleMapping(UserBean userBean);

	public UserBean getUserDetailsBasesdOnMobileNoEmailId(UserBean userBean);

	int updateUserPassword(UserBean userBean);

	int insertPasswordResetOnUser(UserBean userBean);

	int storePlainPassword(String plainPassword);

	int addOldNewPassword(CustomUser user, String confNewEncPass);

	int insertAuditRecord(UserBean userBean, String tableName);

	public UserBean getOldUserDetails(UserBean userBean);

	public UserBean getOldUseRoleMappingDetails(UserBean userBean);

	public UserBean getOldUserDetailsTemp(UserBean userBean);

	public UserBean getOldUserRoleMappingTemp(UserBean userBean);

	public UserBean getOldUserDetailsBasedOnUsrNameEmailId(UserBean userBean);

	public UserBean getOldUserDetailsBasedOnUserId(UserBean userBean);

	public UserBean getOldUserDetailsBasedOnParticipantIdOrUserId(UserBean userBean);

	public UserBean getOldUserDetailsBasedOnUserNameUserId(UserBean userBean);

	public int updateUserPlainPassword(UserBean userBean);

	public int updateLogoutTime(UserLoginLog userLoginLog);

	public UserLoginLog getUserLoginData(UserLoginLog userLoginLog);

	public UserBean getUserNameBasedOnUserId(UserBean userBean);

	boolean checkUserExistinBothUserDetailandTemp(UserBean userBean);

	public UserBean getUserDetailsTemp();

	int addUserDetailsTemp(UserBean userBean);

	public Cfg_Role getLastCfgRoleRecord(Cfg_Role cfgRole);

	int addUserRoleMappingTemp(UserBean userBean);

	public UserRoleMappingTemp getUserRoleMappingTempLastInsert();

	public boolean checkUserExistBasedOnUserId(UserBean userBean);

	public int addUserDetails(UserBean userBean);

	public UserBean getUserDetailOnUserName(UserBean userBean);

}

