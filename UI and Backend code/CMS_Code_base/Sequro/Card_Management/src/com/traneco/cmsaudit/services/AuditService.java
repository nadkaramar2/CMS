package com.traneco.cmsaudit.services;

import com.traneco.cmsaudit.model.CmsAudit;

public interface AuditService 
{
	public int addAudit(String participantId, String tableName, String columnName, String newFeild, String oldFeild,String actionName, String createdBy);

	public int addAuditEntry(CmsAudit cmsAudit);
	
	
}
