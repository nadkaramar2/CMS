package com.TranEco.cardManagement.HSMThales.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.common.Constant;
import com.TranEco.cardManagement.utils.StringUtil;

@Service
@Lazy
public class HSMThalesServiceImpl implements HSMThalesService 
{
	private static Logger logger = Logger.getLogger(HSMThalesServiceImpl.class.getPackage().getName());

	@Autowired
	@Lazy
	DataOutputStream hSMSocketConnectionOut;

	@Autowired
	@Lazy
	DataInputStream hSMSocketConnectionIn;

	@Override
	public String ValidateARQC(String strCard, String strScheme, String strKey, HashMap<String, String> Req_EMV_Data)
			throws IOException 
	{
		String strHSMReqHeader = "0001";
		String strHSMCommand = "KQ";
		String strModeFlag = "0";
		String strTransactionData = Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F02)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F03)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F1A)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_95)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_5F2A)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9A)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9C)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F37)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_82)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F36)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F10);
		int iTransactionDataLen = strTransactionData.length() / 2;

		StringBuffer strKQReqData = new StringBuffer();
		strKQReqData.append(strHSMReqHeader).append(strHSMCommand).append(strModeFlag).append(strScheme).append(strKey)
				.append(strCard).append(Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F36))
				.append(String.valueOf(iTransactionDataLen)).append(strTransactionData).append(";")
				.append(Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F26));

		hSMSocketConnectionOut.writeUTF(strKQReqData.toString());
		hSMSocketConnectionOut.flush();
		String strARQCValidationResponse = hSMSocketConnectionIn.readUTF();
		logger.info(strARQCValidationResponse);
		return strARQCValidationResponse;
	}

	@Override
	public String GenerateARPC(String strCard, String strScheme, String strKey, HashMap<String, String> Req_EMV_Data)
			throws IOException 
	{
		String strHSMReqHeader = "0001";
		String strHSMCommand = "KQ";
		String strModeFlag = "2";

		StringBuffer strKQReqData = new StringBuffer();
		strKQReqData.append(strHSMReqHeader).append(strHSMCommand).append(strModeFlag).append(strScheme).append(strKey)
				.append(strCard).append(Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F36))
				.append(Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F26)).append("0000");

		hSMSocketConnectionOut.writeUTF(strKQReqData.toString());
		hSMSocketConnectionOut.flush();
		String strARQCValidationResponse = hSMSocketConnectionIn.readUTF();
		logger.info(strARQCValidationResponse);
		return strARQCValidationResponse;
	}

	@Override
	public String ARPCARQCProcess(String strCard, String strScheme, String strKey, HashMap<String, String> Req_EMV_Data)
			throws IOException 
	{
		String strHSMReqHeader = "0001";
		String strHSMCommand = "KQ";
		String strModeFlag = "1";
		String strTransactionData = Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F02)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F03)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F1A)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_95)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_5F2A)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9A)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9C)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F37)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_82)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F36)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F10);
		int iTransactionDataLen = strTransactionData.length() / 2;

		StringBuffer strKQReqData = new StringBuffer();
		strKQReqData.append(strHSMReqHeader).append(strHSMCommand).append(strModeFlag).append(strScheme).append(strKey)
				.append(strCard).append(Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F36))
				.append(String.valueOf(iTransactionDataLen)).append(strTransactionData).append(";")
				.append(Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F26)).append("0000");

		hSMSocketConnectionOut.writeUTF(strKQReqData.toString());
		hSMSocketConnectionOut.flush();
		String strARQCValidationResponse = hSMSocketConnectionIn.readUTF();
		logger.info(strARQCValidationResponse);
		return strARQCValidationResponse;
	}

	@Override
	public String GeneratePinOffset(String strCard, String strPVK, String strDecimalizationTable, String strPinLength)
			throws IOException 
	{
		String strEncryptedRandomPin = GenerateRandomPin(strCard, strPinLength);

		if (strEncryptedRandomPin.length() > 2) 
		{
			String strHSMReqHeader = "0001";
			String strHSMCommand = "DE";
			String strInputCardNo = strCard.substring(strCard.length() - 13, strCard.length() - 1);
			String strPinValidData = strCard.substring(4, strCard.length() - 5) + "N"
					+ strCard.substring(strCard.length() - 1, strCard.length() - 1);
			StringBuffer strDEReqData = new StringBuffer();
			strDEReqData.append(strHSMReqHeader).append(strHSMCommand).append(strPVK).append(strEncryptedRandomPin)
					.append(strPinLength).append(strInputCardNo).append(strPinLength).append(strDecimalizationTable)
					.append(StringUtil.pad(strPinValidData, 12, 'F', 'R'));

			hSMSocketConnectionOut.writeUTF(strDEReqData.toString());
			hSMSocketConnectionOut.flush();
			String strGeneratePinOffsetResponse = hSMSocketConnectionIn.readUTF();
			if (strGeneratePinOffsetResponse.substring(3, 4).equals("DF")) 
			{
				String strDFResponseCode = strGeneratePinOffsetResponse.substring(5, 6);
				if (strDFResponseCode.equals("00")) 
				{
					return strDFResponseCode.substring(7, 18);
				}
				else
					return "10";
			} 
			else
				return "10";
		}
		else
			return "10";
	}

	@Override
	public String ARPCARQCProcessforKW(String strCard, String strScheme, String strKey,
			HashMap<String, String> Req_EMV_Data, String strCSU, String strIssuerPrepData) throws IOException {
		String strHSMReqHeader = "0001";
		String strHSMCommand = "KW";
		String strModeFlag = "3";
		// String strScheme = "2";
		String strFormattedPAN = strCard.substring(2, 14) + "001";
		String strTransactionData = Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F02)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F03)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F1A)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_95)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_5F2A)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9A)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9C)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F37)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_82)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F36)
				+ Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F10);
		int iTransactionDataLen = strTransactionData.length() / 2;
		String strHexTransactionDataLen = Integer.toHexString(iTransactionDataLen);

		StringBuffer strKQReqData = new StringBuffer();
		strKQReqData.append(strHSMReqHeader).append(strHSMCommand).append(strModeFlag).append(strScheme).append(strKey)
				.append(strFormattedPAN).append(Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F36))
				.append(strHexTransactionDataLen).append(strTransactionData).append(";")
				.append(Req_EMV_Data.get(Constant.EMV_Tags_for_ARQC_Validation.EMV_TAG_9F26)).append(strCSU).append("8")
				.append(strIssuerPrepData);

		/*
		 * hSMSocketConnectionOut.writeUTF(strKQReqData.toString());
		 * hSMSocketConnectionOut.flush(); String strARQCValidationResponse =
		 * hSMSocketConnectionIn.readUTF();
		 */
		String strARQCValidationResponse = null;
		logger.info(strARQCValidationResponse);
		return strARQCValidationResponse;
	}

	@Override
	public String ValidatePinBlock(String strCard, String strZPK, String strPVK, String strPinBlock,
			String strDecimalizationTable, String strPINOffset) throws IOException 
	{
		String strHSMReqHeader = "0001";
		String strHSMCommand = "EA";
		String strPinValidData = strCard.substring(4, strCard.length() - 5) + "N"
				+ strCard.substring(4, strCard.length() - 5);
		StringBuffer strEAReqData = new StringBuffer();
		strEAReqData.append(strHSMReqHeader).append(strHSMCommand).append(strZPK).append(strPVK).append("12")
				.append(strPinBlock).append("01").append("04")
				.append(strCard.substring(strCard.length() - 13, strCard.length() - 1)).append(strDecimalizationTable)
				.append(StringUtil.pad(strPinValidData, 12, 'F', 'R')).append(strPINOffset);

		hSMSocketConnectionOut.writeUTF(strEAReqData.toString());
		hSMSocketConnectionOut.flush();
		String strARQCValidationResponse = hSMSocketConnectionIn.readUTF();
		return strARQCValidationResponse;
	}

	@Override
	public String ValidateCVV(String strCard, String strExpDate, String strServiceCode, String strCVK, String strCVV)
			throws IOException 
	{
		String strHSMReqHeader = "0001";
		String strHSMCommand = "CY";
		StringBuffer strCYReqData = new StringBuffer();
		strCYReqData.append(strHSMReqHeader).append(strHSMCommand).append(strCVK).append(strCVV).append(strCard)
				.append(";").append(strExpDate).append(strServiceCode);

		hSMSocketConnectionOut.writeUTF(strCYReqData.toString());
		hSMSocketConnectionOut.flush();
		String strValidateCVVResponse = hSMSocketConnectionIn.readUTF();
		return strValidateCVVResponse;
	}

	@Override
	public String GenerateCVV(String strCard, String strExpDate, String strServiceCode, String strCVK)
			throws IOException 
	{
		logger.info("GenerateCVV Request-------------");
		String strHSMReqHeader = "0001";
		String strHSMCommand = "CW";
		StringBuffer strCWReqData = new StringBuffer();
		strCWReqData.append(strHSMReqHeader).append(strHSMCommand).append(strCVK).append(strCard).append(";")
				.append(strExpDate).append(strServiceCode);
		logger.info("GenerateCVV Request-------------" + strCWReqData.toString());
		logger.info("GenerateCVV strHSMReqHeader-------------" + strHSMReqHeader);
		logger.info("GenerateCVV strHSMCommand-------------" + strHSMCommand);
		logger.info("GenerateCVV strCVK-------------" + strCVK);
		logger.info("GenerateCVV ;-------------;");
		logger.info("GenerateCVV strExpDate-------------" + strExpDate);
		logger.info("GenerateCVV strServiceCode-------------" + strServiceCode);

		hSMSocketConnectionOut.writeUTF(strCWReqData.toString());
		hSMSocketConnectionOut.flush();
		String strGenerateCVVResponse = hSMSocketConnectionIn.readUTF();
		logger.info("GenerateCVV Response-------------" + strGenerateCVVResponse);
		// String strGenerateCVVResponse = "0001CX00492";
		return strGenerateCVVResponse;
	}

	@Override
	public String DerivePin(String strCard, String strPVK, String strDecimalizationTable, String strPinLength,
			String strOffset) throws IOException 
	{
		String strHSMReqHeader = "0001";
		String strHSMCommand = "EE";
		String strInputCardNo = strCard.substring(strCard.length() - 13, strCard.length() - 1);
		String strPinValidData = strCard.substring(4, strCard.length() - 5) + "N"
				+ strCard.substring(strCard.length() - 1, strCard.length() - 1);
		StringBuffer strEEReqData = new StringBuffer();
		strEEReqData.append(strHSMReqHeader).append(strHSMCommand).append(strPVK).append(strOffset).append(strPinLength)
				.append(strInputCardNo).append(strDecimalizationTable).append(strPinValidData);

		logger.info("DerivePin() : Derive Pin Command Request data to HSM " + strEEReqData.toString());

		hSMSocketConnectionOut.writeUTF(strEEReqData.toString());
		hSMSocketConnectionOut.flush();
		String strGenerateEFResponse = hSMSocketConnectionIn.readUTF();
		// String strGenerateEFResponse = null;
		logger.info("DerivePin() :Derive Pin Command Response data to HSM " + strGenerateEFResponse.toString());
		if (strGenerateEFResponse.substring(3, 4).equals("EF")) 
		{
			String strEEResponseCode = strGenerateEFResponse.substring(5, 6);
			if (strEEResponseCode.equals("00")) 
			{
				return strEEResponseCode.substring(7, 18);
			} 
			else 
			{
				logger.info(
						"DerivePin() : Error Response Recieved from HSM for Derive Pin Command" + strEEResponseCode);
				return "10";
			}
		} 
		else
			return "10";
	}

	@Override
	public String LoadFormattingData(String strData) throws IOException 
	{
		String strHSMReqHeader = "0001";
		String strHSMCommand = "PA";
		StringBuffer strPAReqData = new StringBuffer();
		strPAReqData.append(strHSMReqHeader).append(strHSMCommand).append(strData);

		hSMSocketConnectionOut.writeUTF(strPAReqData.toString());
		hSMSocketConnectionOut.flush();
		String strGeneratePBResponse = hSMSocketConnectionIn.readUTF();
		// String strGeneratePBResponse = null;
		if (strGeneratePBResponse.substring(3, 4).equals("PB")) 
		{
			String strPBResponseCode = strGeneratePBResponse.substring(5, 6);
			if (strPBResponseCode.equals("00")) 
			{
				return strPBResponseCode;
			} 
			else
				return "10";
		} else
			return "10";
	}

	@Override
	public String PrintPinMailerData(String strCard, String strPIN, String strData) throws IOException {
		String strHSMReqHeader = "0001";
		String strHSMCommand = "PE";
		String strDocumentType = "A";
		String strInputCardNo = strCard.substring(strCard.length() - 13, strCard.length() - 1);

		StringBuffer strPAReqData = new StringBuffer();
		strPAReqData.append(strHSMReqHeader).append(strHSMCommand).append(strData).append(strDocumentType)
				.append(strInputCardNo).append(strPIN).append(strData);

		hSMSocketConnectionOut.writeUTF(strPAReqData.toString());
		hSMSocketConnectionOut.flush();
		String strGeneratePBResponse = hSMSocketConnectionIn.readUTF();
		// String strGeneratePBResponse = null;
		if (strGeneratePBResponse.substring(3, 4).equals("PB")) 
		{
			String strPBResponseCode = strGeneratePBResponse.substring(5, 6);
			if (!strPBResponseCode.equals("00")) 
			{
				logger.info("PrintPinMailerData() Error during Pin Printing Process " + strPBResponseCode);
			}
			return strPBResponseCode;
		} else
			return "10";
	}

	@Override
	public String InitiatePinPrintingProcess(String strParticipant, String strCardType, String strCard, String strPVK,
			String strDecimalizationTable, String strPinLength, String strPinOffset, String strFormattingdata,
			String strPrintData) throws IOException 
	{
		String strResponse = DerivePin(strCard, strPVK, strDecimalizationTable, strPinLength, strPinOffset);
		if (strResponse.length() > 2) 
		{
			String strEncryptedPin = strResponse;
			String strLoadFormattingRes = LoadFormattingData(strFormattingdata);
			if (strLoadFormattingRes.equals("00")) 
			{
				String strPinPrintRes = PrintPinMailerData(strCard, strEncryptedPin, strPrintData);
				if (!strPinPrintRes.equals("00"))
					logger.info("InitiatePinPrintingProcess() : Error in Pin Printing , Response Code : "
							+ strLoadFormattingRes);
				return strPinPrintRes;
			} else {
				logger.info("InitiatePinPrintingProcess() : Error in Load Formatting Data , Response Code : "
						+ strLoadFormattingRes);
				return "10";
			}
		} else {
			logger.info("InitiatePinPrintingProcess() : Error in Derive Pin , Response Code : " + strResponse);
			return "10";
		}

	}

	@Override
	public String GenerateRandomPin(String strCard, String strPinLength) throws IOException 
	{
		String strHSMReqHeader = "0001";
		String strHSMCommand = "JA";
		String strInputCardNo = strCard.substring(strCard.length() - 13, strCard.length() - 1);
		String strPinValidData = strCard.substring(4, strCard.length() - 5) + "N"
				+ strCard.substring(strCard.length() - 1, strCard.length() - 1);
		//System.out.println("HSMThalesServiceImpl.GenerateRandomPin()"+strPinValidData);
		StringBuffer strJAReqData = new StringBuffer();
		strJAReqData.append(strHSMReqHeader).append(strHSMCommand).append(strInputCardNo).append(strPinLength);

		hSMSocketConnectionOut.writeUTF(strJAReqData.toString());
		hSMSocketConnectionOut.flush();
		String strGenerateRandomPinResponse = hSMSocketConnectionIn.readUTF();
		// String strGenerateRandomPinResponse = null;
		if (strGenerateRandomPinResponse.substring(3, 4).equals("JB")) 
		{
			String strJBResponseCode = strGenerateRandomPinResponse.substring(5, 6);
			if (strJBResponseCode.equals("00")) 
			{
				String strEncryptedRandomPin = strJBResponseCode.substring(7, 18);
				return strEncryptedRandomPin;
			}
			else
				return strJBResponseCode;
		} else
			return "10";

	}
}
