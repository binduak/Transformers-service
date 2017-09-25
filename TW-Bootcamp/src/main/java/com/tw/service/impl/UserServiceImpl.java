package com.tw.service.impl;

import com.tw.response.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tw.dao.IUserDAO;
import com.tw.utility.ApplicationUtility;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements IUserService {

private static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Autowired
	private IUserDAO userDAO;


	@Override
	@Transactional
	public UserInfo getUserLoginInfo(String username, String password) {
		log.debug(ApplicationUtility.ENTER_METHOD  + "getUserLoginInfo");
		UserInfo loggedInUserInfo = userDAO.getUserLoginInfo(username, password);
		log.debug(ApplicationUtility.EXIT_METHOD  + "getUserLoginInfo");
		return loggedInUserInfo;
	}
}
