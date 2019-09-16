package org.jfpc.framework.support;

import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jfpc.common.login.service.LoginService;
import org.jfpc.constants.ISFrameworkConstants;
import org.jfpc.framework.bean.LoginerBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.cache.SessionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * 画面控制层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyControllerSupport extends MyFrameworkSupport implements ISFrameworkConstants {

	private static final Logger logger = LoggerFactory.getLogger(MyControllerSupport.class);

	/**
	 * 页面视图
	 */
	public MyModelAndViewSupport getModelAndView(String pageId) {
		return new MyModelAndViewSupport(pageId);
	}

	/**
	 * 页面视图
	 */
	public MyModelAndViewSupport getModelAndView() {
		return new MyModelAndViewSupport("");
	}

	/**
	 * 登录拦截
	 * 
	 * @param response
	 */
	@ModelAttribute
	public boolean doCheckLogin(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
			logger.debug("requestUrl//////////>>>>>>=====" + request.getRequestURI());
		// 定义sessionID
		super.setSessionid(request.getSession().getId());
		// 从参数里面获得
		String token = request.getParameter(CONSTANT_USER_TOKEN);
		if (logger.isDebugEnabled())
			logger.debug("Token///////////////>>>>>>=====" + token);
		super.setToken(token);
		if (StringUtils.isEmpty(token)) {
			goBack(request, response);
			return false;
		}
		else{
			super.setToken(token);				
		}

		// 判断TOKEN有效性
		if (!doCheckToken(request, response)) {
			goBack(request, response);
		}
		return true;
	}

	String callBackUrl = "/00009999";	
	
	/**
	 * 业务回调方法
	 * @param request
	 * @param response
	 */
	private void goBack(HttpServletRequest request, HttpServletResponse response){
		String _callBackUrl = request.getParameter("callBackUrl");
		if (logger.isDebugEnabled())
			logger.debug("doCheckLogin>>>>>>=====" + _callBackUrl);
		try {
			if (StringUtils.isNotEmpty(_callBackUrl))
				callBackUrl = _callBackUrl;
			SessionHelper.removeSessionAttribute(CONSTANT_USER_TOKEN);
			RESTResultBean rs = new RESTResultBean();
			rs.setCode("9");
			rs.setMessage("登录失效，请重新登录");
			
			OutputStream out = response.getOutputStream();
			
			out.write(JSONObject.fromObject(rs).toString().getBytes("UTF-8"));
			out.flush();
			out.close();
			//response.sendRedirect(callBackUrl);
			//response.sendError(405);
//			形式： sendError(int errnum )说明：用来向客户端发送错误信息，这对调试程序有很大帮助。常用的常量级错误代码有：
//			SC_CONTINUE， 状态码是100，表示客户端无法连接。
//			SC_SWITHING_PROTOCOLS,状态码是101，表示服务器正向报头中注明的协议切换。
//			SC_OK，状态码是200.表示请求被成功处理。
//			SC_CREATED，状态码是201，表示请求被成功处理，并在服务器方创建了一个新的资源。
//			SC_ACCEPTED，状态码是202，表示请求正在被处理，但尚未完成。
//			SC_NON_AUTHORITATIVE_INFORMATION，状态码是203，表示客户端所表达的mate信息并非来自服务器。
//			SC_NO_CONTENT，状态码是204，表示请求被成功处理，但没有新的信息返回。
//			SC_RESET_CONTENT,状态码是205，表示导致请求被发送的文档视图应该重置。
//			SC_PARTIAL_CONTENT,状态码是206，表示服务器已经完成对资源的GET请求。
//			SC_MULTI_CHOICES，状态码是300，表示对应于一系列表述的被请求资源都有明确的位置。
//			SC_MOVED_PERMANENTLY，状态码是301，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中应当使用一个新的URL.
//			SC_MOVED_TEMPORARILY,状态码是302，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中仍使用原来的URL.
//			SC_SEE_OTHER,状态码是303，表示请求的响应可以在一个不同的URL中找到。
//			SC_NOT_MODIFIED，状态码是304，表示一个有条件的GET操作发现资源可以利用，且没有被改变。
//			SC_USE_PROXY，状态码是305，表示被请求的资源必须通过特定位置的代理来访问。
//			SC_BAD_REQUEST，状态码是400，表示客户发出的请求句法不正确。
//			SC_UNAUTHORIZED,状态码是401，表示请求HTTP认证。
//			SC_PAYMENT_REQUIRED，状态码是402，表示为以后的使用保留。
//			SC_FORBIDDEN，状态码是403，表示服务器明白客户的请求，但拒绝响应。
//			SC_NOT_FAND，状态码是404，表示所请求的资源不可用。
//			SC_METHOD_NOT_ALLOWED,状态码是405，表示在请求行中标示的方法不允许对请求URL所标明的资源使用。
//			SC_NOT_ACCEPTTABLE,状态码是406，表示被请求的资源只能响应实体，而且此符合请求所发送的可接受头部域的实体的确包含不可接受的内容。
//			SC_PHOXY_AUTHENTICATION_REQUIRED,状态码是407，表示客户端必须先向代理验证。			
		} catch (Exception e) {
		}
	}
	
	@Resource
	protected LoginService LoginService_;
	public boolean doCheckToken(HttpServletRequest request, HttpServletResponse response) {
		LoginerBean loginer = new LoginerBean();
		loginer.setToken(super.getToken());
		boolean result = LoginService_.doCheckToken(loginer);
		//request.setAttribute("loginUrl", loginer.getLoginUrl());
		request.setAttribute("", "");
		return result;
	}

	public boolean doCheckComputer(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return false;
	}

}
