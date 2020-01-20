package org.zmsoft.framework.job.impl;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 实际运行任务<br>
 * 有状态的任务不能并发执行
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
@DisallowConcurrentExecution // 有状态，防止并发
public class QuartzDisallowJobImpl extends ATaskJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		executeInternal(context);
	}
}
