package com.deppon.demo.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deppon.demo.quartz.BaseQuartzJobBean;


public class QuartzLogCleanJob extends BaseQuartzJobBean {
	private static Logger logger = LoggerFactory.getLogger(QuartzLogCleanJob.class);
	@Override
	protected void doTask() throws Exception {
		logger.info("定时调度！");
	}
}
