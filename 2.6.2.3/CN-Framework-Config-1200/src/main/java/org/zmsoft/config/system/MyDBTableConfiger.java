package org.zmsoft.config.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//开启事务
@EnableTransactionManagement(proxyTargetClass = true) 
//数据库初始化配置
//@ImportResource(locations={"classpath:spring-db.xml"})
public class MyDBTableConfiger {

}
