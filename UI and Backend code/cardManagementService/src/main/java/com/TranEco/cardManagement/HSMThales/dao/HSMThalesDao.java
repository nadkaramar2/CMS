package com.TranEco.cardManagement.HSMThales.dao;

import com.TranEco.cardManagement.HSMThales.model.Temporary_Pin_Printing_Data;

public interface HSMThalesDao {	
	public Temporary_Pin_Printing_Data PopulatePrintingData(String strParticipantID, String strCardNumber, String strMemberNumber);
}
