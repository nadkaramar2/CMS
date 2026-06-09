package com.traneco.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResponseBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String outResponseCode;
	private String message;
	private String outCard;
	private String outTokenCard;
	private String flag;
	
	private String responseCode;
	
	private String responseDesc;
	/*
	 * private Date fromDate; private Date toDate;
	 */
	
	@Override
	public String toString() {
		return "ResponseBean [outResponseCode=" + outResponseCode + ", message=" + message + ", outTokenCard="
				+ outTokenCard + "]";
	}
	
}
