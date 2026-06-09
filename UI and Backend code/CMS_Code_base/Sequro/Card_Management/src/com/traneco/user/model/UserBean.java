package com.traneco.user.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Component
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class UserBean {
	private int strId;
    private String   strUserID;
    private String   strParticipantID;
    private String   strUserAccessType;
    private String   strFirstName;
    private String   strLastName;
    private String   strUserName;
    private String   strMobileNo;
    private String   strEmailID;
    private String   strSecQueID;
    private String   strSecQueAns;
    private String   strPassword;
    private String   strUserStatusID;
    private String   strIsActive;
    private String   strSensitiveFlag;
    private String   strRoleID;
    private String   strCreatedBy;
    private String[] strRequestData;
    private String   strRequestID;
    private String   strUserTempID;
    private String   strRequestBtn;
    private String   strRemark;
    private int		 iEnforcePasswordChange;
    private String   strGroupID;
    
    //added by prashant T 25Oct2023
    private String isPasswordReset;
    private String plainPassword;
    private int strSensitiveDate;
    
    private int userRoleId;
    private int approvalStatusId;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastSuccessfulLogon;
    private int forgotPasswordValidationFailedAttempt;
    
    private Date lastPasswordChange;
    
    private int strUserTypeId;
    
    private int loginFailedAttemptsCount;
    private String strAction;
    private String userFullName;
    private String responseCode;
    private String status;
    private String message;
}

