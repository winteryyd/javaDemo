package com.deppon.demo.jdbc.persistence.dao;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


/**
 * DAO支持类实现
 * @param <T>
 */
public interface ICrudDao<T> extends IBaseDao , InitializingBean, DisposableBean {

	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id);
	
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