package com.TranEco.cardManagement.transaction.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataElement {

	List<TLV> tlvList;
	
	public DataElement() {
		super();
	}

	public DataElement(List<TLV> tlvList) {
		super();
		this.tlvList = tlvList;
	}

	public List<TLV> getTlvList() {
		return tlvList;
	}

	public void setTlvList(List<TLV> tlvList) {
		this.tlvList = tlvList;
	}
	
}
