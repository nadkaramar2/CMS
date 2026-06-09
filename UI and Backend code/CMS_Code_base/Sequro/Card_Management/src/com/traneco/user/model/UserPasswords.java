package com.traneco.user.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserPasswords implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String user_id;
	private String user_name;
	private String email;
	private int password_attempts_count;
	private String old_password;
	private String new_password;
	private Date last_password_changed_date;
}
