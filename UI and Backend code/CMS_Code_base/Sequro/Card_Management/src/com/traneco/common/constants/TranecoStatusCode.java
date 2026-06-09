
/**
 * @author  mithape
 * @version 1.0
 * @purpose This class is used to define Store Procedure Response Code.
 *
 * @History
 * ===============================================================================================================================================
 *     @Version         @Date           @Author                 @Purpose
 * ===============================================================================================================================================
 *     1.0                      15-01-18        Mayur I                 This class is used to define Store Procedure Response Code.
 * ===============================================================================================================================================
 *
 */
package com.traneco.common.constants;

import java.io.Serializable;

import lombok.Data;

@Data
public class TranecoStatusCode implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	public static final String SUPERADMIN = "superAdmin";
	public static final String saltKey = "ef3ddaf43bb16823582c808d2a83c5c9";
	public static final String iv = "b0fd78db1b095b2cb02c249c3ff7ceb8";
	public static final String passPhrase = "K8%gsv*()";
	public static final int iterationCount = 1000;
	public static final int keySize = 128;
	public static final String ValidatePassword = "1@46!%0*3(7^";
	
	/* SP Return Status Codes */
	public static final String STATUS_SUCCESS = "00";
	public static final String STATUS_SQL_EXCEPTION = "01";
	public static final String STATUS_DUPLICATE = "02";
	public static final String STATUS_PENDING = "03";

	/* SP In Parameter Action Codes */
	public static final String REQTYPE_ADD = "ADD";
	public static final String REQTYPE_EDIT = "EDIT";
	public static final String REQTYPE_UPDATE = "UPDATE";
	public static final String REQTYPE_DELETE = "DELETE";
	public static final String REQTYPE_VIEW = "VIEW";
	public static final String REQTYPE_APPROVE = "APPROVE";
	public static final String REQTYPE_EDIT_SEARCH = "EDIT_SEARCH";
	public static final String REQTYPE_SHOW_ROLE = "SHOW_ROLE";

	public static final String PRIMARY_FLAG = "1";

	public static final String NEW_CARD_FUNCTION = "NEWCARD";
	public static final String INSTANT_CARD_FUNCTION = "INSTANTCARD";
	public static final String REPLACEMENT_CARD_FUNCTION = "RPLCARD";
	public static final String RENEW_CARD_FUNCTION = "RNWCARD";

	public static final String RENEW_WITH_NEW_CARD_FUNCTION = "RNWCARD";
	
	public static final String EMBOSS_LINE1 = "INSTANT CARDHOLDER";
	public static final String ENCODE_FIRST = "INSTANT";
	public static final String ENCODE_LAST = "CARDHOLDER";

	/*
	 * Menu Id For Card Details
	 */

	public static final String LIMIT_MANAGEMENT = "1";
	public static final String CARD_STATUS_MANAGEMENT = "2";
	public static final String PIN_MANAGEMENT = "3";
	public static final String PLASTIC_MANAGEMENT = "4";
	public static final String SERVICE_MANAGEMENT = "5";
	public static final String MCC_MANAGEMENT = "6";

	public static final String INSTANT_CARD_MANAGEMENT = "1";
	public static final String UPDATE_INSTANT_CARD = "2";
	
	
	public static final String CREATE_CUSTOMER_ID_TYPE = "1";
	public static final String VIEW_CUSTOMER_ACCOUNTS = "2";
	public static final String CATEGORY_LIST = "3";
	public static final String CREATE_UPDATE_ACCOUNT_TYPE = "4";
	public static final String CREATE_ACCOUNT = "5";
	public static final String VIEW_ISSUED_ACCOUNT = "6";
	public static final String CREATE_INSTANT_ACCOUNT = "7";
	public static final String VIEW_AVAILABLE_INSTANT_ACCOUNT = "8";
	public static final String ACCOUNT_CREDIT_LIMIT = "9";
	
	public static final String CARD_ACCOUNT_LINKAGE = "7"; 
	public static final String VIEW_OUTSTANDING_ACCOUNT = "9";
	
	/* bin and card configuration */
	public static final String BIN_CONFIGURATION = "1";
	public static final String CARD_TYPE_CONFIGURATION = "2";
	public static final String CARD_TEMPLATE_CONFIGURATION = "3";
	public static final String CARD_PLASTIC_CONFIGURATION = "4";
	public static final String EMBOSS_CONFIGURATION = "5";
	
	
	/* branch configuration */
	public static final String BRANCH_CONFIGURATION = "1";
	public static final String BRANCH_CODE_CONFIGURATION = "2";
	public static final String ACCOUNT_TYPE_CONFIGURATION = "3";
	public static final String ADDRESS_TYPE_CONFIGURATION = "4";
	public static final String EMAIL_TYPE_CONFIGURATION = "5";
	
	/* Token Configuration */
	public static final String CARD_TOKEN_CONFIG = "1";
	public static final String MOBILE_TOKEN = "2";
	
	/* Participant Configuration */
	public static final String PARTICIPANT_CONFIG = "1";
	public static final String CONNECTION_CONFIG = "2";
	public static final String ISO_CONFIG = "3";
	
	public static final String ACCOUNT_DETAILS = "1";
	public static final String CARD_ACCOUNT_LINKAGE_DETAILS = "2";
	public static final String VIEW_LINKED_ACCOUNT_WALLETS = "3";
	
	
	//Added by prashant T for statemet view seperation
	public static final String VIEW_ACCOUNT_STATEMENT = "1";
	public static final String GLACCOUNT_STATEMENT= "2";
	
	
	
	public static final String EMBOSS_LINE = "INSTANT ACCOUNT HOLDER";
	public static final String ENCODE_FIRST_NAME = "INSTANT ACCOUNT";
	public static final String ENCODE_LAST_NAME = "ACCOUNT HOLDER";
	
	//Added by Sunny Soni for Wallet configurtion menu Start
	public static final String ADD_PARTICIPANT_WISE_WALLET = "1";
	public static final String ADD_BLOCK_MCC_CONFIG_FORM = "2";
	public static final String ADD_ACCOUNT_TYPE_WISE_WALLET = "3";
	//Added by Sunny Soni for Wallet configurtion menu Start
	
	
	//Added by Sagark for GL Account Creation menu Start
    public static final String GL_ACCOUNT_CREATION = "1";
    public static final String VIEW_GLACCOUNT_CREATION = "2";
    
       
    public static final String VIEW_GLACCOUNT_STATEMENT= "4";  //added by sagark date:08/01/2023
    //Added by Sagark fro GL Account Creation menu End
	
    //changes by prashant
    public static final String LOAD_BALANCE = "1";
    public static final String GLACCOUNT_BALANCELOADING= "2";
    
    //Added by Prashant Tayde for charing module configuration
    public static final String ADD_CHARGE_RELATED = "1";
    public static final String ADD_CHARGE_TYPE = "2";
    public static final String CHARGING_MODULE_CONFIG = "3";
    
    //Added by prashant Tayde for credit card calculation
    public static final String MCC_WISE_INTEREST = "1";
    public static final String MCC_WISE_INTERESTVIEW ="2";
    public static final String REVOLVING_CREDIT_CARD = "3";
    
    //Added change regarding to control account Start
    public static final String CONTROL_VIEW_ACCOUNT = "1";
	public static final String TRANSFER_IN_OUT = "2";
	//Added change regarding to control account End
    
    public static class JournalTransfer
    {
    	public static final String GT_TO_GL = "1";
    	public static final String GT_TO_ACCOUNT = "2";
    	public static final String ACCOUNT_TO_GL = "3";
		public static final String ACCOUNT_TO_ACCOUNT = "4";
    }

    public static class CustomerInfoService 
    {
    	public static final String CUSTOMER_SEARCH = "1";
    	public static final String CUSTOMER_DETAILS = "2";
    	public static final String ACCOUNT_DETAILS = "3";
		public static final String CARD_DETAILS = "4";
		public static final String ACCOUNT_STATEMENT = "5";
    }
    
	public static class CustomerService {
		public static final String CUSTOMER_DETAILS = "1";
		public static final String CARD_DETAILS = "2";
		public static final String ACCOUNT_DETAILS = "3";
		public static final String ADDRESS_DETAILS = "4";
		public static final String CONTACT_DETAILS = "5";
		public static final String DOCUMENT_DETAILS = "6";
	}

	public static class ControlAccount {
		public static final String CREATE_VIEW_ACCOUNT = "1";
		public static final String CREATE_NUBAN_TYPE_CONFIG = "2";
		//public static final String TRANSFER_IN_OUT = "2";
		
		public static final String TRANSFER_IN_OUT = "1";
		
	}
	
	
	public static class BulkTransfer {
		
		public static final String ONE_TO_MANY_ACCOUNTS = "1";
		public static final String MANY_TO_ONE_ACCOUNT = "2";
		public static final String GL_TO_MANY_ACCOUNTS = "3";
		public static final String MANY_TO_GL_ACCOUNT = "4";
		public static final String BULK_TRANSFER = "5";
		public static final String VERIFY_ACCOUNTS = "6";
		public static final String AUTHORIZE_ACCOUNTS = "7";
		
		
	}
	
	public static class Configuration 
	{
		/*public static final String CREATE_VIEW_ACCOUNT = "1";		
		public static final String CATEGORY_LIST = "3";
		public static final String GL_ACCOUNT_CREATION = "4";
	    public static final String VIEW_GLACCOUNT_CREATION = "5";
	    public static final String CREATE_UPDATE_ACCOUNT_TYPE = "6";
	    public static final String ACCOUNT_CREDIT_LIMIT = "7";
	    public static final String TRANSACTION_TYPE = "8";
	    */
	    
	    public static final String CREATE_VIEW_ACCOUNT = "1";
		public static final String CATEGORY_LIST = "3";
		public static final String GL_ACCOUNT_CREATION = "4";
	    public static final String VIEW_GLACCOUNT_CREATION = "5";
	    public static final String CREATE_UPDATE_ACCOUNT_TYPE = "6";
	    public static final String VIEW_ACCOUNT_TYPE = "7";
	    public static final String ACCOUNT_CREDIT_LIMIT = "8";
	    public static final String TRANSACTION_TYPE = "9";
	    public static final String VIEW_TRANSACTION_TYPE = "10";
	    public static final String VAT_TYPE_MAPPED = "11";
	    public static final String FEE_TYPE_MAPPED = "12";
	    
	}
	
	public static class CustomerAccounts 
	{
		public static final String CREATE_CUSTOMER_ID_TYPE = "1";
		public static final String VIEW_CUSTOMER_ACCOUNTS = "2";
		public static final String APPROVE_UPGRADE_TIER = "3";
		public static final String APPROVE_CUSTOMER = "4";
		public static final String CREATE_ACCOUNT = "5";
		public static final String VIEW_ISSUED_ACCOUNT = "6";
		public static final String CREATE_INSTANT_ACCOUNT = "7";
		public static final String VIEW_INSTANT_ACCOUNT = "8";
		public static final String APPROVE_ACCOUNT = "9";
	}
	
	public static class linkCards{
		public static final String CARD_ACCOUNT_LINKAGE = "1"; 
		public static final String CARD_ACCOUNT_LINKAGE_VIEW = "2";
	}
	
	public static class ReportStatement
	{
		public static final String VIEW_ACCOUNT_STATEMENT = "1";
		public static final String CHART_ACCOUNTS = "2";
		public static final String GL_ACCOUNT_STATEMENT = "3";
		public static final String VIEW_OUTSTANDING_ACCOUNT = "4";
		
		//Added changes on 05-May-2023 Start
		public static final String VIEW_TXN_REPORT="5";
	  	public static final String VIEW_JOURNAL_REPORT="6";
	  	public static final String REG_CUSTOMER_REPORT = "7";
	  	public static final String REG_CUST_WITH_LINKED_ACCOUNT = "8";
	  	public static final String CASH_WITHDRAWAL_STATEMENT = "9";
	  	public static final String CASH_DEPOSIT_STATEMENT = "10";
	  //Added changes on 05-May-2023 End
	}
	
	public static class AuditStatusCode
	{
		public static final String KEY_GENERATED = "GENERATE KEY SELECTED";
		public static final String PASSWORD_VERIFIED_CUST1 = "CUSTODIAN_1 PASSWORD VERIFIED";
		public static final String FAILED_PASSWORD_CUST1 = "CUSTODIAN_1 PASSWORD WAS WRONG";
		public static final String FAILED_PASSWORD_CUST2 = "CUSTODIAN_2 PASSWORD WAS WRONG";
		public static final String KEY_SUBMITED_CUST1  = "CUSTODIAN_1 noted key and submitted";
		public static final String KEY_SUBMITED_CUST2  = "CUSTODIAN_2 noted key and submitted";
		public static final String PASSWORD_VERIFIED_CUST2 = "CUSTODIAN_2 PASSWORD VERIFIED";
		public static final String KEY_STORED_FILE = "KEY STORED IN FILE";
		public static final String KEY_STORED_DB = "KEY STORED IN DB";	
		
		public static final String KEY_DISPLAYED_CUST1 = "KEY DISAPLAYED CUSTODAIN_1";
		public static final String KEY_DISPLAYED_CUST2 = "KEY DISAPLAYED CUSTODAIN_2";
		
		public static final String RE_ENTER_KEY = "RE_ENTER KEY SELECTED";
		public static final String ENTER_KEY_CUST1 = "Key Encryption Key 1st part entered";
		public static final String ENTER_KEY_CUST2 = "Key Encryption Key 2nd part entered";
		
	}
	
	public static class ChargingModuleService
	{
		public static final String CHARGES_APPLIED = "Charges Applied";
		public static final String CHARGES_PENDING = "Charges Pending to be applied";
	}
	
	public static class UserService
	{
		public static final String USER_ACCESS_TYPE = "DB";
	}
}
