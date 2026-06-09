package com.TranEco.cardManagement.crypt.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public class EncryptData implements Serializable{

	private static final long serialVersionUID = 1L;
	private String iv;
	private String key;                          // actual key used for encryption 
	private String encryptionData;				// actual data which needs to be encrypted
	private String encryptedData;
	private String decryptedData;
	private String publicKey;
	private String privateKey;
	private MultipartFile file;
	
	
	
}
