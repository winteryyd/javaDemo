package com.deppon.demo.batch.ItemProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.deppon.demo.base.entity.TestEntity;


public class AdepterItemProcessor implements ItemProcessor<Object, Object> {
	private static final Logger logger = LoggerFactory.getLogger(AdepterItemProcessor.class);
	public Object process(Object o) throws Exception {
		logger.info("{}",o);
		TestEntity e= (TestEntity)o;
		if(e.getId().equals("50")){
			throw new Exception("invalid data");
		}
		return o;
	}

}
