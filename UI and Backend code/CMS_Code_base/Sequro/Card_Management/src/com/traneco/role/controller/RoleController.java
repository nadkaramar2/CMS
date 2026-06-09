package com.traneco.role.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.traneco.common.SessionDTO;
import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.util.Utils;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.role.model.GroupBean;
import com.traneco.role.model.RoleBean;
import com.traneco.role.services.RoleService;
import com.traneco.role.validator.RoleValidator;
import com.traneco.user.services.UserService;

@Controller
public class RoleController 
{
	private String className = this.getClass().getSimpleName();
	@Autowired
	RoleService roleService;
	
	@Autowired
	SessionDTO sessionDTO;
	
	@Autowired
	Environment env;
	
	@Autowired
	LoggerUtil log;
	
	@Autowired
	RoleValidator roleValidator;
	
	@Autowired
    UserService  userService;

	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public String addRole(@ModelAttribute("roleBean") RoleBean roleBean, BindingResult result, ModelMap model) {
		String methodName = "addRole";
		try 
		{
			log.doLog(4, className, methodName, "Executing " + methodName);
			roleBean.setStrAction(TranecoStatusCode.REQTYPE_ADD);
			// roleValidator.validate(roleBean, result);

			// Can we remove this DB call??
			roleBean.setMenuList(roleService.loadAllMenu()); 
			roleBean.setSubMenuList(roleService.loadAllParentSubMenu());
			roleBean.setParentMenuList(roleService.loadAllParentMenu());
			model.addAttribute("groupList", userService.getGroupList(sessionDTO.getParticipantid()));
			/*
			 * if (result.hasErrors()) { model.addAttribute("display", "none"); } else {
			 */
			roleService.addRole(roleBean);
			model.addAttribute("display", "block");

			switch (roleBean.getStrResultCode()) 
			{
			case TranecoStatusCode.STATUS_SUCCESS:
				model.addAttribute("success", env.getProperty("imps.role.add.success"));
				roleBean.setStrRoleName("");
				roleBean.setStrDescription("");
				break;

			case TranecoStatusCode.STATUS_SQL_EXCEPTION:
				model.addAttribute("exception", env.getProperty("imps.role.add.failure"));
				
				break;

			case TranecoStatusCode.STATUS_DUPLICATE:
				model.addAttribute("error", env.getProperty("imps.role.add.duplicate"));

				break;

			default:
				break;
			}
			/* } */

			log.doLog(4, className, methodName, "ResultCode from SP :" + roleBean.getStrResultCode());
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.role.sql.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		finally 
		{
			Utils.closeResources(methodName);
		}

		return "addRoleForm";
	}

	@RequestMapping(value = "/addRoleForm", method = RequestMethod.GET)
	public String addRoleForm(ModelMap model, RoleBean roleBean) 
	{
		String methodName = "addRoleForm";
		try 
		{
			log.doLog(4, className, methodName, "Executing " + methodName);
			roleBean.setMenuList(roleService.loadAllMenu());
			roleBean.setSubMenuList(roleService.loadAllParentSubMenu());
			roleBean.setParentMenuList(roleService.loadAllParentMenu());
			model.addAttribute("roleBean", roleBean);
			model.addAttribute("groupList", userService.getGroupList(sessionDTO.getParticipantid()));
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.role.sql.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		finally
		{
			Utils.closeResources(methodName);
		}
		return "addRoleForm";
	}

	@RequestMapping(value = "/approveRole", method = RequestMethod.POST)
	public String approveRole(@ModelAttribute("roleBean") RoleBean roleBean, ModelMap model, BindingResult result,
			HttpServletRequest request) 
		{
		String methodName = "approveRole";
		
		List<RoleBean> roleList = null;
		ObjectMapper mapper = new ObjectMapper();

		try 
		{
			log.doLog(4, className, methodName, "Executing " + methodName);
			roleBean.setStrAction(TranecoStatusCode.REQTYPE_APPROVE);

			// roleValidator.validate(roleBean, result);

			/*
			 * if (result.hasErrors()) { model.addAttribute("error", " ");
			 * model.addAttribute("display", "block"); approveRoleForm(model, roleBean); }
			 * else {
			 */
			String[] tempID = roleBean.getStrRoleTempID().split("\\|");
			roleBean.setStrRoleTempID(tempID[0]);
			roleBean.setIRoleID(Integer.parseInt(tempID[1]));
			roleService.approveRole(roleBean);

			switch (roleBean.getStrResultCode()) 
			{
			case TranecoStatusCode.STATUS_SUCCESS:
				model.addAttribute("success",
						env.getProperty("imps.role.approve.success") + " Ref ID " + roleBean.getStrRoleTempID());
				break;

			case TranecoStatusCode.STATUS_SQL_EXCEPTION:
				model.addAttribute("exception",
						env.getProperty("imps.role.sql.exception") + " Ref ID " + roleBean.getStrRoleTempID());

				break;

			case TranecoStatusCode.STATUS_DUPLICATE:
				model.addAttribute("error",
						env.getProperty("imps.role.approve.error") + " Ref ID " + roleBean.getStrRoleTempID());

				break;

			default:
				break;
			}

			model.addAttribute("display", "block");
			roleList = roleService.getPendingRoleForApproval();

			if (roleList.isEmpty()) 
			{
				log.doLog(4, className, methodName, "No role record found pending for approval.");
				model.addAttribute("error", env.getProperty("imps.role.approve.no.record"));
				model.addAttribute("display", "block");
			} 
			else 
			{
				log.doLog(4, className, methodName, "Role found pending for approval. Count = " + roleList.size());
				model.addAttribute("roleList", mapper.writeValueAsString(roleList));
				roleBean.setStrRemark("");
			}
			// }
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.role.sql.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		} 
		finally 
		{
			Utils.closeResources(methodName, roleList, mapper);
		}

		return "approveRoleForm";
	}

	@RequestMapping(value = "/approveRoleForm", method = RequestMethod.GET)
	public String approveRoleForm(ModelMap model, RoleBean roleBean) 
	{
		List<RoleBean> roleList = null;
		ObjectMapper mapper = new ObjectMapper();
		String methodName = "approveRoleForm";

		try 
		{
			log.doLog(4, className, methodName, "Executing " + methodName);
			roleList = roleService.getPendingRoleForApproval();

			if (!roleList.isEmpty()) 
			{
				log.doLog(4, className, methodName, "Role found pending for approval. Count = " + roleList.size());
				model.addAttribute("roleList", mapper.writeValueAsString(roleList));
			}
		} 
		catch (Exception e) 
		{
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.role.sql.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		finally 
		{
			Utils.closeResources(roleList, mapper, methodName);
		}

		return "approveRoleForm";
	}

	@RequestMapping(value = "/editRole", method = RequestMethod.POST)
	public String editRole(@ModelAttribute("roleBean") RoleBean roleBean, ModelMap model, BindingResult result) {
		String methodName = "editRole";

		roleBean.setStrAction(TranecoStatusCode.REQTYPE_EDIT);
		try 
		{
			log.doLog(4, className, methodName, "Executing " + methodName);
			roleValidator.validate(roleBean, result);

			if (result.hasErrors()) 
			{
				model.addAttribute("error", " ");
				model.addAttribute("display", "block");
				roleBean.setMenuList(roleService.loadAllMenu());
				roleBean.setSubMenuList(roleService.loadAllParentSubMenu());
				roleBean.setParentMenuList(roleService.loadAllParentMenu());
				roleService.getRoleForEdit(roleBean);

				return "showRole";
			} else {
				roleService.editRole(roleBean);
				model.addAttribute("display", "block");

				switch (roleBean.getStrResultCode()) {
				case TranecoStatusCode.STATUS_SUCCESS:
					log.doLog(4, className, methodName,
							"Role edited successfully. Role Name = " + roleBean.getStrRoleName());
					model.addAttribute("success", env.getProperty("imps.role.edit.success"));

					break;

				case TranecoStatusCode.STATUS_SQL_EXCEPTION:
					log.doLog(4, className, methodName,
							"SQL Error while editing role. Role Name = " + roleBean.getStrRoleName());
					model.addAttribute("exception", env.getProperty("imps.role.edit.failure"));

					break;

				default:
					break;
				}

				log.doLog(4, className, methodName, "ResultCode from SP :" + roleBean.getStrResultCode());
				model.remove("roleBean");
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.role.sql.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		} finally {
			Utils.closeResources(methodName);
		}

		return "editRoleForm";
	}

	@RequestMapping(value = "/editRoleForm", method = RequestMethod.GET)
	public String editRoleForm(@ModelAttribute("roleBean") RoleBean roleBean, ModelMap model) {
		String methodName = "editRoleForm";
		List<RoleBean> roleList = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			log.doLog(4, className, methodName, "Executing " + methodName);
			roleList = roleService.searchRoleToEdit();

			if (!roleList.isEmpty()) {
				log.doLog(4, className, methodName, "Found " + roleList.size() + " roles to edit.");
				model.addAttribute("roleList", mapper.writeValueAsString(roleList));
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.role.sql.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		} finally {
			Utils.closeResources(roleList, methodName, mapper);
		}

		model.addAttribute("roleBean", roleBean);

		return "editRoleForm";
	}

	@RequestMapping(value = "/showRoleToEdit", method = RequestMethod.POST)
	public String showRoleToEdit(@ModelAttribute("roleBean") RoleBean roleBean, BindingResult result, ModelMap model) {
		String methodName = "showRoleToEdit";
		List<RoleBean> roleList = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			log.doLog(4, className, methodName, "Executing " + methodName);
			roleBean.setStrAction(TranecoStatusCode.REQTYPE_SHOW_ROLE);
			roleValidator.validate(roleBean, result);

			if (result.hasErrors()) 
			{
				model.addAttribute("error", " ");
				model.addAttribute("display", "block");
				model.addAttribute("roleBean", roleBean);
				roleList = roleService.searchRoleToEdit();
				model.addAttribute("roleList", mapper.writeValueAsString(roleList));

				return "editRoleForm";
			} else {
				roleBean.setMenuList(roleService.loadAllMenu());
				roleBean.setSubMenuList(roleService.loadAllParentSubMenu());
				roleBean.setParentMenuList(roleService.loadAllParentMenu());
				log.doLog(4, className, methodName, "Getting roleid " + roleBean.getIRoleID());
				roleService.getRoleForEdit(roleBean);

				if (roleBean.getStrPendingForApproval().equals("true")) {
					model.addAttribute("error", env.getProperty("imps.role.approval.pending"));
					model.addAttribute("display", "block");
					model.addAttribute("roleBean", roleBean);
				}
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.role.edit.fetch.privileges"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		} finally {
			Utils.closeResources(methodName, mapper, roleList);
		}

		return "showRole";
	}

	@RequestMapping(value = "/viewRole", method = RequestMethod.POST)
	public String viewRole(@ModelAttribute("roleBean") RoleBean roleBean, ModelMap model, BindingResult result) {
		String methodName = "viewRole";
		List<RoleBean> roleList = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			log.doLog(4, className, methodName, "Executing " + methodName);
			roleBean.setStrAction(TranecoStatusCode.REQTYPE_VIEW);
			roleValidator.validate(roleBean, result);

			if (result.hasErrors()) {
				model.addAttribute("error", " ");
				model.addAttribute("display", "block");
				roleList = roleService.searchRoleToEdit();
				model.addAttribute("roleList", mapper.writeValueAsString(roleList));

				return "viewRoleForm";
			} else {
				roleBean.setMenuList(roleService.loadAllMenu());
				roleBean.setSubMenuList(roleService.loadAllParentSubMenu());
				roleBean.setParentMenuList(roleService.loadAllParentMenu());
				roleService.getRoleForView(roleBean);
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.role.sql.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		} finally {
			Utils.closeResources(methodName, roleList, mapper);
			model.addAttribute("roleBean", roleBean);
		}

		return "viewRole";
	}

	@RequestMapping(value = "/viewRoleForm", method = RequestMethod.GET)
	public String viewRoleForm(@ModelAttribute("roleBean") RoleBean roleBean, ModelMap model, BindingResult result) {
		String methodName = "viewRoleForm";
		List<RoleBean> roleList = null;
		ObjectMapper mapper = new ObjectMapper();

		try {
			log.doLog(4, className, methodName, "Executing " + methodName);
			roleList = roleService.searchRoleToEdit();

			if (roleList.isEmpty()) {
				model.addAttribute("display", "block");
				log.doLog(4, className, methodName, "No role found to view.");
				model.addAttribute("error", env.getProperty("imps.role.view.no.record"));
				model.addAttribute("display", "block");
			} else {
				log.doLog(4, className, methodName, "Role record found for view. Count = " + roleList.size());
				model.addAttribute("roleList", mapper.writeValueAsString(roleList));
			}
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.role.sql.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		} finally {
			Utils.closeResources(roleList, methodName, mapper);
		}

		model.addAttribute("roleBean", roleBean);

		return "viewRoleForm";
	}

	@RequestMapping(value = "/addGroupForm", method = RequestMethod.GET)
	public String addGroupForm(GroupBean groupBean, ModelMap model) {
		String methodName = "addGroupForm";

		try {
			groupBean.setStrParticipantID(sessionDTO.getParticipantid());
			groupBean.setStrCreatedBy(""+sessionDTO.getUserID());
			model.addAttribute("groupBean", groupBean);
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));

		}
		return "addGroupForm";
	}

}
