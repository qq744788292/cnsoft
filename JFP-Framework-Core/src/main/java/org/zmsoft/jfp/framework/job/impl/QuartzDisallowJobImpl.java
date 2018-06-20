package org.zmsoft.jfp.framework.job.impl;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 实际运行任务<br>
 * 有状态的任务不能并发执行
 *
 */
@DisallowConcurrentExecution // 有状态，防止并发
public class QuartzDisallowJobImpl extends ATaskJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		executeInternal(context);
	}
}
