package org.zmsoft.framework.job.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 实际运行任务<br>
 * 无状态的任务可并发执行
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class QuartzJobImpl extends ATaskJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		executeInternal(context);
	}
}
