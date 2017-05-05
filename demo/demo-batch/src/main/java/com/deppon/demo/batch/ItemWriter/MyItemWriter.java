package com.deppon.demo.batch.ItemWriter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class MyItemWriter implements ItemWriter<Object>{
	private static final Logger logger = LoggerFactory.getLogger(MyItemWriter.class);
	public void write(List<? extends Object> items) throws Exception {
		for(Object item:items ){
			logger.info("MyItemWriter:{}",item);
		}
	}

}
