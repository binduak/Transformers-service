package com.tw.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.exception.ValidationException;

public class ApplicationUtility {
	
	public static final String ENTER_METHOD ="Entered Method";
	public static final String EXIT_METHOD = "Exited Method";
	public static final String NO_RECORD_FOUND = "No Record Found";
	public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
	public static final String RESPONSE_SUCCESS_MESSAGE = "Success";
	public static final int RESPONSE_SUCCESS_CODE = 0;
	
	public static final String RESPONSE_FAILURE_MESSAGE = "Failure";
	public static final int RESPONSE_FAILURE_CODE = 400;
	
	public static boolean validateEmptyString (String toValidate) {
		if (toValidate!=null && toValidate.trim().length()>0) 	return true;
		return false;
	}
	
	public static Date convertDOBToDate (String dateToConvert) {
		 try {
			return formatter.parse(dateToConvert);
		} catch (ParseException e) {
			throw new ValidationException(TakeAwayApplicationExceptionUtlility.INVALID_DATE_FORMAT_DOB_ERROR_MESSAGE,
					TakeAwayApplicationExceptionUtlility.INVALID_DATE_FORMAT_DOB_ERROR_CODE);
		}
	}
	
}
