
package com.traneco.role.model;

import lombok.Data;

@Data
public class MenuBean {
    private int    menuId;
    private int    parentMenuId;
    private int    parentSubMenuId;
    private String menuName;
    private String checked;
}

