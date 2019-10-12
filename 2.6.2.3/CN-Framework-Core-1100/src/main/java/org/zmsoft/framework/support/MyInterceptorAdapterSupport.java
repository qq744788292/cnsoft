package org.zmsoft.framework.support;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 自定义拦截器
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class MyInterceptorAdapterSupport extends MyFrameWorkSupport implements WebMvcConfigurer, HandlerInterceptor, ICFrameworkConstants {

}
