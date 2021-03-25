package org.cnsoft.framework.support;

import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 自定义拦截器
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class MyInterceptorAdapterSupport extends MyFrameWorkSupport implements WebMvcConfigurer, HandlerInterceptor, ICFrameworkConstants {

}
