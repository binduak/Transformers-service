package com.concretepage.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.request.CategoryRequest;
import com.tw.response.BaseResponse;
import com.tw.response.ItemInfoResponse;
import com.tw.utility.ApplicationUtility;

@SuppressWarnings("unchecked")
public class TestItemManagement {

	final String BASE_URL = "http://localhost:8080/item/";
	final String GET_ITEM_BY_CATEGORY_URL = "getAllItemByCategory";
	public void testGetAllItemByCategory () {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		CategoryRequest categoryRequest = new CategoryRequest();
		categoryRequest.setCategoryId(1);
		categoryRequest.setCategoryName("Textile");
		BaseResponse<List<ItemInfoResponse>> getItemListByCategoryResponse = restTemplate.postForObject(BASE_URL+GET_ITEM_BY_CATEGORY_URL, categoryRequest, BaseResponse.class);
		assertThat(getItemListByCategoryResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(getItemListByCategoryResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
		assertThat(getItemListByCategoryResponse.getData().size()).isEqualTo(2);
		List<ItemInfoResponse> toValidateItemListResponse = getItemListByCategoryResponse.getData();
		assertThat(toValidateItemListResponse).isNotNull();

	}
	
	public void testGetAllItemByInvalidCategory () {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		CategoryRequest categoryRequest = new CategoryRequest();
		categoryRequest.setCategoryId(100);
		categoryRequest.setCategoryName("Textileqe23");
		BaseResponse<List<ItemInfoResponse>> getItemListByCategoryResponse = restTemplate.postForObject(BASE_URL+GET_ITEM_BY_CATEGORY_URL, categoryRequest, BaseResponse.class);
		assertThat(getItemListByCategoryResponse.getResponseStatus()).isEqualTo(TakeAwayApplicationExceptionUtlility.INVALID_CATEGORY_INFO_ERROR_MESSAGE);
		assertThat(getItemListByCategoryResponse.getResponseCode()).isEqualTo(TakeAwayApplicationExceptionUtlility.INVALID_CATEGORY_INFO_ERROR_CODE);
		List<ItemInfoResponse> toValidateItemListResponse = getItemListByCategoryResponse.getData();
		assertThat(toValidateItemListResponse).isNull();

	}

	public static void main(String args[]) {
		TestItemManagement categoryManagement = new TestItemManagement();
		//categoryManagement.testGetAllItemByCategory();
		categoryManagement.testGetAllItemByInvalidCategory ();
	}
}
