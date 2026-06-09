/**
 *@author  mithape
 *@version 1.0
 *@purpose This class is used to define logger levels.
 * 
 *@History
 *===============================================================================================================================================
 *     @Version         @Date         	@Author                 @Purpose	
 *===============================================================================================================================================
 *     1.0        		15-01-18       	Mayur I                	This class is used to define logger levels.
 *===============================================================================================================================================
 *
 */

package com.traneco.common.util.logger;

public interface Constants {

	String EMPTY = "";
	String NULL = null;

	public static final int LTW = 1;
	public static final int LTI = 2;
	public static final int LTE = 3;
	public static final int LTD = 4;

	public static final String ZPK = "ZPK";
	public static final String ZPK_CV = "ZPK_CV";
	public static final String ZMK = "ZMK";
	public static final String ZMK_CV = "ZMK_CV";
	public static final String MAC_KEY = "MAC_KEY";
	public static final String MAC_CV = "MAC_CV";
	public static final String MAC_FIELDS = "MAC_FIELDS";

	public static final String CVK = "CVK";
	public static final String PVK = "PVK";
	public static final String MDK = "MDK";
	public static final String SEC_KEY = "SEC_KEY";

}
