package com.TranEco.cardManagement.customerManagement.dao;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.customerManagement.model.EmailMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.EmailMaintainanceResponse;

@Repository
public class EmailMaintainanceDaoImpl implements EmailMaintainanceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public EmailMaintainanceResponse maintainEmail(EmailMaintainanceRequest request) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName(QueryConstant.SP_EmailMaintainance);
		SqlParameterSource in = new MapSqlParameterSource()
		.addValue("inParticipantID", 			request.getStrParticipantID())
		.addValue("inCitizenID", 				request.getStrCitizenID())
		.addValue("inCustomerID", 				request.getStrCustomerID())
		.addValue("inCIFKey", 					request.getStrCIFKey())
		.addValue("inFunction", 				request.getStrFunction())
		.addValue("inEmailType", 				request.getStrEmailType())
		.addValue("inEmailID", 					request.getStrEmailID())
		.addValue("inEmailPrimaryFlag", 		request.getStrEmailPrimaryFlag());
		Map<String, Object> out = jdbcCall.execute(in);
		EmailMaintainanceResponse outResponseObj = new  EmailMaintainanceResponse();
		outResponseObj.setOutResponseCode(out.get("outresponse").toString());
		outResponseObj.setMessage(out.get("outdescription").toString());
		
		return outResponseObj;
	}
}
