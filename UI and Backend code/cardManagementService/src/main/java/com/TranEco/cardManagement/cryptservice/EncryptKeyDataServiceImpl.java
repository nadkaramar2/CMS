package com.TranEco.cardManagement.cryptservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.crypt.dao.EncryptKeyDataDao;
import com.TranEco.cardManagement.crypt.model.EncryptKeyModel;
/*
 * Author By Prashant Tayde 
 */

@Service
public class EncryptKeyDataServiceImpl implements EncryptKeyDataService
{
	@Autowired
	EncryptKeyDataDao encryptKeyDataDao;
	
	@Autowired
	Environment env;
	
	@Override
	public EncryptKeyModel getKeySaltPhrase() 
	{
		return encryptKeyDataDao.getKeySaltPhrase();
	}
	
	@Override
	public EncryptKeyModel getEncDecValue() 
	{
		return encryptKeyDataDao.getEncDecValue();
	}

	@Override
	public EncryptKeyModel addGenetaedKey(String value) 
	{
		EncryptKeyModel responseBean = new EncryptKeyModel();
		try 
		{
			 File file = new File("key.txt");
			//File file = new File("/usr/local/tomcat/paths/key.txt");
			//File file = new File(env.getProperty("keystoreFile"));
			if (!file.exists() || !file.isFile() || !file.canRead()) 
		     {
	           throw new IOException("Invalid file path or cannot read file.");
	         }
		  	  BufferedReader br = new BufferedReader(new FileReader(file));
		      StringBuilder sb = new StringBuilder();
		      String line;
		      while ((line = br.readLine()) != null) 
		      {
		          sb.append(line);
		      }
		      String fileAsString = sb.toString().replace("\n", "").replace("\r", "");
		      String key = fileAsString;
		 
		  if (key != null)
		  {
			  FileOutputStream fos = new FileOutputStream("key.txt");
			// FileOutputStream fos = new FileOutputStream("/usr/local/tomcat/paths/key.txt");
			//  FileOutputStream fos = new FileOutputStream(env.getProperty("keystoreFile"));
			  
			 FileWriter writer = new FileWriter(("key.txt"), true); // 'true' for append mode
			 //FileWriter writer = new FileWriter(("/usr/local/tomcat/paths/key.txt"), true);
			 // FileWriter writer = new FileWriter((env.getProperty("keystoreFile")), true);
			  String keyPartForTxtFile = value;
			  writer.write(keyPartForTxtFile);
			  writer.close();
			  responseBean.setOutResponseCode("00");
			  responseBean.setFlag("success");;
			  responseBean.setMessage("Key Stored in File.");
		  }
		  else
		  	{
			 FileWriter writer = new FileWriter(("key.txt"), true); // 'true' for append mode
			  //FileWriter writer = new FileWriter(("/usr/local/tomcat/paths/key.txt"), true); // 'true' for append mode
			 // FileWriter writer = new FileWriter((env.getProperty("keystoreFile")), true); // 'true' for append mode
			  String keyPartForTxtFile = value;
			  writer.write(keyPartForTxtFile);
			  writer.close();
			  responseBean.setOutResponseCode("00");
			  responseBean.setFlag("success");;
			  responseBean.setMessage("Key Stored in File.");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return responseBean;
	}
}
