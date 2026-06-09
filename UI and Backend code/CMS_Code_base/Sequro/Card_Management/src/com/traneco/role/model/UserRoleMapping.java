package com.traneco.role.model;

import java.util.Date;

import com.traneco.user.model.UserBean;

import lombok.Data;

@Data
public class UserRoleMapping 
{
	private int id;
	private int userId;
	private int roleId;
	private int participantId;
	private String userStatus;
	private String createdBy;
	private Date createdDate;
	private String lastModifiedBy;
	private Date lastModifiedDate;
	private int groupId;
	
	private UserBean userBean;
}
