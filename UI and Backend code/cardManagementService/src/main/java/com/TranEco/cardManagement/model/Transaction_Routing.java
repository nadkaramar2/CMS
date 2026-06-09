package com.TranEco.cardManagement.model;

import lombok.Data;

@Data
public class Transaction_Routing {
	private int id;
	private int acq_partid;
	private int bin_start;
	private int bin_end;
	private int participant_id;
	private int cms_participant_id;
	private String routing_priority;
	private String routing_rule; 
}
