package com.traneco.cmsaudit.dao;

import com.traneco.cmsaudit.model.CmsAudit;

public interface CmsAuditDao 
{
	public int addAudit(CmsAudit cmsAudit);
	
	public int addAuditEntry(CmsAudit cmsAudit);
	
}
