package com.TranEco.cardManagement.transaction.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public class TLV implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private TAGType type;
	
	public TLV() {
	}

	public TLV(String name, TAGType type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}


	public TAGType getType() {
		return type;
	}

	@XmlAttribute
	public void setType(TAGType type) {
		this.type = type;
	}
	
	
}
