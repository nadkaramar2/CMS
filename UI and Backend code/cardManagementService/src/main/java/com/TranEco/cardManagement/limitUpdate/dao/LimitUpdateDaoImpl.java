package com.TranEco.cardManagement.limitUpdate.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.limitUpdate.model.LimitUpdateRequest;
import com.TranEco.cardManagement.limitUpdate.model.LimitUpdateResponse;
import com.TranEco.cardManagement.common.QueryConstant;

@Repository
public class LimitUpdateDaoImpl implements LimitUpdateDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public LimitUpdateResponse limitUpdate(LimitUpdateRequest request) 
	{
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName(QueryConstant.SP_LimitUpdate);
		SqlParameterSource in = new MapSqlParameterSource()
		.addValue("inParticipantID", 			request.getStrPartID())
		.addValue("inCardNo", 					request.getStrCardNumber())
		.addValue("inCardSeqNo", 				request.getStrCardSeqNumber())
		.addValue("inOnlineATMLimit", 		    request.getStrOnlineATMLimit())
		.addValue("inOnlinePOSLimit", 			request.getStrOnlinePOSLimit())
		.addValue("inOnlineECOMLimit", 		    request.getStrOnlineECOMLimit())
		.addValue("inOfflineLimit", 			request.getStrOfflineLimit())
		.addValue("inMonthlyLimit", 		request.getStrMonthlyLimit())
		.addValue("inWeeklyLimit", 		request.getStrWeeklyLimit())
		.addValue("inDailyLimit", 		request.getStrDailyLimit());
		Map<String, Object> out = jdbcCall.execute(in);
		LimitUpdateResponse outResponseObj = new  LimitUpdateResponse();
		outResponseObj.setOutResponseCode(out.get("outresponse").toString());
		outResponseObj.setMessage(out.get("outdescription").toString());
		return outResponseObj;
	}

}
