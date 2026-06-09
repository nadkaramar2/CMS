package com.TranEco.cardManagement.EmbossingProcess.dao;

import java.util.List;

import com.TranEco.cardManagement.EmbossingProcess.model.TemporaryEmbossingData;

public interface EmbossDao {
	
	public List<TemporaryEmbossingData> getEmbossingData(String cardType,String flag);
	public int embossFlag(String card);
}
