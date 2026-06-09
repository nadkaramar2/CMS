package com.TranEco.cardManagement.test;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.TranEco.cardManagement.inquiryServices.model.CardDetails;
import com.TranEco.cardManagement.test.service.TestService;
//Testing ===Created by Prashant Tayde
@RestController
@RequestMapping("/test")
public class TestController 
{
	@Autowired
	TestService cardDetailsService;
	
	@Autowired
	private static Environment env;
	
	private final String INIT_VECTOR = "A0b1C2d3E4f56789";
	private final String ALG="AES";
	private final String CIPHER="AES/CBC/PKCS5PADDING";
	private final String UTF_8 = "UTF-8";
	private final String key = "h?4W~qQ%H93$TJ0h";
	//private static char[] key1 = env.getProperty("key").toCharArray();
	
	/*
	 * public TestController(char[] key) { this.key = key; }
	 */

	private String getClearKey()
	{
		//return new String(key= "h?4W~qQ%H93$TJ0h".toCharArray());
		return new String(key);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "encryptCardNo", consumes = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.TEXT_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public CardDetails cardDetailsData(@RequestBody CardDetails request) 
	{
		CardDetails response = new CardDetails();
		try 
		{
			CardDetails cardDetails = cardDetailsService.getCardDetails(request);
			String tokenCard = cardDetails.getStrTokenCard();
			String encryptedBytes = encrypt("6666661049263123");
			System.out.println("Encrypted Bytes::"+encryptedBytes);
			System.out.println("Decrypted Bytes ::: "+decrypt(encryptedBytes));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			response.setMessage("Internal Error");
			response.setOutResponseCode("99");
		}
		return response;
	}
	
	public String encrypt(String value) throws Exception
	{
		IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(UTF_8));
		SecretKeySpec skeySpec = new SecretKeySpec(getClearKey().getBytes(UTF_8), ALG);
 
		Cipher cipher = Cipher.getInstance(CIPHER);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
 
		byte[] encrypted = cipher.doFinal(value.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
		
	}
	
	
	public String decrypt(String encrypted) throws Exception{
		IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(UTF_8));
		SecretKeySpec skeySpec = new SecretKeySpec(getClearKey().getBytes(UTF_8), ALG);
 
		Cipher cipher = Cipher.getInstance(CIPHER);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
 
		return new String(original);
	}	
}
