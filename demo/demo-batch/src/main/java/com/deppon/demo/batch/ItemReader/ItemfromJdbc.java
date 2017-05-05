package com.deppon.demo.batch.ItemReader;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.deppon.demo.jdbc.session.Session;


public class ItemfromJdbc implements ItemReader<Object>{
	private static final Logger logger = LoggerFactory.getLogger(ItemfromJdbc.class);
	@Autowired
	private Session session;
	
	private List<Object> list;
	private int index=-1;
	private String clazz;
	
	@SuppressWarnings("unchecked")
	public Object read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		if(list==null){
			if(session==null){
				session = new Session();
				session.biuldConnection();
			}
			list = (List<Object>) session.getAll(Class.forName(clazz));
		}
		index++;
		if(index<list.size()){
			return list.get(index);
		}else{
			return null;
		}
	}
	
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

}
