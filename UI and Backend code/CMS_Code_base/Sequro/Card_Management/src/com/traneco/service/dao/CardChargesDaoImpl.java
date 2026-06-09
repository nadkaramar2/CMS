package com.traneco.service.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.traneco.service.model.CardAccountLinkage;

@Repository
public class CardChargesDaoImpl implements CardChargesDao
{

	@Autowired
	JdbcTemplate jdbcCMSTemplate;

	
	@Override
	public int addCardCharges(CardAccountLinkage cardAccountLinkageObject) 
	{
		try 
		{
			int count = this.jdbcCMSTemplate.update(
					" INSERT INTO card_charges (card_type,token_card,account_type,account_number, "
					+ " charge_type,charge_status,charge_applied_date_time) VALUES (?,?,?,?,?,?,?) ",
					new Object[] { cardAccountLinkageObject.getStrCardType(),
							cardAccountLinkageObject.getStrTokenCard(),cardAccountLinkageObject.getStrAccountType(),
							cardAccountLinkageObject.getStrAccountNumber(),cardAccountLinkageObject.getStrChargeType(),
							"Y",new Date()
					});
			return count;
		}
		
		catch (Exception e) 
		
		{
			e.printStackTrace();
		}
		return 0;
	}
}
