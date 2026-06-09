package com.traneco.encDecKey.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.traneco.cmsaudit.dao.CmsAuditDao;
import com.traneco.cmsaudit.model.CmsAudit;
import com.traneco.cmsaudit.services.AuditService;
import com.traneco.common.ResponseBean;
import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.util.Utils;
import com.traneco.encDecKey.dao.EncDecKeyDao;
import com.traneco.keyEncrypt.model.EncryptKeyModel;
import com.traneco.keyEncrypt.service.EncryptKeyDataService;
/*
 * Author By Prashant Tayde for AJAX Call
 */

@Service
public class EncDecKeyServiceImpl implements EncDecKeyService {

	@Autowired
	EncDecKeyDao encDecKeyDao;
	
	@Autowired
	EncryptKeyDataService encryptKeyDataService;

	@Autowired
	Environment env;
	
	@Autowired
	private AuditService auditService;
	
	@Autowired
	private CmsAuditDao cmsAuditDao;

	@Override
	public ResponseBean generateCardEncDecKey() 
	{
		ResponseBean responseBean = new ResponseBean();
		EncryptKeyModel encryptKeyModel = new EncryptKeyModel();
		try 
		{
			CmsAudit cmsAudit = new CmsAudit();
			cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_GENERATED);
			cmsAuditDao.addAuditEntry(cmsAudit);
			String generateKey = Utils.getRandomString(32);
			File file = new File(env.getProperty("EncDeckeystoreFile"));

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
			String key = sb.toString().replace("\n", "").replace("\r", "");
			if (key != null) 
			{
				FileOutputStream fos = new FileOutputStream(env.getProperty("EncDeckeystoreFile"));
				FileWriter writer = new FileWriter(env.getProperty("EncDeckeystoreFile"), true); // 'true' for append mode
				String keyPartForTxtFile = generateKey.substring(0, 16);
				writer.write(keyPartForTxtFile);
				writer.close();
				cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_STORED_FILE);
				auditService.addAuditEntry(cmsAudit);
				ResponseBean addGenetaedKey = encryptKeyDataService.addGenetaedKey(keyPartForTxtFile);
				if (TranecoStatusCode.STATUS_SUCCESS.equals(addGenetaedKey.getOutResponseCode())) 
				{
					String keyPartForDBFile = generateKey.substring(16);
					encryptKeyModel.setValue(keyPartForDBFile);
					int insertEncDecValueInDB = encryptKeyDataService.insertEncDecValueInTempDB(keyPartForDBFile);
					System.out.println("EncDecKeyServiceImpl.Inserted In Database:::PrashantDB()"+insertEncDecValueInDB);
					if (insertEncDecValueInDB > 0) 
					{
						responseBean.setOutResponseCode("00");
						responseBean.setFlag("success");
						responseBean.setMessage("Key Generated Successfully");
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_STORED_DB);
						auditService.addAuditEntry(cmsAudit);
					} 
					else 
					{
						responseBean.setOutResponseCode("99");
						responseBean.setFlag("failed");
						responseBean.setMessage("failed to update in DB");
					}
				}
				else 
				{
					responseBean.setOutResponseCode("99");
					responseBean.setFlag("failed");
					responseBean.setMessage("failed to update");
				}
			} 
			else 
			{
				String keyPartForTxtFile = generateKey.substring(0, 16);
				FileOutputStream fos = new FileOutputStream(env.getProperty("EncDeckeystoreFile"));
				FileWriter writer = new FileWriter(env.getProperty("EncDeckeystoreFile"), true); // 'true' for append mode
				writer.write(keyPartForTxtFile);
				writer.close();
				ResponseBean addGenetaedKey = encryptKeyDataService.addGenetaedKey(keyPartForTxtFile);
				if (TranecoStatusCode.STATUS_SUCCESS.equals(addGenetaedKey.getOutResponseCode())) 
				{
					String keyPartForDBFile = generateKey.substring(16);
					int insertEncDecValueInDB = encryptKeyDataService.insertEncDecValueInTempDB(keyPartForDBFile);
					if (insertEncDecValueInDB > 0) 
					{
						responseBean.setOutResponseCode("00");
						responseBean.setFlag("success");
						responseBean.setMessage("Key Generated Successfully");
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_STORED_DB);
						cmsAuditDao.addAuditEntry(cmsAudit);
					} 
					else 
					{
						responseBean.setOutResponseCode("99");
						responseBean.setFlag("failed");
						responseBean.setMessage("failed to insert in DB");
					}
				}
				else 
				{
					responseBean.setOutResponseCode("99");
					responseBean.setFlag("failed");
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return responseBean;
	}

	@Override
	public int validateCustdian1Password(EncryptKeyModel encryptKeyModel) 
	{
		return encDecKeyDao.validateCustdian1Password(encryptKeyModel);
	}

	@Override
	public int validateCustdian2Password(EncryptKeyModel encryptKeyModel) 
	{
		return encDecKeyDao.validateCustdian2Password(encryptKeyModel);
	}
}
