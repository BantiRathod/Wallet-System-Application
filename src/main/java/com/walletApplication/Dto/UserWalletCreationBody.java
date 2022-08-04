package com.walletApplication.Dto;

import lombok.Data;

@Data
public class UserWalletCreationBody {
	private String userId;
	private long amount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}


}
