package com.TranEco.cardManagement.cryptservice;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TranEco.cardManagement.crypt.EncodeDecode;
import com.TranEco.cardManagement.crypt.model.EncryptData;

@Service
public class AESEncryptDecryptService 
{
	private int TAG_LENGTH = 128;

	// IV is passed from the request [we can keep the iv static or generate random
	// iv]
	private byte[] IV;

	public String encrypt(EncryptData encryptData) throws Exception{
		try 
		{
		String message = encryptData.getEncryptionData();
		byte[] messageInBytes = message.getBytes();

		String iv = encryptData.getIv();
		IV = EncodeDecode.decode(iv);

		String secretKey = encryptData.getKey();
		byte[] decodedSecretKey = EncodeDecode.decode(secretKey);

		SecretKey key = new SecretKeySpec(decodedSecretKey, "AES");

		Cipher encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, IV);
		encryptionCipher.init(Cipher.ENCRYPT_MODE, key, spec);
		// IV = encryptionCipher.getIV();
		byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
		return EncodeDecode.encode(encryptedBytes);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("Encryption Error");
		}
	}

	// decryption Normally
	public String decrypt(EncryptData encryptData) throws Exception {

		String encryptedData = encryptData.getEncryptedData();
		String key = encryptData.getKey();
		String iv = encryptData.getIv();
		byte[] IV = EncodeDecode.decode(iv);
		byte[] dataInBytes = EncodeDecode.decode(encryptedData);
		byte[] keyInBytes = EncodeDecode.decode(key);
		SecretKey secretKey = new SecretKeySpec(keyInBytes, "AES");
		Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");

		GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, IV);
		decryptionCipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
		byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
		String decryptedData = new String(decryptedBytes);
		return decryptedData;
	}

	public IvParameterSpec getRandomIV() {

		SecureRandom random = new SecureRandom();
		byte[] iv = new byte[32]; // 16 bytes for AES-128, 24 bytes for AES-192, 32 bytes for AES-256
		random.nextBytes(iv);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		return ivSpec;
	}
	
		// Decryption process for the file Which Was Encrypted ---
		public String decryptFile(EncryptData encryptData) throws Exception 
		{
			String decryptedAllData = "";
			
			//file
			MultipartFile encryptedFile = encryptData.getFile();

			//key
			String key = encryptData.getKey();
			byte[] keyInBytes = EncodeDecode.decode(key);
			SecretKey secretKey = new SecretKeySpec(keyInBytes, "AES");
			
			//iv
			String iv = encryptData.getIv();
			byte[] decodedBytes = EncodeDecode.decode(iv);
			IV = decodedBytes;
			
			Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
			GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH, IV);    
			decryptionCipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
			File file = convertMultipartFileToFile(encryptedFile);
			boolean exists = file.exists();
			System.err.println(exists);
			FileReader fileReader = new FileReader(file.getPath());
			BufferedReader reader = new BufferedReader(fileReader);
		    String line;
		    while ((line = reader.readLine()) != null)
		    {
		    		System.out.println(line);
		        	byte[] dataInBytes = EncodeDecode.decode(line);
		        	byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);
		        	String decryptedData = new String(decryptedBytes);
		        	decryptedAllData = decryptedAllData + "\n" + decryptedData;
		        	System.out.println(decryptedData);
		    }
		        reader.close();
			return decryptedAllData;
		}

		//converting the multipart file to normal File Object for API of rec3ived multipart File
		private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {   
	        byte[] bytes = multipartFile.getBytes();
	        Path path = Paths.get(multipartFile.getOriginalFilename());
	        Files.write(path, bytes);
	        File file = path.toFile();
	        return file;
	    }
		
}
