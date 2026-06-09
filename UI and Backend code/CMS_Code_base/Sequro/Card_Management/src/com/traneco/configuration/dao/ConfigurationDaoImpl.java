package com.traneco.configuration.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.traneco.clientSearch.model.AddressDetailsList;
import com.traneco.clientSearch.model.CardDetails;
import com.traneco.clientSearch.model.CardDetailsList;
import com.traneco.clientSearch.model.CardStatus;
import com.traneco.clientSearch.model.CustomerDetails;
import com.traneco.clientSearch.model.DocumentDetailsList;
import com.traneco.clientSearch.model.EmailDetailsList;
import com.traneco.clientSearch.model.PhoneDetailsList;
import com.traneco.cmsaudit.services.AuditService;
import com.traneco.common.KeyValueBean;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.QueryConstant;
import com.traneco.common.util.AESEncDec;
import com.traneco.configuration.model.AccountCreditLimitCategory;
import com.traneco.configuration.model.AccountDetails;
import com.traneco.configuration.model.AccountInterestMaster;
import com.traneco.configuration.model.AccountLinkedWalletMaster;
import com.traneco.configuration.model.AccountNumberModel;
import com.traneco.configuration.model.AccountTranMaster;
import com.traneco.configuration.model.AccountTransactionLimitModel;
import com.traneco.configuration.model.AccountTypeMaster;
import com.traneco.configuration.model.AccountTypeModel;
import com.traneco.configuration.model.AddressProofDocumentTypeMaster;
import com.traneco.configuration.model.AddressTypeModel;
import com.traneco.configuration.model.BalanceUpdateInAccountMaster;
import com.traneco.configuration.model.BinModel;
import com.traneco.configuration.model.BranchCodeModel;
import com.traneco.configuration.model.BranchTypeModel;
import com.traneco.configuration.model.CardNoModel;
import com.traneco.configuration.model.CardPlasticModel;
import com.traneco.configuration.model.CardTemplateModel;
import com.traneco.configuration.model.CardTokenModel;
import com.traneco.configuration.model.CardType;
import com.traneco.configuration.model.CardTypeModel;
import com.traneco.configuration.model.CategoryListModel;
import com.traneco.configuration.model.Channels;
import com.traneco.configuration.model.ChargesTypeModel;
import com.traneco.configuration.model.CityModel;
import com.traneco.configuration.model.ConnectionConfigModel;
import com.traneco.configuration.model.CountryModel;
import com.traneco.configuration.model.EmailTypeModel;
import com.traneco.configuration.model.EmbossConfigModel;
import com.traneco.configuration.model.GLAccountLoadingMaster;
import com.traneco.configuration.model.GLAccountStatement;
import com.traneco.configuration.model.GlAccountTypeChargesMaster;
import com.traneco.configuration.model.IdentityProofDocumentTypeMaster;
import com.traneco.configuration.model.InstantAccountCreation;
import com.traneco.configuration.model.IsoConfigModel;
import com.traneco.configuration.model.KeyConfigModel;
import com.traneco.configuration.model.LoadBalanceModel;
import com.traneco.configuration.model.MccCodeModel;
import com.traneco.configuration.model.MccWiseInterestModel;
import com.traneco.configuration.model.MobileTokenModel;
import com.traneco.configuration.model.NcmcServiceModel;
import com.traneco.configuration.model.ParticipantConfigModel;
import com.traneco.configuration.model.PhoneTypeModel;
import com.traneco.configuration.model.StateModel;
import com.traneco.configuration.model.TaxConfigModel;
import com.traneco.configuration.model.TranMaster;
import com.traneco.configuration.model.TransactionTypeModel;
import com.traneco.configuration.model.UpdateGLAccount;
import com.traneco.configuration.model.cardAcntLinkageMaster;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.service.model.AccountCreation;
import com.traneco.service.model.AccountStatement;
import com.traneco.service.model.AccountTransactionLimitation;
import com.traneco.service.model.BulkUploadData;
import com.traneco.service.model.CardAccountLinkage;

@Repository
public class ConfigurationDaoImpl implements ConfigurationDao 
{
	@Autowired
	JdbcTemplate jdbcCMSTemplate;

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	@Autowired
	SessionDTO sessionDTO;
	
	@Autowired
	AESEncDec aesEncDec;
	
	@Autowired
	AuditService auditService;
	
	@Autowired
	ConfigurationService configurationService;

	public int addBin(BinModel binModel) 
	{
		int count = this.jdbcCMSTemplate.update("INSERT INTO cfg_bin (bin,description,participant_id,network_scheme,flag) VALUES (?,?,?,?,?)",
						new Object[] { binModel.getStrBin(), binModel.getStrBinDesc(),
								this.sessionDTO.getParticipantid(), binModel.getStrNetworkScheme(),
								(binModel.getFlag() == null) ? "N" : "Y" });					
		
		return count;
	}

	public List<BinModel> getBin() 
	{
		List<BinModel> binList = this.jdbcCMSTemplate.query(
				"SELECT bin AS strBin,description AS strBinDesc, id AS strID, network_scheme as strNetworkScheme, flag as flag FROM cfg_bin WHERE participant_id = ?",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(BinModel.class));
		return binList;
	}

	public List<CountryModel> getCountry() 
	{
		List<CountryModel> countryList = this.jdbcCMSTemplate.query(
				"SELECT id AS strID, country_name AS strCountryName, shortname AS strShortName FROM cfg_country  ",
				new Object[0], (RowMapper) new BeanPropertyRowMapper(CountryModel.class));
		System.out.println("ConfigurationDaoImpl.getCountry()" + countryList);
		return countryList;
	}

	public int addCountry(CountryModel countryModel) 
	{
		int count = this.jdbcCMSTemplate
				.update("INSERT INTO cfg_country (country_name,status,participant_id) VALUES (?,?,?)", new Object[] {
						countryModel.getStrCountryName(), Integer.valueOf(1), this.sessionDTO.getParticipantid() });
		return count;
	}

	public List<StateModel> getState() {
		List<StateModel> stateList = this.jdbcCMSTemplate.query(
				" SELECT cs.id AS strID, cc.id AS strCountryID, cs.state_name AS strStateName FROM cfg_state cs LEFT JOIN cfg_country cc ON cs.country_id = cc.id WHERE cs.country_id = ?  ",
				new Object[0], (RowMapper) new BeanPropertyRowMapper(StateModel.class));
		return stateList;
	}

	public List<StateModel> getStateData(String countryCode) {
		List<StateModel> stateList = this.jdbcCMSTemplate.query(
				" SELECT cs.id AS strID, cc.id AS strCountryID, cs.state_name AS strStateName FROM cfg_state cs LEFT JOIN cfg_country cc ON cs.country_id = cc.id WHERE cs.country_id = ?  ",
				new Object[] { countryCode }, (RowMapper) new BeanPropertyRowMapper(StateModel.class));
		return stateList;
	}

	public List<CityModel> getCityData(String state) {
		List<CityModel> cityList = this.jdbcCMSTemplate.query(
				" SELECT cs.id AS strID, cc.id AS strStateID, cs.city_name AS strCityName FROM cfg_city cs LEFT JOIN cfg_state cc ON cs.state_id = cc.id WHERE cs.state_id = ?  ",
				new Object[] { state }, (RowMapper) new BeanPropertyRowMapper(CityModel.class));
		return cityList;
	}

	public int addState(StateModel stateModel) 
	{
		int count = this.jdbcCMSTemplate.update(
				"INSERT INTO cfg_state (country_id,state_name,status,participant_id) VALUES (?,?,?,?)",
				new Object[] { stateModel.getStrCountryID(), stateModel.getStrStateName(), Integer.valueOf(1),
						this.sessionDTO.getParticipantid() });
		return count;
	}

	public List<BranchTypeModel> getBranch() 
	{
		List<BranchTypeModel> branchList = this.jdbcCMSTemplate.query(
				"SELECT id AS strID,branch_name AS strBranchName,branch_desc AS strBranchDesc FROM cfg_branch WHERE participant_id=?",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(BranchTypeModel.class));
		return branchList;
	}

	public int addBranch(BranchTypeModel branchTypeModel) 
	{
		int count = this.jdbcCMSTemplate.update(
				"INSERT INTO cfg_branch (branch_name,branch_desc,participant_id) VALUES (?,?,?)",
				new Object[] { branchTypeModel.getStrBranchName(), branchTypeModel.getStrBranchDesc(),
						this.sessionDTO.getParticipantid() });
		return count;
	}

	public List<BranchCodeModel> getBranchCode() {
		List<BranchCodeModel> branchCodeList = this.jdbcCMSTemplate.query(
				" SELECT id AS strID,branch_code AS strBranchCode,branch_Address AS strBranchAddress,branch_type AS strBranchType,ext_id AS strExtID FROM cfg_branch_code  WHERE participant_id=?",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(BranchCodeModel.class));
		return branchCodeList;
	}

	public int addBranchCode(BranchCodeModel branchCodeModel) {
		int count = this.jdbcCMSTemplate.update(
				"INSERT INTO cfg_branch_code (branch_code,branch_Address,branch_type,ext_id,participant_id) VALUES(?,?,?,?,?)",
				new Object[] { branchCodeModel.getStrBranchCode(), branchCodeModel.getStrBranchAddress(),
						branchCodeModel.getStrBranchType(), branchCodeModel.getStrExtID(),
						this.sessionDTO.getParticipantid() });
		return count;
	}

	public List<AccountTypeModel> getAccount() {
		List<AccountTypeModel> accountList = this.jdbcCMSTemplate.query(
				" SELECT id AS strID,Account_description AS strAccountDesc,participant_id AS strPart,ext_id AS strExt FROM cfg_account_type WHERE participant_id=?",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(AccountTypeModel.class));
		return accountList;
	}

	public int addAccount(AccountTypeModel accountTypeModel) {
		int count = this.jdbcCMSTemplate.update(
				" INSERT INTO cfg_account_type (Account_description,participant_id,ext_id) VALUES (?,?,?)",
				new Object[] { accountTypeModel.getStrAccountDesc(), this.sessionDTO.getParticipantid(),
						accountTypeModel.getStrExt() });
		return count;
	}

