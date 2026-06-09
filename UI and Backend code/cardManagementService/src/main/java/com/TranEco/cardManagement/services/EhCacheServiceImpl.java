package com.TranEco.cardManagement.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.dao.EhCacheDaoImpl;
import com.TranEco.cardManagement.model.Cfg_Account_Type;
import com.TranEco.cardManagement.model.Cfg_Address_Type;
import com.TranEco.cardManagement.model.Cfg_Bin;
import com.TranEco.cardManagement.model.Cfg_Card_Plastic;
import com.TranEco.cardManagement.model.Cfg_Card_Template;
import com.TranEco.cardManagement.model.Cfg_Card_Type;
import com.TranEco.cardManagement.model.Cfg_Document_Type;
import com.TranEco.cardManagement.model.Cfg_EMV_Tags;
import com.TranEco.cardManagement.model.Cfg_Email_Type;
import com.TranEco.cardManagement.model.Cfg_Embossing_Data;
import com.TranEco.cardManagement.model.Cfg_Interface_Key;
import com.TranEco.cardManagement.model.Cfg_Keys;
import com.TranEco.cardManagement.model.Cfg_POS_Entry_Mode;
import com.TranEco.cardManagement.model.Cfg_Participant;
import com.TranEco.cardManagement.model.Cfg_Phone_Type;
import com.TranEco.cardManagement.model.Cfg_Pin_Printing_Data;
import com.TranEco.cardManagement.model.Cfg_Pin_Printing_Field_List;
import com.TranEco.cardManagement.model.Cfg_Pin_Printing_Fields;
import com.TranEco.cardManagement.model.Cfg_Status;
import com.TranEco.cardManagement.model.Interface_Config;


@Service
public class EhCacheServiceImpl implements EhCacheService 
{
	@Autowired
	EhCacheDaoImpl ehCacheDao;
	
	@Override
	/* @Cacheable("Cfg_Card_Template") */
	public HashMap<String, Cfg_Card_Template> getCfg_Card_Template() 
	{
		return ehCacheDao.getCfg_Card_Template();
	}

	@Override
	/* @Cacheable("Cfg_Participant") */
	public HashMap<String, Cfg_Participant> getCfg_participant() {
		return ehCacheDao.getCfg_participant();
	}

	@Override
	/* @Cacheable("Cfg_Card_Type") */
	public HashMap<String, Cfg_Card_Type> getCfg_Card_Type() {
		return ehCacheDao.getCfg_Card_Type();
	}

	@Override
	/* @Cacheable("Cfg_Bin") */
	public HashMap<String, Cfg_Bin> getCfg_Bin() {
		return ehCacheDao.getCfg_Bin();
	}

	@Override
	/* @Cacheable("Cfg_Address_Type") */
	public HashMap<String, Cfg_Address_Type> getCfg_Address_Type() {
		return ehCacheDao.getCfg_Address_Type();
	}

	@Override
	/* @Cacheable("Cfg_Phone_Type") */
	public HashMap<String, Cfg_Phone_Type> getCfg_Phone_Type() {
		return ehCacheDao.getCfg_Phone_Type();
	}

	@Override
	/* @Cacheable("Cfg_Email_Type") */
	public HashMap<String, Cfg_Email_Type> getCfg_Email_Type() {
		return ehCacheDao.getCfg_Email_Type();
	}

	@Override
	/* @Cacheable("Cfg_Account_Type") */
	public HashMap<String, Cfg_Account_Type> getCfg_Account_Type() {
		return ehCacheDao.getCfg_Account_Type();
	}

	@Override
	/* @Cacheable("Cfg_Status") */
	public HashMap<String, Cfg_Status> getCfg_Status() {
		return ehCacheDao.getCfg_Status();
	}
	
	@Override
	/* @Cacheable("Cfg_POS_Entry_Mode") */
	public HashMap<String, Cfg_POS_Entry_Mode> getCfg_POS_Entry_Mode() {
		return ehCacheDao.getCfg_POS_Entry_Mode();
	}
	
