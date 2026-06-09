
package com.traneco.role.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.traneco.role.model.Cfg_Role;
import com.traneco.role.model.Cfg_Role_Temp;
import com.traneco.role.model.GroupBean;
import com.traneco.role.model.MenuBean;
import com.traneco.role.model.ParentMenuBean;
import com.traneco.role.model.ParentSubMenuBean;
import com.traneco.role.model.RoleBean;
import com.traneco.role.model.RoleMenuMappingTemp;
import com.traneco.role.model.UserRoleMappingTemp;

@Repository
public interface RoleDao 
{
    public void addRole(RoleBean roleBean) throws Exception;

    public void approveRole(RoleBean roleBean) throws Exception;

    public void editRole(RoleBean roleBean) throws Exception;

    public List<MenuBean> loadAllMenu() throws Exception;

    public List<ParentMenuBean> loadAllParentMenu() throws Exception;

    public List<ParentSubMenuBean> loadAllParentSubMenu() throws Exception;

    public List<RoleBean> searchRoleToEdit() throws Exception;

    public List<RoleBean> getPendingRoleForApproval();

    public List<RoleBean> getRoleForEdit(RoleBean roleBean);

    public String getRolePendingStatus(RoleBean roleBean) throws Exception;
    
    public int insertGroup(GroupBean bean);

	public int InsertAuditEntry(RoleBean roleBean, String tableName);

	UserRoleMappingTemp getUserRoleMappingTeampRoleId();

	int addCfgRoleTemp(RoleBean roleBean);

	RoleMenuMappingTemp getRoleMenuMappingTeamp(RoleMenuMappingTemp roleMenuMappingTemp);

	int[] addRoleMenuMappingTemp(List<RoleMenuMappingTemp> roleMenuMappingTemp);

	int getRoleExistCount(Cfg_Role cfgRole);

	Cfg_Role_Temp getCfgRoleTempForLastInsertId(Cfg_Role_Temp cfg_Role_Temp);

	int addCfgRole(Cfg_Role_Temp cfg_Role_Temp);

	List<RoleMenuMappingTemp> getRoleMenuMappingTempForId(RoleMenuMappingTemp roleMenuMappingTemp);

	int[] batchRoleMenuMapping(List<RoleMenuMappingTemp> roleMenuMappingTemp);

	Cfg_Role_Temp getCfgRoleForLastInsertId(Cfg_Role_Temp cfgRoleTemp);

	int updateCfg_Role(Cfg_Role_Temp cfgRoleTemp);

	int deleteRoleMenuMapping(Cfg_Role_Temp cfgRoleTemp);

    boolean checkRoleExist(RoleBean roleBean);

	public int[] batchRoleMenuMappingTemp(List<RoleMenuMappingTemp> roleMenuMappingTemp);
    
}

