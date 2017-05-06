package com.deppon.demo.myorm.dao;

import org.springframework.stereotype.Repository;

import com.deppon.demo.base.entity.TestEntity;
import com.deppon.demo.framework.server.dao.ITestDao;



@Repository
public class TestDao extends CrudDao<TestEntity> implements ITestDao {

	public void initTable(TestEntity entity) {
		System.out.println(sqlSession);
		sqlSession.dropTable(entity.getClass());
		sqlSession.createTable(entity.getClass());
	}
	
}
