package com.tw.exception;

public class TakeAwayApplicationExceptionUtlility {
	
	
	public static final int APPLICATION_ERROR_CODE = 1000;
	public static final String APPLICATION_ERROR_MESSAGE = "Application Error Contact Admin";

	public static final int NO_RECORD_ERROR_CODE = 1001;
	public static final String NO_RECORD_ERROR_MESSAGE = "NO RECORDS FOUND";
	
	public static final int BLOB_CREATION_ERROR_CODE = 1002;
	public static final String BLOB_CREATION_ERROR_MESSAGE = "File Couldn't Be saved";
	
	public static final int DUPLICATE_RECORD_ERROR_CODE = 1003;
	public static final String DUPLICATE_RECORD_ERROR_MESSAGE = "Name Already Exists";
	
	public static final int DATABASE_ERROR_CODE = 1004;
	public static final String DATABASE_ERROR_MESSAGE = "Database Error";
	
	public static final int INVALID_PARMETER_ERROR_CODE = 1005;
	public static final String INVALID_PARMETER_ERROR_MESSAGE = "Please Recheck your Input";
	
	public static final int INVALID_DATE_ERROR_CODE = 1006;
	public static final String INVALID_DATE_ERROR_MESSAGE = "Invalid Date";
	
	public static final int INVALID_USER_LOGIN_ERROR_CODE = 1007;
	public static final String INVALID_USER_LOGIN_ERROR_MESSAGE ="Invalid login details - Please recheck the input";
	
	public static final int INVALID_USERINFO_DATABASE_ERROR_CODE = 1008;
	public static final String INVALID_USERINFO_DATABASE_ERROR_MESSAGE ="Please contact the Admin.The details are inconsistent";
	
	public static final int USERNAME_OR_EMAILID_EXISTS_ERROR_CODE = 1009;
	public static final String USERNAME_OR_EMAILID_EXISTS_ERROR_MESSAGE ="Username or Emailid already exist in the repo";
	
	public static final int NO_CATEGORY_FOUND_ERROR_CODE = 1010;
	public static final String NO_CATEGORY_FOUND_ERROR_MESSAGE ="No Category found";
	
	public static final int INVALID_DATE_FORMAT_DOB_ERROR_CODE = 1011;
	public static final String INVALID_DATE_FORMAT_DOB_ERROR_MESSAGE ="Invalid Date format for DOB";
	
	public static final int INVALID_CATEGORY_INFO_ERROR_CODE = 1012;
	public static final String INVALID_CATEGORY_INFO_ERROR_MESSAGE ="Invalid Category Details";
	
	public static final int NO_ITEM_GIVEN_CATEGORY_ERROR_CODE = 1013;
	public static final String NO_ITEM_GIVEN_CATEGORY_ERROR_MESSAGE ="No item for this category";
	
	public static final int INVALID_CATEGORY_OR_SELLER_INFO_ERROR_CODE = 1014;
	public static final String INVALID_CATEGORY_OR_SELLER_INFO_ERROR_MESSAGE ="Invalid Seller or Category information";
	
}
