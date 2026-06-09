package com.traneco.service.model;

import lombok.Data;

@Data
public class FundomoAccountResponse {
	private String registrationStatus;
	private String accountReference;
	private String clientCode;
	private String reason;
}
