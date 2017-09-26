package com.tw.service.impl;

import java.util.List;

import com.tw.entity.Category;
import com.tw.entity.Items;

public interface IItemService {

	List<Items> fetchAllItemsByCategory(Category fetchByCategory);

}