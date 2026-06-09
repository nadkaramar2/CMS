package com.TranEco.cardManagement.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.model.Cfg_Account_Type;
import com.TranEco.cardManagement.model.Cfg_Address_Type;
import com.TranEco.cardManagement.model.Cfg_Bin;
import com.TranEco.cardManagement.model.Cfg_Card_Template;
import com.TranEco.cardManagement.model.Cfg_Card_Type;
import com.TranEco.cardManagement.model.Cfg_Document_Type;
import com.TranEco.cardManagement.model.Cfg_Email_Type;
import com.TranEco.cardManagement.model.Cfg_Participant;
import com.TranEco.cardManagement.model.Cfg_Phone_Type;
import com.TranEco.cardManagement.model.Cfg_Status;
import com.TranEco.cardManagement.model.Interface_Config;
import com.TranEco.cardManagement.model.Transaction_Routing;
import com.TranEco.cardManagement.model.Cfg_POS_Entry_Mode;
import com.TranEco.cardManagement.model.Cfg_EMV_Tags;
import com.TranEco.cardManagement.model.Cfg_Pin_Printing_Data;
import com.TranEco.cardManagement.model.Cfg_Pin_Printing_Fields;
import com.TranEco.cardManagement.model.Cfg_Pin_Printing_Field_List;
import com.TranEco.cardManagement.model.Cfg_Keys;
import com.TranEco.cardManagement.model.Cfg_Embossing_Data;
import com.TranEco.cardManagement.model.Cfg_Interface_Key;
import com.TranEco.cardManagement.model.Cfg_Card_Plastic;

