package org.zmsoft.config.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
// 开启异步调用
@EnableAsync
// 开启定时任务
@EnableScheduling
public class MySpringConfiger {

}
