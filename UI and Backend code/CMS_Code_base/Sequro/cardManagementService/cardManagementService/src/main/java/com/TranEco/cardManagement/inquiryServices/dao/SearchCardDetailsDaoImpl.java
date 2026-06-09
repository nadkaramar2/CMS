package com.TranEco.cardManagement.inquiryServices.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.inquiryServices.model.AccountDetailsList;
import com.TranEco.cardManagement.inquiryServices.model.CardDetails;
import com.TranEco.cardManagement.inquiryServices.model.CardNCMCServiceList;
import com.TranEco.cardManagement.inquiryServices.model.CardStatusDetailsList;
import com.TranEco.cardManagement.inquiryServices.model.EnquireCardDetailsRequest;
import com.TranEco.cardManagement.inquiryServices.model.EnquireCardDetailsResponse;
import com.TranEco.cardManagement.inquiryServices.model.LimitDetails;

@Repository
public class SearchCardDetailsDaoImpl implements SearchCardDetailsDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public EnquireCardDetailsResponse SearchCardDetails(EnquireCardDetailsRequest request) 
	{
		EnquireCardDetailsResponse response = new EnquireCardDetailsResponse();
		CardDetails cardDetails = new CardDetails();
		if (request.getStrParticipantID() != null && request.getStrCardNumber() != null
				&& request.getStrMemberNo() != null) 
		{
			cardDetails = (CardDetails) jdbcTemplate.queryForObject(
					QueryConstant.Get_Card_Details, new Object[] { request.getStrParticipantID(),
							//CardUtils.encrypt(request.getStrCardNumber(), CardUtils.getSecretKeys()),CardUtils.encrypt(request.getStrCardNumber(), CardUtils.getSecretKeys()), request.getStrMemberNo() },
					request.getStrCardNumber(), request.getStrMemberNo() },
					new BeanPropertyRowMapper<CardDetails>(CardDetails.class));
			if (cardDetails == null) 
			{
				response.setOutResponseCode("01");
				response.setMessage("Error: Could not fetch Card Data");
			}
			else 
			{
				response.setOutResponseCode("00");
				response.setMessage("Success");
				response.setCardDetails(cardDetails);
				LimitDetails limitDetails = (LimitDetails) jdbcTemplate.queryForObject(
						QueryConstant.Get_Card_Limit_Details,
						new Object[] { request.getStrParticipantID(),
								//CardUtils.encrypt(request.getStrCardNumber(), CardUtils.getSecretKeys()) ,CardUtils.encrypt(request.getStrCardNumber(), CardUtils.getSecretKeys()),
								request.getStrCardNumber(),
								request.getStrMemberNo() },
						new BeanPropertyRowMapper<LimitDetails>(LimitDetails.class));
				if (limitDetails != null)
					response.setLimitDetails(limitDetails);
				
				List<AccountDetailsList> accountDetailsList = null;

				accountDetailsList = jdbcTemplate.query(QueryConstant.Get_Card_Account_List,
						new Object[] { request.getStrParticipantID(), request.getStrCardNumber(),
								request.getStrMemberNo() },
						new BeanPropertyRowMapper<AccountDetailsList>(AccountDetailsList.class));
				if (accountDetailsList != null)
					response.setAccountDetailsList(accountDetailsList);

				List<CardStatusDetailsList> cardStatusDetailsList = null;

				cardStatusDetailsList = jdbcTemplate.query(QueryConstant.Get_Card_Status_List,
						new Object[] { request.getStrParticipantID(), request.getStrCardNumber(),
								request.getStrMemberNo() },
						new BeanPropertyRowMapper<CardStatusDetailsList>(CardStatusDetailsList.class));
				if (cardStatusDetailsList != null)
					response.setCardStatusDetailsList(cardStatusDetailsList);

				List<CardNCMCServiceList> cardNCMCServiceList = null;

				cardNCMCServiceList = jdbcTemplate.query(QueryConstant.Get_Card_NCMC_Service_List,
						new Object[] { request.getStrParticipantID(), request.getStrCardNumber(),
								request.getStrMemberNo() },
						new BeanPropertyRowMapper<CardNCMCServiceList>(CardNCMCServiceList.class));
				if (cardNCMCServiceList != null)
					response.setCardNCMCServiceList(cardNCMCServiceList);
			}
		} 
		else 
		{
			response.setOutResponseCode("99");
			response.setMessage("Please provide proper Input");
		}
		return response;
	}
}
