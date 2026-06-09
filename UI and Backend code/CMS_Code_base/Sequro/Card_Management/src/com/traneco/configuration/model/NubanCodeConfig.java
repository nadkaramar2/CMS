package com.traneco.configuration.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class NubanCodeConfig implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int strId;
    private String strNubanType;
    private String strNubanCode;
    private String strNubanSerialNo;
    private String strParticipantId;
    private Date strCreatedDate;
    
    private String strDMBNumber1;
    private String strDMBNumber2;
    
    private String strOFINumber1;
    private String strOFINumber2;
    
    private String strNubanTypeDescription;
    
}
