/**
 *@author  mithape
 *@version 1.0
 *@purpose This Class Is Used To Define Parent Menu Details.
 * 
 *@History
 *===============================================================================================================================================
 *     Version          Date            Author                  Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This Class Is Used To Define Parent Menu Details.
 *===============================================================================================================================================
 *
 */

package com.traneco.login.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class ParentMenu implements Serializable {

	private static final long serialVersionUID = 1L;
	private int parentMenuID;
	private String parentMenuName;
	private String menuIcon;

	public int getParentMenuID() {
		return parentMenuID;
	}
	public void setParentMenuID(int parentMenuID) {
		this.parentMenuID = parentMenuID;
	}
	public String getParentMenuName() {
		return parentMenuName;
	}
	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuIcon == null) ? 0 : menuIcon.hashCode());
		result = prime * result + parentMenuID;
		result = prime * result + ((parentMenuName == null) ? 0 : parentMenuName.hashCode());
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
		ParentMenu other = (ParentMenu) obj;
		if (menuIcon == null) {
			if (other.menuIcon != null)
				return false;
		} else if (!menuIcon.equals(other.menuIcon))
			return false;
		if (parentMenuID != other.parentMenuID)
			return false;
		if (parentMenuName == null) {
			if (other.parentMenuName != null)
				return false;
		} else if (!parentMenuName.equals(other.parentMenuName))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParentMenu [parentMenuID=");
		builder.append(parentMenuID);
		builder.append(", parentMenuName=");
		builder.append(parentMenuName);
		builder.append(", menuIcon=");
		builder.append(menuIcon);
		builder.append("]");
		return builder.toString();
	}				
}
