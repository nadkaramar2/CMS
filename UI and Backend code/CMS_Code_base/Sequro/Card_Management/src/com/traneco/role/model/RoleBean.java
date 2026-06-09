
package com.traneco.role.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class RoleBean 
{
	private Integer iRoleID;
	private int iParticipantId;
	private String strRoleName;
	private String strDescription;
	private String strRemark;
	private String strMenuIds;
	private String [] strMenuIdsArr;
	private String strResultCode;
	private String strResultMessage;
	private String strCreatedBy;
	private String strCreatedDate;
	private String strAction;
	private String strActive;
	private String strNewOrModified;
	private String strRequestBtn;
	private String strPendingForApproval;
	private String [] strCheckBox;
	private List<String> statusList = new ArrayList<String>()
	{
	private static final long serialVersionUID = 1L;
	{
		add("false"); 
		add("true");
		}
	};
	
	
	/*Start parameters for edit role*/
	private List<ParentMenuBean> parentMenuList;
	private List<ParentSubMenuBean> subMenuList;
	private List<MenuBean> menuList;
	private String   strGroupID;
	private int iMenuId;
	private String strMenuName;
	private int iParentSubMenuId;
	private String strSubMenuName;
	private int iParentMenuId;
	private String strParentMenuName;
	/*End parameters for edit role*/
	
	/*Start parameters for approve role*/
	private String strRoleTempID;
	private int iIsActive;
	private int iIsDeleted;
	private String strLastModifiedBy;
	private String strLastModifiedDate;
	private String strApprovedBy;
	private String strApprovedDate;
	private int iApprovalStatusID;
	private String strCreadtedByUser;
	private String strApproveAction;
	private String [] strRoleTempIDs;
	/*End parameters for approve role*/
	
	
	private int roleMenuMappingId;
	private int roleTempId;
}
