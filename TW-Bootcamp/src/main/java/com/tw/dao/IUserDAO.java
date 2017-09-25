package com.tw.dao;

import com.tw.entity.Users;
import com.tw.response.UserInfo;

public interface IUserDAO {

	void saveUser(Users toSaveUser);


	UserInfo getUserLoginInfo(String username, String password);

}