package com.traneco.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class EmailTemplate implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String strFrom;
	private String strTo;
	private String strCc;
	private String strBcc;
	private String strSubject;
	private String strText;
	private String strPathToAttachment;
	private ArrayList<String> strToList = new ArrayList<String>();
	private List<String> strCcList = new ArrayList<String>();
	private List<String> strBccList = new ArrayList<String>();
}
