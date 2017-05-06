package com.deppon.demo.framework.server.service;

import com.deppon.demo.base.entity.TestEntity;
import com.deppon.demo.framework.server.dao.ITestDao;
import com.deppon.demo.framework.service.ICrudService;

public interface ITestService extends ICrudService<ITestDao, TestEntity>{
	public void test();
}
