package com.TranEco.cardManagement.participantNode.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.TranEco.cardManagement.common.QueryConstant;

@Repository
public class ParticipantNodeDaoImpl implements ParticipantNodeDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void updateNodeStatus(String partId, String nodeStatus) {
		jdbcTemplate.update(QueryConstant.UPDATE_PARTICIPANT_NODE, new Object[] {
				nodeStatus,
				new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
				partId
		});
	}
	
	
	
}
