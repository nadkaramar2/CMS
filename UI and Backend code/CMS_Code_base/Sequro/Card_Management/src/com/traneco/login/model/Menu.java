/**
 *@author  mithape
 *@version 1.0
 *@purpose This Class Is Used To Define Menu Details.
 * 
 *@History
 *===============================================================================================================================================
 *     Version          Date            Author                  Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This Class Is Used To Define Menu Details.
 *===============================================================================================================================================
 *
 */

package com.traneco.login.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	private int menuID;
	private String parentSubMenuID;
	private String menuIcon;
	private String menuName;
	private String menuAction;
	
	public int getMenuID() {
		return menuID;
	}
	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}
	public String getParentSubMenuID() {
		return parentSubMenuID;
	}
	public void setParentSubMenuID(String parentSubMenuID) {
		this.parentSubMenuID = parentSubMenuID;
	}	
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuAction() {
		return menuAction;
	}
	public void setMenuAction(String menuAction) {
		this.menuAction = menuAction;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuAction == null) ? 0 : menuAction.hashCode());
		result = prime * result + menuID;
		result = prime * result + ((menuIcon == null) ? 0 : menuIcon.hashCode());
		result = prime * result + ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result + ((parentSubMenuID == null) ? 0 : parentSubMenuID.hashCode());
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
		Menu other = (Menu) obj;
		if (menuAction == null) {
			if (other.menuAction != null)
				return false;
		} else if (!menuAction.equals(other.menuAction))
			return false;
		if (menuID != other.menuID)
			return false;
		if (menuIcon == null) {
			if (other.menuIcon != null)
				return false;
		} else if (!menuIcon.equals(other.menuIcon))
			return false;
		if (menuName == null) {
			if (other.menuName != null)
				return false;
		} else if (!menuName.equals(other.menuName))
			return false;
		if (parentSubMenuID == null) {
			if (other.parentSubMenuID != null)
				return false;
		} else if (!parentSubMenuID.equals(other.parentSubMenuID))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Menu [menuID=");
		builder.append(menuID);
		builder.append(", parentSubMenuID=");
		builder.append(parentSubMenuID);
		builder.append(", menuIcon=");
		builder.append(menuIcon);
		builder.append(", menuName=");
		builder.append(menuName);
		builder.append(", menuAction=");
		builder.append(menuAction);
		builder.append("]");
		return builder.toString();
	}	
}
