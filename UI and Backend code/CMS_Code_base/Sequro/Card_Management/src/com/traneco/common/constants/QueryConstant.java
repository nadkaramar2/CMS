package com.traneco.common.constants;

public class QueryConstant 
{
	public static final String LOAD_By_USER = "select u.user_id as userid,u.last_successful_logon as last_successful_logon,u.last_password_change_datetime as last_password_changed_date,u.login_failed_attemps_count as loginFailedAttemptsCount, u.user_name as username, u.participant_id as participantid, "
			+ " ci.participant_desc as participantdesc, u.mobile_number as mobileno, u.email_id as email, u.user_password as password, u.sensitive_date as sensitive_date ,rm.group_id as groupid,cr.role_name as role "
			+ " from user_details u inner join user_role_mapping rm on u.user_id=rm.user_id inner join `cfg_role` cr on rm.group_id = cr.group_id inner join cfg_interface ci on u.participant_id = ci.participant_id where u.user_name=? ";

	public static final String LOAD_ALL_ROLE = "select cr.role_name as rolename, mam.action_name as roleaction1, ccm.action_name as roleaction2 from cfg_menu_action_mapping mam inner join role_menu_mapping rmm on mam.menu_id=rmm.menu_id inner join "
			+ "cfg_cms_menu ccm on rmm.menu_id=ccm.id inner join cfg_role cr on cr.participant_id=rmm.participant_id ";

	public static final String LOAD_ROLE_PERMISSION = " select tbl.rolename, roleaction from  "
			+ "			(select rm.role_name as rolename,  ma.action_name as roleaction  "
			+ "			from `cfg_menu_action_mapping` ma "
			+ "			inner join `role_menu_mapping` rmapp on ma.menu_id=rmapp.menu_id  "
			+ "			inner join `cfg_role` as rm on rm.group_id = rmapp.group_id  " + "			union all "
			+ "			select rm.role_name as rolename,mm.action_name as roleaction1 from `cfg_cms_menu` as mm "
			+ "			inner join `role_menu_mapping` as rmm on rmm.menu_id = mm.id "
			+ "			inner join `cfg_role` as rm on rm.group_id = rmm.group_id   where rm.group_id = ? "
			+ "			)tbl  ";

	public static final String Load_MENU_LIST = " select distinct (mm.id) as menuid,mm.parent_submenu_id as parentsubmenuid,mm.menu_name as menuname, "
			+ " mm.action_name as menuaction,mm.menu_icon as menuicon from "
			+ " `role_menu_mapping` rm inner join `cfg_cms_menu` mm on rm.menu_id=mm.id inner join `user_role_mapping` ur on ur.role_id = rm.role_id "
			+ " where ur.group_id=? and rm.participant_id=? and mm.status=1 and mm.id <> 1 ";

	public static final String LOAD_SUBPARENT_MENU_LIST = "select sm.id as parentsubmenuid,sm.parentmenu_id as parentmenuid,sm.submenu_name as submenuname from "
			+ " `cfg_parent_submenu` sm where sm.id in ( "
			+ " select  mm.parent_submenu_id  from `role_menu_mapping` rm inner join `cfg_cms_menu` mm on rm.menu_id=mm.id inner join `user_role_mapping` ur on ur.role_id = rm.role_id "
			+ " where ur.group_id=? and rm.participant_id=? and mm.status=1) and sm.status=1 ";

	public static final String LOAD_PARENT_MENU_LIST = "select pm.id as parentmenuid,pm.parentmenu_name as parentmenuname,pm.menu_icon as menuicon from `cfg_parentmenu` pm "
			+ " where pm.id in (select sm.parentmenu_id from `cfg_parent_submenu` sm "
			+ " where sm.id in (select  mm.parent_submenu_id  from "
			+ " `role_menu_mapping` rm inner join `cfg_cms_menu` mm on rm.menu_id=mm.id inner join `user_role_mapping` ur on ur.role_id = rm.role_id "
			+ " where ur.group_id=? and rm.participant_id=? and mm.status=1) and sm.status=1)  and pm.status=1  ";

	/*
	 * INSTITUTION
	 */

	public static final String LOAD_INSTITUTION_MASTER = "select trim(participant_id) as lkpkey, concat(interface_type,' : ',participant_desc) as lkpvalue from `cfg_interface` ";

	public static final String SP_ADD_INSTITUTION = "USP_institution_add";

	public static final String LOAD_VIEW_INSTITUTION_MASTER = " select  "
			+ " im.institutionid as institutionid, im.institutioncode as institutioncode, "
			+ " im.institutiondesc as institutiondesc, im.outwarddayamtlimit as outwarddayamtlimit, "
			+ " im.outwardmonthamtlimit as outwardmonthamtlimit, im.outwardminamttran as outwardminamttran, "
			+ " im.outwardmaxamttran as outwardmaxamttran, im.outwarddaymaxtrancount as outwarddaymaxtrancount, "
			+ " im.outwardmonthmaxtrancount as outwardmonthmaxtrancount, um.username as createdby, "
			+ " im.createddate as createddate, ifnull(im.lastmodifiedby, 'na') as lastmodifiedby, "
			+ " ifnull(im.lastmodifieddate, 'na') as lastmodifieddate " + " from " + " imps_inst_mast im "
			+ " left join imps_user_mast um " + " on im.createdby = um.userid where im.institutionid=? ";

	public static final String EDIT_INSTITUTION_BY_ID = " select  "
			+ " im.institutionid as institutionid, im.institutioncode as institutioncode, "
			+ " im.institutiondesc as institutiondesc, im.outwarddayamtlimit as outwarddayamtlimit,  "
			+ " im.outwardmonthamtlimit as outwardmonthamtlimit, im.outwardminamttran as outwardminamttran, "
			+ " im.outwardmaxamttran as outwardmaxamttran, im.outwarddaymaxtrancount as outwarddaymaxtrancount, "
			+ " im.outwardmonthmaxtrancount as outwardmonthmaxtrancount, um.username as createdby, "
			+ " im.createddate as createddate, ifnull(im.lastmodifiedby, 'na') as lastmodifiedby, "
			+ " ifnull(im.lastmodifieddate, 'na') as lastmodifieddate  " + " from " + " imps_inst_mast im "
			+ " left join imps_user_mast um " + " on im.createdby = um.userid " + " where im.institutionid = ?";

	public static final String SP_EDIT_INSTITUTION = "usp_institution_edit";

	public static final String LOAD_APPROVAL_PENDING_LIST_INSTITUTION = " select "
			+ " im.institutionid as institutionid, im.institutioncode as institutioncode, "
			+ " im.institutiondesc as institutiondesc, im.outwarddayamtlimit as outwarddayamtlimit, "
			+ " im.outwardmonthamtlimit as outwardmonthamtlimit, im.outwardminamttran as outwardminamttran, "
			+ " im.outwardmaxamttran as outwardmaxamttran, im.outwarddaymaxtrancount as outwarddaymaxtrancount, "
			+ " im.outwardmonthmaxtrancount as outwardmonthmaxtrancount, ifnull (um.username,'superadmin') as createdby, "
			+ " im.createddate as createddate, ifnull(im.lastmodifiedby, 'superadmin') as lastmodifiedby, "
			+ " ifnull(im.lastmodifieddate, 'na') as lastmodifieddate,if(im.approvalstatusid=3,'pending','') as approvalstatus, "
			+ " concat(im.institutionid,'|',im.institutiontempid,'|',im.outwarddayamtlimit,'|',im.outwardmonthamtlimit,'|', "
			+ " im.outwardminamttran,'|',im.outwardmaxamttran,'|',im.outwarddaymaxtrancount,'|',im.outwardmonthmaxtrancount "
			+ " ,'|',im.createdby) as requestid  from   imps_inst_mast_temp im "
			+ " left join imps_user_mast um  on im.createdby = um.userid "
			+ " where  im.approvalstatusid=3 and im.createdby!=? and im.institutionid=? ";

	public static final String SP_APPROVE_INSTITUTION = "usp_institution_edit";

	public static final String CHECK_APPROVAL_PENDING_STATUS = " select count(*) from imps_inst_mast_temp  where institutionid=? and approvalstatusid=3 ";
	/*
	 * END INSTITUTION
	 */

	/*
	 * User Management
	 */

	public static final String GET_SECRET_QUESTION_LIST = "select id as lkpkey,description as lkpvalue from cfg_secret_question ";

	public static final String CHECK_APPROVAL_PENDING_STATUS_USER = " select count(*) from `user_details_temp` where user_id=? and approval_status_id=3";

	public static final String GET_ROLE_LIST = " select id as lkpkey,role_name as lkpvalue from cfg_role where participant_id=? ";

	public static final String SP_USER_MANAGEMENT = "USP_USER_MANAGEMENT";

