package com.TranEco.cardManagement.EmbossingProcess.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.EmbossingProcess.model.TemporaryEmbossingData;
import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.utils.CardUtils;
import com.TranEco.cardManagement.utils.EncDecUtils;

@Repository
public class EmbossDaoImpl implements EmbossDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	EncDecUtils encDecUtils;
	
	@Autowired
	CardUtils  cardUtils;
	
	/*
	@Override
	public List<TemporaryEmbossingData> getEmbossingData(String cardType, String flag) 
	{
		List<TemporaryEmbossingData> embossList = jdbcTemplate.query(QueryConstant.Get_Embossing_Data, new Object[] {flag, cardType},
				new BeanPropertyRowMapper<TemporaryEmbossingData>(TemporaryEmbossingData.class));
		List<TemporaryEmbossingData> embossList1=new ArrayList<TemporaryEmbossingData>();
		
		
		
		//embossList1.addAll(0, embossList);
		//embossList1.get(0).setStrCardNo(CardUtils.decrypt(embossList.get(0).getStrCardNo(), CardUtils.getSecretKeys()));
		
		return embossList;
	}
	*/
	

	@Override
	public List<TemporaryEmbossingData> getEmbossingData(String cardType, String flag) 
	{
		try
		{
		List<TemporaryEmbossingData> embossList = jdbcTemplate.query(QueryConstant.Get_Embossing_Data,
				new Object[] { flag, cardType },
				new BeanPropertyRowMapper<TemporaryEmbossingData>(TemporaryEmbossingData.class));
		List<TemporaryEmbossingData> embossResponseList = new ArrayList<TemporaryEmbossingData>();

		for (TemporaryEmbossingData temporaryEmbossingData : embossList)
		{
			String strEncryptedCardNo = temporaryEmbossingData.getStrCardNo();
			String strCardNo = cardUtils.decrypt(strEncryptedCardNo);
			temporaryEmbossingData.setStrCardNo(strCardNo);
			embossResponseList.add(temporaryEmbossingData);
		}
		return embossResponseList;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int embossFlag(String card) 
	{
		return jdbcTemplate.update(QueryConstant.UPDATE_EMBOSS_FILE,new Object[] {card});
	}

}
