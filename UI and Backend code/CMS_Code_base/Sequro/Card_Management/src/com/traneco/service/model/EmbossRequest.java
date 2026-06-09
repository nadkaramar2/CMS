package com.traneco.service.model;

import lombok.Data;

@Data
public class EmbossRequest implements Comparable<EmbossRequest> {
	private String flag;
	private String fileName;
	private String filePath;
	private String strSelectID;
	private String cardType;
	private int id;

	@Override
	public int compareTo(EmbossRequest o) {
		int compareage = ((EmbossRequest) o).getId();
		return compareage - this.id;
	}

}
