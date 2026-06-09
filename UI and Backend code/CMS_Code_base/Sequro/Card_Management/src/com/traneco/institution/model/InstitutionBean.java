package com.traneco.institution.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class InstitutionBean implements Serializable{
	
	private static final long serialVersionUID = 1L;		
	private String institutionId;
	private String institutionCode;
	private String institutionDesc;
	private String outwardDayAmtLimit;
	private String outwardMonthAmtLimit;
	private String outwardMinAmtTran;
	private String outwardMaxAmtTran;
	private String outwardDayMaxTranCount;
	private String outwardMonthMaxTranCount;
	private String createdBy;
	private String createdById;
	private String createdDate;
	private String lastModifiedBy;
	private String lastModifiedDate;
	private String type;
	private String approvalStatus;
	private String requestID;
	private String [] approveID;
	private String remark;
	
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	public String getInstitutionCode() {
		return institutionCode;
	}
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}
	public String getInstitutionDesc() {
		return institutionDesc;
	}
	public void setInstitutionDesc(String institutionDesc) {
		this.institutionDesc = institutionDesc;
	}
	public String getOutwardDayAmtLimit() {
		return outwardDayAmtLimit;
	}
	public void setOutwardDayAmtLimit(String outwardDayAmtLimit) {
		this.outwardDayAmtLimit = outwardDayAmtLimit;
	}
	public String getOutwardMonthAmtLimit() {
		return outwardMonthAmtLimit;
	}
	public void setOutwardMonthAmtLimit(String outwardMonthAmtLimit) {
		this.outwardMonthAmtLimit = outwardMonthAmtLimit;
	}
	public String getOutwardMinAmtTran() {
		return outwardMinAmtTran;
	}
	public void setOutwardMinAmtTran(String outwardMinAmtTran) {
		this.outwardMinAmtTran = outwardMinAmtTran;
	}
	public String getOutwardMaxAmtTran() {
		return outwardMaxAmtTran;
	}
	public void setOutwardMaxAmtTran(String outwardMaxAmtTran) {
		this.outwardMaxAmtTran = outwardMaxAmtTran;
	}
	public String getOutwardDayMaxTranCount() {
		return outwardDayMaxTranCount;
	}
	public void setOutwardDayMaxTranCount(String outwardDayMaxTranCount) {
		this.outwardDayMaxTranCount = outwardDayMaxTranCount;
	}
	public String getOutwardMonthMaxTranCount() {
		return outwardMonthMaxTranCount;
	}
	public void setOutwardMonthMaxTranCount(String outwardMonthMaxTranCount) {
		this.outwardMonthMaxTranCount = outwardMonthMaxTranCount;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getCreatedById() {
		return createdById;
	}
	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	public String [] getApproveID() {
		return approveID;
	}
	public void setApproveID(String [] approveID) {
		this.approveID = approveID;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
