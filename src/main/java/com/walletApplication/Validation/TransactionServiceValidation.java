package com.walletApplication.Validation;

public class TransactionServiceValidation {
	public static void validateTransactionSenderUserId(String senderUserId) throws Exception {

		if (senderUserId.length() != 10)
			throw new Exception("invalid Sender UserId");
	}

	public static void validateTransactionReceiverUserId(String receiverUserId) throws Exception {

		if (receiverUserId.length() != 10)
			throw new Exception("invalid Sender UserId");
	}

	public static void validateTransactionAmount(long amount) throws Exception {
	      
		if (amount == 0)
			throw new Exception("not valid amount");
	}

	public static void validateTransactionStatus(String status) throws Exception {
	
		if (status.length() == 0)
			throw new Exception("invalid status");
	}

	public static void validateTransactionId(int transactionId) throws Exception {
		String id;
		id = String.valueOf(transactionId);
		if (id.length() == 0) {
			throw new Exception("wrong order Id");
		}
		
	}
	
	public static void validateTransactionType(String transactionType) throws Exception {
		if (transactionType.length() == 0)
			throw new Exception("invalid type");
	}
	
	public static void validateOrderId(int orderId) throws Exception {
		String id;
		id = String.valueOf(orderId);
		if (id.length() == 0) {
			throw new Exception("wrong order Id");
		}
		
	}

}
