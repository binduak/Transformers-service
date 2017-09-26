package com.tw.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tw.dao.ICategoryDAO;
import com.tw.entity.Category;
import com.tw.exception.DAOException;
import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.utility.ApplicationUtility;

@Repository("categoryDAO")
public class CategoryDAOImpl  extends AbstractEntityDAOImpl <Category, Long> implements ICategoryDAO {

	/* (non-Javadoc)
	 * @see com.tw.dao.impl.CategoryDAO#fecthCategoryResponse()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> fecthAllCategory () {
		log.debug(ApplicationUtility.ENTER_METHOD  + "fecthAllCategory");
		try {
			Query getCategoryQuery = getEntityManager().createNamedQuery("getAllCategory");
			List<Category> fetchedCategoryList =getCategoryQuery.getResultList();
			if (fetchedCategoryList != null && fetchedCategoryList.size() >0) {
				log.debug(ApplicationUtility.EXIT_METHOD  + "fecthAllCategory");
				return fetchedCategoryList;
			}
			throw new DAOException(TakeAwayApplicationExceptionUtlility.NO_CATEGORY_FOUND_ERROR_MESSAGE,
					TakeAwayApplicationExceptionUtlility.NO_CATEGORY_FOUND_ERROR_CODE);

		}catch (DataAccessException exception) {
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
	}
	
	@Override
	public boolean validateCategoryByNameAndId (Category toValidateCateegory) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "fecthAllCategory");
		try {
			Query getCategoryValdiateQuery = getEntityManager().createNamedQuery("validateCategoryByNameAndId");
			getCategoryValdiateQuery.setParameter("validateCategoryId", toValidateCateegory.getCategoryId());
			getCategoryValdiateQuery.setParameter("validateCategoryName", toValidateCateegory.getCategoryName());
			Category fetchedCategory = (Category) getCategoryValdiateQuery.getSingleResult();
			if (fetchedCategory != null && fetchedCategory.getCategoryId() >0) {
				log.debug(ApplicationUtility.EXIT_METHOD  + "fecthAllCategory");
				return true;
			}
			return false;
		}catch (NoResultException noResultException) {
			log.warn( toValidateCateegory , noResultException);
			return false;
		}catch (DataAccessException exception) {
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
	}
}
