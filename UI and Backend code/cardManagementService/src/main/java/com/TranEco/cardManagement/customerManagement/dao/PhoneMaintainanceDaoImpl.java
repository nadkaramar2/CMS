package com.TranEco.cardManagement.customerManagement.dao;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.customerManagement.model.PhoneMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.PhoneMaintainanceResponse;

@Repository
public class PhoneMaintainanceDaoImpl implements PhoneMaintainanceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public PhoneMaintainanceResponse maintainPhone(PhoneMaintainanceRequest request) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName(QueryConstant.SP_PhoneMaintainance);
		SqlParameterSource in = new MapSqlParameterSource()
		.addValue("inParticipantID", 			request.getStrParticipantID())
		.addValue("inCitizenID", 				request.getStrCitizenID())
		.addValue("inCustomerID", 				request.getStrCustomerID())
		.addValue("inCIFKey", 					request.getStrCIFKey())
		.addValue("inFunction", 				request.getStrFunction())
		.addValue("inPhoneType", 				request.getStrPhoneType())
		.addValue("inPhoneNo", 					request.getStrPhoneNo())
		.addValue("inPhonePrimaryFlag", 		request.getStrPhonePrimaryFlag());
		Map<String, Object> out = jdbcCall.execute(in);
		PhoneMaintainanceResponse outResponseObj = new  PhoneMaintainanceResponse();
		outResponseObj.setOutResponseCode(out.get("outresponse").toString());
		outResponseObj.setMessage(out.get("outdescription").toString());
		
		return outResponseObj;
	}
}
