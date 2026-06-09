/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to perform business logic for institution.
 * 
 *@History
 *===============================================================================================================================================
 *     @Version         @Date         	@Author                 @Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to perform business logic for institution.
 *===============================================================================================================================================
 *
 */

package com.traneco.institution.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.traneco.common.CardStatusCode;
import com.traneco.common.KeyValueBean;
import com.traneco.common.SessionDTO;
import com.traneco.common.constants.QueryConstant;
import com.traneco.common.logger.model.UserLoggingModel;
import com.traneco.common.logging.services.LoggingService;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.institution.model.InstitutionBean;

@Repository
public class InstitutionDaoImpl implements InstitutionDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;

	@Autowired
	Environment env;

	@Autowired
	SessionDTO sessionDTO;

	@Autowired
	LoggerUtil log;
	
	@Autowired
	LoggingService userLoggingService;

	private String className = this.getClass().getSimpleName();
	String respCode = null;

	@Override
	public List<KeyValueBean> getInstitutionList() 
	{
		List<KeyValueBean> instList = new ArrayList<KeyValueBean>();
		instList = jdbcTemplate.query(QueryConstant.LOAD_INSTITUTION_MASTER,
				new BeanPropertyRowMapper<KeyValueBean>(KeyValueBean.class));
		return instList;
	}

	@Override
	public String addInstitution(String instCode, String instDesc) {
		String methodName = "addInstitution";
		UserLoggingModel userLoggingModel = null;
		
		log.doLog(2, className, methodName,
				"RequestParam : InstitutionCode :" + instCode + " InstitutionDesc :" + instDesc);
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(QueryConstant.SP_ADD_INSTITUTION);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_InstitutionCode", instCode);
		inParamMap.put("P_InstitutionDesc", instDesc);
		inParamMap.put("P_UserPass", env.getProperty("imps.superadmin.password"));
		inParamMap.put("P_CreatedBy", sessionDTO.getUserID());
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		log.doLog(2, className, methodName, "StoreProcedure Calling : " + QueryConstant.SP_ADD_INSTITUTION);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		simpleJdbcCallResult.forEach((k, v) -> {
			if ("p_response_code".equalsIgnoreCase(k))
				respCode = String.valueOf(v);
		});
		log.doLog(2, className, methodName, "ResponseCode StoreProcedure " + respCode);
		return respCode;
	}

	@Override
	public List<InstitutionBean> getViewInstitutionList() {
		List<InstitutionBean> viewInstList = new ArrayList<InstitutionBean>();
		viewInstList = jdbcTemplate.query(QueryConstant.LOAD_VIEW_INSTITUTION_MASTER,
				new Object[] { sessionDTO.getParticipantid() },
				new BeanPropertyRowMapper<InstitutionBean>(InstitutionBean.class));
		return viewInstList;
	}

	@Override
	public InstitutionBean editInstDetailsByID(String instId) {
		InstitutionBean institutionBean = null;
		institutionBean = (InstitutionBean) jdbcTemplate.queryForObject(QueryConstant.EDIT_INSTITUTION_BY_ID,
				new Object[] { instId }, new BeanPropertyRowMapper<InstitutionBean>(InstitutionBean.class));
		return institutionBean;
	}

	@Override
	public String editInst(InstitutionBean institutionBean) 
	{
		String methodName = "editInst";
		log.doLog(2, className, methodName,
				"RequestParam : InstitutionCode :" + institutionBean.getInstitutionCode() + " InstitutionID :"
						+ institutionBean.getInstitutionId() + " OutwardDayAmtLimit : "
						+ institutionBean.getOutwardDayAmtLimit() + " OutwardMonthAmtLimit : "
						+ institutionBean.getOutwardMonthAmtLimit() + " OutwardMinAmtTran : "
						+ institutionBean.getOutwardMinAmtTran() + " OutwardMaxAmtTran : "
						+ institutionBean.getOutwardMaxAmtTran() + " OutwardDayMaxTranCount : "
						+ institutionBean.getOutwardDayMaxTranCount() + " OutwardMonthMaxTranCount : "
						+ institutionBean.getOutwardMonthMaxTranCount());
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(QueryConstant.SP_EDIT_INSTITUTION);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_Action", CardStatusCode.REQTYPE_EDIT);
		inParamMap.put("P_InstitutionId", institutionBean.getInstitutionId());
		inParamMap.put("P_InstitutionDesc", institutionBean.getInstitutionDesc());
		inParamMap.put("P_InstitutionTempId", null);
		inParamMap.put("P_OutwardDayAmtLimit", institutionBean.getOutwardDayAmtLimit());
		inParamMap.put("P_OutwardMonthAmtLimit", institutionBean.getOutwardMonthAmtLimit());
		inParamMap.put("P_OutwardDayAmtLimit", institutionBean.getOutwardDayAmtLimit());
		inParamMap.put("P_OutwardMinAmtTran", institutionBean.getOutwardMinAmtTran());
		inParamMap.put("P_OutwardMaxAmtTran", institutionBean.getOutwardMaxAmtTran());
		inParamMap.put("P_OutwardDayMaxTranCount", institutionBean.getOutwardDayMaxTranCount());
		inParamMap.put("P_OutwardMonthMaxTranCount", institutionBean.getOutwardMonthMaxTranCount());
		inParamMap.put("P_CreatedBy", sessionDTO.getUserID());
		inParamMap.put("P_ApprovedBy", null);
		inParamMap.put("P_Remark", null);
		inParamMap.put("P_ApprovalStatusID", null);

		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		log.doLog(2, className, methodName, "StoreProcedure Calling : " + QueryConstant.SP_EDIT_INSTITUTION);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		simpleJdbcCallResult.forEach((k, v) -> {
			if ("p_response_code".equalsIgnoreCase(k))
				respCode = String.valueOf(v);
		});
		log.doLog(2, className, methodName, "ResponseCode StoreProcedure " + respCode);
		return respCode;
	}

	@Override
	public List<InstitutionBean> getApprovalInstitutionPendingList(String userID) {
		List<InstitutionBean> approveInstList = new ArrayList<InstitutionBean>();
		approveInstList = jdbcTemplate.query(QueryConstant.LOAD_APPROVAL_PENDING_LIST_INSTITUTION,
				new Object[] { userID, sessionDTO.getParticipantid() },
				new BeanPropertyRowMapper<InstitutionBean>(InstitutionBean.class));
		return approveInstList;
	}

	@Override
	public String approvInstitution(String requestID, String approveStatus, String remark) {
		String methodName = "approvInstitution";
		String[] data = requestID.split("\\|");

		log.doLog(2, className, methodName,
				"RequestParam :  InstitutionID :" + data[0] + " OutwardDayAmtLimit : " + data[2]
						+ " OutwardMonthAmtLimit : " + data[3] + " OutwardMinAmtTran : " + data[4]
						+ " OutwardMaxAmtTran : " + data[5] + " OutwardDayMaxTranCount : " + data[6]
						+ " OutwardMonthMaxTranCount : " + data[7]);
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(QueryConstant.SP_EDIT_INSTITUTION);
		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("P_Action", CardStatusCode.REQTYPE_APPROVE);
		inParamMap.put("P_InstitutionId", data[0]);
		inParamMap.put("P_InstitutionDesc", null);
		inParamMap.put("P_InstitutionTempId", data[1]);
		inParamMap.put("P_OutwardDayAmtLimit", data[2]);
		inParamMap.put("P_OutwardMonthAmtLimit", data[3]);
		inParamMap.put("P_OutwardMinAmtTran", data[4]);
		inParamMap.put("P_OutwardMaxAmtTran", data[5]);
		inParamMap.put("P_OutwardDayMaxTranCount", data[6]);
		inParamMap.put("P_OutwardMonthMaxTranCount", data[7]);
		inParamMap.put("P_CreatedBy", sessionDTO.getUserID());
		inParamMap.put("P_ApprovedBy", sessionDTO.getUserID());
		inParamMap.put("P_Remark", remark);
		inParamMap.put("P_ApprovalStatusID", approveStatus);

		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		log.doLog(2, className, methodName, "StoreProcedure Calling : " + QueryConstant.SP_APPROVE_INSTITUTION);
		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		simpleJdbcCallResult.forEach((k, v) -> {
			if ("p_response_code".equalsIgnoreCase(k))
				respCode = String.valueOf(v);
		});
		log.doLog(2, className, methodName, "ResponseCode StoreProcedure " + respCode);
		return respCode;
	}

	@Override
	public int getApprovalPendingStatus(String instID) {
		int count = (int) jdbcTemplate.queryForObject(QueryConstant.CHECK_APPROVAL_PENDING_STATUS,
				new Object[] { instID }, Integer.class);
		return count;
	}

}
