package com.TranEco.cardManagement.test.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.inquiryServices.model.CardDetails;

@Repository
public class TestDaoImpl implements TestDao
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	@SuppressWarnings("deprecation")
	@Override
	public CardDetails getCardDetails(CardDetails request) 
	{
		try 
		   {
			CardDetails queryForObject = jdbcTemplate.queryForObject(QueryConstant.GET_CARD_DATA, new Object[] {},
		   	new BeanPropertyRowMapper<CardDetails>(CardDetails.class));
		    return queryForObject; 
		   } catch (Exception e) 
		   {
		      e.printStackTrace();
		   }
		return null;
	}
	
}
