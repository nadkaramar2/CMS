package com;

import com.traneco.common.util.AESEncDec;

public class PasswordEncDec 
{
	public static void main(String[] args) throws Exception 
	{
		AESEncDec aesEncDec = new AESEncDec();
		String cardNumber = "6666661068527275";
		String encrypt = "gveovO+mjtn1PJDWbgnNxSC7GxNuFwgHIF4aNbosHuk=:A271D06ECC301498CA3E68D0B021A187";
		System.out.println("Encrypt Card Number:::"+encrypt);
		
		String decrypt = aesEncDec.decrypt(encrypt.toString()); 
		System.out.println("Decrypt Card Number::::"+decrypt);
	}
	
	
	public String getSecretKeys()
	{
		String key = "eDFl?f&UKEZ6eK!s1NJ!ZkZGH5oxHQ31";
		return key;
		
	}
}
