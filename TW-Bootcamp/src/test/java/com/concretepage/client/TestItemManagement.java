package com.concretepage.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.request.CategoryRequest;
import com.tw.request.ItemCreateRequest;
import com.tw.response.BaseResponse;
import com.tw.response.ItemInfoResponse;
import com.tw.utility.ApplicationUtility;

@SuppressWarnings("unchecked")
public class TestItemManagement {

	final String BASE_URL = "http://localhost:8080/";
	final String GET_ITEM_BY_CATEGORY_URL = "items";
	final String SELLER_CREATE_ITEM_BY_CATEGORY_URL="items";
	public void testGetAllItemByCategory () {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		BaseResponse<List<ItemInfoResponse>> getItemListByCategoryResponse = restTemplate.getForObject((BASE_URL+GET_ITEM_BY_CATEGORY_URL+"?categoryId=1&categoryName=Textile"), BaseResponse.class);
		assertThat(getItemListByCategoryResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(getItemListByCategoryResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
		assertThat(getItemListByCategoryResponse.getData().size()).isEqualTo(3);
		List<ItemInfoResponse> toValidateItemListResponse = getItemListByCategoryResponse.getData();
		assertThat(toValidateItemListResponse).isNotNull();

	}
	
	public void testGetAllItemByInvalidCategory () {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		BaseResponse<List<ItemInfoResponse>> getItemListByCategoryResponse = restTemplate.getForObject(BASE_URL+GET_ITEM_BY_CATEGORY_URL+"?categoryId=100&categoryName=Textileqe23", BaseResponse.class);
		assertThat(getItemListByCategoryResponse.getResponseStatus()).isEqualTo(TakeAwayApplicationExceptionUtlility.INVALID_CATEGORY_INFO_ERROR_MESSAGE);
		assertThat(getItemListByCategoryResponse.getResponseCode()).isEqualTo(TakeAwayApplicationExceptionUtlility.INVALID_CATEGORY_INFO_ERROR_CODE);
		List<ItemInfoResponse> toValidateItemListResponse = getItemListByCategoryResponse.getData();
		assertThat(toValidateItemListResponse).isNull();

	}
	
	public void testSaveValidIteminfo () {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		ItemCreateRequest itemCreateRequest = new ItemCreateRequest();
		itemCreateRequest.setCategoryId(1);
		itemCreateRequest.setCategoryName("Textile");
		itemCreateRequest.setTitle("Test Item Title");
		itemCreateRequest.setDescription("Test Item Description");
		itemCreateRequest.setImagePath("http://sampletest.com/imeagetest");
		itemCreateRequest.setItemName("Test Item Name");
		itemCreateRequest.setPrice(489);
		itemCreateRequest.setQuantity(2);
		itemCreateRequest.setSellerId(1);
		itemCreateRequest.setSellerUsername("4$tw");
		BaseResponse<Boolean> saveItemByCategoryAndSellerResponse = restTemplate.postForObject(BASE_URL+SELLER_CREATE_ITEM_BY_CATEGORY_URL, itemCreateRequest, BaseResponse.class);
		assertThat(saveItemByCategoryAndSellerResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(saveItemByCategoryAndSellerResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
		assertThat(saveItemByCategoryAndSellerResponse.getData()).isTrue();
	}
	
	
	public void testSaveInValidSellerinfo () {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		RestTemplate restTemplate = new RestTemplate();
		ItemCreateRequest itemCreateRequest = new ItemCreateRequest();
		itemCreateRequest.setCategoryId(1);
		itemCreateRequest.setCategoryName("Textile");
		itemCreateRequest.setTitle("Test Item Title");
		itemCreateRequest.setDescription("Test Item Description");
		itemCreateRequest.setImagePath("http://sampletest.com/imeagetest");
		itemCreateRequest.setItemName("Test Item Name");
		itemCreateRequest.setPrice(489);
		itemCreateRequest.setQuantity(2);
		itemCreateRequest.setSellerId(1000);
		itemCreateRequest.setSellerUsername("4$tw@#$@#%");
		BaseResponse<Boolean> saveItemByCategoryAndSellerResponse = restTemplate.postForObject(BASE_URL+SELLER_CREATE_ITEM_BY_CATEGORY_URL, itemCreateRequest, BaseResponse.class);
		assertThat(saveItemByCategoryAndSellerResponse.getResponseStatus()).isEqualTo(TakeAwayApplicationExceptionUtlility.INVALID_CATEGORY_OR_SELLER_INFO_ERROR_MESSAGE);
		assertThat(saveItemByCategoryAndSellerResponse.getResponseCode()).isEqualTo(TakeAwayApplicationExceptionUtlility.INVALID_CATEGORY_OR_SELLER_INFO_ERROR_CODE);
		assertThat(saveItemByCategoryAndSellerResponse.getData()).isNull();
	}

	public static void main(String args[]) {
		TestItemManagement categoryManagement = new TestItemManagement();
		//categoryManagement.testGetAllItemByCategory();
		//categoryManagement.testGetAllItemByInvalidCategory ();
		//categoryManagement.testSaveValidIteminfo ();
		categoryManagement.testSaveInValidSellerinfo();
	}
}
