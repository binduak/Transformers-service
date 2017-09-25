package com.concretepage.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.tw.request.LoginRequest;
import com.tw.response.BaseResponse;

public class TestUserManagement {

	public void testUserValidLogin() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:8080/login";
	    LoginRequest loginRequest = new LoginRequest();
	    loginRequest.setUsername("1@tw.com");
	    loginRequest.setPassword("123456");
	    @SuppressWarnings("unchecked")
		BaseResponse<String> loginResponse = restTemplate.postForObject(url, loginRequest, BaseResponse.class);
         System.out.println("getData"+loginResponse.getData());
         System.out.println("getResponseStatus"+loginResponse.getResponseStatus());
         System.out.println("getResponseCode"+loginResponse.getResponseCode());
    }
	
	
	  public static void main(String args[]) {
		  TestUserManagement userManageTest = new TestUserManagement();
		  userManageTest.testUserValidLogin();
	  }
}
