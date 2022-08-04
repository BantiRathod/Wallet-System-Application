package com.walletApplication.Dto;

import lombok.Data;

@Data
public class MerchantWalletCreationBody {
	private String merchantId;
	private long amount;

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


}
