package com.traneco.report.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.traneco.configuration.model.TranMaster;
import com.traneco.report.model.CardHotListing;
import com.traneco.report.model.CardInventoryIssued;
import com.traneco.report.model.CardInventoryPending;
import com.traneco.report.model.HotListingMIS;
import com.traneco.report.model.TransactionDetails;

@Repository
public interface ReportDao {

	//Added by prashant Tayde 
	
	public List<TransactionDetails> getTransactionReportDetails();
	
	public List<TranMaster> getSearchDetails(String fromDate, String todate);
	
	public List<CardInventoryIssued> getCardInventoryIssued(String fromDate, String todate);
	
	public List<CardInventoryPending> getCardInventoryPending(String fromDate, String todate);
	
	public List<CardHotListing> getCardHotListing(String fromDate, String todate);
	
	public List<HotListingMIS> getHotListingMIS(String fromDate, String todate);
	
	public List<HotListingMIS> getHotListingMISnetwork(String fromDate, String todate);
	
	public int getTotalHotListingMIS(String fromDate, String todate);

	
	//Ended by Prashant Tayde
	
}
