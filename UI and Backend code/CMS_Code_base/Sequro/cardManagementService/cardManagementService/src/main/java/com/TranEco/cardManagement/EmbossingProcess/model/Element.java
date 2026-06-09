package com.TranEco.cardManagement.EmbossingProcess.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class Element implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@XmlAttribute(name="id")
	private int id;
	private int length;
	private String type;
	private String value;
	private String paddingType;
	private String paddingvalue;

}
