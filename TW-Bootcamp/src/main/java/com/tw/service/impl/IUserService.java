package com.tw.service.impl;

import javax.transaction.Transactional;

public interface IUserService {

	boolean validateUserLogin(String username, String password);

}