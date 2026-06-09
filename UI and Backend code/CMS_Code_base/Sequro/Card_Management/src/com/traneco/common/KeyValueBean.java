
/**
 * @author  mithape
 * @version 1.0
 * @purpose This class is used to define Store Procedure Response Code.
 *
 * @History
 * ===============================================================================================================================================
 *     @Version         @Date           @Author                 @Purpose
 * ===============================================================================================================================================
 *     1.0                      15-01-18        Mayur I                 This class is used to define Store Procedure Response Code.
 * ===============================================================================================================================================
 *
 */
package com.traneco.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class KeyValueBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String            lkpkey;
    private String            lkpvalue;
    private String 			  resp;
}

