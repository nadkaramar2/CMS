
package com.traneco.role.constant;

public final class RoleQueryConstant {
	public static final String LOAD_ALL_PARENT_MENU = " SELECT id AS parentMenuId, parentMenu_Name AS parentMenuName FROM `cfg_parentmenu` ";
	public static final String LOAD_ALL_PARENT_SUBMENU_LIST = " SELECT id AS parentSubMenuId, parentMenu_Id AS parentMenuId, subMenu_Name AS subMenuName FROM `cfg_parent_submenu` ";
	public static final String LOAD_ALL_MENU = " SELECT id AS menuId, parent_Menu_Id AS parentMenuId, parent_SubMenu_Id AS parentSubMenuId, menu_Name AS menuName FROM `cfg_cms_menu` ";
	public static final String ROLE_ACTION_SP = "usp_role_add";
	public static final String SEARCH_ROLES_LIST = " SELECT DISTINCT   " + " R.roleName, R.roleID, R.description, "
			+ " R.createdDate, "
			+ " CASE WHEN R.LastModifiedBy IS NULL THEN 'New' ELSE 'Modified' END AS newOrModified, "
			+ " U.FirstName + ' ' + U.LastName AS createdBy FROM IMPS_ROLE_MAST R "
			+ " INNER JOIN IMPS_USER_MAST U ON U.UserID = R.CreatedBy WHERE "
			+ " r.institutionid = ? AND u.institutionid = ? ";
	public static final String LOAD_ROLE_FOR_EDIT = "SELECT  " + "  R.roleName, R.roleID, R.description, R.createdBy, "
			+ "  R.CreatedDate AS createDate, M.menuId, M.menuName, PM.parentMenuId, PM.parentMenuName, PSM.parentSubMenuId, PSM.submenuName, "
			+ "  CASE WHEN R.LastModifiedBy IS NULL  THEN 'New' ELSE 'Modified' END AS newOrModified  "
			+ "FROM IMPS_ROLE_MAST R  " + "  INNER JOIN imps_role_menu_mapping RMM  " + "    ON RMM.RoleID = R.RoleID  "
			+ "  INNER JOIN IMPS_USER_MAST U  " + "    ON U.UserID = R.CreatedBy  " + "  INNER JOIN IMPS_MENU_MAST M  "
			+ "    ON M.MenuId = RMM.MenuID  " + "  INNER JOIN IMPS_PARENT_SUBMENU_MAST PSM  "
			+ "    ON PSM.ParentMenuID = M.ParentMenuID " + "  INNER JOIN IMPS_PARENT_MENU_MAST PM "
			+ "  ON  PM.parentmenuid = PM.parentmenuid " + "WHERE R.RoleId = ? " + "AND R.institutionid = ? "
			+ "AND M.parentmenuid = PSM.parentmenuid " + "AND M.parentmenuid = PM.parentmenuid "
			+ "AND M.parentsubmenuid = PSM.parentsubmenuid ";
	public static final String LOAD_PENDING_ROLE_FOR_APPROVAL = "SELECT  RT.id as strRoleTempID, RT.role_ID as iRoleID, RT.role_Name as strRoleName, RT.role_description as strDescription, "
			+ "u.User_Name as strCreatedBy, RT.created_Date as strCreatedDate, "
			+ "RT.last_Modified_By as strLastModifiedBy, RT.last_Modified_Date as strLastModifiedDate, RT.approved_By as strApprovedBy, "
			+ "RT.approved_Date as strApprovedDate, RT.remarks as strRemark, "
			+ "RT.approval_Status_ID, U.user_full_name AS 'strCreadtedByUser', "
			+ "CASE WHEN Rm.id IS NULL  THEN 'New'  ELSE 'Modified' END AS 'strNewOrModified' "
			+ "FROM `cfg_role_temp` RT  INNER JOIN `user_details` U   ON U.User_ID = RT.Created_By "
			+ "LEFT OUTER JOIN `cfg_role` RM  ON RM.id = RT.Role_ID  WHERE RT.Approval_Status_ID = 3 "
			+ "AND RT.Approved_By IS NULL  AND RT.Created_By <> ?  AND RT.participant_id = ?  ";
	public static final String CHECK_ROLE_FOR_APPROVAL_PENDING = " SELECT roleName FROM IMPS_ROLE_MAST_TEMP WHERE roleid = ? AND approvalstatusid = 3 AND institutionid = ? ";
}

//~ Formatted by Jindent --- http://www.jindent.com
