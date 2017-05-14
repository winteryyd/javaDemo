package com.deppon.demo.batch.executor;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.deppon.demo.batch.BaseBatchExecutor;
@Component("cachePushExecutor")
public class CachePushExecutor extends BaseBatchExecutor{
	
	@Resource(name = "testEntityBatchJob")
	private Job job;

	@Override
	protected Job getJob() {
		// TODO Auto-generated method stub
		return job;
	}

	@Override
	protected JobParameters getJobParameters() {
		// TODO Auto-generated method stub
		return new JobParametersBuilder()  
	    .addLong("_ids", 0L)
	    .addDate("date", new Date())  
	    .toJobParameters() ;
	}
}
