package com.concretepage.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tw.response.BaseResponse;
import com.tw.response.CategoryResponse;
import com.tw.utility.ApplicationUtility;

public class TestCategoryManagement {

	final String BASE_URL = "http://localhost:8080/category/";
	final String GET_CATEGORY_URL = "getAllCategory";
	public void testGetAllCategory () {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BaseResponse<List<CategoryResponse>>> fetchedResponse = restTemplate.exchange(BASE_URL+GET_CATEGORY_URL, HttpMethod.GET, null, new ParameterizedTypeReference<BaseResponse<List<CategoryResponse>>>() {
		});
		BaseResponse<List<CategoryResponse>> toValidateCategoryListResponse = fetchedResponse.getBody();
		assertThat(toValidateCategoryListResponse).isNotNull();
		assertThat(toValidateCategoryListResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCESS_MESSAGE);
		assertThat(toValidateCategoryListResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCESS_CODE);
		assertThat(toValidateCategoryListResponse.getData().size()).isEqualTo(6);

	}

	public static void main(String args[]) {
		TestCategoryManagement categoryManagement = new TestCategoryManagement();
		categoryManagement.testGetAllCategory();
	}
}
