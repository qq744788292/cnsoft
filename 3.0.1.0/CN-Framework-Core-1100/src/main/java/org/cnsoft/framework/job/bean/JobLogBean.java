package org.cnsoft.framework.job.bean;

import org.cnsoft.framework.core.ObjectBean;

/** 定时任务运行日志表 */
public class JobLogBean extends ObjectBean {
	/**
	 * 任务识别ID
	 */
	private String taskId = null;

	/**
	 * 任务ID
	 */
	private String jobId = null;

	/**
	 * 任务编号
	 */
	private String jobNo = null;

	/**
	 * 任务名称
	 */
	private String jobName = null;

	/**
	 * 启动时间
	 */
	private String jobStartTime = null;

	/**
	 * 运行时间（秒）
	 */
	private String jobUseTime = null;

	/**
	 * 运行状态
	 */
	private String jobRunStatus = null;

	/**
	 * 运行结果
	 */
	private String jobRunResult = null;

	/**
	 * 数值型变量初始化<br>
	 * 仅在插入场合默认调用
	 * 
	 * @see #loadDefauft()
	 */
	public void prepareNumeric() {
	}

	/**
	 * 获取任务识别ID
	 *
	 * @return Task_id 任务识别ID
	 */
	public String getTaskId() {
		return this.taskId;
	}

	/**
	 * 获取任务ID
	 *
	 * @return Job_id 任务ID
	 */
	public String getJobId() {
		return this.jobId;
	}

	/**
	 * 获取任务编号
	 *
	 * @return Job_no 任务编号
	 */
	public String getJobNo() {
		return this.jobNo;
	}

	/**
	 * 获取任务名称
	 *
	 * @return Job_name 任务名称
	 */
	public String getJobName() {
		return this.jobName;
	}

	/**
	 * 获取启动时间
	 *
	 * @return Job_start_time 启动时间
	 */
	public String getJobStartTime() {
		return this.jobStartTime;
	}

	/**
	 * 获取运行时间（秒）
	 *
	 * @return Job_use_time 运行时间（秒）
	 */
	public String getJobUseTime() {
		return this.jobUseTime;
	}

	/**
	 * 获取运行状态
	 *
	 * @return Job_run_status 运行状态
	 */
	public String getJobRunStatus() {
		return this.jobRunStatus;
	}

	/**
	 * 获取运行结果
	 *
	 * @return Job_run_result 运行结果
	 */
	public String getJobRunResult() {
		return this.jobRunResult;
	}

	/**
	 * 设置任务识别ID
	 *
	 * @param Task_id
	 *            任务识别ID
	 */
	public void setTaskId(String taskid) {
		this.taskId = taskid;
	}

	/**
	 * 设置任务ID
	 *
	 * @param Job_id
	 *            任务ID
	 */
	public void setJobId(String jobid) {
		this.jobId = jobid;
	}

	/**
	 * 设置任务编号
	 *
	 * @param Job_no
	 *            任务编号
	 */
	public void setJobNo(String jobno) {
		this.jobNo = jobno;
	}

	/**
	 * 设置任务名称
	 *
	 * @param Job_name
	 *            任务名称
	 */
	public void setJobName(String jobname) {
		this.jobName = jobname;
	}

	/**
	 * 设置启动时间
	 *
	 * @param Job_start_time
	 *            启动时间
	 */
	public void setJobStartTime(String jobstarttime) {
		this.jobStartTime = jobstarttime;
	}

	/**
	 * 设置运行时间（秒）
	 *
	 * @param Job_use_time
	 *            运行时间（秒）
	 */
	public void setJobUseTime(String jobusetime) {
		this.jobUseTime = jobusetime;
	}

	/**
	 * 设置运行状态
	 *
	 * @param Job_run_status
	 *            运行状态
	 */
	public void setJobRunStatus(String jobrunstatus) {
		this.jobRunStatus = jobrunstatus;
	}

	/**
	 * 设置运行结果
	 *
	 * @param Job_run_result
	 *            运行结果
	 */
	public void setJobRunResult(String jobrunresult) {
		this.jobRunResult = jobrunresult;
	}

}
