package com.TranEco.cardManagement.common;

public class Constant 
{
	public class CMS_Constants 
	{
		public static final String INVALID_CARD_TYPE = "01";
		public static final String INVALID_CARD_BIN = "02";
		public static final String INVALID_ADDRESS_TYPE = "03";
		public static final String INVALID_PHONE_TYPE = "04";
		public static final String INVALID_EMAIL_TYPE = "05";
		public static final String INVALID_ACCOUNT_TYPE = "06";
		public static final String INVALID_CUSTOMER_ID = "07";
		public static final String INVALID_DOCUMENT_TYPE = "08";
		public static final String DUPLICATE_CITIZEN_ID = "10";
		public static final String INVALID_FUNCTION_TYPE = "11";
		public static final String INVALID_INPUT_DATA = "12";
		public static final String INVALID_CARD_TEMPLATE = "13";
		public static final String UNABLE_TO_MODIFY_PERMANENT_STATUS = "15";
		public static final String CARD_FORMAT = "####-XXXX-XXXX-####";
		
		public static final String KEY = "CMS123";
	}

	public class CardAuthConstant 
	{
		public static final String PURCHASE = "00";
		public static final String PURCHASE_WITH_CASHBACK = "09";
		public static final String CASH_POS = "01";
		public static final String OFFLINE_DEBIT_POST = "00";
		public static final String MONEY_ADD = "28";
		public static final String SERVICE_CREATION = "83";
		public static final String BALANCE_INQUIRY = "31";
	}
	
	public class Card_Authentication_RC_Constants 
	{
		public static final String INVALID_CARD = "1001";
		public static final String INVALID_CARD_STATUS = "1002";
		public static final String INVALID_CARD_EXPIRY = "1003";
		public static final String INVALID_CVV = "1004";
		public static final String INVALID_PINBLOCK = "1005";
		public static final String INVALID_EMVTAGS = "1006";
		public static final String INVALID_ARQC = "1007";
		public static final String INVALID_REQUEST_TYPE = "1011";
		public static final String INVALID_TRANSACTION_TYPE = "1008";
	}
	
	public class Transaction_Status {
		public static final String INFLIGHT = "0";
		public static final String SENT_TO_ISSUER = "1";
		public static final String RECIEVED_RESPONSE_BACK_FROM_ISSUER = "2";
		public static final String COMPLETED = "4";
	}
	
	public class EMV_Tags_for_ARQC_Validation 
	{
		public static final String EMV_TAG_9F02 = "9F02";
		public static final String EMV_TAG_9F03 = "9F03";
		public static final String EMV_TAG_9F1A = "9F1A";
		public static final String EMV_TAG_95 = "95";
		public static final String EMV_TAG_5F2A = "5F2A";
		public static final String EMV_TAG_9A = "9A";
		public static final String EMV_TAG_9C = "9C";
		public static final String EMV_TAG_9F37 = "9F37";
		public static final String EMV_TAG_82 = "82";
		public static final String EMV_TAG_9F36 = "9F36";
		public static final String EMV_TAG_9F10 = "9F10";
		public static final String EMV_TAG_9F26 = "9F26";
	}
	
	public class Pin_Printing_Fields
	{
		public static final String PMDESC = "PMDESC";
		public static final String PMCITY = "PMCITY";
		public static final String PMCSIT = "PMCSIT";
		public static final String PMCZIP = "PMCZIP";
		public static final String CARD = "@CARD";
	}
	
	public class Emboss_File 
	{
		public static final String STATIC = "S";
		public static final String DYNAMIC = "D";
		public static final String TAB = "TAB";
	}
}
