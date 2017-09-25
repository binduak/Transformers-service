package com.tw.utility;

public class ApplicationUtility {
	
	public static final String ENTER_METHOD ="Entered Method";
	public static final String EXIT_METHOD = "Exited Method";
	public static final String NO_RECORD_FOUND = "No Record Found";
	
	
	public static final String RESPONSE_SUCESS_MESSAGE = "Sucess";
	public static final int RESPONSE_SUCESS_CODE = 0;
	
	public static final String RESPONSE_FAILURE_MESSAGE = "Failure";
	public static final int RESPONSE_FAILURE_CODE = 400;
	
	public static boolean validateEmptyString (String toValidate) {
		if (toValidate!=null && toValidate.trim().length()>0) 	return true;
		return false;
	}
}
