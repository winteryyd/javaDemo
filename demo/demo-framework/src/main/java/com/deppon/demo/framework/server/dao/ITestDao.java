package com.deppon.demo.framework.server.dao;

import com.deppon.demo.base.entity.TestEntity;
import com.deppon.demo.framework.dao.ICrudDao;


public interface ITestDao extends ICrudDao<TestEntity> {
	public void initTable(TestEntity entity);
}
