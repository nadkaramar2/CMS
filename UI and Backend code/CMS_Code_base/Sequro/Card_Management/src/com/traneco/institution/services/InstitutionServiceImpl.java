/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to define institution service business logic.
 * 
 *@History
 *===============================================================================================================================================
 *     Version          Date            Author                  Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to define institution service business logic.
 *===============================================================================================================================================
 *
 */

package com.traneco.institution.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traneco.common.KeyValueBean;
import com.traneco.institution.dao.InstitutionDao;
import com.traneco.institution.model.InstitutionBean;

@Service
public class InstitutionServiceImpl implements InstitutionService {

	@Autowired
	InstitutionDao institutionDao;

	@Override
	public List<KeyValueBean> getInstitutionList() {
		return institutionDao.getInstitutionList();
	}

	@Override
	public String addInstitution(String instCode, String instDesc) {
		return institutionDao.addInstitution(instCode, instDesc);

	}

	@Override
	public List<InstitutionBean> getViewInstitutionList() {
		return institutionDao.getViewInstitutionList();
	}

	@Override
	public InstitutionBean editInstDetailsByID(String instId) {
		return institutionDao.editInstDetailsByID(instId);
	}

	@Override
	public String editInst(InstitutionBean institutionBean) {
		return institutionDao.editInst(institutionBean);
	}

	@Override
	public List<InstitutionBean> getApprovalInstitutionPendingList(String userID) {
		return institutionDao.getApprovalInstitutionPendingList(userID);
	}

	@Override
	public String approvInstitution(String requestID, String approveStatus, String remark) {
		return institutionDao.approvInstitution(requestID, approveStatus, remark);
	}

	@Override
	public int getApprovalPendingStatus(String instID) {
		return institutionDao.getApprovalPendingStatus(instID);
	}
}