	public List<AddressTypeModel> getAddress() 
	{
		List<AddressTypeModel> accountList = this.jdbcCMSTemplate.query(
				" SELECT id AS strID,address_description AS strAddressDesc,participant_id AS strPart,ext_id AS strExt FROM cfg_address_type WHERE participant_id=? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(AddressTypeModel.class));
		return accountList;
	}

	public int addAddress(AddressTypeModel addressTypeModel) 
	{
		int count = this.jdbcCMSTemplate.update(
				" INSERT INTO cfg_address_type (address_description,participant_id,ext_id) VALUES (?,?,?) ",
				new Object[] { addressTypeModel.getStrAddressDesc(), this.sessionDTO.getParticipantid(),
						addressTypeModel.getStrExt() });
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<CardTypeModel> getCardType() 
	{
		//For Sequro
		/*
		 * List<CardTypeModel> accountList = this.jdbcCMSTemplate.query(
		 * " SELECT cct.card_type AS strCardType, cct.description AS strCardDesc, " +
		 * "cct.participant_id AS strPartID   , cct.bin AS strBin, cct.bin_suffix AS strBinSuffix, "
		 * + "cct.cvn_method_id AS strCVN, ce.endpoint AS strEndpoint,  " +
		 * "cct.pinmailer_format AS strPinFormat, cct.decimalization_table AS strDecTable, "
		 * +
		 * "cct.id AS strID, cct.ncmc_flag AS strFlag, cct.card_gen_type as strCardGenerationType "
		 * +
		 * "FROM cfg_card_type cct LEFT JOIN cfg_endpoint ce ON cct.endpoint = ce.id WHERE participant_id=? "
		 * , new Object[] { this.sessionDTO.getParticipantid() }, (RowMapper) new
		 * BeanPropertyRowMapper(CardTypeModel.class)); return accountList;
		 */
		
		//For Montra  SEQ233470000001
		
		List<CardTypeModel> accountList = this.jdbcCMSTemplate.query(
				" SELECT cct.card_type AS strCardType, cct.description AS strCardDesc, "
				+ "cct.participant_id AS strPartID   , cct.bin AS strBin, cct.bin_suffix AS strBinSuffix, "
				+ "cct.cvn_method_id AS strCVN, ce.endpoint AS strEndpoint,  "
				+ "cct.pinmailer_format AS strPinFormat, cct.decimalization_table AS strDecTable, "
				+ "cct.id AS strID, cct.ncmc_flag AS strFlag, cct.card_gen_type as strCardGenerationType "
				+ "FROM cfg_card_type cct LEFT JOIN cfg_endpoint ce ON cct.endpoint = ce.id WHERE participant_id=? ",
				new Object[] { "SEQ233470000001" },
				(RowMapper) new BeanPropertyRowMapper(CardTypeModel.class));
		return accountList;
	}

	/*
	 * public int addCardType(CardTypeModel cardTypeModel) { GeneratedKeyHolder
	 * generatedKeyHolder = new GeneratedKeyHolder(); try {
	 * this.jdbcCMSTemplate.update((PreparedStatementCreator) new Object(),
	 * (KeyHolder) generatedKeyHolder);
	 * System.out.println("generatedKeyHolder::["+generatedKeyHolder+"]"); return
	 * generatedKeyHolder.getKey().intValue(); } catch (Exception e) {
	 * e.printStackTrace(); } return 1; }
	 */
	
	
	//changes added by prashant Tayde
	// For Montra Account Type Added 
	
	public int addCardType(CardTypeModel cardTypeModel) {
		int count = this.jdbcCMSTemplate.update(
				" INSERT INTO cfg_card_type (card_type,description,account_type,account_description,participant_id,bin,bin_suffix, "
						+ "cvn_method_id,pinmailer_format,decimalization_table,ncmc_flag,endpoint,card_gen_type,card_token) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
				new Object[] { cardTypeModel.getStrCardType(), cardTypeModel.getStrCardDesc(),
						cardTypeModel.getStrAccountType(), cardTypeModel.getStrAccountTypeDesc(),
						this.sessionDTO.getParticipantid(), cardTypeModel.getStrBin(), cardTypeModel.getStrBinSuffix(),
						"0", "0", "0", cardTypeModel.getStrFlag(), cardTypeModel.getStrEndpoint(),
						cardTypeModel.getStrCardGenerationType(), cardTypeModel.getICardTokenFlag() });
		return count;
	}
	 

	//changes added by prashant Tayde
		// For Montra Account Type Added 
	/*
	 * public int addCardType(CardTypeModel cardTypeModel) { int count =
	 * this.jdbcCMSTemplate.update(
	 * " INSERT INTO cfg_card_type (card_type,description,participant_id,bin,bin_suffix, "
	 * +"cvn_method_id,pinmailer_format,decimalization_table,ncmc_flag,endpoint,card_gen_type,card_token) VALUES (?,?,?,?,?,?,?,?,?,?,?,?) "
	 * , new Object[] { cardTypeModel.getStrCardType(),
	 * cardTypeModel.getStrCardDesc(),
	 * this.sessionDTO.getParticipantid(),cardTypeModel.getStrBin(),cardTypeModel.
	 * getStrBinSuffix(),"0","0","0",
	 * cardTypeModel.getStrFlag(),cardTypeModel.getStrEndpoint(),cardTypeModel.
	 * getStrCardGenerationType(), cardTypeModel.getICardTokenFlag() }); return
	 * count; }
	 */
	
	public List<CardTemplateModel> getCardTemplate() {
		List<CardTemplateModel> accountList = this.jdbcCMSTemplate.query(
				" SELECT cct.id AS strID, cct.card_type_id AS strCardType, CONCAT(ct.card_type,' : ',ct.description) AS typeDesc, cct.service_code AS strServiceCode,  cct.card_issue_type_id AS strCardIssue, cct.card_mailer_Issue_flag AS strCardMailerFlag,  cct.pin_mailer_Issue_flag AS strPinMailerFlag, cct.temporary_limit_activation_flag AS strTempLimitFlag,  cct.daily_pin_retry_limit AS strDailyPinLimit, cct.consecutive_pin_retry_limit AS strConPinLimit,  cct.online_atm_limit AS strOnlineAtmLimit, cct.online_pos_limit AS strOnlinePosLimit,  cct.online_ecom_limit AS strOnlineEcomLimit, cct.offline_limit AS strOfflineLimit,  cct.monthly_limit AS strMonthlyLimit, cct.weekly_limit AS strWeeklyLimit,cct.daily_limit AS strDailyLimit,cct.expiry_cfg_type as strExpiryType, case when cct.expiry_cfg_type = 'M' then cct.expiry_month else cct.expiry_year end as strExpireValue  FROM cfg_card_template cct INNER JOIN cfg_card_type ct ON cct.card_type_id=ct.id WHERE cct.participant_id=?",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(CardTemplateModel.class));
		return accountList;
	}

	public int addCardTemplate(CardTemplateModel cardTemplateModel) 
	{
		int month = 0, year = 0;
		if ("M".equals(cardTemplateModel.getStrExpiryType())) 
		{
			month = Integer.parseInt(
					(cardTemplateModel.getStrExpireValue() == null) ? "0" : cardTemplateModel.getStrExpireValue());
		} 
		else 
		{
			year = Integer.parseInt(
					(cardTemplateModel.getStrExpireValue() == null) ? "0" : cardTemplateModel.getStrExpireValue());
		}
		int count = this.jdbcCMSTemplate.update(
				"INSERT INTO cfg_card_template (card_type_id , service_code ,  card_issue_type_id , card_mailer_Issue_flag ,  pin_mailer_Issue_flag , temporary_limit_activation_flag ,  daily_pin_retry_limit , consecutive_pin_retry_limit ,  online_atm_limit , online_pos_limit ,  online_ecom_limit , offline_limit ,  monthly_limit , weekly_limit ,daily_limit, participant_id,expiry_cfg_type,expiry_month,expiry_year)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
				new Object[] { cardTemplateModel.getStrCardType(), cardTemplateModel.getStrServiceCode(),
						(cardTemplateModel.getStrCardIssue() == null) ? "0" : cardTemplateModel.getStrCardIssue(),
						cardTemplateModel.getStrCardMailerFlag(), cardTemplateModel.getStrPinMailerFlag(),
						cardTemplateModel.getStrTempLimitFlag(), cardTemplateModel.getStrDailyPinLimit(),
						cardTemplateModel.getStrConPinLimit(), cardTemplateModel.getStrOnlineAtmLimit(),
						cardTemplateModel.getStrOnlinePosLimit(), cardTemplateModel.getStrOnlineEcomLimit(),
						cardTemplateModel.getStrOfflineLimit(), cardTemplateModel.getStrMonthlyLimit(),
						cardTemplateModel.getStrWeeklyLimit(), cardTemplateModel.getStrDailyLimit(),
						this.sessionDTO.getParticipantid(), cardTemplateModel.getStrExpiryType(),
						Integer.valueOf(month), Integer.valueOf(year) });
		return count;
	}

	public List<CardPlasticModel> getCardPlastic() {
		List<CardPlasticModel> accountList = this.jdbcCMSTemplate.query(
				"  SELECT ccp.id AS strID , ccp.card_type_id AS strCardType, CONCAT(ct.card_type,' : ',ct.description) AS typeDesc, ccp.vendor_id AS strVendorID, ccp.CVV_position AS strCVV,  ccp.servicecode_position AS strServicePos, ccp.expiry_date_position AS strExpPos, ccp.card_seq_position AS strSeqPos  FROM cfg_card_plastic ccp INNER JOIN cfg_card_type ct ON ccp.card_type_id=ct.id WHERE ccp.participant_id=?  ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(CardPlasticModel.class));
		return accountList;
	}

	public int addCardPlastic(CardPlasticModel cardPlasticModel) {
		int count = this.jdbcCMSTemplate.update(
				" INSERT INTO cfg_card_plastic (participant_id,card_type_id,vendor_id,CVV_position,servicecode_position,expiry_date_position,  card_seq_position) VALUES (?,?,?,?,?,?,?) ",
				new Object[] { this.sessionDTO.getParticipantid(), cardPlasticModel.getStrCardType(),
						cardPlasticModel.getStrVendorID(), cardPlasticModel.getStrCVV(),
						cardPlasticModel.getStrServicePos(), cardPlasticModel.getStrExpPos(),
						cardPlasticModel.getStrSeqPos() });
		return count;
	}

	public List<EmailTypeModel> getEmail() {
		List<EmailTypeModel> emailList = this.jdbcCMSTemplate.query(
				" SELECT id AS strID,email_description AS strEmail,participant_id AS strPart,ext_id AS strExt FROM cfg_email_type WHERE participant_id=? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(EmailTypeModel.class));
		return emailList;
	}

	public int addEmail(EmailTypeModel emailTypeModel) {
		int count = this.jdbcCMSTemplate.update(
				" INSERT INTO cfg_email_type (email_description,participant_id,ext_id) VALUES (?,?,?) ", new Object[] {
						emailTypeModel.getStrEmail(), this.sessionDTO.getParticipantid(), emailTypeModel.getStrExt() });
		System.out.println("ConfigurationDaoImpl.addEmail()" + emailTypeModel + this.sessionDTO.getParticipantid());
		return count;
	}

	public List<PhoneTypeModel> getPhone() {
		List<PhoneTypeModel> phoneList = this.jdbcCMSTemplate.query(
				" SELECT id AS strID,phone_description AS strPhone,participant_id AS strPart,ext_id AS strExt FROM cfg_phone_type WHERE participant_id=? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(PhoneTypeModel.class));
		return phoneList;
	}

	public int addPhone(PhoneTypeModel phoneTypeModel) 
	{
		int count = this.jdbcCMSTemplate.update(
				" INSERT INTO cfg_phone_type (phone_description,participant_id,ext_id) VALUES (?,?,?) ", new Object[] {
						phoneTypeModel.getStrPhone(), this.sessionDTO.getParticipantid(), phoneTypeModel.getStrExt() });
		return count;
	}

	public List<NcmcServiceModel> getNcmc() 
	{
		List<NcmcServiceModel> ncmcList = this.jdbcCMSTemplate.query(
				" SELECT id AS strID,ncmc_code AS strNcmcID,participant_id AS strPart, active AS strFlag FROM cfg_ncmc_code WHERE participant_id=? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(NcmcServiceModel.class));
		return ncmcList;
	}

	public int addNcmcService(NcmcServiceModel ncmcServiceModel) 
	{
		int count = this.jdbcCMSTemplate.update(
				" INSERT INTO cfg_ncmc_code (ncmc_code,participant_id,active) VALUES (?,?,?) ",
				new Object[] { ncmcServiceModel.getStrNcmcID(), this.sessionDTO.getParticipantid(),
						ncmcServiceModel.getStrFlag() });
		return count;
	}

	public List<KeyConfigModel> getKeyConfig() 
	{
		List<KeyConfigModel> keyList = null;/*
											 * this.jdbcCMSTemplate.query(
											 * "SELECT id AS strID,cvk_key AS strCVK,pvk_key AS strPVK,mdk_key AS strMDK,security_key_id AS strSecKeyID,participant_id AS strPartID FROM cfg_keys WHERE participant_id=?"
											 * , new Object[] { this.sessionDTO.getParticipantid() }, (RowMapper) new
											 * BeanPropertyRowMapper(KeyConfigModel.class));
											 */
		return keyList;
	}
	
	public int addKeyConfig(String attr_id, String attr_value, String part_id) 
	{
		/*
		 * int count = this.jdbcCMSTemplate.update(
		 * " INSERT INTO cfg_keys (cvk_key,pvk_key,mdk_key,security_key_id,participant_id) VALUES (CARD_TOKEN(?),CARD_TOKEN(?),CARD_TOKEN(?),CARD_TOKEN(?),?)"
		 * , new Object[] { keyConfigModel.getStrCVK(), keyConfigModel.getStrPVK(),
		 * keyConfigModel.getStrMDK(), keyConfigModel.getStrSecKeyID(),
		 * this.sessionDTO.getParticipantid() });
		 */
		int count = this.jdbcCMSTemplate.update(
				" INSERT INTO cfg_keys (attribute_id, attribute_value, participant_id) VALUES (? ,?, ?)",
				new Object[] { attr_id, attr_value, part_id });
		return count;
	}

	public int validateSenPwd(CardNoModel cardNoModel) 
	{
		return ((Integer) this.jdbcTemplate.queryForObject(
				//"SELECT COUNT(*) FROM user_details WHERE user_id=? AND sensitive_pwd=?",
				"SELECT COUNT(*) FROM user_details WHERE user_id=? AND user_password=?",
				new Object[] { cardNoModel.getUserID(), cardNoModel.getPassword() }, Integer.class)).intValue();
	}

	public int deleteBin(String id) 
	{
		try 
		{
		BinModel binModel = new BinModel();
		binModel.setStrID(id);
		binModel = configurationService.getBinOldDataforDelete(binModel);
		if (binModel != null)
		{
			configurationService.deletedAuditRecords(binModel, "cfg_bin");
		}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM cfg_bin WHERE id=? ", new Object[] { id });
		return count;
	}

	public int updateBin(BinModel binModel) 
	{
		try 
		{
			BinModel binModel1 = new BinModel();
			binModel1 = configurationService.getBinOldDataforUpdate(binModel);
			if (binModel1 != null && binModel1.getStrBin() != null)
			{
				configurationService.updateAuditData(binModel, binModel1, "cfg_bin");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(
				" UPDATE cfg_bin SET description=?, network_scheme=?, flag=? WHERE id=? AND bin=? ",
				new Object[] { binModel.getStrBinDesc(), binModel.getStrNetworkScheme(), binModel.getFlag(),
						binModel.getStrID(), binModel.getStrBin() });
		
		return count;
	}

	public int deleteBranch(String id) 
	{
		try 
		{
			BranchTypeModel branchTypeModel = new BranchTypeModel();
			branchTypeModel.setStrID(id);
			branchTypeModel = configurationService.getBranchTypeOldDataForDelete(branchTypeModel);
			if (branchTypeModel != null)
			{
				configurationService.deletedAuditRecords(branchTypeModel, "cfg_branch");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM cfg_branch WHERE id=? ", new Object[] { id });
		return count;
	}

	public int updateBranch(BranchTypeModel branchTypeModel) 
	{
		try 
		{
			BranchTypeModel branchTypeModel2 = new BranchTypeModel();
			branchTypeModel2 = configurationService.getBranchTypeOldDataForUpdate(branchTypeModel);
			if (branchTypeModel2.getStrBranchDesc() != null)
			{
				configurationService.updateAuditData(branchTypeModel, branchTypeModel2, "cfg_branch");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" UPDATE cfg_branch SET branch_desc=? WHERE id=? AND branch_name=? ",
				new Object[] { branchTypeModel.getStrBranchDesc(), branchTypeModel.getStrID(),
						branchTypeModel.getStrBranchName() });
		return count;
	}

	public int deleteBranchCode(String id) 
	{
		try 
		{
			BranchCodeModel branchCodeModel = new BranchCodeModel();
			branchCodeModel.setStrID(id);
			branchCodeModel = configurationService.getOldBranchCodeDataForDelete(branchCodeModel);
			if (branchCodeModel != null)
			{
				configurationService.deletedAuditRecords(branchCodeModel, "cfg_branch_code");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM cfg_branch_code WHERE id=? ", new Object[] { id });
		return count;
	}

	public int updateBranchCode(BranchCodeModel branchCodeModel) 
	{
		try 
		{
			BranchCodeModel branchCodeModel2 = new BranchCodeModel();
			branchCodeModel2 = configurationService.getOldBranchCodeDataForUpdate(branchCodeModel);
			if (branchCodeModel2 != null && branchCodeModel2.getStrBranchCode() != null)
			{
				configurationService.updateAuditData(branchCodeModel, branchCodeModel2, "cfg_branch_code");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(
				" UPDATE cfg_branch_code SET branch_Address=?,branch_type=?, ext_id=? WHERE id=? AND branch_code=? ",
				new Object[] { branchCodeModel.getStrBranchAddress(), branchCodeModel.getStrBranchType(),
						branchCodeModel.getStrExtID(), branchCodeModel.getStrID(),
						branchCodeModel.getStrBranchCode() });
		
		return count;
	}

	public int deleteAccount(String id) 
	{
		try 
		{
			AccountTypeModel accountTypeModel = new AccountTypeModel();
			accountTypeModel.setStrID(id);
			accountTypeModel = configurationService.getOldAccountTypeDataForDelete(accountTypeModel);
			if(accountTypeModel != null)
			{
				configurationService.deletedAuditRecords(accountTypeModel, "cfg_account_type");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM cfg_account_type WHERE id=? ", new Object[] { id });
		return count;
	}

	public int updateAccount(AccountTypeModel accountTypeModel) 
	{
		try 
		{
			AccountTypeModel accountTypeModel2 = new AccountTypeModel();
			accountTypeModel2 = configurationService.getOldAccountTypeDataForUpdate(accountTypeModel);
			if (accountTypeModel2 != null && accountTypeModel2.getStrAccountDesc() != null)
			{
				configurationService.updateAuditData(accountTypeModel, accountTypeModel2, "cfg_account_type");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(
				" UPDATE cfg_account_type SET ext_id=? WHERE id=? AND Account_description=? ",
				new Object[] { accountTypeModel.getStrExt(), accountTypeModel.getStrID(),
						accountTypeModel.getStrAccountDesc() });
		return count;
	}

	public int deleteAddress(String id) 
	{
		try 
		{
			AddressTypeModel addressTypeModel = new AddressTypeModel();
			addressTypeModel.setStrID(id);
			addressTypeModel = configurationService.getOldAddressTypeDataForDelete(addressTypeModel);
			if (addressTypeModel != null)
			{
				configurationService.deletedAuditRecords(addressTypeModel, "cfg_address_type");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM cfg_address_type WHERE id=? ", new Object[] { id });
		return count;
	}

	public int updateAddress(AddressTypeModel addressTypeModel) 
	{
		try {
			AddressTypeModel addressTypeModel2 = new AddressTypeModel();
			addressTypeModel2 = configurationService.getOldAddressTypeDataForUpdate(addressTypeModel);
			if (addressTypeModel2.getStrID() != null && addressTypeModel2.getStrAddressDesc() != null)
			{
			  configurationService.updateAuditData(addressTypeModel, addressTypeModel2, "cfg_address_type");	
			}
			
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(
				" UPDATE cfg_address_type SET ext_id=? WHERE id=? AND address_description=? ",
				new Object[] { addressTypeModel.getStrExt(), addressTypeModel.getStrID(),
						addressTypeModel.getStrAddressDesc() });
		
		return count;
	}

	public int deleteCardType(String id) 
	{
		try 
		{
			CardTypeModel cardtyeModel = new CardTypeModel();
			cardtyeModel.setStrID(id);
			cardtyeModel = configurationService.getOldCardTypeDataForDelete(cardtyeModel);
			if (cardtyeModel != null && cardtyeModel.getStrID() != null)
			{
				configurationService.deletedAuditRecords(cardtyeModel, "cfg_card_type");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM cfg_card_type WHERE id=? ", new Object[] { id });
		
		try 
		{
			CardTypeModel cardtypeModel = new CardTypeModel();
			cardtypeModel.setStrCardType(id);
			cardtypeModel = configurationService.getOldCardTypeMccDataForDelete(cardtypeModel);
			if (cardtypeModel != null)
			{
				configurationService.deletedAuditRecords(cardtypeModel, "card_type_mcc");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		this.jdbcCMSTemplate.update(" DELETE FROM card_type_mcc WHERE card_type=? ", new Object[] { id });
		return count;
	}

	public int updateCardType(CardTypeModel cardTypeModel) 
	{
		try 
		{
			CardTypeModel cardTypeModel1 = new CardTypeModel();
			cardTypeModel1 = configurationService.getOldCardTypeDataForUpdate(cardTypeModel);
			if (cardTypeModel1 != null)
			{
				configurationService.updateAuditData(cardTypeModel, cardTypeModel1, "cfg_card_type");
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();		
		}
		
		int count = this.jdbcCMSTemplate.update(
				" UPDATE cfg_card_type SET description=? ,bin=?,bin_suffix=?,ncmc_flag=?,endpoint=? WHERE id=? ",
				new Object[] { cardTypeModel.getStrCardDesc(), cardTypeModel.getStrBin(),
						cardTypeModel.getStrBinSuffix(), cardTypeModel.getStrFlag(),
						(cardTypeModel.getStrEndpoint() == "") ? "0" : cardTypeModel.getStrEndpoint(),
						cardTypeModel.getStrID() });
		
		return count;
	}

	public int deleteCardTemplate(String id) 
	{
		try 
		{
		 CardTemplateModel cardTemplateModel = new CardTemplateModel();
		 cardTemplateModel.setStrID(id);
		 cardTemplateModel = configurationService.getoldCardTemplateData(cardTemplateModel);
		 if(cardTemplateModel.getStrID() != null)
		 {
			 configurationService.deletedAuditRecords(cardTemplateModel, "cfg_card_template");
		 }
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM cfg_card_template WHERE id=? ", new Object[] { id });
		return count;
	}

	public int updateCardTemplate(CardTemplateModel cardTemplateModel) 
	{
		try 
		{
			CardTemplateModel cardTemplateModel1 = cardTemplateModel;
			cardTemplateModel1 = configurationService.getoldCardTemplateData(cardTemplateModel);
			if (cardTemplateModel.getStrID() != null)
			{
				configurationService.updateAuditData(cardTemplateModel, cardTemplateModel1, "cfg_card_template");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		int month = 0, year = 0;
		if ("M".equals(cardTemplateModel.getStrExpiryType())) {
			month = Integer.parseInt(
					(cardTemplateModel.getStrExpireValue() == null) ? "0" : cardTemplateModel.getStrExpireValue());
		} else {
			year = Integer.parseInt(
					(cardTemplateModel.getStrExpireValue() == null) ? "0" : cardTemplateModel.getStrExpireValue());
		}
		int count = this.jdbcCMSTemplate.update(
				" UPDATE cfg_card_template SET service_code=? ,card_mailer_Issue_flag=?,pin_mailer_Issue_flag=?, temporary_limit_activation_flag=?, daily_pin_retry_limit=?, consecutive_pin_retry_limit=?,online_atm_limit=?,online_pos_limit=?,online_ecom_limit=?,offline_limit=?,monthly_limit=?,weekly_limit=?,daily_limit=?,expiry_cfg_type=?,expiry_month=?,expiry_year=? WHERE id=?",
				new Object[] { cardTemplateModel.getStrServiceCode(), cardTemplateModel.getStrCardMailerFlag(),
						cardTemplateModel.getStrPinMailerFlag(), cardTemplateModel.getStrTempLimitFlag(),
						cardTemplateModel.getStrDailyPinLimit(), cardTemplateModel.getStrConPinLimit(),
						cardTemplateModel.getStrOnlineAtmLimit(), cardTemplateModel.getStrOnlinePosLimit(),
						cardTemplateModel.getStrOnlineEcomLimit(), cardTemplateModel.getStrOfflineLimit(),
						cardTemplateModel.getStrMonthlyLimit(), cardTemplateModel.getStrWeeklyLimit(),
						cardTemplateModel.getStrDailyLimit(), cardTemplateModel.getStrExpiryType(),
						Integer.valueOf(month), Integer.valueOf(year), cardTemplateModel.getStrID() });
		
		return count;
	}

	public int deleteCardPlastic(String id) 
	{
		try 
		{
			CardPlasticModel cardPlasticModel = new CardPlasticModel();
			cardPlasticModel.setStrID(id);
			cardPlasticModel = configurationService.getOldCardPlasticData(cardPlasticModel);
			if (cardPlasticModel != null)
			{
				configurationService.deletedAuditRecords(cardPlasticModel, "cfg_card_plastic");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM cfg_card_plastic WHERE id=? ", new Object[] { id });
		return count;
	}

	public int updateCardPlastic(CardPlasticModel cardPlasticModel) 
	{
		try 
		{
			CardPlasticModel cardPlasticModel2 = new CardPlasticModel();
			cardPlasticModel2 = configurationService.getOldCardPlasticData(cardPlasticModel);
			if (cardPlasticModel2 != null)
			{
				configurationService.updateAuditData(cardPlasticModel, cardPlasticModel2, "cfg_card_plastic");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(
				" UPDATE cfg_card_plastic SET vendor_id=? ,CVV_position=?,servicecode_position=?, expiry_date_position=?, card_seq_position=? WHERE id=?",
				new Object[] { cardPlasticModel.getStrVendorID(), cardPlasticModel.getStrCVV(),
						cardPlasticModel.getStrServicePos(), cardPlasticModel.getStrExpPos(),
						cardPlasticModel.getStrSeqPos(), cardPlasticModel.getStrID() });
		
		return count;
	}

	public int deleteEmail(String id) {
		try 
		{
			EmailTypeModel emailTypeModel = new EmailTypeModel();
			emailTypeModel.setStrID(id);
			emailTypeModel = configurationService.getOldEmailData(emailTypeModel);
			if (emailTypeModel != null)
			{
				configurationService.deletedAuditRecords(emailTypeModel, "cfg_email_type");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM cfg_email_type WHERE id=? ", new Object[] { id });
		return count;
	}

	public int updateEmail(EmailTypeModel emailTypeModel) 
	{
		try 
		{
			EmailTypeModel emailTypeModel1 = new EmailTypeModel();
			emailTypeModel1 = configurationService.getOldEmailData(emailTypeModel);
			if (emailTypeModel1 != null)
			{
				configurationService.updateAuditData(emailTypeModel, emailTypeModel1, "cfg_email_type");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(
				" UPDATE cfg_email_type SET ext_id=? WHERE id=? AND email_description=? ",
				new Object[] { emailTypeModel.getStrExt(), emailTypeModel.getStrID(), emailTypeModel.getStrEmail() });
		
		return count;
	}

	public int deleteNCMC(String id) 
	{
		try 
		{
			NcmcServiceModel ncmcServiceModel = new NcmcServiceModel();
			ncmcServiceModel.setStrID(id);
			ncmcServiceModel = configurationService.getOldNCMCData(ncmcServiceModel);
			if (ncmcServiceModel != null)
			{
				configurationService.deletedAuditRecords(ncmcServiceModel, "cfg_ncmc_code");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM cfg_ncmc_code WHERE id=? ", new Object[] { id });
		return count;
	}

	public int updateNCMC(NcmcServiceModel ncmcServiceModel) 
	{
		try 
		{
			NcmcServiceModel ncmcServiceModel1 = ncmcServiceModel;
			ncmcServiceModel1 = configurationService.getOldNCMCData(ncmcServiceModel);
			if (ncmcServiceModel1 != null)
			{
				configurationService.updateAuditData(ncmcServiceModel, ncmcServiceModel1, "cfg_ncmc_code");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" UPDATE cfg_ncmc_code SET active=?  WHERE id=? and ncmc_code=?",
				new Object[] { ncmcServiceModel.getStrFlag(), ncmcServiceModel.getStrID(),
						ncmcServiceModel.getStrNcmcID() });
		
		return count;
	}

	public int deleteKey(String id) 
	{
		try 
		{
			KeyConfigModel keyConfigModel = new KeyConfigModel();
			keyConfigModel = configurationService.getOldKeyData(keyConfigModel);
			if (keyConfigModel != null)
			{
				configurationService.deletedAuditRecords(keyConfigModel, "cfg_keys");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM cfg_keys WHERE id=? ", new Object[] { id });
		return count;
	}

	public int updateKey(KeyConfigModel keyConfigModel) 
	{
		try 
		{
			KeyConfigModel keyConfigModel1 = keyConfigModel;
			keyConfigModel1 = configurationService.getOldKeyData(keyConfigModel);
			if (keyConfigModel1 != null)
			{
				configurationService.updateAuditData(keyConfigModel, keyConfigModel1, "cfg_keys");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate
				.update(" UPDATE cfg_keys SET cvk_key=?, pvk_key=?, mdk_key=?, security_key_id=? WHERE id=? ",
						new Object[] { keyConfigModel.getStrCVK(), keyConfigModel.getStrPVK(),
								keyConfigModel.getStrMDK(), keyConfigModel.getStrSecKeyID(),
								keyConfigModel.getStrID() });
		
		return count;
	}

	public List<StateModel> getStateList(String id) 
	{
		List<StateModel> stateList = this.jdbcCMSTemplate.query(
				" SELECT cs.id AS strID, cc.id AS strCountryID, cs.state_name AS strStateName FROM cfg_state cs LEFT JOIN cfg_country cc ON cs.country_id = cc.id WHERE cs.country_id = ?  ",
				new Object[] { id }, (RowMapper) new BeanPropertyRowMapper(StateModel.class));
		return stateList;
	}

	public List<CityModel> getCityList(String id) {
		List<CityModel> cityList = this.jdbcCMSTemplate.query(
				" SELECT cs.id AS strID, cc.id AS strStateID, cs.city_name AS strCityName FROM cfg_city cs LEFT JOIN cfg_state cc ON cs.state_id = cc.id WHERE cs.state_id = ?  ",
				new Object[] { id }, (RowMapper) new BeanPropertyRowMapper(CityModel.class));
		return cityList;
	}

	public Map<String, Object> getCardNo(String tokenCard) 
	{
		try 
		{
			List<Map<String, Object>> rows;
			rows = this.jdbcCMSTemplate.queryForList(
					" SELECT card_details.enc_card AS card, card_token.token_details AS token FROM card_details LEFT JOIN card_token ON card_details.id = card_token.card_id WHERE token_card=? ",
					new Object[] { tokenCard});
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> row=new ArrayList<Map<String, Object>>();
			
			if (rows != null && !rows.isEmpty()) 
			{
			map.put("token",rows.get(0).get("token"));
			map.put("card", aesEncDec.decrypt(rows.get(0).get("card").toString()));
			row.add(map);
			return row.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}

	public List<EmbossConfigModel> getEmbossConfig() {
		List<EmbossConfigModel> embossList = this.jdbcCMSTemplate.query(
				" SELECT id AS strID,vender_name AS strServiceName,format AS strFormat FROM cfg_emboss_fmt WHERE participant_id=? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(EmbossConfigModel.class));
		return embossList;
	}

	public int addEmboss(EmbossConfigModel embossConfigModel) {
		return this.jdbcCMSTemplate.update(
				"INSERT INTO cfg_emboss_fmt (vender_name,format,participant_id) VALUES (?,?,?)",
				new Object[] { embossConfigModel.getStrServiceName(), embossConfigModel.getStrFormat(),
						this.sessionDTO.getParticipantid() });
	}

	public List<EmbossConfigModel> getEmbossNameConfig() {
		List<EmbossConfigModel> embossList = this.jdbcCMSTemplate.query(
				" SELECT id AS strID,vender_name AS strServiceName FROM cfg_emboss_fmt WHERE participant_id=? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(EmbossConfigModel.class));
		return embossList;
	}

	public List<NcmcServiceModel> getNcmcCode() {
		List<NcmcServiceModel> NcmcList = this.jdbcCMSTemplate.query(
				" SELECT ncmc_code as strNcmcID FROM cfg_ncmc_code WHERE participant_id=? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(NcmcServiceModel.class));
		return NcmcList;
	}

	public List<CardTypeModel> getCardTypeNCMC() 
	{
		List<CardTypeModel> accountList = this.jdbcCMSTemplate.query(
				" SELECT card_type AS strCardType, description AS strCardDesc, participant_id AS strPartID  , bin AS strBin, bin_suffix AS strBinSuffix, cvn_method_id AS strCVN,  pinmailer_format AS strPinFormat, decimalization_table AS strDecTable, id AS strID, ncmc_flag AS strFlag FROM cfg_card_type WHERE participant_id=? and ncmc_flag=2",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(CardTypeModel.class));
		return accountList;
	}

	public int addNCMCService(NcmcServiceModel ncmcServiceModel) 
	{
		String[] data = ncmcServiceModel.getStrNcmcID().split("\\,");
		int count = 0;
		if (data != null)
			this.jdbcCMSTemplate.update("DELETE FROM cfg_ncmc_service WHERE card_type=? AND participant_id=?",
					new Object[] { ncmcServiceModel.getStrCardType(), this.sessionDTO.getParticipantid() });
		
		try 
		{
			this.auditService.addAudit(sessionDTO.getParticipantid(), "cfg_ncmc_service", null, null, null, "DELETE", this.sessionDTO.getLoginID());
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		byte b;
		int i;
		String[] arrayOfString1;
		for (i = (arrayOfString1 = data).length, b = 0; b < i;) {
			String ncmc = arrayOfString1[b];
			count += this.jdbcCMSTemplate.update(
					"INSERT INTO cfg_ncmc_service (participant_id,ncmc_service_id,card_type) VALUES (?,?,?)",
					new Object[] { this.sessionDTO.getParticipantid(), ncmc, ncmcServiceModel.getStrCardType() });
			b++;
		}
		return count;
	}

	public List<KeyValueBean> getncmcList(String type) {
		List<KeyValueBean> accountList = this.jdbcCMSTemplate.query(
				"SELECT ncmc_service_id AS lkpkey FROM cfg_ncmc_service WHERE card_type=? AND participant_id=?",
				new Object[] { type, this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(KeyValueBean.class));
		return accountList;
	}

	public String getNetworkScheme(String bin) {
		return (String) this.jdbcCMSTemplate.queryForObject(
				" SELECT network_scheme FROM cfg_bin WHERE bin=? OR bin = (SELECT bin FROM cfg_card_type WHERE id=? AND participant_id=?) AND  participant_id=? ",
				new Object[] { bin, bin, this.sessionDTO.getParticipantid(), this.sessionDTO.getParticipantid() },
				String.class);
	}

	public String getBinFlag(String bin) {
		return (String) this.jdbcCMSTemplate.queryForObject(
				" SELECT flag FROM cfg_bin WHERE bin=? OR bin=  (SELECT bin FROM cfg_card_type WHERE id=? AND participant_id=?)  AND participant_id=? ",
				new Object[] { bin, bin, this.sessionDTO.getParticipantid(), this.sessionDTO.getParticipantid() },
				String.class);
	}

	public List<KeyValueBean> getEndPoint() {
		List<KeyValueBean> endpointList = this.jdbcCMSTemplate.query(
				" SELECT id AS lkpkey, endpoint AS lkpvalue FROM cfg_endpoint ", new Object[0],
				(RowMapper) new BeanPropertyRowMapper(KeyValueBean.class));
		return endpointList;
	}

	public List<KeyValueBean> getCfg_MCC() {
		List<KeyValueBean> getCfg_MCC = this.jdbcCMSTemplate.query(
				" SELECT mcc AS lkpkey, CONCAT(mcc,' : ',description) AS lkpvalue FROM cfg_mcc ", new Object[0],
				(RowMapper) new BeanPropertyRowMapper(KeyValueBean.class));
		return getCfg_MCC;
	}

	public List<KeyValueBean> getCard_Type_MCC(String id) {
		List<KeyValueBean> getCfg_MCC = this.jdbcCMSTemplate.query(
				" SELECT cm.mcc AS lkpkey, CONCAT(cm.mcc,' : ',cm.description) AS lkpvalue FROM card_type_mcc ctc INNER JOIN cfg_mcc cm ON ctc.mcc=cm.mcc WHERE ctc.card_type=? AND ctc.participant_id=? ",
				new Object[] { id, this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(KeyValueBean.class));
		return getCfg_MCC;
	}

	public int getBinCardType(String bin) {
		return ((Integer) this.jdbcCMSTemplate.queryForObject(
				" SELECT id FROM cfg_card_type WHERE CONCAT(bin,bin_suffix) = ? AND participant_id = ? ",
				new Object[] { bin, this.sessionDTO.getParticipantid() }, Integer.class)).intValue();
	}

	public int getAddressType(String type) {
		return ((Integer) this.jdbcCMSTemplate.queryForObject(
				" SELECT id FROM cfg_address_type WHERE address_description=? AND participant_id=? ",
				new Object[] { type, this.sessionDTO.getParticipantid() }, Integer.class)).intValue();
	}

	public int getEmailType(String type) {
		return ((Integer) this.jdbcCMSTemplate.queryForObject(
				" SELECT id FROM cfg_email_type WHERE email_description=? AND participant_id=? ",
				new Object[] { type, this.sessionDTO.getParticipantid() }, Integer.class)).intValue();
	}

	public int getDocType(String type) {
		return ((Integer) this.jdbcCMSTemplate.queryForObject(
				" SELECT id FROM cfg_document_type WHERE document_Type=? AND participant_id=? ",
				new Object[] { type, this.sessionDTO.getParticipantid() }, Integer.class)).intValue();
	}

	public int getCityID(String id) {
		return ((Integer) this.jdbcCMSTemplate.queryForObject(" SELECT id FROM cfg_city WHERE UPPER(city_name) = ?  ",
				new Object[] { id }, Integer.class)).intValue();
	}

	public int getStateID(String id) {
		return ((Integer) this.jdbcCMSTemplate.queryForObject(" SELECT id FROM cfg_state WHERE UPPER(state_name) = ? ",
				new Object[] { id }, Integer.class)).intValue();
	}

	public int getCountryID(String id) {
		return ((Integer) this.jdbcCMSTemplate.queryForObject(
				" SELECT id FROM cfg_country WHERE UPPER(country_name) = ? ", new Object[] { id }, Integer.class))
						.intValue();
	}

	public int getAccountID(String id) {
		return ((Integer) this.jdbcCMSTemplate.queryForObject(
				" SELECT id FROM cfg_account_type WHERE Account_description=? ", new Object[] { id }, Integer.class))
						.intValue();
	}

	public int updateMCC(CardTypeModel cardTypeModel) 
	{
		try 
		{
			CardTypeModel cardTypeModel1 = new CardTypeModel();
			cardTypeModel1 = configurationService.getOldCardTypeMccDataForDelete(cardTypeModel);
			if (cardTypeModel1.getStrCardType() != null)
			{
				configurationService.deletedAuditRecords(cardTypeModel1, "card_type_mcc");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(" DELETE FROM card_type_mcc WHERE card_type=? ",
				new Object[] { cardTypeModel.getStrCardType() });
		
		if (cardTypeModel.getStrMcc() != null) {
			byte b;
			int i;
			String[] arrayOfString;
			for (i = (arrayOfString = cardTypeModel.getStrMcc().split("\\,")).length, b = 0; b < i;) {
				String data = arrayOfString[b];
				count += this.jdbcCMSTemplate.update(" INSERT INTO card_type_mcc VALUES (?,?,?) ",
						new Object[] { cardTypeModel.getStrCardType(), data, this.sessionDTO.getParticipantid() });
				b++;
			}
		}
		return count;
	}

	public List<KeyValueBean> getCard_MCC(String id) {
		List<KeyValueBean> getCfg_MCC = this.jdbcCMSTemplate.query(
				" SELECT mcc AS lkpkey FROM card_mcc_service WHERE card_number=? AND participant_id=? ",
				new Object[] { id, this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(KeyValueBean.class));
		return getCfg_MCC;
	}

	public int getBatchUpdateCount(String id) {
		return ((Integer) this.jdbcCMSTemplate.queryForObject(
				" SELECT COUNT(*) FROM bulk_data WHERE status='Pending' AND file_name=? AND participant_id=? ",
				new Object[] { id, this.sessionDTO.getParticipantid() }, Integer.class)).intValue();
	}

	public int addCardToken(CardTokenModel cardTokenModel) 
	{
		try 
		{
			cardTokenModel = configurationService.getOldCardTokenData(cardTokenModel);
			if (cardTokenModel.getStrCardType() != null)
			{
				configurationService.deletedAuditRecords(cardTokenModel, "cfg_card_token");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		this.jdbcCMSTemplate.update(" DELETE FROM cfg_card_token WHERE card_type = ? ",
				new Object[] { cardTokenModel.getStrCardType() });
		
		return this.jdbcCMSTemplate.update(
				" INSERT INTO cfg_card_token (card_type,length,onDemand,cron,description) VALUES (?,?,?,?,?) ",
				new Object[] { cardTokenModel.getStrCardType(), cardTokenModel.getStrLength(),
						Integer.valueOf(cardTokenModel.getStrDemandFlag()), cardTokenModel.getStrDemondCronExpr(),
						cardTokenModel.getEnDesc() });
	}

	public List<CardTokenModel> getCardTokenList() {
		List<CardTokenModel> getCfg_Card_Token = this.jdbcCMSTemplate.query(
				" SELECT cct.id, CONCAT(ct.card_type, ' : ', ct.description) AS strCardType, cct.LENGTH AS strLength, cct.onDemand AS strDemandFlag, cct.cron AS strDemondCronExpr, cct.description AS enDesc  FROM cfg_card_token AS cct INNER JOIN cfg_card_type ct ON cct.card_type = ct.id ",
				(RowMapper) new BeanPropertyRowMapper(CardTokenModel.class));
		return getCfg_Card_Token;
	}

	public List<CardTypeModel> getCfgCardType() {
		List<CardTypeModel> accountList = this.jdbcCMSTemplate.query(
				" SELECT CONCAT(cf.card_type, ' : ' ,cf.description) AS strCardType, cf.id AS strID FROM cfg_card_type cf LEFT JOIN cfg_card_token ct ON cf.id = ct.card_type WHERE ct.card_type IS NULL AND  cf.card_token = 1 ",
				(RowMapper) new BeanPropertyRowMapper(CardTypeModel.class));
		return accountList;
	}

	public List<MobileTokenModel> getMobileTokenList() {
		return this.jdbcCMSTemplate.query(
				" SELECT mobile, length AS strLength, description AS enDesc FROM cfg_mobile_token ",
				(RowMapper) new BeanPropertyRowMapper(MobileTokenModel.class));
	}

	public int addIsoData(IsoConfigModel isoConfig) {
		int count = this.jdbcCMSTemplate.update(
				"INSERT INTO cfg_iso (name, participant_id, data, connectionName) VALUES (?,?,?,?)",
				new Object[] { isoConfig.getName(), this.sessionDTO.getParticipantid(), isoConfig.getData(),
						isoConfig.getConnectionName() });
		return count;
	}

	public List<IsoConfigModel> getISOConfigList() {
		List<IsoConfigModel> isoList = this.jdbcCMSTemplate.query(
				" SELECT id, name, data, connectionName FROM cfg_iso WHERE participant_id= ? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(IsoConfigModel.class));
		return isoList;
	}

	@Override
	public List<KeyValueBean> getParticipantList() {
		List<KeyValueBean> list = this.jdbcCMSTemplate.query(
				" SELECT  id AS lkpkey,  CONCAT(participant_name , ' : ', description) AS lkpvalue FROM cfg_participant where participant_type != 'CMS' and display_flag = 'Y' ",
				(RowMapper) new BeanPropertyRowMapper(KeyValueBean.class));
		return list;
	}

	@Override
	public int addConnection(ConnectionConfigModel connConfig) {
		int count = this.jdbcCMSTemplate.update(
				" INSERT INTO cfg_connection (name, service_type, connection_type, participant_id, ip, port) VALUES (?, ?, ?, ?, ?, ?) ",
				new Object[] { connConfig.getName(), connConfig.getServiceType(), connConfig.getConnType(),
						connConfig.getParticipantId(), connConfig.getIp(), connConfig.getPort() });
		return count;
	}

	@Override
	public List<ConnectionConfigModel> getConnectionList() {
		List<ConnectionConfigModel> list = this.jdbcCMSTemplate.query(
				" SELECT c.name, c.service_type as serviceType, c.connection_type as connType, CONCAT(p.participant_name, ' : ', p.description) AS participantId, c.ip, c.port FROM cfg_connection c JOIN cfg_participant p ON c.participant_id = p.id  ",
				(RowMapper) new BeanPropertyRowMapper(ConnectionConfigModel.class));
		return list;
	}

	@Override
	public int addParticipant(ParticipantConfigModel partConfig) {
		int id = ((Integer) this.jdbcCMSTemplate.queryForObject(" SELECT MAX( id ) FROM cfg_participant ",
				new Object[] {}, Integer.class)).intValue();
		;
		int count = this.jdbcCMSTemplate.update(
				" INSERT INTO cfg_participant (id, participant_name, description, participant_type, display_flag) VALUES (?, ?, ?, ?, ?) ",
				new Object[] { id + 1, partConfig.getParticipantName(), partConfig.getDescription(),
						partConfig.getParticipantType(), partConfig.getFlag() });
		return count;
	}

	@Override
	public List<ParticipantConfigModel> getParticipantConfigList() {
		List<ParticipantConfigModel> list = this.jdbcCMSTemplate.query(
				" SELECT id, participant_name as participantName, description as description, participant_type as participantType, display_flag as flag FROM cfg_participant ",
				(RowMapper) new BeanPropertyRowMapper(ParticipantConfigModel.class));
		return list;
	}

	// Added by Prashant Tayde

	@Override
	public List<ChargesTypeModel> getChargesTypeList() {
		List<ChargesTypeModel> list = this.jdbcCMSTemplate.query(
				" SELECT id, charges as chargesType FROM charges_type ",
				(RowMapper) new BeanPropertyRowMapper(ChargesTypeModel.class));
		return list;
	}

	/*
	 * @Override public List<AccountType> getAccountTypeList() { List<AccountType>
	 * list = this.jdbcCMSTemplate.query(
	 * " SELECT id, account_Type as accountType FROM account_Type ", (RowMapper) new
	 * BeanPropertyRowMapper(AccountType.class)); return list; }
	 */

	// Added by Prashant Tayde

	@Override
	public List<CardType> getCardTypeList() {
		List<CardType> list = this.jdbcCMSTemplate.query(" SELECT id, card_Type as cardType FROM card_type ",
				(RowMapper) new BeanPropertyRowMapper(CardType.class));
		return list;

	}

	// Added by Prashant Tayde
	// add changes Is_Credit_Type in AccountTypemaster by sagark date:30-11-2022
	// Code changes Added by Prashant Tayde 12-12-22
	@Override
	public int addAccountTypeMaster(AccountTypeMaster accountTypeMaster) {
		try {
			int count = this.jdbcCMSTemplate.update(
					" INSERT INTO account_type_master (participant_id,account_type, last_account_number,description,account_number_length,account_number_start_digit,status,category_type,dormancy_periods_in_days,allow_load_cash,tax_type,CGST,IGST,SGST,is_credit_type,created_by) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
					new Object[] { this.sessionDTO.getParticipantid(), accountTypeMaster.getStrAccountType(),
							accountTypeMaster.getStrLastAccNumber(), accountTypeMaster.getStrDescription(),
							accountTypeMaster.getStrAccNumLength(), accountTypeMaster.getStrAccNumStartDigit(),
							accountTypeMaster.getStrStatus(), accountTypeMaster.getStrCategory(),
							accountTypeMaster.getStrDormantPeriod(), accountTypeMaster.getStrAllowLoadCash(),
							accountTypeMaster.getStrTaxType(), accountTypeMaster.getStrCGST(),
							accountTypeMaster.getStrIGST(), accountTypeMaster.getStrSGST(),
							(accountTypeMaster.getStrIsCreditType() == null) ? "N" : "Y",
							this.sessionDTO.getLoginID() }); // add flag yes or no

			System.out.println("ConfigurationDaoImpl.addAccountTypeMaster()" + accountTypeMaster);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Added by Prashant Tayde
	@Override
	public int deleteAccountType(String id) {
		int count = this.jdbcCMSTemplate.update(" DELETE FROM account_type_master WHERE id=? ", new Object[] { id });
		return count;
	}

	// Added by Prashant Tayde
	@Override
	public int updateAccountType(AccountTypeMaster accountTypeMaster) {
		try {
			int count = this.jdbcCMSTemplate.update(
					" UPDATE account_type_master SET account_type = ? , description = ? , "
							+ "account_number_length = ? ,account_number_start_digit = ? , status = ? , category_type= ?, dormancy_periods_in_months = ?, allow_load_cash = ?, is_credit_type=? "
							+ "WHERE id = ? ",
					new Object[] { accountTypeMaster.getStrAccountType(), accountTypeMaster.getStrDescription(),
							accountTypeMaster.getStrAccNumLength(), accountTypeMaster.getStrAccNumStartDigit(),
							accountTypeMaster.getStrStatus(), accountTypeMaster.getStrCategory(),
							accountTypeMaster.getStrDormantPeriod(), accountTypeMaster.getStrAllowLoadCash(),
							accountTypeMaster.getStrIsCreditType(), accountTypeMaster.getStrID() });
			System.out.println("ConfigurationDaoImpl.updateAccountType()" + accountTypeMaster);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addAccountTransactionLimit(AccountTransactionLimitModel accountTransactionLimitModel) {
		try {
			int count = this.jdbcCMSTemplate.update(
					" INSERT INTO account_transaction_limit (participant_id,account_type,account_number,single_txn_limit,daily_txn_limit,monthly_txn_limit,yearly_txn_limit,created_by) VALUES (?,?,?,?,?,?,?,?) ",
					new Object[] { this.sessionDTO.getParticipantid(), accountTransactionLimitModel.getStrAccountType(),
							accountTransactionLimitModel.getStrAccountNumber(),
							accountTransactionLimitModel.getStrSingleTxnLimit(),
							accountTransactionLimitModel.getStrDailyTxnLimit(),
							accountTransactionLimitModel.getStrMonthlyTxnLimit(),
							accountTransactionLimitModel.getStrYearlyTxnLimit(), this.sessionDTO.getLoginID() });
			System.out.println("ConfigurationDaoImpl.addAccountTypeMaster()" + accountTransactionLimitModel);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Added by Prashant Tayde
	@Override
	public List<AccountTypeMaster> getAccounTypeMaster() {
		try {
			List<AccountTypeMaster> accountTypeMasterList = this.jdbcCMSTemplate.query(
					" SELECT id as strID , account_type AS strAccountType, description AS strDescription, account_number_length AS strAccNumLength, "
							+ " account_number_start_digit AS strAccNumStartDigit,is_credit_type As strIsCreditType, "
							+ " category_type AS strCategory,dormancy_periods_in_months AS strDormantPeriod,allow_load_cash AS strAllowLoadCash, "
							+ " status AS strStatus from account_type_master where participant_id = ? ",
					new Object[] { this.sessionDTO.getParticipantid() },
					(RowMapper) new BeanPropertyRowMapper(AccountTypeMaster.class));
			System.out.println("ConfigurationDaoImpl.getAccounTypeMaster()" + accountTypeMasterList);
			return accountTypeMasterList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// Added by Prashant Tayde
	@Override
	public int deleteCardAccountLinkage(String id) {
		int count = this.jdbcCMSTemplate.update(" DELETE FROM card_account_linkage_master WHERE id=? ",
				new Object[] { id });
		System.out.println("ConfigurationDaoImpl.deleteCardAccountLinkage()" + "deleteId" + count);
		return count;
	}

	// Added by Prashant Tayde
	@Override
	public int updateCardAccountLinkage(CardAccountLinkage cardAccountLinkage) {
		int count = this.jdbcCMSTemplate.update(
				" UPDATE card_account_linkage_master SET account_type=? , card_type=? , account_number=? , card_number=? WHERE id=? ",
				new Object[] { cardAccountLinkage.getStrAccountType(), cardAccountLinkage.getStrCardType(),
						cardAccountLinkage.getStrAccountNumber(), cardAccountLinkage.getStrCardNumber(),
						cardAccountLinkage.getStrID() });
		return count;

	}

	// Added by Prashant Tayde
	@Override
	public int addCardAccountLinkage(CardAccountLinkage cardAccountLinkage) {
		try {
			int count = this.jdbcCMSTemplate.update(
					" INSERT INTO card_account_linkage_master (participant_id,account_type,account_number,card_number,card_type,created_by) VALUES (?,?,?,?,?,?)",
					new Object[] { this.sessionDTO.getParticipantid(), cardAccountLinkage.getStrAccountType(),
							cardAccountLinkage.getStrAccountNumber(), cardAccountLinkage.getStrCardNumber(),
							cardAccountLinkage.getStrCardType(), this.sessionDTO.getLoginID() });
			System.out.println("ConfigurationDaoImpl.addCardAccountLinkage()" + cardAccountLinkage);
			return count;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	// Added by Prashant Tayde
	@Override
	public List<CardAccountLinkage> getCardAccountLinkage() {
		List<CardAccountLinkage> cardAccountLinkageList = this.jdbcCMSTemplate.query(
				" SELECT id as strID, account_type AS strAccountType, account_number AS strAccountNumber, card_type AS strCardType, "
						+ " card_number AS strCardNumber from card_account_linkage_master WHERE participant_id = ? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(CardAccountLinkage.class));
		return cardAccountLinkageList;
	}
	
	@Override
	public List<CardAccountLinkage> getCardLinkAccountList(CardAccountLinkage cardAccountLinkage) 
	{
		List<CardAccountLinkage> cardAccountLinkageList = this.jdbcCMSTemplate.query(
				" SELECT id as strID, account_type AS strAccountType, account_number AS strAccountNumber, card_type AS strCardType, "
						+ " card_number AS strCardNumber from card_account_linkage_master "
						+ "WHERE participant_id = ? AND account_number = ?",
				new Object[] { cardAccountLinkage.getStrParticipantID(), cardAccountLinkage.getStrAccountNumber() },
				(RowMapper) new BeanPropertyRowMapper(CardAccountLinkage.class));
		return cardAccountLinkageList;
	}

	// Added by Prashant Tayde
	@Override
	public List<AccountCreation> getAccountCreation() {
		List<AccountCreation> accountCreationList = this.jdbcCMSTemplate.query(
				" SELECT id as strID , account_type AS strAccountType, account_number AS strAccountNumber, title AS strTitle, first_name AS strFirstName, middle_name AS strMiddleName, "
						+ " last_name AS strLastName,gender AS strGender,dob AS strDOB,address1 AS strAddress1 ,address2 AS strAddress2,pincode AS strPinCode, "
						+ " city AS strCity,state AS strState,country AS strCountryCode,created_by AS strCreatedBy, tax_type as strTaxType , status AS strStatus "
						+ " from account_master WHERE participant_id = ? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(AccountCreation.class));
		System.out.println("ConfigurationDaoImpl.getAccountCreation()" + accountCreationList);
		return accountCreationList;
	}

	// Added by Prashant Tayde
	@Override
	public int addAccountCreation(AccountCreation accountCreation) {
		try {
			// country = getCountry(accountCreation.getStrCountryCode());
			/*
			 * String countryName = getCountryName(accountCreation.getStrCountryCode());
			 * accountCreation.setStrCountry(countryName);
			 */
			// state = getStaet

			int count = this.jdbcCMSTemplate.update(
					" INSERT INTO account_master (participant_id,account_type,account_number,title,first_name,middle_name,last_name,gender,dob,email,mobile_no,address1,address2,address3,pincode,city,state,country,phone_no,status,tax_type,is_instant_account,credit_limit_amount) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { this.sessionDTO.getParticipantid(), accountCreation.getStrAccountType(),
							accountCreation.getStrAccountNumber(), accountCreation.getStrTitle(),
							accountCreation.getStrFirstName(), accountCreation.getStrMiddleName(),
							accountCreation.getStrLastName(), accountCreation.getStrGender(),
							accountCreation.getStrDOB(), accountCreation.getStrEmailID(),
							accountCreation.getStrMobileNo(), accountCreation.getStrAddress1(),
							accountCreation.getStrAddress2(), accountCreation.getStrAddress3(),
							accountCreation.getStrPinCode(), accountCreation.getStrCity(),
							accountCreation.getStrState(), accountCreation.getStrCountry(),
							accountCreation.getStrPhoneNo(), "Active", accountCreation.getStrTaxType(), "N",
							accountCreation.getStrCreditLimitAmount() });
			System.out.println("ConfigurationDaoImpl.addAccountCreation()" + accountCreation);

			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Added by Prashant Tayde
	@Override
	public List<AccountCreation> getAccountType() {
		List<AccountCreation> accountCreation = this.jdbcCMSTemplate.query(
				" SELECT account_type AS strAccountType, last_account_number AS strLastAccNumber FROM account_type_master ",
				// ",
				// " SELECT id AS strID , atm.account_type AS StrAccountType,
				// atm.last_account_number AS strLastAccNumber FROM account_type_master atm ",
				// + " WHERE participant_id = ? ",

				(RowMapper) new BeanPropertyRowMapper(AccountCreation.class));

		System.out.println("ConfigurationDaoImpl.getAccountType()" + accountCreation);
		return accountCreation;
	}

	// Added by Prashant Tayde
	@Override
	public int deleteAccountCreation(String id) {
		int count = this.jdbcCMSTemplate.update(" DELETE FROM account_master WHERE id=? ", new Object[] { id });
		return count;
	}

	@Override
	public int updateAccountCreation(AccountCreation accountCreation) {
		System.out.println("ConfigurationDaoImpl.updateAccountCreation()" + accountCreation);
		try {
			int count = this.jdbcCMSTemplate.update(
					"UPDATE account_master SET address1 = ?,  address2 = ?, address3 = ?, pincode = ?, country = ?, state = ?, city = ? , status = ?, created_by = ?"
							+ " WHERE id = ? and account_type = ? and account_number = ?",
					new Object[] { accountCreation.getStrAddress1(), accountCreation.getStrAddress2(),
							accountCreation.getStrAddress3(), accountCreation.getStrPinCode(),
							accountCreation.getStrCountryCode(), accountCreation.getStrState(),
							accountCreation.getStrCity(), "Active", this.sessionDTO.getLoginID(),
							accountCreation.getStrID(), accountCreation.getStrAccountType(),
							accountCreation.getStrAccountNumber() });
			System.out.println("ConfigurationDaoImpl.updateAccountCreation()" + accountCreation);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Ended by Prashant Tayde

	// Added by Sunny Soni for updating account master from instant account Start
	@Override
	public int updateAccountCreationFromInstanceAccount(AccountCreation accountCreation) {
		System.out.println("ConfigurationDaoImpl.updateAccountCreation()" + accountCreation);
		try {
			long mobileNo = Long
					.parseLong((accountCreation.getStrMobileNo() == null ? "" : accountCreation.getStrMobileNo()));
			long phoneNo = Long
					.parseLong((accountCreation.getStrPhoneNo() == null ? "" : accountCreation.getStrPhoneNo()));
			int count = this.jdbcCMSTemplate.update(
					"UPDATE account_master SET title = ?, first_name = ?, middle_name = ?, last_name = ?,"
							+ " gender = ? , dob = ? , email = ?, mobile_no = ?, address1 = ?,  address2 = ?, "
							+ " address3 = ?, pincode = ?, phone_no = ?,  is_instant_account = ?, status = ?, created_by = ?"
							+ "	WHERE id = ? and account_type = ? and account_number = ?",
					new Object[] { accountCreation.getStrTitle(), accountCreation.getStrFirstName(),
							accountCreation.getStrMiddleName(), accountCreation.getStrLastName(),
							accountCreation.getStrGender(), accountCreation.getStrDOB(),
							accountCreation.getStrEmailID(), mobileNo, accountCreation.getStrAddress1(),
							accountCreation.getStrAddress2(), accountCreation.getStrAddress3(),
							accountCreation.getStrPinCode(), phoneNo, "N", "Active", this.sessionDTO.getLoginID(),
							accountCreation.getStrID(), accountCreation.getStrAccountType(),
							accountCreation.getStrAccountNumber() });
			System.out.println("ConfigurationDaoImpl.updateAccountCreation()" + accountCreation);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	// Added by Sunny Soni for updating account master from instant account End

	// Added by Sunny Soni for getting account Number Start
	@Override
	public int updateLastAccountNumber(String updatedAccountNo, String accountType) {
		int count = 0;
		try {
			count = this.jdbcCMSTemplate.update(
					"UPDATE account_type_master SET last_account_number = ? " + "WHERE account_type = ?",
					new Object[] { updatedAccountNo, accountType });

			System.out.println("count::[" + count + "]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public String getAccounNumberBasedOnAccountType(String accountType) {
		try {

			String lastAccNumber = this.jdbcCMSTemplate.queryForObject(
					"select last_account_number from account_type_master where account_type = ?",
					new Object[] { accountType }, String.class);
			System.out.println("ConfigurationDaoImpl.getAccounNumberBasedOnAccountType()" + lastAccNumber);
			System.out.println("lastAccNumber::" + lastAccNumber);
			return lastAccNumber;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountType;

	}

	// Added by Sunny Soni for getting account Number End

	// Added by Prashant Tayde

	@Override
	public List<AccountNumberModel> getAccounNumberListBasedOnAccountType(String accountType) {
		List<AccountNumberModel> accountNumberList = this.jdbcCMSTemplate.query(
				" SELECT account_number AS accountNumber FROM account_master WHERE account_type = ?  ",
				new Object[] { accountType }, (RowMapper) new BeanPropertyRowMapper(AccountNumberModel.class));
		return accountNumberList;
	}

	@Override
	public int addInstantAccount(InstantAccountCreation instantAccountCreation) {
		try {
			int count = this.jdbcCMSTemplate.update(
					" INSERT INTO instant_account_master (participant_id,account_type,quantity,bank_name) VALUES (?,?,?,?) ",
					new Object[] { this.sessionDTO.getParticipantid(), instantAccountCreation.getStrAccountType(),
							instantAccountCreation.getStrQuantity(), instantAccountCreation.getStrAccountName() });
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<InstantAccountCreation> getInstantAccount() {
		List<InstantAccountCreation> instantAccountCreationList = this.jdbcCMSTemplate.query(
				" SELECT id as strID , account_type AS strAccountType , quantity AS strQuantity, "
						+ " first_name AS strFirstName , middle_name AS strMiddleName, last_name AS strLastName "
						+ " from instant_account_master WHERE participant_id = ? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(InstantAccountCreation.class));
		return instantAccountCreationList;
	}

	// Ended by Prashant Tayde

	// Added by Prashant Tayde for getting account creation

	@Override
	public List<AccountCreation> getAccountCreationData(String id) {
		List<AccountCreation> accountCreationList = null;
		try {
			accountCreationList = this.jdbcCMSTemplate.query(
					"SELECT id AS strID, account_type AS strAccountType, account_number AS strAccountNumber, "
							+ "title AS strTitle, first_name AS strFirstName, middle_name AS strMiddleName, "
							+ "last_name AS strLastName, gender AS strGender, dob AS strDOB, "
							+ "address1 AS strAddress1, address2 AS strAddress2, address3 AS strAddress3, pincode AS strPinCode, "
							+ "mobile_no AS strMobileNo, phone_no AS strPhoneNo, email AS strEmailID, "
							+ "city AS strCity, state AS strState, country AS strCountry, created_by AS strCreatedBy, "
							+ "status AS strStatus, is_instant_account As strIsInstantAccount "
							+ "from account_master WHERE id = ? ",
					new Object[] { id }, (RowMapper) new BeanPropertyRowMapper(AccountCreation.class));
			System.out.println("ConfigurationDaoImpl.getAccountCreation()" + accountCreationList);
			return accountCreationList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountCreationList;
	}

	@Override
	public List<AccountDetails> getAccountDetailsData(AccountDetails accountDetails) {
		List<AccountDetails> accountDetailsList = null;
		try {
			accountDetailsList = this.jdbcCMSTemplate.query(
					"SELECT am.id AS strID, am.account_type AS strAccountType, am.account_number AS strAccountNumber, "
							+ " am.credit_limit_category AS strAccountCatogory, "
							+ "am.title AS strTitle, am.first_name AS strFirstName, am.middle_name AS strMiddleName, "
							+ "am.last_name AS strLastName, am.gender AS strGender, am.dob AS strDOB, "
							+ " am.address1 AS strAddress1, am.address2 AS strAddress2, am.address3 AS strAddress3, am.pincode AS strPinCode, "
							+ " am.mobile_no AS strMobileNo,am.phone_no AS strPhoneNo, am.email AS strEmailId, "
							+ " am.city AS strCity, am.state AS strState, am.country AS strCountry , am.creation_date AS strAccountCreationDate, "
							+ " am.available_daily_limit AS strDailyAvailableLimit, am.available_monthly_limit AS strMonthlyAvailableLimit,"
							+ " am.available_yearly_limit AS strYearlyAvailableLimit , am.credit_limit_amount AS strAssignedCreditLimit, "
							+ " am.available_credit_limit AS strAvailableCreditLimit , am.closing_balance AS strAvailableBalance, "
							+ "am.date_of_dormancy AS strDateofDormancy , calm.creation_date AS strAccountCreationDate, "
							+ "atl.single_txn_limit AS strAssignedSingleLimit, atl.daily_txn_limit AS strAssignedDailyLimit, "
							+ "atl.monthly_txn_limit AS strAssignedMonthlyLimit, atl.yearly_txn_limit AS strAssignedYearlyLimit, "
							+ "calm.card_number FROM account_master am "
							+ " INNER JOIN card_account_linkage_master calm ON am.account_number = calm.account_number "
							+ " INNER JOIN account_transaction_limit atl ON calm.account_number = atl.account_number "
							+ " WHERE (am.account_number = ? OR calm.card_number = ? ) AND "
							+ "(am.account_type = ? ) ",
					/*
					 * + "OR  " + " (calm.card_number = '2702980504203726705') ",
					 */

					new Object[] {
							(accountDetails.getStrAccountNumber() != null) ? accountDetails.getStrAccountNumber() : "",
							accountDetails.getStrCardNumber() != null ? accountDetails.getStrCardNumber() : "",
							accountDetails.getStrAccountType() },
					(RowMapper) new BeanPropertyRowMapper(AccountDetails.class));

			System.out.println("ConfigurationDaoImpl.getAccountDetailsData()" + accountDetailsList);
			return accountDetailsList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountDetailsList;
	}

	// Ended by Prashant Tayde

	// Added by Sunny Soni for inserting multiple records of account Start
	@Override
	public int[] batchAccountInsert(List<AccountCreation> accountCreationlist) {
		final String participantId = this.sessionDTO.getParticipantid();
		return this.jdbcCMSTemplate.batchUpdate(
				"INSERT INTO account_master (participant_id, account_type, account_number, first_name, is_instant_account) values(?,?,?,?,?)",
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement psmt, int i) throws SQLException {
						psmt.setString(1, participantId);
						psmt.setString(2, accountCreationlist.get(i).getStrAccountType());
						psmt.setString(3, accountCreationlist.get(i).getStrAccountNumber());
						psmt.setString(4, accountCreationlist.get(i).getStrFirstName());
						psmt.setString(5, "Y");
					}

					@Override
					public int getBatchSize() {
						return accountCreationlist.size();
					}
				});
	}
	// Added by Sunny Soni for inserting multiple records of account End

	// Added by Sunny Soni for get instance or viewed type related account list for
	// edit Start
	@Override
	public List<AccountCreation> getAccountCreationListDataBasedOnTypes(String isInstanceAccountStr) {
		List<AccountCreation> accountCreationList = this.jdbcCMSTemplate.query(
				" SELECT id as strID , account_type AS strAccountType, account_number AS strAccountNumber, title AS strTitle, first_name AS strFirstName, middle_name AS strMiddleName, "
						+ " last_name AS strLastName,gender AS strGender,dob AS strDOB,address1 AS strAddress1 ,address2 AS strAddress2,pincode AS strPinCode, "
						+ " city AS strCity,state AS strState,country AS strCountry,created_by AS strCreatedBy,status AS strStatus "
						+ " from account_master WHERE participant_id = ? and is_instant_account = ?",
				new Object[] { this.sessionDTO.getParticipantid(), isInstanceAccountStr },
				(RowMapper) new BeanPropertyRowMapper(AccountCreation.class));
		System.out.println("ConfigurationDaoImpl.getAccountCreation()" + accountCreationList);
		return accountCreationList;
	}
	// Added by Sunny Soni for get instance or viewed type related account list for
	// edit End

	// Added by Prashant T for Phonecode start
	@Override
	public List<CountryModel> getPhoneCode() {
		List<CountryModel> phoneCodeList = this.jdbcCMSTemplate.query(
				//"SELECT id AS strID, phonecode AS strPhoneCode FROM cfg_country  ", new Object[0],
				"SELECT id AS strID, shortname AS strShortName, phonecode AS strPhoneCode FROM cfg_country  ", new Object[0],
				(RowMapper) new BeanPropertyRowMapper(CountryModel.class));
		System.out.println("ConfigurationDaoImpl.getPhoneCode()" + phoneCodeList);
		return phoneCodeList;
	}
	// Added by Prashant T for Phonecode end

	// Account Limit catorgory changes done by prashant
	@Override
	public List<AccountCreditLimitCategory> getAccountCreditLimiCategory() {
		try {
			List<AccountCreditLimitCategory> creditCategoryLimitList = this.jdbcCMSTemplate.query(
					" SELECT id as strID, credit_type AS strCreditType, credit_limit AS strCreditLimit "
							+ " from account_credit_limit_category  where participant_id = ? ",
					new Object[] { this.sessionDTO.getParticipantid() },
					(RowMapper) new BeanPropertyRowMapper(AccountCreditLimitCategory.class));
			System.out.println("ConfigurationDaoImpl.getAccountCreditLimiCategory()" + creditCategoryLimitList);
			return creditCategoryLimitList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// added changes to save credit limit category data ==prashant T
	@Override
	public int addCreditLimitCategory(AccountCreditLimitCategory accountCreditLimitCategory) {
		try {
			int count = this.jdbcCMSTemplate.update(
					" INSERT INTO account_credit_limit_category	(participant_id , credit_type,credit_limit,created_by) VALUES (?,?,?,?) ",
					new Object[] { this.sessionDTO.getParticipantid(), accountCreditLimitCategory.getStrCreditType(),
							accountCreditLimitCategory.getStrCreditLimit(), this.sessionDTO.getLoginID() });
			return count;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return 0;
	}

	// added changes to get tax type data==prashant T

	@Override
	public List<TaxConfigModel> getTaxType() {
		List<TaxConfigModel> taxlist = this.jdbcCMSTemplate.query(
				" SELECT id as strID , tax_type AS strTaxType FROM tax_config ",
				(RowMapper) new BeanPropertyRowMapper(TaxConfigModel.class));
		return taxlist;

	}

	// added changes to get interest for mcc wise data === prashant T
	@Override
	public List<MccWiseInterestModel> getMccWiseInterest() {
		try {
			List<MccWiseInterestModel> mccWiseInterestList = this.jdbcCMSTemplate.query(
					" SELECT id as strID , account_type as strAccountType , mcc_code AS strMccCode, "
							+ " interest_rate AS strInterestRate, grace_period AS strGracePeriod , payment_received_within AS strPaymentReceivedWithinDays "
							+ " from mcc_wise_interest where participant_id = ? ",
					new Object[] { this.sessionDTO.getParticipantid() },
					(RowMapper) new BeanPropertyRowMapper(MccWiseInterestModel.class));
			System.out.println("ConfigurationDaoImpl.getMccWiseInterest()" + mccWiseInterestList);
			return mccWiseInterestList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// added changes to update account limit credit== prashant T

	@Override
	public int updateAccountLimitCredit(AccountCreditLimitCategory accountCreditLimitCategory) {
		try {
			int count = this.jdbcCMSTemplate.update(
					"UPDATE account_credit_limit_category SET credit_type = ?, credit_limit = ? " + " WHERE id = ? ",
					new Object[] { accountCreditLimitCategory.getStrCreditType(),
							accountCreditLimitCategory.getStrCreditLimit(), accountCreditLimitCategory.getStrID() });
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// added to changes for saving the mcc interest data==prashant T
	@Override
	public int saveMccInterest(MccWiseInterestModel mccWiseInterestModel) {
		try {
			int count = this.jdbcCMSTemplate.update(
					" INSERT INTO mcc_wise_interest (participant_id,account_type,mcc_code,interest_rate,grace_period,payment_received_within,created_by) VALUES (?,?,?,?,?,?,?) ",
					new Object[] { this.sessionDTO.getParticipantid(), mccWiseInterestModel.getStrAccountType(),
							mccWiseInterestModel.getStrMccCode(), mccWiseInterestModel.getStrInterestRate(),
							mccWiseInterestModel.getStrGracePeriod(),
							mccWiseInterestModel.getStrPaymentReceivedWithinDays(), this.sessionDTO.getLoginID() });
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<AccountTransactionLimitation> getAccountTransactionLimitationList(AccountTypeMaster accountTypeMaster) 
	{
		try
		{
			List<AccountTransactionLimitation> accountTxnLimitList = this.jdbcCMSTemplate.query(
					"SELECT single_txn_limit AS strSingleTxnLimit, daily_txn_limit AS strDailyTxnLimit, "
					+"monthly_txn_limit AS strMonthlyTxnLimit, yearly_txn_limit AS strYearlyTxnLimit "
					+ "FROM account_transaction_limitation "
					+ "WHERE participant_id = ? AND account_type = ? ",
					new Object[] { this.sessionDTO.getParticipantid(), accountTypeMaster.getStrAccountType()},
					(RowMapper) new BeanPropertyRowMapper(AccountTransactionLimitation.class));
			return accountTxnLimitList;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int addLoadBalance(LoadBalanceModel loadBalanceModel) {
		try {
			int count = this.jdbcCMSTemplate.update(
					" INSERT INTO account_loading_master (participant_id,account_type,account_number,"
					+ "loaded_balance,created_by) VALUES (?,?,?,?,?) ",
					new Object[] { this.sessionDTO.getParticipantid(), loadBalanceModel.getStrAccountType(),
							loadBalanceModel.getStrAccountNumber(), loadBalanceModel.getStrLoadedBalance(),
							this.sessionDTO.getLoginID() });
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<LoadBalanceModel> getLoadBalance() {
		List<LoadBalanceModel> loadbalanceList = this.jdbcCMSTemplate.query(
				" SELECT id as strID , account_type AS strAccountType , account_number AS strAccountNumber,"
						+ "account_category AS strAccountCategory , total_loading_balance AS strTotalLoadBalance,"
						+ "CHANNEL AS strChannel, transaction_id AS strTransactionId "
						+ " FROM account_loading_master where participant_id = ? ",
				new Object[] { this.sessionDTO.getParticipantid() },
				(RowMapper) new BeanPropertyRowMapper(LoadBalanceModel.class));
		return loadbalanceList;
	}

	@Override
	public List<CardAccountLinkage> getCardAccountLinkage(String accountType, String accountNumber) 
	{
		List<CardAccountLinkage> getCardAccountLinkage = jdbcCMSTemplate.query(QueryConstant.ACCOUNT_LINKAGE_SEARCH,
				new BeanPropertyRowMapper<CardAccountLinkage>(CardAccountLinkage.class),
				new Object[] { accountType, accountNumber });

		System.out.println("ReportDaoImpl.getCardAccountLinkage()" + getCardAccountLinkage.size());
		return getCardAccountLinkage;

	}

	@Override
	public List<CardAccountLinkage> getCardAccountLinkageCard(String cardType, String cardNumber) {
		List<CardAccountLinkage> getCardAccountLinkage = jdbcCMSTemplate.query(QueryConstant.CARD_LINKAGE_SEARCH,
				new BeanPropertyRowMapper<CardAccountLinkage>(CardAccountLinkage.class),
				new Object[] { cardType, cardNumber });

		System.out.println("ReportDaoImpl.getCardAccountLinkage()" + getCardAccountLinkage.size());
		return getCardAccountLinkage;

	}

	@Override
	public List<AccountTypeMaster> getAmountTransactionlist() {
		List<AccountTypeMaster> AccountTransactionList = this.jdbcCMSTemplate.query( // select
																						// participant_id,mcc_code,account_type
																						// from
																						// mcc_wise_percentange_account_type
				" Select id as strID,account_type as strAccountType from account_type_master ",
				(RowMapper) new BeanPropertyRowMapper(AccountTypeMaster.class));
		System.out.println("ConfigurationDaoImpl.getAmountTransactionlist()" + AccountTransactionList);
		return AccountTransactionList;
	}

	@Override
	public List<MccCodeModel> getMccCodeList() {
		List<MccCodeModel> mccCodeList = this.jdbcCMSTemplate.query(
				" SELECT id AS strID , mcc_code AS strMccCode , mcc_desc AS strMccDesc FROM merchant_category_code_master ",
				(RowMapper) new BeanPropertyRowMapper(MccCodeModel.class));
		return mccCodeList;

	}

	@Override
	public List<AccountTransactionLimitModel> getTransactionLimitData(String accountType) {
		List<AccountTransactionLimitModel> limitList = this.jdbcCMSTemplate.query(
				" SELECT single_txn_limit AS strAssignedSingleLimit,daily_txn_limit AS strAssignedDailyLimit, "
						+ " monthly_txn_limit AS strAssignedMonthlyLimit,yearly_txn_limit AS strAssignedYearlyMonthlyLimit FROM account_transaction_limit "
						+ " WHERE account_type = 'NORMAL' ",
				(RowMapper) new BeanPropertyRowMapper(AccountTransactionLimitModel.class));
		System.out.println("ConfigurationDaoImpl.getTransactionLimitData()" + limitList);
		return limitList;

	}

	@Override
	public List<AddressProofDocumentTypeMaster> getAddressProofDocumentTypeMasters(String participantId) 
	{
		try 
		{
			List<AddressProofDocumentTypeMaster> addressProofdocumenttList = this.jdbcCMSTemplate.query(
					"SELECT id AS strId, document_type AS strDocumentType "
					+"FROM address_proof_document_type_master "
					+ "WHERE participant_id = ?",
					new Object[] {participantId},
					(RowMapper) new BeanPropertyRowMapper(AddressProofDocumentTypeMaster.class));
		    System.out.println("ConfigurationDaoImpl.getAddressProofDocumentTypeMasters()"+addressProofdocumenttList);
			return addressProofdocumenttList;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<IdentityProofDocumentTypeMaster> getIdentityProofDocumentTypeMasters(String participantId) 
	{ 
		try 
		{
			List<IdentityProofDocumentTypeMaster> identityProofDocumentList = this.jdbcCMSTemplate.query(
					"SELECT id AS strId, document_type AS strDocumentType "
					+"FROM identity_proof_document_type_master "
					+ "WHERE participant_id = ?",
					new Object[] {participantId},
					(RowMapper) new BeanPropertyRowMapper(IdentityProofDocumentTypeMaster.class));
			return identityProofDocumentList;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CategoryListModel> getCategoryList() 
	{
		List<CategoryListModel> categoryList = this.jdbcCMSTemplate.query("SELECT id as strID , "
				+ "type AS strType , description AS strDescription FROM account_type_category_master ",
				(RowMapper) new BeanPropertyRowMapper(CategoryListModel.class));
		return categoryList;
	}

	@Override
	public int updateMCCWiseInterest(MccWiseInterestModel mccWiseInterestModel) 
	{
		try {
			int count = this.jdbcCMSTemplate.update(
					"UPDATE mcc_wise_interest SET account_type = ?, mcc_code = ? , interest_rate = ? , grace_period = ? "
							+ " WHERE id = ? ",
					new Object[] { mccWiseInterestModel.getStrAccountType(), mccWiseInterestModel.getStrMccCode(),
							mccWiseInterestModel.getStrInterestRate(), mccWiseInterestModel.getStrGracePeriod(),
							mccWiseInterestModel.getStrID() });
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<AccountCreditLimitCategory> getCreditTypelist() {
		List<AccountCreditLimitCategory> AccountTypeList = this.jdbcCMSTemplate.query(""
				+ "Select id as strID, credit_type as strCreditType "
				+ "from account_credit_limit_category",
                (RowMapper) new BeanPropertyRowMapper(AccountCreditLimitCategory.class));
        System.out.println("ConfigurationDaoImpl.getCreditTypelist()"+AccountTypeList);
        return AccountTypeList;
	}
	
	@Override
	public List<AccountCreation> getInterestOutstanding(String accountNumber) {
		try {
	List<AccountCreation> getInterestOutstanding  = jdbcCMSTemplate.query(
	        " SELECT account_number AS strAccountNumber, "
	        + " total_outstanding_balance AS strTotalOutstandingBalance ,"
			+ " total_outstanding_interest AS strTotalOutstandingInterest "
			+ " FROM account_master WHERE account_number = ? ",
	new BeanPropertyRowMapper<AccountCreation>(AccountCreation.class), new Object[] {accountNumber });
		System.out.println("Query Executed:::"+getInterestOutstanding);
		return getInterestOutstanding;
	}catch (Exception e) {
		e.printStackTrace();
	}
		return null;
}

	@Override
	public List<AccountInterestMaster> getOutStandingList(String accountNumber) 
	{
		try 
		{
			List<AccountInterestMaster> getInterestOutstandingList  = this.jdbcCMSTemplate.query(QueryConstant.INTEREST_OUTSTANDING_LIST,
			new BeanPropertyRowMapper<AccountInterestMaster>(AccountInterestMaster.class), new Object[] { accountNumber });
			return getInterestOutstandingList;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
//	public List<CardAccountLinkage> getCardAccountLinkages(CardAccountLinkage cardAccountLinkage) 
	public CardAccountLinkage getCardAccountLinkages(CardAccountLinkage cardAccountLinkage)
	{
		try
		{
	        List<CardAccountLinkage> cardAccountLinkages = this.jdbcCMSTemplate.query(
					"SELECT account_type AS strAccountType, account_number AS strAccountNumber, "
					+"card_number AS strCardNumber, card_type AS strCardType, "
					+"card_status AS strCardStatus, account_status AS strAccountStatus, "
					+"creation_date AS strCreationDate FROM card_account_linkage_master "
					+ "WHERE participant_id = ? AND account_number = ? AND card_number = ?",
					new Object[] 
					{
						cardAccountLinkage.getStrParticipantID(), 
						(cardAccountLinkage.getStrAccountNumber()!=null) ? cardAccountLinkage.getStrAccountNumber() : "", 
						(cardAccountLinkage.getStrCardNumber()!=null) ? cardAccountLinkage.getStrCardNumber() : ""
					},
					(RowMapper) new BeanPropertyRowMapper(CardAccountLinkage.class));
					
	        if(cardAccountLinkages!=null && cardAccountLinkages.size() > 0)
	        {
	        	cardAccountLinkage = cardAccountLinkages.get(0);
	        }
	        return cardAccountLinkage;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AccountTransactionLimitModel getAccountTransactionLimitModel(AccountTransactionLimitModel accountTransactionLimitModel) 
	{
		try 
		{
			List<AccountTransactionLimitModel> accountTransactionLimitModels = this.jdbcCMSTemplate.query(
					"SELECT account_type AS strAccountType, single_txn_limit AS strSingleTxnLimit, "
					+"daily_txn_limit AS strDailyTxnLimit, monthly_txn_limit AS strMonthlyTxnLimit, "
					+"yearly_txn_limit AS strYearlyTxnLimit FROM account_transaction_limit "
					+ "WHERE participant_id = ? AND account_type = ?",
					new Object[] 
					{
						accountTransactionLimitModel.getStrParticipantId(), 
						(accountTransactionLimitModel.getStrAccountType()!=null) ? accountTransactionLimitModel.getStrAccountType() : ""
					},
					(RowMapper) new BeanPropertyRowMapper(AccountTransactionLimitModel.class));
					
	        if(accountTransactionLimitModels!=null && accountTransactionLimitModels.size() > 0)
	        {
	        	accountTransactionLimitModel = accountTransactionLimitModels.get(0);
	        }
	        return accountTransactionLimitModel;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public String getCountryName(String countryCode) {
		String countryName = this.jdbcCMSTemplate.queryForObject("select country_name from cfg_country where id = ?",
				new Object[] { countryCode }, String.class);
		System.out.println("countryName::" + countryName);
		return countryName;
	}

	@Override
	public String getStateName(String id) 
	{
		String stateName = this.jdbcCMSTemplate.queryForObject("SELECT state_name FROM cfg_state WHERE id = ?",
				new Object[] { id }, String.class);
		System.out.println("stateName::" + stateName);
		return stateName;
	}

	@Override
	public String getCityName(String id) 
	{
		String cityName = this.jdbcCMSTemplate.queryForObject("SELECT city_name FROM cfg_city WHERE id = ?",
				new Object[] { id }, String.class);
		System.out.println("cityName::" + cityName);
		return cityName;
	}

	// added by sagark for linked Account Wallet Date:22-12-2022
	@Override
	public List<AccountLinkedWalletMaster> getLinkedAccountList(AccountLinkedWalletMaster accountLinkedWalletMaster) 
	{
		try
		{
			String sqlQuery = "SELECT wallet_account_number AS strWalletAccountNumber, "
					+ " account_type AS strAccountType,mcc_code AS strMcc,account_number AS strAccountNumber, "
					+ " percentage AS strPercentage,available_balance AS strAvailableBalance "
					+ " FROM wallet_account_master where account_number = ?";
			List<AccountLinkedWalletMaster> linkedaccountlist = this.jdbcCMSTemplate.query(
				sqlQuery, 
				new Object[] {accountLinkedWalletMaster.getStrAccountNumber()},
				(RowMapper) new BeanPropertyRowMapper(AccountLinkedWalletMaster.class)
			);
			System.out.println("ConfigurationDaoImpl.getLinkedAccountList()" + linkedaccountlist);
			return linkedaccountlist;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	// added by sagark for linked Account Wallet Date:23-12-2022
	@Override
	public List<AccountLinkedWalletMaster> getlinkedAccountWallet(String walletAccountNumber) {
		try {
			List<AccountLinkedWalletMaster> getlinkedAccountWallet = jdbcCMSTemplate.query(
					"SELECT wallet_account_number AS strWalletAccountNumber,"
							+ " account_type AS strAccountType,mcc_code AS strMcc,"
							+ " percentage AS strPercentage,available_balance AS strAvailableBalance FROM wallet_account_master WHERE  account_number= ? ",
					new BeanPropertyRowMapper<AccountLinkedWalletMaster>(AccountLinkedWalletMaster.class),
					new Object[] { walletAccountNumber });
			System.out.println("Query Executed:::" + getlinkedAccountWallet);
			return getlinkedAccountWallet;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AccountCreditLimitCategory> getAccountCreditTypeCategorylist() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//added on 23-12-22 by ankit{for displaying in drop-down Box}
	
		@Override
		public List<AccountTypeMaster> getAccounTypeFromAcocuntMaster() {
			try {
				String sql = "SELECT account_type AS strAccountType, description AS strDescription FROM account_type_master WHERE is_credit_type = 'N'";
				
				List<AccountTypeMaster> accountTypeMasterList = this.jdbcCMSTemplate.query(sql,
						new Object[] {},
						(RowMapper) new BeanPropertyRowMapper(AccountTypeMaster.class));
				System.out.println("ConfigurationDaoImpl.getAccounTypeMaster()" + accountTypeMasterList);
				
				return accountTypeMasterList;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		//added on 23-12-22 by ankit

		//added on 23-12-22 by ankit to insert Data 
		//modified on 24-12-22 added date and time
		@Override
		public int addLoadBalances(LoadBalanceModel loadBalanceModel) {
			try {
				String sql = "INSERT INTO account_loading_master(participant_id,account_type,account_number,account_category,date_of_loading,time_of_loading,loaded_balance,channel,transaction_id,created_by) VALUES (?,?,?,?,?,?,?,?,?,?)";
				int count = this.jdbcCMSTemplate.update(sql,
						new Object[] { this.sessionDTO.getParticipantid(), loadBalanceModel.getStrAccountType(),
								loadBalanceModel.getStrAccountNumber(),loadBalanceModel.getStrAccountCategory(),
								loadBalanceModel.getStrDateOfLoading(),loadBalanceModel.getStrTimeOfLoading(),
								loadBalanceModel.getStrLoadedBalance(),loadBalanceModel.getStrChannel(),
								loadBalanceModel.getStrTransactionId(), this.sessionDTO.getLoginID() });
				
				return count;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		//added on 23-12-22 by ankit

	// added on 23-12-22
	// method to fetch a single acocunt
	@Override
	public List<AccountCreation> getAccountMasterDetails(String strAccountType, String strAccountNumber) {
		try {
			String sql = "SELECT load_count as strLoadCounter, "
					+ "opening_balance as strOpeningBalance,closing_balance as strClosingBalance"
					+ " from account_master where account_type = ? AND account_number = ?";

			List<AccountCreation> accountDetails = this.jdbcCMSTemplate.query(sql,
					new Object[] { strAccountType, strAccountNumber },
					(RowMapper) new BeanPropertyRowMapper(AccountCreation.class));
			return accountDetails;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// added on 23-12-22 by ankit to update BalanceInAccoutnMaster and increment
	// Counter
	// modified 26/12/22
	@Override
	public int updateBalance(BalanceUpdateInAccountMaster balance) 
	{
		String str = "UPDATE account_master SET opening_balance=?, closing_balance=?, load_count=? where account_type=? AND account_number=?";
		try 
		{
			int count = this.jdbcCMSTemplate.update(str,
					new Object[] { balance.getStrOpeningBalance(), balance.getStrClosingBalance(),
							balance.getStrLoadCount(), balance.getStrAccountType(), balance.getStrAccountNumber() });
			return count;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}
	// added on 23-12-22 by ankit

	// added on 26-12-12 to fetch category_type
	@Override
	public String getCategoryTypeFromAccountTypeMaster(String accountType) 
	{
		try 
		{
			String sql = "select category_type as strCategoryType from account_type_master where account_type=?";
			String categoryType = this.jdbcCMSTemplate.queryForObject(sql, new Object[] { accountType }, String.class);
			return categoryType;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	// modified on 27-12-22 by ankit to fetch the allowed load Cash
	@Override
	public String getAllowLoadCashFromAccountTypeMaster(String accountType) 
	{
		try 
		{
			String sql = "select allow_load_cash as strAllowLoadCash from account_type_master where account_type=?";
			String categoryType = this.jdbcCMSTemplate.queryForObject(sql, new Object[] { accountType }, String.class);
			return categoryType;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	// added on 27-12-22 by ankit

	// added on 27-12-22 tp fetch the load channels from the channels table
	@Override
	public List<Channels> getChannels() 
	{
		try 
		{
			String sql = "SELECT channel_type as strChannelType, "
					+ "channel_description as strChannelDescription "
					+ "FROM channels";
			List<Channels> channelTypes = this.jdbcCMSTemplate.query(sql,
					(RowMapper) new BeanPropertyRowMapper(Channels.class));
			return channelTypes;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	// added on 27/12/22

	@Override
	public String getAvailableBalance(String accountType, String accountNumber) 
	{
		try 
		{
			String sql = "SELECT closing_balance as strClosingBalance "
					+ "FROM account_master where account_type = ? AND account_number = ?";
			String closingBalance = this.jdbcCMSTemplate.queryForObject(sql,
					new Object[] { accountType, accountNumber }, String.class);
			return closingBalance;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AccountTranMaster> getLastTransaction() 
	{
		try 
		{
			String sql = "SELECT txn_id as strTxn_id,local_tran_date as strLocalTranDate FROM account_tran_master ORDER BY id DESC LIMIT 1";
			List<AccountTranMaster> transactionId = this.jdbcCMSTemplate.query(sql,
					(RowMapper) new BeanPropertyRowMapper(AccountTranMaster.class));
			/*
			 * List<String> transactionId = this.jdbcCMSTemplate.queryForList(sql,
			 * String.class);
			 */
			return transactionId;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addTransaction(AccountTranMaster accountTranMaster) 
	{
		try 
		{
			String sql = "INSERT INTO account_tran_master(participant_id, "
					+ "txn_id, "
					+ "tran_type, to_account_number, transaction_amount, local_tran_time, "
					+ ""
					+ "local_tran_date) VALUES (?,?,?,?,?,?,?)";
			int count = this.jdbcCMSTemplate.update(sql,
					new Object[] { this.sessionDTO.getParticipantid(), accountTranMaster.getStrTxn_id(),
							accountTranMaster.getStrTran_type(), accountTranMaster.getStrTo_account_number(),
							accountTranMaster.getStrTransaction_amount(), accountTranMaster.getStrLocal_tran_time(),
							accountTranMaster.getStrLocal_tran_date() });
			return count;
		}
		catch (Exception e) 
		{
			System.out.println("ConfigurationDaoImpl.addTransaction()" + e);
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addEntryInStatement(AccountStatement accountStatement) 
	{
		try 
		{
			String sql = "INSERT INTO account_statement(participant_id,account_number,account_type,closing_balance,created_by,created_date,is_gl_type,transaction_amount,transaction_date,transaction_details,transaction_id,txn_type,txn_mode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int count = this.jdbcCMSTemplate.update(sql,
					new Object[] { this.sessionDTO.getParticipantid(), accountStatement.getStrAccountNumber(),
							accountStatement.getStrAccountType(), accountStatement.getStrClosingBalance(),
							this.sessionDTO.getLoginID(), accountStatement.getStrCreatedDate(),
							accountStatement.getStrIsGLType(), accountStatement.getStrTransactionAmount(),
							accountStatement.getStrTransactionDate(), accountStatement.getStrTransactionDetails(),
							accountStatement.getStrTransactionID(), accountStatement.getStrTransactionType(),
							accountStatement.getStrTransactionMode() });
			return count;
		}
		catch (Exception e) 
		{
			System.out.println("ConfigurationDaoImpl.addEntryInStatement()" + e);
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int addGLEntryInStatement(GLAccountStatement accountStatement) 
	{
		try 
		{
			String sql = "INSERT INTO account_statement(participant_id,account_number,"
					+ "account_type,closing_balance,created_by,created_date,is_gl_type,"
					+ "transaction_amount,transaction_date,transaction_details,"
					+ "transaction_id,txn_type,txn_mode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			int count = this.jdbcCMSTemplate.update(sql,
					new Object[] { this.sessionDTO.getParticipantid(), accountStatement.getStrAccountNumber(),
							accountStatement.getStrAccountType(), accountStatement.getStrClosingBalance(),
							this.sessionDTO.getLoginID(), accountStatement.getStrCreatedDate(),
							accountStatement.getStrIsGLType(), accountStatement.getStrTransactionAmount(),
							accountStatement.getStrTransactionDate(), accountStatement.getStrTransactionDetails(),
							accountStatement.getStrTransactionID(), accountStatement.getStrTranType(),
							accountStatement.getStrTranMode() });
			return count;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}
	/*
	 * public String getCountryName(String countryCode) { String countryName =
	 * this.jdbcCMSTemplate.
	 * queryForObject("select country_name from cfg_country where id = ?", new
	 * Object[] { countryCode }, String.class); System.out.println("countryName::" +
	 * countryName); return countryName; }
	 */

	/*
	 * * Account Statement Changes by Jyoti Shirahatti
	 */
	@Override
	public List<AccountStatement> getAccountStatementByDate(String fromDate, String toDate) {
		List<AccountStatement> getAccountStatementByDate = jdbcCMSTemplate.query(
				QueryConstant.ACCOUNT_STATEMENT_DATE_SEARCH,
				new BeanPropertyRowMapper<AccountStatement>(AccountStatement.class), new Object[] { fromDate, toDate });

		System.out.println("ReportDaoImpl.getCardAccountLinkage()" + getAccountStatementByDate.size());
		return getAccountStatementByDate;

	}

	@Override
	public List<AccountStatement> getAccountStatement(String accountType, String accountNumber, String fromDate,
			String toDate) {
		List<AccountStatement> getAccountStatement = jdbcCMSTemplate.query(QueryConstant.ACCOUNT_STATEMENT_SEARCH,
				new BeanPropertyRowMapper<AccountStatement>(AccountStatement.class),
				new Object[] { accountType, accountNumber, fromDate, toDate });

		System.out.println("ReportDaoImpl.getCardAccountLinkage()" + getAccountStatement.size());
		return getAccountStatement;

	}

	// added on 06-01-2023 to get the GL Account Types
	@Override
	public List<GLAccountLoadingMaster> getGLAccountTypes() {
		try {
			String sql = "SELECT gl_account_type as strGLAccountType,account_description as strGLAccountDescription"
					+ " FROM gl_account_type_master";
			List<GLAccountLoadingMaster> GlAccounts = this.jdbcCMSTemplate.query(sql,
					(RowMapper) new BeanPropertyRowMapper(GLAccountLoadingMaster.class));
			return GlAccounts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//Added on 09-01-2023 to fetch the Closing Balance of GL modified on 12-01-2023
			@Override
			public String fetchClosingBalanceOfGlAccount(String strAccountType,String strAccountNumber) 
			{
				try {	
					String sql = "SELECT closing_balance as strClosingBalance "
							+ "FROM gl_account_type_master "
							+ "where gl_account_type = ? AND account_number=?";

					//String sql = "SELECT closing_balance as strClosingBalance FROM gl_account_type_master where account_number=?";
					String closingBalance = this.jdbcCMSTemplate.queryForObject(sql,
							new Object[] {strAccountType,strAccountNumber},
							String.class);
					return closingBalance;
				}catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
	// Added on 09-01-2023 to fetch the LoadCount

	// Added on 09-01-2023 to update the Gl Account count and balance
	@Override
	public int updateGLAccountDetails(UpdateGLAccount updateGLAccount) {
		try {
			// String updateLoadCountAndClosingBalance = "UPDATE gl_account_loading_master
			// SET closing_balance=?, load_count=? where account_number=?";
			// updateGLAccount.getStrLoadCountString()
			String updateClosingBalance = "UPDATE gl_account_type_master SET closing_balance = ? "
					+ "where account_number=?";
			int chedckUpdate = this.jdbcCMSTemplate.update(updateClosingBalance, new Object[] {
					updateGLAccount.getStrClosingBalanceString(), updateGLAccount.getStrAccountNumber() });
			return chedckUpdate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Added on 09-01-2023 to add load in GL account by ankit
	@Override
	public int addLoadInGLAccount(GLAccountLoadingMaster glAccountLoadingMaster) {
		try {
			String sql = "INSERT INTO gl_account_loading_master(account_type,account_description,account_number,date_of_loading,time_of_loading,tran_id,channel,loaded_amount,account_created_by) VALUES (?,?,?,?,?,?,?,?,?)";
			int count = this.jdbcCMSTemplate.update(sql, new Object[] { glAccountLoadingMaster.getStrAccountCreatedBy(),
					glAccountLoadingMaster.getStrGLAccountDescription(), glAccountLoadingMaster.getStrAccountNumber(),
					glAccountLoadingMaster.getStrDateOfLoading(), glAccountLoadingMaster.getStrTimeOfLoading(),
					glAccountLoadingMaster.getStrTransactionId(), glAccountLoadingMaster.getStrChannel(),
					glAccountLoadingMaster.getStrLoadedBalance(), this.sessionDTO.getLoginID() });
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// added on 10-02-2023 to fetch GL Account Type and GL account Description by
	// ankit
	@Override
	public List<GLAccountLoadingMaster> fetchGLAccountDetails(String accountNumber) {

		try {
			String fetchAccountDetails = "SELECT gl_account_type as strGLAccountType,account_description as strGLAccountDescription FROM gl_account_type_master where account_number=?";
			List glAccountDetails = this.jdbcCMSTemplate.query(fetchAccountDetails, new Object[] { accountNumber },
					(RowMapper) new BeanPropertyRowMapper(GLAccountLoadingMaster.class));
			return glAccountDetails;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Added by sagark for accountType date:08/01/23

	@Override
	public List<GlAccountTypeChargesMaster> getAccountTypecharges() {
		List<GlAccountTypeChargesMaster> glaccountTypeCreation = this.jdbcCMSTemplate.query(
				"SELECT distinct(charge_type) AS strChargeType FROM gl_account_type_charges",

				(RowMapper) new BeanPropertyRowMapper(AccountCreation.class));

		System.out.println("ConfigurationDaoImpl.getAccountType()" + glaccountTypeCreation);
		return glaccountTypeCreation;
	}

	@Override
	public List<GlAccountTypeChargesMaster> getGlAccountType() {
		try {
			List<GlAccountTypeChargesMaster> glAccountTypelist = this.jdbcCMSTemplate.query(
					"SELECT distinct(charge_type) AS strChargeType FROM gl_account_type_charges",

					(RowMapper) new BeanPropertyRowMapper(GlAccountTypeChargesMaster.class));
			System.out.println("ConfigurationDaoImpl.getGlAccountType()" + glAccountTypelist);
			return glAccountTypelist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Added by Sunny Soni for test [Start]
	@Override
	public List<Map<String, Object>> getCardNoList() 
	{
		try {
			List<Map<String, Object>> rows = this.jdbcCMSTemplate.queryForList(
					" SELECT card_details.enc_card AS card"
					+ " FROM card_details",
					new Object[] { });
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<Map<String, Object>> row=new ArrayList<Map<String, Object>>();
			
			if (rows != null && !rows.isEmpty()) 
			{
				map.put("card",aesEncDec.decrypt(rows.get(0).get("card").toString()));
			}
			row.add(map);
			return row;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getSinglemaskedCardNumber(String cardNumber, String cardType) 
	{
		try 
		{	
			String sql = "SELECT card_number FROM card_details WHERE enc_card = ? and card_type = ?";

			String existingCardNumber = this.jdbcCMSTemplate.queryForObject(sql,
					new Object[] {aesEncDec.encrypt(cardNumber), cardType},
					String.class);
			return existingCardNumber;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CardTypeModel> getCardTypeInfo(CardTypeModel cardTypeModel)
	{
		try 
		{
			String sqlQuery = "SELECT cfg_card_type.id AS strCardId, cfg_card_type.card_type AS strCardTypeData, "
					+ "card_details.card_number AS strCardNumber, card_details.enc_card AS strCardEncCard, "
					+ "cfg_card_type.description  AS strCardDescr, card_details.token_card AS strTokenCard, "
					+ "cfg_card_type.bin AS strBin,card_details.expiry_date as strCardExpDate "
					+ "FROM card_details LEFT JOIN cfg_card_type ON card_details.card_type = cfg_card_type.id "
					+ "WHERE "
					//+ "card_details.enc_card = ? and card_details.card_type = ? ";
					//+ "card_details.enc_card = ? ";
					+ "card_details.card_number = ? ";
			List<CardTypeModel> cardTypeModels = this.jdbcCMSTemplate.query(sqlQuery,
					//new Object[] {aesEncDec.encrypt(cardTypeModel.getCardNumber(), aesEncDec.getSecretKeys()), cardTypeModel.getStrCardType()},
					//new Object[] {aesEncDec.encrypt(cardTypeModel.getCardNumber(), aesEncDec.getSecretKeys())},
					new Object[] {cardTypeModel.getStrCardNumber().trim()},
					(RowMapper) new BeanPropertyRowMapper(CardTypeModel.class));
			
			System.out.println("cardTypeModels:::" +cardTypeModels);
			return cardTypeModels;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Added by Sagark for transaction Type Creation Start
	@Override
	public int addTranscationTypeData(TransactionTypeModel transactionTypeModel) 
	{
		try 
		{
			String sql = "INSERT INTO transaction_type_master"
					+ "(id,participant_id,txn_type_id,txn_type_description,"
					+ "txn_type_keyword,processing_code,gl_account_type,gl_account_no) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			int count = this.jdbcCMSTemplate.update(sql, new Object[] {transactionTypeModel.getStrID(),transactionTypeModel.getStrParticipantId(),
					transactionTypeModel.getStrTxnTypeId(),transactionTypeModel.getStrTxnTypeDescrp(),
					transactionTypeModel.getStrTxnTypeKeyWord(),transactionTypeModel.getStrProcessingCode(),
					transactionTypeModel.getStrGLAccountType(),
					transactionTypeModel.getStrGLAccountNumber()
					});
			return count;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	//Added by Sagark for transaction Type Creation End

	@Override
	public boolean checkTransactionTypeDataAlreadyConfigured(TransactionTypeModel transactionTypeModel) {
		try
		{
			String sql = "SELECT count(*) AS count FROM transaction_type_master "
					+ "WHERE txn_type_id = ? OR txn_type_keyword = ? OR processing_code = ? OR gl_account_type = ? OR gl_account_no = ? ";
			int count = jdbcCMSTemplate.queryForObject(sql, Integer.class, 
					new Object[] 
					{ 
						transactionTypeModel.getStrTxnTypeId(), 
						transactionTypeModel.getStrTxnTypeKeyWord(),
						transactionTypeModel.getStrProcessingCode(),
						transactionTypeModel.getStrGLAccountType(),
						transactionTypeModel.getStrGLAccountNumber()
					});
			return count > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*@Override
	public cardAcntLinkageMaster getInfofrmCardLinkage(String cardNumber) 
	{
		try 
		{
			String sqlQuery = "SELECT account_type, account_number, "
					+ "card_status, account_status FROM card_account_linkage_master "
					+ "WHERE enc_card_number = ? ";
			
			List<cardAcntLinkageMaster> cardDetails = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {aesEncDec.encrypt(cardNumber)}, 
					(RowMapper) new BeanPropertyRowMapper(cardAcntLinkageMaster.class));
			
			System.out.println("cardTypeModels:::" +cardDetails);
			
			if (cardDetails !=null && cardDetails.size() > 0) 
			{
				return cardDetails.get(0);
			}
			// return cardDetails;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	@Override
	public cardAcntLinkageMaster getInfofrmCardLinkage(String cardNumber) 
	{
		try 
		{
			String sqlQuery = "SELECT account_type, account_number, "
					+ "card_status, account_status FROM card_account_linkage_master "
					+ "WHERE card_number = ? ";
			
			List<cardAcntLinkageMaster> cardDetails = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {cardNumber}, 
					(RowMapper) new BeanPropertyRowMapper(cardAcntLinkageMaster.class));
			
			System.out.println("cardTypeModels:::" +cardDetails);
			
			if (cardDetails !=null && cardDetails.size() > 0) 
			{
				return cardDetails.get(0);
			}
			// return cardDetails;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEntry(TranMaster tranMaster)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = sdf.format(new Date());
			
			StringBuilder insertQuerySb = new StringBuilder("INSERT INTO TRAN_MASTER ");
			insertQuerySb.append("(");
			insertQuerySb.append("TXN_ID, PARTICIPANT_ID, LOCAL_TRAN_DATE, LOCAL_TRAN_TIME,");
			insertQuerySb.append("MTI, TRAN_TYPE, PROCESSING_CODE, TRANSACTION_AMOUNT, RESPONSE_CODE,");
			insertQuerySb.append("AUTH_CODE, RRN, STAN, MID,");
			insertQuerySb.append("TID, MCC, MASK_CARD_NO, ECN_CARD_NO");
			insertQuerySb.append(")");
			insertQuerySb.append(" VALUES ");
			insertQuerySb.append("(");
			insertQuerySb.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
			insertQuerySb.append(")");
			
			
			int count = this.jdbcCMSTemplate.update(insertQuerySb.toString(), 
					new Object[] 
					{
							tranMaster.getStrtxnId(),
							tranMaster.getStrParticipantId(),
							/*
							 * new Date().getTime()+"", "",
							 */
							/* new Date().getTime()+"", */
							formattedDate,
							LocalTime.now()+"",
							tranMaster.getStrMti(), 
							tranMaster.getStrTxnType(),
							tranMaster.getStrProcessingCode(),
							tranMaster.getStrTxnAmount(),
							tranMaster.getStrResponseCode(),
							tranMaster.getStrAuthCode(),
							tranMaster.getStrRRN(),
							tranMaster.getStrStan(),
							tranMaster.getStrMid(),
							tranMaster.getStrTID(),
							tranMaster.getStrMccCode(),
							tranMaster.getStrMaskCardNo(),
							tranMaster.getStrEcnCardNo()
					});
			
			return count;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	//Added By Jyoti
	/*
	@Override
	public CardDetailsList getCardDetails(String cardNumber) 
	{
		try 
		{
			System.out.println("cardNumber:::[" +cardNumber+"]");
			StringBuilder sqlQuery = new StringBuilder("SELECT cs.status_flag AS strCardStatus , cd.expiry_date AS strExpiryDate , cd.card_number AS  strCardNumber ,cd.token_card AS strTokenCard ,cd.issued_to_customer AS strCustomerIssuedDate ");
			sqlQuery.append("FROM card_details cd INNER JOIN cfg_status cs ON cd.card_status = cs.id WHERE cd.enc_card = ? ") ;

			List<CardDetailsList> cardDetails = this.jdbcCMSTemplate.query(sqlQuery.toString(), new Object[] {aesEncDec.encrypt(cardNumber)}, (RowMapper) new BeanPropertyRowMapper(CardDetailsList.class));

			System.out.println("cardTypeModels:::" +cardDetails);
			if (cardDetails !=null && cardDetails.size() > 0) 
			{
				return cardDetails.get(0);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/
	
	public CardDetailsList getCardDetails(String cardNumber) 
	{
		try 
		{
			System.out.println("cardNumber:::[" +cardNumber+"]");
			StringBuilder sqlQuery = new StringBuilder("SELECT cs.status_flag AS strCardStatus , cd.expiry_date AS strExpiryDate , cd.card_number AS  strCardNumber ,cd.token_card AS strTokenCard ,cd.issued_to_customer AS strCustomerIssuedDate ");
			sqlQuery.append("FROM card_details cd INNER JOIN cfg_status cs ON cd.card_status = cs.id WHERE cd.card_number = ? ") ;

			List<CardDetailsList> cardDetails = this.jdbcCMSTemplate.query(sqlQuery.toString(), new Object[] {cardNumber}, (RowMapper) new BeanPropertyRowMapper(CardDetailsList.class));

			System.out.println("cardTypeModels:::" +cardDetails);
			if (cardDetails !=null && cardDetails.size() > 0) 
			{
				return cardDetails.get(0);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean checkStanAlreadyExist(String stan)
	{
		try
		{
			String sql = "SELECT COUNT(*) AS cnt FROM tran_master atm WHERE atm.stan = ? ";
			int count = jdbcCMSTemplate.queryForObject(sql, Integer.class, 
					new Object[] 
					{ 
							stan
					});
			return count > 0;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int addCardAccountLinkageCMS(CardAccountLinkage cardAccountLinkage)
	{
		try
		{ 
			StringBuilder insertQuerySb = new StringBuilder("INSERT INTO card_account_linkage_master(");
			insertQuerySb.append("participant_id, account_type, account_number, card_number, enc_card_number,");
			insertQuerySb.append("card_id, card_type, card_description, card_status, account_status, token_card,");
			insertQuerySb.append("card_holder_name, network_type, card_expiry_date, card_cvv, creation_date, created_by) ");
			insertQuerySb.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			int count = this.jdbcCMSTemplate.update(insertQuerySb.toString(),
				new Object[]
				{ 
					this.sessionDTO.getParticipantid(), cardAccountLinkage.getStrAccountType(),
					 cardAccountLinkage.getStrAccountNumber(), cardAccountLinkage.getStrCardNumber(),
					 cardAccountLinkage.getStrCardEncCard(), cardAccountLinkage.getStrCardId(),
					 cardAccountLinkage.getStrCardType(),cardAccountLinkage.getStrCardDescription(),
					 cardAccountLinkage.getStrCardStatus(), cardAccountLinkage.getStrAccountStatus(), 
					 cardAccountLinkage.getStrTokenCard(), cardAccountLinkage.getStrCardHolderName(), 
					 cardAccountLinkage.getStrNetworkType(), cardAccountLinkage.getStrCardExpDate(), 
					 cardAccountLinkage.getStrCardCvv(), new Date(), this.sessionDTO.getLoginID()
				}
			);
			System.out.println("ConfigurationDaoImpl.addCardAccountLinkageCMS()" + cardAccountLinkage);
			return count;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getNetworkType(CardAccountLinkage cardAccountLinkage)
	{
		try
		{
			String networkType = this.jdbcCMSTemplate.queryForObject("SELECT network_scheme FROM cfg_bin cfg WHERE cfg.bin = ?", new Object[] { cardAccountLinkage.getStrBin() }, String.class);
			System.out.println("networkType::" + networkType);
			return networkType;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getCardHolderName(CardAccountLinkage cardAccountLinkage) 
	{
		try 
		{
			String strCardHolderName = this.jdbcCMSTemplate.queryForObject(	"SELECT CONCAT_WS(' ', am.emboss_line1, am.emboss_line2) AS strCardHolderName FROM card_plastic_details am WHERE am.card_number = ?",
					new Object[] { cardAccountLinkage.getStrTokenCard() }, String.class);
			System.out.println("strCardHolderName::" + strCardHolderName);
			return strCardHolderName;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean isCardAlreadyLinked(String tokenCard) 
	{
		try
		{
			if (tokenCard == null) 
			{
				return false;
			}
			String sql = "SELECT count(*) AS cnt FROM card_account_linkage_master AS calm WHERE calm.token_card = '"+tokenCard.trim()+"'";
			int count = jdbcCMSTemplate.queryForObject(sql, Integer.class, new Object[] {});
			return count > 0;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int updateIssueCardToCustomer(CardDetails cardDetails) 
	{
		try {
			CardDetails cardDetails1 = cardDetails;
			cardDetails1 = configurationService.getOldCardDetails(cardDetails);
			if (cardDetails1 != null)
			{
				configurationService.updateAuditData(cardDetails, cardDetails1, "card_details");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = this.jdbcCMSTemplate.update(
				" UPDATE card_details SET issued_to_customer = ? WHERE token_card = ? ",
				new Object[] { cardDetails.getStrIssuedToCustomer(), cardDetails.getStrTokenCard() });
		return count;
		
	}

	@Override
	public List<ConnectionConfigModel> getEndPoints() {
		List<ConnectionConfigModel> endpointsList = this.jdbcCMSTemplate.query(
				" SELECT id AS id, name AS name FROM cfg_connection Where service_type = 'server' ",
				new Object[0],
				(RowMapper) new BeanPropertyRowMapper(ConnectionConfigModel.class));
		return endpointsList;
		
	}
	
	
	
	@Override
	public CardStatus getCardStatus(String tokenCard) {
		try 
		{
			System.out.println("cardNumber:::[" +tokenCard+"]");
			StringBuilder sqlQuery = new StringBuilder("SELECT csh.participant_id AS strParticipant_ID , csh.card_number AS strCardNumber , csh.member_number AS strMemberNo , ");
			sqlQuery.append("csh.status_code AS strStatusCode , csh.card_status_description AS strDescription , csh.status_change_user AS StrStatusChangeUser ");
			sqlQuery.append("FROM card_status_history csh WHERE csh.card_number = ? ") ;

			List<CardStatus> cardStatus = this.jdbcCMSTemplate.query(sqlQuery.toString(), new Object[] {tokenCard}, (RowMapper) new BeanPropertyRowMapper(CardStatus.class));
			System.out.println("cardTypeModels:::" +cardStatus);
			if (cardStatus !=null && cardStatus.size() > 0) 
			{
				return cardStatus.get(0);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addAccountTypeDesc(AccountTypeMaster accountTypeMaster) {
		try {
			int count = this.jdbcCMSTemplate.update(
					" INSERT INTO cfg_account_type (Account_description,participant_id) VALUES (?,?) ",
					new Object[] {accountTypeMaster.getStrAccountType(),this.sessionDTO.getParticipantid()
							});
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public int addEndPointEntry(ConnectionConfigModel connConfig) {
		try {
			int count = this.jdbcCMSTemplate.update(
					" INSERT INTO cfg_endpoint (endpoint,url) VALUES (?,?) ",
					new Object[] {connConfig.getName(),connConfig.getIp()
							});
			return count;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	
	}

	@Override
	public BinModel getBinOldDataforUpdate(BinModel binModel2) 
	{
		try 
		{
			String sqlQuery = " SELECT bin AS strBin, description AS strBinDesc, "
					+ " network_scheme AS strNetworkScheme, flag AS flag FROM cfg_bin "
					+ "WHERE id = ? AND bin = ? ";
			
			List<BinModel> binDetails = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {binModel2.getStrID(),binModel2.getStrBin()}, 
					(RowMapper) new BeanPropertyRowMapper(BinModel.class));
			
			System.out.println("binDetails:::" +binDetails);
			
			if (binDetails !=null && binDetails.size() > 0) 
			{
				return binDetails.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public BinModel getBinOldDataforDelete(BinModel binModel) {
		try 
		{
			String sqlQuery = " SELECT bin AS strBin, description AS strBinDesc, "
					+ " network_scheme AS strNetworkScheme, flag AS flag FROM cfg_bin "
					+ " WHERE id = ? ";
			
			List<BinModel> binDetails = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {binModel.getStrID()}, 
					(RowMapper) new BeanPropertyRowMapper(BinModel.class));
			
			System.out.println("binDetails:::" +binDetails);
			
			if (binDetails !=null && binDetails.size() > 0) 
			{
				return binDetails.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BranchTypeModel getBranchTypeOldDataForUpdate(BranchTypeModel branchTypeModel) 
	{
		try 
		{
			String sqlQuery = "select id AS strID, branch_name AS strBranchName,branch_desc AS strBranchDesc,participant_id AS strSelectID FROM cfg_branch "
					+ " WHERE id = ? AND branch_name = ? ";
			
			List<BranchTypeModel> branchTypeModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {branchTypeModel.getStrID(),branchTypeModel.getStrBranchName() }, 
					(RowMapper) new BeanPropertyRowMapper(BranchTypeModel.class));
			
			System.out.println("branchTypeModels:::" +branchTypeModels);
			
			if (branchTypeModels !=null && branchTypeModels.size() > 0) 
			{
				return branchTypeModels.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public BranchTypeModel getBranchTypeOldDataForDelete(BranchTypeModel branchTypeModel) {
		try 
		{
			String sqlQuery = "select id AS strID, branch_name AS strBranchName,branch_desc AS strBranchDesc,participant_id AS strSelectID FROM cfg_branch "
					+ " WHERE id = ? ";
			
			List<BranchTypeModel> branchTypeModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {branchTypeModel.getStrID()}, 
					(RowMapper) new BeanPropertyRowMapper(BranchTypeModel.class));
			
			System.out.println("branchTypeModels:::" +branchTypeModels);
			
			if (branchTypeModels !=null && branchTypeModels.size() > 0) 
			{
				return branchTypeModels.get(0);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public BranchCodeModel getOldBranchCodeDataForUpdate(BranchCodeModel branchCodeModel) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strID,branch_code AS strBranchCode, branch_Address AS strBranchAddress, "
					+ " participant_id AS strPartID, branch_type AS strBranchType, ext_id AS strExtID FROM cfg_branch_code "
					+ " WHERE id = ? AND branch_code = ? ";
			
			List<BranchCodeModel> branchCodeModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {branchCodeModel.getStrID(), branchCodeModel.getStrBranchCode() }, 
					(RowMapper) new BeanPropertyRowMapper(BranchCodeModel.class));
			
			System.out.println("branchCodeModels:::" +branchCodeModels);
			
			if (branchCodeModels !=null && branchCodeModels.size() > 0) 
			{
				return branchCodeModels.get(0);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public BranchCodeModel getOldBranchCodeDataForDelete(BranchCodeModel branchCodeModel) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strID,branch_code AS strBranchCode, branch_Address AS strBranchAddress, "
					+ " participant_id AS strPartID, branch_type AS strBranchType, ext_id AS strExtID FROM cfg_branch_code "
					+ " WHERE id = ?  ";
			
			List<BranchCodeModel> branchCodeDetails = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {branchCodeModel.getStrID() }, 
					(RowMapper) new BeanPropertyRowMapper(BranchCodeModel.class));
			
			System.out.println("branchCodeDetails:::" +branchCodeDetails);
			
			if (branchCodeDetails !=null && branchCodeDetails.size() > 0) 
			{
				return branchCodeDetails.get(0);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AccountTypeModel getOldAccountTypeDataForUpdate(AccountTypeModel accountTypeModel) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strID,Account_description AS strAccountDesc,participant_id AS strPart,ext_id AS strExt "
					+ "FROM cfg_account_type where id = ? AND Account_description = ? ";
			
			List<AccountTypeModel> accountTypeModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { accountTypeModel.getStrID(), accountTypeModel.getStrAccountDesc()}, 
					(RowMapper) new BeanPropertyRowMapper(AccountTypeModel.class));
			
			System.out.println("accountTypeModels:::" +accountTypeModels);
			
			if (accountTypeModels !=null && accountTypeModels.size() > 0) 
			{
				return accountTypeModels.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AccountTypeModel getOldAccountTypeDataForDelete(AccountTypeModel accountTypeModel) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strID,Account_description AS strAccountDesc,participant_id AS strPart,ext_id AS strExt "
					+ "FROM cfg_account_type where id = ? ";
			
			List<AccountTypeModel> accountTypeModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { accountTypeModel.getStrID()}, 
					(RowMapper) new BeanPropertyRowMapper(AccountTypeModel.class));
			
			System.out.println("accountTypeModels:::" +accountTypeModels);
			
			if (accountTypeModels !=null && accountTypeModels.size() > 0) 
			{
				return accountTypeModels.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AddressTypeModel getOldAddressTypeDataForDelete(AddressTypeModel addressTypeModel) {
		try 
		{
			String sqlQuery = "SELECT id AS strID, participant_id AS strPart,address_description AS strAddressDesc,ext_id AS strExt "
					+ "FROM cfg_address_type WHERE ID = ? ";
			
			List<AddressTypeModel> addresstypeModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {addressTypeModel.getStrID() }, 
					(RowMapper) new BeanPropertyRowMapper(AddressTypeModel.class));
			
			System.out.println("addresstypeModels:::" +addresstypeModels);
			
			if (addresstypeModels !=null && addresstypeModels.size() > 0) 
			{
				return addresstypeModels.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public AddressTypeModel getOldAddressTypeDataForUpdate(AddressTypeModel addressTypeModel) {
		try 
		{
			String sqlQuery = "SELECT id AS strID, participant_id AS strPart,address_description AS strAddressDesc,ext_id AS strExt "
					+ "FROM cfg_address_type WHERE ID = ? AND address_description = ? ";
			
			List<AddressTypeModel> addresstypeModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {addressTypeModel.getStrID(), addressTypeModel.getStrAddressDesc() }, 
					(RowMapper) new BeanPropertyRowMapper(AddressTypeModel.class));
			
			System.out.println("addresstypeModels:::" +addresstypeModels);
			
			if (addresstypeModels !=null && addresstypeModels.size() > 0) 
			{
				return addresstypeModels.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public CardTypeModel getOldCardTypeDataForDelete(CardTypeModel cardTypeModel) 
	{
	try 
	{
		String sqlQuery = "SELECT id AS strID, card_type AS strCardType,description AS strCardDesc,"
				+ "participant_id AS strPartID,bin AS strBin,bin_suffix AS strBinSuffix,"
				+ "card_template_id AS strCardTemplateId,card_plastic_id AS strCardPlasticId,"
				+ "cvn_method_id AS strCVN,pinmailer_format AS strPinFormat,decimalization_table AS strDecTable,ncmc_flag AS strFlag,"
				+ "endpoint AS strEndpoint,card_gen_type AS strCardGenerationType,card_token AS iCardTokenFlag FROM cfg_card_type "
				+ "WHERE id = ? ";
		
		List<CardTypeModel> cardTypeDetails = this.jdbcCMSTemplate.query(sqlQuery,
				new Object[] { cardTypeModel.getStrID()}, 
				(RowMapper) new BeanPropertyRowMapper(CardTypeModel.class));
		
		System.out.println("cardTypeDetails:::" +cardTypeDetails);
		
		if (cardTypeDetails !=null && cardTypeDetails.size() > 0) 
		{
			return cardTypeDetails.get(0);
		}
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
		return null;
	}
	
	@Override
	public CardTypeModel getOldCardTypeDataForUpdate(CardTypeModel cardTypeModel) 
	{
	try 
	{
		String sqlQuery = "SELECT description AS strCardDesc,"
				+ "bin AS strBin,bin_suffix AS strBinSuffix,"
				+ "ncmc_flag AS strFlag,endpoint AS strEndpoint "
				+ "FROM cfg_card_type WHERE id = ? ";
		
		List<CardTypeModel> cardTypeModelDetails = this.jdbcCMSTemplate.query(sqlQuery,
				new Object[] {cardTypeModel.getStrID() }, 
				(RowMapper) new BeanPropertyRowMapper(CardTypeModel.class));
		
		System.out.println("cardTypeModelDetails:::" +cardTypeModelDetails);
		
		if (cardTypeModelDetails !=null && cardTypeModelDetails.size() > 0) 
		{
			return cardTypeModelDetails.get(0);
		}
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
		return null;
	}
	
	
	@Override
	public CardTypeModel getOldCardTypeMccDataForDelete(CardTypeModel cardtypeModel) 
	{
		try 
		{
			//use OR in Where condition
			String sqlQuery = "SELECT card_type AS strCardType, mcc AS strMcc, participant_id AS strPartID FROM card_type_mcc "
					+ "WHERE card_type = ? ";
			
			List<CardTypeModel> cardTypeData = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { cardtypeModel.getStrCardType() }, 
					(RowMapper) new BeanPropertyRowMapper(CardTypeModel.class));
			
			System.out.println("cardTypeData:::" +cardTypeData);
			
			if (cardTypeData !=null && cardTypeData.size() > 0) 
			{
				return cardTypeData.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public CardPlasticModel getOldCardPlasticData(CardPlasticModel cardPlasticModel) 
	{
		try 
		{
			
			String sqlQuery = "SELECT id AS strID, participant_id AS participantId,card_type_id AS strCardType,"
					+ "vendor_id AS strVendorID,CVV_position AS strCVV,servicecode_position AS strServicePos,"
					+ "expiry_date_position AS strExpPos,card_seq_position AS strSeqPos FROM "
					+ "cfg_card_plastic WHERE id = "+cardPlasticModel.getStrID()+" ";
			
			List<CardPlasticModel> cardPlasticModeldetails = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {}, 
					(RowMapper) new BeanPropertyRowMapper(CardPlasticModel.class));
			
			System.out.println("cardPlasticModeldetails:::" +cardPlasticModeldetails);
			
			if (cardPlasticModeldetails !=null && cardPlasticModeldetails.size() > 0) 
			{
				return cardPlasticModeldetails.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EmailTypeModel getOldEmailData(EmailTypeModel emailTypeModel) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strID,email_description AS strEmail,participant_id AS strPart, "
					+ "ext_id AS strExt FROM cfg_email_type WHERE id = "+emailTypeModel.getStrID()+" ";
			
			List<EmailTypeModel> emailTypeModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {}, 
					(RowMapper) new BeanPropertyRowMapper(EmailTypeModel.class));
			
			System.out.println("emailTypeModels:::" +emailTypeModels);
			
			if (emailTypeModels !=null && emailTypeModels.size() > 0) 
			{
				return emailTypeModels.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public NcmcServiceModel getOldNCMCData(NcmcServiceModel ncmcServiceModel) 
	{
		try 
		{
			//use OR
			String sqlQuery = "SELECT id AS strID,participant_id AS strPartID,ncmc_code AS strNcmcID, "
					+ "active AS strFlag FROM cfg_ncmc_code WHERE id = ? OR ncmc_code= ? ";
			
			List<NcmcServiceModel> ncmcServiceModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {ncmcServiceModel.getStrID(),ncmcServiceModel.getStrNcmcID() }, 
					(RowMapper) new BeanPropertyRowMapper(NcmcServiceModel.class));
			
			System.out.println("ncmcServiceModels:::" +ncmcServiceModels);
			
			if (ncmcServiceModels !=null && ncmcServiceModels.size() > 0) 
			{
				return ncmcServiceModels.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public KeyConfigModel getOldKeyData(KeyConfigModel keyConfigModel) {
		try 
		{
			//Need to Write a query
			String sqlQuery = "";
			
			List<KeyConfigModel> binDetails = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { }, 
					(RowMapper) new BeanPropertyRowMapper(KeyConfigModel.class));
			
			System.out.println("binDetails:::" +binDetails);
			
			if (binDetails !=null && binDetails.size() > 0) 
			{
				return binDetails.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CardDetails getOldCardDetails(CardDetails cardDetails) 
	{
		try 
		{
			String sqlQuery = "SELECT id,participant_id,order_branch,card_type,instant_flag,card_number,token_card,enc_card,member_number,customer_id,card_bin,service_code,card_issue_date,cardholder_since,expiry_date,daily_pin_retry_limit,daily_pin_retry_count,consecutive_pin_retry_limit,consecutive_pin_retry_count,"
					+ "online_atm_limit,online_pos_limit,online_ecom_limit,offline_limit,monthly_limit,weekly_limit,daily_limit,card_issued_user,last_updated_user,emboss_flag,issued_to_customer "
					+ "FROM card_details WHERE token_card = "+cardDetails.getStrTokenCard()+" ";
			
			List<CardDetails> cardDetailss = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { }, 
					(RowMapper) new BeanPropertyRowMapper(CardDetails.class));
			
			System.out.println("cardDetailss:::" +cardDetailss);
			
			if (cardDetailss !=null && cardDetailss.size() > 0) 
			{
				return cardDetailss.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CardTemplateModel getoldCardTemplateData(CardTemplateModel cardTemplateModel) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strID,participant_id AS strParticipantId,default_card_Status AS cardStatus,"
					+ "card_type_id AS strCardType,service_code AS strServiceCode,card_issue_type_id AS strCardIssue,"
					+ "card_mailer_Issue_flag AS strCardMailerFlag,pin_mailer_Issue_flag AS strPinMailerFlag,"
					+ "temporary_limit_activation_flag AS strTempLimitFlag,daily_pin_retry_limit AS strDailyPinLimit,"
					+ "consecutive_pin_retry_limit AS strConPinLimit,online_atm_limit AS strOnlineAtmLimit,"
					+ "online_pos_limit AS strOnlinePosLimit,online_ecom_limit AS strOnlineEcomLimit,"
					+ "offline_limit AS strOfflineLimit,monthly_limit AS strMonthlyLimit,"
					+ "weekly_limit AS strWeeklyLimit,daily_limit AS strDailyLimit,expiry_cfg_type AS strExpiryType,expiry_month AS expiryMonth,expiry_year AS expiryYear "
					+ "FROM cfg_card_template WHERE id = ? ";
			
			List<CardTemplateModel> cardTemplateModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { cardTemplateModel.getStrID()}, 
					(RowMapper) new BeanPropertyRowMapper(CardTemplateModel.class));
			
			System.out.println("cardTemplateModels:::" +cardTemplateModels);
			
			if (cardTemplateModels !=null && cardTemplateModels.size() > 0) 
			{
				return cardTemplateModels.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CardTokenModel getOldCardTokenData(CardTokenModel cardTokenModel) 
	{
		try 
		{
			//Need to Write a query
			String sqlQuery = "";
			
			List<CardTokenModel> cardTokenModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { cardTokenModel.getStrCardType()}, 
					(RowMapper) new BeanPropertyRowMapper(CardTokenModel.class));
			
			System.out.println("cardTokenModels:::" +cardTokenModels);
			
			if (cardTokenModels !=null && cardTokenModels.size() > 0) 
			{
				return cardTokenModels.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CardDetails getOldCustomerIdOnTokencard(CardDetails cardDetails) {
		try 
		{
			String sqlQuery = "SELECT AS customer_id AS strCustomerId From card_details"
					+ " Where token_card = ? ";
			
			List<CardDetails> cardDetailss = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { cardDetails.getStrTokenCard()}, 
					(RowMapper) new BeanPropertyRowMapper(CardDetails.class));
			
			System.out.println("cardDetailss:::" +cardDetailss);
			
			if (cardDetailss !=null && cardDetailss.size() > 0) 
			{
				return cardDetailss.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CardPlasticModel getOldCardPlasticForUpdate(CardPlasticModel cardPlasticModel) 
	{
		try 
		{
			String sqlQuery = "";
			
			List<CardPlasticModel> cardPlasticModels = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { cardPlasticModel.getStrCardType(),cardPlasticModel.getStrSelectID()}, 
					(RowMapper) new BeanPropertyRowMapper(CardPlasticModel.class));
			
			System.out.println("cardPlasticModels:::" +cardPlasticModels);
			
			if (cardPlasticModels !=null && cardPlasticModels.size() > 0) 
			{
				return cardPlasticModels.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CustomerDetails getOldCustomerDetailsOnId(CustomerDetails customerDetails) 
	{
		try 
	{
		String sqlQuery = "SELECT participant_id AS strParticipantID, citizen_id AS strCitizenID,"
				+ "cif_key AS strCIFKey,first_name AS strFirstName,middle_name AS strMiddleName,last_Name AS strLastName,"
				+ "dob AS strDOB,sex AS strGender,mother_maiden_name AS strMotherMaidenName "
				+ "WHERE id = ? ";
		
		List<CustomerDetails> customerDetailss = this.jdbcCMSTemplate.query(sqlQuery,
				new Object[] { customerDetails.getId()}, 
				(RowMapper) new BeanPropertyRowMapper(CustomerDetails.class));
		System.out.println("customerDetailss:::" +customerDetailss);
		
		if (customerDetailss !=null && customerDetailss.size() > 0) 
		{
			return customerDetailss.get(0);
		}
	} 
	catch (Exception e) 
	{
		e.printStackTrace();
	}
		return null;
	}

	@Override
	public AddressDetailsList getOldAddressDetailsBasedOnCustId(AddressDetailsList addressDetails) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strAddressID, customer_id AS strCustomerID,"
					+ "address_type_id AS strAddressType,primary_flag AS strAddressPrimaryFlag,address1 AS strAddress1,"
					+ "address2 AS strAddress2,address3 AS strAddress3,city AS strCity,state AS strState,"
					+ "country_code AS strCountryCode,pin_code AS strPinCode From customer_address "
					+ "WHERE customer_id = ? ";
			
			List<AddressDetailsList> addressDetailsList = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { addressDetails.getStrCustomerID()}, 
					(RowMapper) new BeanPropertyRowMapper(AddressDetailsList.class));
			System.out.println("addressDetailsList:::" +addressDetailsList);
			
			if (addressDetailsList !=null && addressDetailsList.size() > 0) 
			{
				return addressDetailsList.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PhoneDetailsList getOldPhoneDetailsData(PhoneDetailsList phoneDetails) 
	{
		try 
		{
			String sqlQuery = " SELECT id AS strPhoneID, customer_id AS strCustomerID,"
					+ " phone_type_id AS strPhoneType,primary_flag AS strPhonePrimaryFlag,"
					+ " phone_no AS strPhoneNo "
					+" From customer_contact "
					+ "WHERE customer_id = ? ";
			
			List<PhoneDetailsList> phoneDetailsList = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { phoneDetails.getStrCustomerID()}, 
					(RowMapper) new BeanPropertyRowMapper(PhoneDetailsList.class));
			System.out.println("phoneDetailsList:::" +phoneDetailsList);
			
			if (phoneDetailsList !=null && phoneDetailsList.size() > 0) 
			{
				return phoneDetailsList.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BulkUploadData getOldBulkUploadData(BulkUploadData bulkUploadData) {
		
		return null;
	}

	@Override
	public DocumentDetailsList getDocumentDetailsList(DocumentDetailsList documentDetailsList) 
	{
		try 
		{
			String sqlQuery = " SELECT id AS strDocumentID, customer_id AS strCustomerId, "
					+ " document_type_id AS strDocumentType,document_number AS strDocumentTypeDesc, "
					+" From customer_document "
					+ "WHERE customer_id = ? ";
			
			List<DocumentDetailsList> phoneDetailsList = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { documentDetailsList.getStrDocumentID()}, 
					(RowMapper) new BeanPropertyRowMapper(DocumentDetailsList.class));
			System.out.println("phoneDetailsList:::" +phoneDetailsList);
			
			if (phoneDetailsList !=null && phoneDetailsList.size() > 0) 
			{
				return phoneDetailsList.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EmailDetailsList getEmailDetailsList(EmailDetailsList emailDetailsList) 
	{
		try 
		{
			String sqlQuery = " SELECT id AS strID, customer_id AS strCustomerId, "
					+ " email_type_id AS strEmailType,primary_flag AS strEmailPrimaryFlag,email_id AS strEmailID "
					+" From customer_email "
					+ "WHERE customer_id = ? ";
			
			List<EmailDetailsList> emailDetailsLists = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] { emailDetailsList.getStrCustomerId()}, 
					(RowMapper) new BeanPropertyRowMapper(EmailDetailsList.class));
			System.out.println("emailDetailsLists:::" +emailDetailsLists);
			
			if (emailDetailsLists !=null && emailDetailsLists.size() > 0) 
			{
				return emailDetailsLists.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CardDetails getOldCardPlasticDetailsData(CardDetails cardDetails) 
	{
		try 
		{
			String sqlQuery = "SELECT id AS strId, participant_id AS strParticipantID,card_number AS strCardNumber,member_number AS strMemberNo, "
					+ "card_issue_code AS strCardIssueCode,emboss_line1 AS strEmbossLine1,emboss_line2 AS strEmbossLine2, "
					+ "encode_first_name AS strEncodeFirstName,encode_middle_name AS strEncodeMiddleName,encode_last_name AS strEncodeLastName,pinmailer_flag AS strPinMailerIssueFlag,cardmailer_issue_date AS strCardMailerIssueDate "
					+ "FROM card_plastic_details WHERE card_number = "+cardDetails.getStrTokenCard()+"";
			
			List<CardDetails> cardDetailss = this.jdbcCMSTemplate.query(sqlQuery,
					new Object[] {}, 
					(RowMapper) new BeanPropertyRowMapper(CardDetails.class));
			System.out.println("cardDetailss:::" +cardDetailss);
			if (cardDetailss !=null && cardDetailss.size() > 0) 
			{
				return cardDetailss.get(0);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CardAccountLinkage getOldCardAccountLinkageDetails(CardAccountLinkage cardAccountLinkage) 
	{
		return null;
	}

	@Override
	public List<CardTypeModel> getCardTypeBasedOnParticipantId(CardTypeModel cardTypeModel) 
	{
		try 
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT cct.card_type AS strCardType, cct.description AS strCardDesc,");
					sqlQuery.append("cct.participant_id AS strPartID, cct.bin AS strBin, cct.bin_suffix AS strBinSuffix,");
					sqlQuery.append("cct.cvn_method_id AS strCVN, ce.endpoint AS strEndpoint,");
					sqlQuery.append("cct.pinmailer_format AS strPinFormat, cct.decimalization_table AS strDecTable,");
					sqlQuery.append("cct.id AS strID, cct.ncmc_flag AS strFlag, cct.card_gen_type as strCardGenerationType ");
					sqlQuery.append("FROM cfg_card_type cct LEFT JOIN cfg_endpoint ce ON cct.endpoint = ce.id WHERE participant_id="+cardTypeModel.getStrParticipantID()+" ");
					
					List<CardTypeModel> viewCardTypelist = this.jdbcCMSTemplate.query(sqlQuery.toString(),
					new BeanPropertyRowMapper<CardTypeModel>(CardTypeModel.class),
					new Object[] 
					{
							
					});
			return viewCardTypelist;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CardTypeModel> getCardType(CardTypeModel cardTypeModel) 
	{
		try 
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT CONCAT(cct.card_type,'-',cct.description) AS strCardType, ");
					sqlQuery.append("cct.participant_id AS strPartID, cct.bin AS strBin, cct.bin_suffix AS strBinSuffix,");
					sqlQuery.append("cct.cvn_method_id AS strCVN, ce.endpoint AS strEndpoint,");
					sqlQuery.append("cct.pinmailer_format AS strPinFormat, cct.decimalization_table AS strDecTable,");
					sqlQuery.append("cct.id AS strID, cct.ncmc_flag AS strFlag, cct.card_gen_type as strCardGenerationType ");
					sqlQuery.append("FROM cfg_card_type cct LEFT JOIN cfg_endpoint ce ON cct.endpoint = ce.id WHERE participant_id="+cardTypeModel.getStrParticipantID()+" ");
					
					List<CardTypeModel> viewCardTypelist = this.jdbcCMSTemplate.query(sqlQuery.toString(),
					new BeanPropertyRowMapper<CardTypeModel>(CardTypeModel.class),
					new Object[] 
					{});
			return viewCardTypelist;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	
}
