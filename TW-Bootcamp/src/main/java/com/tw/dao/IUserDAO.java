package com.tw.dao;

import com.tw.entity.Users;

public interface IUserDAO extends IAbstractEntityDAO<Users, Long>{

	void saveUser(Users toSaveUser);


	Users getUserLoginInfo(String username, String password);


	boolean isUsernameOrEmailPresent(Users toValidateUser);

}