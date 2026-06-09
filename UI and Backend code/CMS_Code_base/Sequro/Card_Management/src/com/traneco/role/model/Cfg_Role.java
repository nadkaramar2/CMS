package com.traneco.role.model;

import java.util.Date;

import lombok.Data;

@Data
public class Cfg_Role 
{
	private int id;
	private int participantId;
	private int roleId;
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
	private int groupId;
	
	
	
	@Override
	public String toString() {
		return "Cfg_Role [id=" + id + ", participantId=" + participantId + ", roleName=" + roleName
				+ ", roleDescription=" + roleDescription + ", status=" + status + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedDate="
				+ lastModifiedDate + ", approvedBy=" + approvedBy + ", approvedDate=" + approvedDate + ", remarks="
				+ remarks + ", groupId=" + groupId + "]";
	}
	
	
	
	
}
