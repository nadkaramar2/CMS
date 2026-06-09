package com.TranEco.cardManagement.inquiryServices.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.inquiryServices.model.SearchClientCardRequest;
import com.TranEco.cardManagement.inquiryServices.model.SearchClientCardResponse;
import com.TranEco.cardManagement.inquiryServices.model.SearchClientCardResponseList;
import com.TranEco.cardManagement.utils.CardUtils;
import com.TranEco.cardManagement.utils.EncDecUtils;

@Repository
public class SearchClientCardDaoImpl implements SearchClientCardDao
{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	EncDecUtils encDecUtils;
	
	@Autowired
	CardUtils  cardUtils;
	
	@Override
	public SearchClientCardResponse searchClientCard(SearchClientCardRequest request) 
	{
		try
		{
		SearchClientCardResponse response = new SearchClientCardResponse();
		
		List <SearchClientCardResponseList> searchClientCardResponseList = null;
		
		String query = "";
		
		if("1".equals(request.getStrSearchType())) 
		{
			query = " AND a.citizen_id IS NULL) ";
		}else {
			query = " AND a.citizen_id IS NOT NULL) ";
		}
		
		if(request.getStrCustomerName() != null && request.getStrCustomerName().length() > 0) {
			searchClientCardResponseList = jdbcTemplate.query(
					QueryConstant.Get_Search_Client_card_Name+query, 
					new Object[] {
									"%"+request.getStrCustomerName().toUpperCase()+"%",
									"%"+request.getStrCustomerName().toUpperCase()+"%",
									request.getStrParticipantID()
								 }, new BeanPropertyRowMapper<SearchClientCardResponseList>(SearchClientCardResponseList.class));
		}else if(request.getStrCardNumber() != null && request.getStrCardNumber().length() > 0) {
			searchClientCardResponseList = jdbcTemplate.query(
					QueryConstant.Get_Search_Client_card_CardNO+query, 
					new Object[] {
									"%"+cardUtils.encrypt(request.getStrCardNumber()) +"%",
									request.getStrParticipantID()
								 }, new BeanPropertyRowMapper<SearchClientCardResponseList>(SearchClientCardResponseList.class));
		}else if(request.getStrAccountNumber() != null && request.getStrAccountNumber().length() > 0) {
			searchClientCardResponseList = jdbcTemplate.query(
					QueryConstant.Get_Search_Client_card_AccountNO+query, 
					new Object[] {
									"%"+request.getStrAccountNumber()+"%",
									request.getStrParticipantID()
								 }, new BeanPropertyRowMapper<SearchClientCardResponseList>(SearchClientCardResponseList.class));
		}else if(request.getStrCIFKey() != null && request.getStrCIFKey().length() > 0) {
			searchClientCardResponseList = jdbcTemplate.query(
					QueryConstant.Get_Search_Client_card_CIF+query, 
					new Object[] {
									"%"+request.getStrCIFKey()+"%",
									request.getStrParticipantID()
								 }, new BeanPropertyRowMapper<SearchClientCardResponseList>(SearchClientCardResponseList.class));
		}else if(request.getStrCitizenID() != null && request.getStrCitizenID().length() > 0) {
			searchClientCardResponseList = jdbcTemplate.query(
					QueryConstant.Get_Search_Client_card_Citizen+query, 
					new Object[] {
									"%"+request.getStrCitizenID()+"%",
									request.getStrParticipantID()
								 }, new BeanPropertyRowMapper<SearchClientCardResponseList>(SearchClientCardResponseList.class));
		}else if(request.getStrCardType() != null && request.getStrCardType().length() > 0) {
			searchClientCardResponseList = jdbcTemplate.query(
					QueryConstant.Get_Search_Client_card_Type+query, 
					new Object[] {
									request.getStrCardType(),
									request.getStrParticipantID()
								 }, new BeanPropertyRowMapper<SearchClientCardResponseList>(SearchClientCardResponseList.class));
		}
		
		
		 if (searchClientCardResponseList.isEmpty())
		 {
			 response.setOutResponseCode("01");
			 response.setMessage("No Matching Data Found");
		 }
		 else
		 {
			 response.setOutResponseCode("00");
			 response.setMessage("Success");
			 response.setSearchClientCardResponseList(searchClientCardResponseList);
		 }
		 return response;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null; 
		
	}
}
