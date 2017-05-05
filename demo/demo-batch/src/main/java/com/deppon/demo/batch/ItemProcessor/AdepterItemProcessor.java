package com.deppon.demo.batch.ItemProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class AdepterItemProcessor implements ItemProcessor<Object, Object> {
	private static final Logger logger = LoggerFactory.getLogger(AdepterItemProcessor.class);
	public Object process(Object o) throws Exception {
		logger.info("ItemProcessor:{}",o);
		return o;
	}

}
