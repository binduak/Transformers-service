package com.tw.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tw.dao.IBuyerDAO;
import com.tw.entity.Buyer;
import com.tw.exception.DAOException;
import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.utility.ApplicationUtility;

@Repository("buyerDAO")
public class BuyerDAOImpl extends AbstractEntityDAOImpl <Buyer, Long> implements IBuyerDAO {

	static Logger log = Logger.getLogger(BuyerDAOImpl.class.getName());
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IBuyerDAO#saveBuyer(com.tw.entity.Buyer)
	 */
	@Override
	public void saveBuyer(Buyer toSaveBuyer) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "saveBuyer");
		try {
			super.save(toSaveBuyer);
		} catch (DataAccessException exception) {
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
		
		log.debug(ApplicationUtility.EXIT_METHOD + "saveBuyer");
	}
}
