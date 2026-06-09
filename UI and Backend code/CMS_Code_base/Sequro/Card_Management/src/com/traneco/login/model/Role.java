/**
*@author  mithape
*@version 1.0
*@purpose This Class Is Used To User Role Name and Actions.
* 
*@History
*===============================================================================================================================================
*     Version          Date            Author                  Purpose	
*===============================================================================================================================================
*     1.0        		15-01-18       	Mayur I                This Class Is Used To User Role Name and Actions.
*===============================================================================================================================================
*
*/

package com.traneco.login.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class Role implements GrantedAuthority, Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String action;
	private List<Permission> Permissions;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Permission> getPermissions() {
		return Permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		Permissions = permissions;
	}
	public String getAuthority() {
		return name;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Permissions == null) ? 0 : Permissions.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (Permissions == null) {
			if (other.Permissions != null)
				return false;
		} else if (!Permissions.equals(other.Permissions))
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [name=");
		builder.append(name);
		builder.append(", action=");
		builder.append(action);
		builder.append(", Permissions=");
		builder.append(Permissions);
		builder.append("]");
		return builder.toString();
	}
}
