package com.TranEco.cardManagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cfg_Bin implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String bin;
	private String network_scheme;
	private String description;
	private String participant_id;

}
