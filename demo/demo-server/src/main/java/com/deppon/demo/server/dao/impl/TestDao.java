package com.deppon.demo.server.dao.impl;

import org.springframework.stereotype.Repository;

import com.deppon.demo.jdbc.persistence.dao.impl.CrudDao;
import com.deppon.demo.server.dao.ITestDao;
import com.deppon.demo.shared.entity.TestEntity;

@Repository
public class TestDao extends CrudDao<TestEntity> implements ITestDao {

	public void initTable(TestEntity entity) {
		System.out.println(session);
		session.dropTable(entity.getClass());
		session.createTable(entity.getClass());
	}

}
