package com.TranEco.cardManagement.common;

public class QueryConstant 
{
	public static final String SP_DebitCardIssuance = "cardIssuance";
	public static final String SP_CustomerAddittion = "customerAddition";
	public static final String SP_AddressMaintainance = "addressMaintainance";
	public static final String SP_PhoneMaintainance = "phoneMaintainance";
	public static final String SP_EmailMaintainance = "emailMaintainance";
	public static final String SP_DocumentMaintainance = "documentMaintainance";
	public static final String SP_LimitUpdate = "limitUpdate";
	public static final String SP_AccountAddittion = "accountAddittion";
	public static final String SP_AccountDelinking = "accountDelinking";
	public static final String SP_PrimaryUpdate = "primaryUpdate";
	public static final String SP_CardStatus = "cardStatus";
	public static final String SP_CutomerAddressUpdate = "cutomerAddressUpdate";
	public static final String SP_CutomerPhoneUpdate = "cutomerPhoneUpdate";
	public static final String SP_ValidateCard = "validateCard";

	public static final String GET_BIN_SUFFIX_FLAG="SELECT COUNT(*) FROM `cfg_bin` WHERE `bin`=? AND flag='Y'";	
	
	public static final String Get_Cfg_Card_Template = " SELECT id,participant_id,default_card_Status,card_type_id,service_code,card_issue_type_id, " + 
			" card_mailer_Issue_flag,pin_mailer_Issue_flag,temporary_limit_activation_flag,daily_pin_retry_limit,consecutive_pin_retry_limit,online_atm_limit, " + 
			" online_pos_limit,online_ecom_limit,offline_limit,monthly_limit,weekly_limit,daily_limit, expiry_cfg_type, " + 
			" CASE WHEN expiry_month IS NULL THEN \"0\" ELSE expiry_month END AS expiry_month , CASE WHEN expiry_year IS NULL THEN \"0\" ELSE expiry_year END AS expiry_year " + 
			" FROM cfg_card_template ";

	public static final String Get_Cfg_participant = "SELECT id,participant_name,description FROM cfg_participant";

	/*
	public static final String Get_Cfg_Card_Type = " SELECT cct.id,cct.card_type,cct.description,cp.id AS participant_id,cct.bin,cct.bin_suffix,cct.card_template_id,cct.card_plastic_id,cct.cvn_method_id,cct.pinmailer_format, cct.decimalization_table, " + 
			" ,cct.card_gen_type AS strCardGenerationType, cct.card_token as card_token FROM cfg_card_type " + 
			" cct INNER JOIN cfg_participant cp ON cct.participant_id = cp.id ";
	*/
	
	public static final String Get_Cfg_Card_Type = " SELECT cct.id,cct.card_type,cct.description,cp.id AS participant_id,cct.bin,cct.bin_suffix,cct.card_template_id,cct.card_plastic_id,cct.cvn_method_id,cct.pinmailer_format, cct.decimalization_table, "
			+" cct.card_gen_type AS strCardGenerationType, cct.card_token as card_token FROM cfg_card_type " 
			+" cct INNER JOIN cfg_participant cp ON cct.participant_id = cp.id ";

	public static final String Get_Cfg_Keys = "SELECT cct.participant_id,cct.card_type, cck.cvk_key, cck.pvk_key, cck.mdk_key FROM cfg_card_type cct"
			+ " INNER JOIN cfg_keys cck ON cct.security_key_id = cck.id";

	public static final String Get_Gfg_Bin = "SELECT cb.id,cb.bin,cb.network_scheme,cb.description,cp.participant_name AS participant_id  FROM cfg_bin cb INNER JOIN cfg_participant cp "
			+ " ON cb.id = cp.id";

	public static final String Get_Cfg_Address_Type = "SELECT id,participant_id,address_description,ext_id FROM cfg_address_type";

	public static final String Get_Cfg_Phone_Type = "SELECT id,participant_id,phone_description,ext_id FROM cfg_phone_type";

	public static final String Get_Cfg_Email_Type = "SELECT id,email_description,participant_id,ext_id FROM cfg_email_type";

	public static final String Get_Document_Type = "SELECT id,participant_id,document_type,document_description FROM cfg_document_type ";

