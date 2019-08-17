package com.lcgj.sjzt.rool.manager;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 企业接口权限检查接口
 * 
 * @author spookfcy
 *
 */
public interface ISCompanyRoolCheck {
	String API_ROLE_KEY = "";
	
	public boolean checkCompanyHdp(RequestContext context, HttpServletRequest request, String appId) throws ZuulException;
}
