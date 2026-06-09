
package com.traneco.role.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.traneco.common.SessionDTO;
import com.traneco.common.constants.QueryConstant;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.role.constant.RoleQueryConstant;
import com.traneco.role.model.Cfg_Role;
import com.traneco.role.model.Cfg_Role_Temp;
import com.traneco.role.model.GroupBean;
import com.traneco.role.model.MenuBean;
import com.traneco.role.model.ParentMenuBean;
import com.traneco.role.model.ParentSubMenuBean;
import com.traneco.role.model.RoleBean;
import com.traneco.role.model.RoleMenuMappingTemp;
import com.traneco.role.model.UserRoleMappingTemp;
import com.traneco.role.services.RoleService;

@Repository
public class RoleDaoImpl implements RoleDao 
{
	private String className = this.getClass().getSimpleName();
	@Autowired
	@Qualifier("jdbcTemplate")
	JdbcTemplate jdbcTemplate;
	SimpleJdbcCall simpleJdbcCall;
	@Autowired
	SessionDTO sessionDTO;
	@Autowired
	LoggerUtil log;
	Map<String, Object> outParamMap;
	Map<String, Object> inParamMap;
	SqlParameterSource in;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	JdbcTemplate jdbcCMSTemplate;
	
	
	@Override
	public void addRole(RoleBean roleBean) throws Exception 
	{
		inParamMap = new HashMap<String, Object>();

		String methodName = "addRole";

		inParamMap.put("P_Action", roleBean.getStrAction());
		inParamMap.put("p_participantid", sessionDTO.getParticipantid());
		inParamMap.put("P_RoleTempId", null);
		inParamMap.put("P_RoleId", null);
		inParamMap.put("P_RoleName", roleBean.getStrRoleName());
		inParamMap.put("P_Description", roleBean.getStrDescription());
		inParamMap.put("P_MenuIds", roleBean.getStrMenuIds());
		inParamMap.put("P_Remark", null);
		inParamMap.put("P_CreatedBy", sessionDTO.getUserID());
		inParamMap.put("P_ApprovedBy", null);
		inParamMap.put("P_ApprovalStatusID", null);
		inParamMap.put("P_GroupID", roleBean.getStrGroupID());
		outParamMap = callSP(inParamMap, methodName);
		roleBean.setStrResultCode(outParamMap.get("p_responsecode").toString());
		Utils.closeResources(inParamMap, outParamMap);
	}
	
	
	/* For Changing role management through Code
	public void addRole(RoleBean roleBean) throws Exception
	{
	   try 
		{
		if (roleBean.getStrAction().toUpperCase().equalsIgnoreCase(TranecoStatusCode.REQTYPE_ADD))
		{
			UserRoleMappingTemp userRoleMappingTemp = new UserRoleMappingTemp();
			userRoleMappingTemp = roleService.getUserRoleMappingTeampRoleId();
			roleBean.setIRoleID(userRoleMappingTemp.getRoleId());
			
			boolean isRoleExist = roleService.checkRoleExist(roleBean);
			if (!isRoleExist)
			{
			int count = roleService.addCfgRoleTemp(roleBean);
			if (count > 0)
			{
				RoleMenuMappingTemp roleMenuMappingTemp = new RoleMenuMappingTemp();
				roleMenuMappingTemp = roleService.getRoleMenuMappingTeamp(roleMenuMappingTemp);
				roleBean.setRoleTempId(roleMenuMappingTemp.getRoleTempId() + 1);
				
				int tempRoleMenuMappingId = roleMenuMappingTemp.getRoleMenuMappingId() + 1;
				String[] menuIds = roleBean.getStrMenuIds().split(",");
				if (menuIds.length > 0)
				{
				List<RoleMenuMappingTemp> menuIdList = new ArrayList<RoleMenuMappingTemp>();
				for(String menuList : menuIds)
				{
					String[] menuIdsArray = menuList.split(",");
					for (int i = 0; i < menuIdsArray.length ; i++) 
				  {
					RoleMenuMappingTemp roleMenuMappingTemp2 = new RoleMenuMappingTemp();
					roleMenuMappingTemp2.setRoleTempId(roleBean.getRoleTempId() + i);
					roleMenuMappingTemp2.setRoleMenuMappingId(tempRoleMenuMappingId);
					tempRoleMenuMappingId++;
					roleMenuMappingTemp2.setParticipantId(6);
					roleMenuMappingTemp2.setRoleId(roleBean.getIRoleID());
					roleMenuMappingTemp2.setMenuId(menuIdsArray[i].trim());
					roleMenuMappingTemp2.setStatus(1);
					roleMenuMappingTemp2.setCreatedBy(sessionDTO.getUserID());
					roleMenuMappingTemp2.setGroupId(roleBean.getStrGroupID());
					menuIdList.add(roleMenuMappingTemp2);
				}
			  }
				if (menuIdList.size() > 0)
				{
					int[] insertBatch = roleService.batchRoleMenuMappingTemp(menuIdList);
					if (insertBatch.length > 0)
					{
						roleBean.setStrResultCode("00");
					}
				}
				else 
				{
					roleBean.setStrResultCode("02");
				}
			}
			else 
			{
				roleBean.setStrResultCode("02");
				roleBean.setStrResultMessage("Please Select Menu's !");
			}
			}
			else 
			{
				roleBean.setStrResultCode("02");
				roleBean.setStrResultMessage("Failed to Insert Role !");
			}
		  }
			else 
			{
				roleBean.setStrResultCode("02");
				roleBean.setStrResultMessage("Role Already Exist !");
			}
			
		}
	  }
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	*/
	
	
	@Override
	public void approveRole(RoleBean roleBean) throws Exception 
	{
		inParamMap = new HashMap<String, Object>();

		String methodName = "approveRole";

		inParamMap.put("P_Action", roleBean.getStrAction());
		inParamMap.put("p_participantid", sessionDTO.getParticipantid());
		inParamMap.put("P_RoleTempId", roleBean.getStrRoleTempID());
		inParamMap.put("P_RoleId", roleBean.getIRoleID());
		inParamMap.put("P_RoleName", null);
		inParamMap.put("P_Description", null);
		inParamMap.put("P_MenuIds", null);
		inParamMap.put("P_Remark", roleBean.getStrRemark());
		inParamMap.put("P_CreatedBy", sessionDTO.getUserID());
		inParamMap.put("P_ApprovedBy", sessionDTO.getUserID());
		inParamMap.put("P_ApprovalStatusID", roleBean.getIApprovalStatusID());
		inParamMap.put("P_GroupID", roleBean.getStrGroupID());
		outParamMap = callSP(inParamMap, methodName);
		roleBean.setStrResultCode(outParamMap.get("p_responsecode").toString());
		Utils.closeResources(inParamMap, outParamMap);
	}
	
	
	
