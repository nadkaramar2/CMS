package com.TranEco.cardManagement.scheduler.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.TranEco.cardManagement.debitCardManagement.dao.DebitCardIssuanceDaoImpl;
import com.TranEco.cardManagement.debitCardManagement.model.DebitCardIssuanceResponse;
import com.TranEco.cardManagement.scheduler.dao.SchedulerDao;
import com.TranEco.cardManagement.scheduler.model.CardTokenModel;
import com.TranEco.cardManagement.scheduler.model.MobileTokenModel;
import com.TranEco.cardManagement.scheduler.model.SchedulerModel;
import com.TranEco.cardManagement.utils.CardUtils;
import com.TranEco.cardManagement.utils.EncDecUtils;
import com.TranEco.cardManagement.utils.StringUtil;
import com.google.gson.Gson;

@Service
@Lazy
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	SchedulerDao schedulerDao;
	
	@Autowired
	DebitCardIssuanceDaoImpl debitCardIssuanceDao;
	
	@Autowired
	ThreadPoolTaskScheduler scheduler;
	
	@Autowired
	@Lazy
	SchedulerService schedulerService;
	
	@Autowired
	EncDecUtils encDecUtils;
	
	@Override
	public List<SchedulerModel> getAllScheduler() {
		return schedulerDao.getAllScheduler();
	}

	@Override
	public void schedulerTask(List<SchedulerModel> scheduleList) {
		for(SchedulerModel schedulerModel : scheduleList) {
			scheduleTask(schedulerModel);
		}
	}
	
	@Override
	public void scheduleTask(SchedulerModel schedulerModel) {
		Runnable runnable = new ScheduleTask(schedulerModel, schedulerDao);
		if(schedulerModel.getStrDemandFlag() == 1) {
			Thread thread = new Thread(runnable);
			thread.start();
		}else {
			if(StringUtils.isNoneBlank(schedulerModel.getCron())) {
				String cronExpr = StringUtils.substring(schedulerModel.getCron(), 0, schedulerModel.getCron().length()-1);
				scheduler.schedule(runnable, new CronTrigger(StringUtils.replace(cronExpr, "#", "/").replace("L", "28-31")  , TimeZone.getTimeZone(TimeZone.getDefault().getID())));
			}
		}
	}

	@Override
	public boolean addCardTokenTask(SchedulerModel schedulerModel)
	{
		
		Map<String, Object> map = schedulerDao.findCardToken(schedulerModel);
		if(map != null) 
		{
			Gson gson = new Gson();
			Optional<String> cardToken = debitCardIssuanceDao.getTokenCard(schedulerModel.getEncryptedCardNumber());
			if(cardToken != null && cardToken.isPresent()) 
			{
				int length = map.get("length") == null ? 12 : Integer.parseInt(""+map.get("length"));
				List<CardTokenModel> cardTokenList = new LinkedList<CardTokenModel>(Arrays.asList(gson.fromJson(cardToken.get(), CardTokenModel[].class)));
				cardTokenList.add(new CardTokenModel(CardUtils.generateCardToken(length), StringUtil.getCurrentDateTime()));
				String cardJson = gson.toJson(cardTokenList);
				schedulerDao.updateCardToken(String.valueOf(map.get("id")), cardJson);
			}
			else
			{
				List<CardTokenModel> cardTokenList = Arrays.asList(new CardTokenModel(CardUtils.generateCardToken(schedulerModel.getLength() == 0 ? 12 : schedulerModel.getLength()), StringUtil.getCurrentDateTime()));
				System.out.println("SchedulerServiceImpl.addCardTokenTask()"+cardTokenList);
				String cardJson = gson.toJson(cardTokenList);
				schedulerDao.addCardToken(String.valueOf(map.get("id")), String.valueOf(map.get("cardType")), cardJson);
			}
			return true;
		}else 
		{
			return false;
		}
	}

	@Override
	public String getMobileTokenDetails(String mobile) {
		return schedulerDao.getMobileTokenDetails(mobile);
	}

	@Override
	public int addMobileTokenDetails(MobileTokenModel mobileTokenModel) {
		return schedulerDao.addMobileTokenDetails(mobileTokenModel);
	}

	@Override
	public MobileTokenModel getMobileTokenScheduleDetails(String mobile) {
		MobileTokenModel tokenScheduler = schedulerDao.getMobileTokenScheduleDetails(mobile);
			return tokenScheduler;
	}

	@Override
	public int addMobileTokenScheduleDetails(MobileTokenModel mobileTokenModel) {
		return schedulerDao.addMobileTokenScheduleDetails(mobileTokenModel);
	}

	@Override
	public DebitCardIssuanceResponse scheduleMobileToken(MobileTokenModel mobileTokenModel) 
	{
		DebitCardIssuanceResponse response = new DebitCardIssuanceResponse();
		if(mobileTokenModel.getStrDemandFlag() == 1) 
		{
			Gson gson = new Gson();
			String token = getMobileTokenDetails(mobileTokenModel.getMobile());
			if(StringUtils.isNoneBlank(token)) 
			{
				List<MobileTokenModel> mobileTokenList = new LinkedList<MobileTokenModel>(Arrays.asList(gson.fromJson(token, MobileTokenModel[].class)));
				mobileTokenList.add(new MobileTokenModel(CardUtils.generateCardToken(mobileTokenModel.getStrLength() == 0 ? 12 : mobileTokenModel.getStrLength()), StringUtil.getCurrentDateTime()));
				mobileTokenModel.setMobileToken(gson.toJson(mobileTokenList));
				schedulerDao.updateMobileToken(mobileTokenModel);
			}
			else 
			{
				List<MobileTokenModel> cardToken = Arrays.asList(new MobileTokenModel(CardUtils.generateCardToken(mobileTokenModel.getStrLength() == 0 ? 12 : mobileTokenModel.getStrLength()), StringUtil.getCurrentDateTime()));
				MobileTokenModel mobileToken = new MobileTokenModel();
				mobileToken.setMobile(mobileTokenModel.getMobile());
				mobileToken.setMobileToken(gson.toJson(cardToken));
				schedulerDao.addMobileTokenDetails(mobileToken);
			}
		}else 
		{
			MobileTokenModel tokenDetails = getMobileTokenScheduleDetails(mobileTokenModel.getMobile());
			if(tokenDetails != null) 
			{
				response.setOutResponseCode("99");
				response.setMessage("Duplicate Mobile Token Request ");
				return response;
			}
			else 
			{
				addMobileTokenScheduleDetails(mobileTokenModel);
				schedulerMobileTask(Arrays.asList(mobileTokenModel));
			}
		}
		response.setOutResponseCode("00");
		response.setMessage("Success");
		return response;
	}

	@Override
	public List<MobileTokenModel> getAllMobileScheduler() {
		return schedulerDao.getAllMobileScheduler();
	}

	@Override
	public void schedulerMobileTask(List<MobileTokenModel> scheduleList) {
		for(MobileTokenModel schedulerModel : scheduleList) {
			Runnable runnable = new MobileSchedulerTask(schedulerModel, schedulerService);
			if(StringUtils.isNoneBlank(schedulerModel.getStrDemondCronExpr())) {
				String cronExpr = StringUtils.substring(schedulerModel.getStrDemondCronExpr(), 0, schedulerModel.getStrDemondCronExpr().length()-1);
				scheduler.schedule(runnable, new CronTrigger(StringUtils.replace(cronExpr, "#", "/").replace("L", "28-31")  , TimeZone.getTimeZone(TimeZone.getDefault().getID())));
			}
		}
	}

	@Override
	public List<MobileTokenModel> getMobileToken(String mobile) {
		String token = schedulerService.getMobileTokenDetails(mobile);
		if(StringUtils.isNoneBlank(token)) {
			Gson gson = new Gson();
			List<MobileTokenModel> mobileTokenList = new LinkedList<MobileTokenModel>(Arrays.asList(gson.fromJson(token, MobileTokenModel[].class)));
			return mobileTokenList;
		}else {
			return Collections.emptyList();
		}
	}

}