	public static final String LOAD_APPROVAL_PENDING_LIST_USER = " select distinct um.user_id as struserid, um.id as strusertempid,  um.participant_id as strparticipantid, um.user_full_name as strfirstname, "
			+ " um.user_name as strusername, um.mobile_number as strmobileno, um.email_id as stremailid, "
			+ " case when um.sensitive_date=0  then 'no' else 'yes' end  as strsensitiveflag, rm.role_name as strroleid , "
			+ " case when  us.user_name is null then 'superadmin' else us.user_name end as strcreatedby, ur.group_id as strgroupid from `user_details_temp` um "
			+ " left join `user_role_mapping_temp` ur on ur.user_id= um.user_id and um.participant_id = ur.participant_id  "
			+ " left join `cfg_role` rm on um.participant_id = rm.participant_id and ur.group_id=rm.group_id left join `user_details` us "
			+ " on um.created_by=us.user_id where um.user_type_id = 3 and um.participant_id = ? and  "
			+ " um.approval_status_id = 3 and um.created_by <> ? and ur.approval_status_id=3 ";

	public static final String GET_USER_LIST = " select um.user_id as struserid, um.participant_id as strparticipantid, split_str(um.user_full_name, ' ', 1) as strfirstname, "
			+ " split_str(um.user_full_name, ' ', 2) as strlastname, um.user_name as strusername, um.mobile_number as strmobileno, um.email_id as stremailid, "
			+ " case when um.sensitive_date=0  then 'no' else 'yes' end  as strsensitiveflag, rm.role_name as strroleid , "
			+ " us.user_name as strcreatedby from `user_details` um "
			+ " left join `user_role_mapping` ur on ur.user_id= um.user_id and um.participant_id = ur.participant_id "
			+ " left join `cfg_role` rm on um.participant_id = rm.participant_id and ur.role_id=rm.id left join `user_details` us "
			+ " on um.created_by=us.user_id where um.user_type_id = 3 and um.participant_id = ?";

	public static final String GET_USER_DETAILS_BY_USERID = "select um.user_id as struserid, um.participant_id as strparticipantid, split_str(um.user_full_name, ' ', 1) as strfirstname, "
			+ " split_str(um.user_full_name, ' ', 2) as strlastname, um.user_name as strusername, um.mobile_number as strmobileno, um.email_id as stremailid, "
			+ " case when um.sensitive_date=0  then 'n' else 'y' end  as strsensitiveflag, rm.id as strroleid , "
			+ " um.secret_question_id as strsecqueid, um.secret_question_answer as strsecqueans, "
			+ " case when um.user_access_type_id=1 then 'db' else 'ldap' end as struseraccesstype, "
			+ " us.user_name as strcreatedby, us.user_status_id as struserstatusid from `user_details` um "
			+ " left join `user_role_mapping` ur on ur.user_id= um.user_id and um.participant_id = ur.participant_id "
			+ " left join `cfg_role` rm on ur.role_id=rm.id and um.participant_id = rm.participant_id "
			+ " left join `user_details` us on um.created_by=us.user_id "
			+ " where um.user_type_id=3 and um.participant_id=? and um.user_id=? ";

	public static final String GET_USER_STATUS = "select userstatusid as lkpkey, userstatus as lkpvalue from imps_usrstat_mast";

	/*
	 * END User Management
	 */

	/*
	 * Channel Management
	 */

	public static final String SP_ADD_CHANNEL = "usp_channel_add";

	public static final String SP_EDIT_CHANNEL = "usp_channel_add";

	public static final String SP_APPROVE_CHANNEL = "usp_channel_add";

	public static final String CHANNEL_TYPE = "select channeltype_id as lkpkey,channeltype_desc as lkpvalue from imps_chnnl_type";

	public static final String TXNS_TYPE = "select type_id as lkpkey ,type_desc as lkpvalue from imps_txns_type";

	public static final String EDIT_CHANNEL = "select cm.channelid as channelid,cm.channelname as channelname, cm.channeltypeid as channeltype,cm.channelpassword as channelpassword, cm.channelsmsflag as channelsmsflag, "
			+ "cm.outwarddayamtlimit as outwarddayamtlimit,cm.outwardmonthamtlimit as outwardmonthamtlimit, cm.outwardminamttran as outwardminamttran,cm.outwardmaxamttran as outwardmaxamttran, "
			+ "cm.outwarddaymaxtrancount as outwarddaymaxtrancount,cm.outwardmonthmaxtrancount as outwardmonthmaxtrancount from imps_chnnl_mast "
			+ " cm where institutionid=?";

	public static final String EDIT_CHANNEL_DETAILS = "select cm.channelid as channelid, cm.channelname as channelname, group_concat(tx.type_id) as txnstype , "
			+ "  cm.channeltypeid as channeltype, cm.channelpassword as channelpassword, cm.channelsmsflag as channelsmsflag, "
			+ "  cm.outwarddayamtlimit as outwarddayamtlimit, cm.outwardmonthamtlimit as outwardmonthamtlimit, "
			+ "  cm.outwardminamttran as outwardminamttran, cm.outwardmaxamttran as outwardmaxamttran, "
			+ "  cm.outwarddaymaxtrancount as outwarddaymaxtrancount, cm.outwardmonthmaxtrancount as outwardmonthmaxtrancount \r\n"
			+ "  from imps_chnnl_mast cm,imps_txns_mast tx  where tx.channel_id = cm.channelid  and channelid=? "
			+ "  and cm.institutionid=? group by tx.channel_id ";

	public static final String DUPLICATE_EDIT_CHANNEL_CHECK = "select count(*) from imps_chnnl_mast_temp where  channelid=? and institutionid=? and approvalstatusid = 3";

	public static final String VIEW_CHANNEL = "select cm.channelid as channelid,cm.channelname as channelname, cm.channelpassword as channelpassword, cm.channelsmsflag as channelsmsflag, "
			+ "cm.outwarddayamtlimit as outwarddayamtlimit,cm.outwardmonthamtlimit as outwardmonthamtlimit, cm.outwardminamttran as outwardminamttran,cm.outwardmaxamttran as outwardmaxamttran, "
			+ "cm.outwarddaymaxtrancount as outwarddaymaxtrancount,cm.outwardmonthmaxtrancount as outwardmonthmaxtrancount from imps_chnnl_mast cm where institutionid=?";

	public static final String APPROVE_CHANNEL = "select cm.channelid as channelid,cm.channelname as channelname, cm.channelpassword as channelpassword, cm.channelsmsflag as channelsmsflag, "
			+ "cm.outwarddayamtlimit as outwarddayamtlimit,cm.outwardmonthamtlimit as outwardmonthamtlimit, cm.outwardminamttran as outwardminamttran,cm.outwardmaxamttran as outwardmaxamttran, "
			+ "cm.outwarddaymaxtrancount as outwarddaymaxtrancount,cm.outwardmonthmaxtrancount as outwardmonthmaxtrancount,cm.approvalstatusid as approvalstatusid from imps_chnnl_mast_temp cm";

	public static final String LOAD_APPROVAL_PENDING = "select cm.channeltempid as channeltempid,cm.channelid as channelid,cm.channelname as channelname, cm.channelpassword as channelpassword, cm.channelsmsflag as channelsmsflag,"
			+ "cm.outwarddayamtlimit as outwarddayamtlimit,cm.outwardmonthamtlimit as outwardmonthamtlimit, cm.outwardminamttran as outwardminamttran,cm.outwardmaxamttran as outwardmaxamttran,cm.outwarddaymaxtrancount as outwarddaymaxtrancount,"
			+ "cm.outwardmonthmaxtrancount as outwardmonthmaxtrancount,cm.approvalstatusid as approvalstatusid,cm.createdby as createdby,if(cm.approvalstatusid=3,'pending','') as approvalstatus,"
			+ "concat (cm.channeltempid,'|',cm.channelid,'|',cm.channelname,'|',cm.channelpassword,'|',cm.channelpasswordhash,'|',cm.channelsmsflag,'|',cm.outwarddayamtlimit,'|',cm.outwardmonthamtlimit,'|',cm.outwardminamttran,'|',"
			+ "cm.outwardmaxamttran,'|',cm.outwarddaymaxtrancount,'|',cm.outwardmonthmaxtrancount,'|',cm.createdby)  as requestid from imps_chnnl_mast_temp cm where approvalstatusid =3 and createdby!=? ";
	/*
	 * END Channel Management
	 */

	/* Start All Queries For Role */

	public static final String LOAD_ALL_PARENT_MENU = " select parentmenuid as parentmenuid, parentmenuname as parentmenuname from imps_parent_menu_mast where institutionid = ? and isactive=1 and isdeleted = 0 ";

	public static final String LOAD_ALL_PARENT_SUBMENU_LIST = " select parentsubmenuid as parentsubmenuid, parentmenuid as parentmenuid, submenuname as submenuname from imps_parent_submenu_mast where institutionid = ? and isactive=1 and isdeleted = 0 ";

	public static final String LOAD_ALL_MENU = " select menuid as menuid, parentmenuid as parentmenuid, parentsubmenuid as parentsubmenuid, menuname as menuname from imps_menu_mast where institutionid = ? and isactive=1 and isdeleted = 0 ";

