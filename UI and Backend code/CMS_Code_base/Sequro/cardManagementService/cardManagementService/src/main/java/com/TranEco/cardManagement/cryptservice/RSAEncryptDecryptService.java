package com.TranEco.cardManagement.cryptservice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.TranEco.cardManagement.crypt.EncodeDecode;
import com.TranEco.cardManagement.crypt.model.EncryptData;

@Component
public class RSAEncryptDecryptService 
{
	@Autowired
	private Environment env;
	
	//file path for testing in local
	//public static String filePath =  "E:\\Prashant\\CMS-19082023\\cardManagementService\\cms\\embossEncrypted";
	
	//public static String filePath =  "/usr/local/tomcat/paths/cms/emboss/";
	//public final String filePath = (env.getProperty("emboss.file.path"));
	public static String filePath  = "D:\\Sequro\\winservice\\cardManagement\\cms\\emboss";
	
	public String encrypt(EncryptData encryptData) throws Exception
	{
			String key = encryptData.getKey();
			String message = encryptData.getEncryptionData();
			byte[] messageToBytes = message.getBytes();
			key.replace("-----BEGIN PUBLIC KEY-----", "")
			   .replace("-----END PUBLIC KEY-----", "")
			   .replaceAll("\\s", "");
			byte[] publicKeyBytes = EncodeDecode.decode(key);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encryptedBytes = cipher.doFinal(messageToBytes);
			return EncodeDecode.encode(encryptedBytes);
	}
	
	public String decrypt(EncryptData encryptData) throws Exception
	{
		String key = encryptData.getKey();		
		String encryptedData = encryptData.getEncryptedData();
		byte[] encrptedDataBytes = EncodeDecode.decode(encryptedData);
		key.replace("-----BEGIN PUBLIC KEY-----", "")
		   .replace("-----END PUBLIC KEY-----", "")
		   .replaceAll("\\s", "");
		byte[] privateKeyBytes = EncodeDecode.decode(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec (privateKeyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE,privateKey);
		byte[] decryptedBytes = cipher.doFinal(encrptedDataBytes);
		return new String(decryptedBytes,"UTF8");
	}
	
	
	public String encryptFile(EncryptData encryptData) throws Exception
	{
			String key = encryptData.getKey();
			
			MultipartFile file = encryptData.getFile();
			long size = file.getSize();
			System.err.println(size);
			byte[] fileInBytes = file.getBytes();
			
			key.replace("-----BEGIN PUBLIC KEY-----", "")
			   .replace("-----END PUBLIC KEY-----", "")
			   .replaceAll("\\s", "");
			byte[] publicKeyBytes = EncodeDecode.decode(key);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey publicKey = keyFactory.generatePublic(keySpec);
			Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] encryptedBytes = cipher.doFinal(fileInBytes);
			
			//steps to save the encrypted data in the file 
			String fileName = "output.txt"; // output file name
			File newFile = new File(filePath + fileName); // create a File object
			
			String base64EncodedFile = "";
			try (FileOutputStream fos = new FileOutputStream(newFile)) 
			{
			    fos.write(encryptedBytes); // write bytes to the file
			    System.out.println("Bytes written to file: " + fileName);
			    
			    byte[] fileBytes = Files.readAllBytes(newFile.toPath());
			    // Convert the byte array to base64 format
			    base64EncodedFile = Base64.getEncoder().encodeToString(fileBytes);
			    
			}
			catch (IOException e) 
			{
			    e.printStackTrace();
			}
			//steps to save the encrypted data in the file
			
			//encoded file
			return EncodeDecode.encode(encryptedBytes);
	}
	
	
	//decrypted data in bytes
	public String decryptFile(EncryptData encryptData) throws Exception
	{
		String key = encryptData.getKey();		
		byte[] privateKeyBytes = EncodeDecode.decode(key);
		
		MultipartFile encryptedFile = encryptData.getFile();
		byte[] bytes = encryptedFile.getBytes();
//		String encryptedData = encryptData.getEncryptedData();
//		byte[] encryptedDataBytes = EncodeDecode.decode(encryptedData);
		
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec (privateKeyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE,privateKey);
		byte[] decryptedBytes = cipher.doFinal(bytes);
		
		String fileName = "outputDecrypted.txt"; // output file name
		File file = new File(filePath + fileName); // create a File object
		
		String base64EncodedFile = "";
		try (FileOutputStream fos = new FileOutputStream(file)) 
		{
		    fos.write(decryptedBytes); // write bytes to the file
		    System.out.println("Bytes written to file: " + fileName);
		    
		    byte[] fileBytes = Files.readAllBytes(file.toPath());
		    // Convert the byte array to base64 format
		    base64EncodedFile = Base64.getEncoder().encodeToString(fileBytes);
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
		return new String(decryptedBytes);
	}
	
}
