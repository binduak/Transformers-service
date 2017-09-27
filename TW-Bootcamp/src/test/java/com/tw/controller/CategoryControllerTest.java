package com.tw.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.tw.entity.Category;
import com.tw.exception.DAOException;
import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.response.BaseResponse;
import com.tw.response.CategoryResponse;
import com.tw.service.ICategoryService;
import com.tw.utility.ApplicationUtility;

/*@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@RunWith(SpringRunner.class)
@SpringBootTest
@DatabaseTearDown(type=DatabaseOperation.CLEAN_INSERT)
@ContextConfiguration(locations= {"classpath:emptyDatabase.xml",})*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {
	
	@Autowired
    private CategoryController categoryController;
	
	@MockBean
	ICategoryService categoryService;
	
	 @Before
	    public void setupMock() {
	       MockitoAnnotations.initMocks(this);
	    }
	
	
	@Test
    public void contexLoadsCategoryController() throws Exception {
        assertThat(categoryController).isNotNull();
    }
	
	@Test
	public void testGetAllCategoryInfo() {
		List<Category> toReturnCategoryList = new ArrayList<Category>();
		Category categoryReturnItem1 = new Category();
		categoryReturnItem1.setCategoryId(1);
		categoryReturnItem1.setCategoryName("Test Category name1");
		toReturnCategoryList.add(categoryReturnItem1);
		Category categoryReturnItem2 = new Category();
		categoryReturnItem1.setCategoryId(2);
		categoryReturnItem1.setCategoryName("Test Category name2");
		toReturnCategoryList.add(categoryReturnItem2);
		when(categoryService.fecthAllCategory()).thenReturn(toReturnCategoryList);
		ResponseEntity<BaseResponse<List<CategoryResponse>>> allCategoryInfoResponse = categoryController.getAllCategoryInfo();
		BaseResponse<List<CategoryResponse>> fetchedCategoryListResponse = allCategoryInfoResponse.getBody();
		assertThat(fetchedCategoryListResponse.getResponseStatus()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_MESSAGE);
		assertThat(fetchedCategoryListResponse.getResponseCode()).isEqualTo(ApplicationUtility.RESPONSE_SUCCESS_CODE);
		assertThat(fetchedCategoryListResponse.getData().size()).isEqualTo(2);
	}
	
	@Test
	public void testGetEmptyCategoryError() {
		when(categoryService.fecthAllCategory()).thenThrow(new DAOException(TakeAwayApplicationExceptionUtlility.NO_CATEGORY_FOUND_ERROR_MESSAGE,
				TakeAwayApplicationExceptionUtlility.NO_CATEGORY_FOUND_ERROR_CODE));
		ResponseEntity<BaseResponse<List<CategoryResponse>>> allCategoryInfoResponse = categoryController.getAllCategoryInfo();
		BaseResponse<List<CategoryResponse>> fetchedCategoryListResponse = allCategoryInfoResponse.getBody();
		assertThat(fetchedCategoryListResponse.getResponseStatus()).isEqualTo(TakeAwayApplicationExceptionUtlility.NO_CATEGORY_FOUND_ERROR_MESSAGE);
		assertThat(fetchedCategoryListResponse.getResponseCode()).isEqualTo(TakeAwayApplicationExceptionUtlility.NO_CATEGORY_FOUND_ERROR_CODE);
	}

}
