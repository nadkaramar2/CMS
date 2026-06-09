package com.traneco.configuration.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class TxnReqRes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TxnHeader header;
	private UserInfo userInfo;
	private DeviceInfo deviceInfo;
	private TxnData txnData;
	private ProcessResponse response;
	private List<TxnDetail> txnDetail = new ArrayList<TxnDetail>();

	public TxnHeader getHeader() {
		return header;
	}

	public void setHeader(TxnHeader header) {
		this.header = header;
	}

	public DeviceInfo getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(DeviceInfo deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public ProcessResponse getResponse() {
		return response;
	}

	public void setResponse(ProcessResponse response) {
		this.response = response;
	}

	public TxnData getTxnData() {
		return txnData;
	}

	public void setTxnData(TxnData txnData) {
		this.txnData = txnData;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<TxnDetail> getTxnDetail() {
		return txnDetail;
	}

	public void setTxnDetail(List<TxnDetail> txnDetail) {
		this.txnDetail = txnDetail;
	}

	@Override
	public String toString() {
		return "TxnReqRes [header=" + header + ", userInfo=" + userInfo + ", deviceInfo=" + deviceInfo + ", txnData="
				+ txnData + ", response=" + response + ", txnDetail=" + txnDetail + "]";
	}

	
}
