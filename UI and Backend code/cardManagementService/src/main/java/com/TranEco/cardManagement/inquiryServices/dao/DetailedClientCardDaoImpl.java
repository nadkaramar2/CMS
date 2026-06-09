package com.TranEco.cardManagement.inquiryServices.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.inquiryServices.model.AccountDetailsList;
import com.TranEco.cardManagement.inquiryServices.model.AddressDetailsList;
import com.TranEco.cardManagement.inquiryServices.model.CardList;
import com.TranEco.cardManagement.inquiryServices.model.CustomerDetails;
import com.TranEco.cardManagement.inquiryServices.model.DetailedClientCardRequest;
import com.TranEco.cardManagement.inquiryServices.model.DetailedClientCardResponse;
import com.TranEco.cardManagement.inquiryServices.model.DocumentDetailsList;
import com.TranEco.cardManagement.inquiryServices.model.EmailDetailsList;
import com.TranEco.cardManagement.inquiryServices.model.PhoneDetailsList;

@Repository
public class DetailedClientCardDaoImpl implements DetailedClientCardDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public DetailedClientCardResponse DetailedClientCard(DetailedClientCardRequest request) 
	{
		DetailedClientCardResponse response = new DetailedClientCardResponse();
		CustomerDetails customerDetails = new CustomerDetails();
		if(request.getStrCustomerID() != null && request.getStrCustomerID().length() > 0) 
		{
			customerDetails = (CustomerDetails) jdbcTemplate.queryForObject(
					QueryConstant.Get_Detailed_Search_CustomerID, 
					new Object[] {	request.getStrParticipantID(), request.getStrCustomerID()
									}, new BeanPropertyRowMapper<CustomerDetails>(CustomerDetails.class));
		}else if(request.getStrCitizenID() != null && request.getStrCitizenID().length() > 0) {
			customerDetails = (CustomerDetails) jdbcTemplate.queryForObject(
					QueryConstant.Get_Detailed_Search_CitizenID, 
					new Object[] { 
							request.getStrParticipantID(), request.getStrCitizenID()
								 }, new BeanPropertyRowMapper<CustomerDetails>(CustomerDetails.class));
		}else if(request.getStrCIFKey() != null && request.getStrCIFKey().length() > 0) {
			customerDetails = (CustomerDetails) jdbcTemplate.queryForObject(
					QueryConstant.Get_Detailed_Search_CIFKey, 
					new Object[] {
							request.getStrParticipantID(), request.getStrCIFKey()
								 }, new BeanPropertyRowMapper<CustomerDetails>(CustomerDetails.class));
		}
		else
		{
			response.setOutResponseCode("99");	
			response.setMessage("Please provide proper Input");
		}
		if (customerDetails == null) 
		{
			response.setOutResponseCode("01");	
			response.setMessage("Error: Could not fetch Customer Data");
		}
		else
		{
			response.setOutResponseCode("00");	
			response.setMessage("Success");
			response.setCustomerDetails(customerDetails);
			String strCustID = customerDetails.getStrCustomerID();
			
			List<CardList> cardList = null; 
			
			cardList = jdbcTemplate.query(
					QueryConstant.Get_Card_List, 
					new Object[] {request.getStrParticipantID(),strCustID
								 }, new BeanPropertyRowMapper<CardList>(CardList.class));
			if (cardList !=null)
			{
				response.setCardList(cardList);
								
				List<AccountDetailsList> accountDetailsList = null; 
				
				accountDetailsList = jdbcTemplate.query(
						QueryConstant.Get_Account_List, 
						new Object[] {request.getStrParticipantID(),strCustID  
									 }, new BeanPropertyRowMapper<AccountDetailsList>(AccountDetailsList.class));
			
				if (accountDetailsList != null)
				{
					response.setAccountDetailsList(accountDetailsList);
				}
			}
			
			List<AddressDetailsList> addressDetailsList = null;
			
			addressDetailsList = jdbcTemplate.query(
					QueryConstant.Get_Address_List, 
					new Object[] {strCustID , request.getStrParticipantID()
								 }, new BeanPropertyRowMapper<AddressDetailsList>(AddressDetailsList.class));
		
			if (addressDetailsList != null)
			{
				response.setAddressDetailsList(addressDetailsList);
			}
			
			List<PhoneDetailsList> phoneDetailsList = null;
			
			phoneDetailsList = jdbcTemplate.query(
					QueryConstant.Get_Phone_List, 
					new Object[] {strCustID
								 }, new BeanPropertyRowMapper<PhoneDetailsList>(PhoneDetailsList.class));
		
			if (phoneDetailsList != null)
			{
				response.setPhoneDetailsList(phoneDetailsList);
			}
			
			List<EmailDetailsList> emailDetailsList = null;
			
			emailDetailsList = jdbcTemplate.query(
					QueryConstant.Get_Email_List, 
					new Object[] {strCustID , request.getStrParticipantID()
								 }, new BeanPropertyRowMapper<EmailDetailsList>(EmailDetailsList.class));
		
			if (emailDetailsList != null)
			{
				response.setEmailDetailsList(emailDetailsList);
			}
			
			List<DocumentDetailsList> documentDetailsList = null;
			
			documentDetailsList = jdbcTemplate.query(
					QueryConstant.Get_Document_List, 
					new Object[] {strCustID
								 }, new BeanPropertyRowMapper<DocumentDetailsList>(DocumentDetailsList.class));
		
			if (documentDetailsList != null)
			{
				response.setDocumentDetailsList(documentDetailsList);
			}
			
		}
		 return response;
	}
}
