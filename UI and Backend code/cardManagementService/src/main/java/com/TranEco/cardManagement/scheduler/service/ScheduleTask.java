package com.TranEco.cardManagement.scheduler.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.TranEco.cardManagement.scheduler.dao.SchedulerDao;
import com.TranEco.cardManagement.scheduler.model.CardTokenModel;
import com.TranEco.cardManagement.scheduler.model.SchedulerModel;
import com.TranEco.cardManagement.utils.CardUtils;
import com.TranEco.cardManagement.utils.StringUtil;
import com.google.gson.Gson;

public class ScheduleTask implements Runnable {

	private SchedulerModel schedulerModel = null;
	private SchedulerDao schedulerDao;
	
	
	public ScheduleTask(SchedulerModel schedulerModel, SchedulerDao schedulerDao) {
		this.schedulerModel = schedulerModel;
		this.schedulerDao = schedulerDao;
	}
	
	@Override
	public void run() {
		System.out.println("Running........"+schedulerModel);
		List<String> cardIds = schedulerDao.getCardId(schedulerModel.getCardType());
		Map<String, String> cardTokenList = schedulerDao.getCardTokenList(schedulerModel.getCardType());
		updateOrCreateCardToken(cardIds, cardTokenList, schedulerModel, schedulerDao);
	}
	
	private void updateOrCreateCardToken(List<String> cardIds, Map<String, String> cardTokenList, SchedulerModel schedulerModel, SchedulerDao schedulerDao) {
		Gson gson = new Gson();
		for(String id : cardIds) {
			String data = cardTokenList.get(id);
			if(data != null) {
				List<CardTokenModel> cardToken = new LinkedList<CardTokenModel>(Arrays.asList(gson.fromJson(data, CardTokenModel[].class)));
				cardToken.add(new CardTokenModel(CardUtils.generateCardToken(schedulerModel.getLength()), StringUtil.getCurrentDateTime()));
				String cardJson = gson.toJson(cardToken);
				schedulerDao.updateCardToken(id, cardJson);
			}else {
				List<CardTokenModel> cardToken = Arrays.asList(new CardTokenModel(CardUtils.generateCardToken(schedulerModel.getLength()), StringUtil.getCurrentDateTime()));
				String cardJson = gson.toJson(cardToken);
				schedulerDao.addCardToken(id, schedulerModel.getCardType(), cardJson);
			}
		}
	}

}
