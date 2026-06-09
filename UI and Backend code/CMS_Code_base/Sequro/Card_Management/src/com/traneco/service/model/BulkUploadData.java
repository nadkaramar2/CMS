package com.traneco.service.model;

import java.util.Date;

import lombok.Data;

@Data
public class BulkUploadData 
{
	private String fileName;
	private String data;
	private String createdDate;
	
	private String id;
	private String status;
	private String modifiedBy;
	private Date modifiedDate;
	

}
