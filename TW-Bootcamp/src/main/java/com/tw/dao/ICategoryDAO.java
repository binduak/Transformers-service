package com.tw.dao;

import java.util.List;

import com.tw.entity.Category;

public interface ICategoryDAO {

	List<Category> fecthAllCategory();

	boolean validateCategoryByNameAndId(Category toValidateCateegory);

}