package com.deppon.demo.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public abstract class BaseQuartzJobBean extends QuartzJobBean{

    private static Logger logger = LoggerFactory.getLogger(BaseQuartzJobBean.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try{
        	doTask();
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            context.setResult(e);
        }
    }

    protected abstract void doTask() throws Exception;
}
