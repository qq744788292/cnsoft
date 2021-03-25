package org.cnsoft.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.common.buzzinezz.ISHeaderSupport;
import org.cnsoft.framework.utils.EmptyHelper;
import org.springframework.stereotype.Component;

/**
 * 过滤器
 */
@Component
public class HttpServletHeadFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 继续向后传递修改后的request,拦截器不能实现。
		/////////////////////////////请求头数据拦截/////////////////////////////////////////////
		try {
			ISHeaderSupport ishs = MyBeanFactoryHelper.getBean(ISHeaderSupport.My_HttpRequestHeader_Service);
			if(EmptyHelper.isNotEmpty(ishs))
				ishs.loadHeaderParam((HttpServletRequest) request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//////////////////////////////////////////////////////////////////////////
		chain.doFilter(new HttpRequestParameterWrapper((HttpServletRequest) request), response);
	}

	@Override
	public void destroy() {

	}
}