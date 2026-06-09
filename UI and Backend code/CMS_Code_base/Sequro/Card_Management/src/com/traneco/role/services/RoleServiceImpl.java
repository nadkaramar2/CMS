
package com.traneco.role.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traneco.common.SessionDTO;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.role.dao.RoleDao;
import com.traneco.role.model.Cfg_Role;
import com.traneco.role.model.Cfg_Role_Temp;
import com.traneco.role.model.GroupBean;
import com.traneco.role.model.MenuBean;
import com.traneco.role.model.ParentMenuBean;
import com.traneco.role.model.ParentSubMenuBean;
import com.traneco.role.model.RoleBean;
import com.traneco.role.model.RoleMenuMappingTemp;
import com.traneco.role.model.UserRoleMappingTemp;

@Service("roleService")
public class RoleServiceImpl implements RoleService 
{
    @Autowired
    RoleDao roleDao;
    @Autowired
    LoggerUtil log;
    @Autowired
    SessionDTO sessionDTO;

    @Override
    public void addRole(RoleBean roleBean) throws Exception {
        roleDao.addRole(roleBean);
    }

    @Override
    public void approveRole(RoleBean roleBean) throws Exception {
        roleDao.approveRole(roleBean);
    }

    @Override
    public void editRole(RoleBean roleBean) throws Exception {
        roleDao.editRole(roleBean);
    }

    @Override
    public List<MenuBean> loadAllMenu() throws Exception {
        return roleDao.loadAllMenu();
    }

    @Override
    public List<ParentMenuBean> loadAllParentMenu() throws Exception {
        return roleDao.loadAllParentMenu();
    }

    @Override
    public List<ParentSubMenuBean> loadAllParentSubMenu() throws Exception {
        return roleDao.loadAllParentSubMenu();
    }

    @Override
    public List<RoleBean> searchRoleToEdit() throws Exception {
        return roleDao.searchRoleToEdit();
    }

    @Override
    public List<RoleBean> getPendingRoleForApproval() {
        return roleDao.getPendingRoleForApproval();
    }

    @Override
    public RoleBean getRoleForEdit(RoleBean roleBean) throws Exception {
        List<RoleBean> roleList   = null;
        List<String>   menuIds    = new ArrayList<String>();
        String         roleName   = null;
        String         methodName = "getRoleForEdit";

        try {
            roleList = roleDao.getRoleForEdit(roleBean);

            if ((roleList != null) && (roleList.size() > 0)) {
                roleBean.setStrRoleName(roleList.get(0).getStrRoleName());
                roleBean.setStrDescription(roleList.get(0).getStrDescription());
                roleBean.setStrActive(roleList.get(0).getStrActive());
                roleBean.setIRoleID(roleList.get(0).getIRoleID());
                roleList.forEach(
                    role -> {
                        roleBean.getParentMenuList().forEach(parentMenu -> {
                                if (parentMenu.getParentMenuId() == role.getIParentMenuId()) {
                                    parentMenu.setChecked("true");
                                } else {
                                    parentMenu.setChecked("false");
                                }
                            });
                        roleBean.getSubMenuList().forEach(subMenu -> {
                                if (subMenu.getParentSubMenuId() == role.getIParentSubMenuId()) {
                                    subMenu.setChecked("true");
                                } else {
                                    subMenu.setChecked("false");
                                }
                            });
                        roleBean.getMenuList().forEach(menu -> {
                                if (menu.getMenuId() == role.getIMenuId()) {
                                    menu.setChecked("true");
                                    menuIds.add("" + menu.getMenuId());
                                } else {
                                    menu.setChecked("false");
                                }
                            });
                    });
                roleBean.setStrMenuIdsArr(
                    menuIds.toArray(
                        new String[roleBean.getMenuList().size() + roleBean.getSubMenuList().size() + roleBean.getParentMenuList().size()]));
                roleBean.setStrMenuIds(Utils.convertToString(menuIds));
                roleName = roleDao.getRolePendingStatus(roleBean);

                if ((roleName != null) &&!roleName.equals("")) {
                    roleBean.setStrPendingForApproval("true");
                } else {
                    roleBean.setStrPendingForApproval("false");
                }
            }
        } finally {
            Utils.closeResources(roleList, methodName, roleName, menuIds);
        }

        return roleBean;
    }

