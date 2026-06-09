package com.traneco.user.services;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traneco.common.KeyValueBean;
import com.traneco.login.model.CustomUser;
import com.traneco.login.model.UserLoginLog;
import com.traneco.role.model.Cfg_Role;
import com.traneco.role.model.UserRoleMappingTemp;
import com.traneco.user.dao.UserDao;
import com.traneco.user.model.UserBean;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	UserDao userDaoImpl;

	@Override
	public String addUser(UserBean userBean) 
	{
		return userDaoImpl.addUser(userBean);
	}

	@Override
	public String approvalPendingList(String userID, String approvalID,
			String remark, UserBean userBean) {
		String status = userDaoImpl.approvalPendingList(userID, approvalID,
				remark,userBean);
		return status;
	}

	@Override
	public int checkApprovalPendingCount(String userID) 
	{
		return userDaoImpl.checkApprovalPendingCount(userID);
	}

	@Override
	public String editUser(UserBean userBean) throws NoSuchAlgorithmException {
		return userDaoImpl.editUser(userBean);
	}

	@Override
	public List<UserBean> getApprovalPendingList() {
		return userDaoImpl.getApprovalPendingList();
	}

	@Override
	public List<KeyValueBean> getRoleList() {
		return userDaoImpl.getRoleList();
	}

	@Override
	public List<KeyValueBean> getSecretQuestionList() {
		return userDaoImpl.getSecretQuestionList();
	}

	@Override
	public UserBean getUserDetails(String userID) {
		return userDaoImpl.getUserDetails(userID);
	}

	@Override
	public List<UserBean> getUserList() {
		return userDaoImpl.getUserList();
	}

	@Override
	public List<KeyValueBean> getUserStatusList() {
		return userDaoImpl.getUserStatusList();
	}

	@Override
	public UserBean getUserIdDetails(String userID) {
		return userDaoImpl.getUserIdDetails(userID);
	}

	@Override
	public UserBean getUserProfileDetails() {
		return userDaoImpl.getUserProfile();
	}

	@Override
	public List<KeyValueBean> getGroupList(String partID) {
		return userDaoImpl.getGroupList(partID);
	}

	@Override
	public boolean checkUserExist(UserBean userBean) 
	{
		return userDaoImpl.checkUserExist(userBean);
	}

	@Override
	public int addUserRoleMapping(UserBean userBean) 
	{
		return userDaoImpl.addUserRoleMapping(userBean);
	}

	@Override
	public UserBean getUserDetailsBasesdOnMobileNoEmailId(UserBean userBean) 
	{
		return userDaoImpl.getUserDetailsBasesdOnMobileNoEmailId(userBean);
	}

	@Override
	public int updateUserPassword(UserBean userBean) 
	{
		return userDaoImpl.updateUserPassword(userBean);
	}

	@Override
	public int insertPasswordResetOnUser(UserBean userBean) 
	{
		return userDaoImpl.insertPasswordResetOnUser(userBean);
	}

	@Override
	public int addOldNewPassword(CustomUser user, String confNewEncPass) {
		
		return userDaoImpl.addOldNewPassword(user,confNewEncPass);
	}

	@Override
	public int insertAuditRecord(UserBean userBean, String tableName) 
	{
		return userDaoImpl.insertAuditRecord(userBean,tableName);
	}

	@Override
	public UserBean getOldUserDetails(UserBean userBean) 
	{
		return userDaoImpl.getOldUserDetails(userBean);
	}

	@Override
	public UserBean getOldUseRoleMappingDetails(UserBean userBean) 
	{
		return userDaoImpl.getOldUseRoleMappingDetails(userBean);
	}

	@Override
	public UserBean getOldUserDetailsTemp(UserBean userBean) 
	{
		return userDaoImpl.getOldUserDetailsTemp(userBean);
	}

	@Override
	public UserBean getOldUserRoleMappingTemp(UserBean userBean) 
	{
		return userDaoImpl.getOldUserRoleMappingTemp(userBean);
	}

	@Override
	public UserBean getOldUserDetailsBasedOnUsrNameEmailId(UserBean userBean) 
	{
		return userDaoImpl.getOldUserDetailsBasedOnUsrNameEmailId(userBean);
	}

	@Override
	public UserBean getOldUserDetailsBasedOnUserId(UserBean userBean) 
	{
		return userDaoImpl.getOldUserDetailsBasedOnUserId(userBean);
	}

	@Override
	public UserBean getOldUserDetailsBasedOnParticipantIdOrUserId(UserBean userBean) 
	{
		return userDaoImpl.getOldUserDetailsBasedOnParticipantIdOrUserId(userBean);
	}

	@Override
	public UserBean getOldUserDetailsBasedOnUserNameUserId(UserBean userBean) 
	{
		return userDaoImpl.getOldUserDetailsBasedOnUserNameUserId(userBean);
	}

	@Override
	public int updateUserPlainPassword(UserBean userBean) 
	{
		return userDaoImpl.updateUserPlainPassword(userBean);
	}

	@Override
	public int updateLogoutTime(UserLoginLog userLoginLog) 
	{
		return userDaoImpl.updateLogoutTime(userLoginLog);
	}

	@Override
	public UserLoginLog getUserLoginData(UserLoginLog userLoginLog) 
	{
		return userDaoImpl.getUserLoginData(userLoginLog);
	}

	@Override
	public UserBean getUserNameBasedOnUserId(UserBean userBean) 
	{
		return userDaoImpl.getUserNameBasedOnUserId(userBean);
	}

	@Override
	public boolean checkUserExistinBothUserDetailandTemp(UserBean userBean) 
	{
		return userDaoImpl.checkUserExistinBothUserDetailandTemp(userBean);
	}

	@Override
	public UserBean getUserDetailsTemp() 
	{
		return userDaoImpl.getUserDetailsTemp();
	}

	@Override
	public int addUserDetailsTemp(UserBean userBean) 
	{
		
		return userDaoImpl.addUserDetailsTemp(userBean);
	}

	@Override
	public Cfg_Role getLastCfgRoleRecord(Cfg_Role cfgRole) 
	{
		return userDaoImpl.getLastCfgRoleRecord(cfgRole);
	}

	@Override
	public int addUserRoleMappingTemp(UserBean userBean) 
	{
		return userDaoImpl.addUserRoleMappingTemp(userBean);
	}

	@Override
	public UserRoleMappingTemp getUserRoleMappingTempLastInsert() 
	{
		return userDaoImpl.getUserRoleMappingTempLastInsert();
	}

	@Override
	public boolean checkUserExistBasedOnUserId(UserBean userBean) 
	{
		return userDaoImpl.checkUserExistBasedOnUserId(userBean);
	}

	@Override
	public int addUserDetails(UserBean userBean) 
	{
		return userDaoImpl.addUserDetails(userBean);
	}

	@Override
	public UserBean getUserDetailOnUserName(UserBean userBean) 
	{
		return userDaoImpl.getUserDetailOnUserName(userBean);
	}
}
