package com.deppon.demo.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.demo.quartz.BaseQuartzJobBean;


public class CachePushJob extends BaseQuartzJobBean {
	private static Logger logger = LoggerFactory.getLogger(CachePushJob.class);
	@Override
	protected void doTask() throws Exception {
		logger.info("缓存刷新！");
	}
}
