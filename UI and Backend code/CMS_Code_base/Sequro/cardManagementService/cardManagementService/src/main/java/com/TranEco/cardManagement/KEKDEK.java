package com.TranEco.cardManagement;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class KEKDEK 
{
	static String alg = "AES";
	private static Key key;
	static Cipher cipher;
	
	
	public static void main(String[] args) throws Exception
	{
		try 
		{
			key = KeyGenerator.getInstance(alg).generateKey();
			cipher = Cipher.getInstance(alg);
			String name = "70fXBoJxIa0NgYurH/nQhBzBnYt6kRAbIpuh2MHRn8w=";
			byte[] encryptedBytes = encryptIt(name);
			
			System.out.println("Key::::"+key.toString());
			System.out.println("Encrypted Bytes::"+encryptIt(name));
			System.out.println("Decrypted Bytes ::: "+decrypt(encryptedBytes));
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	
	}
		
	private static byte[] encryptIt(String input) throws Exception
	{
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] iBytes = input.getBytes();
		return cipher.doFinal(iBytes);
	}
	
	
	private static String decrypt(byte[] encryptBytes) throws Exception
	{
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] recoverBytes = cipher.doFinal(encryptBytes);
		String originalText = new String(recoverBytes);
		return originalText;
		
	}
}
