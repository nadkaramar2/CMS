/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to display and perform institution operation.
 * 
 *@History
 *===============================================================================================================================================
 *     @Version         @Date         	@Author                 @Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to display and perform institution operation.
 *===============================================================================================================================================
 *
 */

package com.traneco.institution.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.traneco.common.CardStatusCode;
import com.traneco.common.SessionDTO;
import com.traneco.common.util.logger.LoggerUtil;
import com.traneco.institution.model.InstitutionBean;
import com.traneco.institution.services.InstitutionService;
import com.traneco.institution.validator.InstitutionValidator;


@Controller
public class InstitutionController 
{
	@Autowired
	Environment env;

	@Autowired
	InstitutionService institutionService;

	@Autowired
	SessionDTO sessionDTO;

	@Autowired
	LoggerUtil log;

	@Autowired
	InstitutionValidator institutionValidator;

	private String className = this.getClass().getSimpleName();

	@RequestMapping(value = "/addInstForm", method = RequestMethod.GET)
	public String addInstitutionForm(Model model, InstitutionBean institutionBean) {
		String methodName = "addInstitutionForm";
		model.addAttribute("institutionBean", institutionBean);
		try {
			log.doLog(4, InstitutionController.class.getName(), "addInstitutionForm", sessionDTO.getDisplayName());
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "addInstForm";
	}

	@RequestMapping(value = "/addInst", method = RequestMethod.POST)
	public String addInstitution(Model model, InstitutionBean institutionBean, BindingResult result) {
		String methodName = "addInstitution";
		String respCode = "";
		try {
			log.doLog(4, className, methodName, "Inside Method :" + " InstCode :" + institutionBean.getInstitutionCode()
					+ " InstDesc :" + institutionBean.getInstitutionDesc());
			institutionBean.setType("AddInst");
			institutionValidator.validate(institutionBean, result);
			if (result.hasErrors()) {
				model.addAttribute("display", "none");
			} else {
				respCode = institutionService.addInstitution(institutionBean.getInstitutionCode(),
						institutionBean.getInstitutionDesc());
				model.addAttribute("display", "block");
				switch (respCode) {
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", env.getProperty("imps.instAddForm.sucess"));
					institutionBean = new InstitutionBean();
					break;
				case CardStatusCode.STATUS_SQL_EXCEPTION:
					model.addAttribute("exception", env.getProperty("imps.instAddForm.exception"));
					break;
				case CardStatusCode.STATUS_DUPLICATE:
					model.addAttribute("error", env.getProperty("imps.instAddForm.error"));
					break;
				default:
					break;
				}
			}
			log.doLog(4, className, methodName,
					"End Inside Method :" + " InstCode :" + institutionBean.getInstitutionCode() + " InstDesc :"
							+ institutionBean.getInstitutionDesc() + " ResponseCode :" + respCode);
		} catch (Exception e) {
			model.addAttribute("display", "block");
			model.addAttribute("exception", env.getProperty("imps.instAddForm.exception"));
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		model.addAttribute("institutionBean", institutionBean);
		return "addInstForm";
	}

	@RequestMapping(value = "/viewInstForm", method = RequestMethod.GET)
	public String viewInstForm(Model model, InstitutionBean institutionBean) {
		String methodName = "viewInstForm";
		model.addAttribute("institutionBean", institutionBean);
		try {
			log.doLog(4, InstitutionController.class.getName(), methodName, sessionDTO.getDisplayName());
			ObjectMapper mapper = new ObjectMapper();
			model.addAttribute("viewInstList", mapper.writeValueAsString(institutionService.getViewInstitutionList()));
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "viewInstForm";
	}

	@RequestMapping(value = "/editInstForm", method = RequestMethod.GET)
	public String editInstForm(Model model, InstitutionBean institutionBean) {
		String methodName = "editInstForm";
		model.addAttribute("institutionBean", institutionBean);
		try {
			log.doLog(4, InstitutionController.class.getName(), methodName, sessionDTO.getDisplayName());
			model.addAttribute("editInstList", institutionService.getViewInstitutionList());
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "editInstForm";
	}

	@RequestMapping(value = "/editInstDetails", method = RequestMethod.POST)
	public String editInstDetails(Model model, InstitutionBean institutionBean) {
		String methodName = "editInstDetails";
		try {
			log.doLog(4, InstitutionController.class.getName(), methodName, sessionDTO.getDisplayName());
			model.addAttribute("institutionBean",
					institutionService.editInstDetailsByID(institutionBean.getInstitutionId()));
			int count = institutionService.getApprovalPendingStatus(institutionBean.getInstitutionId());
			if (count > 0) {
				model.addAttribute("display", "block");
				model.addAttribute("flag", false);
				model.addAttribute("error", env.getProperty("imps.instApproveForm.pending"));
			} else {
				model.addAttribute("display", "block");
				model.addAttribute("flag", true);
			}
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "editInstDetails";
	}

	@RequestMapping(value = "/editInst", method = RequestMethod.POST)
	public String editInst(Model model, InstitutionBean institutionBean, BindingResult result) {
		String methodName = "editInst";
		String respCode = "";
		try {
			log.doLog(4, InstitutionController.class.getName(), methodName, sessionDTO.getDisplayName());
			institutionBean.setType("EditInst");
			institutionValidator.validate(institutionBean, result);
			if (result.hasErrors()) {
				model.addAttribute("display", "block");
				return "editInstDetails";
			} else {
				respCode = institutionService.editInst(institutionBean);
				model.addAttribute("display", "block");
				switch (respCode) {
				case CardStatusCode.STATUS_SUCCESS:
					model.addAttribute("success", env.getProperty("imps.instEditForm.sucess"));
					break;
				case CardStatusCode.STATUS_SQL_EXCEPTION:
					model.addAttribute("exception", env.getProperty("imps.instEditForm.exception"));
					break;
				case CardStatusCode.STATUS_DUPLICATE:
					model.addAttribute("error", env.getProperty("imps.instEditForm.error"));
					break;
				default:
					break;
				}
			}
			model.addAttribute("editInstList", institutionService.getViewInstitutionList());
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "editInstForm";
	}

	@RequestMapping(value = "/approveInstForm", method = RequestMethod.GET)
	public String approveInstForm(Model model, InstitutionBean institutionBean) {
		String methodName = "approveInstForm";
		try {
			log.doLog(4, InstitutionController.class.getName(), methodName, sessionDTO.getDisplayName());
			model.addAttribute("instApprovalList",
					institutionService.getApprovalInstitutionPendingList(String.valueOf(sessionDTO.getUserID())));
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "approveInstForm";
	}

	@RequestMapping(value = "/approveInstDetails", method = RequestMethod.POST)
	public String approveInstDetails(Model model, InstitutionBean institutionBean, BindingResult result) {
		String methodName = "approveInstDetails";
		String respCode = "";
		try {
			log.doLog(4, InstitutionController.class.getName(), methodName, sessionDTO.getDisplayName());
			institutionBean.setType("ApproveInst");
			institutionValidator.validate(institutionBean, result);
			if (result.hasErrors()) {
				model.addAttribute("error", " ");
				model.addAttribute("display", "block");
				return "approveInstForm";
			} else {
				String[] data = institutionBean.getApproveID();
				for (int i = 0; i < data.length; i++) {
					respCode = institutionService.approvInstitution(data[i], institutionBean.getRequestID(), institutionBean.getRemark());
					String[] refId = data[i].split("\\|");
					switch (respCode) {
					case CardStatusCode.STATUS_SUCCESS:
						model.addAttribute("success",
								env.getProperty("imps.instApproveForm.sucess") + " Ref ID " + refId[1]);
						break;
					case CardStatusCode.STATUS_SQL_EXCEPTION:
						model.addAttribute("exception",
								env.getProperty("imps.instApproveForm.exception") + " Ref ID " + refId[1]);
						break;
					case CardStatusCode.STATUS_DUPLICATE:
						model.addAttribute("error",
								env.getProperty("imps.instApproveForm.error") + " Ref ID " + refId[1]);
						break;
					default:
						break;
					}
				}
				model.addAttribute("display", "block");
			}
			model.addAttribute("instApprovalList",
					institutionService.getApprovalInstitutionPendingList(String.valueOf(sessionDTO.getUserID())));
		} catch (Exception e) {
			log.doLog(3, className, methodName, LoggerUtil.getExStackTrace(e));
		}
		return "approveInstForm";
	}

}
