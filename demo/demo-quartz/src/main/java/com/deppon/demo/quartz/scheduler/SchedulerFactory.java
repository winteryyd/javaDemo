package com.deppon.demo.quartz.scheduler;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;

import com.deppon.demo.quartz.entity.ScheduleJob;
import com.deppon.demo.quartz.job.QuartzJobFactory;

public class SchedulerFactory {
	/** 调度器 */
	private StdScheduler scheduler;
	/** 计划任务map */
	private static Map<String, ScheduleJob> jobMap = new HashMap<String, ScheduleJob>();

	/**
     * 添加任务
     * @param scheduleJob
     */
    public void addJob(ScheduleJob scheduleJob) {
    	jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(), scheduleJob);
    }
	
    public void startSchedule() throws SchedulerException{
    	Collection<ScheduleJob> jobList =  jobMap.values();
		for (ScheduleJob job : jobList) {
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			//不存在，创建一个
	    	if (null == trigger) {
	    		JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
	    			.withIdentity(job.getJobName(), job.getJobGroup()).build();
	    		jobDetail.getJobDataMap().put("scheduleJob", job);
	    		//表达式调度构建器
	    		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
	    			.getCronExpression());
	    		//按新的cronExpression表达式构建一个新的trigger
	    		trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
	    		scheduler.scheduleJob(jobDetail, trigger);
	    	} else {
	    		// Trigger已存在，那么更新相应的定时设置
	    		//表达式调度构建器
	    		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job
	    			.getCronExpression());
	    		//按新的cronExpression表达式重新构建trigger
	    		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
	    			.withSchedule(scheduleBuilder).build();
	    		//按新的trigger重新设置job执行
	    		scheduler.rescheduleJob(triggerKey, trigger);
	    	}
		}
    }
    
	public StdScheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(StdScheduler scheduler) {
		this.scheduler = scheduler;
	}
}
