package com.TranEco.cardManagement.limitUpdate.services;

import com.TranEco.cardManagement.limitUpdate.model.LimitUpdateRequest;
import com.TranEco.cardManagement.limitUpdate.model.LimitUpdateResponse;

public interface LimitUpdateService {
	public LimitUpdateResponse updateLimit(LimitUpdateRequest request);
}
