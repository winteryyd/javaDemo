package com.deppon.demo.jdbc;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.deppon.demo.jdbc.session.Session;

public class SessionTest {
	public Session session;
	private UserInfo ui; 
	@Before
	public void init(){
		session=new Session();
		session.biuldConnection();
		session.createTable(UserInfo.class);
		ui = new UserInfo();
		ui.setId(3);
		ui.setName("小明");
		ui.setPwd("abcd");
		ui.setAge(new Date());
	}
	
	@Test
	public void TestSave(){
		session.save(ui);
		//assertEquals(1200, getInsuranceAmount(2222));
	}
	
	
}
