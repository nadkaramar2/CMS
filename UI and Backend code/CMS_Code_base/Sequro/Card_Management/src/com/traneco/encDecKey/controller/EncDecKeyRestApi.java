package com.traneco.encDecKey.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.traneco.cmsaudit.model.CmsAudit;
import com.traneco.cmsaudit.services.AuditService;
import com.traneco.common.ResponseBean;
import com.traneco.common.constants.TranecoStatusCode;
import com.traneco.common.util.Utils;
import com.traneco.encDecKey.service.EncDecKeyService;
import com.traneco.keyEncrypt.model.EncryptKeyModel;
import com.traneco.keyEncrypt.service.EncryptKeyDataService;

@RestController
public class EncDecKeyRestApi 
{
	
	@Autowired
	EncDecKeyService encDecKeyService;
	
	@Autowired
	EncryptKeyDataService encryptKeyDataService;
	
	@Autowired
	Environment env;
	
	@Autowired
	AuditService auditService;
	
	@ResponseBody
	@RequestMapping(value = "/generateCardEncDecKey", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean generateCardEncDecKey() 
	{
		ResponseBean responseBean = new ResponseBean();
		try 
		{
			responseBean = encDecKeyService.generateCardEncDecKey();
		}
		catch (Exception e) 
		{
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getGeneratedKeyValue", method = RequestMethod.POST)
	public EncryptKeyModel getGeneratedKeyValue(@RequestBody EncryptKeyModel encryptKeyModel)
	{
		try 
		{
				CmsAudit cmsAudit = new CmsAudit();
				if (encryptKeyModel.getCustodian1Password() != null && encryptKeyModel.getCustodian2Password() == null)
				{
					String salt = env.getProperty("imps.user.login.salt.password");
					String encPass = Utils.generateSecurePassword(encryptKeyModel.getCustodian1Password(), salt);
					encryptKeyModel.setPassword(encPass);
					int count = encDecKeyService.validateCustdian1Password(encryptKeyModel);
					if (count > 0)
					{
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.PASSWORD_VERIFIED_CUST1);
						auditService.addAuditEntry(cmsAudit);
						encryptKeyModel = encryptKeyDataService.getPlainFileKey();
						if(encryptKeyModel.getKey() != null)
						{
							encryptKeyModel.setKey(encryptKeyModel.getKey());
							encryptKeyModel.setResponseCode("00");
							encryptKeyModel.setResponseDesc("Success");
							encryptKeyModel.setMessage("Password Matched");
							cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_DISPLAYED_CUST1);
							auditService.addAuditEntry(cmsAudit);
						}
						else 
						{
							encryptKeyModel.setKey("");
							encryptKeyModel.setValue("");
							encryptKeyModel.setResponseCode("99");
							encryptKeyModel.setResponseDesc("Error : Clear Key");
						}
					}
					else 
					{
						encryptKeyModel.setResponseCode("11");
						encryptKeyModel.setResponseDesc("Invalid Password");
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.FAILED_PASSWORD_CUST1);
						auditService.addAuditEntry(cmsAudit);
					}
				}
				else 
				{
					String salt = env.getProperty("imps.user.login.salt.password");
					String encPass = Utils.generateSecurePassword(encryptKeyModel.getCustodian2Password(), salt);
					encryptKeyModel.setPassword(encPass);
					int count = encDecKeyService.validateCustdian2Password(encryptKeyModel);
					if (count > 0)
					{
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.PASSWORD_VERIFIED_CUST2);
						auditService.addAuditEntry(cmsAudit);
						encryptKeyModel = encryptKeyDataService.getPlainFileKey();
						if(encryptKeyModel.getValue() != null)
						{
							encryptKeyModel.setValue(encryptKeyModel.getValue());
							encryptKeyModel.setResponseCode("00");
							encryptKeyModel.setResponseDesc("Success");
							encryptKeyModel.setMessage("Password Matched");
							cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_DISPLAYED_CUST2);
							auditService.addAuditEntry(cmsAudit);
						}
						else 
						{
							encryptKeyModel.setKey("");
							encryptKeyModel.setValue("");
							encryptKeyModel.setResponseCode("99");
							encryptKeyModel.setResponseDesc("Error : Clear Key");
						}
					}
					else 
					{
						encryptKeyModel.setResponseCode("11");
						encryptKeyModel.setResponseDesc("Invalid Password");
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.FAILED_PASSWORD_CUST2);
						auditService.addAuditEntry(cmsAudit);
					}
				}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return encryptKeyModel;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getClearKeyValue", method = RequestMethod.POST)
	public EncryptKeyModel getClearKeyValue(@RequestBody EncryptKeyModel encryptKeyModel)
	{
		try 
		{
				CmsAudit cmsAudit = new CmsAudit();
				String custodian1Password  = encryptKeyModel.getSenPwd();
				encryptKeyModel.setCustodian1Password(custodian1Password);
				if (encryptKeyModel.getCustodian1Password() != null && encryptKeyModel.getCustodian2Password() == null)
				{
					String salt = env.getProperty("imps.user.login.salt.password");
					String encPass = Utils.generateSecurePassword(encryptKeyModel.getCustodian1Password(), salt);
					encryptKeyModel.setPassword(encPass);
					int count = encDecKeyService.validateCustdian1Password(encryptKeyModel);
					if (count > 0)
					{
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.PASSWORD_VERIFIED_CUST1);
						auditService.addAuditEntry(cmsAudit);
						encryptKeyModel = encryptKeyDataService.getClearFileKey();
						if(encryptKeyModel.getKey() != null)
						{
							encryptKeyModel.setKey(encryptKeyModel.getKey());
							encryptKeyModel.setResponseCode("00");
							encryptKeyModel.setResponseDesc("Success");
							encryptKeyModel.setMessage("Password Matched");
							cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_DISPLAYED_CUST1);
							auditService.addAuditEntry(cmsAudit);
						}
						else 
						{
							encryptKeyModel.setKey("");
							encryptKeyModel.setValue("");
							encryptKeyModel.setResponseCode("99");
							encryptKeyModel.setResponseDesc("Error : Clear Key");
						}
					}
					else 
					{
						encryptKeyModel.setResponseCode("11");
						encryptKeyModel.setResponseDesc("Invalid Password");
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.FAILED_PASSWORD_CUST1);
						auditService.addAuditEntry(cmsAudit);
					}
				}
				else 
				{
					String custodian2Password = encryptKeyModel.getPassword();
					encryptKeyModel.setCustodian2Password(custodian2Password);
					String salt = env.getProperty("imps.user.login.salt.password");
					String encPass = Utils.generateSecurePassword(encryptKeyModel.getCustodian2Password(), salt);
					encryptKeyModel.setPassword(encPass);
					int count = encDecKeyService.validateCustdian2Password(encryptKeyModel);
					if (count > 0)
					{
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.PASSWORD_VERIFIED_CUST2);
						auditService.addAuditEntry(cmsAudit);
						encryptKeyModel = encryptKeyDataService.getClearFileKey();
						if(encryptKeyModel.getValue() != null)
						{
							encryptKeyModel.setValue(encryptKeyModel.getValue());
							encryptKeyModel.setResponseCode("00");
							encryptKeyModel.setResponseDesc("Success");
							encryptKeyModel.setMessage("Password Matched");
							cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_DISPLAYED_CUST2);
							auditService.addAuditEntry(cmsAudit);
						}
						else 
						{
							encryptKeyModel.setKey("");
							encryptKeyModel.setValue("");
							encryptKeyModel.setResponseCode("99");
							encryptKeyModel.setResponseDesc("Error : Clear Key");
						}
					}
					else 
					{
						encryptKeyModel.setResponseCode("11");
						encryptKeyModel.setResponseDesc("Invalid Password");
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.FAILED_PASSWORD_CUST2);
						auditService.addAuditEntry(cmsAudit);
					}
				}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return encryptKeyModel;
	}
	
	//For Generate Key
	@ResponseBody
	@RequestMapping(value = "/saveCustodianKey1", method = RequestMethod.POST)
	public EncryptKeyModel saveCustodianKey(@RequestBody EncryptKeyModel encryptKeyModel)
	{
		EncryptKeyModel responseBean = new EncryptKeyModel();
		try 
		{
			CmsAudit cmsAudit = new CmsAudit();
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
			
			FileOutputStream fos = new FileOutputStream(env.getProperty("EncDeckeystoreFile"));
			FileWriter writer = new FileWriter(env.getProperty("EncDeckeystoreFile"), true); // 'true' for append mode
			String keyPartForTxtFile = encryptKeyModel.getKey().trim();
			writer.write(keyPartForTxtFile);
			writer.close();
			ResponseBean addGenetaedKey = encryptKeyDataService.addGenetaedKey(keyPartForTxtFile);
			if (TranecoStatusCode.STATUS_SUCCESS.equals(addGenetaedKey.getOutResponseCode())) 
			{
				responseBean.setResponseCode("00");
				responseBean.setResponseDesc("Success");
				responseBean.setMessage("Key Stored In File Successfully");
				cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_STORED_FILE);
				auditService.addAuditEntry(cmsAudit);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return responseBean;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/saveCustodianKey2", method = RequestMethod.POST)
	public EncryptKeyModel saveCustodianKey2(@RequestBody EncryptKeyModel encryptKeyModel)
	{
		EncryptKeyModel responseBean = new EncryptKeyModel();
		try 
		{
			CmsAudit cmsAudit = new CmsAudit();
			encryptKeyModel = encryptKeyDataService.getEncDecTempValueFromDB();
			if (encryptKeyModel != null && encryptKeyModel.getValue().trim() != null)
			{
				EncryptKeyModel encDecKeyModel = encryptKeyDataService.getEncDecTempValueFromDB();
				if (encDecKeyModel != null && Optional.ofNullable(encDecKeyModel.getValue()).isPresent())
				{
					int insertEncDecValueInDB = encryptKeyDataService.insertEncDecValueInDB(encDecKeyModel);
					if (insertEncDecValueInDB > 0)
					{
						responseBean.setResponseCode("00");
						responseBean.setResponseDesc("success");
						responseBean.setMessage("Key Stored Successfully..!");
						encryptKeyDataService.deleteEncDecTempValueFromDB();
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_STORED_DB);
						auditService.addAuditEntry(cmsAudit);
					}
					else 
					{
						responseBean.setResponseCode("99");
						responseBean.setResponseDesc("failed");
						responseBean.setMessage("Failed To update In DB");
					}
				}
				else 
				{
					int insertEncDecValueInDB = encryptKeyDataService.insertEncDecValueInDB(encryptKeyModel);
					if (insertEncDecValueInDB > 0) 
					{
						responseBean.setResponseCode("00");
						responseBean.setResponseDesc("success");
						responseBean.setMessage("Key Generated Successfully");
						
						if ("00".equalsIgnoreCase(responseBean.getResponseCode()))
						{
							encryptKeyDataService.deleteEncDecTempValueFromDB();
							cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_STORED_DB);
							auditService.addAuditEntry(cmsAudit);
						}
					} 
					else 
					{
						responseBean.setResponseCode("99");
						responseBean.setResponseDesc("failed");
						responseBean.setMessage("failed to insert in DB");
					}	
				}
			}
			else 
			{
				responseBean.setResponseCode("99");
				responseBean.setFlag("failed");
				responseBean.setMessage("failed to get Key From DB");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return responseBean;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/reEnterKeyValidateCustPassword", method = RequestMethod.POST)
	public EncryptKeyModel reEntryKeyStore(@RequestBody EncryptKeyModel encryptKeyModel) 
	{
		EncryptKeyModel responseBean = new EncryptKeyModel();
		try 
		{
			CmsAudit cmsAudit = new CmsAudit();
			cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.RE_ENTER_KEY);
			auditService.addAuditEntry(cmsAudit);
			if (encryptKeyModel.getCustodian1Password() != null && encryptKeyModel.getCustodian2Password() == null)
			{
				String salt = env.getProperty("imps.user.login.salt.password");
				String encPass = Utils.generateSecurePassword(encryptKeyModel.getCustodian1Password(), salt);
				encryptKeyModel.setPassword(encPass);
				int count = encDecKeyService.validateCustdian1Password(encryptKeyModel);
				if (count > 0)
				{
					responseBean.setResponseCode("00");
					responseBean.setResponseDesc("Success");
					responseBean.setMessage("Password Matched");
					cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.PASSWORD_VERIFIED_CUST1);
					auditService.addAuditEntry(cmsAudit);
					
				}
				else 
				{
					responseBean.setResponseCode("11");
					responseBean.setResponseDesc("Invalid Password");
					cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.FAILED_PASSWORD_CUST1);
					auditService.addAuditEntry(cmsAudit);
				}
			}
			else 
			{
				String salt = env.getProperty("imps.user.login.salt.password");
				String encPass = Utils.generateSecurePassword(encryptKeyModel.getCustodian2Password(), salt);
				encryptKeyModel.setPassword(encPass);
				int count = encDecKeyService.validateCustdian2Password(encryptKeyModel);
				if (count > 0)
				{
					responseBean.setResponseCode("00");
					responseBean.setResponseDesc("Success");
					responseBean.setMessage("Password Matched");
					cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.PASSWORD_VERIFIED_CUST2);
					auditService.addAuditEntry(cmsAudit);
					
				}
				else 
				{
					responseBean.setResponseCode("11");
					responseBean.setResponseDesc("Invalid Password");
					cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.FAILED_PASSWORD_CUST2);
					auditService.addAuditEntry(cmsAudit);
				}
			}
		}
		catch (Exception e) 
		{
			responseBean.setResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/reEntryKeyStoreCust1", method = RequestMethod.POST, produces = "application/json")
	public ResponseBean reEntryKeyStoreCust(EncryptKeyModel encryptKeyModel) 
	{
		ResponseBean responseBean = new ResponseBean();
		CmsAudit cmsAudit = new CmsAudit();
		try 
		{
			if (encryptKeyModel != null && encryptKeyModel.getCustodian1Password() != null)
			{
				String salt = env.getProperty("imps.user.login.salt.password");
				String encPass = Utils.generateSecurePassword(encryptKeyModel.getCustodian2Password(), salt);
				encryptKeyModel.setPassword(encPass);
				int count = encDecKeyService.validateCustdian1Password(encryptKeyModel);
				if (count > 0)
				{
					cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.PASSWORD_VERIFIED_CUST1);
					auditService.addAuditEntry(cmsAudit);
				}
				else 
				{
					encryptKeyModel.setResponseCode("11");
					encryptKeyModel.setResponseDesc("Invalid Password");
					cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.FAILED_PASSWORD_CUST1);
					auditService.addAuditEntry(cmsAudit);
				}
			}
			
		}
		catch (Exception e) 
		{
			responseBean.setOutResponseCode("99");
			responseBean.setMessage("Internal Error");
		}
		return responseBean;
	}
	
	
	//For Re- Enter Key Submit
	@ResponseBody
	@RequestMapping(value = "/saveCustodianReEnterKey1", method = RequestMethod.POST)
	public EncryptKeyModel saveCustodianKey1(@RequestBody EncryptKeyModel encryptKeyModel)
	{
		try 
		{
			if (encryptKeyModel != null && encryptKeyModel.getKey() != null)
			{
				if (encryptKeyModel.getKey().trim().length() == 16)
				{
					CmsAudit cmsAudit = new CmsAudit();
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
					FileOutputStream fos = new FileOutputStream(env.getProperty("EncDeckeystoreFile"));
					FileWriter writer = new FileWriter(env.getProperty("EncDeckeystoreFile"), true); // 'true' for append mode
					String keyPartForTxtFile = encryptKeyModel.getKey();
					writer.write(keyPartForTxtFile);
					writer.close();
					ResponseBean addGenetaedKey = encryptKeyDataService.addGenetaedKey(keyPartForTxtFile);
					if (TranecoStatusCode.STATUS_SUCCESS.equals(addGenetaedKey.getOutResponseCode())) 
					{
						encryptKeyModel.setResponseCode("00");
						encryptKeyModel.setResponseDesc("Success");
						encryptKeyModel.setMessage("Key Stored In File Successfully");
						cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_STORED_FILE);
						auditService.addAuditEntry(cmsAudit);
					}
				}
				else 
				{
					encryptKeyModel.setResponseCode("99");
					encryptKeyModel.setMessage("failed");
					encryptKeyModel.setResponseDesc("Key Length must Be 16 Characters");
				}
			}
			else 
			{
				encryptKeyModel.setResponseCode("99");
				encryptKeyModel.setResponseDesc("failed");
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return encryptKeyModel;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/saveCustodianReEnterKey2", method = RequestMethod.POST)
	public EncryptKeyModel saveCustodianReEnterKey2(@RequestBody EncryptKeyModel encryptKeyModel, Model model)
	{
		EncryptKeyModel responseBean = new EncryptKeyModel();
		CmsAudit cmsAudit = new CmsAudit();
		try 
		{
			if (encryptKeyModel != null && encryptKeyModel.getValue().trim() != null)
			    {
					if (encryptKeyModel.getValue().trim().length() == 16)
					{
						int insertEncDecValueInDB = encryptKeyDataService.insertEncDecValueInDB(encryptKeyModel);
						if (insertEncDecValueInDB > 0) 
						{
							responseBean.setResponseCode("00");
							responseBean.setResponseDesc("Success");
							responseBean.setMessage("Key Stored Successfully");
							cmsAudit.setDmlAction(TranecoStatusCode.AuditStatusCode.KEY_STORED_DB);
							auditService.addAuditEntry(cmsAudit);
						} 
						else 
						{
							responseBean.setResponseCode("99");
							responseBean.setResponseDesc("failed");
							responseBean.setMessage("failed to insert in DB");
						}	
					}
					else 
					{
						responseBean.setResponseCode("99");
						responseBean.setResponseDesc("failed");
						responseBean.setMessage("Key Length must Be 16 Characters");
					}
				}
			else 
			{
				responseBean.setResponseCode("99");
				responseBean.setResponseDesc("failed");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return responseBean;
	}
		
	
}