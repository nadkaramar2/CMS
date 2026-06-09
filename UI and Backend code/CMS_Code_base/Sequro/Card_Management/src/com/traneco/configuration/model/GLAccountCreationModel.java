package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data              //GenrealLegar(GL)
public class GLAccountCreationModel implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strId;
	private String strParticipantId;
	private String strGLAccountType;
	private String strGLDescription;
	private String strGLAccountNumber;
}
