package com.traneco.report.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.traneco.common.SessionDTO;
import com.traneco.common.constants.QueryConstant;
import com.traneco.configuration.model.TranMaster;
import com.traneco.report.model.CardHotListing;
import com.traneco.report.model.CardInventoryIssued;
import com.traneco.report.model.CardInventoryPending;
import com.traneco.report.model.HotListingMIS;
import com.traneco.report.model.TransactionDetails;

@Repository
public class ReportDaoImpl implements ReportDao {

	@Autowired
	JdbcTemplate jdbcCMSTemplate;

	@Autowired
	SessionDTO sessionDTO;
	
	
	//Added by prashant Tayde 
	
	@Override
	public List<TransactionDetails> getTransactionReportDetails() {
		List<TransactionDetails> getReports = jdbcCMSTemplate.query(QueryConstant.GET_TRANSACTION_REPO,
				new BeanPropertyRowMapper<TransactionDetails>(TransactionDetails.class));
		return getReports;
	}

	@Override
	public List<TranMaster> getSearchDetails(String fromDate, String todate) {
		List<TranMaster> getSearchReports = jdbcCMSTemplate.query(QueryConstant.GET_SEARCH_TRANSACTION,
				new BeanPropertyRowMapper<TranMaster>(TranMaster.class), new Object[] { fromDate, todate });

		return getSearchReports;

	}

	@Override
	public List<CardInventoryIssued> getCardInventoryIssued(String fromDate, String todate) {
		List<CardInventoryIssued> getCardInventoryIssued = jdbcCMSTemplate.query(QueryConstant.GET_CARD_INVENTORY,
				new BeanPropertyRowMapper<CardInventoryIssued>(CardInventoryIssued.class),
				new Object[] { fromDate, todate });

		return getCardInventoryIssued;
	}

	@Override
	public List<CardInventoryPending> getCardInventoryPending(String fromDate, String todate) {
		List<CardInventoryPending> getCardInventoryPending = jdbcCMSTemplate.query(QueryConstant.CARD_INVENTORY_PENDING,
				new BeanPropertyRowMapper<CardInventoryPending>(CardInventoryPending.class),
				new Object[] { fromDate, todate });

		return getCardInventoryPending;
	}

	@Override
	public List<CardHotListing> getCardHotListing(String fromDate, String todate) {
		List<CardHotListing> getCardHotListing = jdbcCMSTemplate.query(QueryConstant.CARD_HOT_LISTING,
				new BeanPropertyRowMapper<CardHotListing>(CardHotListing.class),
				new Object[] { fromDate, todate });
		System.out.println("ReportDaoImpl.getCardHotListing()" + getCardHotListing);
		return getCardHotListing;
	}

	@Override
	public List<HotListingMIS> getHotListingMIS(String fromDate, String todate) {
		List<HotListingMIS> getHotListingMIS = jdbcCMSTemplate.query(QueryConstant.HOT_LISTING_MIS,
				new BeanPropertyRowMapper<HotListingMIS>(HotListingMIS.class), new Object[] { fromDate, todate });

		return getHotListingMIS;
	}

	@Override
	public List<HotListingMIS> getHotListingMISnetwork(String fromDate, String todate) {
		List<HotListingMIS> getHotListingMISnetwork = jdbcCMSTemplate.query(QueryConstant.HOT_LISTING_MISNETWORK,
				new BeanPropertyRowMapper<HotListingMIS>(HotListingMIS.class), new Object[] { fromDate, todate });

		return getHotListingMISnetwork;
	}

	@Override
	public int getTotalHotListingMIS(String fromDate, String todate) {
		int mis = jdbcCMSTemplate
				.queryForObject(QueryConstant.TOTAL_HOT_LISTING_MIS, new Object[] { fromDate, todate }, Integer.class)
				.intValue();

		return mis;
	}
	
	
	//Ended by Prashant Tayde

}
