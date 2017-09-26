package com.tw.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

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


	@SuppressWarnings("unchecked")
	@Override
	public Users getUserLoginInfo(String username, String password) {
		try {
			//Need to make a call for the named query
			Query getLoggedInUserQuery = getEntityManager().createNamedQuery("getUserLoginInfo");
			getLoggedInUserQuery.setParameter("username", username);
			getLoggedInUserQuery.setParameter("password", password);
			List<Users> fetchedUserInfoList =getLoggedInUserQuery.getResultList();
			if (fetchedUserInfoList != null && fetchedUserInfoList.size() >0) {
				// Login Is Successful
				log.debug(ApplicationUtility.EXIT_METHOD + "validateUserLogin");
				return fetchedUserInfoList.get(0);
			}
				// Throw error invalid user
				throw new DAOException(TakeAwayApplicationExceptionUtlility.INVALID_USER_LOGIN_ERROR_MESSAGE,
						TakeAwayApplicationExceptionUtlility.INVALID_USER_LOGIN_ERROR_CODE);

		} catch (DataAccessException exception) {
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
	}
	
	@Override
	public boolean isUsernameOrEmailPresent (Users toValidateUser) {
		try {
			EntityManager entityManager = getEntityManager();
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Users> criteriaquery = builder.createQuery(Users.class);
			Root<Users> root = criteriaquery.from(Users.class);
			EntityType<Users> type = entityManager.getMetamodel().entity(Users.class);
			Predicate userNamePerdicate = null;
			Predicate emailPerdicate = 	null;
			if (ApplicationUtility.validateEmptyString(toValidateUser.getUsername())) 	userNamePerdicate = builder.like(root.get(type.getDeclaredSingularAttribute("username", String.class)), toValidateUser.getUsername() );
			if (ApplicationUtility.validateEmptyString(toValidateUser.getEmailId())) emailPerdicate = builder.like(root.get(type.getDeclaredSingularAttribute("emailId", String.class)), toValidateUser.getEmailId() );
			if (userNamePerdicate != null && emailPerdicate !=null) {
				criteriaquery.where(builder.or(userNamePerdicate,emailPerdicate));
			}else {
				if (userNamePerdicate != null) {
					criteriaquery.where(userNamePerdicate);
				}else {
					criteriaquery.where(emailPerdicate);
				}
			}
			List<Users> fetchedUserInfoList = entityManager.createQuery(criteriaquery.select(root)).getResultList();
			if (fetchedUserInfoList !=null && fetchedUserInfoList.size() >0) {
				return true;
			}
			return false;
		} catch (DataAccessException exception) {
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
	}
}

