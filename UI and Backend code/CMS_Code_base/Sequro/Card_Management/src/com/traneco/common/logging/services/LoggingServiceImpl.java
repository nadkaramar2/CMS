package com.traneco.common.logging.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traneco.common.logger.model.UserLoggingModel;
import com.traneco.common.logging.dao.LoggingDao;

@Service
public class LoggingServiceImpl implements LoggingService
{
	@Autowired
	private LoggingDao useLoggingDao;
	
	@Override
	public UserLoggingModel getLoggingStatus(UserLoggingModel userLoggingModel) 
	{
		return useLoggingDao.getLoggingStatus(userLoggingModel);
	}

	@Override
	public int updateLoggingStatus(UserLoggingModel userLoggingModel) 
	{
		return useLoggingDao.updateLoggingStatus(userLoggingModel);
	}

}
