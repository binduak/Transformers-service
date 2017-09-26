package com.concretepage.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.request.BuyerRequest;
import com.tw.request.LoginRequest;
import com.tw.request.SellerRequest;
import com.tw.response.BaseResponse;
import com.tw.response.UserInfoResponse;
import com.tw.utility.ApplicationUtility;
@SuppressWarnings("unchecked")
public class TestUserManagement {
	final String BASE_URL = "http://localhost:8080/user/";
	final String LOGIN_URL =  BASE_URL + "login";
	final String IS_USERNAME_OR_EMAIL_PRESENT_URL = BASE_URL + "isUsernameOrEmailPresent";
	final String REGISTER_BUYER_URL =  BASE_URL + "registerBuyer";
	final String REGISTER_SELLER_URL =  BASE_URL + "registerSeller";
	
	public void testBuyerValidLogin() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("1$tw");
		loginRequest.setPassword("123456");
		BaseResponse<UserInfoResponse> loginResponse = restTemplate.postForObject(LOGIN_URL, loginRequest, BaseResponse.class);
		assertThat(loginResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(loginResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
	}
	
	public void testSellerValidLogin() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("6$tw");
		loginRequest.setPassword("123456");
		BaseResponse<UserInfoResponse> loginResponse = restTemplate.postForObject(LOGIN_URL, loginRequest, BaseResponse.class);
		assertThat(loginResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(loginResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
	}

	public void testUserInValidLogin() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		String login_url =  BASE_URL + "login";
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setUsername("123@!#@#$@#$");
		loginRequest.setPassword("sdf@#@#$");
		BaseResponse<UserInfoResponse> loginResponse = restTemplate.postForObject(login_url, loginRequest, BaseResponse.class);
		System.out.println("getData"+loginResponse.getData());
		assertThat(loginResponse.getResponseStatus()).isEqualTo(TakeAwayApplicationExceptionUtlility.INVALID_USER_LOGIN_ERROR_MESSAGE);
		assertThat(loginResponse.getResponseCode()).isEqualTo(TakeAwayApplicationExceptionUtlility.INVALID_USER_LOGIN_ERROR_CODE);
	}

	public void testValidUsernameAndEmailIdPresent () {
		RestTemplate restTemplate = new RestTemplate();
		// Create a multimap to hold the named parameters
		MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();
		parameters.add("username", "1$tw");
		parameters.add("emailId", "1@tw.com");
		HttpHeaders headers = new HttpHeaders();
		// Create the http entity for the request
		HttpEntity<MultiValueMap<String,String>> entity =
		            new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
		BaseResponse<Boolean> userPresenceResponse= restTemplate.postForObject(IS_USERNAME_OR_EMAIL_PRESENT_URL, entity, BaseResponse.class);
		System.out.println("getData"+userPresenceResponse.getData());
		assertThat(userPresenceResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(userPresenceResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
		assertThat(userPresenceResponse.getData()).isTrue();
	}
	
	public void testInValidUsernamePresent () {
		RestTemplate restTemplate = new RestTemplate();
		// Create a multimap to hold the named parameters
		MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();
		parameters.add("username", "wre!@#123");
		//parameters.add("emailId", "1@tw.com");
		HttpHeaders headers = new HttpHeaders();
		// Create the http entity for the request
		HttpEntity<MultiValueMap<String,String>> entity =
		            new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
		BaseResponse<Boolean> userPresenceResponse= restTemplate.postForObject(IS_USERNAME_OR_EMAIL_PRESENT_URL, entity, BaseResponse.class);
		System.out.println("getData"+userPresenceResponse.getData());
		assertThat(userPresenceResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(userPresenceResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
		assertThat(userPresenceResponse.getData()).isFalse();
	}
	
	public void testInValidEmailIdPresent () {
		RestTemplate restTemplate = new RestTemplate();
		// Create a multimap to hold the named parameters
		MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();
		parameters.add("emailId", "dfwer#@$@#4@gmail.com");
		HttpHeaders headers = new HttpHeaders();
		// Create the http entity for the request
		HttpEntity<MultiValueMap<String,String>> entity =
		            new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
		BaseResponse<Boolean> userPresenceResponse= restTemplate.postForObject(IS_USERNAME_OR_EMAIL_PRESENT_URL, entity, BaseResponse.class);
		System.out.println("getData"+userPresenceResponse.getData());
		assertThat(userPresenceResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(userPresenceResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
		assertThat(userPresenceResponse.getData()).isFalse();
	}
	
	public void testInValidUserNameOrValidEmailIdPresent () {
		RestTemplate restTemplate = new RestTemplate();
		// Create a multimap to hold the named parameters
		MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();
		parameters.add("username", "err3r34");
		parameters.add("emailId", "1@tw.com");
		HttpHeaders headers = new HttpHeaders();
		// Create the http entity for the request
		HttpEntity<MultiValueMap<String,String>> entity =
		            new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
		BaseResponse<Boolean> userPresenceResponse= restTemplate.postForObject(IS_USERNAME_OR_EMAIL_PRESENT_URL, entity, BaseResponse.class);
		System.out.println("getData"+userPresenceResponse.getData());
		assertThat(userPresenceResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(userPresenceResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
		assertThat(userPresenceResponse.getData()).isTrue();
	}
	
	public void testCreateValidBuyer () {
		String NEW_BUYER = "newBuyer";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		BuyerRequest registerBuyerRequest = new BuyerRequest();
		registerBuyerRequest.setUsername(NEW_BUYER+"name");
		registerBuyerRequest.setPassword("123456");
		registerBuyerRequest.setEmailId(NEW_BUYER+"@gsdf.com");
		registerBuyerRequest.setDateOfBirth("2017-02-20");
		registerBuyerRequest.setGender("M");
		registerBuyerRequest.setMobileNumber("12345678");
		registerBuyerRequest.setName(NEW_BUYER+ "name");
		registerBuyerRequest.setAddress(NEW_BUYER+"Gachiboli Hyderbad");
		BaseResponse<Boolean> registerBuyerResponse = restTemplate.postForObject(REGISTER_BUYER_URL, registerBuyerRequest, BaseResponse.class);
		assertThat(registerBuyerResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(registerBuyerResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
		assertThat(registerBuyerResponse.getData()).isTrue();
	}
	
	public void testInValidBuyerDuplicateEmailId () {
		String INVALIDBUYER = "InvalidBuyer";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		BuyerRequest registerBuyerRequest = new BuyerRequest();
		registerBuyerRequest.setUsername("1$tw");
		registerBuyerRequest.setPassword("123456");
		registerBuyerRequest.setEmailId(INVALIDBUYER+"@gsdf.com");
		registerBuyerRequest.setDateOfBirth("2017-12-23");
		registerBuyerRequest.setGender("M");
		registerBuyerRequest.setMobileNumber("12345678");
		registerBuyerRequest.setName(INVALIDBUYER+ "name");
		registerBuyerRequest.setAddress(INVALIDBUYER+"Gachiboli Hyderbad");
		BaseResponse<Boolean> registerBuyerResponse = restTemplate.postForObject(REGISTER_BUYER_URL, registerBuyerRequest, BaseResponse.class);
		assertThat(registerBuyerResponse.getResponseStatus()).isEqualTo(TakeAwayApplicationExceptionUtlility.USERNAME_OR_EMAILID_EXISTS_ERROR_MESSAGE);
		assertThat(registerBuyerResponse.getResponseCode()).isEqualTo(TakeAwayApplicationExceptionUtlility.USERNAME_OR_EMAILID_EXISTS_ERROR_CODE);
		assertThat(registerBuyerResponse.getData()).isNull();
	}
	
	public void testCreateValidSeller () {
		String NEW_SELLER = "newSeller";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		SellerRequest registerSellerRequest = new SellerRequest();
		registerSellerRequest.setUsername(NEW_SELLER+"name");
		registerSellerRequest.setPassword("123456");
		registerSellerRequest.setEmailId(NEW_SELLER+"@gsdf.com");
		registerSellerRequest.setExperienceYears(3);
		registerSellerRequest.setExperienceMonths(5);
		registerSellerRequest.setPanCardNo("QWFG3TBT");
		registerSellerRequest.setMobileNumber("12345678");
		registerSellerRequest.setName(NEW_SELLER+ "name");
		registerSellerRequest.setAddress(NEW_SELLER+"Gachiboli Hyderbad");
		BaseResponse<Boolean> registerBuyerResponse = restTemplate.postForObject(REGISTER_SELLER_URL, registerSellerRequest, BaseResponse.class);
		assertThat(registerBuyerResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(registerBuyerResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
		assertThat(registerBuyerResponse.getData()).isTrue();
	}

	public static void main(String args[]) {
		TestUserManagement userManageTest = new TestUserManagement();
		/*userManageTest.testBuyerValidLogin();
		userManageTest.testSellerValidLogin();
		userManageTest.testUserInValidLogin();
		userManageTest.testValidUsernameAndEmailIdPresent ();
		userManageTest.testInValidUsernamePresent();
		userManageTest.testInValidEmailIdPresent ();
		userManageTest.testInValidUserNameOrValidEmailIdPresent();
		userManageTest.testInValidBuyerDuplicateEmailId();
		userManageTest.testCreateValidSeller();*/
		userManageTest.testCreateValidBuyer();
	}
}
