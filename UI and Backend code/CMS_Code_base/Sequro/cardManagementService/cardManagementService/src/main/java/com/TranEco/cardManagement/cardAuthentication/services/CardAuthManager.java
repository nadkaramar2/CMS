package com.TranEco.cardManagement.cardAuthentication.services;

import java.util.Map;

public interface CardAuthManager {
	public void sendRequestHandler(Map<String, Object> request);

	public void sendRequestHandler(String request);
}
