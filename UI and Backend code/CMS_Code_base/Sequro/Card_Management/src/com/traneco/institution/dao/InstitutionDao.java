/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to define institution method.
 * 
 *@History
 *===============================================================================================================================================
 *     @Version         @Date         	@Author                 @Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to define institution method.
 *===============================================================================================================================================
 *
 */

package com.traneco.institution.dao;

import java.util.List;

import com.traneco.common.KeyValueBean;
import com.traneco.institution.model.InstitutionBean;


public interface InstitutionDao {
	public List<KeyValueBean>  getInstitutionList();
	public String addInstitution(String instCode,String instDesc);
	public List<InstitutionBean> getViewInstitutionList();
	public InstitutionBean editInstDetailsByID(String instId);
	public String editInst(InstitutionBean institutionBean);
	public List<InstitutionBean> getApprovalInstitutionPendingList(String userID);
	public String approvInstitution(String requestID,String approveStatus, String remark);
	public int getApprovalPendingStatus(String instID);
	
}
