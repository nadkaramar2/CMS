package com.traneco.report.controller;

import java.util.List;

import com.traneco.configuration.model.TranMaster;
import com.traneco.report.model.CardHotListing;
import com.traneco.report.model.CardInventoryIssued;
import com.traneco.report.model.CardInventoryPending;

//Author By Prashant T - 29 AUg 2023

public interface CmsReportService 
{

	//For Transaction Report
	String getDownloadedTransactionReportFilePath(List<TranMaster> tranMaster);

	String getDownloadTransactionReportFilePathForExcel(List<TranMaster> tranMaster);
	
	
	//For Card Inventory Issued Report
	String getDownloadedCardInventoryIssuedReportFilePath(List<CardInventoryIssued> cardInventoryIssued);
	
	String getDownloadedCardInventoryIssuedReportFilePathForExcel(List<CardInventoryIssued> tranMaster);
	
	
	//For Card Inventory Pending Report
	String getDownloadedCardInventoryPendingReportFilePath(List<CardInventoryPending> cardInventoryPending);
	
	String getDownloadedCardInventoryPendingReportFilePathForExcel(List<CardInventoryPending> cardInventoryPending);
		
	//For Card HotListing Report
	String getDownloadedCardHotListingReportFilePath(List<CardHotListing> cardHotListing);
	
	String getDownloadedCardHotListingReportFilePathForExcel(List<CardHotListing> cardHotListing);


}

