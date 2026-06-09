package com.traneco.configuration.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class TxnData implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String de002_pan;
	private String de003_processing_code;
	private String de004_amount;
	private String de005_settle_amount;
	private String de007_transaction_date;
	private String de009_conversion_rate_settle;
	private String de011_stan;
	private String de012_local_time;
	private String de013_local_date;
	private String de014_expiry_date;
	private String de015_settlement_date;
	private String de016_conversion_date;
	private String de018_mcc;
	private String de019_acquiring_currency_code;
	private String de022_pos_entry_mode;
	private String de023_card_sequence_nr;
	private String de024_nii;
	private String de025_pos_condition_code;
	private String de026_tran_fee_amount;
	private String de027_tran_fee_settle;
	private String de032_acquirer_inst_id;
	private String de033_forward_inst_id;
	private String de035_track_2;
	private String de037_rrn;
	private String de038_auth_code;
	private String de039_response;
	private String de040_service_restriction_code;
	private String de041_terminal_id;
	private String de042_mid;
	private String de043_merchant_name_loc;
	private String de044_additional_rsp_data;
	private String de045_track_1;
	private String de048_additional_data;
	private String de049_tran_currency_code;
	private String de050_settle_currency_code;
	private String de052_pin_data;
	private String de053_security_control_info;
	private String de054_additional_amount;
	private String de055_emv;
	private String de056_message_reason_code;
	private String de057_acquirer_rec_id;
	private String de058_network_data;
	private String de059_echo_data;
	private String de060_terminal_batch;
	private String de061_additional_pos_amount;
	private String de062_terminal_invoice;
	private String de063_total_req_rsp;
	private String de065_terminal_device_id;
	private String de066_terminal_imei;
	private String de067_terminal_mobile_number;
	private String de068_terminal_encrypted_pan;
	private String de069_retention_data;
	private String de070_network_management_code;
	private String de090_original_data;
	private String de095_replacement_amount;
	private String de102_from_account_numer;
	private String de103_to_account_number;
	private String de104_terminal_geaographical_data;
	private String de120_pos_data_code;
	private String de121_reserve_field;
	private String de122_text_field;
	private String de126_switch_private_data;
	private String de127_private_data;
	private String sys_id;
	private String tran_type;
	private String from_account;
	private String to_account;
	private String switch_terminal_settle_id;
	private String switch_terminal_batch_nr;
	private String txn_id;
	private String previous_txn_id;
	private String next_txn_id;
	//private int participant_id;
	//private Integer participant_id;
	private String participant_id;
	private String sponsor_code;
	private String source_node;
	private String destination_node;
	private String destination_config;
	private String network_name;
	private String reversal_attempt_count;
	private String instrument;
	private String sms_dms_type;
	private String is_international;
	// CARD/UPI/QRCODE/IMPS/ ->by device
	private String instrumentType;
	private String sessionKey;
	private String otp;
	private String payerMobileNo;
	private String ksn;
	private String ksn1;
	private String is_two_ksn;
	// UPI FIELD
	private String tranId;
	private String msgId;
	private String networkTxnReqDateTime;
	private String networkTxnResDateTime;
	private String loadBalanceId;
	private String switchInsertDateTime;

	private String bankTxnRefNo;
	private String bankTxnDate;
	private String bankRrn;
	private String bankStan;
	private String bankAuthcode;
	private String fromAccountName;
	private String bankResCode;
	private String bankTxnStatus;
	private String bankTxnDesc;
	private String prodType;
	private String refId;
	private String payeeInstrument;
	private String payerInstrument;
	private String payeeMobileNo;
	private String payerName;
	private String subMid;
	private String minAmount;
	private String expirePeriod;
	private String seqNo;
	private String payeeEmailId;
	private String payerEmailId;
	private String bankAlias;
	private String pincode;
	private String note;
	private String cardProduct;
	private String cardDesc;
	private String cardBankName;
	private String cardBin;
	private String cardMasked;
	private String cardType;
	private String isInternationalCard;
	private String isAccountType;
	
	private String accountNumber;
	private String accountType;
	private String channel;
	private String channelCode;
	
	private String strAccountNumber;
	
	@Override
	public String toString() {
		return "TxnData [de002_pan=" + de002_pan + ", de003_processing_code=" + de003_processing_code
				+ ", de004_amount=" + de004_amount + ", de005_settle_amount=" + de005_settle_amount
				+ ", de007_transaction_date=" + de007_transaction_date + ", de009_conversion_rate_settle="
				+ de009_conversion_rate_settle + ", de011_stan=" + de011_stan + ", de012_local_time=" + de012_local_time
				+ ", de013_local_date=" + de013_local_date + ", de014_expiry_date=" + de014_expiry_date
				+ ", de015_settlement_date=" + de015_settlement_date + ", de016_conversion_date="
				+ de016_conversion_date + ", de018_mcc=" + de018_mcc + ", de019_acquiring_currency_code="
				+ de019_acquiring_currency_code + ", de022_pos_entry_mode=" + de022_pos_entry_mode
				+ ", de023_card_sequence_nr=" + de023_card_sequence_nr + ", de024_nii=" + de024_nii
				+ ", de025_pos_condition_code=" + de025_pos_condition_code + ", de026_tran_fee_amount="
				+ de026_tran_fee_amount + ", de027_tran_fee_settle=" + de027_tran_fee_settle
				+ ", de032_acquirer_inst_id=" + de032_acquirer_inst_id + ", de033_forward_inst_id="
				+ de033_forward_inst_id + ", de035_track_2=" + de035_track_2 + ", de037_rrn=" + de037_rrn
				+ ", de038_auth_code=" + de038_auth_code + ", de039_response=" + de039_response
				+ ", de040_service_restriction_code=" + de040_service_restriction_code + ", de041_terminal_id="
				+ de041_terminal_id + ", de042_mid=" + de042_mid + ", de043_merchant_name_loc="
				+ de043_merchant_name_loc + ", de044_additional_rsp_data=" + de044_additional_rsp_data
				+ ", de045_track_1=" + de045_track_1 + ", de048_additional_data=" + de048_additional_data
				+ ", de049_tran_currency_code=" + de049_tran_currency_code + ", de050_settle_currency_code="
				+ de050_settle_currency_code + ", de052_pin_data=" + de052_pin_data + ", de053_security_control_info="
				+ de053_security_control_info + ", de054_additional_amount=" + de054_additional_amount + ", de055_emv="
				+ de055_emv + ", de056_message_reason_code=" + de056_message_reason_code + ", de057_acquirer_rec_id="
				+ de057_acquirer_rec_id + ", de058_network_data=" + de058_network_data + ", de059_echo_data="
				+ de059_echo_data + ", de060_terminal_batch=" + de060_terminal_batch + ", de061_additional_pos_amount="
				+ de061_additional_pos_amount + ", de062_terminal_invoice=" + de062_terminal_invoice
				+ ", de063_total_req_rsp=" + de063_total_req_rsp + ", de065_terminal_device_id="
				+ de065_terminal_device_id + ", de066_terminal_imei=" + de066_terminal_imei
				+ ", de067_terminal_mobile_number=" + de067_terminal_mobile_number + ", de068_terminal_encrypted_pan="
				+ de068_terminal_encrypted_pan + ", de069_retention_data=" + de069_retention_data
				+ ", de070_network_management_code=" + de070_network_management_code + ", de090_original_data="
				+ de090_original_data + ", de095_replacement_amount=" + de095_replacement_amount
				+ ", de102_from_account_numer=" + de102_from_account_numer + ", de103_to_account_number="
				+ de103_to_account_number + ", de104_terminal_geaographical_data=" + de104_terminal_geaographical_data
				+ ", de120_pos_data_code=" + de120_pos_data_code + ", de121_reserve_field=" + de121_reserve_field
				+ ", de122_text_field=" + de122_text_field + ", de126_switch_private_data=" + de126_switch_private_data
				+ ", de127_private_data=" + de127_private_data + ", sys_id=" + sys_id + ", tran_type=" + tran_type
				+ ", from_account=" + from_account + ", to_account=" + to_account + ", switch_terminal_settle_id="
				+ switch_terminal_settle_id + ", switch_terminal_batch_nr=" + switch_terminal_batch_nr + ", txn_id="
				+ txn_id + ", previous_txn_id=" + previous_txn_id + ", next_txn_id=" + next_txn_id + ", participant_id="
				+ participant_id + ", sponsor_code=" + sponsor_code + ", source_node=" + source_node
				+ ", destination_node=" + destination_node + ", destination_config=" + destination_config
				+ ", network_name=" + network_name + ", reversal_attempt_count=" + reversal_attempt_count
				+ ", instrument=" + instrument + ", sms_dms_type=" + sms_dms_type + ", is_international="
				+ is_international + ", instrumentType=" + instrumentType + ", sessionKey=" + sessionKey + ", otp="
				+ otp + ", payerMobileNo=" + payerMobileNo + ", ksn=" + ksn + ", ksn1=" + ksn1 + ", is_two_ksn="
				+ is_two_ksn + ", tranId=" + tranId + ", msgId=" + msgId + ", networkTxnReqDateTime="
				+ networkTxnReqDateTime + ", networkTxnResDateTime=" + networkTxnResDateTime + ", loadBalanceId="
				+ loadBalanceId + ", switchInsertDateTime=" + switchInsertDateTime + ", bankTxnRefNo=" + bankTxnRefNo
				+ ", bankTxnDate=" + bankTxnDate + ", bankRrn=" + bankRrn + ", bankStan=" + bankStan + ", bankAuthcode="
				+ bankAuthcode + ", fromAccountName=" + fromAccountName + ", bankResCode=" + bankResCode
				+ ", bankTxnStatus=" + bankTxnStatus + ", bankTxnDesc=" + bankTxnDesc + ", prodType=" + prodType
				+ ", refId=" + refId + ", payeeInstrument=" + payeeInstrument + ", payerInstrument=" + payerInstrument
				+ ", payeeMobileNo=" + payeeMobileNo + ", payerName=" + payerName + ", subMid=" + subMid
				+ ", minAmount=" + minAmount + ", expirePeriod=" + expirePeriod + ", seqNo=" + seqNo + ", payeeEmailId="
				+ payeeEmailId + ", payerEmailId=" + payerEmailId + ", bankAlias=" + bankAlias + ", pincode=" + pincode
				+ ", note=" + note + ", cardProduct=" + cardProduct + ", cardDesc=" + cardDesc + ", cardBankName="
				+ cardBankName + ", cardBin=" + cardBin + ", cardMasked=" + cardMasked + ", cardType=" + cardType
				+ ", isInternationalCard=" + isInternationalCard + ", isAccountType=" + isAccountType
				+ ", accountNumber=" + accountNumber + ", accountType=" + accountType + ", channel=" + channel
				+ ", channelCode=" + channelCode + "]";
	}
	
	
}