package com.TranEco.cardManagement.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Cfg_Embossing_Data implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String participant_id;
	private String card_type;
	private String from_location;
	private String to_location;
	private String embossing_value;
	private String is_dynamic;
}