    @Override
    public RoleBean getRoleForView(RoleBean roleBean) 
    {
        List<RoleBean> roleList   = null;
        List<String>   menuIds    = new ArrayList<String>();
        String         methodName = "getRoleForEdit";

        try 
        {
            roleList = roleDao.getRoleForEdit(roleBean);

            if ((roleList != null) && (roleList.size() > 0)) 
            {
                roleBean.setStrRoleName(roleList.get(0).getStrRoleName());
                roleBean.setStrDescription(roleList.get(0).getStrDescription());
                roleBean.setStrActive(roleList.get(0).getStrActive());
                roleBean.setIRoleID(roleList.get(0).getIRoleID());
                roleList.forEach(
                    role -> {
                        roleBean.getParentMenuList().forEach(parentMenu -> {
                                if (parentMenu.getParentMenuId() == role.getIParentMenuId()) {
                                    parentMenu.setChecked("true");
                                } else {
                                    parentMenu.setChecked("false");
                                }
                            });
                        roleBean.getSubMenuList().forEach(subMenu -> {
                                if (subMenu.getParentSubMenuId() == role.getIParentSubMenuId()) {
                                    subMenu.setChecked("true");
                                } else {
                                    subMenu.setChecked("false");
                                }
                            });
                        roleBean.getMenuList().forEach(menu -> 
                        {
                                if (menu.getMenuId() == role.getIMenuId()) 
                                {
                                    menu.setChecked("true");
                                    menuIds.add("" + menu.getMenuId());
                                }
                                else 
                                {
                                    menu.setChecked("false");
                                }
                            });
                    });
                roleBean.setStrMenuIdsArr(
                    menuIds.toArray(
                        new String[roleBean.getMenuList().size() + roleBean.getSubMenuList().size() + roleBean.getParentMenuList().size()]));
                roleBean.setStrMenuIds(Utils.convertToString(menuIds));
            }
        } finally {
            Utils.closeResources(roleList, methodName, menuIds);
        }

        return roleBean;
    }

	@Override
	public int insertGroup(GroupBean bean) {
		return roleDao.insertGroup(bean);
	}

	@Override
	public int InsertAuditEntry(RoleBean roleBean, String tableName) 
	{
		return roleDao.InsertAuditEntry(roleBean,tableName);
	}

	@Override
	public UserRoleMappingTemp getUserRoleMappingTeampRoleId() 
	{
		return roleDao.getUserRoleMappingTeampRoleId();
	}

	@Override
	public int addCfgRoleTemp(RoleBean roleBean) 
	{
		return roleDao.addCfgRoleTemp(roleBean);
	}

	@Override
	public RoleMenuMappingTemp getRoleMenuMappingTeamp(RoleMenuMappingTemp roleMenuMappingTemp) 
	{
		return roleDao.getRoleMenuMappingTeamp(roleMenuMappingTemp);
	}

	@Override
	public int[] addRoleMenuMappingTemp(List<RoleMenuMappingTemp> roleMenuMappingTemp) 
	{
		return roleDao.addRoleMenuMappingTemp(roleMenuMappingTemp);
	}

	@Override
	public int getRoleExistCount(Cfg_Role cfgRole) 
	{
		return roleDao.getRoleExistCount(cfgRole);
	}

	@Override
	public Cfg_Role_Temp getCfgRoleTempForLastInsertId(Cfg_Role_Temp cfg_Role_Temp) 
	{
		return roleDao.getCfgRoleTempForLastInsertId(cfg_Role_Temp);
	}

	@Override
	public int addCfgRole(Cfg_Role_Temp cfgRoleTemp)
	{
		return roleDao.addCfgRole(cfgRoleTemp);
	}

	@Override
	public List<RoleMenuMappingTemp> getRoleMenuMappingTempForId(RoleMenuMappingTemp roleMenuMappingTemp) 
	{
		return roleDao.getRoleMenuMappingTempForId(roleMenuMappingTemp);
	}

	@Override
	public Cfg_Role_Temp getCfgRoleForLastInsertId(Cfg_Role_Temp cfgRoleTemp) 
	{
		return roleDao.getCfgRoleForLastInsertId(cfgRoleTemp);
	}

	@Override
	public int updateCfg_Role(Cfg_Role_Temp cfgRoleTemp) 
	{
		return roleDao.updateCfg_Role(cfgRoleTemp);
	}

	@Override
	public int deleteRoleMenuMapping(Cfg_Role_Temp cfgRoleTemp) 
	{
		return roleDao.deleteRoleMenuMapping(cfgRoleTemp);
	}

	@Override
	public int[] batchRoleMenuMapping(List<RoleMenuMappingTemp> roleMenuMappingTemp) 
	{
		return roleDao.batchRoleMenuMapping(roleMenuMappingTemp);
	}
	
	@Override
	public int[] batchRoleMenuMappingTemp(List<RoleMenuMappingTemp> roleMenuMappingTemp) 
	{
		return roleDao.batchRoleMenuMappingTemp(roleMenuMappingTemp);
	}

	@Override
	public boolean checkRoleExist(RoleBean roleBean) 
	{
		return roleDao.checkRoleExist(roleBean);
	}
}

