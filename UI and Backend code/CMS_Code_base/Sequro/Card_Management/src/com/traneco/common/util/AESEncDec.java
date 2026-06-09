package com.traneco.common.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.traneco.keyEncrypt.model.EncryptKeyModel;
import com.traneco.keyEncrypt.service.EncryptKeyDataService;

@Component
public class AESEncDec 
{
	@Autowired
	EncryptKeyDataService encryptKeyDataService;
	
	@Autowired
	Environment env;
	
	private final String ALG="AES";
	private final String CIPHER="AES/CBC/PKCS5PADDING";
	private final String UTF_8 = "UTF-8";
	private final String AES = "256";
	

	public String getSecretKeys() 
	  {	
		 try 
		 {
		  EncryptKeyModel encryptKeyModel = encryptKeyDataService.getEncDecValue();
		  File file = new File(env.getProperty("EncDeckeystoreFile"));
		  try (BufferedReader br = new BufferedReader(new FileReader(file))) 
		  {
		      StringBuilder sb = new StringBuilder();
		      String line;
		      while ((line = br.readLine()) != null) 
		      {
		          sb.append(line);
		      }
		      String fileAsString = sb.toString();
		      String key = fileAsString.replace("\n", "").replace("\r", "");
		      String generateKey = key.concat(encryptKeyModel.getValue().trim());
		      return generateKey;
		  } 
		  catch (IOException e) 
		  {
		      e.printStackTrace();
		  }
		 }catch (Exception e) 
		 {
			e.printStackTrace();
		}
		return null; 
	  }
	
	
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
    
    public String getSecretKeyss()
	{
		String key = "eDFl?f&UKEZ6eK!s1NJ!ZkZGH5oxHQ31";
		return key;
		
	}
    
    public static String generateHexIV() 
	{
	    SecureRandom random = new SecureRandom();
	    byte[] ivBytes = new byte[16];
	    random.nextBytes(ivBytes);
	    return bytesToHex(ivBytes);
	}
	
	
	private static String bytesToHex(byte[] bytes) 
	{
	    StringBuilder result = new StringBuilder();
	    for (byte aByte : bytes) {
	        result.append(String.format("%02X", aByte));
	    }
	    return result.toString();
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
    
    
	
	
}
