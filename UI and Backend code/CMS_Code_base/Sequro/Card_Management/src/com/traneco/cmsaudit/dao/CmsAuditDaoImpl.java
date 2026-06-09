package com.traneco.cmsaudit.dao;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.traneco.cmsaudit.model.CmsAudit;
import com.traneco.common.SessionDTO;

@Repository
public class CmsAuditDaoImpl implements CmsAuditDao
{
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	JdbcTemplate jdbcCMSTemplate;
	
	@Autowired
	SessionDTO sessionDTO;
	
	@Override
	public int addAudit(CmsAudit cmsAudit) 
	{
		int count = this.jdbcTemplate.update(
				"INSERT INTO cms_audit (participant_id,table_name,column_name,new_field,old_field,dml_action,created_date,created_by,approved_by) VALUES (?,?,?,?,?,?,?,?,?)",
				new Object[] { cmsAudit.getParticipantId(),cmsAudit.getTableName(), cmsAudit.getColumnName(), cmsAudit.getNewFeild(), cmsAudit.getOldFeild(), cmsAudit.getDmlAction(),new Timestamp(System.currentTimeMillis()), cmsAudit.getCreatedBy(),null});
		return count;
		
	}
	
	
	@Override
	public int addAuditEntry(CmsAudit cmsAudit) 
	{
		int count = this.jdbcTemplate.update(
				"INSERT INTO cms_audit (participant_id,table_name,column_name,new_field,old_field,dml_action,created_date,created_by,approved_by) VALUES (?,?,?,?,?,?,?,?,?)",
				new Object[] { this.sessionDTO.getParticipantid(),cmsAudit.getTableName(), cmsAudit.getColumnName(), cmsAudit.getNewFeild(), cmsAudit.getOldFeild(), cmsAudit.getDmlAction(),new Timestamp(System.currentTimeMillis()), this.sessionDTO.getLoginID(),null});
		return count;
		
	}

}
