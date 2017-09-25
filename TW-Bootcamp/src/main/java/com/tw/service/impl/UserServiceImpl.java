package com.tw.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tw.dao.IUserDAO;
import com.tw.entity.Users;
import com.tw.utility.ApplicationUtility;

@Service
public class UserServiceImpl implements IUserService {

private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Autowired
	private IUserDAO userDAO;


	@Override
	@Transactional
	public Users getUserLoginInfo(String username, String password) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "getUserLoginInfo");
		Users loggedInUserInfo = userDAO.getUserLoginInfo(username, password);
		log.debug(ApplicationUtility.EXIT_METHOD  + "getUserLoginInfo");
		return loggedInUserInfo;
	}
}
