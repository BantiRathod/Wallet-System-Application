package com.walletApplication.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MerchantWalletEntity implements Wallet {
	@Id
	private String merchantId;
	private long amount;
	private String status;

	public MerchantWalletEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MerchantWalletEntity(String merchantId, long amount, String status) {
		super();
		this.merchantId = merchantId;
		this.amount = amount;
		this.status = status;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MerchantWalletEntity [merchantId=" + merchantId + ", amount=" + amount + ", status=" + status + "]";
	}

}
