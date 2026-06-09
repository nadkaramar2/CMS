package com.TranEco.cardManagement.EmbossingProcess.model;

import lombok.Data;

@Data
public class EmbossRequest {
	private String flag;
	private String fileName;
	private String filePath;
	private String strSelectID;
	private String cardType;
	private int id;
}
