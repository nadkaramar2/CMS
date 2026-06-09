package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Categorytype implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String strParticipantID;
	private String strType;
	private String strDescription;
	private String strCategoryType;
	
	
}
