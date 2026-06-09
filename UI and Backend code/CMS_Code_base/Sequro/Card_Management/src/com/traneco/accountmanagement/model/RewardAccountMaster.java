package com.traneco.accountmanagement.model;

import lombok.Data;

@Data
public class RewardAccountMaster 
{
  
	private String id;
	private String participantId;
	private String custId;
	private String accountType;
	private String rewardAccountNo;
	private String transactingAccountNo;
	private String cid;
	private String accountClosingDate;
	private String accountOpeningDate;
	private String closingPointsBalance;
	private String totalPointsRedeemed;
	private String lastRedeemedDate;
	private String lastRedeemedPoints;
	private String cashbackBalanceAmt;
	private String totalCashbackRedeemed;
	private String lastCashbackRedeemedDate;
	private String lastCashbackRedeemedAmt;
}
