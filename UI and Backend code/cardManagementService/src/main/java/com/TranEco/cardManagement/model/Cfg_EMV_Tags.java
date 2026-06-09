package com.TranEco.cardManagement.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Cfg_EMV_Tags implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String participant_id;
	private String card_type_id;
	private String emv_tags;
	private String skip_flag;
}
