package com.traneco.clientSearch.model;
import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CardTypeList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strCardType;
	private String strCardDescription;
	private String strCardBin;
}
