package com.traneco.clientSearch.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.traneco.clientSearch.model.CardDetails;
import com.traneco.clientSearch.model.CardDetailsList;
import com.traneco.clientSearch.model.CustomerDetails;
import com.traneco.clientSearch.services.ClientSearchService;
import com.traneco.cmsaudit.services.AuditService;
import com.traneco.common.KeyValueBean;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.QueryConstant;
import com.traneco.common.util.AESEncDec;
import com.traneco.configuration.model.CardTypeModel;
import com.traneco.configuration.services.ConfigurationService;
import com.traneco.service.model.CardAccountLinkage;
import com.traneco.service.model.ServiceBean;


@Repository
public class CardDetailsDaoImpl implements CardDetailsDao {

	@Autowired
	SessionDTO sessionDTO;

	@Autowired
	JdbcTemplate jdbcCMSTemplate;
	
	@Autowired
	AESEncDec aesEncDec;
	
	@Autowired
	AuditService auditService;
	
	@Autowired
	ClientSearchService clientSearchService;
	
	@Autowired
	ConfigurationService configurationService;
	
	
	@Override
	public CardDetailsList getCard(String cardNo, String mbr) 
	{
		CardDetailsList cardDetailsList = null;
		cardDetailsList = (CardDetailsList) jdbcCMSTemplate.queryForObject(QueryConstant.GET_CARD_DETAILS,
				new Object[] { cardNo, sessionDTO.getParticipantid() },
				new BeanPropertyRowMapper<CardDetailsList>(CardDetailsList.class));
		return cardDetailsList;
	}

