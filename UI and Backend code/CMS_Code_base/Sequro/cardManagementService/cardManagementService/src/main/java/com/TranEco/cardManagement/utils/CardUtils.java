package com.TranEco.cardManagement.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.TranEco.cardManagement.crypt.model.EncryptKeyModel;
import com.TranEco.cardManagement.cryptservice.EncryptKeyDataService;

@Component
public class CardUtils 
{
	
	@Autowired
	EncryptKeyDataService encryptKeyDataService;
	
	@Autowired
	Environment env;
	
	private final String ALG= "AES";
	private final String CIPHER= "AES/CBC/PKCS5PADDING";
	private final String UTF_8 = "UTF-8";
	
	public String getSecretKeys() 
	  {	
		 try 
		 {
		    EncryptKeyModel encryptKeyModel = encryptKeyDataService.getEncDecValue();
		    //BufferedReader br = new BufferedReader(new FileReader("/usr/local/tomcat/paths/key.txt"));
		   // BufferedReader br = new BufferedReader(new FileReader("key.txt"));
		    BufferedReader br = new BufferedReader(new FileReader("D:\\Sequro\\winservice\\cardManagement\\cms\\key.txt"));
			StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null)
	        {
	        	sb.append(line).append("");
	        	line = br.readLine();
	        }
	        String fileAsString = sb.toString();
	        String key = fileAsString.replace("\n", "").replace("\r", "");
	        String generateKey = key.concat(encryptKeyModel.getValue().trim());
		  return generateKey;
		 }
		 catch (Exception e) 
		 {
			e.printStackTrace();
		}
		return null;
	  }
	
	
	/*
	  public String encrypt(String value) throws Exception
		{
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(getSecretKeys().getBytes(UTF_8), ALG);
	 
			Cipher cipher = Cipher.getInstance(CIPHER);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	 
			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64.getEncoder().encodeToString(encrypted);
			
		}
		*/
		
		
	/*
		public String decrypt(String encrypted) throws Exception
		{
			IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(UTF_8));
			SecretKeySpec skeySpec = new SecretKeySpec(getSecretKeys().getBytes(UTF_8), ALG);
	 
			Cipher cipher = Cipher.getInstance(CIPHER);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
	 
			return new String(original);
		}
		
		*/
	
	public String encrypt(String value) throws Exception 
	{
        // Generate a new IV for each encryption
        IvParameterSpec iv = new IvParameterSpec(hexToBytes(generateHexIV()));
        SecretKeySpec skeySpec = new SecretKeySpec(getSecretKeys().getBytes(UTF_8), ALG);
        Cipher cipher = Cipher.getInstance(CIPHER);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(value.getBytes());
        // Combine encrypted data and IV for later extraction during decryption
        return Base64.getEncoder().encodeToString(encrypted) + ":" + bytesToHex(iv.getIV());
    }
 
    public String decrypt(String encrypted) throws Exception 
    {
        // Split the input into encrypted data and IV
        String[] parts = encrypted.split(":");
        IvParameterSpec iv = new IvParameterSpec(hexToBytes(parts[1]));
        SecretKeySpec skeySpec = new SecretKeySpec(getSecretKeys().getBytes(UTF_8), ALG);
        Cipher cipher = Cipher.getInstance(CIPHER);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(Base64.getDecoder().decode(parts[0]));
        return new String(original);
    }
		
		private static byte[] hexToBytes(String hex) 
		{
	        int len = hex.length();
	        byte[] bytes = new byte[len / 2];
	        for (int i = 0; i < len; i += 2) {
	            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
	        }
	        return bytes;
	    }
	
	public static String generateHexIV() 
	{
	    SecureRandom random = new SecureRandom();
	    byte[] ivBytes = new byte[16];
	    random.nextBytes(ivBytes);
	    return bytesToHex(ivBytes);
	}
	
	private static String bytesToHex(byte[] bytes) {
	    StringBuilder result = new StringBuilder();
	    for (byte aByte : bytes) {
	        result.append(String.format("%02X", aByte));
	    }
	    return result.toString();
	}
	
	public static String calculateExpDate() 
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 5); 
		Date nextYear = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	    String strDate= formatter.format(nextYear);
		return strDate;
	}
	
	public static String calculateExpDatebyYear(int iYear) 
	{
		  Calendar cal = Calendar.getInstance();
		  cal.add(Calendar.YEAR, iYear); 
		  Date nextYear = cal.getTime();
		  //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		  String strDate= formatter.format(nextYear);
		  return strDate;
	}
		 
		 public static String calculateExpDatebyMonth(int iMonth) 
		 {
		  Calendar cal = Calendar.getInstance();
		  cal.add(Calendar.MONTH, iMonth); 
		  Date nextYear = cal.getTime();
		  //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		  String strDate= formatter.format(nextYear);
		  return strDate;
		 }

	public static String getCurrentDate() 
	{
		Date date = new Date();  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
	    String strDate= formatter.format(date);  
	    return strDate;
	}
	
	public static String getPinOffset() 
	{
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		return String.valueOf(n);
	}
	
	public static String maskNumber(String value) 
	{
		   if(value != null && !value.contains("*"))
	        {
	            StringBuffer maskNum = new StringBuffer();
	            maskNum.append(value.trim());
	            int size = maskNum.length();
	           
	                if(size > 10)
	                {
	                    int inx = 6;
	                    for(int end = inx + (size - 10); inx < end; inx++)
	                        maskNum.setCharAt(inx, '*');
	                }
	                value = maskNum.toString();
	        }
	        return value;
	   }
	
	public static String tokenCard() 
	{
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Timestamp timestamp = new Timestamp(System.nanoTime());
		return sdf.format(timestamp);
	}
	
	
	public static String generateCardToken(int length) 
	{
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
	public static String hashing(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	public static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
	
	
}
