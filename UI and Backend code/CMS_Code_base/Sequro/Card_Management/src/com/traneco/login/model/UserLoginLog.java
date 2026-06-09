package com.traneco.login.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserLoginLog implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int participantId;
	private int userId;
	private String username;
	private Date loginDateTime;
	private Date logoutDateTime;
	private String ip;
	private String isSuccessful;
	
	
}