	/* For Approving Role through Code
	@Override
	public void approveRole(RoleBean roleBean) throws Exception 
	{
		try 
		{
			if (roleBean.getStrAction().equalsIgnoreCase(TranecoStatusCode.REQTYPE_APPROVE))
			{
				Cfg_Role cfgRole = new Cfg_Role();
				cfgRole.setRoleId(roleBean.getIRoleID());
				cfgRole.setId(Integer.parseInt(roleBean.getStrRoleTempID()));
				cfgRole.setParticipantId(roleBean.getIParticipantId());
				int count =  roleService.getRoleExistCount(cfgRole);
				if (count == 0)
				{
					Cfg_Role_Temp cfgRoleTemp = new Cfg_Role_Temp();
					
					cfgRoleTemp.setId(cfgRole.getId());
					cfgRoleTemp.setRoleId(cfgRole.getRoleId());
					cfgRoleTemp.setParticipantId(cfgRole.getParticipantId());
					cfgRoleTemp = roleService.getCfgRoleTempForLastInsertId(cfgRoleTemp);
					if (cfgRoleTemp != null && ObjectUtils.defaultIfNull(cfgRoleTemp.getId(), 0) != 0)
					{
						int cfgRoleInsert =  roleService.addCfgRole(cfgRoleTemp);
						if (cfgRoleInsert > 0)
						{
							  RoleMenuMappingTemp roleMenuMappingTemps = new RoleMenuMappingTemp();
							  roleMenuMappingTemps.setId(cfgRoleTemp.getId());
							  roleMenuMappingTemps.setParticipantId(cfgRoleTemp.getParticipantId());
							  roleMenuMappingTemps.setGroupId(String.valueOf(cfgRoleTemp.getGroupId()));
							List<RoleMenuMappingTemp> roleMenuMappingTemp = roleService.getRoleMenuMappingTempForId(roleMenuMappingTemps);
							if (roleMenuMappingTemp != null && roleMenuMappingTemp.size() > 0)
							{
								List<RoleMenuMapping> roleMenuMappingList = new ArrayList<>();
								roleMenuMappingTemp.forEach(e ->
								{
									RoleMenuMapping roleMenuMapping = new RoleMenuMapping();
									roleMenuMapping.setId(e.getId());
									roleMenuMapping.setParticipantId(e.getParticipantId());
									roleMenuMapping.setRoleId(e.getRoleId());
									roleMenuMapping.setMenuId(e.getMenuId());
									roleMenuMapping.setCreatedBy(e.getCreatedBy());
									roleMenuMapping.setLastModifiedBy(e.getLastModifiedBy());
									roleMenuMapping.setGroupId(e.getGroupId());
									
									roleMenuMappingList.add(roleMenuMapping);
								});
								
								if (roleMenuMappingList != null && roleMenuMappingList.size() > 0)
								{
									int [] result = roleService.batchRoleMenuMapping(roleMenuMappingTemp);
									if (result.length > 0)
									{
										ProcessResponse response = new ProcessResponse();
										response.setCode("S0000");
										response.setStatus("Success");
										response.setMessage("Batch Entry Updated Successfully");
										roleBean.setStrResultCode("00");
									}
								}
							}
						}		
					}
				}
				else 
				{
					Cfg_Role_Temp cfgRoleTemp = new Cfg_Role_Temp();
					cfgRoleTemp.setRoleId(cfgRole.getId());
					cfgRoleTemp.setParticipantId(cfgRole.getParticipantId());
					cfgRoleTemp = roleService.getCfgRoleForLastInsertId(cfgRoleTemp);
					if (cfgRoleTemp != null)
					{
						int cfgRoleUpdate = roleService.updateCfg_Role(cfgRoleTemp);
						if (cfgRoleUpdate > 0)
						{
							int deletermm =  roleService.deleteRoleMenuMapping(cfgRoleTemp);
							if (deletermm > 0)
							{
								RoleMenuMappingTemp roleMenuMappingTemp = new RoleMenuMappingTemp();
								List<RoleMenuMappingTemp> List = roleService.getRoleMenuMappingTempForId(roleMenuMappingTemp);
								if (List != null && List.size() > 0)
								{
									List<RoleMenuMapping> roleMenuMappingList = new ArrayList<>();
									List.forEach(e ->
									{
										RoleMenuMapping roleMenuMapping = new RoleMenuMapping();
										roleMenuMapping.setId(e.getId());
										roleMenuMapping.setParticipantId(e.getParticipantId());
										roleMenuMapping.setRoleId(e.getRoleId());
										roleMenuMapping.setMenuId(e.getMenuId());
										roleMenuMapping.setCreatedBy(e.getCreatedBy());
										roleMenuMapping.setLastModifiedBy(e.getLastModifiedBy());
										roleMenuMapping.setGroupId(e.getGroupId());
										
										roleMenuMappingList.add(roleMenuMapping);
									});
									
									if (roleMenuMappingList != null && roleMenuMappingList.size() > 0)
									{
										
									}
								}
							}
						}
					}
					
				
				}
			}
			else 
			{
				//Reject Case
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	*/
	
	
	private Map<String, Object> callSP(Map<String, Object> inParamMap, String methodName) throws Exception 
	{
		outParamMap = new HashMap<String, Object>();
		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName(RoleQueryConstant.ROLE_ACTION_SP);
		in = new MapSqlParameterSource(inParamMap);
		log.doLog(4, className, methodName, "Calling SP " + RoleQueryConstant.ROLE_ACTION_SP + " Passing parameters = " + inParamMap);
		outParamMap = simpleJdbcCall.execute(in);
		Utils.closeResources(inParamMap, in);
		return outParamMap;
	}

