/**
*@author  mithape
*@version 1.0
*@purpose This Class Is Used To Define Sub Parent List.
* 
*@History
*===============================================================================================================================================
*     Version          Date            Author                  Purpose	
*===============================================================================================================================================
*     1.0        		15-01-18       	Mayur I                This Class Is Used To Define Sub Parent List.
*===============================================================================================================================================
*
*/


package com.traneco.login.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class SubParentMenu implements Serializable 
{
	
	private static final long serialVersionUID = 1L;
	private int parentSubMenuID;
	private int parentMenuID;
	private String subMenuName;
	
	public int getParentSubMenuID() 
	{
		return parentSubMenuID;
	}
	public void setParentSubMenuID(int parentSubMenuID) 
	{
		this.parentSubMenuID = parentSubMenuID;
	}
	public int getParentMenuID() 
	{
		return parentMenuID;
	}
	public void setParentMenuID(int parentMenuID) 
	{
		this.parentMenuID = parentMenuID;
	}
	public String getSubMenuName() 
	{
		return subMenuName;
	}
	public void setSubMenuName(String subMenuName) 
	{
		this.subMenuName = subMenuName;
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + parentMenuID;
		result = prime * result + parentSubMenuID;
		result = prime * result + ((subMenuName == null) ? 0 : subMenuName.hashCode());
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
		SubParentMenu other = (SubParentMenu) obj;
		if (parentMenuID != other.parentMenuID)
			return false;
		if (parentSubMenuID != other.parentSubMenuID)
			return false;
		if (subMenuName == null) {
			if (other.subMenuName != null)
				return false;
		} else if (!subMenuName.equals(other.subMenuName))
			return false;
		return true;
	}
	
	@Override
	public String toString() 
	{
		StringBuilder builder = new StringBuilder();
		builder.append("SubParentMenu [parentSubMenuID=");
		builder.append(parentSubMenuID);
		builder.append(", parentMenuID=");
		builder.append(parentMenuID);
		builder.append(", subMenuName=");
		builder.append(subMenuName);
		builder.append("]");
		return builder.toString();
	}	
}