	public static final String Get_Cfg_Account_Type = "SELECT id,Account_description AS account_description,participant_id,ext_id FROM cfg_account_type";

	public static final String Get_Cfg_Status = "SELECT id,participant_id,status_description,status_flag,ext_id,temporary_flag FROM cfg_status";

	public static final String Get_Cfg_POS_Entry_Mode = "SELECT id,participant_id,pos_entry_mode,description FROM cfg_pos_entry_mode";

	public static final String Get_Cfg_EMV_Tags = "SELECT participant_id,card_type_id,emv_tags,skip_flag  FROM cfg_emv_tags";

	public static final String CustomerAddressDelete = "DELETE from customer_address where customer_id=?";

	public static final String CustomerPhoneDelete = "DELETE from customer_contact where customer_id=?";
	
	public static final String Get_Cfg_Card_Plastic = "SELECT participant_id,card_type, vendor_id, CVV_position, servicecode_position, expiry_date_position FROM cfg_card_platic";

	public static final String Get_Cfg_Pin_Printing_Data = "SELECT participant_id,pinmailer_format,pinmailer_data FROM cfg_pin_mailer_data";

	public static final String Get_Cfg_Pin_Printing_Fields = "SELECT part_id AS participant_id,FORMAT AS pin_printing_format,sequence_no AS sequence_no,FIELD AS pinprinting_field FROM cfg_pin_printing_fields";

	public static final String Get_Search_Client_card_Name = "SELECT b.id AS id, a.id AS strCustomerID, CONCAT(a.first_name , ' ', a.last_name ) AS strCustomerName, a.citizen_id AS strCitizenID, a.cif_key AS strCIFKey,b.card_number AS strCardNumber,  c.account_number AS strAccountNumber "
			+ "FROM customer_details a LEFT JOIN card_details b ON a.id = b.customer_id "
			+ "LEFT JOIN card_account_linkage c ON b.token_card = c.card_number "
			+ "WHERE (UPPER(a.first_name) LIKE ? OR UPPER(a.last_name) LIKE ? and a.participant_id=?  ";

	public static final String Get_Search_Client_card_CardNO = "SELECT a.id AS strCustomerID, CONCAT(a.first_name , ' ', a.last_name ) AS strCustomerName, a.citizen_id AS strCitizenID, a.cif_key AS strCIFKey,b.card_number AS strCardNumber,  c.account_number AS strAccountNumber "
			+ "FROM customer_details a LEFT JOIN card_details b ON a.id = b.customer_id "
			+ "LEFT JOIN card_account_linkage c ON b.token_card = c.card_number "
			+ "WHERE (b.enc_card LIKE ? and a.participant_id=?  "; 

	public static final String Get_Search_Client_card_AccountNO = "SELECT a.id AS strCustomerID, CONCAT(a.first_name , ' ', a.last_name ) AS strCustomerName, a.citizen_id AS strCitizenID, a.cif_key AS strCIFKey,b.card_number AS strCardNumber,  c.account_number AS strAccountNumber "
			+ "FROM customer_details a LEFT JOIN card_details b ON a.id = b.customer_id "
			+ "LEFT JOIN card_account_linkage c ON b.token_card  = c.card_number "
			+ "WHERE (c.account_number LIKE ? and a.participant_id=?  ";

	public static final String Get_Search_Client_card_CIF = "SELECT a.id AS strCustomerID, CONCAT(a.first_name , ' ', a.last_name ) AS strCustomerName, a.citizen_id AS strCitizenID, a.cif_key AS strCIFKey,b.card_number AS strCardNumber,  c.account_number AS strAccountNumber "
			+ "FROM customer_details a LEFT JOIN card_details b ON a.id = b.customer_id "
			+ "LEFT JOIN card_account_linkage c ON b.token_card = c.card_number "
			+ "WHERE (a.cif_key LIKE ? and a.participant_id=?  ";

	public static final String Get_Search_Client_card_Citizen = "SELECT a.id AS strCustomerID, CONCAT(a.first_name , ' ', a.last_name ) AS strCustomerName, a.citizen_id AS strCitizenID, a.cif_key AS strCIFKey,b.card_number AS strCardNumber,  c.account_number AS strAccountNumber "
			+ "FROM customer_details a LEFT JOIN card_details b ON a.id = b.customer_id "
			+ "LEFT JOIN card_account_linkage c ON b.token_card = c.card_number "
			+ "WHERE (a.citizen_id LIKE ? and a.participant_id=?  ";
	
