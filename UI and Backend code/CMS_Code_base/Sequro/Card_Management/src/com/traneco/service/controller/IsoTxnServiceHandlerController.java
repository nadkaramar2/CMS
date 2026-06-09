package com.traneco.service.controller;

import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.traneco.accountmanagement.services.AccountManagementService;
import com.traneco.clientSearch.model.CardDetailsList;
import com.traneco.clientSearch.model.CardStatus;
import com.traneco.common.util.AESEncDec;
import com.traneco.configuration.model.ProcessResponse;
import com.traneco.configuration.model.TranMaster;
import com.traneco.configuration.model.TxnData;
import com.traneco.configuration.model.TxnHeader;
import com.traneco.configuration.model.TxnReqRes;
import com.traneco.configuration.model.cardAcntLinkageMaster;
import com.traneco.configuration.services.ConfigurationService;

@CrossOrigin("http://103.11.153.212:8086/Card_Management")
@RestController
public class IsoTxnServiceHandlerController 
{
	@Autowired
	AccountManagementService accountManagementService;

	@Autowired
	ConfigurationService configurationService;
	
	@Autowired
	AESEncDec aesEncDec;
	

	@SuppressWarnings("null")
	@CrossOrigin(origins = "http://103.11.153.212:8086/Card_Management", methods = RequestMethod.POST)
	@RequestMapping(value = "/isoTransaction", method = RequestMethod.POST, produces = "application/json")
	public TxnReqRes loginResponse(@RequestBody TxnReqRes txnReqRes) 
	{
		ProcessResponse processResponse = new ProcessResponse();
		TxnHeader txnHeader = txnReqRes.getHeader();
		TxnReqRes txnReqResponse = new TxnReqRes();
		txnReqResponse.setHeader(txnReqRes.getHeader());
		TxnData txnData = txnReqRes.getTxnData();
		TranMaster tranMaster = new TranMaster();

		SimpleDateFormat sdformat = new SimpleDateFormat("MMyy");
		try 
		{
			String maskCardNumber = null;
			if (txnData.getDe002_pan().length() == 16) 
			{
				maskCardNumber = txnData.getDe002_pan().replace(txnData.getDe002_pan().substring(6, txnData.getDe002_pan().length() - 4), "******");
			} 
			else 
			{
				maskCardNumber = txnData.getDe035_track_2().replace(txnData.getDe035_track_2().substring(6, txnData.getDe035_track_2().length() - 4), "******");
			}
			// String maskCardNumber =
			// txnData.getDe002_pan().replace(txnData.getDe002_pan().substring(6,
			// txnData.getDe002_pan().length() - 4),"******");

			txnData.setCardMasked(maskCardNumber); // 456789-120000-0025
			txnData.setDe050_settle_currency_code(txnData.getDe049_tran_currency_code());
			tranMaster.setStrProcessingCode(txnData.getDe003_processing_code());
			tranMaster.setStrMid(txnData.getDe042_mid());
			tranMaster.setStrTID(txnData.getDe041_terminal_id());
			tranMaster.setStrCrdAccptorLoc(txnData.getDe043_merchant_name_loc());
			// tranMaster.setStrTxnAmount(txnData.getDe004_amount());
			DecimalFormat df = new DecimalFormat("0.00");
			tranMaster.setStrTxnAmount(df.format(Long.valueOf(txnReqRes.getTxnData().getDe004_amount()) / 100.00));
			tranMaster.setStrStan(txnData.getDe011_stan());
			tranMaster.setStrCrdAcceptorId(txnData.getDe042_mid());
			tranMaster.setStrCurrncyCode(txnData.getDe049_tran_currency_code());
			tranMaster.setStrMccCode(txnData.getDe018_mcc());
			tranMaster.setStrMti(txnHeader.getMti());
			tranMaster.setStrMaskCardNo(maskCardNumber);
			//tranMaster.setStrParticipantId(Integer.toString(txnData.getParticipant_id()));
			tranMaster.setStrParticipantId(txnData.getParticipant_id());
			tranMaster.setStrRRN(txnData.getDe037_rrn());
			// tranMaster.setStrLocal_tran_date(Utils.getLocalDate());
			
			String CardNumber = txnData.getDe035_track_2();
			String encrypt = aesEncDec.encrypt(CardNumber);
			tranMaster.setStrEcnCardNo(encrypt);
			
			
			long now = System.currentTimeMillis();
			Time sqlTime = new Time(now);
			// tranMaster.setStrLocal_tran_time(Utils.getLocalTime());

			String strExpriyDate = (String) txnData.getDe014_expiry_date();// 1123
			System.out.println("expriyDate :::::::" + strExpriyDate);

			if (!configurationService.checkStanAlreadyExist(txnData.getDe011_stan())) 
			{
				configurationService.insertRequestEntry(tranMaster);

				CardDetailsList getCardDetails = null;

				CardStatus getCardStatus = null;
				if (txnData.getDe002_pan().length() == 16) 
				{
					getCardDetails = configurationService.getCardDetails(txnData.getDe002_pan());
					if (getCardDetails != null) {
						getCardStatus = configurationService.getCardStatus(getCardDetails.getStrTokenCard());
					}
				}
				else 
				{
					getCardDetails = configurationService.getCardDetails(txnData.getDe035_track_2());
					if (getCardDetails != null) 
					{
						getCardStatus = configurationService.getCardStatus(getCardDetails.getStrTokenCard());
					}
				}

				if (getCardDetails != null) // Card exist or not Check POINT
				{
					String cardDetailStatus = getCardDetails.getStrCardStatus();
					if (getCardStatus != null)
					{
						String tokenCard = getCardStatus.getStrCardNumber();
						System.out.println("Token Card::"+tokenCard);
						if (Optional.ofNullable(tokenCard).isPresent()) 
						{
							processResponse.setCode("E0000");
							processResponse.setStatus("Failed");
							processResponse.setMessage("Card Hotlisted");
							processResponse.setDesc("Card Hotlisted");
							txnReqResponse.setResponse(processResponse);
						} 
					}
					
					else 
					{
						String customerIssuedDate = getCardDetails.getStrCustomerIssuedDate();
						if (Optional.ofNullable(customerIssuedDate).isPresent()) 
						{
							if (cardDetailStatus.equals("A")) // Card Status Check POINT
							{
								Date date = new Date();
								String todaysDate = sdformat.format(date);
								Date currentDate = sdformat.parse(todaysDate);

								Date expiryDate = sdformat.parse(strExpriyDate);
								if (currentDate.compareTo(expiryDate) < 0) // Expiry Date Check POINT
								{
									cardAcntLinkageMaster cardaccAcntLinkageMaster = null;
									if (txnData.getDe002_pan().length() == 16) 
									{
										cardaccAcntLinkageMaster = configurationService.getInfofrmCardLinkage(txnData.getDe002_pan());
									} 
									else 
									{
										cardaccAcntLinkageMaster = configurationService.getInfofrmCardLinkage(txnData.getDe035_track_2());
									}

									if (cardaccAcntLinkageMaster != null) 
									{
										txnData.setAccountNumber(cardaccAcntLinkageMaster.getAccountNumber());
										txnData.setAccountType(cardaccAcntLinkageMaster.getAccountType());
										txnReqResponse.setTxnData(txnData);
										txnReqResponse.setHeader(txnHeader);
										txnReqResponse = accountManagementService.sendTransactionDataViaSwitch(txnReqResponse);
									
										tranMaster.setStrMti(txnReqResponse.getHeader().getMti());
										tranMaster.setStrAuthCode(txnReqResponse.getTxnData().getDe038_auth_code());
										tranMaster.setStrResponseCode(txnReqResponse.getTxnData().getDe039_response());
										configurationService.insertResponseEntry(tranMaster);
									} 
									else 
									{
										processResponse.setCode("E0000");
										processResponse.setStatus("Failed");
										processResponse.setMessage("Card Not Linked With Any Account.");
										processResponse.setDesc("Card Not Linked With Any Account.");
										txnReqResponse.setResponse(processResponse);
									}
								} 
								else 
								{
									processResponse.setCode("E0000");
									processResponse.setStatus("Failed");
									processResponse.setMessage("Card Expired.");
									processResponse.setDesc("Card Expired.");
									txnReqResponse.setResponse(processResponse);
								}
							} 
							else 
							{
								processResponse.setCode("E0000");
								processResponse.setStatus("Failed");
								processResponse.setMessage("Card Not Active");
								processResponse.setDesc("Card Not Active");
								txnReqResponse.setResponse(processResponse);
							}
						} 
						else 
						{
							processResponse.setCode("E0000");
							processResponse.setStatus("Failed");
							processResponse.setMessage("Card Not Issued to customer");
							processResponse.setDesc("Card Not Issued to customer");
							txnReqResponse.setResponse(processResponse);
						}
					}
				}
				else 
				{
					processResponse.setCode("E0000");
					processResponse.setStatus("Failed");
					processResponse.setMessage("Card Not Found");
					processResponse.setDesc("Card Not Found");
					txnReqResponse.setResponse(processResponse);
				}
			}
			else 
			{
				processResponse.setCode("E0000");
				processResponse.setStatus("Failed");
				processResponse.setMessage("Duplicate Stan");
				processResponse.setDesc("Duplicate Stan");
				txnReqResponse.setResponse(processResponse);
			}
		} 
		catch (Exception e) 
		{
			processResponse.setCode("E0000");
			processResponse.setStatus("Failed");
			processResponse.setMessage("Internal Server Error");
			processResponse.setDesc("Internal Server Error");
			e.printStackTrace();
			txnReqResponse.setResponse(processResponse);
		}
		return txnReqResponse;
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() 
	{
		return new WebMvcConfigurerAdapter() 
		{
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/loginbyReact").allowedOrigins("http://localhost:3000");
			}
		};

	}

}
