package com.deppon.demo.server.services;

import com.deppon.demo.server.dao.impl.TestDao;
import com.deppon.demo.server.service.base.ICrudService;
import com.deppon.demo.shared.entity.TestEntity;

public interface ITestService extends ICrudService<TestDao, TestEntity>{
	public void test();
}
