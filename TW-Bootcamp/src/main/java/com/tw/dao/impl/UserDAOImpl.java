package com.tw.dao.impl;

import javax.persistence.Query;

import com.tw.response.UserInfo;
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
	public UserInfo getUserLoginInfo(String username, String password) {
		try {
			//Need to make a call for the named query
			Query getLoggedInUserQuery = getEntityManager().createNamedQuery("getUserLoginInfo");
			getLoggedInUserQuery.setParameter("username", username);
			getLoggedInUserQuery.setParameter("password", password);
			Users loggedInUser = (Users) getLoggedInUserQuery.getSingleResult();
			if (loggedInUser == null)
				// Throw error invalid user
				throw new DAOException(TakeAwayApplicationExceptionUtlility.INVALID_USER_LOGIN_ERROR_MESSAGE,
						TakeAwayApplicationExceptionUtlility.INVALID_USER_LOGIN_ERROR_CODE);

				// Login Is Successful
				log.debug(ApplicationUtility.EXIT_METHOD + "validateUserLogin");
				return new UserInfo(loggedInUser.getName(), loggedInUser.getEmailId(),loggedInUser.getUsername(),loggedInUser.getAddress(),loggedInUser.getMobileNumber());
		} catch (DataAccessException exception) {
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
	}
}
