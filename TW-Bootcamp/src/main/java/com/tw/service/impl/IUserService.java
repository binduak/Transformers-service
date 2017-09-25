package com.tw.service.impl;

import com.tw.response.UserInfo;

public interface IUserService {

    UserInfo getUserLoginInfo(String username, String password);
}