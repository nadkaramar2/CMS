package com.TranEco.cardManagement.cardAuthentication.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.TranEco.cardManagement.HSMThales.services.HSMThalesService;
import com.TranEco.cardManagement.cardAuthentication.model.CardAuthenticationInternalRequest;
import com.TranEco.cardManagement.cardAuthentication.model.TransactionData;
import com.TranEco.cardManagement.common.Constant.Card_Authentication_RC_Constants;
import com.TranEco.cardManagement.common.Constant.EMV_Tags_for_ARQC_Validation;
import com.TranEco.cardManagement.common.Constant.Transaction_Status;
import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.model.Cfg_Card_Plastic;
import com.TranEco.cardManagement.model.Cfg_Card_Type;
import com.TranEco.cardManagement.model.Cfg_EMV_Tags;
import com.TranEco.cardManagement.model.Cfg_Keys;
import com.TranEco.cardManagement.services.EhCacheService;
import com.TranEco.cardManagement.utils.StringUtil;

@Repository
public class CardAuthenticationDaoImpl implements CardAuthenticationDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	EhCacheService ehCacheService;

	@Autowired
	HSMThalesService hSMThalesService;

	@Autowired
	@Lazy
	RestTemplate restTemplate;

	@Override
	public String authenticateCard(CardAuthenticationInternalRequest request) throws IOException 
	{
		String strResponseCode = "00";
		try 
		{
			// Identify BIN
			String strBin6Digit = "", strBin8Digit = "";

			if (request.getStrCardNumber().length() > 15) 
			{
				strBin6Digit = request.getStrCardNumber().substring(0, 5);
				strBin8Digit = request.getStrCardNumber().substring(0, 7);
			}

			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName(QueryConstant.SP_ValidateCard);
			SqlParameterSource validateRequestIn = new MapSqlParameterSource()
					.addValue("inParticipantID", request.getStrParticipantID())
					.addValue("inCardNo", request.getStrCardNumber())
					.addValue("inCardSeqNo", request.getStrSequenceNo())
					.addValue("inTransactionAmount", request.getStrTransAmount())
					.addValue("inTransactionLimitClass", request.getStrTransLimitClass());
			// .addValue("inExpiryDate", strExpiryDateCard);
			Map<String, Object> validateRequestOut = jdbcCall.execute(validateRequestIn);
			strResponseCode = (String) validateRequestOut.get("outResponse");
			if (!strResponseCode.equals("00")) 
			{
				return validateRequestOut.get("outresponse").toString();
			}
			else 
			{
				String strCardTypeId = validateRequestOut.get("outCardTypeId").toString();
				// String strServiceCode = validateRequestOut.get("outServiceCode").toString();
				String strPinOffSet = validateRequestOut.get("outPinOffset").toString();
				String strCardScheme = validateRequestOut.get("outCardScheme").toString();
				String strExpiryDate = validateRequestOut.get("outExpiryDate").toString();

				// Fetch Expiry , ServiceCode and CVV from Track 2
				Cfg_Card_Plastic cfg_Card_Plastic = ehCacheService.getCfg_Card_Plastic()
						.get(request.getStrParticipantID() + strCardTypeId);

				int iCVVPosition = cfg_Card_Plastic.getICVV_position();
				int iServiceCodePosition = cfg_Card_Plastic.getServicecode_position();
				int iExpiryDatePosition = cfg_Card_Plastic.getExpiry_date_position();
				String strExpiryDateCard = request.getStrTrack2().substring(iExpiryDatePosition,
						iExpiryDatePosition + 4);
				String strServiceCode = request.getStrTrack2().substring(iServiceCodePosition,
						iServiceCodePosition + 3);
				String strCVV = request.getStrTrack2().substring(iCVVPosition, iCVVPosition + 3);

				HashMap<String, String> Req_EMV_Data = new HashMap<>();
				// Validate Pin Block
				Cfg_Keys cfg_Keys = ehCacheService.getCfg_Keys().get(request.getStrParticipantID() + strCardTypeId);
				String strCSU = "", strServiceID = "", strServiceManagementInfo = "", strPrepAuthData = "", strIDD = "";
				// Validate EMV Data if Present
				if ((request.getStrPOSEntryMode().substring(0, 2) == "05")
						&& (request.getStrPOSEntryMode().substring(0, 2) == "07")) 
				{
					if (!request.getStrEMVData().isEmpty()) 
					{
						String strHSMTag9F03 = null, strEMVTagData = null;
						int iEMVTagPos, iEMVTagDataLength;

						List<Cfg_EMV_Tags> cfg_EMV_Tags_list = ehCacheService.getCfg_EMV_Tags().stream()
								.filter(data -> data.getParticipant_id().equals(request.getStrParticipantID())
										&& data.getCard_type_id().equals(strCardTypeId))
								.collect(Collectors.toList());
						for (Cfg_EMV_Tags cfg_EMV_Tags : cfg_EMV_Tags_list) 
						{
							if (request.getStrEMVData().contains(cfg_EMV_Tags.getEmv_tags())) 
							{
								iEMVTagPos = cfg_EMV_Tags.getEmv_tags().indexOf(request.getStrEMVData());
								iEMVTagDataLength = Integer.parseInt(request.getStrEMVData()
										.substring(iEMVTagPos + cfg_EMV_Tags.getEmv_tags().length(), 2));
								strEMVTagData = request.getStrEMVData().substring(
										iEMVTagPos + cfg_EMV_Tags.getEmv_tags().length() + 2, iEMVTagDataLength * 2);
							} 
							else
								return Card_Authentication_RC_Constants.INVALID_EMVTAGS;

							switch (cfg_EMV_Tags.getEmv_tags()) 
						{
							case EMV_Tags_for_ARQC_Validation.EMV_TAG_9F02:
								Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_9F02, strEMVTagData);
								break;
							case EMV_Tags_for_ARQC_Validation.EMV_TAG_9F03:
								Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_9F03, strEMVTagData);
								break;
							case EMV_Tags_for_ARQC_Validation.EMV_TAG_9F1A:
								Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_9F1A, strEMVTagData);
								break;
							case EMV_Tags_for_ARQC_Validation.EMV_TAG_95:
								Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_95, strEMVTagData);
								break;
							case EMV_Tags_for_ARQC_Validation.EMV_TAG_5F2A:
								Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_5F2A, strEMVTagData);
								break;
							case EMV_Tags_for_ARQC_Validation.EMV_TAG_9A:
								Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_9A, strEMVTagData);
								break;
							case EMV_Tags_for_ARQC_Validation.EMV_TAG_9C:
								Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_9C, strEMVTagData);
								break;
							case EMV_Tags_for_ARQC_Validation.EMV_TAG_9F37:
								Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_9F37, strEMVTagData);
								break;
							case EMV_Tags_for_ARQC_Validation.EMV_TAG_82:
								Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_82, strEMVTagData);
								break;
							case EMV_Tags_for_ARQC_Validation.EMV_TAG_9F36:
								Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_9F36, strEMVTagData);
								break;
							case EMV_Tags_for_ARQC_Validation.EMV_TAG_9F10:
								Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_9F10, strEMVTagData);
								break;
							/*
							 * case EMV_Tags_for_ARQC_Validation.EMV_TAG_9F26 :
							 * Req_EMV_Data.put(EMV_Tags_for_ARQC_Validation.EMV_TAG_9F26 , strEMVTagData);
							 * break;
							 */
							}
						}
					} 
					else 
					{
						return Card_Authentication_RC_Constants.INVALID_EMVTAGS;
					}
				}

				if (strResponseCode.equals("00")) 
				{
					strServiceID = Req_EMV_Data.get(EMV_Tags_for_ARQC_Validation.EMV_TAG_9F10).substring(6, 9);
					strServiceManagementInfo = Req_EMV_Data.get(EMV_Tags_for_ARQC_Validation.EMV_TAG_9F10).substring(10,
							13);
					strIDD = Req_EMV_Data.get(EMV_Tags_for_ARQC_Validation.EMV_TAG_9F10).substring(14, 17);
					// if (request.getStrTransType.equals("13"))
					if (request.getStrTransType().equals("13")) 
					{
						// Validate Service ID
					}

					// Fetch Decimalization Table and Card Control
					Cfg_Card_Type cfg_Card_Type = ehCacheService.getCfg_Card_Type()
							.get(request.getStrParticipantID() + strCardTypeId);

					String strPVK = cfg_Keys.getPvk_key();
					String strZPK = "01234567890ABCDEFGHIJKLMN";// Need to maintain Acquirer Interface details
					String strDecimalizationTable = cfg_Card_Type.getDecimalization_table();

					if (!request.getStrPinBlock().isEmpty()) 
					{
						String response = hSMThalesService.ValidatePinBlock(request.getStrCardNumber(), strZPK, strPVK,
								request.getStrPinBlock(), strDecimalizationTable, strPinOffSet);
						if (!response.equals("00")) 
						{
							return Card_Authentication_RC_Constants.INVALID_PINBLOCK;
						}
					} else if ((request.getStrPOSEntryMode().substring(0, 2).equals("05"))
							|| (request.getStrPOSEntryMode().substring(0, 2).equals("02"))
							|| (request.getStrPOSEntryMode().substring(0, 2).equals("80"))) {
						return Card_Authentication_RC_Constants.INVALID_PINBLOCK;
					}

					// Validate CVV

					String strCVK = cfg_Keys.getCvk_key();

					if (!strCVV.isEmpty()) {
						String response = hSMThalesService.ValidateCVV(request.getStrCardNumber(), strExpiryDateCard,
								strServiceCode, strCVK, strCVV);
						if (!response.equals("00")) {
							return Card_Authentication_RC_Constants.INVALID_CVV;
						}
					}
				}
				// Send transaction to Issuer
				// ResponseEntity<SearchClientCardResponse> response =
				// restTemplate.postForEntity(env.getProperty("cms.url")+env.getProperty("cms.uri.searchClientCard"),
				// request, SearchClientCardResponse.class);
				// response.getBody();
				// ResponseEntity<SearchClientCardResponse> response =
				// restTemplate.getForEntity("http://202.21.39.68/transaction", request,
				// SearchClientCardResponse.class);

				// Response received from Issuer

				// Validate ARQC and ARPC
				if (request.getStrTransType().equals("10") && request.getStrTransType().equals("11")) // Purchase &
				{
					strServiceID = "0000";
					if (strResponseCode.equals("00"))
						strCSU = "80800000";
					else
						strCSU = "80000000";

				} 
				else if (request.getStrTransType().equals("12")) // Money Add
				{
					if (strResponseCode.equals("00"))
						strCSU = "80804000";
					else
						strCSU = "80000000";
				} else if (request.getStrTransType().equals("13")) // Service Creation
				{
					if (strResponseCode.equals("00"))
						strCSU = "80800000";
					else
						strCSU = "80000000";
				} 
				else if (request.getStrTransType().equals("09") && request.getStrTransType().equals("05")) // Cash at
				{
					strServiceID = "0000";
					if (strResponseCode.equals("00"))
						strCSU = "80800000";
					else
						strCSU = "80000000";
				}

				// Construct Tag 91 for response
				if (request.getStrTransType().equals("12")) 
				{
					strPrepAuthData = "FFFE" + StringUtil.pad(request.getStrTransAmount(), 12, '0', 'L');
				} 
				else if (request.getStrTransType().equals("13")) 
				{
					strPrepAuthData = strServiceID + strServiceManagementInfo + strIDD;
				} 
				else 
				{
					strPrepAuthData = "0000000000000000";
				}
				String strMDK = cfg_Keys.getMdk_key();
				String strScheme = ehCacheService.getCfg_Bin().get(request.getStrParticipantID() + strBin6Digit)
						.getNetwork_scheme();

				String strARQCARPCResponse = hSMThalesService.ARPCARQCProcessforKW(request.getStrCardNumber(),
						strScheme, strMDK, Req_EMV_Data, strCSU, strPrepAuthData);
				String strARPC, strTag91, strEMVData = "";
				if (strARQCARPCResponse != null) 
				{
					strARPC = strARQCARPCResponse.substring(4, 11); // ARPC Generated by HSM
					strTag91 = "91" + "8" + strARPC + strCSU + strPrepAuthData;
					strEMVData = strEMVData + strTag91.getBytes();
					if (!strARQCARPCResponse.substring(2, 3).equals("00")) {
						strResponseCode = Card_Authentication_RC_Constants.INVALID_ARQC;
						// Send negative response back to Acquirer and Initiate reversal to Fundamo
					}
				}
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strResponseCode;
	}

	public int logTransactioninDB(HashMap<String, String> transactionData, String strResponseCode) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() 
		{
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException 
			{
				PreparedStatement ps = connection.prepareStatement(QueryConstant.INSERT_TRANSACTION_TABLE,
						new String[] { "id" });
				ps.setString(1, UUID.randomUUID().toString());
				ps.setString(2, transactionData.get("REQ.REQUEST_TYPE"));
				ps.setString(3, transactionData.get("REQ.TRANSACTION_TYPE"));
				ps.setString(4, Transaction_Status.INFLIGHT);
				ps.setString(5, strResponseCode);
				ps.setString(6, transactionData.get("REQ.ACQUIRER_ID"));
				ps.setString(7, transactionData.get("REQ.ISSUER_ID"));
				ps.setString(8, transactionData.get("REQ.TRANSACTION_REF_NO"));
				
				return ps;
			}
		}, keyHolder);

		int iTRansactionID = (int) keyHolder.getKey();

		if (iTRansactionID == 0)
			return iTRansactionID;

		Set<Entry<String, String>> TransactionDataSet = transactionData.entrySet();
		Iterator<Entry<String, String>> TransactionDataIterator = TransactionDataSet.iterator();

		List<TransactionData> transactionDataList = new ArrayList<TransactionData>();
		while (TransactionDataIterator.hasNext()) {
			Map.Entry<String, String> map = (Map.Entry<String, String>) TransactionDataIterator.next();
			transactionDataList.add(new TransactionData(iTRansactionID, map.getKey(), map.getValue()));
		}

		int[] iTRansactionDetailsCount = jdbcTemplate.batchUpdate(QueryConstant.INSERT_TRANSACTION_DETAILS_TABLE,
				new BatchPreparedStatementSetter() 
			{
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException 
					{
						TransactionData transactionData = transactionDataList.get(i);
						ps.setInt(1, transactionData.getTransaction_id());
						ps.setString(2, transactionData.getField());
						ps.setString(3, transactionData.getValue());
					}

					@Override
					public int getBatchSize() 
					{
						return transactionDataList.size();
					}
				});
		if (iTRansactionDetailsCount.length <= 0)
			return iTRansactionID;

		return iTRansactionID;
	}

	public boolean UpdateTransactionResultinDB(int iTransactionId, String strResponseCode,
			String strTransactionStatus) 
	{
		boolean bSuccess = true;
		int iTransactionResultUpdateCount = jdbcTemplate.update(QueryConstant.UPDATE_TRANSACTION_RESULT,
				new Object[] { strTransactionStatus, strResponseCode, iTransactionId });

		if (iTransactionResultUpdateCount > 0)
			bSuccess = false;

		return bSuccess;
	}

	public boolean UpdateTransactionStatusinDB(int iTransactionId, String strTransactionStatus) {

		boolean bSuccess = true;
		int iTransactionStatusUpdateCount = jdbcTemplate.update(QueryConstant.UPDATE_TRANSACTION_STATUS,
				new Object[] { strTransactionStatus, iTransactionId });

		if (iTransactionStatusUpdateCount > 0)
			bSuccess = false;

		return bSuccess;

	}

	@Override
	public int transactionLog(String handlerId, String uuid, String unique_msg, String process_dt,
			String transaction_class, String internalData, String external_data, int request_flag) {
		return jdbcTemplate.update(QueryConstant.INSERT_TXN, new Object[] { handlerId, uuid, unique_msg, process_dt,
				transaction_class, internalData, external_data, request_flag });
	}

}