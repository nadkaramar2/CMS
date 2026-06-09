package com.TranEco.cardManagement.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Cfg_Card_Plastic implements Serializable {

	private static final long serialVersionUID = 1L;
	private String participant_id;
	private String card_type;
	private int iCVV_position;
	private int servicecode_position;
	private int expiry_date_position;
}
