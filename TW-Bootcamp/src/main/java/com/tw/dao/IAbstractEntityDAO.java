package com.tw.dao;

import java.io.Serializable;
import java.util.List;

import com.tw.entity.AbstractEntity;
import com.tw.exception.DAOException;

@SuppressWarnings({ "hiding" ,"rawtypes"})
public interface IAbstractEntityDAO<T, ID extends Serializable> {

	void delete(Object entity);

	void delete(Class<T> clazz, Serializable id);

	void deleteAll(Class<T> clazz);

	<T> T get(Class<T> clazz, Serializable id);

	<T> T get(String sql, Object... values);

	<T> T getByExample(T entity);

	<T> long getCount(Class<T> clazz);

	long getCount(String sql, Object... values);

	<T> List<T> list(Class<T> clazz);

	<T> List<T> list(String sql, Object... values);

	void save(AbstractEntity<T> entity) throws DAOException;

	void update(AbstractEntity<T> entity);

	List query(String sql, Object... values);

	<T> List<T> getDetailsByQuery(String queryName) throws DAOException;

	<T> T getUniqueDetailsByQuery(String queryName, Object... values) throws DAOException;

}