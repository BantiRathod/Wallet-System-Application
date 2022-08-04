package com.walletApplication.Entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransactionEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;

	private int orderId;
	private String transactionType;
	private String senderUserId;
	private String receiverUserId;
	private Date date;
	private long amount;
	private String status;



	public TransactionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	public TransactionEntity(int transactionId, int orderId, String transactionType, String senderUserId,
			String receiverUserId, Date date, long amount, String status) {
		super();
		this.transactionId = transactionId;
		this.orderId = orderId;
		this.transactionType = transactionType;
		this.senderUserId = senderUserId;
		this.receiverUserId = receiverUserId;
		this.date = date;
		this.amount = amount;
		this.status = status;
	}



	public int getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}



	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}



	public String getTransactionType() {
		return transactionType;
	}



	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}



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



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
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
		return "TransactionEntity [transactionId=" + transactionId + ", orderId=" + orderId + ", transactionType="
				+ transactionType + ", senderUserId=" + senderUserId + ", receiverUserId=" + receiverUserId + ", date="
				+ date + ", amount=" + amount + ", status=" + status + "]";
	}
	
	
	
	
	
}