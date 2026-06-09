package com.TranEco.cardManagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cfg_Status implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String participant_id;
	private String status_description;
	private String status_flag;
	private String ext_id;
	private String temporary_flag;
	
}
