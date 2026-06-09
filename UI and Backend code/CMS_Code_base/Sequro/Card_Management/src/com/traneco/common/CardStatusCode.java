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

public class CardStatusCode implements Serializable 
{
	private static final long serialVersionUID = 1L;
	/* SP Return Status Codes */
	public static final String STATUS_SUCCESS = "00";
	public static final String STATUS_SQL_EXCEPTION = "01";
	public static final String STATUS_DUPLICATE = "02";

	/* SP In Parameter Action Codes */
	public static final String REQTYPE_ADD = "ADD";
	public static final String REQTYPE_EDIT = "EDIT";
	public static final String REQTYPE_VIEW = "VIEW";
	public static final String REQTYPE_APPROVE = "APPROVE";
	public static final String REQTYPE_EDIT_SEARCH = "EDIT_SEARCH";
	public static final String REQTYPE_SHOW_ROLE = "SHOW_ROLE";

}
