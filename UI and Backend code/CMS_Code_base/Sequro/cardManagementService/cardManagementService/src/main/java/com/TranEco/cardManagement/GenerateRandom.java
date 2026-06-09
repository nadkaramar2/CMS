package com.TranEco.cardManagement;

import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class GenerateRandom {
	
	private static SecureRandom random = new SecureRandom();
	private static final String CHARACTER_SET="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789$*&@?<>~!%#";
	
	public static void main(String[] args) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		
		SecureRandom secureRandom = new SecureRandom();
		GenerateRandom generateRandom = new GenerateRandom();
		String randomString = generateRandom.getRandomString(32);
		System.out.println("Random Generated:::"+randomString);

		UUID uuid = UUID.randomUUID();
		String dek = uuid.toString();
		System.out.println("DEK:::::"+dek);

		UUID kekUuid = UUID.randomUUID();
		String kek = kekUuid.toString();
		System.out.println("KEK:::::"+kek);

		byte[] dekBytes = convertUUIDToBytes(uuid);
		byte[] kekBytes = convertUUIDToBytes(kekUuid);
		System.out.println("dekBytes::::"+Arrays.toString(dekBytes));
		System.out.println("kekBytes::::"+Arrays.toString(kekBytes));

		SecretKeySpec key = new SecretKeySpec(kekBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedDek = cipher.doFinal(dekBytes);
		System.out.println("encryptedDek:::::"+Arrays.toString(encryptedDek));

	}	
	
	public static byte[] convertUUIDToBytes(UUID uuid) 
	{
	    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
	    bb.putLong(uuid.getMostSignificantBits());
	    bb.putLong(uuid.getLeastSignificantBits());
	    return bb.array();
	}
	
	
	public static String getRandomString(int len) 
	{
        StringBuffer buff = new StringBuffer(len);

        for(int i=0;i<len;i++) 
        {
            int offset = random.nextInt(CHARACTER_SET.length());

            buff.append(CHARACTER_SET.substring(offset,offset+1));
        }
        return buff.toString();
    }
}
