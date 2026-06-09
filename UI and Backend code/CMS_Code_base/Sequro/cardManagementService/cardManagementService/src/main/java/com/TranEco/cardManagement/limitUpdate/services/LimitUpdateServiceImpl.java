package com.TranEco.cardManagement.limitUpdate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.limitUpdate.dao.LimitUpdateDaoImpl;
import com.TranEco.cardManagement.limitUpdate.model.LimitUpdateRequest;
import com.TranEco.cardManagement.limitUpdate.model.LimitUpdateResponse;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;

@Service
public class LimitUpdateServiceImpl implements LimitUpdateService{

	@Autowired
	EhCacheServiceImpl ehCacheService;
	
	@Autowired
	LimitUpdateDaoImpl LimitUpdateDao;
	
	//currency code,branch
	
	@Override
	public LimitUpdateResponse updateLimit(LimitUpdateRequest request) {
		LimitUpdateResponse response = new LimitUpdateResponse();
				response =  LimitUpdateDao.limitUpdate(request);
				return response;
	}

}
