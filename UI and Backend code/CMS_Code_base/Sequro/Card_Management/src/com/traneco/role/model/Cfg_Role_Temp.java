package com.traneco.role.model;

import java.util.Date;

import lombok.Data;

@Data
public class Cfg_Role_Temp 
{
	private int id;
	private int roleId;
	private int participantId;
	private String roleName;
	private String roleDescription;
	private String status;
	private int createdBy;
	private Date createdDate;
	private int lastModifiedBy;
	private Date lastModifiedDate;
	private String approvedBy;
	private Date approvedDate;
	private String remarks;
	private int approvalStatusId;
	private int groupId;
	
	
	@Override
	public String toString() {
		return "Cfg_Role_Temp [id=" + id + ", roleId=" + roleId + ", participantId=" + participantId + ", roleName="
				+ roleName + ", roleDescription=" + roleDescription + ", status=" + status + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + ", remarks="
				+ remarks + ", approvalStatusId=" + approvalStatusId + ", groupId=" + groupId + "]";
	}
	
	
	
}
