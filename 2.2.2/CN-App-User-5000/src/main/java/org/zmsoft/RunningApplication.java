package org.zmsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//自定义扫描路径
@ComponentScan({"org.zmsoft","org.zmsoft","com.cjsoft"})
public class RunningApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RunningApplication.class);
    }
	public static void main(String[] args) {
		SpringApplication.run(RunningApplication.class, args);
	}
}
