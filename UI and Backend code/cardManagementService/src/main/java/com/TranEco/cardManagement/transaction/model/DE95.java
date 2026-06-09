package com.TranEco.cardManagement.transaction.model;

public class DE95 {
	private String acttranamt;
	private String actsettleamt;
	private String acttranfee;
	private String actsetfeeamt;
	public String getActtranamt() {
		return acttranamt;
	}
	public void setActtranamt(String acttranamt) {
		this.acttranamt = acttranamt;
	}
	public String getActsettleamt() {
		return actsettleamt;
	}
	public void setActsettleamt(String actsettleamt) {
		this.actsettleamt = actsettleamt;
	}
	public String getActtranfee() {
		return acttranfee;
	}
	public void setActtranfee(String acttranfee) {
		this.acttranfee = acttranfee;
	}
	public String getActsetfeeamt() {
		return actsetfeeamt;
	}
	public void setActsetfeeamt(String actsetfeeamt) {
		this.actsetfeeamt = actsetfeeamt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DE95 [acttranamt=");
		builder.append(acttranamt);
		builder.append(", actsettleamt=");
		builder.append(actsettleamt);
		builder.append(", acttranfee=");
		builder.append(acttranfee);
		builder.append(", actsetfeeamt=");
		builder.append(actsetfeeamt);
		builder.append("]");
		return builder.toString();
	}
	
}
