package org.cnsoft.framework.config.system;


import org.cnsoft.framework.job.MyTaskSchedulerFactory;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
 
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