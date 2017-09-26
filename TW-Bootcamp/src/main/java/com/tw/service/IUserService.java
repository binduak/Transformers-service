package com.tw.service;

import com.tw.entity.Buyer;
import com.tw.entity.Seller;
import com.tw.entity.Users;

public interface IUserService {

	Users getUserLoginInfo(String username, String password);

	boolean registerBuyer(Buyer toSaveBuyer);

	boolean registerSeller(Seller toSaveSeller);

	boolean isUsernameOrEmailPresent(Users toValidateUser);

}