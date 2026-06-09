package com.traneco.clientSearch.model;
import java.io.Serializable;

import lombok.Data;

@Data
public class DocumentDetailsList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String strDocumentID;
	private String strDocumentType;
	private String strDocumentTypeDesc;
	private String strDocumentNumber;
	private String strCustomerId;
}
