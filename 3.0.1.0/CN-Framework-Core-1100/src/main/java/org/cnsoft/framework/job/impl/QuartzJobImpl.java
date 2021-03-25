package org.cnsoft.framework.job.impl;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 实际运行任务<br>
 * 无状态的任务可并发执行
 *
 * @author CNSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class QuartzJobImpl extends ADynamicTaskJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		executeInternal(context);
	}
}
