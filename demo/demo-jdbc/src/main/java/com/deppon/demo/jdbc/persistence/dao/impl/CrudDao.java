package com.deppon.demo.jdbc.persistence.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.deppon.demo.jdbc.persistence.dao.ICrudDao;
import com.deppon.demo.jdbc.session.Session;

public class CrudDao<T> implements ICrudDao<T>{

	@Autowired
	public Session session;
	
	@Override
	public T get(String id) {
		// TODO Auto-generated method stub
		System.out.println("-------------------");
		return null;
	}

	@Override
	public T get(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(T entity) {
		// TODO Auto-generated method stub
		return session.save(entity);
	}

	@Override
	public boolean update(T entity) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean delete(T entity) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("CrudDao init");
		if(session==null){
			session=new Session();
			session.biuldConnection();
			System.out.println("CrudDao init success！");
		}else{
			session.biuldConnection();
			System.out.println("CrudDao init success！");
		}
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("CrudDao destroy");
		if(session!=null){
			session.close();
		}
	}

}
