package com.deppon.demo.mybatis.dao;

import org.springframework.stereotype.Repository;

import com.deppon.demo.base.entity.TestEntity;
import com.deppon.demo.framework.server.dao.ITestDao;
import com.deppon.demo.mybatis.base.CrudDao;

@Repository
public class TestDaoImpl extends CrudDao<TestEntity> implements ITestDao{

	public void initTable(TestEntity entity) {
		getSqlSession().update(buildStatement(getNamespace(), "initTable"),entity);
	}

	@Override
	public String getNamespace() {
		return this.getClass().getName();
	}

}
