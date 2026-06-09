package com.TranEco.cardManagement.scheduler.service;

import java.util.List;

import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceResponse;
import com.TranEco.cardManagement.scheduler.model.MobileTokenModel;
import com.TranEco.cardManagement.scheduler.model.SchedulerModel;

public interface SchedulerService 
{
	public List<SchedulerModel> getAllScheduler();
	public void schedulerTask(List<SchedulerModel> scheduleList);
	public void scheduleTask(SchedulerModel schedulerModel);
	public boolean addCardTokenTask(SchedulerModel schedulerModel);
	public String getMobileTokenDetails(String mobile);
	public int addMobileTokenDetails(MobileTokenModel mobileTokenModel);
	public MobileTokenModel getMobileTokenScheduleDetails(String mobile);
	public int addMobileTokenScheduleDetails(MobileTokenModel mobileTokenModel);
	public DebitCardIssuanceResponse scheduleMobileToken(MobileTokenModel mobileTokenModel);
	public List<MobileTokenModel> getAllMobileScheduler();
	public void schedulerMobileTask(List<MobileTokenModel> scheduleList);
	public List<MobileTokenModel> getMobileToken(String mobile);
}
