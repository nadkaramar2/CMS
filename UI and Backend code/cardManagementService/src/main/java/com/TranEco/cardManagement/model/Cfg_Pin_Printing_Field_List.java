package com.TranEco.cardManagement.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Cfg_Pin_Printing_Field_List implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String participant_id;
	private String pin_printing_format;
	private String sequence_no;
	private String pinprinting_field;
	
}
