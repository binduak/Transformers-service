package com.tw.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;

import com.tw.dao.IAbstractEntityDAO;
import com.tw.entity.AbstractEntity;
import com.tw.exception.DAOException;
import com.tw.exception.TakeAwayApplicationExceptionUtlility;
import com.tw.utility.ApplicationUtility;

@SuppressWarnings({ "unchecked", "hiding" ,"rawtypes"})
public class AbstractEntityDAOImpl<T, ID extends Serializable> implements IAbstractEntityDAO<T, ID>  {


	public static final Logger log = Logger.getLogger(AbstractEntityDAOImpl.class.getName());
	
	@PersistenceContext	
	private EntityManager entityManager;	
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#delete(java.lang.Object)
	 */
	@Override
	public void delete(Object entity) {
		log.debug(ApplicationUtility.ENTER_METHOD+ "delete");
		entityManager.remove(entity);
		log.debug(ApplicationUtility.EXIT_METHOD+ "delete");
	}

	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#delete(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public void delete(Class<T> clazz, Serializable id) {
		log.debug(ApplicationUtility.ENTER_METHOD+ "delete");
		Query deleteQuery = entityManager.createQuery("delete from "+clazz.getSimpleName()+" where id = ?");
		deleteQuery.setParameter(0, id);
		deleteQuery.executeUpdate();
		log.debug(ApplicationUtility.EXIT_METHOD+ "delete");
	}
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#deleteAll(java.lang.Class)
	 */
	@Override
	public void deleteAll(Class<T> clazz) {
		log.debug(ApplicationUtility.ENTER_METHOD+ "deleteAll");
		Query deleteQuery = entityManager.createQuery("delete from "+clazz.getSimpleName());
		deleteQuery.executeUpdate();
		log.debug(ApplicationUtility.EXIT_METHOD+ "deleteAll");
	}
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#get(java.lang.Class, java.io.Serializable)
	 */
	@Override
	public <T> T get(Class<T> clazz, Serializable id) {
		return (T) entityManager.find(clazz, id);
	}
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#get(java.lang.String, java.lang.Object)
	 */
	@Override
	public <T> T get(String sql, Object... values) {
		log.debug(ApplicationUtility.ENTER_METHOD+ "get");
		Query createQuery = entityManager.createQuery(sql);
		if (null !=values && values.length > 0) {
			int i = 0;
			for (Object id : values) {
				if (null != id) {
					createQuery.setParameter(i++, id);
				}
			}
		}
		List<T> list = createQuery.getResultList();
		if(list == null || list.size()==0 ){
			return null ;
		}else {
			if (list.size()>1) {
				log.warn("(......in get function "+sql+"---"+values+")");
			}
			return list.get(0);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#getByExample(T)
	 */
	@Override
	public <T> T getByExample(T entity) {
		log.debug(ApplicationUtility.ENTER_METHOD+ "getByExample");
		String  sql = "from "+ entity;
		Query createQuery = entityManager.createNativeQuery(sql);
		List<T> list = createQuery.getResultList();
		if(list == null || list.size()==0 ){
			return null ;
		}else {
			if (list.size()>1) {
				log.warn("(......in getByExample function "+entity+")");
			}
			return list.get(0);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#getCount(java.lang.Class)
	 */
	@Override
	public <T> long getCount(Class<T> clazz) {
		log.debug(ApplicationUtility.ENTER_METHOD+ "getCount");
		String  sql = "select count(*) from "+ clazz.getSimpleName();
		Query query = entityManager.createQuery(sql);
		int intValue = ((Number) query.getSingleResult()).intValue();
		log.debug(ApplicationUtility.EXIT_METHOD+ "getCount" + ApplicationUtility.NO_RECORD_FOUND + intValue);
		return intValue;
	}
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#getCount(java.lang.String, java.lang.Object)
	 */
	@Override
	public long getCount(String sql, Object... values) {
		log.debug(ApplicationUtility.ENTER_METHOD+ "getCount");
		Query createQuery = entityManager.createQuery(sql);
		if (null !=values && values.length > 0) {
			int i = 0;
			for (Object id : values) {
				if (null != id) {
					createQuery.setParameter(i++, id);
				}
			}
		}
		int intValue = ((Number) createQuery.getSingleResult()).intValue();
		log.debug(ApplicationUtility.EXIT_METHOD+ "getCount" +ApplicationUtility.NO_RECORD_FOUND+ intValue);
		return intValue;
	}
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#list(java.lang.Class)
	 */
	@Override
	public <T> List<T> list(Class<T> clazz) {
		return entityManager.createQuery("select from "+clazz.getSimpleName()).getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#list(java.lang.String, java.lang.Object)
	 */
	@Override
	public <T> List<T> list(String sql, Object... values) {
		log.debug(ApplicationUtility.ENTER_METHOD+ "list");
		Query createQuery = entityManager.createQuery(sql);
		if (null !=values && values.length > 0) {
			int i = 0;
			for (Object id : values) {
				if (null != id) {
					createQuery.setParameter(i++, id);
				}
			}
		}
		log.debug(ApplicationUtility.EXIT_METHOD+ "list");
		return createQuery.getResultList();
	}
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#save(com.tw.entity.AbstractEntity)
	 */
	@Override
	public void save(AbstractEntity entity) throws DAOException{
		log.debug(ApplicationUtility.ENTER_METHOD+ "save");
		try{
			entity.onCreate();
			entityManager.persist(entity);
		}catch (ConstraintViolationException constraintViolationException){
			log.error(constraintViolationException);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DUPLICATE_RECORD_ERROR_MESSAGE,
					TakeAwayApplicationExceptionUtlility.DUPLICATE_RECORD_ERROR_CODE);
		}catch (DataAccessException accessException){
			log.error(accessException);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE,
					TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,accessException);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#update(com.tw.entity.AbstractEntity)
	 */
	@Override
	public void update(AbstractEntity entity) {
		log.debug(ApplicationUtility.ENTER_METHOD+ "update");
		try{
			entity.onUpdate();;
			entityManager.merge(entity);
		}catch (ConstraintViolationException constraintViolationException){
			log.error(constraintViolationException);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DUPLICATE_RECORD_ERROR_MESSAGE,
					TakeAwayApplicationExceptionUtlility.DUPLICATE_RECORD_ERROR_CODE);
		}catch (DataAccessException accessException){
			log.error(accessException);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE,
					TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,accessException);
		}finally{
			//session.close();
		}
		log.debug(ApplicationUtility.EXIT_METHOD+ "update");
	}
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#query(java.lang.String, java.lang.Object)
	 */
	@Override
	public List query(final String sql, final Object... values) {
		log.debug(ApplicationUtility.ENTER_METHOD+ "query");
		final Query query = entityManager.createQuery(sql);
		if(values != null){
			for(int i =0 ; i<values.length ;i++)
				query.setParameter(i, values[i]);
		}
		log.debug(ApplicationUtility.EXIT_METHOD+ "query");
		return query.getResultList();
	}
	
	/*public Set getUniqueValues(Class<T> clazz,final String values) {
		log.debug(ApplicationUtility.ENTER_METHOD+ "getUniqueValues");
		Criteria criteria = (Criteria) getSession().
				createCriteria(clazz).
				setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property(values), values);
		criteria.setProjection(Projections.distinct(projectionList));
		List fileUploadList  = criteria.list();
		log.debug(ApplicationUtility.EXIT_METHOD+ "getUniqueValues");
		return new HashSet(fileUploadList);
	}*/
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#getDetailsByQuery(java.lang.String)
	 */
	@Override
	public <T> List<T> getDetailsByQuery (String queryName) throws DAOException{
		log.debug(ApplicationUtility.ENTER_METHOD+ "getDetailsByQuery");
		List<T> queryList = new ArrayList<T>();
		try{
			Query getNamedQuery = entityManager.createNamedQuery(queryName);
			queryList = getNamedQuery.getResultList();
			if ((queryList==null || queryList.size()==0)){
				throw new DAOException(TakeAwayApplicationExceptionUtlility.NO_RECORD_ERROR_MESSAGE,
						TakeAwayApplicationExceptionUtlility.NO_RECORD_ERROR_CODE);
			}
		}catch (DataAccessException exception){
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}
		log.debug(ApplicationUtility.EXIT_METHOD+ "getDetailsByQuery");

		return queryList;
	}
	
	/* (non-Javadoc)
	 * @see com.tw.dao.impl.IAbstractEntityDAO#getUniqueDetailsByQuery(java.lang.String, java.lang.Object)
	 */
	@Override
	public <T> T getUniqueDetailsByQuery (String queryName, final Object... values) throws DAOException{
		log.debug(ApplicationUtility.ENTER_METHOD+ "getUniqueDetailsByQuery");
		try{
			Query getNamedQuery = entityManager.createNamedQuery(queryName);
			if(values != null){
				for(int i =0 ; i<values.length ;i++)
					getNamedQuery.setParameter(i, values[i]);
			}
			T returnObject = (T) getNamedQuery.getSingleResult();
			if ((returnObject==null)){
				throw new DAOException(TakeAwayApplicationExceptionUtlility.NO_RECORD_ERROR_MESSAGE,
						TakeAwayApplicationExceptionUtlility.NO_RECORD_ERROR_CODE);
			}
			log.debug(ApplicationUtility.EXIT_METHOD+ "getUniqueDetailsByQuery");
			return returnObject;
		}catch (DataAccessException exception){
			log.error(exception);
			throw new DAOException(TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_MESSAGE
					,TakeAwayApplicationExceptionUtlility.DATABASE_ERROR_CODE,exception);
		}

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
}
