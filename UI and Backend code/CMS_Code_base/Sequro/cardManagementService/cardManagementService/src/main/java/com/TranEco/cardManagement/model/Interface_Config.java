package com.TranEco.cardManagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Interface_Config implements Serializable {
	 
	private static final long serialVersionUID = 1879075449875259287L;
	
	private int id;
	private int handler_id;
	private int participant_id;
	private String key_type;
	private String key_name;
	private String key_value;
}
