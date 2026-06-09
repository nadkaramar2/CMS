/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to define Store Procedure Response Code.
 * 
 *@History
 *===============================================================================================================================================
 *     @Version         @Date         	@Author                 @Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to define Store Procedure Response Code.
 *===============================================================================================================================================
 *
 */

package com.traneco.common;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionDTO implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String ipAddress;
	private String sessionID;	
	private String loginID;
	private String displayName;
	private Timestamp lastLoginDate;
	private int userID;
	private String participantid;
	private String participantDesc;
	private String roleID;
	private String emailID;
	private String mobileNo;
	private String customerID;
	private String sensitiveFlag;
	
	//Added by Prashant to identify application for nigeria client on 09-May-2023 Start
	private String applicationName;
	//Added by Prashant to identify application for nigeria client on 09-May-2023 End
}