	public static final String ROLE_ACTION_SP = "usp_role_add";

	public static final String SEARCH_ROLES_LIST = " select distinct  case when r.isactive=1 then 'true' else 'false ' end as active , "
			+ " r.rolename, r.roleid, r.description, " + " r.createddate, "
			+ " case when r.lastmodifiedby is null then 'new' else 'modified' end as newormodified, "
			+ " u.firstname + ' ' + u.lastname as createdby from imps_role_mast r "
			+ " inner join imps_user_mast u on u.userid = r.createdby where rolename like ? "
			+ " and r.institutionid = ? and u.institutionid = ? ";

	public static final String LOAD_ROLE_FOR_EDIT = "select  case when r.isactive = 1  then 'true'  else 'false '  end as active, "
			+ "  r.isactive, r.rolename, r.roleid, r.description, r.createdby, "
			+ "  r.createddate as createdate, m.menuid, m.menuname, pm.parentmenuid, pm.parentmenuname, psm.parentsubmenuid, psm.submenuname, "
			+ "  case when r.lastmodifiedby is null  then 'new' else 'modified' end as newormodified  "
			+ "from imps_role_mast r  " + "  inner join imps_role_menu_mapping rmm  " + "    on rmm.roleid = r.roleid  "
			+ "  inner join imps_user_mast u  " + "    on u.userid = r.createdby  " + "  inner join imps_menu_mast m  "
			+ "    on m.menuid = rmm.menuid  " + "  inner join imps_parent_submenu_mast psm  "
			+ "    on psm.parentmenuid = m.parentmenuid " + "  inner join imps_parent_menu_mast pm "
			+ "  on  pm.parentmenuid = pm.parentmenuid " + "where r.rolename = ? " + "and r.institutionid = ? "
			+ "and m.parentmenuid = psm.parentmenuid " + "and m.parentmenuid = pm.parentmenuid "
			+ "and m.parentsubmenuid = psm.parentsubmenuid ";

	public static final String LOAD_PENDING_ROLE_FOR_APPROVAL = "select  rt.roletempid, rt.roleid, rt.rolename, rt.description, rt.isactive, rt.isdeleted, rt.createdby, rt.createddate, "
			+ "  rt.lastmodifiedby, rt.lastmodifieddate, rt.approvedby, rt.approveddate, rt.remark, "
			+ "  rt.approvalstatusid, u.firstname + ' ' + u.lastname as 'creadtedbyuser', "
			+ "  case when rm.roleid is null  then 'new'  else 'modified' end as 'newormodified' "
			+ "from imps_role_mast_temp rt " + "  inner join imps_user_mast u " + "    on u.userid = rt.createdby "
			+ "  left outer join imps_role_mast rm " + "    on rm.roleid = rt.roleid "
			+ "where rt.approvalstatusid = 3 " + "  and rt.approvedby is null " +
			// uncomment before deployment " and rt.createdby <> ? "+
			"  and rt.institutionid = ? ";
	/* End All Queries For Role */

	/* End All Queries For Report */
	public static final String InsertAccessTrail = "INSERT INTO imps_usraccess_trail (MenuId,AccessedBy,AccessedByInstId,AccessedOn,IPAddress,RoleId) VALUES(?,?,?,?,?,?)";
	public static final String FetchAccessTrailReport = "SELECT m.MenuName AS menuID, b.UserName AS userID, i.InstitutionDesc AS institutionId, AccessedOn AS createdDate, "
			+ " IPAddress AS ipAddress, r.RoleName AS roleID, b.userName AS userName FROM imps_usraccess_trail a "
			+ " INNER JOIN IMPS_USER_MAST b ON a.AccessedBy = b.UserID INNER JOIN IMPS_MENU_MAST m ON m.MenuID = a.MenuId "
			+ " INNER JOIN IMPS_INST_MAST i ON i.InstitutionId = a.AccessedByInstId INNER JOIN IMPS_ROLE_MAST r "
			+ " ON r.RoleID = a.RoleId WHERE b.userName=? AND a.AccessedByInstId=? AND SUBSTRING(A.ACCESSEDON, 1, 10) BETWEEN ? "
			+ " AND ? ORDER BY a.AccessedOn ASC ";
	public static final String FetchUserList = "select username as username from imps_user_mast where institutionid=? ";
	/* End All Queries For Report */

	public static final String LOAD_MENUID = "select tbl.rolename, roleaction as menuaction ,midd as menuid from "
			+ "(select rm.role_name as rolename, ma.action_name as roleaction ,ma.menu_id as midd "
			+ "from `cfg_menu_action_mapping` ma " + "inner join `role_menu_mapping` rmapp on ma.menu_id=rmapp.menu_id "
			+ "inner join `cfg_role` as rm on rm.id = rmapp.role_id " + "union all "
			+ "select rm.role_name as rolename,mm.action_name as roleaction1, mm.id as midd from `cfg_cms_menu` as mm  "
			+ "inner join `role_menu_mapping` as rmm on rmm.menu_id = mm.id "
			+ "inner join `cfg_role` as rm on rm.id = rmm.role_id  " + "where rm.id = ? and rm.participant_id = ?)tbl ";

	//public static final String GET_USERLOGIN_ATTEMPT = "select case when login_failed_attemps_count is null then '0' else login_failed_attemps_count end as login_failed_attemps_count  from user_details where user_id=? ";
	public static final String GET_USERLOGIN_ATTEMPT = "select login_failed_attemps_count from user_details where user_id=? ";
	
	public static final String UPDATE_USERLOGIN_ATTEMPT = "UPDATE `user_details` SET login_failed_attemps_count=? WHERE User_ID=? ";
	// public static final String UPDATE_USERLOGIN="UPDATE IMPS_USER_MAST SET
	// LastSucessfullLoginOn=? , LOGINFLAG=? WHERE UserID=?";
	public static final String UPDATE_USERLOGIN = "INSERT INTO `user_login_log` (participant_id,user_id,username,login_datetime,client_ip,is_successful) VALUES(?,?,?,?,?,?) ";
	public static final String UPDATE_SUCESS_USERLOGIN = " UPDATE `user_details` SET last_successful_logon=? WHERE participant_id=? AND user_id=? ";
	public static final String FetchLoginAttemptReport = "select a.username as username, logindatetime as  createddate,logoutdatetime as lastmodifieddate ,case when issucessful='1' then 'successfull' else 'failed' end as type from imps_user_mast a inner join imps_usrlogin_log b on a.userid=b.userid where a.username=? and b.institutionid=? and issucessful=? and substring(logindatetime, 1,10) between ?  and ?  order by logindatetime asc";

	public static final String GET_USER_DETAILS_BY_USERID_API = "select um.userID AS userid, um.institutionid AS instID, um.FirstName AS firstName, "
			+ " um.LastName AS lastName, um.UserName AS userName, um.mobileNumber AS mobileNo, um.EmailID AS emailID, "
			+ " CASE WHEN um.SensitiveData=0  THEN 'N' ELSE 'Y' END  AS sensitiveFlag, rm.roleid AS roleID , "
			+ " um.secretquestionid AS secQueID, um.secretquestionanswer AS secQueAns, "
			+ " CASE WHEN um.useraccesstypeid=1 THEN 'DB' ELSE 'Ldap' END AS userAccessType, "
			+ " us.UserName AS createdBy, us.userstatusid as userStatusID FROM IMPS_USER_MAST_TEMP um "
			+ " LEFT JOIN IMPS_USRROLE_MAPPING ur ON UR.UserID= um.UserID AND um.InstitutionId = ur.InstitutionId "
			+ " LEFT JOIN IMPS_ROLE_MAST rm ON ur.RoleId=rm.roleID AND um.InstitutionId = rm.InstitutionId  "
			+ " LEFT JOIN IMPS_USER_MAST us ON um.CreatedBy=us.UserID  "
			+ " WHERE um.usertypeid=3 AND um.institutionid=? AND um.userid=? ";

	public static final String GET_CARD_DETAILS = "SELECT token_card AS strCardNumber, card_number AS strMaskCardNumber,member_number AS strCardSeqNumber,online_atm_limit AS strOnlineATMLimit ,online_pos_limit AS strOnlinePOSLimit,online_ecom_limit AS strOnlineECOMLimit,offline_limit AS strOfflineLimit,monthly_limit AS strMonthlyLimit "
			+ ",weekly_limit AS strWeeklyLimit,daily_limit AS strDailyLimit FROM card_details WHERE token_card = ? AND participant_id=?";

	public static final String Get_Cfg_Status = "SELECT id AS lkpkey,status_description AS lkpvalue FROM cfg_status ";

	public static final String GET_NCMC_SERVICE = "SELECT ncmc_service_id AS lkpkey FROM cfg_ncmc_service WHERE participant_id=? and card_type = (SELECT `card_type` FROM  card_details WHERE token_card =?)";

