package com.traneco.clientSearch.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.traneco.clientSearch.model.CardTypeList;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.QueryConstant;


@Repository
public class CardTypeDaoImpl implements CardTypeDao {

	@Autowired
	SessionDTO sessionDTO;

	@Autowired
	JdbcTemplate jdbcCMSTemplate;

	@Override
	public List<CardTypeList> getCardTypeList() 
	{
		List<CardTypeList> cardTypeList = jdbcCMSTemplate.query(QueryConstant.CARD_TYPE_LIST, new Object[] {},
				new BeanPropertyRowMapper<CardTypeList>(CardTypeList.class));
		return cardTypeList;
	}

	

}
