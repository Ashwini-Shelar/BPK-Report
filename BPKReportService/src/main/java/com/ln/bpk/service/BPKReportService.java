package com.ln.bpk.service;

import java.util.List;

import com.ln.bpk.model.StatusAudit;


public interface BPKReportService {

	public List<List> getTodaysCreatedOrderNumbers();

	public List<StatusAudit> getHistoryByOrderNo(String orderNo);

	public String getWebhookRequestPerStatus(String orderNo, String status);

	
}
