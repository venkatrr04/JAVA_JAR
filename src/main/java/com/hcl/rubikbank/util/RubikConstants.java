package com.hcl.rubikbank.util;

public class RubikConstants {

	private RubikConstants() {

	}

	// Error messages 
	public static final String ERROR_BANK_CODE_EXIST = "Bank code not exist";
	public static final String ERROR_NO_FAVOURITE_ACCOUNT = "No favourite account exists";
	public static final String ERROR_BANK_NOT_FOUND = "No bank account found";
	public static final int ADD_SUCCESS = 1;
	public static final String BANK_DETAILS_URL = "https://localhost:9090/bankdetails/api/bankDetails/{bankCode}";
	public static final String ERROR_ACCOUNT_NAME_NOT_FOUND = "Account name doesn't exists";
	public static final String EDIT_SUCCESS = "edit successful";
}