	public static final String GET_NCMC_LIST_TYPE = "SELECT `ncmc_service_id` AS lkpkey FROM cfg_ncmc_service WHERE card_type=? AND participant_id=?";

	/*
	 * Configuration Query
	 */

	public static final String GET_CARD_NO = " select clear_card(card_details.enc_card) as card, card_token.`token_details` as token from `card_details` left join `card_token` on card_details.id = card_token.`card_id` where token_card=? or enc_card= CARD_TOKEN(?) ";

	public static final String GET_NETWORK_SCHEME = " select network_scheme from `cfg_bin` where `bin`=? or `bin` = (select `bin` from `cfg_card_type` where id=? and participant_id=?) and  participant_id=? ";

	public static final String GET_BIN_FLAG = " select flag from `cfg_bin` where `bin`=? or `bin`= "
			+ " (select `bin` from `cfg_card_type` where id=? and participant_id=?)  and participant_id=? ";

	public static final String INSERT_CFG_BIN = "insert into cfg_bin (`bin`,description,participant_id,network_scheme,flag) values (?,?,?,?,?)";

	public static final String GET_CFG_BIN = "select `bin` as strbin,description as strbindesc, id as strid, network_scheme as strnetworkscheme, flag as flag from cfg_bin where participant_id = ?";

	public static final String GET_CFG_COUNTRY = "select id as strid, country_name as strcountryname, shortname as strshortname "
			+ "from `cfg_country`  ";

	public static final String INSERT_CFG_COUNTRY = "insert into cfg_country (country_name,`status`,participant_id) values (?,?,?)";

	public static final String GET_CFG_STATE = " select cs.id as strid, cc.id as strcountryid, cs.state_name as strstatename from `cfg_state` cs left join `cfg_country` cc on cs.country_id = cc.id where cs.country_id = ?  ";

	public static final String GET_CFG_EMBOSS = " select id as strid,vender_name as strservicename,`format` as strformat from cfg_emboss_fmt where participant_id=? ";

	public static final String GET_CFG_EMBOSS_NAME = " select id as strid,vender_name as strservicename from cfg_emboss_fmt where participant_id=? ";

	public static final String GET_CFG_NCMC_CODE = " select ncmc_code as strncmcid from `cfg_ncmc_code` where participant_id=? ";

	public static final String GET_CFG_CITY = " select cs.id as strid, cc.id as strstateid, cs.city_name as strcityname from `cfg_city` cs left join `cfg_state` cc on cs.state_id = cc.id where cs.state_id = ?  ";

	public static final String INSERT_CFG_STATE = "insert into cfg_state (`country_id`,`state_name`,`status`,`participant_id`) values (?,?,?,?)";

	public static final String INSERT_CFG_BRANCH = "insert into `cfg_branch` (`branch_name`,`branch_desc`,`participant_id`) values (?,?,?)";

	public static final String GET_CFG_BRANCH = "select `id` as strid,`branch_name` as strbranchname,`branch_desc` as strbranchdesc from cfg_branch where `participant_id`=?";

	public static final String GET_CFG_BRANCH_CODE = " select id as strid,`branch_code` as strbranchcode,`branch_address` as strbranchaddress,`branch_type` as strbranchtype,`ext_id` as strextid from `cfg_branch_code` "
			+ " where `participant_id`=?";

	public static final String INSERT_CFG_BRANCH_CODE = "insert into cfg_branch_code (`branch_code`,`branch_address`,`branch_type`,`ext_id`,`participant_id`) values(?,?,?,?,?)";

	public static final String GET_CFG_ACCOUNT = " SELECT id AS strID,`Account_description` AS strAccountDesc,`participant_id` AS strPart,`ext_id` AS strExt FROM `cfg_account_type` WHERE `participant_id`=?";

	public static final String INSERT_CFG_ACCOUNT = " INSERT INTO `cfg_account_type` (`Account_description`,`participant_id`,`ext_id`) VALUES (?,?,?)";

	public static final String GET_CFG_ADDRESS = " SELECT id AS strID,`address_description` AS strAddressDesc,`participant_id` AS strPart,`ext_id` AS strExt FROM `cfg_address_type` WHERE `participant_id`=? ";

	public static final String GET_CFG_EMAIL = " SELECT id AS strID,`email_description` AS strEmail,`participant_id` AS strPart,`ext_id` AS strExt FROM `cfg_email_type` WHERE `participant_id`=? ";

	public static final String GET_CFG_PHONE = " SELECT id AS strID,`phone_description` AS strPhone,`participant_id` AS strPart,`ext_id` AS strExt FROM `cfg_phone_type` WHERE `participant_id`=? ";

	public static final String GET_CFG_NCMC = " SELECT id AS strID,`ncmc_code` AS strNcmcID,`participant_id` AS strPart, `active` AS strFlag FROM `cfg_ncmc_code` WHERE `participant_id`=? ";

	public static final String GET_CFG_KEY = "SELECT id AS strID,`cvk_key` AS strCVK,`pvk_key` AS strPVK,`mdk_key` AS strMDK,`security_key_id` AS strSecKeyID,`participant_id` AS strPartID FROM `cfg_keys` WHERE participant_id=?";

	public static final String INSERT_CFG_KEY = " INSERT INTO cfg_keys (`cvk_key`,`pvk_key`,`mdk_key`,`security_key_id`,`participant_id`) VALUES (CARD_TOKEN(?),CARD_TOKEN(?),CARD_TOKEN(?),CARD_TOKEN(?),?)";

	public static final String INSERT_CFG_NCMC = " INSERT INTO `cfg_ncmc_code` (`ncmc_code`,`participant_id`,`active`) VALUES (?,?,?) ";

	public static final String INSERT_CFG_ADDRESS = " INSERT INTO `cfg_address_type` (`address_description`,`participant_id`,`ext_id`) VALUES (?,?,?) ";

	public static final String INSERT_CFG_EMAIL = " INSERT INTO `cfg_email_type` (`email_description`,`participant_id`,`ext_id`) VALUES (?,?,?) ";

	public static final String INSERT_CFG_PHONE = " INSERT INTO `cfg_phone_type` (`phone_description`,`participant_id`,`ext_id`) VALUES (?,?,?) ";

	public static final String GET_CFG_CARD_TYPE = " SELECT cct.`card_type` AS strCardType, cct.`description` AS strCardDesc, cct.`participant_id` AS strPartID  "
			+ " , cct.`bin` AS strBin, cct.`bin_suffix` AS strBinSuffix, cct.`cvn_method_id` AS strCVN, ce.`endpoint` AS strEndpoint, "
			+ " cct.`pinmailer_format` AS strPinFormat, cct.`decimalization_table` AS strDecTable, cct.`id` AS strID, cct.ncmc_flag AS strFlag, cct.card_gen_type as strCardGenerationType FROM `cfg_card_type` cct LEFT JOIN `cfg_endpoint` ce ON cct.`endpoint`=ce.`id` WHERE `participant_id`=? ";
	
	public static final String GET_CFG_CARD_TYPE_NCMC = " SELECT `card_type` AS strCardType, `description` AS strCardDesc, `participant_id` AS strPartID "
			+ " , `bin` AS strBin, `bin_suffix` AS strBinSuffix, `cvn_method_id` AS strCVN, "
			+ " `pinmailer_format` AS strPinFormat, `decimalization_table` AS strDecTable, `id` AS strID, ncmc_flag AS strFlag FROM `cfg_card_type` WHERE `participant_id`=? and ncmc_flag=2";

	public static final String INSERT_NCMC_SERVICE = "INSERT INTO `cfg_ncmc_service` (participant_id,ncmc_service_id,card_type) VALUES (?,?,?)";

	public static final String DELETE_NCMC_SERVICE = "DELETE FROM cfg_ncmc_service WHERE card_type=? AND participant_id=?";

	public static final String INSERT_CFG_CARD_TYPE = " INSERT INTO cfg_card_type (`card_type`,`description`,`bin`,`bin_suffix`,`cvn_method_id`,`pinmailer_format`,`decimalization_table`,`participant_id`,`ncmc_flag`,`endpoint`,`card_gen_type`,`card_token`) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ";

	public static final String GET_CFG_CARD_TEMPLATE = " SELECT cct.id AS strID, cct.card_type_id AS strCardType, CONCAT(ct.`card_type`,' : ',ct.`description`) AS typeDesc, cct.service_code AS strServiceCode, "
			+ " cct.card_issue_type_id AS strCardIssue, cct.card_mailer_Issue_flag AS strCardMailerFlag, "
			+ " cct.pin_mailer_Issue_flag AS strPinMailerFlag, cct.temporary_limit_activation_flag AS strTempLimitFlag, "
			+ " cct.daily_pin_retry_limit AS strDailyPinLimit, cct.consecutive_pin_retry_limit AS strConPinLimit, "
			+ " cct.online_atm_limit AS strOnlineAtmLimit, cct.online_pos_limit AS strOnlinePosLimit, "
			+ " cct.online_ecom_limit AS strOnlineEcomLimit, cct.offline_limit AS strOfflineLimit, "
			+ " cct.monthly_limit AS strMonthlyLimit, cct.weekly_limit AS strWeeklyLimit,cct.daily_limit AS strDailyLimit,cct.expiry_cfg_type as strExpiryType, case when cct.expiry_cfg_type = 'M' then cct.expiry_month else cct.expiry_year end as strExpireValue "
			+ " FROM cfg_card_template cct INNER JOIN `cfg_card_type` ct ON cct.`card_type_id`=ct.`id` WHERE cct.participant_id=?";

