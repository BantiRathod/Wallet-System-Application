package com.walletApplication.Dto;

public class ZomatoTransactionsRequestBody {
	private String senderUserId;
	private String receiverUserId;
	private long amount;
	private int orderId;
	public ZomatoTransactionsRequestBody() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ZomatoTransactionsRequestBody(String senderUserId, String receiverUserId, long amount, int orderId) {
		super();
		this.senderUserId = senderUserId;
		this.receiverUserId = receiverUserId;
		this.amount = amount;
		this.orderId = orderId;
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
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "ZomatoTransactionsRequestBody [senderUserId=" + senderUserId + ", receiverUserId=" + receiverUserId
				+ ", amount=" + amount + ", orderId=" + orderId + "]";
	}
	
	


}
