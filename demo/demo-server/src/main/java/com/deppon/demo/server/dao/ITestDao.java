package com.deppon.demo.server.dao;

import com.deppon.demo.shared.entity.TestEntity;

public interface ITestDao extends ICrudDao<TestEntity> {
	public void initTable(TestEntity entity);
}
