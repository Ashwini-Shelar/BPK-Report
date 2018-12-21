package com.ln.bpk.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ln.bpk.model.StatusAudit;

@Repository("reportService")
public class BPKReportServiceImpl implements BPKReportService {

	@Autowired
	private JdbcTemplate jdbc;

	private static final Logger logger = LoggerFactory.getLogger(BPKReportServiceImpl.class);

	@Override
	public List<List> getTodaysCreatedOrderNumbers() {

		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MM-dd");
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("WIB"));
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("WIB"));
		cal.add(Calendar.DATE, -1);
		String startDate = dateTimeInGMT.format(cal.getTime()) + " " + "00:00:00";
		String endDate = dateTimeInGMT.format(cal.getTime()) + " " + "23:59:59";

		String sql = "select orderNo from status_audit where modified_date >= timestamp(?) and modified_date <= timestamp(?) and status like 'LP001';";
		try {
			List<List> orderList = jdbc.queryForList(sql, new Object[] { startDate, endDate }, List.class);
			return orderList;
		} catch (Exception ex) {
			logger.error("Exception: {}", ex);
			return null;
		}

	}

	@Override
	public List<StatusAudit> getHistoryByOrderNo(String orderNo) {
		List<StatusAudit> orderList = null;

		String sql = "select * from status_audit where orderNo = '" + orderNo + "' or awbNumber = '" + orderNo
				+ "' order by status;";
		try {
			orderList = jdbc.query(sql, new StatusAuditMapper());
			return orderList;
		} catch (Exception ex) {
			logger.info("Exception: {}", ex);
			return null;
		}
	}

	@Override
	public String getWebhookRequestPerStatus(String orderNo, String status) {
		String requestBody = null;
		String sql = "select webhookRequest from webhookServiceTxn where orderNoOrAwbNumber = '" + orderNo
				+ "' and status = '" + status + "' ";

		try {
			requestBody = jdbc.queryForObject(sql, String.class);
			logger.info("requestBody: {}", requestBody);
			return requestBody;
		} catch (Exception ex) {
			logger.info("Exception: {}", ex);
			return null;
		}

	}

	public static final class StatusAuditMapper implements RowMapper<StatusAudit> {

		@Override
		public StatusAudit mapRow(ResultSet rs, int rowNum) throws SQLException {
			StatusAudit oredrHistory = new StatusAudit();

			do {
				oredrHistory.setId(rs.getInt("id"));
				oredrHistory.setOrderNo(rs.getString("orderNo"));
				oredrHistory.setAwbNumber(rs.getString("awbNumber"));
				oredrHistory.setStatus(rs.getString("status"));
				oredrHistory.setModified_date(rs.getTimestamp("modified_date"));

			} while (rs.next());

			return oredrHistory;
		}
	}
}
