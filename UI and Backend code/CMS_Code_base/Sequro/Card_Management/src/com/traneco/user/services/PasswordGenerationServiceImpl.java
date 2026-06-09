package com.traneco.user.services;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.traneco.common.util.Utils;
import com.traneco.user.dao.UserDao;

@Service
public class PasswordGenerationServiceImpl implements PasswordGenerationService
{
	@Autowired
    Environment env;
	
	@Autowired
	UserDao userDao;
	
	@Override
	public String getEncryptedPassword() 
	{
		try 
		{
			String plainPassword = Utils.getRandomString(8);
			return plainPassword;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String generateSecurePassword(String plainPassword, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
	    SecureRandom random = new SecureRandom();
	    byte[] saltBytes = salt.getBytes();
	    random.nextBytes(saltBytes);

	    KeySpec spec = new PBEKeySpec(plainPassword.toCharArray(), saltBytes, 65536, 128);
	    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    byte[] hash = factory.generateSecret(spec).getEncoded();

	    String returnValue = Base64.getEncoder().encodeToString(hash);
	    System.out.println(returnValue);
	    String returnValues = Base64.getDecoder().decode(returnValue).toString();
	    System.out.println(returnValues);
	    return returnValue;
	}
	

	

	
	
}
