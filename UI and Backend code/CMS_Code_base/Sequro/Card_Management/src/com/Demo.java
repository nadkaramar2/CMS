package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.traneco.common.util.AESEncDec;

@Component
public class Demo {

	private static AESEncDec aesEncDec;

    @Autowired
    public void setAesEncDec(AESEncDec aesEncDec) {
        Demo.aesEncDec = aesEncDec;
    }
	public static void main(String[] args) throws Exception {
		
		String plainCardNumber = "6544561025119398";
		
		String encCardNumber = aesEncDec.encrypt(plainCardNumber);
		System.out.println("Encrypted Card Number:::"+encCardNumber);
		
		String decryptCardNo = aesEncDec.decrypt(encCardNumber);
		System.out.println("Encrypted Card Number:::"+decryptCardNo);
	}
}
