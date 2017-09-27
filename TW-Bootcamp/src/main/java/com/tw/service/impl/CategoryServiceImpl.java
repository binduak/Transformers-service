package com.tw.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tw.dao.ICategoryDAO;
import com.tw.entity.Category;
import com.tw.service.ICategoryService;
import com.tw.utility.ApplicationUtility;

@Service
public class CategoryServiceImpl implements ICategoryService {
	private static final Logger log = Logger.getLogger(CategoryServiceImpl.class.getName());
	
	@Autowired
	private ICategoryDAO categoryDAO;
	
	/* (non-Javadoc)
	 * @see com.tw.service.impl.ICategoryService#fecthAllCategory()
	 */
	@Override
	public List<Category> fecthAllCategory (){
		log.debug(ApplicationUtility.ENTER_METHOD  + "fecthAllCategory");
		List<Category> fecthAllCategory = categoryDAO.fecthAllCategory();
		log.debug(ApplicationUtility.EXIT_METHOD  + "fecthAllCategory");
		return fecthAllCategory;
	}
	
}
