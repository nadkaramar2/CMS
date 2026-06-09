package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CardTokenModel implements Comparable< CardTokenModel >  {
	private String id;
	private String strCardType;
	private String strLength;
	private int strDemandFlag;
	private String strDemondCronExpr;
	private String newData;
	private String enDesc;
	private String cardNo;
	private String cardToken;
	private String createdDate;
	
	@Override
	public int compareTo(CardTokenModel o) {
		return o.getCreatedDate().compareTo(this.createdDate);
	}
	
}
