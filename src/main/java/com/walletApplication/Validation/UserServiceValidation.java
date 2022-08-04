package com.walletApplication.Validation;

public class UserServiceValidation {
	public static void validateUserMobileNumber(String mobileNumber) throws Exception {

		if (mobileNumber.length() != 10)
			throw new Exception("invalid mobileNUmber");
	}

	public static void validateUserName(String name) throws Exception {
		if (name.length() == 0)
			throw new Exception("name not found");
	}

	public static void validateUserPassword(String password) throws Exception {
		if (password.length() == 0)
			throw new Exception("invalid passsword");
	}
}
