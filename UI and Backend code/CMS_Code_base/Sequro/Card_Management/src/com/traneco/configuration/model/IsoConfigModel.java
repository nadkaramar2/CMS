package com.traneco.configuration.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class IsoConfigModel {
	
  private int id;
  
  private String name;
  
  private String data;
  
  private String connectionName;
  
}
