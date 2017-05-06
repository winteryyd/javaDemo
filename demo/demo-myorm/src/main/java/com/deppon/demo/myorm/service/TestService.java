package com.deppon.demo.myorm.service;

import org.springframework.stereotype.Service;

import com.deppon.demo.base.entity.TestEntity;
import com.deppon.demo.framework.server.dao.ITestDao;
import com.deppon.demo.framework.server.service.ITestService;
import com.deppon.demo.myorm.dao.TestDao;
@Service
public class TestService extends CrudService<ITestDao,TestEntity> implements ITestService{

	public void test() {
		dao.initTable(new TestEntity());
	}

}