	public static final String Get_Search_Client_card_Type = "SELECT a.id AS strCustomerID, CONCAT(a.first_name , ' ', a.last_name ) AS strCustomerName, a.citizen_id AS strCitizenID, a.cif_key AS strCIFKey,b.card_number AS strCardNumber,  c.account_number AS strAccountNumber "
			+ "FROM customer_details a LEFT JOIN card_details b ON a.id = b.customer_id "
			+ "LEFT JOIN card_account_linkage c ON b.token_card = c.card_number "
			+ "WHERE (b.card_type = ? and a.participant_id=?  ";

	public static final String Get_Detailed_Search_CustomerID = "SELECT id AS strCustomerID, participant_id AS strParticipantID, citizen_id AS strCitizenID, cif_key AS strCIFKey, first_name AS strFirstName, middle_name AS strMiddleName, last_name AS strLastName, sex AS strGender, dob AS strDOB, mother_maiden_name AS strMotherMaidenName "
			+ "FROM customer_details WHERE participant_id = ? and id = ?";
	public static final String Get_Detailed_Search_CitizenID = "SELECT id AS strCustomerID, participant_id AS strParticipantID, citizen_id AS strCitizenID, cif_key AS strCIFKey, first_name AS strFirstName, middle_name AS strMiddleName, last_name AS strLastName, sex AS strGender, dob AS strDOB, mother_maiden_name AS strMotherMaidenName "
			+ "FROM customer_details WHERE participant_id = ? and citizen_id = ?";

	public static final String Get_Detailed_Search_CIFKey = "SELECT id AS strCustomerID, participant_id AS strParticipantID, citizen_id AS strCitizenID, cif_key AS strCIFKey, first_name AS strFirstName, middle_name AS strMiddleName, last_name AS strLastName, sex AS strGender, dob AS strDOB, mother_maiden_name AS strMotherMaidenName "
			+ "FROM customer_details WHERE participant_id = ? and cif_key = ?";

	/*public static final String Get_Card_List = " SELECT a.card_type AS strCardType, token_card AS strCardNumber, a.card_number AS strMaskCardNumber , a.member_number AS strCardSeqNumber, b.emboss_line1 AS strEmbossLine1, a.card_issue_date AS strCardIssueDate, a.expiry_date AS strExpiryDate, a.card_status AS strCardStatus, a.card_issued_user AS strCardIssuedUser " + 
			" FROM card_details a INNER JOIN card_plastic_details b ON a.token_card = b.card_number AND a.member_number=b.member_number WHERE a.participant_id = ? AND a.customer_id = ? ";
	*/
	
	public static final String Get_Card_List = " SELECT a.card_type AS strCardType, token_card AS strCardNumber, a.card_number AS strMaskCardNumber , a.member_number AS strCardSeqNumber, b.emboss_line1 AS strEmbossLine1, a.card_issue_date AS strCardIssueDate, a.expiry_date AS strExpiryDate, a.card_status AS strCardStatus, a.card_issued_user AS strCardIssuedUser " + 
			" FROM card_details a INNER JOIN card_plastic_details b ON a.token_card = b.card_number AND a.member_number=b.member_number WHERE a.participant_id = ? AND a.customer_id = ? ";

	public static final String Get_Account_List = " SELECT a.token_card AS strCardNumber, a.card_number AS strMaskCard, a.member_number AS strCardSeqNumber, b.account_number AS strAccountNumber, ct.Account_description AS strAccountType,  " + 
			" b.account_currency_code AS strCurrencyCode, b.account_branch_code AS strAccountBranch, b.account_primary_flag AS strPrimaryFlag " + 
			" FROM card_details a LEFT JOIN card_account_linkage b ON a.token_card = b.card_number AND a.member_number=b.member_number LEFT JOIN `cfg_account_type` ct ON b.`account_type`=ct.id WHERE a.participant_id = ? AND a.customer_id = ? ";

