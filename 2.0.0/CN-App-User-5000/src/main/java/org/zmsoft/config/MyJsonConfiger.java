package org.zmsoft.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 启用FastJson
 * @author spookfcy
 * @see https://www.cnblogs.com/skyblue-li/p/5946117.html
 */
@Configuration
public class MyJsonConfiger {
	/**
	 * 通过@Bean注解
	 * 
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {

		// 1.定义一个消息转换对象convert
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		// 2.添加fastJson配置信息，是否需要格式化
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		// 3.在convert添加配置信息
		fastConverter.setFastJsonConfig(fastJsonConfig);

		// 4.将convert添加到converters中
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}
}
