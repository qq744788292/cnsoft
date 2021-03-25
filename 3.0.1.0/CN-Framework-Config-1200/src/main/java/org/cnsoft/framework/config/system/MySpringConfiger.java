package org.cnsoft.framework.config.system;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程配置
 * 
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
@Configuration
// 开启异步调用
@EnableAsync
// 开启定时任务
@EnableScheduling
public class MySpringConfiger {

	@Bean(name = "threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {
		return new ThreadPoolTaskExecutor();
	}

}
