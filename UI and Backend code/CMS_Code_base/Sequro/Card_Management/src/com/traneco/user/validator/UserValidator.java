package com.traneco.user.validator;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.util.Utils;
import com.traneco.user.model.UserBean;

@Component
public class UserValidator implements Validator 
{
    @Autowired
    Environment env;

    @Override
    public boolean supports(Class<?> arg0) 
    {
        return UserBean.class.isAssignableFrom(arg0);
    }

    @Override
    public void validate(Object target, Errors error) 
    {
        UserBean userBean = (UserBean) target;

        switch (userBean.getStrRequestID())
        {
        case TranecoStatusCode.REQTYPE_ADD :
            if (Utils.isEmptyStr(userBean.getStrUserName())) 
            {
                error.rejectValue("strUserName", "imps.addUserForm.empty");
            } 
            else if (!Utils.isAlphaNumeric(userBean.getStrUserName())) 
            {
                error.rejectValue("strUserName", "imps.addUserForm.valid.alphaNumeric");
            }
            else if (userBean.getStrUserName().length()
                       < Integer.parseInt(env.getProperty("imps.addUserForm.userID.min"))) {
                error.rejectValue("strUserName",
                                  "strUserName.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.userID.min")));
            } else if (userBean.getStrUserName().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.userID.max"))) {
                error.rejectValue("strUserName",
                                  "strUserName.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.userID.max")));
            }

            if (Utils.isEmptyStr(userBean.getStrFirstName())) 
            {
                error.rejectValue("strFirstName", "imps.addUserForm.empty");
            }
            else if (!Utils.isAlphaNumeric(userBean.getStrFirstName())) 
            {
                error.rejectValue("strFirstName", "imps.addUserForm.valid.alphaNumeric");
            } 
            else if (userBean.getStrFirstName().length() < Integer.parseInt(env.getProperty("imps.addUserForm.firstName.min"))) {
                error.rejectValue("strFirstName",
                                  "strFirstName.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.firstName.min")));
            } else if (userBean.getStrFirstName().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.firstName.max"))) {
                error.rejectValue("strFirstName",
                                  "strFirstName.max",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.firstName.max")));
            }

            if (Utils.isEmptyStr(userBean.getStrLastName())) {
                error.rejectValue("strLastName", "imps.addUserForm.empty");
            } else if (!Utils.isAlphaNumeric(userBean.getStrLastName())) {
                error.rejectValue("strLastName", "imps.addUserForm.valid.alphaNumeric");
            } else if (userBean.getStrLastName().length()
                       < Integer.parseInt(env.getProperty("imps.addUserForm.lastName.min"))) {
                error.rejectValue("strLastName",
                                  "strLastName.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.lastName.min")));
            } else if (userBean.getStrLastName().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.lastName.max"))) {
                error.rejectValue("strLastName",
                                  "strLastName.max",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.lastName.max")));
            }

            if (Utils.isEmptyStr(userBean.getStrMobileNo())) {
                error.rejectValue("strMobileNo", "imps.addUserForm.empty");
            } else if (!Utils.isNumber(userBean.getStrMobileNo())) {
                error.rejectValue("strMobileNo", "imps.addUserForm.valid.numeric");
            } else if (userBean.getStrMobileNo().length()
                       < Integer.parseInt(env.getProperty("imps.addUserForm.mobileNo.min"))) {
                error.rejectValue("strMobileNo",
                                  "strMobileNo.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.mobileNo.min")));
            } else if (userBean.getStrMobileNo().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.mobileNo.max"))) {
                error.rejectValue("strMobileNo",
                                  "strMobileNo.max",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.mobileNo.max")));
            }

            if (Utils.isEmptyStr(userBean.getStrEmailID())) {
                error.rejectValue("strEmailID", "imps.addUserForm.empty");
            } else if (!Utils.isValidEmail(userBean.getStrEmailID())) 
            {
                error.rejectValue("strEmailID", "imps.addUserForm.valid.email");
            }
            else if (userBean.getStrEmailID().length()
                       < Integer.parseInt(env.getProperty("imps.addUserForm.emailID.min"))) {
                error.rejectValue("strEmailID",
                                  "strEmailID.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.emailID.min")));
            }
            else if (userBean.getStrEmailID().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.emailID.max"))) 
            {
                error.rejectValue("strEmailID",
                                  "strEmailID.max",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.emailID.max")));
            }

            if (Utils.isEmptyStr(userBean.getStrSecQueAns())) 
            {
                error.rejectValue("strSecQueAns", "imps.addUserForm.empty");
            } else if (!Utils.isAlphaNumeric(userBean.getStrSecQueAns())) 
            {
                error.rejectValue("strSecQueAns", "imps.addUserForm.valid.alphaNumeric");
            } else if (userBean.getStrSecQueAns().length()
                       < Integer.parseInt(env.getProperty("imps.addUserForm.secQueAns.min"))) 
            {
                error.rejectValue("strSecQueAns",
                                  "strSecQueAns.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.secQueAns.min")));
            } else if (userBean.getStrSecQueAns().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.secQueAns.max"))) {
                error.rejectValue("strSecQueAns",
                                  "strSecQueAns.max",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.secQueAns.max")));
            }

            if ("NONE".equals(userBean.getStrRoleID())) {
                error.rejectValue("strRoleID", "imps.addUserForm.empty");
            }

            if ("NONE".equals(userBean.getStrSecQueID())) {
                error.rejectValue("strSecQueAns", "imps.addUserForm.empty");
            }

            break;

        case TranecoStatusCode.REQTYPE_EDIT :
            if (Utils.isEmptyStr(userBean.getStrUserName())) {
                error.rejectValue("strUserName", "imps.addUserForm.empty");
            } else if (!Utils.isAlphaNumeric(userBean.getStrUserName())) {
                error.rejectValue("strUserName", "imps.addUserForm.valid.alphaNumeric");
            } else if (userBean.getStrUserName().length()
                       < Integer.parseInt(env.getProperty("imps.addUserForm.userID.min"))) {
                error.rejectValue("strUserName",
                                  "userName.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.userID.min")));
            } else if (userBean.getStrUserName().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.userID.max"))) {
                error.rejectValue("strUserName",
                                  "strUserName.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.userID.max")));
            }

            if (Utils.isEmptyStr(userBean.getStrFirstName())) {
                error.rejectValue("strFirstName", "imps.addUserForm.empty");
            } else if (!Utils.isAlphaNumeric(userBean.getStrFirstName())) {
                error.rejectValue("strFirstName", "imps.addUserForm.valid.alphaNumeric");
            } else if (userBean.getStrFirstName().length()
                       < Integer.parseInt(env.getProperty("imps.addUserForm.firstName.min"))) {
                error.rejectValue("strFirstName",
                                  "strFirstName.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.firstName.min")));
            } else if (userBean.getStrFirstName().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.firstName.max"))) {
                error.rejectValue("strFirstName",
                                  "strFirstName.max",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.firstName.max")));
            }

            if (Utils.isEmptyStr(userBean.getStrLastName())) {
                error.rejectValue("strLastName", "imps.addUserForm.empty");
            } else if (!Utils.isAlphaNumeric(userBean.getStrLastName())) {
                error.rejectValue("strLastName", "imps.addUserForm.valid.alphaNumeric");
            } else if (userBean.getStrLastName().length()
                       < Integer.parseInt(env.getProperty("imps.addUserForm.lastName.min"))) {
                error.rejectValue("strLastName",
                                  "strLastName.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.lastName.min")));
            } else if (userBean.getStrLastName().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.lastName.max"))) {
                error.rejectValue("strLastName",
                                  "strLastName.max",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.lastName.max")));
            }

            if (Utils.isEmptyStr(userBean.getStrMobileNo())) {
                error.rejectValue("strMobileNo", "imps.addUserForm.empty");
            } else if (!Utils.isNumber(userBean.getStrMobileNo())) {
                error.rejectValue("strMobileNo", "imps.addUserForm.valid.numeric");
            } else if (userBean.getStrMobileNo().length()
                       < Integer.parseInt(env.getProperty("imps.addUserForm.mobileNo.min"))) {
                error.rejectValue("strMobileNo",
                                  "strMobileNo.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.mobileNo.min")));
            } else if (userBean.getStrMobileNo().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.mobileNo.max"))) {
                error.rejectValue("strMobileNo",
                                  "strMobileNo.max",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.mobileNo.max")));
            }

            if (Utils.isEmptyStr(userBean.getStrEmailID())) {
                error.rejectValue("strEmailID", "imps.addUserForm.empty");
            } else if (!Utils.isValidEmail(userBean.getStrEmailID())) {
                error.rejectValue("strEmailID", "imps.addUserForm.valid.email");
            } else if (userBean.getStrEmailID().length()
                       < Integer.parseInt(env.getProperty("imps.addUserForm.emailID.min"))) {
                error.rejectValue("strEmailID",
                                  "strEmailID.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.emailID.min")));
            } else if (userBean.getStrEmailID().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.emailID.max"))) {
                error.rejectValue("strEmailID",
                                  "strEmailID.max",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.emailID.max")));
            }

            if (Utils.isEmptyStr(userBean.getStrSecQueAns())) {
                error.rejectValue("strSecQueAns", "imps.addUserForm.empty");
            } else if (!Utils.isAlphaNumericSpace(userBean.getStrSecQueAns())) {
                error.rejectValue("strSecQueAns", "imps.addUserForm.valid.alphaNumeric");
            } else if (userBean.getStrSecQueAns().length()
                       < Integer.parseInt(env.getProperty("imps.addUserForm.secQueAns.min"))) {
                error.rejectValue("strSecQueAns",
                                  "strSecQueAns.min",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.min"),
                                                       env.getProperty("imps.addUserForm.secQueAns.min")));
            } else if (userBean.getStrSecQueAns().length()
                       > Integer.parseInt(env.getProperty("imps.addUserForm.secQueAns.max"))) {
                error.rejectValue("strSecQueAns",
                                  "strSecQueAns.max",
                                  MessageFormat.format(env.getProperty("imps.addUserForm.max"),
                                                       env.getProperty("imps.addUserForm.secQueAns.max")));
            }

            if ("NONE".equals(userBean.getStrRoleID())) 
            {
                error.rejectValue("strRoleID", "imps.addUserForm.empty");
            }

            if ("NONE".equals(userBean.getStrSecQueID())) {
                error.rejectValue("strRoleID", "imps.addUserForm.empty");
            }

            break;

        case TranecoStatusCode.REQTYPE_APPROVE :
            if (userBean.getStrRequestData().length <= 0) 
            {
                error.rejectValue("strRequestID", "imps.Valid.instApproveForm.isEmptyList");
            }
            break;
        }
    }
}

