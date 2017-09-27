package com.tw.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.tw.entity.Category;
import com.tw.exception.BaseException;
import com.tw.response.BaseResponse;
import com.tw.response.CategoryResponse;
import com.tw.service.ICategoryService;
import com.tw.utility.ApplicationUtility;

@CrossOrigin(origins = "*")
@Controller
public class CategoryController {

	public static final Logger log = Logger.getLogger(CategoryController.class.getName());
	
	@Autowired
	ICategoryService categoryService;
	@GetMapping("categories")
	public ResponseEntity<BaseResponse<List<CategoryResponse>>> getAllCategoryInfo() {
		log.debug(ApplicationUtility.ENTER_METHOD  + "getAllCategoryInfo");
		BaseResponse<List<CategoryResponse>> returnResponse = new BaseResponse<>();
		try {
			List<Category> fecthAllCategory = categoryService.fecthAllCategory();
			List<CategoryResponse> returnCategoryResponseList = new ArrayList<>();
			for (Category category : fecthAllCategory) {
				CategoryResponse categoryResponse = new CategoryResponse();
				categoryResponse.setCategoryId(category.getCategoryId());
				categoryResponse.setCategoryName(category.getCategoryName());
				returnCategoryResponseList.add(categoryResponse);
			}
			returnResponse.setData(returnCategoryResponseList);
			returnResponse.setSucessResponse();
		}catch (BaseException baseExceptoin) {
			returnResponse.setResponseCode(baseExceptoin.getErrorCode());
			returnResponse.setResponseStatus(baseExceptoin.getMessage());
		}catch (Exception e) {
			returnResponse.setFailureResponse();
		}
		log.debug(ApplicationUtility.ENTER_METHOD  + "getAllCategoryInfo");
		return new ResponseEntity<>(returnResponse, HttpStatus.OK);
	}
}
