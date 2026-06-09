package com.traneco.clientSearch.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailedClientCardResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String outResponseCode;
	private String message;
	private CustomerDetails customerDetails;
	private List<CardList> cardList;
	private List<AccountDetailsList> accountDetailsList;
	private List<AddressDetailsList> addressDetailsList;
	private List<PhoneDetailsList> phoneDetailsList;
	private List<EmailDetailsList> emailDetailsList;
	private List<DocumentDetailsList> documentDetailsList;
}
