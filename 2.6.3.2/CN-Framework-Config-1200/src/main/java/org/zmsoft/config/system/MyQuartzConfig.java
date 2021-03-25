package org.zmsoft.config.system;


import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.zmsoft.framework.job.MyTaskSchedulerFactory;
 
/**
 * 定时器配置
 */
@Configuration
public class MyQuartzConfig {
	@Autowired
	private MyTaskSchedulerFactory taskSchedulerFactory;
 
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setJobFactory(taskSchedulerFactory);
		return schedulerFactoryBean;
	}
 
	@Bean
	public Scheduler scheduler() {
		return schedulerFactoryBean().getScheduler();
	}
}