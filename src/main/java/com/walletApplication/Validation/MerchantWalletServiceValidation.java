package com.walletApplication.Validation;

public class MerchantWalletServiceValidation {
	public static void validateMerchantWalletId(String merchantId) throws Exception {

		if (merchantId.length() != 10)
			throw new Exception("invalid merchantId");
	}

	public static void validateMerchantWalletAmount(long amount) throws Exception {
		
		
		if (amount == 0)
			throw new Exception("not valid amount");
	}

	public static void validateMerchantWalletStatus(String status) throws Exception {
		if (status.length() == 0)
			throw new Exception("invalid status");
	}

}