	public static final String Get_Address_List = "SELECT a.id as strAddressID, a.address_type_id AS strAddressType, b.address_description AS strAddressTypeDesc, a.address1 AS strAddress1, a.address2 AS strAddress2, a.address3 AS strAddress3, a.city AS strCity , a.state as strState , a.country_code as strCountryCode, a.pin_code AS strPinCode, a.primary_flag AS strAddressPrimaryFlag "
			+ "FROM customer_address a LEFT JOIN cfg_address_type b ON a.address_type_id = b.id  WHERE a.customer_id = ? and b.participant_id = ?";

	public static final String Get_Phone_List = " SELECT a.id AS strPhoneID, a.phone_type_id AS strPhoneType, a.phone_no AS strPhoneNo, a.primary_flag AS strPhonePrimaryFlag " + 
			" FROM customer_contact a   WHERE a.customer_id = ? and a.phone_no is not null ";

	public static final String Get_Email_List = "SELECT a.id AS strID, a.email_type_id AS strEmailType, b.email_description AS strEmailTypeDesc, "
			+ " a.email_id AS strEmailID, a.primary_flag AS strEmailPrimaryFlag "
			+ " FROM customer_email a LEFT JOIN cfg_email_type b "
			+ " ON  a.email_type_id = b.id  WHERE a.customer_id = ? AND b.participant_id = ?";
	public static final String Get_Document_List = "SELECT a.id AS strDocumentID, a.document_type_id AS strDocumentType, b.document_description "
			+ "AS strDocumentTypeDesc, a.document_number AS strDocumentNumber "
			+ "FROM customer_document a LEFT JOIN cfg_document_type b ON "
			+ " a.document_type_id = b.id  WHERE a.customer_id=? and a.document_number is not null ";

	public static final String INSERT_CARD_DETAILS = "INSERT INTO card_details (participant_id,card_type,card_number,token_card,enc_card,member_number,customer_id,service_code,card_issue_date,cardholder_since,expiry_date,card_status, "
			+ "daily_pin_retry_limit,consecutive_pin_retry_limit,online_atm_limit,online_pos_limit,online_ecom_limit,offline_limit,monthly_limit,weekly_limit,daily_limit, "
			+ "last_updated_date,card_issued_user,last_updated_user,instant_flag,order_branch,issued_to_customer) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	public static final String INSERT_CARD_PLASTIC = "INSERT INTO card_plastic_details (participant_id,card_number,member_number,card_issue_code,emboss_line1,emboss_line2,encode_first_name,encode_middle_name,encode_last_name,plastic_flag,pinmailer_flag) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String INSERT_INSTANT_CARD_DETAILS = "INSERT INTO card_details (participant_id,card_type,card_number,token_card,enc_card,member_number,customer_id,service_code,card_issue_date,cardholder_since,expiry_date,card_status, "
			+ "daily_pin_retry_limit,consecutive_pin_retry_limit,online_atm_limit,online_pos_limit,online_ecom_limit,offline_limit,monthly_limit,weekly_limit,daily_limit, "
			+ "last_updated_date,card_issued_user,last_updated_user,instant_flag,order_branch) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	
	
	public static final String Get_Pin_Printing_Data = "SELECT a.participant_id AS strParticipantID, a.card_number AS strCardNumber , a.member_number AS strMemberNo, b.citizen_id AS strCitizenID, b.cif_key AS strCIFKey, b.first_name AS strFirstName, b.middle_name AS strMiddleName, b.last_name AS strLastName, c.address1 AS strAddress1, c.address2 AS strAddress2, c.address3 AS strAddress3, "
			+ " c.city as strCity, c.state as strState, c.country_code as strCountryCode, c.pin_code as strPinCode, d.phone_no as strPhoneNo, e.email_id as strEmailID, f.document_number as strDocumentNumber from card_details a left outer join  customer_details b on a.customer_id = b.id left outer join customer_address c on a.id = c.customer_id "
			+ " left outer join customer_contact d on b.id = d.customer_id  left outer join customer_email e on b.id = e.customer_id  left outer join customer_document f on b.id = f.customer_id where a.participant_id = ? and a.card_number = ? and a.member_number = ? ";

