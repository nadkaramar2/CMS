package com.traneco.service.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.traneco.accountmanagement.services.AccountManagementService;
import com.traneco.clientSearch.model.CardDetailsList;
import com.traneco.common.util.Utils;
import com.traneco.configuration.model.ProcessResponse;
import com.traneco.configuration.model.TranMaster;
import com.traneco.configuration.model.TxnData;
import com.traneco.configuration.model.TxnHeader;
import com.traneco.configuration.model.TxnReqRes;
import com.traneco.configuration.model.cardAcntLinkageMaster;
import com.traneco.configuration.services.ConfigurationService;

//@CrossOrigin("http://103.11.153.212:8086")
@RestController
public class TxnServiceHandlerController 
{
	@Autowired
	AccountManagementService accountManagementService;
	
	@Autowired
	ConfigurationService configurationService;
	
	//@CrossOrigin(origins = "http://103.11.153.212:8086",methods = RequestMethod.POST)
	@RequestMapping(value = "/pushTxn", method = RequestMethod.POST , produces = "application/json")
	public TxnReqRes loginResponse(@RequestBody Map<String,Object> loginData)
	{	
		ProcessResponse processResponse = new ProcessResponse();
		TxnHeader txnHeader =  new TxnHeader();
		TxnReqRes txnReqResponse = new TxnReqRes();
		TxnData txnData = new TxnData();
		TranMaster tranMaster = new TranMaster();
		
		SimpleDateFormat sdformat = new SimpleDateFormat("MMyy");
		String mti = "";
		try 
		{	
			processResponse.setCode("S0000");
			processResponse.setStatus("Success");
			txnReqResponse.setResponse(processResponse);
			
			String cardNumber = (String) loginData.get("cardnmbr");
			mti = (String) loginData.get("MTI");
			String srcTxnId = (String) loginData.get("txnId");
			String particpantId = (String) loginData.get("participantId");
			
			String amount = (String) loginData.get("de004_amount");
			amount = Utils.decimalFormat.format(Utils.stringToDouble(amount));
			
			
			String mcc = (String) loginData.get("de018_mcc");
			String stan = (String) loginData.get("de011_stan");
			String crycode = (String) loginData.get("de049_tran_currency_code");
			String txnId = (String) loginData.get("txnId");
			String MID = (String) loginData.get("de042_mid");
			String tId = (String) loginData.get("de041_terminal_id");
			String cId = (String) loginData.get("de042_CID");
			String loc = (String) loginData.get("de043_merchant_name_loc"); //de037_rrn
			String rrn = (String) loginData.get("de037_rrn");
			
			String processingCode = (String) loginData.get("de003_processing_code");
			
			txnHeader.setMti(mti);
			txnHeader.setChannel("WEB");
			txnHeader.setAppVersion("SequroVer1.0");
			
			String cardBin = cardNumber.substring(0, 6);
			//txnData.setCardBin(cardBin); 
			
			String maskCardNumber = cardNumber.replace(cardNumber.substring(6, cardNumber.length() - 4),"******");			
			txnData.setCardMasked(maskCardNumber);// 456789-120000-0025
			
			txnData.setDe041_terminal_id(tId); 
			txnData.setDe042_mid(MID);
			txnData.setTxn_id(txnId);
			txnData.setDe050_settle_currency_code(crycode);
			txnData.setDe011_stan(stan);
			txnData.setDe018_mcc(mcc);
			txnData.setDe004_amount(amount);
			//txnData.setParticipant_id(Utils.stringToInt(particpantId));
			txnData.setParticipant_id(particpantId);
			txnData.setDe037_rrn(rrn);
			
			txnData.setDe003_processing_code(processingCode);
			
			tranMaster.setStrMuserId(MID);
			tranMaster.setStrTID(tId);
			tranMaster.setStrCrdAccptorLoc(loc);
			tranMaster.setStrTxnAmount(amount);
			tranMaster.setStrStan(stan);
			tranMaster.setStrCrdAcceptorId(cId);
			tranMaster.setStrCurrncyCode(crycode);
			tranMaster.setStrMccCode(mcc);
			tranMaster.setStrMti(mti);
			tranMaster.setStrMaskCardNo(maskCardNumber);
			tranMaster.setStrParticipantId(particpantId);
			tranMaster.setStrRRN(rrn);
			//tranMaster.setStrLocal_tran_date(Utils.getLocalDate());
			long now = System.currentTimeMillis();
	        Time sqlTime = new Time(now);
			//tranMaster.setStrLocal_tran_time(Utils.getLocalDate());
			
			tranMaster.setStrProcessingCode(processingCode);
			
			String strExpriyDate = (String) loginData.get("expiryDate");//1123
			System.out.println("expriyDate :::::::"+strExpriyDate);
			
			if (!configurationService.checkStanAlreadyExist(stan)) 
			{
				configurationService.insertRequestEntry(tranMaster);
				  
				CardDetailsList getCardDetails = configurationService.getCardDetails(maskCardNumber);				
				String cardDetailStatus = getCardDetails.getStrCardStatus();
				
				if(cardDetailStatus.equals("A") ) //Card Status Check POINT
				{
					Date date = new Date();			
					String todaysDate = sdformat.format(date);
					Date currentDate = sdformat.parse(todaysDate);
					
					Date expiryDate = sdformat.parse(strExpriyDate);
					if (currentDate.compareTo(expiryDate) < 0) //Expiry Date Check POINT
					{
						cardAcntLinkageMaster cardaccAcntLinkageMaster = configurationService.getInfofrmCardLinkage(maskCardNumber);
						if (cardaccAcntLinkageMaster != null)
						{
							txnData.setAccountNumber(cardaccAcntLinkageMaster.getAccountNumber());
							//txnData.setStrAccountNumber(cardaccAcntLinkageMaster.getAccountNumber());
							txnData.setAccountType(cardaccAcntLinkageMaster.getAccountType());
							txnReqResponse.setTxnData(txnData);
							txnReqResponse.setHeader(txnHeader);
							processResponse = accountManagementService.sendTransactionData(txnReqResponse);
							System.out.println("Prashant :::-TxnServiceHandlerController.loginResponse()"+txnReqResponse);
						}
						else
						{
							processResponse.setCode("E0000");
							processResponse.setStatus("Failed");
							processResponse.setMessage("Card Not Linked With Any Account.");
						}
					}
					else
					{
						processResponse.setCode("E0000");
						processResponse.setStatus("Failed");
						processResponse.setMessage("Card Expired.");
					}
				}
				else
				{
					processResponse.setCode("E0000");
					processResponse.setStatus("Failed");
					processResponse.setMessage("Card Details Not Found");
				}
			}
			else
			{
				processResponse.setCode("E0000");
				processResponse.setStatus("Failed");
				processResponse.setMessage("Duplicate Stan");
			}
		} 
		catch (Exception e) 
		{
			processResponse.setCode("E0000");
			processResponse.setStatus("Failed");
			processResponse.setMessage("Internal Server Error");
			e.printStackTrace();
		}
		tranMaster.setStrMti(Utils.getResponseMTI(mti));
		tranMaster.setStrAuthCode(Utils.getAlphaNumericString());
		tranMaster.setStrResponseCode(txnReqResponse.getTxnData().getDe039_response());
		configurationService.insertResponseEntry(tranMaster);
		txnReqResponse.setResponse(processResponse);
		System.out.println("Prashant ---- txnReqResponse::::"+txnReqResponse);
		return txnReqResponse;
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() 
	{
      return new WebMvcConfigurerAdapter() {
         @Override
         public void addCorsMappings(CorsRegistry registry) 
         {
            //registry.addMapping("/loginbyReact").allowedOrigins("http://localhost:3000");
            registry.addMapping("/**");
         }
      };

	}	

}
