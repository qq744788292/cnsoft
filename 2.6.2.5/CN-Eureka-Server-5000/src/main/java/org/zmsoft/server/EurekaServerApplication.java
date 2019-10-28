package org.zmsoft.server;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.zmsoft.config.ZMSystemConfig;
import org.zmsoft.framework.support.MyBeanFactoryHelper;

/**
 * 注册中心
 * @author ZMSoft
 *
 */
@SpringBootApplication
@EnableEurekaServer // 服务注册功能
@EnableWebSecurity // 防csrf攻击
public class EurekaServerApplication extends WebSecurityConfigurerAdapter {
	
	/**
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		super.configure(http);
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}    
	
    ////////////////////////////////////////启动后加载内容////////////////////////////////////////
	public void run(ApplicationArguments args) throws Exception {
		try {
			ZMSystemConfig system = MyBeanFactoryHelper.getBean("ZMSystemConfig");
			system.run();
		} catch (Exception e) {
		}
	}
}
