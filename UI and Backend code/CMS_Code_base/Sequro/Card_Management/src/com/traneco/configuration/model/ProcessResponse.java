package com.traneco.configuration.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.traneco.accountmanagement.model.BankListResponse;

import lombok.Data;

@Data
//@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ProcessResponse implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String desc;
	private String userDefinedMessage;
	private String errorEvent;
	private String status;
	private String message;
	private String isCurrencyTransfer;
	private List<CardTypeModel> cardTypeList = new ArrayList<CardTypeModel>();
	private List<BankListResponse> banksList = new ArrayList<BankListResponse>();
	private List<CurrencyTransferMaster> currencyTransferMasterList = new ArrayList<CurrencyTransferMaster>();
	private TranMaster accountTranMasterObj;
	
	
	
	@Override
	public String toString() {
		return "ProcessResponse [code=" + code + ", desc=" + desc + ", userDefinedMessage=" + userDefinedMessage
				+ ", errorEvent=" + errorEvent + ", status=" + status + ", message=" + message + ", isCurrencyTransfer="
				+ isCurrencyTransfer + ", cardTypeList=" + cardTypeList + ", banksList=" + banksList
				+ ", currencyTransferMasterList=" + currencyTransferMasterList + ", accountTranMasterObj=" + accountTranMasterObj + "]";
	}
	
	
	
	
	
	
	
}