	@Override
	public void editRole(RoleBean roleBean) throws Exception 
	{
		inParamMap = new HashMap<String, Object>();

		String methodName = "editRole";

		inParamMap.put("P_Action", roleBean.getStrAction());
		inParamMap.put("p_participantid", sessionDTO.getParticipantid());
		inParamMap.put("P_RoleTempId", null);
		inParamMap.put("P_RoleId", roleBean.getIRoleID());
		inParamMap.put("P_RoleName", roleBean.getStrRoleName());
		inParamMap.put("P_Description", roleBean.getStrDescription());
		inParamMap.put("P_MenuIds", Utils.convertToStingBuff(roleBean.getStrMenuIdsArr()));
		inParamMap.put("P_Remark", null);
		inParamMap.put("P_CreatedBy", sessionDTO.getUserID());
		inParamMap.put("P_ApprovedBy", null);
		inParamMap.put("P_ApprovalStatusID", null);
		inParamMap.put("P_GroupID", roleBean.getStrGroupID());
		outParamMap = callSP(inParamMap, methodName);
		roleBean.setStrResultCode(outParamMap.get("P_Responsecode").toString());
		Utils.closeResources(inParamMap, outParamMap);
	}

	@Override
	public List<MenuBean> loadAllMenu() throws Exception 
	{
		log.doLog(4, className, "loadAllMenu", "Executing Query : " + RoleQueryConstant.LOAD_ALL_MENU);

		return jdbcTemplate.query(RoleQueryConstant.LOAD_ALL_MENU, new Object[] {},
				new BeanPropertyRowMapper<MenuBean>(MenuBean.class));
	}

