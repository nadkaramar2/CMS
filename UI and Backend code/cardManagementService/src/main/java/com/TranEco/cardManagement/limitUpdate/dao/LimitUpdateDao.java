package com.TranEco.cardManagement.limitUpdate.dao;

import com.TranEco.cardManagement.limitUpdate.model.LimitUpdateRequest;
import com.TranEco.cardManagement.limitUpdate.model.LimitUpdateResponse;

public interface LimitUpdateDao {
	
	public LimitUpdateResponse limitUpdate(LimitUpdateRequest request);
}
