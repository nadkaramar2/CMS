package com.traneco.clientSearch.model;
import java.util.List;

import lombok.Data;

@Data
public class EnquireCardDetailsResponse {
	private String outResponseCode;
	private String message;
	private CardDetails cardDetails;
	private LimitDetails limitDetails;
	private List<CardStatusDetailsList> cardStatusDetailsList;
	private List<AccountDetailsList> accountDetailsList;
	private List<CardNCMCServiceList> cardNCMCServiceList;
}
