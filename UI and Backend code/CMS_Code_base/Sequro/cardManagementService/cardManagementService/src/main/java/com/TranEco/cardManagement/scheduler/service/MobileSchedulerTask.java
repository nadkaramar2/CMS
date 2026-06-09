package com.TranEco.cardManagement.scheduler.service;

import java.util.List;

import com.TranEco.cardManagement.scheduler.model.MobileTokenModel;

public class MobileSchedulerTask implements Runnable {

	private MobileTokenModel mobileTokenModels;
	private SchedulerService schedulerService;
	
	public MobileSchedulerTask() {
	}
	
	public MobileSchedulerTask(MobileTokenModel mobileTokenModels, SchedulerService schedulerService) {
		this.mobileTokenModels = mobileTokenModels;
		this.schedulerService = schedulerService;
	}	
	
	
	
	@Override
	public void run() {
			System.out.println("Mobile Scheduler Running....");
			mobileTokenModels.setStrDemandFlag(1);
			schedulerService.scheduleMobileToken(mobileTokenModels);
		}
}
