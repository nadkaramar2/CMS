package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileTokenModel implements Comparable< MobileTokenModel >  {
	private String id;
	private String strLength;
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
	
}
