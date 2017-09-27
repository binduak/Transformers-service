package com.tw.dao;

import com.tw.entity.Buyer;

public interface IBuyerDAO extends IAbstractEntityDAO<Buyer, Long>{

	void saveBuyer(Buyer toSaveBuyer);

}