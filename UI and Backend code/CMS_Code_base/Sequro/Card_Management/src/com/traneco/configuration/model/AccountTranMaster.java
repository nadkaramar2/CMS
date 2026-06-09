package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AccountTranMaster implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String strID;
	private String strParticipantId;
	private String strTxn_id;
	private String strSys_id;
	private String strMti;
	private String strTran_type;
	private String strAccountType;   //add account_type number  
	private String strAccountNumber;
	private String strTransaction_amount;
	private String strStan;
	private String strLocal_tran_time;
	private String strLocal_tran_date;
	private String strMcc;
	private String strPosCondCode;	
	
	private String strTID;
	private String strMid;
	private String strFrom_account_number;
	private String strTo_account_number;
	private String strSwitch_txn_date;
	
	
	private String fromDate;
	private String toDate;
	private String strResponseCode;
	private String strAuthCode;
	private String txn_Date;
	private String txn_Time;
	private String strTxnTypeKeyWord;
	
	//private String strParticipant_id;
	//private String strFrom_account;
	//private String strTo_account;  
	//private String strAmount_settlement;
	//private String strPos_entry_mode;
	//private String strTran_fee_amount;
	//private String strTran_fee_settle;
	//private String strAcquirer_inst_id;
	//private String strForward_inst_id;
	//private String strTrack_2;
	//private String strService_restriction_code;	
	//private String strMerchant_name_location;
	//private String strTrack_1;
	//private String strTran_currency_code;
	//private String strSettle_curency_code;
	//private String strPin_data;
	//private String strMessage_reason_code;
	//private String strAcquiring_receiving_id;
	/*
	private String strTerminal_device_id;
	private String strTerminal_invoice;	
	private String strTerminal_imei;	
	private String strTerminal_batch_detail;
	
	private String strEncrypted_pan;
	private String strResponse_code;
	private String strEcho_data;
	
	private String strAuthorize_id;
	private String strRetrieval_refrence_nr;
	private String strTerminal_geographical_data;
	
	private String strPrevious_txn_id;
	
	private String strSource_node;
	private String strDestination_node;
	private String strInstrument;
	private String strInstrument_type;
	private String strSms_dms_type;	
	private String strPayee_mobile_no;	
	private String strPincode;	
	private String strSequro_res_code;	
	private String strCard_product;	
	private String strCard_desc;
	private String strCard_bank_name;
	private String strCard_bin;
	private String strCard_masked;
	private String strCard_type;
	private String strIs_international_card;
	private String strApproval_code;	
	private String strTotalOutStandingBalance;
	private String strTotalOutStandingInterest;
	private String strLocalTranDate;	
	private String strCreatedBy;
	*/
}
