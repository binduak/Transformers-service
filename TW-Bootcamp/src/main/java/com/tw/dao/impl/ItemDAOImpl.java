package com.tw.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.tw.dao.IItemDAO;
import com.tw.entity.Category;
import com.tw.entity.Items;
import com.tw.exception.DAOException;
import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.utility.ApplicationUtility;

@Repository("itemDAO")
public class ItemDAOImpl extends AbstractEntityDAOImpl <Items, Long> implements IItemDAO  {

	static Logger log = Logger.getLogger(ItemDAOImpl.class.getName());
	
	
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IItemDAO#getItemsByCategory(com.tw.entity.Category)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Items> getItemsByCategory(Category category) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "getItemsByCategory");
		try {
			//Need to make a call for the named query
			Query itemsByCategory = getEntityManager().createNamedQuery("getAllItemsByCategory");
			itemsByCategory.setParameter("categoryId", category.getCategoryId());
			List<Items> fetchedItemsList = itemsByCategory.getResultList();
			if (fetchedItemsList != null && fetchedItemsList.size() > 0) {
				// Login Is Successful
				log.debug(ApplicationUtility.EXIT_METHOD + "getItemsByCategory");
				return fetchedItemsList;
			}
				// Throw error invalid user
				throw new DAOException(TakeAwayApplicationExceptionUtlility.NO_ITEM_GIVEN_CATEGORY_ERROR_MESSAGE,
						TakeAwayApplicationExceptionUtlility.NO_ITEM_GIVEN_CATEGORY_ERROR_CODE);

		} catch (DataAccessException exception) {
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
	}
}
