package com.ln.bpk.model;

public class BPKReportModel {

	private String orderNo;
	@Override
	public String toString() {
		return "BPKReportModel [orderNo=" + orderNo + ", sttNumber=" + sttNumber + ", lastWebhookStatus="
				+ lastWebhookStatus + ", webhookRequest=" + webhookRequest + ", timestamp=" + timestamp + "]";
	}

	private String sttNumber;
	private String lastWebhookStatus;
	private String webhookRequest;
	private String timestamp;

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getWebhookRequest() {
		return webhookRequest;
	}

	public void setWebhookRequest(String webhookRequest) {
		this.webhookRequest = webhookRequest;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSttNumber() {
		return sttNumber;
	}

	public void setSttNumber(String sttNumber) {
		this.sttNumber = sttNumber;
	}

	public String getLastWebhookStatus() {
		return lastWebhookStatus;
	}

	public void setLastWebhookStatus(String lastWebhookStatus) {
		this.lastWebhookStatus = lastWebhookStatus;
	}
}
