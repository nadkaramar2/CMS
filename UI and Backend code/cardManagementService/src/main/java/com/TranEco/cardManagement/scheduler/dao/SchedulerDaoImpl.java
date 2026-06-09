package com.TranEco.cardManagement.scheduler.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;
import com.TranEco.cardManagement.scheduler.model.MobileTokenModel;
import com.TranEco.cardManagement.scheduler.model.SchedulerModel;
import com.TranEco.cardManagement.utils.CardUtils;
import com.TranEco.cardManagement.utils.EncDecUtils;

@Repository
@Lazy
public class SchedulerDaoImpl implements SchedulerDao 
{
	@Autowired
	@Lazy
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	EncDecUtils encDecUtils;
	
	@Autowired
	CardUtils cardUtils;
	
	@Override
	public List<SchedulerModel> getAllScheduler() 
	{
		return jdbcTemplate.query(QueryConstant.GET_SCHEDULE_CARD_TOKEN,
				new BeanPropertyRowMapper<SchedulerModel>(SchedulerModel.class));
	}

	@Override
	public List<String> getCardId(String cardType) 
	{
		return jdbcTemplate.query(QueryConstant.GET_CARD_ID, new Object[] { cardType },
				(rs, rownum) -> new String(rs.getString("id")));
	}

	@Override
	public Map<String, String> getCardTokenList(String cardType) 
	{
		Map<String, String> cardMap = new HashMap<String, String>();
		List<Map<String, Object>> cardList = jdbcTemplate.queryForList(QueryConstant.GET_CARD_TOKEN,
				new Object[] { cardType });
		for (Map m : cardList) 
		{
			cardMap.put(String.valueOf(m.get("card_id")), String.valueOf(m.get("token_details")));
		}
		return cardMap;
	}

	@Override
	public int addCardToken(String id, String cardType, String token) 
	{
		return jdbcTemplate.update(QueryConstant.INSERT_CARD_TOKEN, new Object[] { id, cardType, token });
	}

	@Override
	public int updateCardToken(String id, String token) 
	{
		return jdbcTemplate.update(QueryConstant.UPDATE_CARD_TOKEN, new Object[] { token, id });
	}

	@Override
	public Map<String, Object> findCardToken(SchedulerModel schedulerModel) 
	{
		try {
			
			List<Map<String, Object>> rows;
			//String encCardNo = cardUtils.encrypt(schedulerModel.getCardNo());
			//String[] parts = encCardNo.split(":");
			rows = jdbcTemplate.queryForList(QueryConstant.FIND_CARD_TOKEN,
					//new Object[] {parts[0]});
					new Object[] {schedulerModel.getEncryptedCardNumber()});
			if (rows != null && !rows.isEmpty()) 
			{
				return rows.get(0);
			}
			else 
			{
				return null;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public String getTokenDetails(String cardId, String cardType) {
		return null; // jdbcTemplate.queryForObject(QueryConstant.GET_TOKEN, new Object[] { cardId,
						// cardType }, Optional.class);
	}

	@Override
	public String getMobileTokenDetails(String mobile) 
	{
		try 
		{
			return jdbcTemplate.queryForObject(QueryConstant.GET_MOBILE_TOKEN, new Object[] { mobile }, String.class);
		}
		catch (Exception e) 
		{
			return null;
		}
	}

	@Override
	public int addMobileTokenDetails(MobileTokenModel mobileTokenModel) 
	{
		return jdbcTemplate.update(QueryConstant.ADD_MOBILE_TOKEN,
				new Object[] { mobileTokenModel.getMobile(), mobileTokenModel.getMobileToken() });
	}

	@Override
	public MobileTokenModel getMobileTokenScheduleDetails(String mobile) 
	{
		try 
		{
			return jdbcTemplate.queryForObject(QueryConstant.GET_MOBILE_TOKEN_SCHEDULER, new Object[] { mobile },
					new BeanPropertyRowMapper<MobileTokenModel>(MobileTokenModel.class));
		}
		catch (EmptyResultDataAccessException e) 
		{
			return null;
		}
	}

	@Override
	public int addMobileTokenScheduleDetails(MobileTokenModel mobileTokenModel) 
	{
		return jdbcTemplate.update(QueryConstant.ADD_MOBILE_CFG_TOKEN,
				new Object[] { mobileTokenModel.getMobile(), mobileTokenModel.getStrLength(),
						mobileTokenModel.getStrDemandFlag(), mobileTokenModel.getStrDemondCronExpr(),
						mobileTokenModel.getEnDesc() });
	}

	@Override
	public int updateMobileToken(MobileTokenModel mobileTokenModel) 
	{
		return jdbcTemplate.update(QueryConstant.UPDATE_MOBILE_TOKEN,
				new Object[] { mobileTokenModel.getMobileToken(), mobileTokenModel.getMobile() });
	}

	@Override
	public List<MobileTokenModel> getAllMobileScheduler() 
	{
		return jdbcTemplate.query(QueryConstant.GET_ALL_MOBILE_TOKEN,
				new BeanPropertyRowMapper<MobileTokenModel>(MobileTokenModel.class));
	}

}
