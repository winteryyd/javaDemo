package com.deppon.demo.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseBatchExecutor extends AbstractBatchExecutor {

	private static Logger logger = LoggerFactory.getLogger(BaseBatchExecutor.class);

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobExplorer jobExplorer;
	
	protected abstract Job getJob();

	protected abstract JobParameters getJobParameters();

	public JobLauncher getJobLauncher(){
		return jobLauncher;
	}

	public JobExplorer getJobExplorer(){
		return jobExplorer;
	}


}
