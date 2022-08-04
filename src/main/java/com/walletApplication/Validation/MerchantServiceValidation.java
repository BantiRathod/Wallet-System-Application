package com.walletApplication.Validation;

public class MerchantServiceValidation {
	public static void validateMerchantMobileNumber(String mobileNumber) throws Exception {

		if (mobileNumber.length() != 10)
			throw new Exception("invalid mobileNUmber");
	}

	public static void validateMerchantName(String name) throws Exception {
		if (name.length() == 0)
			throw new Exception("name not found");
	}

	public static void validateMerchantPassword(String password) throws Exception {
		if (password.length() == 0)
			throw new Exception("invalid passsword");
	}
	
	public static void validateMerchantAddress(String address) throws Exception {
		if(address.length()==0)
			throw new Exception("invalid address");
	}
}
