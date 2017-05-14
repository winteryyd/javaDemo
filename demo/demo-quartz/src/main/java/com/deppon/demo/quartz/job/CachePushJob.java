package com.deppon.demo.quartz.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.demo.batch.BatchExecutor;
import com.deppon.demo.quartz.BaseQuartzJobBean;

public class CachePushJob extends BaseQuartzJobBean {
	private static Logger logger = LoggerFactory.getLogger(CachePushJob.class);
	
	@Resource(name = "cachePushExecutor")
	private BatchExecutor batchExecutor;

	@Override
	protected void doTask() throws Exception {
		logger.info("缓存刷新！");
		batchExecutor.execute();
	}
	
	
}
