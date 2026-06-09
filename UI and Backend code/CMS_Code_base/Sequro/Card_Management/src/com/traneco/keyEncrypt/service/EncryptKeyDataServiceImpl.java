package com.traneco.keyEncrypt.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.traneco.common.ResponseBean;
import com.traneco.common.util.Utils;
import com.traneco.keyEncrypt.dao.EncryptKeyDataDao;
import com.traneco.keyEncrypt.model.EncryptKeyModel;

@Service
public class EncryptKeyDataServiceImpl implements EncryptKeyDataService
{
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	EncryptKeyDataDao encryptKeyDataDao;
	
	@Autowired
	Environment env;
	

	@Override
	public int updateEncDecValueInDB(EncryptKeyModel encryptKeyModel) 
	{
		return encryptKeyDataDao.updateEncDecValueInDB(encryptKeyModel);
	}


	@Override
	public int insertEncDecValueInTempDB(String keyPartForDBFile) {
		
		return encryptKeyDataDao.insertEncDecValueInTempDB(keyPartForDBFile);
	}


	public ResponseBean addGenetaedKey(String value) 
	{
		ResponseEntity<ResponseBean> response = restTemplate.postForEntity(
				env.getProperty("cms.url") + env.getProperty("cms.uri.keyAddition"), value,
				ResponseBean.class);
		return response.getBody();
	}


	@Override
	public EncryptKeyModel getEncDecValue() 
	{
		return encryptKeyDataDao.getEncDecValue();
	}


	@Override
	public EncryptKeyModel getMaskedValue() 
	{
		return encryptKeyDataDao.getMaskedValue();
	}


	@Override
	public EncryptKeyModel getMaskedFileKey() 
	{
		  EncryptKeyModel encryptModel = new EncryptKeyModel();
		  EncryptKeyModel maskedValue = encryptKeyDataDao.getMaskedValue();
		  File file = new File(env.getProperty("EncDeckeystoreFile"));
		  try (BufferedReader br = new BufferedReader(new FileReader(file))) 
		  {
		      StringBuilder sb = new StringBuilder();
		      String line;
		      while ((line = br.readLine()) != null) 
		      {
		          sb.append(line);
		      }
		      String fileAsString = sb.toString();
		      String key = fileAsString.replace("\n", "").replace("\r", "");
		      String maskKey = Utils.maskValue(key);
		      encryptModel.setKey(maskKey.trim());
		      encryptModel.setValue(maskedValue.getValue());
		  } 
		  catch (IOException e) 
		  {
		      e.printStackTrace();
		  }
		return encryptModel;
	}


	@Override
	public EncryptKeyModel getPlainFileKey() 
	{
		  EncryptKeyModel encryptModel = new EncryptKeyModel();
		  encryptModel = encryptKeyDataDao.getEncDecTempValueFromDB();
		  File file = new File(env.getProperty("EncDeckeystoreFile"));
		  try (BufferedReader br = new BufferedReader(new FileReader(file))) 
		  {
		      StringBuilder sb = new StringBuilder();
		      String line;
		      while ((line = br.readLine()) != null) 
		      {
		          sb.append(line);
		      }
		      String fileAsString = sb.toString();
		      String key = fileAsString.replace("\n", "").replace("\r", "");
		      encryptModel.setKey(key.trim());
		      encryptModel.setValue(encryptModel.getValue());
		  } 
		  catch (IOException e) 
		  {
		      e.printStackTrace();
		  }
		  return encryptModel;
	}

	
	@Override
	public EncryptKeyModel getClearFileKey() 
	{
		  EncryptKeyModel encryptModel = new EncryptKeyModel();
		  encryptModel = encryptKeyDataDao.getEncDecValue();
		  File file = new File(env.getProperty("EncDeckeystoreFile"));
		  try (BufferedReader br = new BufferedReader(new FileReader(file))) 
		  {
		      StringBuilder sb = new StringBuilder();
		      String line;
		      while ((line = br.readLine()) != null) 
		      {
		          sb.append(line);
		      }
		      String fileAsString = sb.toString();
		      String key = fileAsString.replace("\n", "").replace("\r", "");
		      encryptModel.setKey(key.trim());
		      encryptModel.setValue(encryptModel.getValue());
		  } 
		  catch (IOException e) 
		  {
		      e.printStackTrace();
		  }
		  return encryptModel;
	}

	@Override
	public EncryptKeyModel getEncDecTempValueFromDB() 
	{
		return encryptKeyDataDao.getEncDecTempValueFromDB();
	}


	@Override
	public int insertEncDecValueInDB(EncryptKeyModel encryptKeyModel) 
	{
		return encryptKeyDataDao.insertEncDecValueInDB(encryptKeyModel);
	}


	@Override
	public int deleteEncDecTempValueFromDB() 
	{
		return encryptKeyDataDao.deleteEncDecTempValueFromDB();
	}

}