	/*
	 * public static final String Get_Embossing_Data =
	 * "SELECT a.participant_id AS strParticipantID, a.card_number AS strCardNumber , a.member_number AS strMemberNo, a.expiry_date AS strExpiryDate, a.service_code as strServiceCode, b.emboss_line1 AS strEmbossLine1, b.emboss_line2 AS strEmbossLine2, b.encode_first_name AS strEncodeFirstName, b.encode_middle_name AS strEncodeMiddleName, b.encode_last_name AS strEncodeLastName"
	 * +
	 * "  from card_details a left outer join card_plastic_details b on a.participant_id = b.participant_id and a.card_number = b.card_number and a.member_number=b.member_number where a.participant_id = ? and a.card_number = ? and a.member_number = ? "
	 * ;
	 */

	public static final String Get_Card_Limit_Details = "SELECT a.participant_id as strPartID, a.card_number as strCardNumber , a.member_number as strCardSeqNumber, a.online_atm_limit as strOnlineATMLimit, b.online_atm_limit_used as strOnlineATMLimitUsed, a.online_pos_limit as strOnlinePOSLimit, b.online_pos_limit_used as strOnlinePOSLimitUsed, a.online_ecom_limit as strOnlineECOMLimit, b.online_ecom_limit_used as strOnlineECOMLimitUsed, "
			+ "a.offline_limit as strOfflineLimit, b.offline_limit_used as strOfflineLimitUsed,  a.monthly_limit as strMonthlyLimit, b.monthly_limit_used as strMonthlyLimit, a.weekly_limit as strWeeklyLimit , b.weekly_limit_used as strWeeklyLimitUsed, a.daily_limit as strDailyLimit, b.daily_limit_used  as strDailyLimitUsed "
			+ "FROM card_details a LEFT JOIN card_limit_statistics b ON a.participant_id = b.participant_id and a.card_number = b.card_number and a.member_number=b.member_number WHERE a.participant_id = ? and (a.enc_card='?' OR a.token_card=?)  and a.member_number= ?";

	public static final String Get_Card_Account_List = "SELECT a.card_number AS strCardNumber, a.member_number AS strCardSeqNumber, b.account_number AS strAccountNumber, b.account_type AS strAccountType, b.account_currency_code AS strCurrencyCode, b.account_branch_code as strAccountBranch, b.account_primary_flag as strPrimaryFlag "
			+ "FROM card_details a INNER JOIN card_account_linkage b ON a.card_number = b.card_number and a.member_number=b.member_number WHERE  a.participant_id = ? and a.card_number = ? and a.member_number= ?";

	public static final String Get_Card_Status_List = " SELECT a.participant_id AS strPartID,  c.card_number AS strCardNumber , a.member_number AS strCardSeqNumber, b.status_description AS strCardStatusCode, a.card_status_description AS strCardStatusDescription , a.status_change_user AS strCardStatusChangeUser, a.status_change_date AS strCardStatusChangeDate, a.card_status_description AS strCardStatusChangeMemo " + 
			" FROM card_status_history a LEFT JOIN cfg_status b ON a.status_code = b.id LEFT JOIN card_details c ON a.card_number = c.token_card WHERE a.participant_id =? AND c.token_card =? AND a.member_number=? ORDER BY a.id DESC ";
  
	 public static final String Get_Card_Details = "SELECT a.participant_id AS strPartID, a.card_type AS strCardType, a.card_number AS strCardNumber , a.token_card AS strTokenCard, a.member_number AS strCardSeqNumber, a.service_code AS strServiceCode, b.emboss_line1 AS strEmbossLine1, b.emboss_line2 AS strEmbossLine2, b.encode_first_name AS strEncodeFirstName, " + 
	 		" b.encode_middle_name AS strEncodeMiddleName, b.encode_last_name AS strEncodeLastName, a.card_issue_date AS strCardIssueDate, b.card_issue_code AS strCardIssueCode, a.cardholder_since AS strCardHolderSince, a.expiry_date AS strExpiryDate, a.new_expiry_date AS strNewExpiryDate, a.card_status AS strCardStatus, " + 
	 		" a.daily_pin_retry_limit AS strDailyPinRetryLimit,a.daily_pin_retry_count AS strDailyPinRetryCount, a.consecutive_pin_retry_limit AS strConsecutivePinRetryLimit, a.consecutive_pin_retry_count AS strConsecutivePinRetryCount, a.last_updated_date AS strLastUpdatedDate ,card_issued_user AS strCardIssuedUser , last_updated_user AS strLastUpdatedUser, " + 
	 		" b.plastic_flag AS strCardMailerIssueFlag, b.pinmailer_flag AS strPinMailerIssueFlag, b.cardmailer_issue_date AS strCardMailerIssueDate, b.pinmailer_issue_date AS strPinMailerIssueDate FROM card_details a INNER JOIN card_plastic_details b ON a.participant_id = b.participant_id AND a.token_card = b.card_number AND a.member_number=b.member_number WHERE a.participant_id =? AND (a.enc_card='?' OR a.token_card=?) AND a.member_number= ? ";
	 
