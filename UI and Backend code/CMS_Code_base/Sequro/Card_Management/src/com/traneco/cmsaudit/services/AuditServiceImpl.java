package com.traneco.cmsaudit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traneco.cmsaudit.dao.CmsAuditDao;
import com.traneco.cmsaudit.model.CmsAudit;

@Service
public class AuditServiceImpl implements AuditService
{
	@Autowired
	private CmsAuditDao cmsAuditDao;
	
	
	@Override
	public int addAudit(String participantId, String tableName, String columnName, String newFeild, String oldFeild, String actionName, String createdBy) 
	{
		try 
		{
			CmsAudit cmsAudit = new CmsAudit();
			cmsAudit.setParticipantId(participantId);
			cmsAudit.setTableName(tableName);
			cmsAudit.setColumnName(columnName);
			cmsAudit.setNewFeild(newFeild);
			cmsAudit.setOldFeild(oldFeild);
			cmsAudit.setDmlAction(actionName);
			cmsAudit.setCreatedBy(createdBy);
			return cmsAuditDao.addAudit(cmsAudit);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int addAuditEntry(CmsAudit cmsAudit) 
	{
		return cmsAuditDao.addAuditEntry(cmsAudit);
	}

}
