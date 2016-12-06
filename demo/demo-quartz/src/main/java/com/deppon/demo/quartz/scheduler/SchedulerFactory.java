package com.deppon.demo.quartz.scheduler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;

import com.deppon.demo.quartz.entity.ScheduleJob;
import com.deppon.demo.quartz.job.QuartzJobFactory;

public class SchedulerFactory {
	/** 调度器 */
	private StdScheduler scheduler;
	/** 计划任务map */
	private static Map<String, ScheduleJob> jobMap = new HashMap<String, ScheduleJob>();

	/**
	 * 添加任务
	 * 
	 * @param scheduleJob
	 */
	public void addJob(ScheduleJob scheduleJob) {
		jobMap.put(scheduleJob.getJobGroup() + "_" + scheduleJob.getJobName(),
				scheduleJob);
	}

	/**
	 * 启动调度
	 * 
	 * @throws SchedulerException
	 */
	public void startSchedule() throws SchedulerException {
		Collection<ScheduleJob> jobList = jobMap.values();
		//System.out.println(jobList.size());
		for (ScheduleJob job : jobList) {
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(),
					job.getJobGroup());
			CronTrigger trigger = (CronTrigger) scheduler
					.getTrigger(triggerKey);
			// 不存在，创建一个
			if (null == trigger) {
				JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
						.withIdentity(job.getJobName(), job.getJobGroup())
						.build();
				jobDetail.getJobDataMap().put("scheduleJob", job);
				// 表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(job.getCronExpression());
				// 按新的cronExpression表达式构建一个新的trigger
				trigger = TriggerBuilder.newTrigger()
						.withIdentity(job.getJobName(), job.getJobGroup())
						.withSchedule(scheduleBuilder).build();
				scheduler.scheduleJob(jobDetail, trigger);
			} else {
				// Trigger已存在，那么更新相应的定时设置
				// 表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
						.cronSchedule(job.getCronExpression());
				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
						.withSchedule(scheduleBuilder).build();
				// 按新的trigger重新设置job执行
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		}
	}

	/**
	 * 得到计划中的任务
	 * 指那些已经添加到quartz调度器的任务，
	 * 因为quartz并没有直接提供这样的查询接口，
	 * 所以我们需要结合JobKey和Trigger来实现
	 * @return
	 * @throws SchedulerException
	 */
	public List<ScheduleJob> getPlanJob() throws SchedulerException {
		// Scheduler scheduler = scheduler;
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler
					.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJob job = new ScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setRemark("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler
						.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 获取运行中的任务
	 * 实现和计划中的任务类似
	 * @return
	 * @throws SchedulerException
	 */
	public List<ScheduleJob> getRunJob() throws SchedulerException {
		List<JobExecutionContext> executingJobs = scheduler
				.getCurrentlyExecutingJobs();
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(
				executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			ScheduleJob job = new ScheduleJob();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setRemark("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler
					.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;

	}

	/**
	 * 暂停任务
	 * 
	 * @param scheduleJob
	 * @return
	 */
	public boolean pauseJob(ScheduleJob scheduleJob) {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroup());
		try {
			scheduler.pauseJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 恢复任务
	 * 和暂停任务相对
	 * @param scheduleJob
	 * @return
	 */
	public boolean resumeJob(ScheduleJob scheduleJob) {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroup());
		try {
			scheduler.resumeJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 删除任务
	 * 删除任务后，所对应的trigger也将被删除 
	 * @param scheduleJob
	 * @return
	 */
	public boolean deleteJob(ScheduleJob scheduleJob) {
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),
				scheduleJob.getJobGroup());
		try {
			scheduler.deleteJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 刷新时间表达式
	 * 更新之后，任务将立即按新的时间表达式执行
	 * @param scheduleJob
	 * @return
	 */
	public boolean refreshJob(ScheduleJob scheduleJob) {
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),
					scheduleJob.getJobGroup());
			// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder
					.cronSchedule(scheduleJob.getCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
					.withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 立即运行任务 
	 * 这里的立即运行，只会运行一次，方便测试时用。
	 * quartz是通过临时生成一个trigger的方式来实现的，
	 * 这个trigger将在本次任务运行完成之后自动删除。
	 * trigger的key是随机生成的
	 * @param scheduleJob
	 * @return
	 */
	public boolean runOnce(ScheduleJob scheduleJob){
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		try {
			scheduler.triggerJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return false;
		}
	}
	public StdScheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(StdScheduler scheduler) {
		this.scheduler = scheduler;
	}
}
