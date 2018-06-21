package org.zmsoft.jfp.framework.support;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.constants.IDBConstants;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.token.TokenBusinessSupport;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.framework.utils.PKHelper;
import org.zmsoft.jfp.framework.utils.RandomHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * Token工具
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class MyTokenCommonSupport implements IFrameworkConstants, IDBConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	// 缓存中心
	@Resource
	protected ISCacheService myCacheService;

	public String loadSecurityCode(HttpServletRequest request, HttpServletResponse response) {
		String token = loadToken(request, response);
		return loadSecurityCode(token);
	}
	public String loadSecurityCode(String token) {
		String code = RandomHelper.getRandomNumerical(4);
		logger.debug("CheckCodeImageApi=====code>>>>>" + code + "=====token>>>>>" + token);
		myCacheService.putObject(CONSTANT_SECURITY_CODE + token, code, 300, false);
		return code;
	}

	/**
	 * 安全码验证
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean checkSecurityCode(HttpServletRequest request, HttpServletResponse response) {
		return checkSecurityCode(request, response, false);
	}
	public boolean checkSecurityCode(HttpServletRequest request, HttpServletResponse response, boolean autodelete) {
		String token = loadToken(request, response);
		String curCode = request.getParameter("securityCode");	
		return checkSecurityCode(token, curCode, autodelete);
	}
	public boolean checkSecurityCode(String token, String curCode, boolean autodelete) {
		try{
			if (EmptyHelper.isEmpty(curCode))
				return false;
			String secCode;
			if(autodelete)
				secCode = (String) myCacheService.deleteObject(CONSTANT_SECURITY_CODE + token, false);
			else
				secCode = (String) myCacheService.getObject(CONSTANT_SECURITY_CODE + token, false);
			logger.debug("CheckCodeImageApi=====curCode>>>>>" + curCode + "=====token>>>>>" + token + "=====SecurityCode>>>>>" + secCode);
			return secCode.equals(curCode);
		}catch(Exception e){
			return false;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 获得Token属性值
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String loadToken(HttpServletRequest request, HttpServletResponse response) {
		// 获得参数里面的Token
		String token = request.getParameter(CONSTANT_USER_TOKEN);
		if (EmptyHelper.isEmpty(token)) {
            token = request.getSession().getId().toUpperCase();
//			// 获得URI上面的Token
//			String[] path = request.getRequestURI().split("/");
//			if (path[path.length - 1].length() > 20)
//				token = path[path.length - 1];
		}
		return token;
	}

	/**
	 * 用户登录检查
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * @see #doCheckToken
	 */
	public boolean doCheckLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获得参数里面的Token
		String token = loadToken(request, response);
		if (EmptyHelper.isEmpty(token)) {
			return false;
		}

		logger.debug("token==>>>" + token);

		if (doCheckToken(token) == false) {
			return false;
		}

		return true;
	}

	/**
	 * 
	 * @param bizToken
	 * @return
	 * @throws Exception
	 * @see #tokenFail
	 */
	public boolean doCheckToken(String bizToken) throws Exception {
		TokenBusinessSupport tokenBiz = TokenBusinessSupport.build(bizToken);
		tokenBiz.setMyCacheService(myCacheService);
		return tokenBiz.checkToken();
	}

	/*
	 * 页面返回模式<br> 0：page页面直接返回<br> 1:JSON数据拦截返回
	 */
	public void doTokenFail(HttpServletRequest request, HttpServletResponse response, String returnType) {
		try {
			// 销毁session
			request.getSession().invalidate();
			if (ZERO.equals(returnType)) {
				// 标准拦截器
				//response.sendError(HttpServletResponse.SC_FORBIDDEN);// 403
				response.sendRedirect(SYSTEM_ROOT);
			} else if (ONE.equals(returnType)) {
				// JSON拦截器
				RESTResultBean<String> result = new RESTResultBean<String>();
				result.setJobId(PKHelper.creatPUKey());
				result.setCode(SYSTEM_TOKEN_ERROR_CODE);
				result.setMsg(MESSAGE_TOKEN_ERROR);// 登录失效，请重新登录
				response.setCharacterEncoding(SYSTEM_CHARSET);
				response.setContentType("text/html;charset=UTF-8");
				response.getOutputStream().write(JSON.toJSONString(result).toString().getBytes("UTF-8"));
			}
		} catch (Throwable e) {
		}
	}

	// 形式： sendError(int errnum)说明：用来向客户端发送错误信息，这对调试程序有很大帮助。常用的常量级错误代码有：
	// SC_CONTINUE， 状态码是100，表示客户端无法连接。
	// SC_SWITHING_PROTOCOLS,状态码是101，表示服务器正向报头中注明的协议切换。
	// SC_OK，状态码是200.表示请求被成功处理。
	// SC_CREATED，状态码是201，表示请求被成功处理，并在服务器方创建了一个新的资源。
	// SC_ACCEPTED，状态码是202，表示请求正在被处理，但尚未完成。
	// SC_NON_AUTHORITATIVE_INFORMATION，状态码是203，表示客户端所表达的mate信息并非来自服务器。
	// SC_NO_CONTENT，状态码是204，表示请求被成功处理，但没有新的信息返回。
	// SC_RESET_CONTENT,状态码是205，表示导致请求被发送的文档视图应该重置。
	// SC_PARTIAL_CONTENT,状态码是206，表示服务器已经完成对资源的GET请求。
	// SC_MULTI_CHOICES，状态码是300，表示对应于一系列表述的被请求资源都有明确的位置。
	// SC_MOVED_PERMANENTLY，状态码是301，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中应当使用一个新的URL.
	// SC_MOVED_TEMPORARILY,状态码是302，表示请求所申请的资源已经被移到一个新的地方，并且将来的参考点在请求中仍使用原来的URL.
	// SC_SEE_OTHER,状态码是303，表示请求的响应可以在一个不同的URL中找到。
	// SC_NOT_MODIFIED，状态码是304，表示一个有条件的GET操作发现资源可以利用，且没有被改变。
	// SC_USE_PROXY，状态码是305，表示被请求的资源必须通过特定位置的代理来访问。
	// SC_BAD_REQUEST，状态码是400，表示客户发出的请求句法不正确。
	// SC_UNAUTHORIZED,状态码是401，表示请求HTTP认证。
	// SC_PAYMENT_REQUIRED，状态码是402，表示为以后的使用保留。
	// SC_FORBIDDEN，状态码是403，表示服务器明白客户的请求，但拒绝响应。
	// SC_NOT_FAND，状态码是404，表示所请求的资源不可用。
	// SC_METHOD_NOT_ALLOWED,状态码是405，表示在请求行中标示的方法不允许对请求URL所标明的资源使用。
	// SC_NOT_ACCEPTTABLE,状态码是406，表示被请求的资源只能响应实体，而且此符合请求所发送的可接受头部域的实体的确包含不可接受的内容。
	// SC_PHOXY_AUTHENTICATION_REQUIRED,状态码是407，表示客户端必须先向代理验证。
}
