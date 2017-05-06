package com.deppon.demo.myorm.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.deppon.demo.base.entity.BaseEntity;
import com.deppon.demo.framework.dao.ICrudDao;
import com.deppon.demo.framework.service.ICrudService;
import com.deppon.demo.myorm.dao.CrudDao;

public abstract class CrudService<D extends ICrudDao<T>, T extends BaseEntity<T>>
		implements ICrudService<D, T> {
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
		return dao.insert(entity)>-1;
	}
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public boolean update(T entity){
		return dao.update(entity)>-1;
	}
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param entity
	 * @return
	 */
	public boolean delete(T entity){
		return dao.delete(entity)>-1;
	}
}
