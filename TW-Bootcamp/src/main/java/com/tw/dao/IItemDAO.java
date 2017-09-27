package com.tw.dao;

import java.util.List;

import com.tw.entity.Category;
import com.tw.entity.Items;

public interface IItemDAO extends IAbstractEntityDAO<Items, Long> {

	List<Items> getItemsByCategory(Category category);

	void saveItem(Items toSaveItems);

}