	public static final String INSERT_CFG_CARD_TEMPLATE = "INSERT INTO cfg_card_template (card_type_id , service_code , "
			+ " card_issue_type_id , card_mailer_Issue_flag , "
			+ " pin_mailer_Issue_flag , temporary_limit_activation_flag , "
			+ " daily_pin_retry_limit , consecutive_pin_retry_limit , " + " online_atm_limit , online_pos_limit , "
			+ " online_ecom_limit , offline_limit , "
			+ " monthly_limit , weekly_limit ,daily_limit, participant_id,expiry_cfg_type,expiry_month,expiry_year) "
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

	public static final String GET_CFG_CARD_PLASTIC = "  SELECT ccp.`id` AS strID , ccp.`card_type_id` AS strCardType, CONCAT(ct.`card_type`,' : ',ct.`description`) AS typeDesc, ccp.`vendor_id` AS strVendorID, ccp.`CVV_position` AS strCVV, "
			+ " ccp.`servicecode_position` AS strServicePos, ccp.`expiry_date_position` AS strExpPos, ccp.`card_seq_position` AS strSeqPos "
			+ " FROM `cfg_card_plastic` ccp INNER JOIN `cfg_card_type` ct ON ccp.`card_type_id`=ct.`id` WHERE ccp.`participant_id`=?  ";

	public static final String INSERT_CFG_CARD_PLASTIC = " INSERT INTO cfg_card_plastic (`participant_id`,`card_type_id`,`vendor_id`,`CVV_position`,`servicecode_position`,`expiry_date_position`, "
			+ " `card_seq_position`) VALUES (?,?,?,?,?,?,?) ";

	public static final String GET_USER_SENPWD = "SELECT COUNT(*) FROM `user_details` WHERE `user_id`=? AND `sensitive_pwd`=?";

	public static final String GET_CARD_LIST = " SELECT CONCAT(token_card,\"|\",member_number,\"|\",card_type) AS lkpkey,  card_number AS lkpvalue FROM  card_details WHERE participant_id=? and customer_id=? ";

	public static final String DELETE_BIN_BY_ID = " DELETE FROM `cfg_bin` WHERE id=? ";

	public static final String DELETE_BRANCH_BY_ID = " DELETE FROM `cfg_branch` WHERE id=? ";

	public static final String ADD_EMBOSS = "INSERT INTO cfg_emboss_fmt (vender_name,`format`,participant_id) VALUES (?,?,?)";

	public static final String DELETE_BRANCH_CODE_BY_ID = " DELETE FROM `cfg_branch_code` WHERE id=? ";

	public static final String DELETE_ACCOUNT_TYPE_BY_ID = " DELETE FROM `cfg_account_type` WHERE id=? ";

	public static final String DELETE_ADDRESS_TYPE_BY_ID = " DELETE FROM `cfg_address_type` WHERE id=? ";

	public static final String DELETE_EMAIL_TYPE_BY_ID = " DELETE FROM `cfg_email_type` WHERE id=? ";

	public static final String DELETE_KEY_BY_ID = " DELETE FROM `cfg_keys` WHERE id=? ";

	public static final String DELETE_NCMC_TYPE_BY_ID = " DELETE FROM `cfg_ncmc_code` WHERE id=? ";

	public static final String DELETE_CARD_TYPE_BY_ID = " DELETE FROM `cfg_card_type` WHERE id=? ";

	public static final String DELETE_CARD_TEMP_BY_ID = " DELETE FROM `cfg_card_template` WHERE id=? ";

	public static final String DELETE_CARD_PLASTIC_BY_ID = " DELETE FROM `cfg_card_plastic` WHERE id=? ";

	public static final String UPDATE_NCMC_TYPE_BY_ID = " UPDATE cfg_ncmc_code SET `active`=?  WHERE id=? and ncmc_code=?";

	public static final String UPDATE_CARD_PLASTIC_BY_ID = " UPDATE cfg_card_plastic SET `vendor_id`=? ,`CVV_position`=?,`servicecode_position`=?, `expiry_date_position`=?, `card_seq_position`=? WHERE id=?";

	public static final String UPDATE_CARD_TEMP_BY_ID = " UPDATE cfg_card_template SET `service_code`=? ,`card_mailer_Issue_flag`=?,`pin_mailer_Issue_flag`=?, `temporary_limit_activation_flag`=?, `daily_pin_retry_limit`=?, `consecutive_pin_retry_limit`=?,`online_atm_limit`=?,`online_pos_limit`=?,`online_ecom_limit`=?,`offline_limit`=?,`monthly_limit`=?,`weekly_limit`=?,`daily_limit`=?,expiry_cfg_type=?,expiry_month=?,expiry_year=? WHERE id=?";

	public static final String UPDATE_CARD_TYPE_BY_ID = " UPDATE cfg_card_type SET `description`=? ,`bin`=?,`bin_suffix`=?,`ncmc_flag`=?,`endpoint`=? WHERE id=? ";

	public static final String UPDATE_ADDRESS_TYPE_BY_ID = " UPDATE cfg_address_type SET `ext_id`=? WHERE id=? AND `address_description`=? ";

	public static final String UPDATE_EMAIL_TYPE_BY_ID = " UPDATE cfg_email_type SET `ext_id`=? WHERE id=? AND `email_description`=? ";

	public static final String UPDATE_KEY_BY_ID = " UPDATE cfg_keys SET `cvk_key`=?, `pvk_key`=?, `mdk_key`=?, `security_key_id`=? WHERE id=? ";

	public static final String UPDATE_ACCOUNT_TYPE_BY_ID = " UPDATE cfg_account_type SET `ext_id`=? WHERE id=? AND `Account_description`=? ";

	public static final String UPDATE_BIN_BY_ID = " UPDATE cfg_bin SET `description`=?, network_scheme=?, flag=? WHERE id=? AND `bin`=? ";

	public static final String UPDATE_BRANCH_CODE_BY_ID = " UPDATE cfg_branch_code SET `branch_Address`=?,`branch_type`=?, `ext_id`=? WHERE id=? AND `branch_code`=? ";

	public static final String UPDATE_BRANCH_BY_ID = " UPDATE cfg_branch SET `branch_desc`=? WHERE id=? AND `branch_name`=? ";

	public static final String GET_DOCUMENT_TYPE = " SELECT id AS lkpkey, `document_description` AS lkpvalue FROM `cfg_document_type` ";

	public static final String GET_CURRENCY_CODE = " SELECT currency_code AS lkpkey, swift_currency_code AS lkpvalue FROM `cfg_currency_code` ";

	public static final String INSERT_GROUP = " INSERT INTO `group_mapping` (`name`,`description`,`created_by`,`participant_id`,`created_date`) VALUES (?,?,?,?,NOW()) ";

	public static final String SELECT_GROUP = " SELECT COUNT(*) FROM group_mapping WHERE UPPER(`name`) = ? ";

	public static final String GET_GROUP_LIST = " SELECT `id` AS lkpkey, `name` AS lkpvalue FROM `group_mapping` WHERE `participant_id`=? ";

	public static final String GET_PART_CONFIG_KEY = " SELECT `config_key` AS lkpkey,`config_value` AS lkpvalue FROM `participant_config` pc LEFT JOIN `cfg_participant_key_type` pk ON pc.`participant_key_type`=pc.`id` WHERE pc.`participant_id`=? ";

	//For Sequro
	/* public static final String UPDATE_CLIENT = " UPDATE `customer_details` SET `citizen_id`=?, `cif_key`=?, `first_name`=?, `middle_name`=?, `last_Name`=?, "
			+ " `sex`=?, `dob`=?, `mother_maiden_name`=? WHERE id=? "; */
	
	//For Montra
	public static final String UPDATE_CLIENT = " UPDATE `customer_details` SET `citizen_id`=?, `cif_key`=?, `first_name`=?, `middle_name`=?, `last_Name`=?, "
			+ " `sex`=?, `dob`=?, `mother_maiden_name`=? WHERE customer_id=? ";

	public static final String GET_CARD = " SELECT cd.enc_card AS inCard, CONCAT(cc.`card_type`,' : ',cc.`description`) AS inCardType, cu.citizen_id AS inCustomerID, cd.`token_card` AS cardFlag FROM `card_details` cd INNER JOIN `cfg_card_type` cc ON cd.`card_type` = cc.id INNER JOIN `customer_details` cu ON cd.`customer_id`= cu.id WHERE cd.enc_card = ? ";

