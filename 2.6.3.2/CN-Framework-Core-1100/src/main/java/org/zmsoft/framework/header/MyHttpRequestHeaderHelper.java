package org.zmsoft.framework.header;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.framework.common.buzzinezz.ISHeaderSupport;
import org.zmsoft.framework.constants.ICFrameworkConstants;
import org.zmsoft.framework.support.MyBeanFactoryHelper;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * Http请求工具
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public class MyHttpRequestHeaderHelper implements ICFrameworkConstants {
	// 日志
	protected static Logger logger = LoggerFactory.getLogger(MyHttpRequestHeaderHelper.class);

	public static void loadHeaderParam(HttpServletRequest request) {

		try {
			ISHeaderSupport header = MyBeanFactoryHelper.getBean(ISHeaderSupport.My_HttpRequestHeader_Service);
			if (EmptyHelper.isEmpty(header)) {
				logger.warn("当前模式下自定义头处理没有实现......[" + ISHeaderSupport.My_HttpRequestHeader_Service + "]");
			} else {
				header.loadHeaderParam(request);;
			}
		} catch (Exception e) {
			// logger.error("定时任务管理加载失败", e);
		}

	}
}
