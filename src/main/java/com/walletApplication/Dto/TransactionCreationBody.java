package com.walletApplication.Dto;

import lombok.Data;

@Data
public class TransactionCreationBody {
	private String senderUserId;
	private String receiverUserId;
	private long amount;

	public String getSenderUserId() {
		return senderUserId;
	}

	public void setSenderUserId(String senderUserId) {
		this.senderUserId = senderUserId;
	}

	public String getReceiverUserId() {
		return receiverUserId;
	}

	public void setReceiverUserId(String receiverUserId) {
		this.receiverUserId = receiverUserId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}


}