	public static final String GET_EMBOSS_CARD = " SELECT COUNT(*) AS `count`,CONCAT(cc.`card_type`,' : ',cc.`description`) AS cardType, CASE WHEN cd.`instant_flag` = 'Y' THEN 'Instant Card' ELSE 'Personalize Card' END AS flag, cc.`id` as id, cd.`instant_flag` as eflag FROM `card_details` cd INNER JOIN  `cfg_card_type` cc ON cd.`card_type` = cc.id WHERE cd.`emboss_flag` IS NULL GROUP BY cd.card_type , cd.instant_flag ";

	public static final String UPDATE_CARD = " UPDATE card_details SET `customer_id` = ? WHERE `token_card` = ? ";

	public static final String GET_CLEAR_CARD = " SELECT enc_card FROM `card_details` WHERE token_card=? ";

	public static final String GET_ENDPOINT = " SELECT id AS lkpkey, `endpoint` AS lkpvalue FROM `cfg_endpoint` ";

	public static final String GET_ENDPOINT_URL = " SELECT ce.`url` FROM `cfg_endpoint` ce INNER JOIN `cfg_card_type` cct ON ce.`id`= cct.`endpoint` INNER JOIN `card_details` cd ON cd.`card_type`=cct.`id` WHERE token_card=? ";

	public static final String GET_Pinprinting_List = " SELECT COUNT(*)AS `count`, CONCAT(cct.`card_type`,\" : \",cct.`description`) AS cardTypeDesc,cct.id AS cardType FROM `card_plastic_details` cpd INNER JOIN `card_details` cd ON cpd.card_number=cd.`token_card` INNER JOIN `cfg_card_type` cct ON cct.`id`=cd.`card_type` WHERE cpd.pinmailer_flag ='Y' AND cd.`participant_id`=? GROUP BY cd.`card_type` ";

	public static final String UPDATE_PIN_PRINTING = " UPDATE `card_plastic_details` SET pinmailer_flag=?,pinmailer_issue_date=NOW() WHERE card_number IN (SELECT token_card FROM `card_details` WHERE card_type=? AND participant_id=?) AND participant_id=? ";

	public static final String GET_MCC = " SELECT `mcc` AS lkpkey, CONCAT(`mcc`,' : ',`description`) AS lkpvalue FROM `cfg_mcc` ";

	public static final String DELETE_MCC = " DELETE FROM card_type_mcc WHERE card_type=? ";

	public static final String INSERT_MCC = " INSERT INTO `card_type_mcc` VALUES (?,?,?) ";

	public static final String GET_CARD_TYPE_MCC = " SELECT cm.mcc AS lkpkey, CONCAT(cm.`mcc`,' : ',cm.`description`) AS lkpvalue FROM `card_type_mcc` ctc INNER JOIN `cfg_mcc` cm ON ctc.`mcc`=cm.`mcc` WHERE ctc.card_type=? AND ctc.participant_id=? ";

	public static final String GET_CARD_MCC = " SELECT mcc AS lkpkey FROM `card_mcc_service` WHERE card_number=? AND participant_id=? ";

	public static final String GET_BIN_CARDTYPE = " SELECT id FROM `cfg_card_type` WHERE CONCAT(`bin`,`bin_suffix`) = ? AND participant_id = ? ";

	public static final String GET_ADDRESS_TYPE = " SELECT id FROM `cfg_address_type` WHERE address_description=? AND participant_id=? ";

	public static final String GET_EMAIL_TYPE = " SELECT id FROM `cfg_email_type` WHERE email_description=? AND participant_id=? ";

	public static final String GET_DOC_TYPE = " SELECT id FROM `cfg_document_type` WHERE document_Type=? AND participant_id=? ";

	public static final String DELETE_CUST = " DELETE FROM `customer_details` WHERE id=? ";

	public static final String DELETE_ADDRESS = " DELETE FROM `customer_address` WHERE customer_id=? ";

	public static final String DELETE_CONTACT = " DELETE FROM `customer_contact` WHERE customer_id=? ";

	public static final String DELETE_DOCUMENT = " DELETE FROM `customer_document` WHERE customer_id=? ";

	public static final String DELETE_CARD = " DELETE FROM `card_details` WHERE token_card=? ";

	public static final String DELETE_EMAIL = " DELETE FROM `customer_email` WHERE customer_id=? ";

	public static final String DELETE_CARD_PLST = " DELETE FROM `card_plastic_details` WHERE card_number=? ";

	public static final String DELETE_ACCOUNT = " DELETE FROM `card_account_linkage` WHERE card_number=? ";

	public static final String GET_STATE_ID = " SELECT id FROM `cfg_state` WHERE UPPER(state_name) = ? ";

	public static final String GET_CITY_ID = " SELECT id FROM `cfg_city` WHERE UPPER(city_name) = ?  ";

	public static final String GET_COUNTRY = " SELECT id FROM `cfg_country` WHERE UPPER(country_name) = ? ";

	public static final String GET_ACCOUNT_ID = " SELECT id FROM `cfg_account_type` WHERE `Account_description`=? ";

	public static final String INSERT_BULK_DATA = " INSERT INTO bulk_data (file_name,`data`,`status`,created_by,created_date,participant_id) VALUES (?,?,?,?,now(),?) ";

	public static final String DELETE_CARD_MCC = " DELETE FROM card_mcc_service WHERE card_number=? AND participant_id=? ";

	public static final String INSERT_CARD_MCC = " INSERT INTO card_mcc_service (card_number,mcc,participant_id) VALUES (?,?,?) ";

	public static final String GET_BULK_DATA = "SELECT file_name as fileName , created_date as createdDate FROM `bulk_data` ORDER BY created_date ASC";

	public static final String GET_BATCH_ID_DATA = "SELECT `data` AS lkpkey, `id` AS lkpvalue, `status` as resp  FROM `bulk_data` WHERE file_name=? AND participant_id=?";

	public static final String UPDATE_BULK_DATA = " UPDATE `bulk_data` SET `status`=?, modified_by=?, modified_date=NOW() WHERE `id`=? ";

	public static final String GET_BATCH_UPDATE_COUNT = " SELECT COUNT(*) FROM `bulk_data` WHERE `status`='Pending' AND file_name=? AND participant_id=? ";

	public static final String GET_TXN = " SELECT `type`,`description`,`amount`,`refid`,`status`,`txndate` FROM `transaction_details-bkp` ";

	public static final String DELETE_CARD_TOKEN = " DELETE FROM cfg_card_token WHERE card_type = ? ";

	public static final String INSERT_CARD_TOKEN = " INSERT INTO cfg_card_token (card_type,`length`,onDemand,cron,description) VALUES (?,?,?,?,?) ";

	public static final String GET_CARD_TOKEN = " SELECT cct.id, CONCAT(ct.card_type, ' : ', ct.description) AS strCardType, cct.LENGTH AS strLength, cct.onDemand AS strDemandFlag, cct.cron AS strDemondCronExpr, cct.description AS enDesc  FROM `cfg_card_token` AS cct INNER JOIN `cfg_card_type` ct ON cct.card_type = ct.id ";

	public static final String GET_CARD_TYPE = " SELECT CONCAT(cf.card_type, ' : ' ,cf.description) AS strCardType, cf.id AS strID FROM `cfg_card_type` cf LEFT JOIN cfg_card_token ct ON cf.id = ct.card_type WHERE ct.card_type IS NULL AND  cf.`card_token` = 1 ";

	public static final String GET_INVENTROY = " SELECT CONCAT(cct.card_type, ' : ', cct.description) AS cardType,"
			+ "SUM(CASE WHEN cust.citizen_id IS NULL THEN 1 ELSE 0 END) AS unsold,"
			+ "SUM(CASE WHEN cust.citizen_id IS NOT NULL THEN 1 ELSE 0 END) AS sold "
			+ "FROM card_details AS cd INNER JOIN "
			+ "customer_details AS cust ON cd.customer_id = cust.id INNER JOIN "
			+ "cfg_card_type cct ON cd.card_type = cct.id "
			+ "GROUP BY cd.card_type ";

	public static final String GET_MOBILE_TOKEN = " SELECT mobile, `length` AS strLength, description AS enDesc FROM `cfg_mobile_token` ";

	public static final String GET_MOBILE_TOKEN_STR = " SELECT token FROM `mobile_token` WHERE mobile = ? ";

	public static final String ADD_ISO_CONFIG = "INSERT INTO `cfg_iso` (`name`, participant_id, `data`) VALUES (?,?,?)";

	public static final String GET_ISO_CONFIG = " SELECT id, `name`, `data` FROM `cfg_iso` WHERE participant_id= ? ";

	//public static final String ACCOUNT_STATEMENT_DATE_SEARCH = " SELECT id AS strID,account_holder_name AS strAccountHolderName,account_number AS strAccountNumber,account_type AS strAccountType,transaction_id AS strTransactionID,transaction_date AS strTransactionDate,transaction_details AS strTransactionDetails,credit_amount AS strCreditAmount,debit_amount AS strDebitAmount from `account_statement` AS accstmt where accstmt.transaction_date between ? and ? ";

