package com.traneco.user.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmailSettingMaster implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String strId;
	private String strParticipantId;
	private String host;
	private int port;
	private String username;
	private String password;
	private String fromEmailAddress;
	private String footerSignatureText;
}
