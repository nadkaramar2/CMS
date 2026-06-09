package com.TranEco.cardManagement.EmbossingProcess.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement(name="emboss")
@XmlAccessorType(XmlAccessType.FIELD)
//@Component
public class EmbossFile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Element> element;
	
}
