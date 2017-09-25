package com.tw.service.impl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tw.dao.IUserDAO;
import com.tw.utility.ApplicationUtility;

@Service
public class UserServiceImpl implements IUserService {

public static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Autowired
	private IUserDAO userDAO;
	
	@Override
	@Transactional
	public boolean  validateUserLogin(String username, String password) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "validateUserLogin");
		boolean validateUserLogin = userDAO.validateUserLogin(username, password);
		log.debug(ApplicationUtility.EXIT_METHOD  + "validateUserLogin");
		return validateUserLogin;
	}
}
