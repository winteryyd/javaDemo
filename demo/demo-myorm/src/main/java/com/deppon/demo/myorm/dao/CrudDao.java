package com.deppon.demo.myorm.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.deppon.demo.cache.annotation.CacheDelete;
import com.deppon.demo.cache.annotation.CacheSave;
import com.deppon.demo.cache.annotation.Cacheable;
import com.deppon.demo.framework.dao.ICrudDao;
import com.deppon.demo.jdbc.session.Session;

public abstract class CrudDao<T> implements ICrudDao<T> {
	private static final Logger logger = LoggerFactory.getLogger(CrudDao.class);

	@Autowired
	public Session sqlSession;

	@Cacheable(
			namespace = "demo", 
			fieldsKey = { "#entityClass.name", "#id" }, 
			expire = 86400
			)
	public T get(Class<T> entityClass, String id) {
		return (T) sqlSession.get(entityClass, id);
	}
	
	@CacheSave(
			namespace = "demo", 
			fieldsKey = { "#entity.getClass().name","#entity.id"}, 
			expire = 86400
			)
	public int insert(T entity) {
		// TODO Auto-generated method stub
		return sqlSession.save(entity);
	}

	@CacheSave(
			namespace = "demo", 
			fieldsKey = { "#entity.getClass().name","#entity.id"}, 
			expire = 86400
			)
	public int update(T entity) {
		// TODO Auto-generated method stub
		return sqlSession.update(entity);
	}

	@CacheDelete(
			namespace = "demo", 
			fieldsKey = { "#entity.getClass().name","#entity.id"}
			)
	public int delete(T entity) {
		// TODO Auto-generated method stub
		return sqlSession.delete(entity);
	}

	public void afterPropertiesSet() throws Exception {
		if (sqlSession == null) {
			logger.info("sqlSession init failed！");
		} else {
			logger.info("sqlSession init success！");
		}
	}

	public void destroy() throws Exception {
		logger.info("CrudDao destroy");
		if (sqlSession != null) {
			sqlSession.close();
		}
	}

}
