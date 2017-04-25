package com.deppon.demo.server.services.impl;

import org.springframework.stereotype.Service;

import com.deppon.demo.jdbc.persistence.service.impl.CrudService;
import com.deppon.demo.server.dao.ITestDao;
import com.deppon.demo.server.dao.impl.TestDao;
import com.deppon.demo.server.services.ITestService;
import com.deppon.demo.shared.entity.TestEntity;
@Service
public class TestService extends CrudService<TestDao,TestEntity> implements ITestService{

	public void test() {
		dao.initTable(new TestEntity());
	}

}
