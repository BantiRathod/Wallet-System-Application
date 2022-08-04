package com.walletApplication.Validation;

public class UserWalletServiceValidation {
	public static void validateUserWalletId(String userId) throws Exception {

		if (userId.length() != 10)
			throw new Exception("invalid userId");
	}

	public static void validateUserWalletAmount(long amount) throws Exception {
		
		if (amount == 0)
			throw new Exception("not valid amount");
	}

	public static void validateUserWalletStatus(String status) throws Exception {
		if (status.length() == 0)
			throw new Exception("invalid status");
	}
}
