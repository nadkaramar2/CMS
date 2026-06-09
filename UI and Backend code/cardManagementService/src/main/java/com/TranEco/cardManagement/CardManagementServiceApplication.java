package com.TranEco.cardManagement;

import java.io.DataOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardManagementServiceApplication 
{
	@Autowired
	DataOutputStream txnSocketConnectionOut;
	
	public static void main(String[] args) throws Exception 
	{
        SpringApplication.run(CardManagementServiceApplication.class, args);
    }	

}
