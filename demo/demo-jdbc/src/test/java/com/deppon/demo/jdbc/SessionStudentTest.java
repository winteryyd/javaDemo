package com.deppon.demo.jdbc;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.deppon.demo.jdbc.session.Session;

public class SessionStudentTest {
	public Session session;
	private Student student; 
	@Before
	public void init(){
		session=new Session();
		session.biuldConnection();
		student = new Student();
		student.setStuId(1);
		student.setStuName("小明");
		student.setBirthday(new Date());
	}
	
	@Test
	public void TestSave(){
		session.createTable(Student.class);
		boolean bool = session.save(student);
		assertEquals(true, bool);
	}
	
	@Test
	public void TestGet(){
		Student student = session.get(Student.class, 1);
		System.out.println("TestGet: "+student.getStuName());
		assertEquals("小明", student.getStuName());
	}
	
	@Test
	public void TestUpdate(){
		student.setStuName("小红");
		
		boolean bool = session.update(student);
		
		System.out.println("TestUpdate: "+session.get(Student.class, 1).getStuName());
		assertEquals(true, bool);
	}
	
	@Test
	public void TestDelete(){
		boolean bool = session.delete(student);
		System.out.println("TestDelete:"+bool);
		assertEquals(true, bool);
	}
	
	@After
	public void closeConnection(){
		session.close();
	}
}
