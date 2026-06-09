/**
*@author  mithape
*@version 1.0
*@purpose This Class Is Used To Define User Access Permission.
* 
*@History
*===============================================================================================================================================
*     Version          Date            Author                  Purpose	
*===============================================================================================================================================
*     1.0        		15-01-18       	Mayur I                This Class Is Used To Define User Access Permission.
*===============================================================================================================================================
*
*/

package com.traneco.login.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Permission implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Permission other = (Permission) obj;
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
		builder.append("Permission [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}		
}
