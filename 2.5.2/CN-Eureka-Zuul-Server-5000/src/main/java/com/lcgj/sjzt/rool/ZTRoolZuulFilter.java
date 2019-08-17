package com.lcgj.sjzt.rool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.utils.EmptyHelper;

import com.lcgj.sjzt.rool.manager.ZTManagerHdpService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 自定义过滤器
 * 
 * @author spookfcy
 * @see <ISCompanyRoolCheck>
 */
@Component
public class ZTRoolZuulFilter extends ZuulFilter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	ZTManagerHdpService ZTManagerHdpService_;

	/**
	 * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
	 * 
	 * @return
	 * @throws ZuulException
	 */
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		logger.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
		String appId = request.getParameter("appId");
		String msg;
		if (EmptyHelper.isEmpty(appId)) {
			msg = "appId is empty";
			logger.warn(msg);
			loadFail(context, msg);
			return null;
		}

		// 权限验证
		if (ZTManagerHdpService_.checkCompanyHdp(context, request, appId) == false) {
			msg = "appId is no rool";
			logger.warn(msg);
			loadFail(context, msg);
		}

		return null;
	}

	private void loadFail(RequestContext context, String msg) {
		context.setSendZuulResponse(false);
		context.setResponseStatusCode(401);
		try {
			context.getResponse().getWriter().write(msg);
		} catch (Exception e) {
		}
	}

	/**
	 * 这里可以写逻辑判断，是否要过滤，true,永远过滤。是否需要执行run函数
	 */
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 执行时序（ 0的优先级最高，即最先执行。）
	 * 
	 * @return
	 */
	public int filterOrder() {
		return 0;
	}

	/**
	 * 返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下： pre：路由之前 routing：路由之时 post：
	 * 路由之后 error：发送错误调用
	 */
	public String filterType() {
		return "pre";
	}

}
