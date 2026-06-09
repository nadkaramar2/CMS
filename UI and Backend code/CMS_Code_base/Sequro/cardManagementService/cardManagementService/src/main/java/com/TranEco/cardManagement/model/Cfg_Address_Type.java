package com.TranEco.cardManagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cfg_Address_Type implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String participant_id;
	private String adddress_description;
	private String ext_id;

}
