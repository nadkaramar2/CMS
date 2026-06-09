package com.traneco.configuration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddressProofDocumentTypeMaster implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String strId;
	private String strParticipantId;
	private String strDocumentType;
	private String strDocumentDescr;
}
