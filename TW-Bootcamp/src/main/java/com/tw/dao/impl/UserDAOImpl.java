package com.tw.dao.impl;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.tw.dao.IUserDAO;
import com.tw.entity.Users;
import com.tw.exception.DAOException;
import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.utility.ApplicationUtility;

@Repository("userDAO")
public class UserDAOImpl extends AbstractEntityDAOImpl <Users, Long> implements IUserDAO {

	static Logger log = Logger.getLogger(UserDAOImpl.class.getName());
	
	@Override
	public void saveUser(Users toSaveUser) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "saveUser");
		try {
			super.save(toSaveUser);
		} catch (DataAccessException exception) {
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
		
		log.debug(ApplicationUtility.EXIT_METHOD + "saveUser");
	}
	
	@Override
	public boolean validateUserLogin (String username, String password) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "saveUser");
		try {
			//Need to make a call for the named query
			Query loginValidationQuery = getEntityManager().createNamedQuery("validateUserLoginInfo");
			loginValidationQuery.setParameter("username", username);
			loginValidationQuery.setParameter("password", password);
			int intValue = ((Number) loginValidationQuery.getSingleResult()).intValue();
			if (intValue ==0) {
				// Throw error invalid user
				throw new DAOException(TakeAwayApplicationExceptionUtlility.INVALID_USER_LOGIN_ERROR_MESSAGE,
						TakeAwayApplicationExceptionUtlility.INVALID_USER_LOGIN_ERROR_CODE);
			}else if (intValue ==1){
				// Login Is Sucessfull
				log.debug(ApplicationUtility.EXIT_METHOD + "validateUserLogin");
				return true;
			}else {
				// Need to raise error to contact the admin. Since duplicate record
				throw new DAOException(TakeAwayApplicationExceptionUtlility.INVALID_USERINFO_DATABASE_ERROR_MESSAGE,
						TakeAwayApplicationExceptionUtlility.INVALID_USERINFO_DATABASE_ERROR_CODE);
			}
		} catch (DataAccessException exception) {
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
	}
}
