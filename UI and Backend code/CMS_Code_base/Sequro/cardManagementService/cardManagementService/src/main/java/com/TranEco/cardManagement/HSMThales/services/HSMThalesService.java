package com.TranEco.cardManagement.HSMThales.services;

import java.io.IOException;
import java.util.HashMap;

public interface HSMThalesService 
{
	public String ValidateARQC(String strCard, String strScheme, String strKey, HashMap <String, String> Req_EMV_Data) throws IOException;
	
	public String GenerateARPC(String strCard, String strScheme, String strKey, HashMap <String, String> Req_EMV_Data) throws IOException;

	public String ValidateCVV(String strCard, String strExpDate, String strServiceCode, String strCVK, String strCVV) throws IOException;
	
	public String GenerateCVV(String strCard, String strExpDate, String strServiceCode, String strCVK) throws IOException;
	
	public String ValidatePinBlock(String strCard, String strZPK, String strPVK, String strPinBlock, String strDecimalizationTable, String strPINOffset) throws IOException;

	public String ARPCARQCProcess(String strCard, String strScheme, String strKey, HashMap <String, String> Req_EMV_Data)  throws IOException;

	public String GenerateRandomPin(String strCard, String strPinLength) throws IOException;
	
	public String GeneratePinOffset(String strCard, String strPVK, String strDecimalizationTable, String strPinLength) throws IOException;
	
	public String InitiatePinPrintingProcess(String strParticipant,	String strCardType,	String strCard,	String strPVK, String strDecimalizationTable, String strPinLength, String strPinOffset,	String strFormattingdata, String strPrintData) throws IOException;

	public String DerivePin(String strCard, String strPVK, String strDecimalizationTable, String strPinLength, String strOffset) throws IOException;

	public String LoadFormattingData(String strData) throws IOException; 

	public String PrintPinMailerData(String strCard, String strPIN, String strData) throws IOException;

	public String ARPCARQCProcessforKW(String strCard, String strScheme, String strKey,   HashMap <String, String> Req_EMV_Data, String strCSU, String strIssuerPrepData) throws IOException;

}