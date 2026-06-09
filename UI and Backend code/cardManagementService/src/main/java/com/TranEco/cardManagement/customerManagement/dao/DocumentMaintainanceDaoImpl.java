package com.TranEco.cardManagement.customerManagement.dao;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.customerManagement.model.DocumentMaintainanceRequest;
import com.TranEco.cardManagement.customerManagement.model.DocumentMaintainanceResponse;

@Repository
public class DocumentMaintainanceDaoImpl implements DocumentMaintainanceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public DocumentMaintainanceResponse maintainDocument(DocumentMaintainanceRequest request) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName(QueryConstant.SP_DocumentMaintainance);
		SqlParameterSource in = new MapSqlParameterSource()
		.addValue("inParticipantID", 			request.getStrParticipantID())
		.addValue("inCitizenID", 				request.getStrCitizenID())
		.addValue("inCIFKey", 					request.getStrCIFKey())
		.addValue("inCustomerID", 				request.getStrCustomerID())
		.addValue("inFunction", 				request.getStrFunction())
		.addValue("inDocumentType", 			request.getStrDocumentType())
		.addValue("inDocumentNumber", 			request.getStrDocumentNumber())
		.addValue("inDocumentID", 				request.getStrDocumentID());
		Map<String, Object> out = jdbcCall.execute(in);
		DocumentMaintainanceResponse outResponseObj = new  DocumentMaintainanceResponse();
		outResponseObj.setOutResponseCode(out.get("outresponse").toString());
		outResponseObj.setMessage(out.get("outdescription").toString());
		
		return outResponseObj;
	}
}
