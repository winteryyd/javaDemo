package com.deppon.demo.jdbc;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
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
	}
	
	@Test
	public void TestSave(){
		session.createTable(UserInfo.class);
		ui = new UserInfo();
		ui.setId(3);
		ui.setName("小明");
		ui.setPwd("abcd");
		ui.setAge(new Date());
	
		boolean bool = session.save(ui);
		assertEquals(true, bool);
	}
	
	@Test
	public void TestGet(){
		UserInfo userInfo = session.get(UserInfo.class, 3);
		System.out.println("TestGet: "+userInfo.getName());
		assertEquals("小明", userInfo.getName());
	}
	
	@Test
	public void TestUpdate(){
		ui = new UserInfo();
		ui.setId(3);
		ui.setPwd("1234");
		ui.setAge(new Date());
		ui.setName("小红");
		
		boolean bool = session.update(ui);
		
		System.out.println("TestUpdate: "+session.get(UserInfo.class, 3).getName());
		assertEquals(true, bool);
	}
	
	@Test
	public void TestDelete(){
		ui = new UserInfo();
		ui.setId(3);
		ui.setPwd("1234");
		ui.setAge(new Date());
		ui.setName("小红");
		boolean bool = session.delete(ui);
		System.out.println("TestDelete:"+bool);
		assertEquals(true, bool);
	}
	
	@After
	public void close(){
		session.dropTable(UserInfo.class);
		session.close();
	}
}
