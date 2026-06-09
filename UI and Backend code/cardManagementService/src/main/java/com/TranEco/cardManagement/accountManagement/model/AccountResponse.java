package com.TranEco.cardManagement.accountManagement.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AccountResponse {
	private String outResponseCode;
	private String message;
}
