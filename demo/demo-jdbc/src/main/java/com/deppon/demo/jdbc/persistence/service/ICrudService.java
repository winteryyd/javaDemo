package com.deppon.demo.jdbc.persistence.service;

import com.deppon.demo.jdbc.persistence.dao.ICrudDao;
import com.deppon.demo.jdbc.persistence.entity.BaseEntity;

public interface ICrudService<D extends ICrudDao<T>,T extends BaseEntity<T>> extends IBaseService{
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id);
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity);
	
	
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
	 * @param id
	 * @return
	 */
	public boolean delete(String id);
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param entity
	 * @return
	 */
	public boolean delete(T entity);
}
