package com.traneco.accountmanagement.model;

import java.io.Serializable;

public class BankListResponse implements Serializable{

private static final long serialVersionUID = 1L;
	
	private String bankCode;
	private String bankName;

	//for MiddleWare Api
	private String appId;
	private String auditId;
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	
	
}
