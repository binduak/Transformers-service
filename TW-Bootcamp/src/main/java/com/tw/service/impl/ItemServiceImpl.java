package com.tw.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.dao.ICategoryDAO;
import com.tw.dao.IItemDAO;
import com.tw.entity.Category;
import com.tw.entity.Items;
import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.exception.ValidationException;
import com.tw.utility.ApplicationUtility;

@Service
public class ItemServiceImpl implements IItemService {

	private static final Logger log = Logger.getLogger(ItemServiceImpl.class.getName());
	
	@Autowired
	ICategoryDAO categoryDAO;
	
	@Autowired
	IItemDAO itemDAO;
	
	/* (non-Javadoc)
	 * @see com.tw.service.impl.IItemService#fetchAllItemsByCategory(com.tw.entity.Category)
	 */
	@Override
	public List<Items> fetchAllItemsByCategory (Category fetchByCategory){
		log.debug(ApplicationUtility.ENTER_METHOD  + "fetchAllItemsByCategory");
		if (categoryDAO.validateCategoryByNameAndId(fetchByCategory)) {
			// Need to call itemDao to fetch all details
			List<Items> fetchedItems = itemDAO.getItemsByCategory(fetchByCategory);
			log.debug(ApplicationUtility.EXIT_METHOD  + "fetchAllItemsByCategory");
			return fetchedItems;
		}
		throw new ValidationException(TakeAwayApplicationExceptionUtlility.INVALID_CATEGORY_INFO_ERROR_MESSAGE, TakeAwayApplicationExceptionUtlility.INVALID_CATEGORY_INFO_ERROR_CODE);
	}
}
