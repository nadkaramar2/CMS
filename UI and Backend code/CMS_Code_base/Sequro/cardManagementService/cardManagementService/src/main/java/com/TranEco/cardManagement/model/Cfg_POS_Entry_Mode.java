package com.TranEco.cardManagement.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Cfg_POS_Entry_Mode implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String participant_id;
	private String pos_entry_mode;
	private String description;
	
}
