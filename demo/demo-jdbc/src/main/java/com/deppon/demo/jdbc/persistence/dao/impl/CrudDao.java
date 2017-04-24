package com.deppon.demo.jdbc.persistence.dao.impl;

import com.deppon.demo.jdbc.persistence.dao.ICrudDao;

public class CrudDao<T> implements ICrudDao<T>{

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
	public int insert(T entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(T entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(T entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}
