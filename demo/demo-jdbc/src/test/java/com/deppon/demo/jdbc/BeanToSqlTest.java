package com.deppon.demo.jdbc;

import org.junit.Test;

import com.deppon.demo.jdbc.entity.UserInfo;
import com.deppon.demo.jdbc.util.BeanToSql;


public class BeanToSqlTest {
	@Test
	public void getInsertObjectSqlTest(){
		UserInfo u = new UserInfo();
		System.out.println(BeanToSql.getInsertObjectSql(u));
	}
}