	public static final String Get_Embossing_Data = "SELECT a.participant_id AS strParticipantID, a.enc_card AS strCardNo , a.token_card AS tokenCard, DATE_FORMAT(a.card_issue_date,\"%m/%y\") AS strActiveDate, a.member_number AS strMemberNumber, DATE_FORMAT(STR_TO_DATE(expiry_date,'%d-%m-%Y'),\"%m/%y\") AS strExpiryDate, a.service_code AS strServiceCode, b.emboss_line1 AS strEmbossLine1, b.emboss_line2 AS strEmbossLine2, b.encode_first_name AS strEncodeFirstName, b.encode_middle_name AS strEncodeMiddleName, b.encode_last_name AS strEncodeLastName " + 
			" FROM card_details a INNER  JOIN card_plastic_details b ON a.participant_id = b.participant_id AND a.token_card = b.card_number AND a.member_number=b.member_number AND a.emboss_flag IS NULL AND a.instant_flag=? AND a.card_type=? ";
	
	public static final String Get_Card_NCMC_Service_List = "SELECT a.participant_id AS strParticipantID, a.card_number AS strCardNumber, a.member_number AS strMemberNo, a.ncmc_service_id as strNCMCServiceId, b.active AS strActiveFlag "
			+ "  from card_ncmc_service a left outer join cfg_ncmc_service b on a.participant_id = b.participant_id and a.ncmc_service_id = b.ncmc_service_id  where a.participant_id = ? and a.card_number = ? and a.member_number = ? ";

	public static final String UPDATE_EMBOSS_FILE=" UPDATE `card_details` SET emboss_flag ='Y' WHERE token_card=? ";
	
	public static final String INSERT_STATUS_DETAILS = "INSERT INTO card_status_history (participant_id,card_number,member_number,status_change_user,status_change_date,status_code,card_status_description) VALUES(?,?,?,?,?,?,?) ";

	public static final String UPDATE_CARD_STATUS = "UPDATE card_details set card_status = ? , last_update_date = ? , last_update_user = ? where  participant_id = ? and card_number = ? and member_number= ?";

	public static final String UPDATE_PIN_RETRY_LIMIT = "UPDATE card_details set consecutive_pin_retry_limit = ? , daily_pin_retry_limit = ? , last_updated_date = ? , last_updated_user = ?  where  participant_id = ? and token_card = ? and member_number= ?";

	public static final String UPDATE_PIN_MAILER_FLAG = "UPDATE card_plastic_details set pinmailer_flag = ? , last_update_date = ? , last_update_user = ? where  participant_id = ? and card_number = ? and member_number= ?";

	public static final String DELETE_CARD_SERVICE = "DELETE from card_ncmc_service  where  participant_id = ? and card_number = ? and member_number= ? ";
	
	public static final String GET_CARD_DETAIL="SELECT * FROM card_details WHERE participant_id=? AND token_card=? AND member_number=?";
	
