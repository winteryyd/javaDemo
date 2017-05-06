package com.deppon.demo.server.dao;

import org.springframework.stereotype.Component;

import com.deppon.demo.shared.entity.UserEntity;
@Component("userDao")
public interface IUserDao {
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public UserEntity get(String id);
	
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	public int insert(UserEntity entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(UserEntity entity);
	
	/**
	 * 删除数据（物理删除，从数据库中彻底删除）
	 * @param entity
	 * @return
	 */
	public int delete(UserEntity entity);
}
