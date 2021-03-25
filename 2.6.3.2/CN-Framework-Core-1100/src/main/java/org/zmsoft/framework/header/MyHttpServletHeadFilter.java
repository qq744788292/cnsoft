package org.zmsoft.framework.header;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * Http请求过滤器
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
@Component
public class MyHttpServletHeadFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 继续向后传递修改后的request,拦截器不能实现。
		/////////////////////////////请求头数据拦截/////////////////////////////////////////////
		MyHttpRequestHeaderHelper.loadHeaderParam((HttpServletRequest) request);
		//////////////////////////////////////////////////////////////////////////
		chain.doFilter(new MyHttpRequestParameterWrapper((HttpServletRequest) request), response);
	}

	@Override
	public void destroy() {
	}
	
}