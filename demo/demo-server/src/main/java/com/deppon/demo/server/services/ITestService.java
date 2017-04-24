package com.deppon.demo.server.services;

import com.deppon.demo.jdbc.persistence.service.ICrudService;
import com.deppon.demo.server.dao.ITestDao;
import com.deppon.demo.server.dao.impl.TestDao;
import com.deppon.demo.shared.entity.TestEntity;

public interface ITestService extends ICrudService<TestDao, TestEntity>{
	public void test();
}
