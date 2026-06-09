package com.traneco.common.logger.model;

import java.sql.Time;
import java.util.Date;

import lombok.Data;

@Data
public class UserLoggingModel
{
	private int id;
	private String userid;
	private String userflag;
	private Date updatedDate;
	private Time updatdedTime;
	
	public static String userLogFlag = "N";
}
