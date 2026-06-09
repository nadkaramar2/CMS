package com.TranEco.cardManagement.scheduler.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MobileTokenModel implements Comparable< MobileTokenModel >  {
	private String id;
	private int strLength;
	private int strDemandFlag;
	private String strDemondCronExpr;
	private String newData;
	private String enDesc;
	private String mobile;
	private String mobileToken;
	private String createdDate;
	
	
	@Override
	public int compareTo(MobileTokenModel o) {
		return o.getCreatedDate().compareTo(this.createdDate);
	}



	public MobileTokenModel(String mobileToken, String createdDate) {
		super();
		this.mobileToken = mobileToken;
		this.createdDate = createdDate;
	}
	
}
