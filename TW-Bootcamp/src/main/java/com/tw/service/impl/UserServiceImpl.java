package com.tw.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tw.dao.IBuyerDAO;
import com.tw.dao.ISellerDAO;
import com.tw.dao.IUserDAO;
import com.tw.entity.Buyer;
import com.tw.entity.Seller;
import com.tw.entity.Users;
import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.exception.ValidationException;
import com.tw.service.IUserService;
import com.tw.utility.ApplicationUtility;

@Service
public class UserServiceImpl implements IUserService {

private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Autowired
	private IUserDAO userDAO;
	@Autowired
	private IBuyerDAO buyerDAO;
	@Autowired
	private ISellerDAO sellerDAO;
	
	/* (non-Javadoc)
	 * @see com.tw.service.impl.IUserService#getUserLoginInfo(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional
	public Users getUserLoginInfo(String username, String password) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "getUserLoginInfo");
		Users loggedInUserInfo = userDAO.getUserLoginInfo(username, password);
		log.debug(ApplicationUtility.EXIT_METHOD  + "getUserLoginInfo");
		return loggedInUserInfo;
	}
	
	/* (non-Javadoc)
	 * @see com.tw.service.impl.IUserService#registerBuyer(com.tw.entity.Buyer)
	 */
	@Override
	@Transactional
	public boolean registerBuyer (Buyer toSaveBuyer) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "registerBuyer");
		Users toValidateUser = new Users();
		toValidateUser.setUsername(toSaveBuyer.getBuyerUserInfo().getUsername());
		toValidateUser.setEmailId(toSaveBuyer.getBuyerUserInfo().getEmailId());
		
		if (!userDAO.isUsernameOrEmailPresent(toValidateUser)) {
			buyerDAO.saveBuyer(toSaveBuyer);
			log.debug(ApplicationUtility.EXIT_METHOD  + "registerBuyer");
			return true;
		}
			throw new ValidationException(TakeAwayApplicationExceptionUtlility.USERNAME_OR_EMAILID_EXISTS_ERROR_MESSAGE,
					TakeAwayApplicationExceptionUtlility.USERNAME_OR_EMAILID_EXISTS_ERROR_CODE);
		
	}
	
	/* (non-Javadoc)
	 * @see com.tw.service.impl.IUserService#registerSeller(com.tw.entity.Seller)
	 */
	@Override
	@Transactional
	public boolean registerSeller (Seller toSaveSeller) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "registerSeller");
		Users toValidateUser = new Users();
		toValidateUser.setUsername(toSaveSeller.getSellerUserInfo().getUsername());
		toValidateUser.setEmailId(toSaveSeller.getSellerUserInfo().getEmailId());
		if (!userDAO.isUsernameOrEmailPresent(toValidateUser)) {
			sellerDAO.saveSeller(toSaveSeller);
			log.debug(ApplicationUtility.EXIT_METHOD  + "registerSeller");
			return true;
		}
		throw new ValidationException(TakeAwayApplicationExceptionUtlility.USERNAME_OR_EMAILID_EXISTS_ERROR_MESSAGE,
				TakeAwayApplicationExceptionUtlility.USERNAME_OR_EMAILID_EXISTS_ERROR_CODE);
	}
	
	/* (non-Javadoc)
	 * @see com.tw.service.impl.IUserService#isUsernameOrEmailPresent(com.tw.entity.Users)
	 */
	@Override
	@Transactional
	public boolean isUsernameOrEmailPresent (Users toValidateUser) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "isUserPresent");
		boolean validateUserInfoExist = userDAO.isUsernameOrEmailPresent(toValidateUser);
		log.debug(ApplicationUtility.EXIT_METHOD  + "isUserPresent");
		return validateUserInfoExist;
	}
	
}
