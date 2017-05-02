package com.deppon.demo.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;

public abstract class AbstractBatchExecutor implements BatchExecutor {

	private static Logger logger = LoggerFactory.getLogger(AbstractBatchExecutor.class);

	protected abstract Job getJob();

	protected abstract JobLauncher getJobLauncher();

	protected abstract JobParameters getJobParameters();

	protected abstract JobExplorer getJobExplorer();

	
	public void execute() {
		JobExecution jobExecution;
		logger.info("{} will begin to execute.", getJob().getName());

		try {
			jobExecution = getJobLauncher().run(getJob(), getJobParameters());
			while (jobExecution.isRunning()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} 

	}
}
