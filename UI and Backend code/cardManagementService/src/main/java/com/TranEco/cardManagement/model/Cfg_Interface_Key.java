package com.TranEco.cardManagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cfg_Interface_Key implements Serializable {

	private static final long serialVersionUID = -6335138181400700554L;
	
	private String key_type;
	private String description;
}
