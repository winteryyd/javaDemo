package com.deppon.demo.server.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deppon.demo.cache.annotation.CacheDelete;
import com.deppon.demo.cache.annotation.CacheSave;
import com.deppon.demo.cache.annotation.Cacheable;
import com.deppon.demo.cache.aop.CacheSaveAspect;
import com.deppon.demo.jdbc.session.Session;
import com.deppon.demo.server.dao.ICrudDao;

public abstract class CrudDao<T> implements ICrudDao<T> {
	private static final Logger logger = LoggerFactory.getLogger(CrudDao.class);

	@Autowired
	public Session session;

	@Cacheable(
			namespace = "demo", 
			fieldsKey = { "#entityClass.name", "#id" }, 
			expire = 86400
			)
	public T get(Class<T> entityClass, String id) {
		return (T) session.get(entityClass, id);
	}

	@CacheSave(
			namespace = "demo", 
			fieldsKey = { "#entity.getClass().name","#entity.id"}, 
			expire = 86400
			)
	public boolean insert(T entity) {
		// TODO Auto-generated method stub
		return session.save(entity);
	}

	@CacheSave(
			namespace = "demo", 
			fieldsKey = { "#entity.getClass().name","#entity.id"}, 
			expire = 86400
			)
	public boolean update(T entity) {
		// TODO Auto-generated method stub
		return session.update(entity);
	}

	@CacheDelete(
			namespace = "demo", 
			fieldsKey = { "#entity.getClass().name","#entity.id"}
			)
	public boolean delete(T entity) {
		// TODO Auto-generated method stub
		return session.delete(entity);
	}

	public void afterPropertiesSet() throws Exception {
		if (session == null) {
			session = new Session();
			session.biuldConnection();
			logger.info("CrudDao init success！");
		} else {
			session.biuldConnection();
			logger.info("CrudDao init success！");
		}
	}

	public void destroy() throws Exception {
		logger.info("CrudDao destroy");
		if (session != null) {
			session.close();
		}
	}

}
