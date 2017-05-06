package com.deppon.demo.mybatis.service;

import org.springframework.stereotype.Service;

import com.deppon.demo.base.entity.TestEntity;
import com.deppon.demo.framework.server.dao.ITestDao;
import com.deppon.demo.framework.server.service.ITestService;
@Service
public class TestService extends CrudService<ITestDao,TestEntity> implements ITestService{

	public void test() {
		dao.initTable(new TestEntity());
	}

}
