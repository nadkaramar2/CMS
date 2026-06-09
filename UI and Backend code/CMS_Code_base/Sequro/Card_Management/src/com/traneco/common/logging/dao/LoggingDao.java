package com.traneco.common.logging.dao;

import com.traneco.common.logger.model.UserLoggingModel;

public interface LoggingDao {

	UserLoggingModel getLoggingStatus(UserLoggingModel userLoggingModel);

	int updateLoggingStatus(UserLoggingModel userLoggingModel);

}
