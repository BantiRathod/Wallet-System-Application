package com.walletApplication.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserWalletEntity implements Wallet {
	@Id
	private String userId;
	private long amount;
	private String status;

	public UserWalletEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserWalletEntity(String userId, long amount, String status) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.status = status;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserWalletEntity [userId=" + userId + ", amount=" + amount + ", status=" + status + "]";
	}
    
	
	
	
	

}	