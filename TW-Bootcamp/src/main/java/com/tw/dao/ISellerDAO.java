package com.tw.dao;

import com.tw.entity.Seller;

public interface ISellerDAO extends IAbstractEntityDAO<Seller, Long>{

	void saveSeller(Seller toSaveSeller);

	boolean validateSellerByUsernameAndId(Seller toValidateSeller);

}