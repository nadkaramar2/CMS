package com.traneco.common.logging.services;

import com.traneco.common.logger.model.UserLoggingModel;

public interface LoggingService {

	UserLoggingModel getLoggingStatus(UserLoggingModel userLoggingModel);

	int updateLoggingStatus(UserLoggingModel userLoggingModel);

}
