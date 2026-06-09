package com.TranEco.cardManagement.scheduler.dao;

import java.util.List;
import java.util.Map;

import com.TranEco.cardManagement.scheduler.model.MobileTokenModel;
import com.TranEco.cardManagement.scheduler.model.SchedulerModel;

public interface SchedulerDao {
	public List<SchedulerModel> getAllScheduler();
	public List<String> getCardId(String cardType);
	public Map<String,String> getCardTokenList(String cardType);
	public int addCardToken(String id, String cardType, String token);
	public int updateCardToken(String id, String token); 
	public Map<String, Object> findCardToken(SchedulerModel schedulerModel);
	public String getTokenDetails(String cardId, String cardType);
	public String getMobileTokenDetails(String mobile);
	public int addMobileTokenDetails(MobileTokenModel mobileTokenModel);
	public MobileTokenModel getMobileTokenScheduleDetails(String mobile);
	public int addMobileTokenScheduleDetails(MobileTokenModel mobileTokenModel);
	public int updateMobileToken(MobileTokenModel mobileTokenModel);
	public List<MobileTokenModel> getAllMobileScheduler();
}
