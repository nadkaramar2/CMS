package com.TranEco.cardManagement.crypt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.TranEco.cardManagement.cryptservice.FileDecryptService;

@RestController
public class EncryptDecryptController
{
    
    @Autowired
    private FileDecryptService fileDecryptService;
    
    	//crated by ankit for decryption of the file to see the decrypted data
  	@RequestMapping(method = RequestMethod.POST, value = "decryptFile")
  	public ResponseEntity<?> decryptFileProcess(@RequestParam("file") MultipartFile file) 
  	{
  	        if (file == null || file.isEmpty()) 
  	        {
  	            return new ResponseEntity<>("Missing file", HttpStatus.BAD_REQUEST);
  	        }
  	        String decryptFile = fileDecryptService.decryptFile(file);
  	        return new ResponseEntity<>(decryptFile, HttpStatus.OK);
  	    }
}
