package org.cnsoft.framework.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.cnsoft.framework.beans.MyBeanFactoryHelper;
import org.cnsoft.framework.common.buzzinezz.ISHeaderSupport;
import org.cnsoft.framework.utils.EmptyHelper;

public class HttpRequestParameterWrapper extends HttpServletRequestWrapper {

	public HttpRequestParameterWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Enumeration<String> getParameterNames() {
		Enumeration<String> enumeration = super.getParameterNames();
		ArrayList<String> list = Collections.list(enumeration);
		//请求头列表 
		ISHeaderSupport ishs = MyBeanFactoryHelper.getBean(ISHeaderSupport.My_HttpRequestHeader_Service);
		if(EmptyHelper.isNotEmpty(ishs))
			list.addAll(ishs.getHeaderParams().currentDatas().keySet());
		return Collections.enumeration(list);
	}

	@Override
	public String[] getParameterValues(String name) {
		//请求头拦截并返回内容
		ISHeaderSupport ishs = MyBeanFactoryHelper.getBean(ISHeaderSupport.My_HttpRequestHeader_Service);
		if(EmptyHelper.isNotEmpty(ishs)) {
			String value = ishs.getHeaderParams().loadAttribute(name); 
			if (EmptyHelper.isNotEmpty(value)) {
				return new String[] { value };
			}
		}
		return super.getParameterValues(name);
	}
}