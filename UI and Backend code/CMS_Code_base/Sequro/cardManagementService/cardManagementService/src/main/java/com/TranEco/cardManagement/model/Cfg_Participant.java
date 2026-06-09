package com.TranEco.cardManagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cfg_Participant implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String participant_name;
	private String description;
}