	public static final String INSERT_CARD_DETAILS_RPLCARD="INSERT INTO card_details(participant_id,card_type,card_number,token_card,enc_card,member_number,customer_id, " + 
			" service_code,card_issue_date,cardholder_since, expiry_date,card_status,pin_offset,daily_pin_retry_limit,daily_pin_retry_count, " + 
			" consecutive_pin_retry_limit,consecutive_pin_retry_count,online_atm_limit,online_pos_limit,online_ecom_limit, " + 
			" offline_limit,monthly_limit, weekly_limit , daily_limit ,last_updated_date ,card_issued_user , last_updated_user, instant_flag, order_branch ) " + 
			" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	
	public static final String INSERT_CARD_DETAILS_RNWCARD="INSERT INTO card_details(participant_id,card_type,card_number,token_card,enc_card,member_number,customer_id, " + 
			" service_code,card_issue_date,cardholder_since, expiry_date,card_status,pin_offset,daily_pin_retry_limit,daily_pin_retry_count, " + 
			" consecutive_pin_retry_limit,consecutive_pin_retry_count,online_atm_limit,online_pos_limit,online_ecom_limit, " + 
			" offline_limit,monthly_limit, weekly_limit , daily_limit ,last_updated_date ,card_issued_user , last_updated_user, instant_flag, order_branch ) " + 
			" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	
	public static final String INSERT_CARD_PLASTIC_RPLCARD="INSERT INTO card_plastic_details (participant_id, card_number, member_number, card_issue_code, emboss_line1, " + 
			" emboss_line2, encode_first_name,encode_middle_name, encode_last_name, plastic_flag, pinmailer_flag) " + 
			" VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
	
	public static final String GET_CARD_STATUS="SELECT card_status FROM card_details WHERE participant_id=? AND token_card=? AND member_number=?";
	
	public static final String UPDATE_CARD_EXP="UPDATE card_details SET new_expiry_date=? WHERE participant_id=? AND token_card=? AND member_number=?";

	public static final String INSERT_CUST_DETAILS="INSERT INTO `customer_details` (participant_id) VALUES (?)";
	
	public static final String INSERT_CUST_ADD="INSERT INTO `customer_address` (customer_id) VALUES (?)";
	
	public static final String INSERT_CUST_CONTACT="INSERT INTO `customer_contact` (customer_id) VALUES (?)";
	
	public static final String INSERT_CUST_DOCUMENT="INSERT INTO `customer_document` (customer_id) VALUES (?)";
	
	public static final String INSERT_CUST_EMAIL="INSERT INTO `customer_email` (`customer_id`) VALUES (?)";
	
	public static final String CHECK_RECORD_PRESENT = " SELECT COUNT(*) FROM card_details WHERE participant_id = ? AND enc_card LIKE ? ";
	 
	public static final String CHECK_LAST_CARD_NO = " SELECT MAX(enc_card) FROM card_details WHERE participant_id = ? AND enc_card LIKE ? ";
	
	public static final String GET_SCHEDULE_CARD_TOKEN = " SELECT id, cron, card_type AS cardType, length FROM `cfg_card_token` ";
	
	public static final String GET_CARD_ID = " SELECT id FROM `card_details` WHERE card_type = ? ";
	
	public static final String GET_CARD_TOKEN = " SELECT id, card_id, token_details FROM card_token WHERE card_type = ? ";
	
	public static final String INSERT_CARD_TOKEN = " INSERT INTO `card_token` (card_id, card_type, token_details) VALUES (?,?,?)  ";
	
	public static final String UPDATE_CARD_TOKEN = " UPDATE `card_token` SET token_details = ? WHERE card_id = ? ";
	
	public static final String GET_TOKEN = " SELECT ct.token_details FROM card_details AS cd INNER JOIN card_token AS ct ON cd.id = ct.card_id WHERE enc_card = ? ";
	
	public static final String FIND_CARD_TOKEN = " SELECT card.id AS id, card.card_type AS cardType, cct.length FROM `card_details` AS card INNER JOIN `cfg_card_type` AS cfg ON card.card_type = cfg.id LEFT JOIN `cfg_card_token` cct ON cfg.id = cct.card_type  WHERE card.enc_card = ? AND cfg.card_token = 1 ";
	
	public static final String GET_MOBILE_TOKEN = " SELECT token FROM `mobile_token` WHERE mobile = ?  ";
	
	public static final String GET_MOBILE_TOKEN_SCHEDULER = " SELECT `length`, onDemand, cron, description FROM `cfg_mobile_token` WHERE mobile = ?  "; 
	
	public static final String ADD_MOBILE_TOKEN = " INSERT INTO `mobile_token` (mobile, token) VALUES (?,?) ";
	
