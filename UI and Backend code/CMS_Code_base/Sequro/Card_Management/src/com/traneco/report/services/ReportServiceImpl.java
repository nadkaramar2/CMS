package com.traneco.report.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.traneco.configuration.model.TranMaster;
import com.traneco.report.dao.ReportDao;
import com.traneco.report.model.CardHotListing;
import com.traneco.report.model.CardInventoryIssued;
import com.traneco.report.model.CardInventoryPending;
import com.traneco.report.model.HotListingMIS;
import com.traneco.report.model.TransactionDetails;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	ReportDao reportDao;

	@Autowired
	Environment env;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	//Added by prashant Tayde
	
	@Override
	public List<TransactionDetails> getTransactionReportDetails() {

		return reportDao.getTransactionReportDetails();
	}

	@Override
	public List<TranMaster> getSearchDetails(String fromDate, String todate) 
	{
		return this.reportDao.getSearchDetails(fromDate, todate);
	}

	@Override
	public List<CardInventoryIssued> getCardInventoryIssued(String fromDate, String todate) {

		return this.reportDao.getCardInventoryIssued(fromDate, todate);
	}

	@Override
	public List<CardInventoryPending> getCardInventoryPending(String fromDate, String todate) {

		return this.reportDao.getCardInventoryPending(fromDate, todate);
	}

	@Override
	public List<CardHotListing> getCardHotListing(String fromDate, String todate) {

		return this.reportDao.getCardHotListing(fromDate, todate);
	}

	@Override
	public List<HotListingMIS> getHotListingMIS(String fromDate, String todate) {

		return this.reportDao.getHotListingMIS(fromDate, todate);
	}

	@Override
	public List<HotListingMIS> getHotListingMISNetwork(String fromDate, String todate) {

		return this.reportDao.getHotListingMISnetwork(fromDate, todate);
	}

	public int getTotalHotListingMIS(String fromDate, String todate) {

		return this.reportDao.getTotalHotListingMIS(fromDate, todate);
	}

	//Ended by prashant Tayde
	
}
