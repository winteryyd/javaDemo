package com.deppon.demo.batch.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deppon.demo.batch.AbstractBatchExecutor;

//@Service
public class helloworld extends AbstractBatchExecutor {

	//@Resource(name = "helloWorldJob")
	private Job job;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobExplorer jobExplorer;

	@Override
	protected Job getJob() {
		return job;
	}

	@Override
	protected JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	@Override
	protected JobParameters getJobParameters() {
		return new JobParametersBuilder()  
	    .addString("inputFile", "file:./products.txt")
	    .addDate("date", new Date())  
	    .toJobParameters() ;
	}

	@Override
	protected JobExplorer getJobExplorer() {
		return jobExplorer;
	}

}
