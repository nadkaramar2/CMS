package com.TranEco.cardManagement.scheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardTokenModel implements Comparable< CardTokenModel > {
	
	private String cardToken;
	private String createdDate;
	
	@Override
	public int compareTo(CardTokenModel o) {
		return o.getCreatedDate().compareTo(this.createdDate);
	}
}
