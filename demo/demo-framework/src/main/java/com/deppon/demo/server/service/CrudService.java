package com.deppon.demo.server.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.deppon.demo.server.dao.base.CrudDao;
import com.deppon.demo.shared.entity.BaseEntity;

public abstract class CrudService<D extends CrudDao<T>, T extends BaseEntity<T>>
		extends BaseService implements ICrudService<D, T> {
	/**
	 * 持久层对象
	 */
	@Autowired
	public D dao;

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public T get(Class<T> entityClass,String id) {
		return dao.get(entityClass, id);
	}

	/**
	 * 保存数据
	 * 
	 * @param entity
	 */
	public boolean insert(T entity) {
		return dao.insert(entity);
	}
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public boolean update(T entity){
		return dao.update(entity);
	}
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param entity
	 * @return
	 */
	public boolean delete(T entity){
		return dao.delete(entity);
	}
}