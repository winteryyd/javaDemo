package com.deppon.demo.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.demo.server.dao.mybatis.IMybatisDao;
import com.deppon.demo.server.service.base.BaseService;
import com.deppon.demo.shared.entity.BaseEntity;

public abstract class MybatisService<D extends IMybatisDao<T>, T extends BaseEntity<T>>
		extends BaseService {
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
	 * 保存数据（插入或更新）
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void insert(T entity) {
		dao.insert(entity);
	}

	/**
	 * 保存数据（插入或更新）
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void update(T entity) {
		dao.update(entity);
	}

	/**
	 * 删除数据
	 * 
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}
}
