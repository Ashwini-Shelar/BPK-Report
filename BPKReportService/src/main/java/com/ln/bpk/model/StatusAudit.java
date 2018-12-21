package com.ln.bpk.model;

import java.sql.Timestamp;

public class StatusAudit {

	@Override
	public String toString() {
		return "StatusAudit [id=" + id + ", orderNo=" + orderNo + ", status=" + status + ", modified_date="
				+ modified_date + ", awbNumber=" + awbNumber + "]";
	}

	private int id;
	private String orderNo;
	private String status;
	private Timestamp modified_date;
	private String awbNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getModified_date() {
		return modified_date;
	}

	public void setModified_date(Timestamp modified_date) {
		this.modified_date = modified_date;
	}

	public String getAwbNumber() {
		return awbNumber;
	}

	public void setAwbNumber(String awbNumber) {
		this.awbNumber = awbNumber;
	}
}
