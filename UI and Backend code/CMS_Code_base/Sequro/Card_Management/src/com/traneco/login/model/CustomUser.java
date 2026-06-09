/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to define user login details.
 * 
 *@History
 *===============================================================================================================================================
 *     Version          Date            Author                  Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to define user login details.
 *===============================================================================================================================================
 *
 */

package com.traneco.login.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CustomUser implements UserDetails, Serializable {
	
    private static final long serialVersionUID = 1L;
    private int userid;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private boolean loginFlag = false;
    private List<Map<String, Object>> listRole;
    private String participantid;
    private String participantDesc;
    private List<Role> authorities;
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;
    private String mobileNo;
    private int loginfailedattemptscount;
    private String lastpasswordchangedatetime;
    private int roleID;
    private String last_successful_logon;
    private String createddate;
    private String rolename;
    private String sensitive_date;
    private String groupID;
    
    private String resetPasswordFlag;
	
    private String user_id;
	private String user_name;
	private int password_attempts_count;
	private String old_password;
	private String new_password;
	private Date last_password_changed_date;
	
	private String isPasswordReset;
	
	private String userStatusFlag;
    private Date loginDateTime;
	
    public List<Role> getAuthorities()
    {
	return authorities;
    }

    public void setAuthorities(List<Role> authorities)
    {
	this.authorities = authorities;
    }

    public List<Map<String, Object>> getListRole()
    {
	return listRole;
    }

    public void setListRole(List<Map<String, Object>> listRole)
    {
	this.listRole = listRole;
    }
	
	@Override
	public String toString() {
		return "CustomUser [userid=" + userid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + ", loginFlag=" + loginFlag
				+ ", listRole=" + listRole + ", participantid=" + participantid + ", participantDesc=" + participantDesc
				+ ", authorities=" + authorities + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked="
				+ accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired + ", enabled=" + enabled
				+ ", mobileNo=" + mobileNo + ", loginfailedattemptscount=" + loginfailedattemptscount
				+ ", lastpasswordchangedatetime=" + lastpasswordchangedatetime + ", roleID=" + roleID
				+ ", last_successful_logon=" + last_successful_logon + ", createddate=" + createddate + ", rolename="
				+ rolename + ", sensitive_date=" + sensitive_date + ", groupID=" + groupID + ", resetPasswordFlag="
				+ resetPasswordFlag + "]";
	}

	// Equals method for checking the User is present in session registry or not
	// checking only 5 variables
	@Override
	public boolean equals(Object obj)
	{
	    if (obj == null)
	    {
		return false;
	    }
	    if (this == obj)
	    {
		return true;
	    }
	    if (!(obj instanceof CustomUser))
	    {
		return false;
	    }
	    CustomUser other = (CustomUser) obj;
	    return Objects.equals(this.username, other.username) && Objects.equals(
		    this.password, other.password
	    ) && Objects.equals(this.email, other.email) && Objects.equals(this.firstName, other.firstName)
		    && Objects.equals(this.lastName, other.lastName) && Objects.equals(this.role, other.role);
	}

}
