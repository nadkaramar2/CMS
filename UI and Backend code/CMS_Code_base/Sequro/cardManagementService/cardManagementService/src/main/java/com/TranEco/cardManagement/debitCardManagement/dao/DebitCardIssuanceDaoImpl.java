package com.TranEco.cardManagement.debitCardManagement.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.debitCardManagement.model.CardStatusRequest;
import com.TranEco.cardManagement.debitCardManagement.model.CardStatusResponse;
import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceRequest;
import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceResponse;
import com.TranEco.cardManagement.debitCardManagement.model.NCMCServiceUpdationRequest;
import com.TranEco.cardManagement.debitCardManagement.model.NCMCServiceUpdationResponse;
import com.TranEco.cardManagement.debitCardManagement.model.PINManagementRequest;
import com.TranEco.cardManagement.debitCardManagement.model.PINManagementResponse;
import com.TranEco.cardManagement.model.Cfg_Card_Template;
import com.TranEco.cardManagement.services.EhCacheServiceImpl;
import com.TranEco.cardManagement.utils.CardNumberGenerator;
import com.TranEco.cardManagement.utils.CardUtils;
import com.TranEco.cardManagement.utils.EncDecUtils;

@Repository("debitCardIssuanceDao")
public class DebitCardIssuanceDaoImpl implements DebitCardIssuanceDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	EhCacheServiceImpl ehCacheService;
	
	@Autowired
	EncDecUtils encDecUtils;
	
	@Autowired
	CardUtils cardUtils;
	
	
	@Override
	public DebitCardIssuanceResponse debitCardIssuanceRequest(DebitCardIssuanceRequest request,
			Cfg_Card_Template cfg_Card_template, String strInBin) 
	{
		
		try 
		{
		int count = 0;
		DebitCardIssuanceResponse response = new DebitCardIssuanceResponse();
		String tokenCard = CardUtils.tokenCard();
		if ("NEWCARD".equals(request.getInFunction())) 
		{
			count += jdbcTemplate.update(QueryConstant.INSERT_CARD_DETAILS, new Object[] 
			{ 
					request.getInPartID(),
					request.getInCardType(), 
					CardUtils.maskNumber(request.getInCard()), 
					tokenCard,
					cardUtils.encrypt(request.getInCard()),
					//request.getInCard(),
					1, request.getInCustomerID(), cfg_Card_template.getService_code(),
					new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
					// request.getInFunction().equalsIgnoreCase("RNWCARD") ? "" :
					// CardUtils.calculateExpDate(),
					request.getInFunction().equalsIgnoreCase("RNWCARD") ? "" : GenerateExpiryDate(cfg_Card_template), 1,
					cfg_Card_template.getDaily_pin_retry_limit(), cfg_Card_template.getConsecutive_pin_retry_limit(),
					cfg_Card_template.getOnline_atm_limit(), cfg_Card_template.getOnline_pos_limit(),
					cfg_Card_template.getOnline_ecom_limit(), cfg_Card_template.getOffline_limit(),
					cfg_Card_template.getMonthly_limit(), cfg_Card_template.getWeekly_limit(),
					cfg_Card_template.getDaily_limit(), new Timestamp(System.currentTimeMillis()),
					request.getInUserID(), request.getInUserID(), request.getInInstantFlag(), request.getInBranchCode(),new Timestamp(System.currentTimeMillis()) });
			count += jdbcTemplate.update(QueryConstant.INSERT_CARD_PLASTIC,
					new Object[] { request.getInPartID(), tokenCard, 1, request.getInCardIssueCode(),
							request.getInEmbossLine1(), request.getInEmbossLine2(), request.getInEncodeFirstName(),
							request.getInEncodeMiddleName(), request.getInEncodeLastName(),
							request.getInCardMailerFlag() == null ? cfg_Card_template.getCard_mailer_Issue_flag()
									: request.getInCardMailerFlag(),
							request.getInPinMailerFlag() == null ? cfg_Card_template.getPin_mailer_Issue_flag()
									: request.getInCardMailerFlag()

					});
			if (count > 1) 
			{
				response.setOutResponseCode("00");
				response.setMessage("Success");
				response.setOutCard(CardUtils.maskNumber(request.getInCard()));
				response.setOutTokenCard(tokenCard);
			} else 
			{
				response.setOutResponseCode("99");
				response.setMessage("Unable to add card !");
			}
		} 
		else if ("RPLCARD".equals(request.getInFunction())) 
		{
			if ("Y".equals(request.getInHotListFlag())) 
			{
				CardStatusRequest cardStatusRequest = new CardStatusRequest();
				cardStatusRequest.setStrCardNo(request.getInCard());
				cardStatusRequest.setStrDescription(request.getInCode());
				cardStatusRequest.setStrMemberNo(request.getInMbr());
				cardStatusRequest.setStrParticipant_ID(request.getInPartID());
				//cardStatusRequest.setStrStatusChangeUser("sachin");
				cardStatusRequest.setStrStatusChangeUser(request.getInUserID());
				cardStatusRequest.setStrStatusCode("11");
				CardStatusResponse statusResponse = cardStatusRequest(cardStatusRequest);
				if (!"00".equals(statusResponse.getOutResponseCode())) 
				{
					response.setOutResponseCode(statusResponse.getOutResponseCode());
					response.setMessage(statusResponse.getMessage());
					return response;
				}
			}

			List<Map<String, Object>> rows = jdbcTemplate.queryForList(QueryConstant.GET_CARD_DETAIL,
					new Object[] { request.getInPartID(), request.getInCard(), request.getInMbr() });
			for (Map<String, Object> row : rows) 
			{
				count = jdbcTemplate.update(QueryConstant.INSERT_CARD_DETAILS_RPLCARD,
						new Object[] { request.getInPartID(), request.getInCardType(),
								CardUtils.maskNumber(request.getInNewCard()), tokenCard, 
								cardUtils.encrypt(request.getInNewCard()),
								//request.getInNewCard(),
								request.getInMbr(), row.get("customer_id"), row.get("service_code"),
								new Timestamp(System.currentTimeMillis()), row.get("cardholder_since"),
								// CardUtils.calculateExpDate(),
								GenerateExpiryDate(cfg_Card_template), 1, null, row.get("daily_pin_retry_limit"),
								row.get("daily_pin_retry_count"), row.get("consecutive_pin_retry_limit"),
								row.get("consecutive_pin_retry_count"), row.get("online_atm_limit"),
								row.get("online_pos_limit"), row.get("online_ecom_limit"), row.get("offline_limit"),
								row.get("monthly_limit"), row.get("weekly_limit"), row.get("daily_limit"),
								new Timestamp(System.currentTimeMillis()), request.getInUserID(), request.getInUserID(),
								row.get("instant_flag"), row.get("order_branch") });

				break;
			}

			count += jdbcTemplate.update(QueryConstant.INSERT_CARD_PLASTIC_RPLCARD,
					new Object[] { request.getInPartID(), tokenCard, request.getInMbr(), request.getInCardIssueCode(),
							request.getInEmbossLine1(), request.getInEmbossLine2(), request.getInEncodeFirstName(),
							request.getInEncodeMiddleName(), request.getInEncodeLastName(),
							request.getInCardMailerFlag(), request.getInPinMailerFlag() });

			if (count > 1) 
			{
				response.setOutResponseCode("00");
				response.setMessage("Success");
				response.setOutCard(request.getInNewCard());
				response.setOutTokenCard(tokenCard);
			} 
			else 
			{
				response.setOutResponseCode("99");
				response.setMessage("Unable to add card !");
			}

		} else if ("RNWCARD".equals(request.getInFunction())) 
		{
			Integer status = jdbcTemplate.queryForObject(QueryConstant.GET_CARD_STATUS,
					new Object[] { request.getInPartID(), request.getInCard(), request.getInMbr() }, Integer.class);

			if (status < 10) 
			{
				/*jdbcTemplate.update(QueryConstant.UPDATE_CARD_EXP, new Object[] {
						GenerateExpiryDate(cfg_Card_template), request.getInPartID(), request.getInCard(),
						request.getInMbr() });*/
				List<Map<String, Object>> rows = jdbcTemplate.queryForList(QueryConstant.GET_CARD_DETAIL,
						new Object[] { request.getInPartID(), request.getInCard(), request.getInMbr() });
				
				Map<String, Object> row = rows.get(0);
				
				count = jdbcTemplate.update(QueryConstant.INSERT_CARD_DETAILS_RNWCARD,
						new Object[] { request.getInPartID(), row.get("card_type"),
								row.get("card_number"), tokenCard, row.get("enc_card"),
								2, row.get("customer_id"), row.get("service_code"),
								new Timestamp(System.currentTimeMillis()), row.get("cardholder_since"),
								GenerateExpiryDate(cfg_Card_template), 1, null, row.get("daily_pin_retry_limit"),
								row.get("daily_pin_retry_count"), row.get("consecutive_pin_retry_limit"),
								row.get("consecutive_pin_retry_count"), row.get("online_atm_limit"),
								row.get("online_pos_limit"), row.get("online_ecom_limit"), row.get("offline_limit"),
								row.get("monthly_limit"), row.get("weekly_limit"), row.get("daily_limit"),
								new Timestamp(System.currentTimeMillis()), request.getInUserID(), request.getInUserID(),
								row.get("instant_flag"), row.get("order_branch") });
				
				List<Map<String, Object>> plastic_rows = jdbcTemplate.queryForList(QueryConstant.GET_PLASTIC_DETAILS,
						new Object[] { row.get("token_card"), request.getInPartID() });
				
				Map<String, Object> plastic_row = plastic_rows.get(0);
				
				count = jdbcTemplate.update(QueryConstant.INSERT_PLASTIC_DETAILS,
						new Object[] { request.getInPartID(), tokenCard, 2, plastic_row.get("card_issue_code"), plastic_row.get("emboss_line1")
								,plastic_row.get("emboss_line2"), plastic_row.get("encode_first_name"), plastic_row.get("encode_middle_name"), plastic_row.get("encode_last_name"),
								plastic_row.get("plastic_flag"), plastic_row.get("pinmailer_flag")
						});
				
				response.setOutResponseCode("00");
				response.setMessage("Success");
				response.setOutCard(request.getInCard());
				response.setOutTokenCard(tokenCard);

			} else {
				response.setOutResponseCode("99");
				response.setMessage("Card Renewal Not Allowed!");
			}

		} else if ("INSTANTCARD".equals(request.getInFunction())) 
		{
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(QueryConstant.INSERT_CUST_DETAILS,
							new String[] { "id" });
					ps.setString(1, request.getInPartID());
					return ps;
				}
			}, keyHolder);

			request.setInCustomerID("" + keyHolder.getKey());

			count += jdbcTemplate.update(QueryConstant.INSERT_CUST_ADD, new Object[] { request.getInCustomerID() });

			count += jdbcTemplate.update(QueryConstant.INSERT_CUST_CONTACT, new Object[] { request.getInCustomerID() });

			count += jdbcTemplate.update(QueryConstant.INSERT_CUST_DOCUMENT,
					new Object[] { request.getInCustomerID() });

			count += jdbcTemplate.update(QueryConstant.INSERT_CUST_EMAIL, new Object[] { request.getInCustomerID() });
			
			String encryptedCard = cardUtils.encrypt(request.getInCard()); 
			
			count += jdbcTemplate.update(QueryConstant.INSERT_INSTANT_CARD_DETAILS, new Object[] { request.getInPartID(),
					request.getInCardType(), CardUtils.maskNumber(request.getInCard()), tokenCard, 
					encryptedCard,
					//request.getInCard(),
					1, request.getInCustomerID(), cfg_Card_template.getService_code(),
					new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()),
					// request.getInFunction().equalsIgnoreCase("RNWCARD") ? "" :
					// CardUtils.calculateExpDate(),
					request.getInFunction().equalsIgnoreCase("RNWCARD") ? "" : GenerateExpiryDate(cfg_Card_template), 1,
					cfg_Card_template.getDaily_pin_retry_limit(), cfg_Card_template.getConsecutive_pin_retry_limit(),
					cfg_Card_template.getOnline_atm_limit(), cfg_Card_template.getOnline_pos_limit(),
					cfg_Card_template.getOnline_ecom_limit(), cfg_Card_template.getOffline_limit(),
					cfg_Card_template.getMonthly_limit(), cfg_Card_template.getWeekly_limit(),
					cfg_Card_template.getDaily_limit(), new Timestamp(System.currentTimeMillis()),
					request.getInUserID(), request.getInUserID(), request.getInInstantFlag(), request.getInBranchCode()
					
			});
					
					
			count += jdbcTemplate.update(QueryConstant.INSERT_CARD_PLASTIC,
					new Object[] { request.getInPartID(), tokenCard, 1,
							request.getInCardIssueCode() == null ? "" : request.getInCardIssueCode(),
							request.getInEmbossLine1(), request.getInEmbossLine2(), request.getInEncodeFirstName(),
							request.getInEncodeMiddleName(), request.getInEncodeLastName(),
							request.getInCardMailerFlag() == null ? cfg_Card_template.getCard_mailer_Issue_flag()
									: request.getInCardMailerFlag(),
							request.getInPinMailerFlag() == null ? cfg_Card_template.getPin_mailer_Issue_flag()
									: request.getInCardMailerFlag()

					});
			if (count > 1) 
			{
				response.setOutResponseCode("00");
				response.setMessage("Success");
				response.setOutCard(request.getInCard());
				response.setOutTokenCard(tokenCard);
				response.setEncryptedCardNumber(encryptedCard);
			} 
			else 
			{
				response.setOutResponseCode("99");
				response.setMessage("Unable to add card !");
			}

		}
		return response;
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
		
	}

	/*
	@Override
	public CardStatusResponse cardStatusRequest(CardStatusRequest request) 
	{
		CardStatusResponse response = new CardStatusResponse();

		List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.VARCHAR), new SqlParameter(Types.VARCHAR),
				new SqlParameter(Types.VARCHAR), new SqlParameter(Types.INTEGER), new SqlParameter(Types.VARCHAR),
				new SqlParameter(Types.VARCHAR), new SqlOutParameter("outresponse", Types.VARCHAR),
				new SqlOutParameter("outresponseMsg", Types.VARCHAR));

		Map<String, Object> t = jdbcTemplate.call(new CallableStatementCreator() 
		{
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement callableStatement = con.prepareCall("{call cardStatus (?,?,?,?,?,?,?,?)}");
				callableStatement.setString(1, request.getStrParticipant_ID());
				callableStatement.setString(2, request.getStrCardNo());
				callableStatement.setString(3, request.getStrMemberNo());
				callableStatement.setString(4, request.getStrStatusCode());
				callableStatement.setString(5, request.getStrDescription());
				callableStatement.setString(6,
						request.getStrStatusChangeUser() == null ? "" : request.getStrStatusChangeUser());
				callableStatement.registerOutParameter(7, Types.VARCHAR);
				callableStatement.registerOutParameter(8, Types.VARCHAR);
				return callableStatement;
			}
		}, parameters);
		response.setOutResponseCode(t.get("outresponse").toString());
		response.setMessage(t.get("outresponseMsg").toString());

		return response;
	}
	*/
	
	@Override
	public CardStatusResponse cardStatusRequest(CardStatusRequest request) 
	{
		CardStatusResponse response = new CardStatusResponse();
		try 
		{
			int iCount = 0;
			int Count = 0;
			Integer cardCount = jdbcTemplate.queryForObject(QueryConstant.CHECK_CARD_COUNT_CARD_STATUS,
					new Object[] { request.getStrParticipant_ID(),request.getStrCardNo(),request.getStrMemberNo() }, Integer.class);
			 
		//if (Integer.parseInt(cardDetails.getStrCardCount()) > 0 || cardDetails.getStrCardStatus() != null)
			if (cardCount != null)
			{
			Integer statusCode = jdbcTemplate.queryForObject(QueryConstant.CHECK_CFG_STATUS_EXIST,
					new Object[] { request.getStrStatusCode() }, Integer.class);
			 
			if(statusCode != null)
			{
				iCount+= jdbcTemplate.update(QueryConstant.UPDATE_CARD_DETAILS,
						new Object[] { request.getStrStatusCode(),request.getStrCardNo(),request.getStrParticipant_ID()});
				
				
				Count+= jdbcTemplate.update(QueryConstant.INSERT_CARD_STATUS_HISTORY,
						new Object[] { request.getStrParticipant_ID(),request.getStrCardNo(),request.getStrMemberNo(),request.getStrStatusChangeUser(), new Date(),request.getStrStatusCode(),new Date(),request.getStrDescription()});
				
				response.setOutResponseCode("00");
				response.setMessage("success");
			}
			else 
			{
				response.setOutResponseCode("91");
				response.setMessage("Permanent Card Status can not be Chanaged !");
			}
		}
		else 
		{
			response.setOutResponseCode("93");
			response.setMessage("Card Number Not Found !");
	  }
    }
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public PINManagementResponse PINManagementRequest(PINManagementRequest request) {
		PINManagementResponse response = new PINManagementResponse();
		int iCount = 0;
		if (request.getStrPinMailerUpdateFlag().equals("Y")) 
		{
			iCount += jdbcTemplate.update(QueryConstant.UPDATE_PIN_MAILER_FLAG,
					new Object[] { request.getStrPinMailerIssueFlag(), new Timestamp(System.currentTimeMillis()),
							request.getStrUserID(), request.getStrParticipantID(), request.getStrCardNo(),
							request.getStrMemberNo() });
		}

		if (request.getStrPinRetryFlag().equals("Y")) 
		{
			iCount += jdbcTemplate.update(QueryConstant.UPDATE_PIN_RETRY_LIMIT,
					new Object[] { request.getStrConsecutivePinRetryLimit(), request.getStrDailyPinRetryLimit(),
							new Timestamp(System.currentTimeMillis()), request.getStrUserID(),
							request.getStrParticipantID(), request.getStrCardNo(), request.getStrMemberNo() });
		}
		if (iCount > 0) 
		{
			response.setOutResponseCode("00");
			response.setMessage("Success");
		} 
		else 
		{
			response.setOutResponseCode("99");
			response.setMessage("Error in Card Status Update");
		}
		return response;
	}

	@Override
	public NCMCServiceUpdationResponse NCMCServiceUpdationRequest(NCMCServiceUpdationRequest request) 
	{
		NCMCServiceUpdationResponse response = new NCMCServiceUpdationResponse();

		int iDeleteCount = 0;
		if (request.getNCMCServiceList() != null && request.getNCMCServiceList().size() > 0) 
		{
			iDeleteCount = jdbcTemplate.update(QueryConstant.DELETE_CARD_SERVICE,
					new Object[] { request.getStrParticipantID(), request.getStrCardNo(), request.getStrMemberNo() });

			int i = 0, iCount = 0;
			String strQuery = "insert into card_ncmc_service values ";
			String strAppendQuery = "";
			for (i = 0; i < request.getNCMCServiceList().size(); i++) 
			{
				if (strAppendQuery.equals("")) 
				{
					strAppendQuery = "(" + request.getStrCardNo() + ", '"
							+ request.getNCMCServiceList().get(i).getStrNCMCServiceID() + "' , "
							+ request.getStrParticipantID() + ", " + request.getStrMemberNo() + ")";
				} 
				else
					strAppendQuery = strAppendQuery + ", (" + request.getStrCardNo() + ", '"
							+ request.getNCMCServiceList().get(i).getStrNCMCServiceID() + "' , "
							+ request.getStrParticipantID() + ", " + request.getStrMemberNo() + ")";
			}
			if (!strAppendQuery.equals("")) 
			{
				strQuery = strQuery + strAppendQuery;
				iCount += jdbcTemplate.update(strQuery);
				if (iCount == i) 
				{
					response.setOutResponseCode("00");
					response.setMessage("Success");
				}
				else 
				{
					response.setOutResponseCode("99");
					response.setMessage("Error in updating Service");
				}

			}
		} else {
			response.setOutResponseCode("99");
			response.setMessage("Empty NCMC List !");
		}

		return response;
	}

	@Override
	public int getBinSuffixFlag(String bin) {
		int count = jdbcTemplate.queryForObject(QueryConstant.GET_BIN_SUFFIX_FLAG, new Object[] {bin}, 
				Integer.class);
		return count;
	}

	public String GenerateExpiryDate(Cfg_Card_Template cfg_Card_template) {
		String strExpiryDate = null;
		if (cfg_Card_template.getExpiry_cfg_type().equals("Y"))
			strExpiryDate = CardUtils.calculateExpDatebyYear(cfg_Card_template.getExpiry_year());
		else if (cfg_Card_template.getExpiry_cfg_type().equals("M"))
			strExpiryDate = CardUtils.calculateExpDatebyMonth(cfg_Card_template.getExpiry_month());

		if (strExpiryDate != null)
			return strExpiryDate;
		else
			return "00-00-0000 00:00:00";
	}

	@Override
	public String getLastSequenceNo(String strParticipantID, String strCardBin) 
	{
		try 
		{
		int iCardCount = 0;
		String LastCard = null;
		BigInteger strLastCardNo = null;
		StringBuilder builder = new StringBuilder("");
		iCardCount = (int) jdbcTemplate.queryForObject(QueryConstant.CHECK_RECORD_PRESENT,
				new Object[] { strParticipantID,cardUtils.encrypt(strCardBin)+"%", }, Integer.class);
		
		if (iCardCount > 0) 
		{
			LastCard = jdbcTemplate.queryForObject(QueryConstant.CHECK_LAST_CARD_NO,
					new Object[] { strParticipantID, cardUtils.encrypt(strCardBin)+"%", }, String.class);
			strLastCardNo = new BigInteger(cardUtils.decrypt(LastCard.substring(0, 15)));
			
			builder.append(strLastCardNo.add(new BigInteger("1")))
					.append(CardNumberGenerator.getCheckDigit("" + strLastCardNo));
		} 
		else 
		{
			strLastCardNo = new BigInteger(StringUtils.rightPad(strCardBin, 15, '0').substring(0, 15));
			builder.append(strLastCardNo.add(new BigInteger("1")))
					.append(CardNumberGenerator.getCheckDigit("" + strLastCardNo));
		}
		return builder.toString();
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Optional<String> getTokenCard(String cardNo) {
		try {
			return jdbcTemplate.queryForObject(QueryConstant.GET_TOKEN, new Object[] { cardNo }, Optional.class);
		}catch (Exception e) {
			return null;
		}
	}
	

	@Override
	public CardStatusResponse getCardDetailsBasedOnCardNumber(CardStatusRequest request) 
	{
		CardStatusResponse response = new CardStatusResponse();
		int iCount = 0;
		int Count = 0;
		try 
		{
			//CardDetails cardDetails = new CardDetails();
			Integer cardCount = jdbcTemplate.queryForObject(QueryConstant.CHECK_CARD_COUNT_CARD_STATUS,
					new Object[] { request.getStrParticipant_ID(),request.getStrCardNo(),request.getStrMemberNo() }, Integer.class);
			 
		//if (Integer.parseInt(cardDetails.getStrCardCount()) > 0 || cardDetails.getStrCardStatus() != null)
			if (cardCount != null)
			{
			Integer statusCode = jdbcTemplate.queryForObject(QueryConstant.CHECK_CFG_STATUS_EXIST,
					new Object[] { request.getStrStatusCode() }, Integer.class);
			 
			if(statusCode != null)
			{
				iCount+= jdbcTemplate.update(QueryConstant.UPDATE_CARD_DETAILS,
						new Object[] { request.getStrStatusCode(),request.getStrCardNo(),request.getStrParticipant_ID()});
				
				
				Count+= jdbcTemplate.update(QueryConstant.INSERT_CARD_STATUS_HISTORY,
						new Object[] { request.getStrParticipant_ID(),request.getStrCardNo(),request.getStrMemberNo(),request.getStrStatusChangeUser(), new Date(),request.getStrStatusCode(),new Date(),request.getStrDescription()});
				
				response.setOutResponseCode("00");
				response.setMessage("success");
			}
			else 
			{
				response.setOutResponseCode("91");
				response.setMessage("Permanent Card Status can not be Chanaged !");
			}
		}
		else 
		{
			response.setOutResponseCode("93");
			response.setMessage("Card Number Not Found !");
	  }
    }
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return response;
	}
}
