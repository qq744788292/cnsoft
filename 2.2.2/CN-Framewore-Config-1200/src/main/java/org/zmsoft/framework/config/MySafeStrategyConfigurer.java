package org.zmsoft.framework.config;

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

import com.alibaba.fastjson.JSON;
import org.zmsoft.framework.cache.client.ClientHelper;
import org.zmsoft.framework.client.ClientBusinessSupport;
import org.zmsoft.framework.holder.SpringContextHolder;
import org.zmsoft.framework.log.LogDataBean;
import org.zmsoft.framework.log.LogDataSupport;
import org.zmsoft.framework.support.MyControllerSupport;
import org.zmsoft.framework.support.MyFailSupport;
import org.zmsoft.framework.support.MyInterceptorAdapterSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.HttpRequestHelper;

/**
 * IP安全策略
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Configuration
public class MySafeStrategyConfigurer extends MyInterceptorAdapterSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	MyYmlConfigSupport myYmlConfigSupport;

	public MyYmlConfigSupport getMyYmlConfigSupport() {
		if (EmptyHelper.isEmpty(myYmlConfigSupport))
			myYmlConfigSupport = SpringContextHolder.getBean("MyYmlConfigSupport");
		return myYmlConfigSupport;
	}

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
			// 变量初始化
			String _returnType_ = ZERO;// 页面返回模式, 0：page页面直接返回,1:JSON数据拦截返回
			MyControllerSupport controller = null;
			// 获得接口返回类型
			if (handler instanceof HandlerMethod) {
				HandlerMethod h = (HandlerMethod) handler;
				if ("Result".equals(h.getMethod().getReturnType().getSimpleName())) {
					_returnType_ = ONE;
				}
				if (h.getBean() instanceof MyControllerSupport) {
					controller = (MyControllerSupport) h.getBean();
				}
			}

			// 登录检测
			if (controller != null) {
				if (controller.doCheckLogin(request, response) == false) {
					MyFailSupport.doTokenFail(request, response, _returnType_);
					handle = false;
				}
				if ((handle == true) && (getMyYmlConfigSupport().isRoolCheck() == true) && (controller.doCheckRole(request, response) == false)) {
					MyFailSupport.doRoleFail(request, response, _returnType_);
					handle = false;
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
			System.out.println("=====消耗时间=====>>>>>" + request.getRequestURL().toString());
			System.out.println("=====请求参数=====>>>>>" + JSON.toJSONString(request.getParameterMap()));
			System.out.println("=====消耗时间=====>>>>>" + executeTime);
		}

		// 登录日志
		ClientBusinessSupport cbs = ClientHelper.getClientBusinessSupport();
		LogDataSupport LogDataService_ = SpringContextHolder.getBean("LogDataSupport");
		LogDataBean logData = new LogDataBean();
		logData.setApi(cbs.getReferralLink());
		logData.setIpAdress(cbs.getClientIp());
		logData.setUserId("MySafeStrategyConfigurer");
		logData.setLogData(request.getParameterMap());
		logData.setExecuteTime(executeTime);
		LogDataService_.setLogData(logData);
		LogDataService_.flush();
		return true;
	}
}
