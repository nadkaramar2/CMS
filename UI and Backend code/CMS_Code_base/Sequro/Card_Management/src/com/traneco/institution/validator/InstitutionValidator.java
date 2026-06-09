/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to validate all institution request.
 * 
 *@History
 *===============================================================================================================================================
 *     Version          Date            Author                  Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to validate all institution request.
 *===============================================================================================================================================
 *
 */

package com.traneco.institution.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.traneco.common.util.Utils;
import com.traneco.institution.model.InstitutionBean;

@Component
public class InstitutionValidator implements Validator {

	@Autowired
	Environment env;

	@Override
	public boolean supports(Class<?> classz) { 
		return InstitutionBean.class.isAssignableFrom(classz);
	}

	@Override
	public void validate(Object obj, Errors err) {


		InstitutionBean instBean = (InstitutionBean) obj;
		if("AddInst".equals(instBean.getType())) {

			ValidationUtils.rejectIfEmptyOrWhitespace(err, "institutionCode", "error.institutionCode", env.getProperty("imps.error.instAddForm.instCode"));
			ValidationUtils.rejectIfEmptyOrWhitespace(err, "institutionDesc", "error.institutionDesc", env.getProperty("imps.instEditForm.institutionDesc"));

			if (!Utils.isAlphaNumeric(Utils.trim(instBean.getInstitutionCode())))
				err.rejectValue("institutionCode", "imps.Valid.instAddForm.instCode");
			else if(Utils.trim(instBean.getInstitutionCode()).length() < Integer.parseInt(env.getProperty("imps.instAddForm.instCode.min.size")))
				err.rejectValue("institutionCode", "imps.error.instAddForm.instCode.min.size");
			else if(Utils.trim(instBean.getInstitutionCode()).length() > Integer.parseInt(env.getProperty("imps.instAddForm.instCode.max.size")))
				err.rejectValue("institutionCode", "imps.error.instAddForm.instCode.max.size");
			
			if(!Utils.isAlphaNumeric(Utils.trim(instBean.getInstitutionDesc())))
				err.rejectValue("institutionDesc", "imps.Valid.instEditForm.institutionDesc");
			else if(Utils.trim(instBean.getInstitutionDesc()).length() < Integer.parseInt(env.getProperty("imps.instAddForm.instDesc.min.size")))
				err.rejectValue("institutionDesc", "imps.error.instAddForm.instDesc.min.size");
			else if(Utils.trim(instBean.getInstitutionDesc()).length() > Integer.parseInt(env.getProperty("imps.instAddForm.instDesc.max.size")))
				err.rejectValue("institutionDesc", "imps.error.instAddForm.instDesc.max.size");

		}

		if("EditInst".equals(instBean.getType())) {

			if(Utils.isEmptyStr(instBean.getInstitutionDesc()))
				err.rejectValue("institutionDesc", "imps.instEditForm.institutionDesc");
			else if(!Utils.isAlphaNumeric(instBean.getInstitutionDesc()))
				err.rejectValue("institutionDesc", "imps.Valid.instEditForm.institutionDesc");

			if(Utils.isEmptyStr(instBean.getOutwardDayAmtLimit()))
				err.rejectValue("outwardDayAmtLimit", "imps.instEditForm.outwardDayAmtLimit");
			else if(!Utils.isNumber(instBean.getOutwardDayAmtLimit()))
				err.rejectValue("outwardDayAmtLimit", "imps.Valid.instEditForm.outwardDayAmtLimit");

			if(Utils.isEmptyStr(instBean.getOutwardMonthAmtLimit()))
				err.rejectValue("outwardMonthAmtLimit", "imps.instEditForm.outwardMonthAmtLimit");
			else if(!Utils.isNumber(instBean.getOutwardMonthAmtLimit()))
				err.rejectValue("outwardMonthAmtLimit", "imps.Valid.instEditForm.outwardMonthAmtLimit");

			if(Utils.isEmptyStr(instBean.getOutwardMinAmtTran()))
				err.rejectValue("outwardMinAmtTran", "imps.instEditForm.outwardMinAmtTran");
			else if(!Utils.isNumber(instBean.getOutwardMinAmtTran()))
				err.rejectValue("outwardMinAmtTran", "imps.Valid.instEditForm.outwardMinAmtTran");

			if(Utils.isEmptyStr(instBean.getOutwardMaxAmtTran()))
				err.rejectValue("outwardMaxAmtTran", "imps.instEditForm.outwardMaxAmtTran");
			else if(!Utils.isNumber(instBean.getOutwardMaxAmtTran()))
				err.rejectValue("outwardMaxAmtTran", "imps.Valid.instEditForm.outwardMaxAmtTran");

			if(Utils.isEmptyStr(instBean.getOutwardDayMaxTranCount()))
				err.rejectValue("outwardDayMaxTranCount", "imps.instEditForm.outwardDayMaxTranCount");
			else if(!Utils.isNumber(instBean.getOutwardDayMaxTranCount()))
				err.rejectValue("outwardDayMaxTranCount", "imps.Valid.instEditForm.outwardDayMaxTranCount");

			if(Utils.isEmptyStr(instBean.getOutwardMonthMaxTranCount()))
				err.rejectValue("outwardMonthMaxTranCount", "imps.instEditForm.outwardMonthMaxTranCount");
			else if(!Utils.isNumber(instBean.getOutwardMonthMaxTranCount()))
				err.rejectValue("outwardMonthMaxTranCount", "imps.Valid.instEditForm.outwardMonthMaxTranCount");

		}

		if("ApproveInst".equals(instBean.getType())) {
			if(instBean.getApproveID().length <= 0)
				err.rejectValue("approveID", "imps.Valid.instApproveForm.isEmptyList");
		}
	}
}
