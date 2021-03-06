package com.deppon.demo.framework.service;

import com.deppon.demo.base.entity.BaseEntity;
import com.deppon.demo.framework.dao.ICrudDao;


public interface ICrudService<D extends ICrudDao<T>,T extends BaseEntity<T>> extends IBaseService{
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(Class<T> entityClass,String id);
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public boolean insert(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public boolean update(T entity);
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param entity
	 * @return
	 */
	public boolean delete(T entity);
}
