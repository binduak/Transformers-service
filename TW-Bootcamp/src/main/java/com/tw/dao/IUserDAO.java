package com.tw.dao;

import com.tw.entity.Users;

public interface IUserDAO {

	void saveUser(Users toSaveUser);

	boolean validateUserLogin(String username, String password);

}