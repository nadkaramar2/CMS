
package com.traneco.role.model;

import lombok.Data;

@Data
public class ParentSubMenuBean {
	private int parentSubMenuId;
	private int parentMenuId;
	private String subMenuName;
	private String checked;
}
