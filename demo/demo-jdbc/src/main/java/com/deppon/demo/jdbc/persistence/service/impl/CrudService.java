package com.deppon.demo.jdbc.persistence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.deppon.demo.jdbc.persistence.dao.ICrudDao;
import com.deppon.demo.jdbc.persistence.dao.impl.CrudDao;
import com.deppon.demo.jdbc.persistence.entity.BaseEntity;
import com.deppon.demo.jdbc.persistence.service.ICrudService;

public abstract class CrudService<D extends CrudDao<T>, T extends BaseEntity<T>>
		extends BaseService implements ICrudService<D, T> {
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	/**
	 * 获取单条数据
	 * 
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}

	/**
	 * 获取单条数据
	 * 
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}

	/**
	 * 保存数据
	 * 
	 * @param entity
	 */
	public boolean insert(T entity) {
		dao.insert(entity);
		return true;
	}
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public boolean update(T entity){
		dao.update(entity);
		return true;
	}
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param id
	 * @return
	 */
	public boolean delete(String id){
		dao.delete(id);
		return true;
	}
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param entity
	 * @return
	 */
	public boolean delete(T entity){
		dao.delete(entity);
		return true;
	}
}
