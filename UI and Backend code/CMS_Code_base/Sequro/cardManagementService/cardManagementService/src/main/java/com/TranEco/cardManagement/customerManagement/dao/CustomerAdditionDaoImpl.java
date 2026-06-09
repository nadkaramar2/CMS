package com.TranEco.cardManagement.customerManagement.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.customerManagement.model.CustomerAdditionRequest;
import com.TranEco.cardManagement.customerManagement.model.CustomerAdditionResponse;

@Repository
public class CustomerAdditionDaoImpl implements CustomerAdditionDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public CustomerAdditionResponse addCustomer(CustomerAdditionRequest request) 
	{
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName(QueryConstant.SP_CustomerAddittion);
		SqlParameterSource in = new MapSqlParameterSource()
		.addValue("inParticipantID", 			request.getStrParticipantID())
		.addValue("inCitizenID", 				request.getStrCitizenID())
		.addValue("inCIFKey", 					request.getStrCIFKey())
		.addValue("inFirstName", 				request.getStrFirstName())
		.addValue("inMiddleName", 				request.getStrMiddleName())
		.addValue("inLastName", 				request.getStrLastName())
		.addValue("inAddressType", 				request.getStrAddressType())
		.addValue("inAddress1", 				request.getStrAddress1())
		.addValue("inAddress2", 				request.getStrAddress2())
		.addValue("inAddress3", 				request.getStrAddress3())
		.addValue("inCity", 					request.getStrCity())
		.addValue("inState", 					request.getStrState())
		.addValue("inCountryCode", 				request.getStrCountryCode())
		.addValue("inPinCode", 					request.getStrPinCode())
		.addValue("inAddressPrimaryFlag", 		request.getStrAddressPrimaryFlag())
		.addValue("inPhoneType", 				request.getStrPhoneType())
		.addValue("inPhoneNo", 					request.getStrPhoneNo())
		.addValue("inPhonePrimaryFlag", 		request.getStrPhonePrimaryFlag())
		.addValue("inEmailType", 				request.getStrEmailType())
		.addValue("inEmailID", 					request.getStrEmailID())
		.addValue("inEmailPrimaryFlag", 		request.getStrEmailPrimaryFlag())
		.addValue("inGender", 					request.getStrGender())
		.addValue("inDOB", 					    request.getStrDOB())
		.addValue("inMotherMaidenName", 		request.getStrMotherMaidenName())
		.addValue("inDocumentType", 			request.getStrDocumentType())
		.addValue("inDocumentNumber", 			request.getStrDocumentNumber())
		.addValue("inCustomerID", 			request.getStrSelectID());
		Map<String, Object> out = jdbcCall.execute(in);
		CustomerAdditionResponse outResponseObj = new  CustomerAdditionResponse();
		outResponseObj.setOutResponseCode(out.get("outresponse").toString());
		outResponseObj.setMessage(out.get("outdescription").toString());
		if(out.get("outcustomerid") != null) 
		{
			outResponseObj.setOutcustomerid(out.get("outcustomerid").toString());
		}
		else 
		{
			outResponseObj.setOutcustomerid("");
		}
		return outResponseObj;
	}

}
