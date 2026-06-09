package com.traneco.configuration.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class ChargingModule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String accountNumber;
	private String bankName;
	private String status;
	private String categoryID;
	private String accountType;
	private String chargesType;
	private String cardType;
	private String amount;
	private String modeOfTranscation;

}
