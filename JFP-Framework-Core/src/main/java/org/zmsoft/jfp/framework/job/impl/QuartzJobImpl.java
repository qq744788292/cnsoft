package org.zmsoft.jfp.framework.job.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 实际运行任务<br>
 * 无状态的任务可并发执行
 *
 */
public class QuartzJobImpl extends ATaskJob {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		executeInternal(context);
	}
}
