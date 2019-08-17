package org.zmsoft.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//开启异步调用
@EnableAsync
//开启定时任务
@EnableScheduling
//开启事务
@EnableTransactionManagement(proxyTargetClass = true) 
//自定义配置
//@ImportResource(locations={"classpath:config/spring.xml"})
public class MySpringConfiger {

}
