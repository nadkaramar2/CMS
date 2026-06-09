package com.TranEco.cardManagement.HSMThales.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.HSMThales.model.Temporary_Pin_Printing_Data;
import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;

@Repository("HSMThalesDao")
public class HSMThalesDaoImpl implements HSMThalesDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	EhCacheServiceImpl ehCacheService;
	
	@Override
	public Temporary_Pin_Printing_Data PopulatePrintingData(String strParticipantID, String strCardNumber, String strMemberNumber) 
	{
		Temporary_Pin_Printing_Data tempPinData = null;
		if(strParticipantID != null && strCardNumber != null && strMemberNumber != null) 
		{
			tempPinData = (Temporary_Pin_Printing_Data) jdbcTemplate.query(
					QueryConstant.Get_Pin_Printing_Data, 
					new Object[] {	strParticipantID, strCardNumber, strMemberNumber
									}, new BeanPropertyRowMapper<Temporary_Pin_Printing_Data>(Temporary_Pin_Printing_Data.class));
			if (tempPinData == null) 
			{
				//response.setOutResponseCode("01");	
				//response.setMessage("Error: Could not fetch Pin Priting Data");
			}
		}	
		return tempPinData;
	}
	
}
