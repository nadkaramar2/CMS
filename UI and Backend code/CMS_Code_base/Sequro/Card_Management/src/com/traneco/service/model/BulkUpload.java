package com.traneco.service.model;

import lombok.Data;

@Data
public class BulkUpload {
	private ServiceBean serviceBean;
	private AccountRequest accountRequest;
	private Client client;

}
