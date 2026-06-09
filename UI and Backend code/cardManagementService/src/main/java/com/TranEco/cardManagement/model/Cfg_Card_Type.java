package com.TranEco.cardManagement.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Cfg_Card_Type implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String id;
	private String card_type;
	private String description;
	private String participant_id;
	private String bin;
	private String bin_suffix;
	private String card_template_id;
	private String card_plastic_id;
	private String cvn_method_id;
	private String security_key_id;
	private String pinmailer_format;
	private String flag;
	private String strCardGenerationType;
	private String decimalization_table;
	private int card_token;
}
