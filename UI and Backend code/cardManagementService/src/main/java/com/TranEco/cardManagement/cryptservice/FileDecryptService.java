package com.TranEco.cardManagement.cryptservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.TranEco.cardManagement.crypt.RSAStaticKey;
import com.TranEco.cardManagement.crypt.dao.EncryptedKeyDataDao;
import com.TranEco.cardManagement.crypt.model.EncryptData;
import com.TranEco.cardManagement.crypt.model.EncryptKeyData;
import com.TranEco.cardManagement.crypt.model.RsaKeyValue;

@Service
public class FileDecryptService implements FileEncryptDecryptService
{
    @Autowired
    private EncryptedKeyDataDao encryptKeyDataDao;
    
    @Autowired
    private RSAEncryptDecryptService rsaEncryptDecryptService;
    
    @Autowired
    private AESEncryptDecryptService aesEncryptDecryptService;
    
    @Autowired
    RSAKeyValueService rsaKeyValueService;
    
    @Override
    public String decryptFile(MultipartFile file)
   {
	String message = "";
	String originalFilename = file.getOriginalFilename();
	String fileName = removeExtension(originalFilename);
	List<EncryptKeyData> keyDataByFileName = encryptKeyDataDao.getKeyDataByFileName(fileName);
	if(keyDataByFileName.isEmpty())
	{
	    message = "File Not Found ";
	    return message;
	}
	else 
	{
	    EncryptData aesEncryptData = new EncryptData();
	    aesEncryptData.setEncryptedData(keyDataByFileName.get(0).getAesEncodedKey());
	    //aesEncryptData.setKey(RSAStaticKey.privateKey1);
	    RsaKeyValue rsKeyValue = rsaKeyValueService.getRSAKeyValue(1);
	    aesEncryptData.setKey(rsKeyValue.getPrivateKey());
	    try
	    {
		String aesKeyForFileDecryption = rsaEncryptDecryptService.decrypt(aesEncryptData);
		
		EncryptData rsaEncryptData= new EncryptData();
		rsaEncryptData.setFile(file);
		rsaEncryptData.setKey(aesKeyForFileDecryption);
		rsaEncryptData.setIv(keyDataByFileName.get(0).aesEncodedIv);
		String decryptedFile = aesEncryptDecryptService.decryptFile(rsaEncryptData);
		return decryptedFile;
		
	    } catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	}
	return null;
    }
    
    public String removeExtension(String fileName) 
    {
	   int lastIndex = fileName.lastIndexOf(".");
	   if (lastIndex == -1) 
	   {
	       return fileName;
	   }
	   return fileName.substring(0, lastIndex);
	}

    
}
