package com.TranEco.cardManagement.dao;

import java.util.HashMap;
import java.util.List;

import com.TranEco.cardManagement.model.Cfg_Account_Type;
import com.TranEco.cardManagement.model.Cfg_Address_Type;
import com.TranEco.cardManagement.model.Cfg_Bin;
import com.TranEco.cardManagement.model.Cfg_Card_Plastic;
import com.TranEco.cardManagement.model.Cfg_Card_Template;
import com.TranEco.cardManagement.model.Cfg_Card_Type;
import com.TranEco.cardManagement.model.Cfg_Document_Type;
import com.TranEco.cardManagement.model.Cfg_Email_Type;
import com.TranEco.cardManagement.model.Cfg_Participant;
import com.TranEco.cardManagement.model.Cfg_Phone_Type;
import com.TranEco.cardManagement.model.Cfg_Status;
import com.TranEco.cardManagement.model.Interface_Config;
import com.TranEco.cardManagement.model.Transaction_Routing;
import com.TranEco.cardManagement.model.Cfg_Pin_Printing_Data;
import com.TranEco.cardManagement.model.Cfg_Pin_Printing_Fields;
import com.TranEco.cardManagement.model.Cfg_POS_Entry_Mode;
import com.TranEco.cardManagement.model.Cfg_EMV_Tags;
import com.TranEco.cardManagement.model.Cfg_Pin_Printing_Field_List;
import com.TranEco.cardManagement.model.Cfg_Keys;
import com.TranEco.cardManagement.model.Cfg_Embossing_Data;
import com.TranEco.cardManagement.model.Cfg_Interface_Key;

public interface EhCacheDao {
	public HashMap<String, Cfg_Card_Template> getCfg_Card_Template();
	public HashMap<String, Cfg_Participant> getCfg_participant();
	public HashMap<String, Cfg_Card_Type> getCfg_Card_Type();
	public HashMap<String, Cfg_Bin> getCfg_Bin();
	public HashMap<String, Cfg_Address_Type> getCfg_Address_Type();
	public HashMap<String, Cfg_Phone_Type> getCfg_Phone_Type();
	public HashMap<String, Cfg_Email_Type> getCfg_Email_Type();
	public HashMap<String, Cfg_Account_Type> getCfg_Account_Type();
	public HashMap<String, Cfg_Status> getCfg_Status();
	public HashMap<String, Cfg_POS_Entry_Mode> getCfg_POS_Entry_Mode();
	public List<Cfg_EMV_Tags> getCfg_EMV_Tags();
	public HashMap<String,Cfg_Pin_Printing_Data> getCfg_Pin_Printing_Data();
	public HashMap<String,Cfg_Pin_Printing_Fields> getCfg_Pin_Printing_Fields();
	public List<Cfg_Pin_Printing_Field_List> getCfg_Pin_Printing_Field_List();
	public HashMap<String,Cfg_Keys> getCfg_Keys();
	public List<Cfg_Embossing_Data> getCfg_Embossing_Data();
	public HashMap<String, Cfg_Document_Type> getCfg_Document_Type();
	public HashMap<String, Cfg_Card_Plastic> getCfg_Card_Plastic();
	public List<Interface_Config> getInterface_Config();
	public List<Cfg_Interface_Key> getCfg_Interface_Key();
	public List<Transaction_Routing> transactionRouting();
}
