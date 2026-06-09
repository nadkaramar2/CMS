package com.TranEco.cardManagement.customerManagement.dao;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.customerManagement.model.AddressMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.AddressMaintainanceResponse;

@Repository
public class AddressMaintainanceDaoImpl implements AddressMaintainanceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public AddressMaintainanceResponse maintainAddress(AddressMaintainanceRequest request) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName(QueryConstant.SP_AddressMaintainance);
		SqlParameterSource in = new MapSqlParameterSource()
		.addValue("inParticipantID", 			request.getStrParticipantID())
		.addValue("inCustomerID", 				request.getStrCustomerID())
		.addValue("inCitizenID", 				request.getStrCitizenID())
		.addValue("inCIFKey", 					request.getStrCIFKey())
		.addValue("inFunction", 				request.getStrFunctionType())
		.addValue("inAddressType", 				request.getStrAddressType())
		.addValue("inAddress1", 				request.getStrAddress1())
		.addValue("inAddress2", 				request.getStrAddress2())
		.addValue("inAddress3", 				request.getStrAddress3())
		.addValue("inCity", 					request.getStrCity())
		.addValue("inState", 					request.getStrState())
		.addValue("inCountryCode", 				request.getStrCountryCode())
		.addValue("inPinCode", 					request.getStrPinCode())
		.addValue("inAddressPrimaryFlag", 		request.getStrAddressPrimaryFlag());
		Map<String, Object> out = jdbcCall.execute(in);
		AddressMaintainanceResponse outResponseObj = new  AddressMaintainanceResponse();
		outResponseObj.setOutResponseCode(out.get("outresponse").toString());
		outResponseObj.setMessage(out.get("outdescription").toString());
		
		return outResponseObj;
	}
}
