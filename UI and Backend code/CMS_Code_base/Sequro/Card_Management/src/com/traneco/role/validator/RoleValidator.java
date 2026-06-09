package com.traneco.role.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.util.Utils;
import com.traneco.role.model.RoleBean;

@Component
public class RoleValidator implements Validator 
{
    @Autowired
    Environment env;

    @Override
    public boolean supports(Class<?> arg0) {
        return RoleBean.class.isAssignableFrom(arg0);
    }

    @Override
    public void validate(Object arg0, Errors arg1) {
        RoleBean roleBean = (RoleBean) arg0;

        switch (roleBean.getStrAction()) {
        case TranecoStatusCode.REQTYPE_ADD :
            if (Utils.isEmptyStr(roleBean.getStrRoleName())) {
                arg1.rejectValue("strRoleName", "imps.role.roleName.blank");
            } else if (!Utils.isAlphaNumeric(roleBean.getStrRoleName())) {
                arg1.rejectValue("strRoleName", "imps.role.invalid.roleName");
            } else if (!Utils.isMinLenValid(roleBean.getStrRoleName(),
                                            Integer.parseInt(env.getProperty("imps.role.name.min.length")))) {
                arg1.rejectValue("strRoleName", "imps.role.name.min.length.invalid");
            } else if (!Utils.isMaxLenValid(roleBean.getStrRoleName(),
                                            Integer.parseInt(env.getProperty("imps.role.name.max.length")))) {
                arg1.rejectValue("strRoleName", "imps.role.name.max.length.invalid");
            }

            if (Utils.isEmptyStr(roleBean.getStrDescription())) {
                arg1.rejectValue("strDescription", "imps.role.description.blank");
            } else if (!Utils.isAlphaNumericSpace(roleBean.getStrDescription())) {
                arg1.rejectValue("strDescription", "imps.role.invalid.description");
            } else if (!Utils.isMinLenValid(roleBean.getStrDescription(),
                                            Integer.parseInt(env.getProperty("imps.role.desc.min.length")))) {
                arg1.rejectValue("strDescription", "imps.role.desc.min.length.invalid");
            } else if (!Utils.isMaxLenValid(roleBean.getStrDescription(),
                                            Integer.parseInt(env.getProperty("imps.role.desc.max.length")))) {
                arg1.rejectValue("strDescription", "imps.role.desc.max.length.invalid");
            }

            if (Utils.isEmptyStr(roleBean.getStrMenuIds())) {
                arg1.rejectValue("menuIds", "imps.role.menuids.blank");
            }

            break;

        case TranecoStatusCode.REQTYPE_SHOW_ROLE :
            if (Utils.isEmptyStr((roleBean.getIRoleID() == null)
                                 ? ""
                                 : String.valueOf(roleBean.getIRoleID()))) {
                arg1.rejectValue("iRoleID", "imps.role.roleId.blank");
            } else if (!Utils.isNumber((roleBean.getIRoleID() == null)
                                       ? ""
                                       : String.valueOf(roleBean.getIRoleID()))) {
                arg1.rejectValue("iRoleID", "imps.role.invalid.roleId");
            }

            break;

        case TranecoStatusCode.REQTYPE_EDIT :
            if (Utils.isEmptyStr(roleBean.getStrRoleName())) {
                arg1.rejectValue("strRoleName", "imps.role.roleName.blank");
            } else if (!Utils.isAlphaNumeric(roleBean.getStrRoleName())) {
                arg1.rejectValue("strRoleName", "imps.role.invalid.roleName");
            } else if (!Utils.isMinLenValid(roleBean.getStrRoleName(),
                                            Integer.parseInt(env.getProperty("imps.role.name.min.length")))) {
                arg1.rejectValue("strRoleName", "imps.role.name.min.length.invalid");
            } else if (!Utils.isMaxLenValid(roleBean.getStrRoleName(),
                                            Integer.parseInt(env.getProperty("imps.role.name.max.length")))) {
                arg1.rejectValue("strRoleName", "imps.role.name.max.length.invalid");
            }

            if (Utils.isEmptyStr(roleBean.getStrDescription())) {
                arg1.rejectValue("strDescription", "imps.role.description.blank");
            } else if (!Utils.isAlphaNumeric(roleBean.getStrDescription())) {
                arg1.rejectValue("strDescription", "imps.role.invalid.description");
            } else if (!Utils.isMinLenValid(roleBean.getStrDescription(),
                                            Integer.parseInt(env.getProperty("imps.role.desc.min.length")))) {
                arg1.rejectValue("strRoleName", "imps.role.desc.min.length.invalid");
            } else if (!Utils.isMaxLenValid(roleBean.getStrDescription(),
                                            Integer.parseInt(env.getProperty("imps.role.desc.max.length")))) {
                arg1.rejectValue("strRoleName", "imps.role.desc.max.length.invalid");
            }

            if ((roleBean.getStrMenuIdsArr() == null) || (roleBean.getStrMenuIdsArr().length < 1)) {
                arg1.rejectValue("menuIdsArr", "imps.role.menuids.blank");
            }

            break;

        case TranecoStatusCode.REQTYPE_APPROVE :
            if ((roleBean.getStrRoleTempIDs() == null) || (roleBean.getStrRoleTempIDs().length < 1)) {
                arg1.rejectValue("roleTempID", "imps.role.approve.select.blank");
            }

            if (Utils.isEmptyStr(roleBean.getStrRemark())) {
                arg1.rejectValue("remark", "imps.role.approve.remark.blank");
            } else if (!Utils.isAlphaNumericSpace(roleBean.getStrRemark())) {
                arg1.rejectValue("remark", "imps.role.invalid.remark");
            } else if (!Utils.isMinLenValid(roleBean.getStrRemark(),
                                            Integer.parseInt(env.getProperty("imps.role.remark.min.length")))) {
                arg1.rejectValue("remark", "imps.role.remark.min.length.invalid");
            } else if (!Utils.isMaxLenValid(roleBean.getStrRemark(),
                                            Integer.parseInt(env.getProperty("imps.role.remark.max.length")))) {
                arg1.rejectValue("remark", "imps.role.remark.max.length.invalid");
            }

            break;

        case TranecoStatusCode.REQTYPE_VIEW :
            if (Utils.isEmptyStr((roleBean.getIRoleID() == null)
                                 ? ""
                                 : String.valueOf(roleBean.getIRoleID()))) {
                arg1.rejectValue("roleID", "imps.role.roleId.blank");
            } else if (!Utils.isNumber((roleBean.getIRoleID() == null)
                                       ? ""
                                       : String.valueOf(roleBean.getIRoleID()))) {
                arg1.rejectValue("roleID", "imps.role.invalid.roleId");
            }

            break;

        default :
            break;
        }
    }
}