	//public static final String ACCOUNT_STATEMENT_SEARCH = " SELECT id AS strID,account_holder_name AS strAccountHolderName,account_number AS strAccountNumber,account_type AS strAccountType,transaction_id AS strTransactionID,transaction_date AS strTransactionDate,transaction_details AS strTransactionDetails,credit_amount AS strCreditAmount,debit_amount AS strDebitAmount from `account_statement` AS accstmt where accstmt.account_type = ? and accstmt.account_number = ? and accstmt.transaction_date between ? and ? ";

	public static final String GET_TRANSACTION_REPO = " SELECT `transaction_time`, `transaction_type`,`transaction_status`,`transaction_amount`, `transaction_ref_no`, `acquirer_id` from `transaction_details` ";

	public static final String GET_SEARCH_TRANSACTION = " SELECT local_tran_date AS strLocal_tran_date, local_tran_time AS strLocal_tran_time, "
			+ "tm.txn_id AS strtxnId, tm.mask_card_no AS strMaskCardNo,tm.transaction_amount AS strTxnAmount, "
			+ "tm.processing_code AS strProcessingCode, "
			+ "IFNULL(tm.mid, '-') AS strMid, IFNULL(tm.tid, '-') AS strTID, "
			+ "IFNULL(tm.rrn, '-') AS strRRN, IFNULL(tm.mcc, '-') AS strMccCode, IFNULL(tm.response_code, '-') AS strResponseCode "
			+ "FROM tran_master tm where local_tran_date between ? AND ? ";

	public static final String GET_CARD_INVENTORY = " SELECT card_issue_date,CONCAT(cct.card_type , ' : ', cct.description) AS cardType, "
			+ "cct.bin AS card_bin,"
			+ "COUNT(CASE WHEN cd.issued_to_customer IS NOT NULL AND cd.card_status != 11 AND cd.card_status != 4 AND cd.card_status != 2 THEN 1 END) AS sold, "
			+ "cd.expiry_date FROM card_details AS cd "
			+ "INNER JOIN cfg_card_type cct ON cd.card_type = cct.id "
			+ "GROUP BY cd.card_type,card_issue_date,description,card_bin,expiry_date "
			+ "HAVING card_issue_date BETWEEN ? AND ? AND sold > 0 ";

	public static final String CARD_INVENTORY_PENDING = " SELECT card_issue_date,CONCAT(cct.card_type , ' : ', cct.description) AS cardType, "
			+ "cct.bin AS card_bin, "
			+ "COUNT(CASE WHEN cd.issued_to_customer IS NULL AND cd.instant_flag = 'Y' AND cd.card_status != 11 AND cd.card_status != 4 AND cd.card_status != 2 THEN 1 END) AS unsold, "
			+ "cd.expiry_date FROM card_details AS cd "
			+ "INNER JOIN cfg_card_type cct ON cd.card_type = cct.id "
			+ "GROUP BY cd.card_type,card_issue_date,description,card_bin,expiry_date "
			+ "HAVING card_issue_date BETWEEN ? AND ? AND unsold > 0 ";
			

	public static final String CARD_HOT_LISTING = " SELECT card_issue_date , CONCAT(cct.card_type , ' : ', cct.description) AS cardType,cd.card_number, "
			+ "DATE(csh.card_status_changed_date) AS card_status_changed_date, TIME(csh.card_status_changed_date) AS card_status_changed_time, "
			+ "csh.card_status_description,csh.status_change_user "
			+ "FROM card_status_history AS csh "
			+ "INNER JOIN card_details cd ON csh.card_number  = cd.token_card "
			+ "INNER JOIN cfg_card_type cct ON cd.card_type = cct.id "
			+ "HAVING card_status_changed_date BETWEEN ? AND ? ";
			

	public static final String HOT_LISTING_MIS = " SELECT CONCAT(cct.card_type , ' : ', cct.description) AS cardType, "
			+ " CASE WHEN cust.citizen_id IS NOT NULL THEN COUNT(cust.citizen_id) ELSE 0 END AS cardCount , card_issue_date  FROM `card_details` "
			+ " AS cd INNER JOIN `customer_details` AS cust ON cd.customer_id = cust.id  "
			+ " INNER JOIN `cfg_card_type` cct ON cd.card_type = cct.id GROUP BY cd.card_type "
			+ " having  card_issue_date between ? AND ? ";

	public static final String HOT_LISTING_MISNETWORK = " SELECT t.card_issue_date,t.network_type,sum(t.network_count) AS network_count "
			+ " FROM(SELECT  card_issue_date, "
			+ "  case when cd.card_number like '4%' then 'Visa'  when cd.card_number like '5%' then 'Master' "
			+ "		when cd.card_number like '6%' then 'Rupay'  else  'Other' end as network_type, "
			+ "		 count(case when cd.card_number like '4%' then 'Visa'  when cd.card_number like '5%' then 'Master' "
			+ "		 when cd.card_number like '6%' then 'Rupay'else  'Other' end) network_count "
			+ "		 FROM `card_details`  AS cd INNER JOIN `customer_details` AS cust ON cd.customer_id = cust.id  "
			+ "		 INNER JOIN `cfg_card_type` cct ON cd.card_type = cct.id GROUP BY cd.card_type "
			+ "		 having card_issue_date between ? AND ? " + "		) AS t group by t.network_type ";

	// public static final String TOTAL_HOT_LISTING_MIS = " SELECT
	// COUNT(card_number) AS total_hot_listed_count from 'card_status_history' where
	// status_change_date between ? AND ? ";

	public static final String TOTAL_HOT_LISTING_MIS = " SELECT COUNT(card_number) AS total_hot_listed_count from card_status_history where status_change_date between ? AND ? ";
	
	
	public static final String ACCOUNT_LINKAGE_SEARCH = "SELECT id AS strID,participant_id AS strParticipantID, "
			+ "account_type AS strAccountType,account_number AS strAccountNumber, "
			+ "card_number AS strCardNumber,card_type AS strCardType, "
			+ "card_status AS strCardStatus,account_status AS strAccountStatus, "
			+ "creation_date AS strCreationDate, "
			+ "created_by AS strCreatedBy from card_account_linkage_master AS link "
			+ "where link.account_type = ? and link.account_number = ? ";
	
	public static final String CARD_LINKAGE_SEARCH = "SELECT id AS strID,participant_id AS strParticipantID, "
			+ "account_type AS strAccountType,account_number AS strAccountNumber,card_number AS strCardNumber,"
			+ "card_type AS strCardType,card_status AS strCardStatus,account_status AS strAccountStatus,"
			+ "creation_date AS strCreationDate,created_by AS strCreatedBy from `card_account_linkage_master` "
			+ "AS link where link.card_type = ? and link.card_number = ? ";

	
	//Added by Sagar for getting interest outstanding data in list Start
	public static final String INTEREST_OUTSTANDING_LIST="SELECT mcc_code AS strMcc,transaction_id AS strTransactionID,"
			+"transaction_amount AS strTransactionAmount,transaction_date AS strTransactionDate,"
		    +"transaction_time AS strTransactionTime,interest_paid_amount AS strInterestPaidAmount,"
			+"calculated_interest AS strCalculateInterest,calculated_GST AS strCalculateGST,"
			+"interest_paid_date  AS strInterestPaidDate,interest_calculated_date AS strInterestCalculateDate "
			+ "FROM interest_account_wise WHERE account_number = ? and  is_paid='N'";		
	//Added by Sagar for getting interest outstanding data in list End
	
	//public static final String ACCOUNT_STATEMENT_DATE_SEARCH = " SELECT id AS strID,account_holder_name AS strAccountHolderName,account_number AS strAccountNumber,account_type AS strAccountType,transaction_id AS strTransactionID,transaction_date AS strTransactionDate,transaction_details AS strTransactionDetails,credit_amount AS strCreditAmount,debit_amount AS strDebitAmount from `account_statement` AS accstmt where accstmt.transaction_date between ? and ? ";
	public static final String ACCOUNT_STATEMENT_DATE_SEARCH = " SELECT id AS strID, "
			+ "account_holder_name AS strAccountHolderName,account_number AS strAccountNumber,"
			+ "account_type AS strAccountType,transaction_id AS strTransactionID,transaction_date AS strTransactionDate, "
			+ "transaction_amount AS strTransactionAmount, transaction_details AS strTransactionDetails, "
			+ "txn_mode AS strTranMode from account_statement AS accstmt "
			+ "where accstmt.transaction_date between ? and ? ";
	
