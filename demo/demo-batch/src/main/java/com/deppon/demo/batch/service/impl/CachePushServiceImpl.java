package com.deppon.demo.batch.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.stereotype.Service;

import com.deppon.demo.batch.BaseBatchExecutor;
import com.deppon.demo.batch.service.ICachePushService;
@Service
public class CachePushServiceImpl extends BaseBatchExecutor implements ICachePushService {
	
	@Resource(name = "testEntityBatchJob")
	private Job job;

	public void pushCache() {
		super.execute();
	}

	@Override
	protected Job getJob() {
		// TODO Auto-generated method stub
		return job;
	}

	@Override
	protected JobParameters getJobParameters() {
		// TODO Auto-generated method stub
		return new JobParametersBuilder()  
	    .addString("inputFile", "file:./aaaa.txt")
	    //.addDate("date", new Date())  
	    .toJobParameters() ;
	}
}