	@Override
	/* @Cacheable("Cfg_EMV_tags") */
	public List<Cfg_EMV_Tags> getCfg_EMV_Tags() {
		return ehCacheDao.getCfg_EMV_Tags();
	}
	
	@Override
	/* @Cacheable("Cfg_Pin_Printing_Data") */
	public HashMap<String, Cfg_Pin_Printing_Data> getCfg_Pin_Printing_Data() {
		return ehCacheDao.getCfg_Pin_Printing_Data();
	}
	
	@Override
	/* @Cacheable("Cfg_Pin_Printing_Fields") */
	public HashMap<String, Cfg_Pin_Printing_Fields> getCfg_Pin_Printing_Fields() {
		return ehCacheDao.getCfg_Pin_Printing_Fields();
	}
	
	@Override
	/* @Cacheable("Cfg_Pin_Printing_Field_List") */
	public List<Cfg_Pin_Printing_Field_List> getCfg_Pin_Printing_Field_List() {
		return ehCacheDao.getCfg_Pin_Printing_Field_List();
	}
	
	@Override
	/* @Cacheable("Cfg_Keys") */
	public HashMap<String, Cfg_Keys> getCfg_Keys() {
		return ehCacheDao.getCfg_Keys();
	}
	
	@Override
	/* @Cacheable("Cfg_Embossing_Data") */
	public List<Cfg_Embossing_Data> getCfg_Embossing_Data() {
		return ehCacheDao.getCfg_Embossing_Data();
	}

	@Override
	public HashMap<String, Cfg_Document_Type> getCfg_Document_Type() {
		return ehCacheDao.getCfg_Document_Type();
	}
	
	@Override
	public HashMap<String, Cfg_Card_Plastic> getCfg_Card_Plastic() {
		return ehCacheDao.getCfg_Card_Plastic();
	}
	
	/* @CacheEvict(value = "Cfg_Card_Template", allEntries = true) */
	public void refreshCfg_Card_Template() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_Participant", allEntries = true) */
	public void refreshCfg_Participant() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_Card_Type", allEntries = true) */
	public void refreshCfg_Card_Type() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_Bin", allEntries = true) */
	public void refreshCfg_Bin() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_Address_Type", allEntries = true) */
	public void refreshCfg_Address_Type() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_Phone_Type", allEntries = true) */
	public void refreshCfg_Phone_Type() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_Email_Type", allEntries = true) */
	public void refreshCfg_Email_Type() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_Account_Type", allEntries = true) */
	public void refreshCfg_Account_Type() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_Status", allEntries = true) */
	public void refreshCfg_Status() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_POS_Entry_Mode", allEntries = true) */
	public void refreshCfg_POS_Entry_Mode() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_EMV_tags", allEntries = true) */
	public void refreshCfg_EMV_tags() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_Pin_Printing_Data", allEntries = true) */
	public void refreshCfg_Pin_Printing_Data() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_Pin_Printing_Fields", allEntries = true) */
	public void refreshCfg_Pin_Printing_Fields() throws Throwable {}
	
	/* @CacheEvict(value = "Cfg_Embossing_Data", allEntries = true) */
	public void refreshCfg_Embossing_Data() throws Throwable {}
	
	
	@Override
	public void refreshCache() throws Throwable {
		refreshCfg_Card_Template();
		refreshCfg_Participant();
		refreshCfg_Card_Type();
		refreshCfg_Bin();
		refreshCfg_Address_Type();
		refreshCfg_Phone_Type();
		refreshCfg_Email_Type();
		refreshCfg_Account_Type();
		refreshCfg_Status();
		refreshCfg_POS_Entry_Mode();
		refreshCfg_EMV_tags();
		refreshCfg_Pin_Printing_Data();
		refreshCfg_Pin_Printing_Fields();
		refreshCfg_Embossing_Data();
	}

	@Override
	public List<Interface_Config> getInterface_Config() {
		return ehCacheDao.getInterface_Config();
	}

	@Override
	public List<Cfg_Interface_Key> getCfg_Interface_Key() {
		return ehCacheDao.getCfg_Interface_Key();
	}
	
}
