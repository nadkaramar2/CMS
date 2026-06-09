package com.TranEco.cardManagement.EmbossingProcess.services;


public interface EmbossingService 
{
	public void embossFlag(String card);
	public void generateEmbossingData(String cardType, String flag);
}