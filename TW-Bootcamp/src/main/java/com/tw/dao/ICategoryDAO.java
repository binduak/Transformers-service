package com.tw.dao;

import java.util.List;

import com.tw.entity.Category;

public interface ICategoryDAO extends IAbstractEntityDAO<Category, Long>{

	List<Category> fecthAllCategory();

	boolean validateCategoryByNameAndId(Category toValidateCateegory);

}