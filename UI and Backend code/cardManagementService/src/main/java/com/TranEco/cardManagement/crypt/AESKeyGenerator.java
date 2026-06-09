package com.TranEco.cardManagement.crypt;



import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

@Component
public class AESKeyGenerator 
{
	private static final int KEY_SIZE = 256;
    private static final int IV_SIZE = 12;

    public SecretKey generateKey() throws NoSuchAlgorithmException 
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(KEY_SIZE, SecureRandom.getInstanceStrong());
        return keyGenerator.generateKey();
    }

    public byte[] generateIV() 
    {
        byte[] iv = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        return iv;
    }
	
    public Map<String, String> exportKeys () throws Exception
	{
		Map<String,String> map = new HashMap<String,String>();
		SecretKey generateKey = generateKey();
		byte[] generateIV = generateIV();
		
		//encoded to base 64 iv and key
		String encodedKey = EncodeDecode.encode(generateKey.getEncoded());
		String encodedIV = EncodeDecode.encode(generateIV);
		System.err.println("SecretKey :"+ EncodeDecode.encode(generateKey.getEncoded()));
		System.err.println("IV :"+ EncodeDecode.encode(generateIV));
		map.put("key", encodedKey);
		map.put("iv", encodedIV);
		return map;
	}
    
}
