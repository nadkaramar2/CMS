package com.TranEco.cardManagement.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Cfg_Keys implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String participant_id;
	private String card_type;
	private String cvk_key;
	private String pvk_key;
	private String mdk_key;
	
}
