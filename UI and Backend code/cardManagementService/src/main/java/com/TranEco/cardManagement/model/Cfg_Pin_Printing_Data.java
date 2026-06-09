package com.TranEco.cardManagement.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Cfg_Pin_Printing_Data implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String participant_id;
	private String pinmailer_format;
	private String pinmailer_data;
}
