package com.TranEco.cardManagement.crypt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.TranEco.cardManagement.crypt.model.EncryptData;

@Component
public class RSAKeyGenerator 
{
	private PrivateKey privateKey;
	private PublicKey publicKey;
	private int keySize = 2048;
	
	
	public Map<String,String> exportKeys()
	{
		Map<String, String> map = new HashMap<>();
		try
		{
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(keySize);
			KeyPair pair = generator.generateKeyPair();
			privateKey = pair.getPrivate();
			publicKey = pair.getPublic();
			
			//encoded format of keys [represented as bytes]
			byte[] encodedPublicKey = publicKey.getEncoded();
			byte[] encodedPrivateKey = privateKey.getEncoded();
			
			
			//convert the encoded key to String [representation as single String]
			String privateKeyString = EncodeDecode.encode(encodedPrivateKey);
			String publicKeyString = EncodeDecode.encode(encodedPublicKey);
			
			//sending keys as response
			map.put("privateKey", privateKeyString);
			map.put("publicKey", publicKeyString);
			
		}
		catch(Exception e)
		{
			map.put("privateKey", null);
			map.put("publicKey", null);
			map.put("message", e.getLocalizedMessage());
			e.printStackTrace();
			System.err.print(e.getLocalizedMessage());
		}
		return map;
	}
	
	public EncryptData generateKeys()
	{
		EncryptData encryptData = new EncryptData();
		try
		{
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(keySize);
			KeyPair pair = generator.generateKeyPair();
			privateKey = pair.getPrivate();
			publicKey = pair.getPublic();
			
			//encoded format of keys [represented as bytes]
			byte[] encodedPublicKey = publicKey.getEncoded();
			byte[] encodedPrivateKey = privateKey.getEncoded();
			
			
			//convert the encoded key to String [representation as single String]
			String privateKeyString = EncodeDecode.encode(encodedPrivateKey);
			String publicKeyString = EncodeDecode.encode(encodedPublicKey);
			
			
			encryptData.setPublicKey(publicKeyString);
			encryptData.setPrivateKey(privateKeyString);
			
		}
		catch(Exception e)
		{
			encryptData.setPublicKey("");
			encryptData.setPrivateKey("");
			e.printStackTrace();
			System.err.print(e.getLocalizedMessage());
		}
		return encryptData;
	}
	
}

