package com.TranEco.cardManagement.cardAuthentication.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.handler.CBSHandler;

@Service
public class CardAuthManagerImpl implements CardAuthManager {

	@Autowired
	CBSHandler cbsHandler;
	
	@Override
	public void sendRequestHandler(Map<String, Object> request) {
		cbsHandler.processTxn(request);
	}

	@Override
	public void sendRequestHandler(String request) {
		// TODO Auto-generated method stub
	}

	
}
