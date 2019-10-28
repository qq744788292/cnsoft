package org.zmsoft.config.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.zmsoft.framework.cache.client.ClientHelper;
import org.zmsoft.framework.client.ClientBusinessSupport;
import org.zmsoft.framework.log.LogDataHelper;
import org.zmsoft.framework.support.MyControllerSupport;
import org.zmsoft.framework.support.MyFailSupport;
import org.zmsoft.framework.support.MyInterceptorAdapterSupport;
import org.zmsoft.framework.support.MyPageControllerSupport;
import org.zmsoft.framework.utils.HttpRequestHelper;

/**
 * IP安全策略
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 * @see <WebMvcConfigurer>, <HandlerInterceptor>
 */
@Configuration
public class MySafeStrategyConfigurer extends MyInterceptorAdapterSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// registry.addResourceHandler("/**").addResourceLocations("/");
	// }
	//
	// /**
	// * 多模块的jsp访问，默认是src/main/webapp，但是多模块的目录只设置yml文件不行
	// * @return
	// */
	// @Bean
	// public InternalResourceViewResolver viewResolver() {
	// InternalResourceViewResolver resolver = new
	// InternalResourceViewResolver();
	// resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
	// // jsp目录
	// resolver.setPrefix("/views");
	// // 后缀
	// resolver.setSuffix(".jsp");
	// return resolver;
	// }

	/////////////////////////////////////////////////////////////////////////////////////////////////
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		registry.addInterceptor(new MySafeStrategyConfigurer()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean handle = true;
		if (handler instanceof HandlerMethod) {// 判断资源路径
			// 记录请求开始时间
			request.setAttribute("_request_start_time_", System.currentTimeMillis());
			// 记录用户信息
			{
				ClientBusinessSupport clent = new ClientBusinessSupport();
				// 用户IP地址
				clent.setClientIp(HttpRequestHelper.getClientRemoteIPAddr(request));
				// 访问域名路径
				clent.setSourceAddress(HttpRequestHelper.getServerAddr(request));
				// 完整路径
				clent.setReferralLink(request.getRequestURL().toString());
				ClientHelper.setClientBusinessSupport(clent);
			}
			// 获得接口返回类型
			if (handler instanceof HandlerMethod) {
				HandlerMethod h = (HandlerMethod) handler;
				// 0：page页面直接返回 1:JSON数据拦截返回
				if (h.getBean() instanceof MyPageControllerSupport) {// JSP网页模式
					MyPageControllerSupport controller = (MyPageControllerSupport) h.getBean();

					// 是否开始检查
					if (controller != null) {
						// 登录检测
						if (controller.doCheckLogin(request, response) == false) {
							MyFailSupport.doTokenFail(request, response, ZERO);
							handle = false;
						}
						// 操作权限检查
						else if (controller.doCheckRole(request, response) == false) {
							MyFailSupport.doRoleFail(request, response, ZERO);
							handle = false;
						}
					}
				} else if (h.getBean() instanceof MyControllerSupport) {
					MyControllerSupport controller = (MyControllerSupport) h.getBean();

					// 是否开始检查
					if (controller != null) {
						// 登录检测
						if (controller.doCheckLogin(request, response) == false) {
							MyFailSupport.doTokenFail(request, response, ONE);
							handle = false;
						}
					}
				} else {// MyTokenCommonSupport

				}
			}
		}
		return handle;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (handler instanceof HandlerMethod) {
			try {
				// 计算
				long startTime = (Long) request.getAttribute("_request_start_time_");
				long endTime = System.currentTimeMillis();
				long executeTime = endTime - startTime;

				doLogData(request, response, (HandlerMethod) handler, executeTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 制作请求日志
	 * 
	 * @param elkLogCache
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	public boolean doLogData(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler, long executeTime) throws Exception {

		if (logger.isDebugEnabled()) {
			System.out.println("=====请求路径=====>>>>>" + request.getRequestURL().toString());
			// System.out.println("=====请求参数=====>>>>>" +
			// JSON.toJSONString(request.getParameterMap()));
			System.out.println("=====消耗时间=====>>>>>" + executeTime);
		}

		// 请求日志
		LogDataHelper.saveLog("MySafeStrategyConfigurer", null, executeTime);
		return true;
	}
}
