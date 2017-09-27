package com.tw.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tw.dao.ISellerDAO;
import com.tw.entity.Seller;
import com.tw.exception.DAOException;
import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.utility.ApplicationUtility;

@Repository("sellerDAO")
public class SellerDAOImpl extends AbstractEntityDAOImpl <Seller, Long> implements ISellerDAO {

	static Logger log = Logger.getLogger(SellerDAOImpl.class.getName());
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.ISellerDAO#saveSeller(com.tw.entity.Seller)
	 */
	@Override
	public void saveSeller(Seller toSaveSeller) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "saveSeller");
		try {
			super.save(toSaveSeller);
		} catch (DataAccessException exception) {
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
		
		log.debug(ApplicationUtility.EXIT_METHOD + "saveSeller");
	}
	
	@Override
	public boolean validateSellerByUsernameAndId (Seller toValidateSeller) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "validateSellerByUsernameAndId");
		try {
			Query getSellerValdiateQuery = getEntityManager().createNamedQuery("validateSellerByUsernameAndId");
			getSellerValdiateQuery.setParameter("validateSellerId", toValidateSeller.getSellerId());
			getSellerValdiateQuery.setParameter("validateUsername", toValidateSeller.getSellerUserInfo().getUsername());
			Seller fetchedSeller = (Seller) getSellerValdiateQuery.getSingleResult();
			if (fetchedSeller != null && fetchedSeller.getSellerId() >0) {
				log.debug(ApplicationUtility.EXIT_METHOD  + "validateSellerByUsernameAndId");
				return true;
			}
			return false;
		}catch (NoResultException noResultException) {
			log.warn( toValidateSeller , noResultException);
			return false;
		}catch (DataAccessException exception) {
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
	}
}
