package com.traneco.api.model;

import java.io.Serializable;

import lombok.Data;

//created by ankit

@Data
public class CardDetailsRequest implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String strTokenCard;
	private String strCardNo;
}
