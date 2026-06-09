package com.TranEco.cardManagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cfg_Card_Template implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String participant_id;
	private String default_card_Status;
	private String card_type_id;
	private String service_code;
	private String card_issue_type_id;
	private String card_mailer_Issue_flag;
	private String pin_mailer_Issue_flag;
	private String temporary_limit_activation_flag;
	private int daily_pin_retry_limit;
	private int consecutive_pin_retry_limit;
	private double online_atm_limit;
	private double online_pos_limit;
	private double online_ecom_limit;
	private double offline_limit;
	private double monthly_limit;
	private double weekly_limit;
	private double daily_limit;
	private String expiry_cfg_type;
	private int expiry_month;
	private int expiry_year; 

}
