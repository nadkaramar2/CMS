package com.TranEco.cardManagement.transaction.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class TransactionRequest {
	
	private String mit;
	private String partAcq;
	private String mc;
	private String mt;
	private String msgType;
	private String pan;
	private String panPrefix;
	private String amount;
	private String transTime;
	private String stan;
	private String dt;
	private String mcat;
	private String panEntry;
	private String pinEntry;
	private String condCode;
	private String posPinCC;
	private String rc;
	private String track2;
	private String rrn;
	private String expiry;
	private String serviceCode;
	private String tid;
	private String mid;
	private LocationData loc;
	private String currency;
	private DE48 de48;
	private DE55 de55;
	private String dccMargin;
	private String mob_Vouch_NR;
	private String mob_Vouch_SRL_NR;
	private String mob_Vouch_Exp_DT;
	private String mob_Vouch_Phone_NR;
	private String mob_Operator_ID;
	private String mob_Vouch_Data;
	private String bna4all;
	private String mt_order_id;
	private String mt_secret_code;
	private String bna4llreg;
	private String term;
	private String cseq;
	private String accountFrom;
	private String accountTo;
	private String emv;
	private String pac_1;
	private String dcc;
	private String aiic;
	private String proccode;
	private String date;
	private String time;
	private DE95 de95;
	private String account_ident_1;
	private String account_ident_2;
	private String bmp120;
	private String de124;
	private Map<String, Object> request;
	
	public TransactionRequest() {
		request = new LinkedHashMap<>();
	}
	
	public String getMit() {
		return mit;
	}
	public void setMit(String mit) {
		this.mit = mit;
	}
	public String getPartAcq() {
		return partAcq;
	}
	public void setPartAcq(String partAcq) {
		this.partAcq = partAcq;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getMt() {
		return mt;
	}
	public void setMt(String mt) {
		this.mt = mt;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPanPrefix() {
		return panPrefix;
	}
	public void setPanPrefix(String panPrefix) {
		this.panPrefix = panPrefix;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getStan() {
		return stan;
	}
	public void setStan(String stan) {
		this.stan = stan;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getMcat() {
		return mcat;
	}
	public void setMcat(String mcat) {
		this.mcat = mcat;
	}
	public String getPanEntry() {
		return panEntry;
	}
	public void setPanEntry(String panEntry) {
		this.panEntry = panEntry;
	}
	public String getPinEntry() {
		return pinEntry;
	}
	public void setPinEntry(String pinEntry) {
		this.pinEntry = pinEntry;
	}
	public String getCondCode() {
		return condCode;
	}
	public void setCondCode(String condCode) {
		this.condCode = condCode;
	}
	public String getPosPinCC() {
		return posPinCC;
	}
	public void setPosPinCC(String posPinCC) {
		this.posPinCC = posPinCC;
	}
	public String getRc() {
		return rc;
	}
	public void setRc(String rc) {
		this.rc = rc;
	}
	public String getTrack2() {
		return track2;
	}
	public void setTrack2(String track2) {
		this.track2 = track2;
	}
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public LocationData getLoc() {
		return loc;
	}
	public void setLoc(LocationData loc) {
		this.loc = loc;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public DE48 getDe48() {
		return de48;
	}
	public void setDe48(DE48 de48) {
		this.de48 = de48;
	}
	public DE55 getDe55() {
		return de55;
	}
	public void setDe55(DE55 de55) {
		this.de55 = de55;
	}
	public String getDccMargin() {
		return dccMargin;
	}
	public void setDccMargin(String dccMargin) {
		this.dccMargin = dccMargin;
	}
	public String getMob_Vouch_NR() {
		return mob_Vouch_NR;
	}
	public void setMob_Vouch_NR(String mob_Vouch_NR) {
		this.mob_Vouch_NR = mob_Vouch_NR;
	}
	public String getMob_Vouch_SRL_NR() {
		return mob_Vouch_SRL_NR;
	}
	public void setMob_Vouch_SRL_NR(String mob_Vouch_SRL_NR) {
		this.mob_Vouch_SRL_NR = mob_Vouch_SRL_NR;
	}
	public String getMob_Vouch_Exp_DT() {
		return mob_Vouch_Exp_DT;
	}
	public void setMob_Vouch_Exp_DT(String mob_Vouch_Exp_DT) {
		this.mob_Vouch_Exp_DT = mob_Vouch_Exp_DT;
	}
	public String getMob_Vouch_Phone_NR() {
		return mob_Vouch_Phone_NR;
	}
	public void setMob_Vouch_Phone_NR(String mob_Vouch_Phone_NR) {
		this.mob_Vouch_Phone_NR = mob_Vouch_Phone_NR;
	}
	public String getMob_Operator_ID() {
		return mob_Operator_ID;
	}
	public void setMob_Operator_ID(String mob_Operator_ID) {
		this.mob_Operator_ID = mob_Operator_ID;
	}
	public String getMob_Vouch_Data() {
		return mob_Vouch_Data;
	}
	public void setMob_Vouch_Data(String mob_Vouch_Data) {
		this.mob_Vouch_Data = mob_Vouch_Data;
	}
	public String getBna4all() {
		return bna4all;
	}
	public void setBna4all(String bna4all) {
		this.bna4all = bna4all;
	}
	public String getMt_order_id() {
		return mt_order_id;
	}
	public void setMt_order_id(String mt_order_id) {
		this.mt_order_id = mt_order_id;
	}
	public String getMt_secret_code() {
		return mt_secret_code;
	}
	public void setMt_secret_code(String mt_secret_code) {
		this.mt_secret_code = mt_secret_code;
	}
	public String getBna4llreg() {
		return bna4llreg;
	}
	public void setBna4llreg(String bna4llreg) {
		this.bna4llreg = bna4llreg;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getCseq() {
		return cseq;
	}
	public void setCseq(String cseq) {
		this.cseq = cseq;
	}
	public String getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(String accountFrom) {
		this.accountFrom = accountFrom;
	}
	public String getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(String accountTo) {
		this.accountTo = accountTo;
	}
	public String getEmv() {
		return emv;
	}
	public void setEmv(String emv) {
		this.emv = emv;
	}
	public String getPac_1() {
		return pac_1;
	}
	public void setPac_1(String pac_1) {
		this.pac_1 = pac_1;
	}
	public String getDcc() {
		return dcc;
	}
	public void setDcc(String dcc) {
		this.dcc = dcc;
	}
	public String getAiic() {
		return aiic;
	}
	public void setAiic(String aiic) {
		this.aiic = aiic;
	}
	public String getProccode() {
		return proccode;
	}
	public void setProccode(String proccode) {
		this.proccode = proccode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public DE95 getDe95_OR() {
		return de95;
	}
	public void setDe95_OR(DE95 de95) {
		this.de95 = de95;
	}
	public String getAccount_ident_1() {
		return account_ident_1;
	}
	public void setAccount_ident_1(String account_ident_1) {
		this.account_ident_1 = account_ident_1;
	}
	public String getAccount_ident_2() {
		return account_ident_2;
	}
	public void setAccount_ident_2(String account_ident_2) {
		this.account_ident_2 = account_ident_2;
	}
	public String getBmp120() {
		return bmp120;
	}
	public void setBmp120(String bmp120) {
		this.bmp120 = bmp120;
	}
	public String getDe124() {
		return de124;
	}
	public void setDe124(String de124) {
		this.de124 = de124;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransactionRequest [mit=");
		builder.append(mit);
		builder.append(", partAcq=");
		builder.append(partAcq);
		builder.append(", mc=");
		builder.append(mc);
		builder.append(", mt=");
		builder.append(mt);
		builder.append(", msgType=");
		builder.append(msgType);
		builder.append(", pan=");
		builder.append(pan);
		builder.append(", panPrefix=");
		builder.append(panPrefix);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", transTime=");
		builder.append(transTime);
		builder.append(", stan=");
		builder.append(stan);
		builder.append(", dt=");
		builder.append(dt);
		builder.append(", mcat=");
		builder.append(mcat);
		builder.append(", panEntry=");
		builder.append(panEntry);
		builder.append(", pinEntry=");
		builder.append(pinEntry);
		builder.append(", condCode=");
		builder.append(condCode);
		builder.append(", posPinCC=");
		builder.append(posPinCC);
		builder.append(", rc=");
		builder.append(rc);
		builder.append(", track2=");
		builder.append(track2);
		builder.append(", rrn=");
		builder.append(rrn);
		builder.append(", expiry=");
		builder.append(expiry);
		builder.append(", serviceCode=");
		builder.append(serviceCode);
		builder.append(", tid=");
		builder.append(tid);
		builder.append(", mid=");
		builder.append(mid);
		builder.append(", loc=");
		builder.append(loc);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", de48=");
		builder.append(de48);
		builder.append(", de55=");
		builder.append(de55);
		builder.append(", dccMargin=");
		builder.append(dccMargin);
		builder.append(", mob_Vouch_NR=");
		builder.append(mob_Vouch_NR);
		builder.append(", mob_Vouch_SRL_NR=");
		builder.append(mob_Vouch_SRL_NR);
		builder.append(", mob_Vouch_Exp_DT=");
		builder.append(mob_Vouch_Exp_DT);
		builder.append(", mob_Vouch_Phone_NR=");
		builder.append(mob_Vouch_Phone_NR);
		builder.append(", mob_Operator_ID=");
		builder.append(mob_Operator_ID);
		builder.append(", mob_Vouch_Data=");
		builder.append(mob_Vouch_Data);
		builder.append(", bna4all=");
		builder.append(bna4all);
		builder.append(", mt_order_id=");
		builder.append(mt_order_id);
		builder.append(", mt_secret_code=");
		builder.append(mt_secret_code);
		builder.append(", bna4llreg=");
		builder.append(bna4llreg);
		builder.append(", term=");
		builder.append(term);
		builder.append(", cseq=");
		builder.append(cseq);
		builder.append(", accountFrom=");
		builder.append(accountFrom);
		builder.append(", accountTo=");
		builder.append(accountTo);
		builder.append(", emv=");
		builder.append(emv);
		builder.append(", pac_1=");
		builder.append(pac_1);
		builder.append(", dcc=");
		builder.append(dcc);
		builder.append(", aiic=");
		builder.append(aiic);
		builder.append(", proccode=");
		builder.append(proccode);
		builder.append(", date=");
		builder.append(date);
		builder.append(", time=");
		builder.append(time);
		builder.append(", de95=");
		builder.append(de95);
		builder.append(", account_ident_1=");
		builder.append(account_ident_1);
		builder.append(", account_ident_2=");
		builder.append(account_ident_2);
		builder.append(", bmp120=");
		builder.append(bmp120);
		builder.append(", de124=");
		builder.append(de124);
		builder.append("]");
		return builder.toString();
	}
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	
}

