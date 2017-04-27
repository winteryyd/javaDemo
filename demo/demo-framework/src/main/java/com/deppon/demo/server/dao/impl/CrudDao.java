package com.deppon.demo.server.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.deppon.demo.cache.annotation.CacheUpdate;
import com.deppon.demo.cache.annotation.Cacheable;
import com.deppon.demo.jdbc.session.Session;
import com.deppon.demo.server.dao.ICrudDao;

public abstract class CrudDao<T> implements ICrudDao<T> {

	@Autowired
	public Session session;

	@Cacheable(namespace = "demo", 
			   fieldsKey = { "#entityClass.name", "#id" }, 
			   expire = 86400
			)
	public T get(Class<T> entityClass, String id) {
		return (T) session.get(entityClass, id);
	}

	public boolean insert(T entity) {
		// TODO Auto-generated method stub
		return session.save(entity);
	}

	@CacheUpdate(valueType = Object.class)
	public boolean update(T entity) {
		// TODO Auto-generated method stub
		return session.update(entity);
	}

	public boolean delete(T entity) {
		// TODO Auto-generated method stub
		return session.delete(entity);
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("CrudDao init");
		if (session == null) {
			session = new Session();
			session.biuldConnection();
			System.out.println("CrudDao init success！");
		} else {
			session.biuldConnection();
			System.out.println("CrudDao init success！");
		}
	}

	public void destroy() throws Exception {
		System.out.println("CrudDao destroy");
		if (session != null) {
			session.close();
		}
	}

}
