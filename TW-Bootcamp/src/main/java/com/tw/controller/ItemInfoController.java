package com.tw.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.activation.FileTypeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tw.entity.Category;
import com.tw.entity.Items;
import com.tw.exception.BaseException;
import com.tw.request.CategoryRequest;
import com.tw.response.BaseResponse;
import com.tw.response.ItemInfoResponse;
import com.tw.service.impl.IItemService;
import com.tw.utility.ApplicationUtility;

@RequestMapping("item")
@Controller
public class ItemInfoController {

	public static final Logger log = Logger.getLogger(ItemInfoController.class.getName());
	@Autowired
	IItemService itemService;
	
	@RequestMapping(value = "/getImage/{id}", method=RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> showImageOnId(@PathVariable("id") String id) throws IOException {
		byte[] imageBytes = Files.readAllBytes(Paths.get("src/main/resources/images/headphone.jpg"));
		return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType( MediaType.IMAGE_JPEG_VALUE))).body(imageBytes);
	}
	
	@RequestMapping(value = "getAllItemByCategory", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse<List<ItemInfoResponse>>> getAllProductInfo (@RequestBody CategoryRequest categoryRequest ) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "getAllProductInfo");
		BaseResponse<List<ItemInfoResponse>> returnSucessResponse = new BaseResponse<>();
		try {
			Category fetchByCategory = new Category();
			fetchByCategory.setCategoryId(categoryRequest.getCategoryId());
			fetchByCategory.setCategoryName(categoryRequest.getCategoryName());
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
			returnSucessResponse.setData(itemInfoResponses);
			returnSucessResponse.setSucessResponse();
		}catch (BaseException baseExceptoin) {
			returnSucessResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnSucessResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnSucessResponse.setFailureResponse();
		}
		log.debug(ApplicationUtility.ENTER_METHOD  + "getAllProductInfo");
		return new ResponseEntity<>(returnSucessResponse, HttpStatus.OK);
	}

}