	public static final String ADD_MOBILE_CFG_TOKEN = " INSERT INTO `cfg_mobile_token` (mobile, `length`, onDemand, cron, description) VALUES (?,?,?,?,?) ";
	
	public static final String UPDATE_MOBILE_TOKEN = " UPDATE `mobile_token` SET token = ? WHERE mobile = ? ";
	
	public static final String GET_ALL_MOBILE_TOKEN = " SELECT id AS id, mobile AS mobile, `length` AS strLength, onDemand AS strDemandFlag, cron AS strDemondCronExpr, description AS enDesc FROM `cfg_mobile_token` ";
	
	public static final String GET_PLASTIC_DETAILS = " SELECT * FROM `card_plastic_details` WHERE card_number = ? AND participant_id = ? ";
	
	public static final String INSERT_PLASTIC_DETAILS = " INSERT INTO `card_plastic_details` (participant_id, card_number, member_number, card_issue_code, emboss_line1, emboss_line2, encode_first_name, encode_middle_name, encode_last_name, plastic_flag, pinmailer_flag) " + 
			" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	
	public static final String INSERT_TRANSACTION_TABLE = "INSERT into transaction(uuid, transaction_time, request_type, transaction_type, transaction_status, transaction_result, acquirer_id, issuer_id , transaction_ref_no) values(?, now() ,?,?,?,?,?,?,?)";

	public static final String INSERT_TRANSACTION_DETAILS_TABLE = "INSERT into transaction_details(transaction_id, field, value) values(?,?,?)";

	public static final String UPDATE_TRANSACTION_RESULT = "UPDATE transaction set transaction_status = ? , transaction_result = ? where id = ?";
	
	public static final String UPDATE_TRANSACTION_STATUS = "UPDATE transaction_details set transaction_status = ?  where id = ?";
	
	public static final String UPDATE_PARTICIPANT_NODE = " update participant_node_status SET node_status_id = ? , last_status_change = ? WHERE participant_id = ? ";
	
	public static final String GET_CFG_INTERFACE = " SELECT id, handler_id, participant_id, key_type, key_name, key_value FROM interface_config ";
	
	public static final String GET_INTERFACE_KEY = " SELECT key_type, description FROM cfg_interface_key ";
	
	public static final String INSERT_TXN = " INSERT INTO transaction_gatekeeper (handler_id, `uuid`, unique_msg, process_dt, transaction_class, internalData, external_data, request_flag) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
	
	public static final String GET_TXN_ROUTING = " SELECT id, acq_partid, bin_start, bin_end, participant_id, cms_participant_id, routing_priority, routing_rule  from transaction_routing ";

	public static final String CHECK_CARD_COUNT_CARD_STATUS = " SELECT COUNT(*) from card_details where participant_id=? AND token_card=? AND member_number=? ";
	
	public static final String CHECK_CFG_STATUS_EXIST = "SELECT id AS id FROM cfg_status WHERE id=? ";

	public static final String UPDATE_CARD_DETAILS = "UPDATE card_details SET card_status = ? WHERE token_card = ? AND participant_id = ? ";
	
	public static final String INSERT_CARD_STATUS_HISTORY = "INSERT INTO card_status_history (participant_id,card_number,member_number,status_change_user,status_change_date,status_code,card_status_changed_date,card_status_description) "
			+ "	VALUES (?,?,?,?,?,?,?,?) ";
			//+ "	VALUES (?,?,?,?,NOW(),?,NOW(),?) ";
	
	public static final String GET_KEY_PHRASE = "SELECT key_salt AS keySalt, key_phrase AS keyPhrase, value FROM card_encrypt_key WHERE id = '1' ";

	public static final String GET_RSA_KEY = "SELECT publicKey AS publicKey , privateKey AS privateKey FROM rsakeys WHERE id = ? ";

	public static final String GET_ENC_DEC_VALUE = "SELECT id AS id , value AS value, created_date AS createdDate, created_by AS created_by FROM card_encrypt_key ";
	
	public static final String GET_CARD_DATA = "SELECT card_number AS strCardNumber, token_card AS strTokenCard FROM card_details WHERE id = '229' ";

}
