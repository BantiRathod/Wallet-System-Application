package com.walletApplication.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Settelment {

	
	private long amount;
	private int totalTran;
	@Id
	private String merchantId;
	private String status;
	
	public Settelment()
	{	
	}
	
	public Settelment(long amount, int totalTran, String merchantId, String status) {
		super();
		this.amount = amount;
		this.totalTran = totalTran;
		this.merchantId = merchantId;
		this.status = status;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public int getTotalTran() {
		return totalTran;
	}
	public void setTotalTran(int totalTran) {
		this.totalTran = totalTran;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
