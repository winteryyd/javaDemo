package com.deppon.demo.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzJobListener extends JobListenerSupport {

    private static Logger logger = LoggerFactory.getLogger(QuartzJobListener.class);

    @Override
    public String getName() {
        return "QuartzJobListener";
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
    	
    }
}
