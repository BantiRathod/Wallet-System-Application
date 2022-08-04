package com.walletApplication.Dto;

import lombok.Data;

@Data
public class UserWalletUpdateEntity {
	private long amount;
	private String status;

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

}
