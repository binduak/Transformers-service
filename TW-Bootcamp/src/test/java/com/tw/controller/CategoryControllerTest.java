package com.tw.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.tw.response.BaseResponse;
import com.tw.response.CategoryResponse;
import com.tw.utility.ApplicationUtility;

@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {
	
	@Autowired
    private CategoryController categoryController;
	
	@Test
    public void contexLoadsCategoryController() throws Exception {
        assertThat(categoryController).isNotNull();
    }
	
	@Test
	public void testGetAllCategoryInfo() {
		ResponseEntity<BaseResponse<List<CategoryResponse>>> allCategoryInfoResponse = categoryController.getAllCategoryInfo();
		BaseResponse<List<CategoryResponse>> fetchedCategoryListResponse = allCategoryInfoResponse.getBody();
		assertThat(fetchedCategoryListResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(fetchedCategoryListResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
		assertThat(fetchedCategoryListResponse.getData().size()).isEqualTo(6);
	}
	
	/*@Test
	@DatabaseSetup("sampleData.xml")
	public void testGetEmptyCategoryError() {
		ResponseEntity<BaseResponse<List<CategoryResponse>>> allCategoryInfoResponse = categoryController.getAllCategoryInfo();
	}*/

}
