package org.zmsoft.framework.header;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.zmsoft.framework.cache.request.RequestHelper;
import org.zmsoft.framework.utils.EmptyHelper;

/**
 * Http请求工具
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 *
 */
public class MyHttpRequestParameterWrapper extends HttpServletRequestWrapper {

	public MyHttpRequestParameterWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Enumeration<String> getParameterNames() {
		Enumeration<String> enumeration = super.getParameterNames();
		ArrayList<String> list = Collections.list(enumeration);
		list.addAll(RequestHelper.getAttributes().getRequestBody().keySet());
		return Collections.enumeration(list);
	}

	@Override
	public String[] getParameterValues(String name) {
		String value = RequestHelper.getAttribute(name); 
		if (EmptyHelper.isNotEmpty(value)) {
			return new String[] { value };
		}
		return super.getParameterValues(name);
	}
}