	@Override
	public List<ParentMenuBean> loadAllParentMenu() throws Exception 
	{
		return jdbcTemplate.query(RoleQueryConstant.LOAD_ALL_PARENT_MENU, new Object[] {},
				new BeanPropertyRowMapper<ParentMenuBean>(ParentMenuBean.class));
	}

	@Override
	public List<ParentSubMenuBean> loadAllParentSubMenu() throws Exception {
		return jdbcTemplate.query(RoleQueryConstant.LOAD_ALL_PARENT_SUBMENU_LIST, new Object[] {},
				new BeanPropertyRowMapper<ParentSubMenuBean>(ParentSubMenuBean.class));
	}

	@Override
	public List<RoleBean> searchRoleToEdit() {
		log.doLog(4, className, "searchRoleToEdit", "Executing Query : " + RoleQueryConstant.SEARCH_ROLES_LIST
				+ " Passing Parameters : " + sessionDTO.getParticipantid());

		return jdbcTemplate.query(RoleQueryConstant.SEARCH_ROLES_LIST,
				new Object[] { sessionDTO.getParticipantid(), sessionDTO.getParticipantid() },
				new BeanPropertyRowMapper<RoleBean>(RoleBean.class));
	}

	@Override
	public List<RoleBean> getPendingRoleForApproval() {
		log.doLog(4, className, "getPendingRoleForApproval",
				"Executing Query : " + RoleQueryConstant.LOAD_PENDING_ROLE_FOR_APPROVAL + " Passing Parameters : "
						+ sessionDTO.getParticipantid() + "," + sessionDTO.getUserID());
				//+ sessionDTO.getParticipantid() + "," + sessionDTO.getLoginID());

		return jdbcTemplate.query(RoleQueryConstant.LOAD_PENDING_ROLE_FOR_APPROVAL,
				new Object[] { sessionDTO.getUserID(), sessionDTO.getParticipantid() },
				//new Object[] { sessionDTO.getLoginID(), sessionDTO.getParticipantid() },
				new BeanPropertyRowMapper<RoleBean>(RoleBean.class));
	}

	@Override
	public List<RoleBean> getRoleForEdit(RoleBean roleBean) 
	{
		log.doLog(4, className, "getRoleForEdit", "Executing Query : " + RoleQueryConstant.LOAD_ROLE_FOR_EDIT
				+ " Passing Parameters : " + roleBean.getIRoleID() + "," + sessionDTO.getParticipantid());

		return jdbcTemplate.query(RoleQueryConstant.LOAD_ROLE_FOR_EDIT,
				new Object[] { roleBean.getIRoleID(), sessionDTO.getParticipantid() },
				new BeanPropertyRowMapper<RoleBean>(RoleBean.class));
	}

	@Override
	public String getRolePendingStatus(RoleBean roleBean) throws Exception 
	{
		String rolename = jdbcTemplate.query(RoleQueryConstant.CHECK_ROLE_FOR_APPROVAL_PENDING,
				new Object[] { roleBean.getIRoleID(), sessionDTO.getParticipantid() },
				new ResultSetExtractor<String>() 
		{
					@Override
					public String extractData(ResultSet arg0) throws SQLException, DataAccessException {
						return arg0.next() ? arg0.getString("roleName") : null;
					}
				});

		return rolename;
	}

