package com.traneco.encDecKey.model;

import java.util.Date;

import lombok.Data;

@Data
public class CardEncDecTempModel {

	private int id;
	private String key;
	private String value;
	private Date createdDate;
	private Date expiredDate;
	private String createdBy;
	private String modifiedBy;
}
