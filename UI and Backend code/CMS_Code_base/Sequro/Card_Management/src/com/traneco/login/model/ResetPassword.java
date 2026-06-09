package com.traneco.login.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResetPassword implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String username;
	private String oldpassword;
	private String newpassword;
	private String confirmnewpassword;
	
	
}
