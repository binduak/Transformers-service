package com.tw.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tw.entity.Category;
import com.tw.entity.Items;
import com.tw.entity.Seller;
import com.tw.entity.Users;
import com.tw.exception.BaseException;
import com.tw.request.ItemCreateRequest;
import com.tw.response.BaseResponse;
import com.tw.response.ItemInfoResponse;
import com.tw.service.IItemService;
import com.tw.utility.ApplicationUtility;

@CrossOrigin(origins = "*")
@Controller
public class ItemInfoController {

	public static final Logger log = Logger.getLogger(ItemInfoController.class.getName());
	@Autowired
	IItemService itemService;
	
	@RequestMapping(value = "items", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse<List<ItemInfoResponse>>> getAllProductInfo (@RequestParam("categoryId") Integer categoryId, @RequestParam("categoryName") String categoryName) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "getAllProductInfo");
		BaseResponse<List<ItemInfoResponse>> returnResponse = new BaseResponse<>();
		try {
			Category fetchByCategory = new Category();
			fetchByCategory.setCategoryId(categoryId);
			fetchByCategory.setCategoryName(categoryName);
			//fetchAllItemsByCategory
			List<Items> fetchAllItemsByCategory = itemService.fetchAllItemsByCategory(fetchByCategory);
			List<ItemInfoResponse> itemInfoResponses = new ArrayList<ItemInfoResponse>();
			for (Items fetchedItem : fetchAllItemsByCategory) {
				ItemInfoResponse itemInfoResponse = new ItemInfoResponse();
				itemInfoResponse.setCategoryId(fetchedItem.getItemCategoryType().getCategoryId());
				itemInfoResponse.setCategoryName(fetchedItem.getItemCategoryType().getCategoryName());
				itemInfoResponse.setDescription(fetchedItem.getDescription());
				itemInfoResponse.setImageURL(fetchedItem.getImagePath());
				itemInfoResponse.setName(fetchedItem.getItemName());
				itemInfoResponse.setPrice(fetchedItem.getPrice());
				itemInfoResponse.setQuantity(fetchedItem.getQuantity());
				itemInfoResponse.setTitle(fetchedItem.getTitle());
				itemInfoResponses.add(itemInfoResponse);
			}
			returnResponse.setData(itemInfoResponses);
			returnResponse.setSucessResponse();
		}catch (BaseException baseExceptoin) {
			returnResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnResponse.setFailureResponse();
		}
		log.debug(ApplicationUtility.ENTER_METHOD  + "getAllProductInfo");
		return new ResponseEntity<>(returnResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "items", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse<Boolean>> sellerCreateItemByCategory (@RequestBody ItemCreateRequest itemCreateRequest) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "sellerCreateItemByCategory");
		BaseResponse<Boolean> returnResponse = new BaseResponse<>();
		try {
			Items toSaveItem = new Items();
			toSaveItem.setDescription(itemCreateRequest.getDescription());
			toSaveItem.setImagePath(itemCreateRequest.getImagePath());
			Category itemSaveCategory = new Category();
			itemSaveCategory.setCategoryId(itemCreateRequest.getCategoryId());
			itemSaveCategory.setCategoryName(itemCreateRequest.getCategoryName());
			toSaveItem.setItemCategoryType(itemSaveCategory);
			toSaveItem.setItemName(itemCreateRequest.getItemName());
			Seller itemOwner = new Seller();
			itemOwner.setSellerId(itemCreateRequest.getSellerId());
			Users toValidateUser = new Users();
			toValidateUser.setUsername(itemCreateRequest.getSellerUsername());
			itemOwner.setSellerUserInfo(toValidateUser);
			toSaveItem.setItemOwner(itemOwner);
			toSaveItem.setPrice(itemCreateRequest.getPrice());
			toSaveItem.setQuantity(itemCreateRequest.getQuantity());
			toSaveItem.setTitle(itemCreateRequest.getTitle());
			returnResponse.setData(itemService.saveItemsValidatingCategoryAndSeller(toSaveItem));
			returnResponse.setSucessResponse();
		}catch (BaseException baseExceptoin) {
			returnResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnResponse.setFailureResponse();
		}
		log.debug(ApplicationUtility.ENTER_METHOD  + "sellerCreateItemByCategory");
		return new ResponseEntity<>(returnResponse, HttpStatus.OK);
		
	}

}
