package com.traneco.service.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BulkTransfer
{
	private int strId;
	
    private String strAccountType;
    private MultipartFile file;
    private List<AccountCreation> accountCreationList;
    
    private String strFromAccountType;
    private String strFromAccountNo;
    
    private String strToAccountType;
    private String strToAccountNo;
    private Double strTransactionAmount;
    private String strMessage;
    
    
    private String strTransactionId;
    private Date strDate;
    private Time strTime;
    private Double strAmount;
    private String strInterfaceReference;
    private String strBulkMode;
    
    private String strBulkData;
    private List<com.traneco.service.model.BulkTransfer> bulkTransferToAccounts;
    
    private List<com.traneco.service.model.BulkTransfer> bulkTransferFromAccounts;
    
    private String strTransferAmount;
    private String accountNo;
	private String accountType;
	private String amount;
	
	private String message;
	private String code;
	private String strStatus;
    
	
	private String strToAccountNumber;
	
	
	private Date strBulkRequestDate;
	private Time strBulkRequestTime;
	private String strBulkResponseDate;
	private String strBulkResponseTime;
	private String strMakerId;
	private String strCheckerId;
	private String allJsonList;
	
	
	private String strIsVerified;
	
	//added for GL
    private String strFromGLAccountNo;
    private String strFromGLAccountDescription;
    private String strFromGLAccountType;

    private String strToGLAccountNo;
    private String strGLAccountDescription;
    private String strToGLAccountType;
    //added for GL
    
    private String strAccountNumber;
	
	
	
	
	
	
    
    
}