	//public static final String ACCOUNT_STATEMENT_SEARCH = " SELECT id AS strID,account_holder_name AS strAccountHolderName,account_number AS strAccountNumber,account_type AS strAccountType,transaction_id AS strTransactionID,transaction_date AS strTransactionDate,transaction_details AS strTransactionDetails,credit_amount AS strCreditAmount,debit_amount AS strDebitAmount from `account_statement` AS accstmt where accstmt.account_type = ? and accstmt.account_number = ? and accstmt.transaction_date between ? and ? ";
	public static final String ACCOUNT_STATEMENT_SEARCH = "SELECT CONCAT_WS(' ', acmst.first_name, acmst.middle_name, acmst.last_name) AS strAccountHolderName,"
			+ "accstmt.account_number AS strAccountNumber, accstmt.account_type AS strAccountType, "
			+ "accstmt.transaction_id AS strTransactionID, accstmt.transaction_date AS strTransactionDate, "
			+ "accstmt.transaction_amount AS strTransactionAmount, accstmt.closing_balance AS strClosingBalance,"
			+ "accstmt.transaction_details AS strTransactionDetails, accstmt.txn_type AS strTranType, "
			+ "accstmt.txn_mode AS strTranMode "
			+ "from account_statement accstmt LEFT JOIN account_master acmst "
			+ "ON accstmt.account_number = acmst.account_number "
			+ "where accstmt.account_type = ? and accstmt.account_number = ? "
			+ "and accstmt.transaction_date BETWEEN ? AND ? "
			+ "ORDER BY accstmt.transaction_date ASC";
	
	public static final String ACCOUNT_ISSUE_SEARCH = ""
			+ "SELECT id AS strID, account_type AS strAccountType, account_number AS strAccountNumber, "
			+ "title AS strTitle, first_name AS strFirstName, middle_name AS strMiddleName, "
			+ "last_name AS strLastName, gender AS strGender, dob AS strDOB, "
			+ "address1 AS strAddress1, address2 AS strAddress2, address3 AS strAddress3, pincode AS strPinCode, "
			+ "mobile_no AS strMobileNo, phone_no AS strPhoneNo, email AS strEmailID, "
			+ "city AS strCity, state AS strState, country AS strCountry, created_by AS strCreatedBy, "
			+ "status AS strStatus, is_instant_account As strIsInstantAccount "
			+ "from account_master WHERE Is_linked_with_card = ? ";
	
	public static final String TRANSACTION_ID_DATA_SEARCH = "SELECT id AS strID, year AS strYear, "
			+ "julian_date AS strJulianDate,last_txn_serial_no AS strLastTxnSerialNo "
			+ "from transaction_id_table "
			+ "where year = ? AND julian_date = ? ORDER BY id DESC LIMIT 1";
	
	public static final String GLACCOUNT_STATEMENT_DATE_SEARCH= "SELECT id AS strID,account_type AS strAccountType,"
			+ "account_number AS strAccountNumber,Ref AS strRef,"
			+ "tran_id AS strTranID,amount AS strstrAmount,transaction_date AS strTransactionDate,"
			+ "FROM gl_account_statement where transaction_date BETWEEN ? AND ? ";
	
	public static final String CARD_TYPE_LIST = "SELECT DISTINCT cd.card_type AS strCardType,cd.description AS strCardDescription,cd.bin AS strCardBin FROM cfg_card_type cd INNER JOIN\n" + 
			"card_details crd ON cd.id = crd.card_type WHERE crd.issued_to_customer IS NULL ";
	
	public static final String UNSOLD_CARD_NO_LIST = "SELECT crd.id AS strCardType,cd.enc_card AS strCardNumber,token_card AS strTokenCard FROM  card_details cd " 
	        + "INNER JOIN cfg_card_type crd ON cd.card_type = crd.id WHERE crd.card_type = ? AND cd.issued_to_customer IS NULL ";

	
	//Added By Prashant Tayde 
	public static final String GET_USER_DETAILS_BY_MOBILENO_EMAIL = "Select User_ID AS strUserID,participant_id AS strParticipantID,User_Access_Type_Id AS strUserAccessType, user_full_name AS strFirstName, "
			+ " User_Name AS strUserName,Mobile_Number AS strMobileNo,Email_ID AS strEmailID,"
			+ " Secret_Question_ID AS strSecQueID,Secret_Question_Answer AS strSecQueAns,last_password_change_datetime AS lastPasswordChange,User_Password As strPassword,User_Status_Id AS strUserStatusID,Created_By AS strCreatedBy, plain_password AS plainPassword, isPasswordReset AS isPasswordReset "
			+ " from user_details Where user_name = ? AND email_id = ? ";
	
	
	//Added by prashant Tayde for Password Update
	
	public static final String UPDATE_USER_PASS = "UPDATE user_details SET user_password = ? , sensitive_pwd = ? WHERE user_name = ? AND email_id = ? ";
	
	public static final String IS_PASSWORD_RESET = "INSERT INTO password_reset SET user_name = ? , email = ? , is_password_reset = ? ,created_date = ? ";
	
	public static final String PLAIN_PASSWORD_USERDETAILS = "INSERT INTO user_details SET plain_password = ? ";
	
	//Added for Mail configuration
	public static final String GET_PARTICIPANT_MAIL = "select id AS strId, host AS host ,port AS port, username AS username, password AS password, fromEmailAddress AS fromEmailAddress, footerSignatureText AS footerSignatureText where participant_id = ? ";
	
	//public static final String UPDATE_RESETPASSWORD_ATTEMPT = "UPDATE `user_details` SET  WHERE User_ID=? "; 
	
	public static final String UPDATE_RESETPASSWORD_USER_DETAILS = "UPDATE `user_details` SET last_password_change_datetime = ? , user_password = ? , isPasswordReset = ? WHERE user_name = ? AND user_id = ? ";
	
	public static final String RESET_PASSWORDS = "SELECT old_password,new_password,last_password_changed_date from user_passwords_history as rp Where user_name = ? ORDER BY rp.last_password_changed_date DESC LIMIT 5 ";
	
	public static final String ADD_OLD_NEW_PASSWORDS = "INSERT INTO user_passwords_history SET user_id = ?, user_name = ?, email = ?, old_password = ?, new_password = ?, last_password_changed_date = ? ";

	public static final String USER_DETAILS = "SELECT user_id AS strUserID,participant_id AS strParticipantID,user_access_type_id AS strUserAccessType,user_full_name AS strFirstName,mobile_number AS strMobileNo,email_id AS strEmailID,last_password_change_datetime AS lastPasswordChange,user_password AS strPassword,isPasswordReset AS isPasswordReset FROM user_details WHERE user_name = ? ";
	
	public static final String AUDIT_ENTRY = "INSERT INTO cms_audit SET participant_id = ? , table_name = ?, column_name = ? ,new_field = ? , old_field = ?, dml_action = ?, created_date ? , created_by = ? ";
	
	public static final String UPDATE_RESETPASSWORD_FLAG_FOR_USER = "UPDATE `user_details` SET forgot_password_validation_failed_attempt=?, last_password_change_datetime = ? , isPasswordReset = ? WHERE user_name = ? AND user_id = ? ";
	
	public static final String GET_ENC_DEC_VALUE = "SELECT id AS id , value AS value, created_date AS createdDate, created_by AS created_by FROM card_encrypt_key ";
	
	public static final String CARD_ENCDEC_TEMP = "SELECT id AS id , value AS value, created_date AS createdDate, created_by AS created_by FROM card_encrypt_key_temp "; 
	
	public static final String DELETE_ENDEC_TEMP = "DELETE FROM card_encrypt_key_temp WHERE id = '1' ";
	
	public static final String GET_MASKED_VALUE = "SELECT id AS id , value AS value FROM card_encrypt_key";
	
	public static final String UPDATE_USER_PLAIN_PASS = "UPDATE user_details SET login_failed_attemps_count = ? , forgot_password_validation_failed_attempt = ? ,last_password_change_datetime = ? , plain_password = ? , isPasswordReset = ? WHERE user_name = ? AND mobile_number = ? AND email_id = ? ";

	public static final String UPDATE_USER_FLAG = "UPDATE user_details SET isPasswordReset = ? WHERE user_name = ? AND mobile_number = ? AND email_id = ? ";

	public static final String LAST_THREE_FAILED_ATTEMPT = "Select user_id AS userId ,participant_id AS participantId,login_datetime AS loginDateTime,is_successful AS isSuccessful FROM user_login_log WHERE participant_id = ? AND user_id = ? LIMIT 3 ";

	public static final String UPDATE_LOGOUT_DATE = "UPDATE user_login_log SET logout_datetime = ? WHERE participant_id = ? AND user_id = ? AND login_datetime = ? ";
	
	public static final String Validate_CUSTODIAN1_PASSWORD = "SELECT COUNT(*) From user_details WHERE user_password = ? AND user_name = 'custodian1' ";
	
	public static final String Validate_CUSTODIAN2_PASSWORD = "SELECT COUNT(*) From user_details WHERE user_password = ? AND user_name = 'custodian2' ";

	public static final String GET_LOGGING_FLAG = "SELECT id AS id, user_id AS userid, flag AS userflag, updated_date AS updatedDate From logging_status ";
	
}

