package com.deppon.demo.quartz.entity;

import javax.persistence.Id;

public class ScheduleJob {
	/** 任务id */
	private String jobId;
	/** 任务名称 */
	private String jobName;
	/** 任务分组 */
	private String jobGroup;
	/** 所调类名 */
	private String classPath;
	/** 所调方法 */
	private String method;
	/** 任务状态 0禁用 1启用 2删除 */
	private String jobStatus;
	/** 任务运行时间表达式 */
	private String cronExpression;
	/** 任务描述 */
	private String remark;

	@Id
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
