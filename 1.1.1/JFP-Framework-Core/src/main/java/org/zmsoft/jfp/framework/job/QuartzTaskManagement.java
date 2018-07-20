package org.zmsoft.jfp.framework.job;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.job.bean.ScheduleJobBean;
import org.zmsoft.jfp.framework.job.impl.QuartzDisallowJobImpl;
import org.zmsoft.jfp.framework.job.impl.QuartzJobImpl;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.PKHelper;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 动态添加任务<br>
 * Quartz Task Management
 * 
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * @see <QuartzJobImpl> <QuartzDisallowJobImpl>
 */
@Service("QuartzTaskManagement")
public class QuartzTaskManagement implements IFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 监控中心
	 */
	private Map<String, ScheduleJobBean> jobMap = new HashMap<String, ScheduleJobBean>();

	/**
	 * 任务工厂<br>
	 * schedulerFactoryBean 由spring创建注入
	 * 
	 * @see spring-task.xml
	 */
	@Resource
	private Scheduler schedulerFactory;

	/**
	 * 强制运行一个任务，独立一次
	 * 
	 * @param jobConfig
	 * @throws SchedulerException
	 */
	public void stopJob(ScheduleJobBean scheduleJobBean) throws SchedulerException {
		// 判断是否存在，存在的场合关闭抓取
		logger.debug("=====>>>>>=====>>>>>监控任务准备关闭=====>>>>>=====>>>>>" + scheduleJobBean.getJobId() + "." + scheduleJobBean.getJobName());
		if (schedulerFactory == null) {
			schedulerFactory = BeanFactoryHelper.getBean("schedulerFactoryBean");
		}
		// 移除原有任务
		ScheduleJobBean job = jobMap.remove(scheduleJobBean.getJobId());
		if (job == null) {
			return;
		} else {
			// 关闭运行中的任务
			TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
			CronTrigger trigger = (CronTrigger) schedulerFactory.getTrigger(triggerKey);
			schedulerFactory.deleteJob(trigger.getJobKey());
		}
		logger.debug("=====>>>>>=====>>>>>监控任务关闭成功=====>>>>>=====>>>>>" + scheduleJobBean.getJobId() + "." + scheduleJobBean.getJobName());
	}

	// 启动一个任务
	public void startJob(ScheduleJobBean scheduleJobBean) throws Exception {
		logger.debug("=====>>>>>=====>>>>>定时监控任务开启=====>>>>>=====>>>>>" + scheduleJobBean.getJobId() + "." + scheduleJobBean.getJobName());
		// 注册一个任务到监控中心
		{
			// 判断是否存在，存在的场合停止操作
			if (jobMap.containsKey(scheduleJobBean.getJobId())) {
				return;
			}
			logger.debug("=====>>>>>添加一个任务到监控器=====>>>>>" + scheduleJobBean.getJobId() + "." + scheduleJobBean.getJobName());

			// 注册到任务监控中心
			jobMap.put(scheduleJobBean.getJobId(), scheduleJobBean);
		}
		if (schedulerFactory == null) {
			schedulerFactory = BeanFactoryHelper.getBean("schedulerFactoryBean");
		}
		// 这里获取任务信息数据
		{
			TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJobBean.getJobName(), scheduleJobBean.getJobGroup());
			// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) schedulerFactory.getTrigger(triggerKey);
			// 不存在，创建一个
			if (null == trigger) {
				JobDetail jobDetail;
				// 指定目标封装为1有状态的任务不能并发执行2无状态的任务可并发执
				// 解决多线程控制问题 concurrent
				if (ONE.equals(scheduleJobBean.getIsConcurrent()))
					jobDetail = JobBuilder.newJob(QuartzDisallowJobImpl.class).withIdentity(scheduleJobBean.getJobName(), scheduleJobBean.getJobGroup()).build();
				else
					jobDetail = JobBuilder.newJob(QuartzJobImpl.class).withIdentity(scheduleJobBean.getJobName(), scheduleJobBean.getJobGroup()).build();
				// 表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobBean.getCronExpression());
				// 按新的cronExpression表达式构建一个新的trigger
				trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
				// 传递配置参数
				jobDetail.getJobDataMap().put("jobConfig", scheduleJobBean);
				jobDetail.getJobDataMap().put("jobKey", PKHelper.creatUUKey());
				// 设置job执行
				schedulerFactory.scheduleJob(jobDetail, trigger);
			} else {// Trigger已存在，那么更新相应的定时设置
				// 表达式调度构建器
				CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJobBean.getCronExpression());
				// 按新的cronExpression表达式重新构建trigger
				trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
				// 按新的trigger重新设置job执行
				schedulerFactory.rescheduleJob(triggerKey, trigger);
			}
		}
		logger.debug("=====>>>>>=====>>>>>定时监控任务开启成功=====>>>>>=====>>>>>" + scheduleJobBean.getJobId() + "." + scheduleJobBean.getJobName());
	}
}
