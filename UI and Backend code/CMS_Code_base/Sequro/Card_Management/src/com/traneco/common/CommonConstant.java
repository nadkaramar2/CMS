package com.traneco.common;

import java.io.Serializable;

public class CommonConstant implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	public static final String PASSWORD_RESET_FLAG = "N";
	
	public static final String  USER_ACTIVE = "A";
	
	public static final String USER_BLOCK = "B";
	
	public static final String PASSWORD_FLAG = "Y";
	
	
	
	public class AuditConfiguration
	{
		public static final String UPDATE_AUDIT = "UPDATE";
		public static final String DELETE_AUDIT = "DELETE";
	}
}