@Repository
public class EhCacheDaoImpl implements EhCacheDao 
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public HashMap<String, Cfg_Card_Template> getCfg_Card_Template() 
	{
		Map<String, Cfg_Card_Template> card_Template_map = new HashMap<String, Cfg_Card_Template>();
		List<Cfg_Card_Template> card_Template_list = jdbcTemplate.query(QueryConstant.Get_Cfg_Card_Template,
				new BeanPropertyRowMapper<Cfg_Card_Template>(Cfg_Card_Template.class));
		for (Cfg_Card_Template card_template : card_Template_list) 
		{
			card_Template_map.put(card_template.getParticipant_id() + card_template.getCard_type_id(), card_template);
		}
		return (HashMap<String, Cfg_Card_Template>) card_Template_map;
	}

	@Override
	public HashMap<String, Cfg_Participant> getCfg_participant() 
	{
		Map<String, Cfg_Participant> cfg_participant_map = new HashMap<String, Cfg_Participant>();
		List<Cfg_Participant> cfg_participant_list = jdbcTemplate.query(QueryConstant.Get_Cfg_participant,
				new BeanPropertyRowMapper<Cfg_Participant>(Cfg_Participant.class));
		for (Cfg_Participant cfg_participant : cfg_participant_list) 
		{
			cfg_participant_map.put(cfg_participant.getParticipant_name(), cfg_participant);
		}
		return (HashMap<String, Cfg_Participant>) cfg_participant_map;
	}

	@Override
	public HashMap<String, Cfg_Card_Type> getCfg_Card_Type() 
	{
		Map<String, Cfg_Card_Type> cfg_card_type_map = new HashMap<String, Cfg_Card_Type>();
		List<Cfg_Card_Type> cfg_card_type_list = jdbcTemplate.query(QueryConstant.Get_Cfg_Card_Type,
				new BeanPropertyRowMapper<Cfg_Card_Type>(Cfg_Card_Type.class));
		for (Cfg_Card_Type cfg_card_type : cfg_card_type_list) {
			cfg_card_type_map.put(cfg_card_type.getParticipant_id() + cfg_card_type.getId(), cfg_card_type);
		}
		return (HashMap<String, Cfg_Card_Type>) cfg_card_type_map;
	}

	@Override
	public HashMap<String, Cfg_Bin> getCfg_Bin() 
	{
		Map<String, Cfg_Bin> cfg_Bin_map = new HashMap<String, Cfg_Bin>();
		List<Cfg_Bin> cfg_Bin_list = jdbcTemplate.query(QueryConstant.Get_Gfg_Bin,
				new BeanPropertyRowMapper<Cfg_Bin>(Cfg_Bin.class));
		for (Cfg_Bin cfg_Bin : cfg_Bin_list) 
		{
			cfg_Bin_map.put(cfg_Bin.getParticipant_id(), cfg_Bin);
		}
		return (HashMap<String, Cfg_Bin>) cfg_Bin_map;
	}

	@Override
	public HashMap<String, Cfg_Address_Type> getCfg_Address_Type() 
	{
		Map<String, Cfg_Address_Type> cfg_address_type_map = new HashMap<String, Cfg_Address_Type>();
		List<Cfg_Address_Type> cfg_address_type_list = jdbcTemplate.query(QueryConstant.Get_Cfg_Address_Type,
				new BeanPropertyRowMapper<Cfg_Address_Type>(Cfg_Address_Type.class));
		for (Cfg_Address_Type cfg_address_type : cfg_address_type_list) 
		{
			cfg_address_type_map.put(cfg_address_type.getParticipant_id() + cfg_address_type.getId(), cfg_address_type);
		}
		return (HashMap<String, Cfg_Address_Type>) cfg_address_type_map;
	}

	@Override
	public HashMap<String, Cfg_Phone_Type> getCfg_Phone_Type() 
	{
		Map<String, Cfg_Phone_Type> cfg_phone_type_map = new HashMap<String, Cfg_Phone_Type>();
		List<Cfg_Phone_Type> cfg_phone_type_list = jdbcTemplate.query(QueryConstant.Get_Cfg_Phone_Type,
				new BeanPropertyRowMapper<Cfg_Phone_Type>(Cfg_Phone_Type.class));
		for (Cfg_Phone_Type cfg_phone_type : cfg_phone_type_list) {
			cfg_phone_type_map.put(cfg_phone_type.getParticipant_id() + cfg_phone_type.getId(), cfg_phone_type);
		}
		return (HashMap<String, Cfg_Phone_Type>) cfg_phone_type_map;
	}

	@Override
	public HashMap<String, Cfg_Email_Type> getCfg_Email_Type() {
		Map<String, Cfg_Email_Type> cfg_email_type_map = new HashMap<String, Cfg_Email_Type>();
		List<Cfg_Email_Type> cfg_email_type_list = jdbcTemplate.query(QueryConstant.Get_Cfg_Email_Type,
				new BeanPropertyRowMapper<Cfg_Email_Type>(Cfg_Email_Type.class));
		for (Cfg_Email_Type cfg_email_type : cfg_email_type_list) {
			cfg_email_type_map.put(cfg_email_type.getParticipant_id() + cfg_email_type.getId(), cfg_email_type);
		}
		return (HashMap<String, Cfg_Email_Type>) cfg_email_type_map;
	}

	@Override
	public HashMap<String, Cfg_Account_Type> getCfg_Account_Type() 
	{
		Map<String, Cfg_Account_Type> cfg_account_type_map = new HashMap<String, Cfg_Account_Type>();
		List<Cfg_Account_Type> cfg_account_type_list = jdbcTemplate.query(QueryConstant.Get_Cfg_Account_Type,
				new BeanPropertyRowMapper<Cfg_Account_Type>(Cfg_Account_Type.class));
		for (Cfg_Account_Type cfg_account_type : cfg_account_type_list) 
		{
			cfg_account_type_map.put(cfg_account_type.getParticipant_id() + cfg_account_type.getId(), cfg_account_type);
		}
		return (HashMap<String, Cfg_Account_Type>) cfg_account_type_map;
	}

	@Override
	public HashMap<String, Cfg_Status> getCfg_Status() 
	{
		Map<String, Cfg_Status> cfg_Status_map = new HashMap<String, Cfg_Status>();
		List<Cfg_Status> Cfg_Status_list = jdbcTemplate.query(QueryConstant.Get_Cfg_Status,
				new BeanPropertyRowMapper<Cfg_Status>(Cfg_Status.class));
		for (Cfg_Status cfg_Status : Cfg_Status_list) 
		{
			cfg_Status_map.put(cfg_Status.getId(), cfg_Status);
		}
		return (HashMap<String, Cfg_Status>) cfg_Status_map;
	}

	@Override
	public HashMap<String, Cfg_POS_Entry_Mode> getCfg_POS_Entry_Mode() {
		Map<String, Cfg_POS_Entry_Mode> cfg_POS_Entry_Mode_map = new HashMap<String, Cfg_POS_Entry_Mode>();
		List<Cfg_POS_Entry_Mode> Cfg_POS_Entry_Mode_list = jdbcTemplate.query(QueryConstant.Get_Cfg_POS_Entry_Mode,
				new BeanPropertyRowMapper<Cfg_POS_Entry_Mode>(Cfg_POS_Entry_Mode.class));
		for (Cfg_POS_Entry_Mode cfg_POS_Entry_Mode : Cfg_POS_Entry_Mode_list) {
			cfg_POS_Entry_Mode_map.put(cfg_POS_Entry_Mode.getParticipant_id(), cfg_POS_Entry_Mode);
		}
		return (HashMap<String, Cfg_POS_Entry_Mode>) cfg_POS_Entry_Mode_map;
	}

	@Override
	public List<Cfg_EMV_Tags> getCfg_EMV_Tags() {
		List<Cfg_EMV_Tags> Cfg_EMV_Tags_list = jdbcTemplate.query(QueryConstant.Get_Cfg_EMV_Tags,
				new BeanPropertyRowMapper<Cfg_EMV_Tags>(Cfg_EMV_Tags.class));
		return Cfg_EMV_Tags_list;
	}

	@Override
	public HashMap<String, Cfg_Pin_Printing_Data> getCfg_Pin_Printing_Data() {
		Map<String, Cfg_Pin_Printing_Data> cfg_Pin_Printing_Data_map = new HashMap<String, Cfg_Pin_Printing_Data>();
		List<Cfg_Pin_Printing_Data> Cfg_Pin_Printing_Data_list = jdbcTemplate.query(
				QueryConstant.Get_Cfg_Pin_Printing_Data,
				new BeanPropertyRowMapper<Cfg_Pin_Printing_Data>(Cfg_Pin_Printing_Data.class));
		for (Cfg_Pin_Printing_Data cfg_Pin_Printing_Data : Cfg_Pin_Printing_Data_list) {
			cfg_Pin_Printing_Data_map.put(
					cfg_Pin_Printing_Data.getParticipant_id() + cfg_Pin_Printing_Data.getPinmailer_format(),
					cfg_Pin_Printing_Data);
		}
		return (HashMap<String, Cfg_Pin_Printing_Data>) cfg_Pin_Printing_Data_map;
	}

	@Override
	public HashMap<String, Cfg_Pin_Printing_Fields> getCfg_Pin_Printing_Fields() {
		Map<String, Cfg_Pin_Printing_Fields> cfg_Pin_Printing_Fields_map = new HashMap<String, Cfg_Pin_Printing_Fields>();
		List<Cfg_Pin_Printing_Fields> Cfg_Pin_Printing_Fields_list = jdbcTemplate.query(
				QueryConstant.Get_Cfg_Pin_Printing_Fields,
				new BeanPropertyRowMapper<Cfg_Pin_Printing_Fields>(Cfg_Pin_Printing_Fields.class));
		for (Cfg_Pin_Printing_Fields cfg_Pin_Printing_Fields : Cfg_Pin_Printing_Fields_list) {
			cfg_Pin_Printing_Fields_map.put(cfg_Pin_Printing_Fields.getParticipant_id()
					+ cfg_Pin_Printing_Fields.getPin_printing_format() + cfg_Pin_Printing_Fields.getSequence_no(),
					cfg_Pin_Printing_Fields);
		}
		return (HashMap<String, Cfg_Pin_Printing_Fields>) cfg_Pin_Printing_Fields_map;
	}

	@Override
	public List<Cfg_Pin_Printing_Field_List> getCfg_Pin_Printing_Field_List() {
		List<Cfg_Pin_Printing_Field_List> Cfg_Pin_Printing_Field_list = jdbcTemplate.query(
				QueryConstant.Get_Cfg_Pin_Printing_Fields,
				new BeanPropertyRowMapper<Cfg_Pin_Printing_Field_List>(Cfg_Pin_Printing_Field_List.class));
		return Cfg_Pin_Printing_Field_list;
	}

	@Override
	public HashMap<String, Cfg_Keys> getCfg_Keys() {
		Map<String, Cfg_Keys> cfg_Keys_map = new HashMap<String, Cfg_Keys>();
		List<Cfg_Keys> Cfg_Keys_list = jdbcTemplate.query(QueryConstant.Get_Cfg_Keys,
				new BeanPropertyRowMapper<Cfg_Keys>(Cfg_Keys.class));
		for (Cfg_Keys Cfg_Keys : Cfg_Keys_list) {
			cfg_Keys_map.put(Cfg_Keys.getParticipant_id() + Cfg_Keys.getCard_type(), Cfg_Keys);
		}
		return (HashMap<String, Cfg_Keys>) cfg_Keys_map;
	}

	@Override
	public List<Cfg_Embossing_Data> getCfg_Embossing_Data() 
	{
		List<Cfg_Embossing_Data> Cfg_Embossing_Data_list = jdbcTemplate.query(QueryConstant.Get_Cfg_Keys,
				new BeanPropertyRowMapper<Cfg_Embossing_Data>(Cfg_Embossing_Data.class));
		return Cfg_Embossing_Data_list;
	}

	@Override
	public HashMap<String, Cfg_Document_Type> getCfg_Document_Type() {
		Map<String, Cfg_Document_Type> cfg_Document_map = new HashMap<String, Cfg_Document_Type>();
		List<Cfg_Document_Type> Cfg_Document_list = jdbcTemplate.query(QueryConstant.Get_Document_Type,
				new BeanPropertyRowMapper<Cfg_Document_Type>(Cfg_Document_Type.class));
		for (Cfg_Document_Type Cfg_Document_Type : Cfg_Document_list) {
			cfg_Document_map.put(Cfg_Document_Type.getId(), Cfg_Document_Type);
		}
		return (HashMap<String, Cfg_Document_Type>) cfg_Document_map;
	}

	@Override
	public HashMap<String, Cfg_Card_Plastic> getCfg_Card_Plastic() {
		Map<String, Cfg_Card_Plastic> cfg_Card_Plastic_map = new HashMap<String, Cfg_Card_Plastic>();
		List<Cfg_Card_Plastic> Cfg_Card_Plastic_list = jdbcTemplate.query(QueryConstant.Get_Cfg_Card_Plastic,
				new BeanPropertyRowMapper<Cfg_Card_Plastic>(Cfg_Card_Plastic.class));
		for (Cfg_Card_Plastic Cfg_Card_Plastic : Cfg_Card_Plastic_list) {
			cfg_Card_Plastic_map.put(Cfg_Card_Plastic.getParticipant_id() + Cfg_Card_Plastic.getCard_type(),
					Cfg_Card_Plastic);
		}
		return (HashMap<String, Cfg_Card_Plastic>) cfg_Card_Plastic_map;
	}

	@Override
	public List<Interface_Config> getInterface_Config() 
	{
		return jdbcTemplate.query(QueryConstant.GET_CFG_INTERFACE,
				new BeanPropertyRowMapper<Interface_Config>(Interface_Config.class));
	}

	@Override
	public List<Cfg_Interface_Key> getCfg_Interface_Key() 
	{
		return jdbcTemplate.query(QueryConstant.GET_INTERFACE_KEY,
				new BeanPropertyRowMapper<Cfg_Interface_Key>(Cfg_Interface_Key.class));
	}

	@Override
	public List<Transaction_Routing> transactionRouting() 
	{
		return jdbcTemplate.query(QueryConstant.GET_TXN_ROUTING,
				new BeanPropertyRowMapper<Transaction_Routing>(Transaction_Routing.class));
	}

}