	@Override
	public int insertGroup(GroupBean bean) 
	{
		int count = jdbcTemplate.queryForObject(QueryConstant.SELECT_GROUP, new Object[] { bean.getStrName() },
				Integer.class);
		if (count <= 0) 
		{
			return jdbcTemplate.update(QueryConstant.INSERT_GROUP, new Object[] { bean.getStrName(),
					bean.getStrDescription(), bean.getStrCreatedBy(), bean.getStrParticipantID() });
		}
		else 
		{
			return 0;
		}
	}

	@Override
	public int InsertAuditEntry(RoleBean roleBean, String tableName) 
	{
		try 
		{
			int count = this.jdbcTemplate.update("INSERT INTO cms_audit (participant_id,table_name,column_name,new_field,old_field,dml_action,created_date,created_by,approved_by) VALUES (?,?,?,?,?,?,?,?,?)",
					new Object[] { sessionDTO.getParticipantid(),tableName, roleBean.getStrRoleName(), null, null, roleBean.getStrAction(),new Timestamp(System.currentTimeMillis()), sessionDTO.getLoginID(),null});
			return count;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
		
	}


	@Override
	public UserRoleMappingTemp getUserRoleMappingTeampRoleId() 
	{
		try 
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT id AS id, user_role_id AS userRoleId, user_id AS userId, role_id AS roleId, participant_id AS participantId, user_status AS userStatus, created_by AS createdBy, created_date AS createdDate, ");
			sqlQuery.append("last_modified_by AS lastModifiedBy, last_modified_date AS lastModifiedDate, approval_status_id AS approvalStatusId, group_id AS groupId ");
			sqlQuery.append("FROM user_role_mapping_temp ORDER BY role_id DESC LIMIT 1 ");
			List<UserRoleMappingTemp> userRoleMappingTemp = this.jdbcTemplate.query(sqlQuery.toString(), new Object[] {}, (RowMapper) new BeanPropertyRowMapper(UserRoleMappingTemp.class));
			
			if (userRoleMappingTemp !=null && userRoleMappingTemp.size() > 0) 
			{
				return userRoleMappingTemp.get(0);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int addCfgRoleTemp(RoleBean roleBean) 
	{
		try 
		{
			int count = this.jdbcTemplate.update("INSERT INTO cfg_role_temp (Role_ID,participant_id,Role_Name,role_Description,Created_By,Remarks,approval_status_id,group_id) VALUES (?,?,?,?,?,?,?,?)",
					new Object[] { roleBean.getIRoleID(),sessionDTO.getParticipantid(),roleBean.getStrRoleName(), roleBean.getStrDescription(), sessionDTO.getUserID(), null, 1,roleBean.getStrGroupID()});
			return count;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public RoleMenuMappingTemp getRoleMenuMappingTeamp(RoleMenuMappingTemp roleMenuMappingTemp) 
	{
		try 
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT id AS id,role_temp_id AS roleTempId,role_menu_mapping_id AS roleMenuMappingId,participant_id AS participantId,role_id AS roleId, ");
			sqlQuery.append("menu_id AS menuId,created_by AS createdBy,approval_status_id AS approvalStatusId,group_id AS groupId ");
			sqlQuery.append("From role_menu_mapping_temp order by role_menu_mapping_id DESC LIMIT 1 ");
			
             List<RoleMenuMappingTemp> roleMenuMappingTemps = this.jdbcTemplate.query(sqlQuery.toString(), new Object[] {}, (RowMapper) new BeanPropertyRowMapper(RoleMenuMappingTemp.class));
			
			if (roleMenuMappingTemps !=null && roleMenuMappingTemps.size() > 0) 
			{
				return roleMenuMappingTemps.get(0);
			}
		
		}
		catch (Exception e) 
		{
		e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int[] addRoleMenuMappingTemp(List<RoleMenuMappingTemp> roleMenuMappingTemp) 
	{
		int[] batchResponse = null;
		try 
		{
			return this.jdbcTemplate.batchUpdate("INSERT INTO role_menu_mapping_temp "
					+ " (id,participant_id,role_id,menu_id,created_by,created_datetime,last_modified_by,last_modified_datetime,group_id)"
					+ "values(?,?,?,?,?,?,?,?) ", new BatchPreparedStatementSetter() 
					{
						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException 
						{
							ps.setInt(1, roleMenuMappingTemp.get(i).getParticipantId());
							ps.setInt(2, roleMenuMappingTemp.get(i).getParticipantId());
							ps.setInt(3, roleMenuMappingTemp.get(i).getRoleId());
							ps.setString(4, roleMenuMappingTemp.get(i).getMenuId());
							ps.setInt(5, roleMenuMappingTemp.get(i).getCreatedBy());
							ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
							ps.setInt(7, roleMenuMappingTemp.get(i).getLastModifiedBy());
							ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
							ps.setString(9, roleMenuMappingTemp.get(i).getGroupId());
							System.out.println("new BatchPreparedStatementSetter() {...}" + ps);
						}

						@Override
						public int getBatchSize() 
						{
							return roleMenuMappingTemp.size();

						}
					});
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return batchResponse;
	}

	@Override
	public int getRoleExistCount(Cfg_Role cfgRole) 
	{
		try 
		{
			int count = this.jdbcTemplate.update("SELECT COUNT(*) FROM `cfg_role` WHERE id = '"+cfgRole.getId()+"' AND status = 1 ",
					new Object[] {});
			return count;
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
		
	}

	@Override
	public Cfg_Role_Temp getCfgRoleTempForLastInsertId(Cfg_Role_Temp cfg_Role_Temp) 
	{
		try 
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT ");
			sqlQuery.append(" id AS id, participant_id AS participantId ,role_name AS roleName,");
			sqlQuery.append(" role_description AS roleDescription,created_by AS createdBy,created_date AS createdDate,");
			sqlQuery.append(" approved_by AS approvedBy ,remarks AS remarks, group_id AS groupId ");
			sqlQuery.append(" FROM `cfg_role_temp` WHERE id = '"+cfg_Role_Temp.getId()+"' AND role_id = '"+cfg_Role_Temp.getRoleId()+"' ");
			
			 List<Cfg_Role_Temp> cfgRoleTemp = this.jdbcTemplate.query(sqlQuery.toString(), new Object[] {}, (RowMapper) new BeanPropertyRowMapper(Cfg_Role_Temp.class));
				
				if (cfgRoleTemp !=null && cfgRoleTemp.size() > 0) 
				{
					return cfgRoleTemp.get(0);
				}
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addCfgRole(Cfg_Role_Temp cfgRoleTemp) 
	{
		try 
		{
			int count = this.jdbcTemplate.update("INSERT INTO `cfg_role`(id,participant_id,role_name,role_description,status,created_by,created_date,approved_by,approved_date,remarks,group_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)",
					new Object[] { cfgRoleTemp.getId(), cfgRoleTemp.getParticipantId(), cfgRoleTemp.getRoleName(),cfgRoleTemp.getRoleDescription(),1,cfgRoleTemp.getCreatedBy(),cfgRoleTemp.getCreatedDate(),cfgRoleTemp.getApprovedBy(), cfgRoleTemp.getApprovedDate(), cfgRoleTemp.getRemarks(), cfgRoleTemp.getGroupId()});
			return count;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public List<RoleMenuMappingTemp> getRoleMenuMappingTempForId(RoleMenuMappingTemp roleMenuMappingTemp) 
	{
		try 
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT id AS id,role_temp_id AS roleTempId, role_menu_mapping_id AS roleMenuMappingId, participant_id AS participantId,");
			sqlQuery.append(" role_id AS roleId, menu_id AS menuId, status AS status, created_by AS createdBy,");
			sqlQuery.append(" created_datetime AS createdDate, last_modified_by AS lastModifiedBy, last_modified_datetime AS lastModifiedDate, ");
			sqlQuery.append(" group_id AS groupId ");
			sqlQuery.append(" FROM role_menu_mapping_temp WHERE group_id = '"+roleMenuMappingTemp.getGroupId()+"' ");
			
			 List<RoleMenuMappingTemp> roleMenuMappintTemp = this.jdbcTemplate.query(sqlQuery.toString(), new Object[] {}, (RowMapper) new BeanPropertyRowMapper(RoleMenuMappingTemp.class));
			
			if (roleMenuMappintTemp != null && roleMenuMappintTemp.size() > 0) 
			{
				return roleMenuMappintTemp;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public int[] batchRoleMenuMappingTemp(List<RoleMenuMappingTemp> roleMenuMapping) {
	    int[] batchResponse = null;
	    try {
	    	return this.jdbcTemplate.batchUpdate("INSERT INTO role_menu_mapping_temp "
	                + " (role_temp_id,role_menu_mapping_id,participant_id, role_id, menu_id, status, created_by, created_datetime, last_modified_by, last_modified_datetime,approval_status_id, group_id) "
	                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new BatchPreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps, int i) throws SQLException {
	                RoleMenuMappingTemp mapping = roleMenuMapping.get(i);
	                ps.setInt(1, mapping.getRoleTempId());
	                ps.setInt(2, mapping.getRoleMenuMappingId());
	                ps.setInt(3, mapping.getParticipantId());
	                ps.setInt(4, mapping.getRoleId());
	                ps.setString(5, mapping.getMenuId());
	                ps.setInt(6, mapping.getStatus());
	                ps.setInt(7, mapping.getCreatedBy());
	                ps.setString(8, Utils.DateAndHoursFormat());
	                ps.setInt(9, mapping.getLastModifiedBy());
	                ps.setString(10, Utils.DateAndHoursFormat());
	                ps.setInt(11, 3);
	                ps.setString(12, mapping.getGroupId());
	                System.out.println("new BatchPreparedStatementSetter() {...}" + ps);
	            }

	            @Override
	            public int getBatchSize() 
	            {
	                return roleMenuMapping.size();
	            }
	        });
	    } catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    return batchResponse;
	}
	
	public int[] batchRoleMenuMapping(List<RoleMenuMappingTemp> roleMenuMapping) 
	{
	    int[] batchResponse = null;
	    try 
	    {
	    	return this.jdbcTemplate.batchUpdate("INSERT INTO role_menu_mapping "
	                + " (id,participant_id, role_id, menu_id, status, created_by, created_datetime, last_modified_by, last_modified_datetime, group_id) "
	                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new BatchPreparedStatementSetter() 
	             {
	            @Override
	            public void setValues(PreparedStatement ps, int i) throws SQLException {
	                RoleMenuMappingTemp mapping = roleMenuMapping.get(i);
	                ps.setInt(1, mapping.getRoleMenuMappingId());
	                ps.setInt(2, mapping.getParticipantId());
	                ps.setInt(3, mapping.getRoleId());
	                ps.setString(4, mapping.getMenuId());
	                ps.setInt(5, mapping.getStatus());
	                ps.setInt(6, mapping.getCreatedBy());
	                ps.setString(7, Utils.DateAndHoursFormat());
	                ps.setInt(8, mapping.getLastModifiedBy());
	                ps.setString(9, Utils.DateAndHoursFormat());
	                ps.setString(10, mapping.getGroupId());
	                System.out.println("new BatchPreparedStatementSetter() {...}" + ps);
	            }

	            @Override
	            public int getBatchSize() {
	                return roleMenuMapping.size();
	            }
	        });
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return batchResponse;
	}


	@Override
	public Cfg_Role_Temp getCfgRoleForLastInsertId(Cfg_Role_Temp cfgRoleTemp) 
	{
		try 
		{
			StringBuilder sqlQuery = new StringBuilder("SELECT ");
			sqlQuery.append(" SELECT id,participant_id,role_name,role_description,created_by,created_date,P_ApprovedBy,NOW(),P_Remark,group_id ");
			sqlQuery.append(" FROM `cfg_role_temp` WHERE id = P_RoleTempId AND role_id = P_RoleId AND participant_id = P_participantID AND approval_status_id = 3; ");
			sqlQuery.append(" ");
			sqlQuery.append(" ");
			sqlQuery.append(" ");
			sqlQuery.append("");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateCfg_Role(Cfg_Role_Temp cfgRoleTemp) 
	{
		try 
		{
			StringBuilder querySb = new StringBuilder("UPDATE cfg_role ");
			querySb.append("");
			querySb.append("");
			
			int count = jdbcTemplate.update(querySb.toString(),
			new Object[]
			{
				
			});
			return count;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteRoleMenuMapping(Cfg_Role_Temp cfgRoleTemp) 
	{
		try 
		{
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean checkRoleExist(RoleBean roleBean) 
	{
		try
		{
			if (roleBean == null) 
			{
				return false;
			}
			String sql = "SELECT count(*) AS cnt FROM cfg_role_temp AS crt WHERE crt.role_name = '"+roleBean.getStrRoleName().trim()+"'";
			int count = jdbcCMSTemplate.queryForObject(sql, Integer.class, new Object[] {});
			return count > 0;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
}
