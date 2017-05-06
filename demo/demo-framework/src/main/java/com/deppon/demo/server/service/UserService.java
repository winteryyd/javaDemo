package com.deppon.demo.server.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deppon.demo.server.dao.mybatis.IUserDao;
import com.deppon.demo.shared.entity.UserEntity;

@Service
public class UserService {
	@Resource(name="userDao")
	private IUserDao userDao;

	public UserEntity get(String id) {
		return userDao.get(id);
	}

	@Transactional(readOnly = false)
	public void insert(UserEntity userEntity) {
		userDao.insert(userEntity);
	}

	@Transactional(readOnly = false)
	public void update(UserEntity userEntity) {
		userDao.update(userEntity);
	}

	@Transactional(readOnly = false)
	public void delete(UserEntity userEntity) {
		userDao.delete(userEntity);
	}
}