	@Override
	public List<KeyValueBean> getCardStatus(String partID) 
	{
		List<KeyValueBean> statuList = jdbcCMSTemplate.query(QueryConstant.Get_Cfg_Status, new Object[] {},
				new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));
		return statuList;
	}

	@Override
	public List<KeyValueBean> getNCMCService(String partID, String cardNo) 
	{
		List<KeyValueBean> ncmcList = jdbcCMSTemplate.query(QueryConstant.GET_NCMC_SERVICE,
				new Object[] { partID, cardNo }, new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));
		return ncmcList;
	}

	@Override
	public List<KeyValueBean> getDocumentType() 
	{
		List<KeyValueBean> docTypeList = jdbcCMSTemplate.query(QueryConstant.GET_DOCUMENT_TYPE,
				new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));
		return docTypeList;
	}

	@Override
	public int updateClient(CustomerDetails customerDetails) 
	{
		return jdbcCMSTemplate.update(QueryConstant.UPDATE_CLIENT,
				new Object[] { customerDetails.getStrCitizenID(), customerDetails.getStrCIFKey(),
						customerDetails.getStrFirstName(), customerDetails.getStrMiddleName(),
						customerDetails.getStrLastName(), customerDetails.getStrGender(), customerDetails.getStrDOB(),
						customerDetails.getStrMotherMaidenName(), customerDetails.getStrCustomerID() });
	}

	@Override
	public int addCardMCC(CardTypeModel cardTypeModel) 
	{
		
		try 
		{
			CardTypeModel cardTypeModel2 = cardTypeModel;
			cardTypeModel = clientSearchService.getCardMccOldDetails(cardTypeModel2);
			if(cardTypeModel.getCardNumber() != null && cardTypeModel.getStrPartID() != null)
			{
				configurationService.deletedAuditRecords(cardTypeModel2, "card_mcc_service");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		int count = jdbcCMSTemplate.update(QueryConstant.DELETE_CARD_MCC,
				new Object[] { cardTypeModel.getStrSelectID(), sessionDTO.getParticipantid() });
		
		if (cardTypeModel.getStrMcc() != null && cardTypeModel.getStrMcc().length() > 0) {
			for (String data : cardTypeModel.getStrMcc().split("\\,")) {
				count += jdbcCMSTemplate.update(QueryConstant.INSERT_CARD_MCC,
						new Object[] { cardTypeModel.getStrSelectID(), data, sessionDTO.getParticipantid() });
			}
		}
		return count;
	}

	//created by ankit
	@Override
	public List<CardDetails> getClearCard(CardDetails cardDetails) {
		//String sql = "select SELECT CLEAR_CARD(card_details.enc_card) AS card, card_token.`token_details` AS token FROM `card_details` LEFT JOIN `card_token` ON card_details.id = card_token.`card_id` WHERE token_card=? OR enc_card= CARD_TOKEN(?)";
		try 
		{
			String sql = "SELECT card_details.enc_card AS strClearCardNo FROM `card_details` WHERE token_card = ?";
			List<CardDetails> query = jdbcCMSTemplate.query(sql,
					new Object[] {cardDetails.getStrTokenCard()},
					new BeanPropertyRowMapper<CardDetails>(CardDetails.class));
			query.get(0).setStrClearCardNo(aesEncDec.decrypt(query.get(0).getStrClearCardNo()));
			
			return query;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	//created by ankit
	
	@Override
	public int updateCardStatus(CardDetails cardDetails) 
	{
		try {
			String sql = "update card_details set card_status = "+2+" where account_number = ?";
			int query = jdbcCMSTemplate.update(sql,
					new Object[] {cardDetails.getStrTokenCard()}
					);
			
			return query;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	@Override
	public CardAccountLinkage getLinkageCardDetailsOnCustId(CardAccountLinkage cardAccountLinkage) 
	{
		try
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT card_status, account_status FROM card_account_linkage_master ");
			sqlQuery.append("WHERE account_number = ? AND token_card = ? ");
			
	        List<CardAccountLinkage> cardAccountLinkages = this.jdbcCMSTemplate.query(
					sqlQuery.toString(),
					(RowMapper<CardAccountLinkage>) new BeanPropertyRowMapper<CardAccountLinkage>(CardAccountLinkage.class),
					new Object[] 
					{
						cardAccountLinkage.getStrAccountNumber(),
						cardAccountLinkage.getStrTokenCard()
					});
					
	        if(cardAccountLinkages != null && cardAccountLinkages.size() > 0)
	        {
	        	return cardAccountLinkages.get(0);
	        }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int updateCardStatusActive(CardAccountLinkage cardAccountLinkage)
	{
		try {
			String sql = "update card_account_linkage_master set card_status = "+"Active"+" and account_status = "+"Active"+" where account_number = ?";
			int query = jdbcCMSTemplate.update(sql,
					new Object[] {cardAccountLinkage.getStrTokenCard()}
					);
			return query;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int updateCardStatusBlock(CardAccountLinkage cardAccountLinkage)
	{
		try 
		{
			String sql = "update card_account_linkage_master set card_status = "+"Block"+" and account_status = "+"Block"+" where account_number = ? ";
			int query = jdbcCMSTemplate.update(sql,
					new Object[] 
							{
							cardAccountLinkage.getStrTokenCard()
							}
					);
			return query;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	
	//Neeed to Change in Query by prashant Tayde ---04 Aug 2023
	@Override
	public List<CardDetails> getCardDetailsData(CardDetails cardDetails) 
	{
		try 
		{
			String sql = "Select * from card_details where issued_to_customer is NOT NULL";
			List<CardDetails> cardDetailsList = jdbcCMSTemplate.query(sql,
					new Object[] {},
					new BeanPropertyRowMapper<CardDetails>(CardDetails.class));
			return cardDetailsList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addInstantInfo(ServiceBean serviceBean) 
	{
		int count = this.jdbcCMSTemplate.update(
				"INSERT INTO instant_card_creation (card_type,branch_code,quantity,emboss_line1,emboss_line2,encode_first_name,encode_middle_name,encode_last_name,created_date,created_by) VALUES (?,?,?,?,?,?,?,?,?,?)",
				new Object[] { serviceBean.getInCardType(),serviceBean.getInBranchCode(),serviceBean.getInQty(),serviceBean.getInEmbossLine1(),serviceBean.getInEmbossLine2(),serviceBean.getInEncodeFirstName(),
						serviceBean.getInEncodeMiddleName(),serviceBean.getInEncodeLastName(), new Timestamp(System.currentTimeMillis()), sessionDTO.getLoginID()});
		return count;
	}
	
	
	@Override
	public List<CardDetailsList> getUnsoldCardList(String cardType) 
	{
		try 
		{
			List<CardDetailsList> unsoldCardList = jdbcCMSTemplate.query(QueryConstant.UNSOLD_CARD_NO_LIST,
					new Object[] { cardType }, new BeanPropertyRowMapper<CardDetailsList>(CardDetailsList.class));
			unsoldCardList.get(0).setStrCardNumber(aesEncDec.decrypt(unsoldCardList.get(0).getStrCardNumber()));
			;
			return unsoldCardList;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CustomerDetails getOldClientDetails(CustomerDetails customerDetails) 
	{
		try
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT citizen_id AS strCitizenID, cif_key AS strCIFKey, first_name AS strFirstName, middle_name AS strMiddleName, last_Name AS strLastName, sex AS strGender, dob AS strDOB, mother_maiden_name AS strMotherMaidenName ");
			sqlQuery.append(" FROM customer_details WHERE id = "+customerDetails.getStrCustomerID()+" ");
			
	        List<CustomerDetails> customerDetailss = this.jdbcCMSTemplate.query(sqlQuery.toString(),
					(RowMapper<CustomerDetails>) new BeanPropertyRowMapper<CustomerDetails>(CustomerDetails.class),
					new Object[] 
					{
					});
	        if(customerDetailss!=null && customerDetailss.size() > 0)
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
	public CardTypeModel getCardMccOldDetails(CardTypeModel cardTypeModel) 
	{
		try
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT card_number, mcc, participant_id FROM card_mcc_service ");
			sqlQuery.append("WHERE card_number = ? AND participant_id = ? ");
			
	        List<CardTypeModel> cardTypeModels = this.jdbcCMSTemplate.query(sqlQuery.toString(),
					(RowMapper<CardTypeModel>) new BeanPropertyRowMapper<CardTypeModel>(CardTypeModel.class),
					new Object[] 
					{
						cardTypeModel.getCardNumber(),
						cardTypeModel.getStrPartID()
					});
	        if(cardTypeModels != null && cardTypeModels.size() > 0)
	        {
	        	return cardTypeModels.get(0);
	        }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
