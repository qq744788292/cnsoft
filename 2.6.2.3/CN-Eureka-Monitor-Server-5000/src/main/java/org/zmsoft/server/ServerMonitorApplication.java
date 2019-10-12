package org.zmsoft.server;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.zmsoft.config.ZMSystemConfig;
import org.zmsoft.framework.support.MyBeanFactoryHelper;

/**
 * 服务器监控
 * @author ZMSoft
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
//自定义扫描路径
@ComponentScan({"org.zmsoft","com.zmsoft"})
public class ServerMonitorApplication extends SpringBootServletInitializer implements ApplicationRunner{

    //////////////////////////////////////启动运行/////////////////////////////////////////////
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        SpringApplication springApplication = builder.application();
		springApplication.setBannerMode(Banner.Mode.OFF);
		builder.sources(ServerMonitorApplication.class);
        return builder;
    }
	public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ServerMonitorApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
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
