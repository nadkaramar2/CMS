package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class TranMaster {
	
	private String strParticipantId;
	private String strLocal_tran_date;
	private String strLocal_tran_time;
	private String strtxnId;
	private String strMti;
	private String strTxnType;
	private String strProcessingCode;
	private String strTxnAmount;
	private String strFeeAmount;
	private String strResponseCode;
	private String strAuthCode;
	private String strSrcTxnId;
	private String strRRN;
	private String strStan;
	private String strMid;
	private String strTID;
	private String strCrdAcceptorId;
	private String strCrdAccptorLoc;
	private String strMccCode;
	private String strAcquiringInstCode;
	private String strPosCondCode;
	private String strMaskCardNo;
	private String strEcnCardNo;
	private String strVpaId;
	private String strCurrncyCode;
	private String strPosDataCode;
	private String strCvdMatch;
	private String strChipTxn;
	private String strProductCode;
	private String strFraudScore;
	private String strUID;
	private String strPAN;
	private String strMobile;
	private String strEncTechIndicator;
	private String strTxnIdentifier;
	private String strAddAcquiringInfo;
	private String strMerchntBussType;
	private String strMacValue;
	private String strTokenId;
	private String strTokenExpDate;
	private String strTokenRedId;
	private String strWalletId;
	private String strTokenType;
	private String strTokenStatus;
	private String strPaymntAccRef;
	private String strTokenReq;
	private String strTspValidation;
	private String strDeviceType;
	private String strDeviceId;
	private String strDeviceName;
	private String strActiveTokens;
	private String strInactiveTokens;
	private String strSuspendedTokens;
	private String strMuserId;
	private String strCuserId;
	private String strAccountNumber;
	private String strEncAccountNumber;
	private String strReservefield1;
	private String strReservefield2;
	private String strReservefield3;
	private String strReservefield4;
	private String strReservefield5;
	
	
	private String fromDate;
	private String toDate;
}
