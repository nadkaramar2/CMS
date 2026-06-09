package com.TranEco.cardManagement.EmbossingProcess.services;

import org.apache.log4j.Logger;

import com.TranEco.cardManagement.EmbossingProcess.dao.EmbossDao;

public class UpdateEmbossFlag extends Thread{
	
	private static Logger logger = Logger.getLogger(UpdateEmbossFlag.class.getPackage().getName());
	
	private String cardNo;
	private EmbossDao embossDao;
	
	public static void updateCardFlag(String cardNo, EmbossDao embossDao) {
		logger.info("updateCardFlag Trigger------- ");
		try {
		UpdateEmbossFlag trigger = new UpdateEmbossFlag();
		trigger.cardNo = cardNo;
		trigger.embossDao = embossDao;
		trigger.start();
		}catch (Exception e) 
		{
			logger.info("Error "+e.getMessage(),e);
		}
	}
	
	
	@Override
	public void run() {
		try {
			int count = this.embossDao.embossFlag(this.cardNo);
			logger.info("CardNo "+count);
		}catch (Exception e) {
			logger.info("Error "+e.getMessage(),e);
		}
	}
	
}
