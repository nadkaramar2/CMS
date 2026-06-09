package com.traneco.common.logging.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.traneco.common.constants.QueryConstant;
import com.traneco.common.logger.model.UserLoggingModel;

@Repository
public class LoggingDaoImpl implements LoggingDao
{
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	JdbcTemplate jdbcCMSTemplate;
	
	
	@Override
	public UserLoggingModel getLoggingStatus(UserLoggingModel userLoggingModel) 
	{
		userLoggingModel = (UserLoggingModel) jdbcCMSTemplate.queryForObject(QueryConstant.GET_LOGGING_FLAG,
				new Object[] { }, new BeanPropertyRowMapper<UserLoggingModel>(UserLoggingModel.class));
		return userLoggingModel;
	}


	@Override
	public int updateLoggingStatus(UserLoggingModel userLoggingModel) 
	{
		return 0;
	}
	
}
