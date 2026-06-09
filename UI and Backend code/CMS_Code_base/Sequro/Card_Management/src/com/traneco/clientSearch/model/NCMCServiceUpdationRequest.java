package com.traneco.clientSearch.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NCMCServiceUpdationRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strParticipantID; 
	private String strCardNo; 
	private String strMemberNo; 
	@JsonProperty("nCMCServiceList")
	private List<NCMCServiceList> nCMCServiceList; 
	private String [] ncmcList;
}
