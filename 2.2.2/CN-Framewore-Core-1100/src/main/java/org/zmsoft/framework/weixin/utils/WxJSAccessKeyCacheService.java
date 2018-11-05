package org.zmsoft.framework.weixin.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.support.MyBusinessLogicSupport;
import org.zmsoft.framework.utils.EmptyHelper;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信分享SDK数字签名
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
@Service("WxShareAccessKeyService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WxJSAccessKeyCacheService extends MyBusinessLogicSupport {

	WxJsapiTicketSupport WxJsapiTicketSupport_ = new WxJsapiTicketSupport();

	/**
	 * 获得微信JSSDK数字签名
	 * 
	 * @param [request,
	 *            appID, appSecret, url]
	 * @return JSONObject
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	public JSONObject loadSignature(HttpServletRequest request, String appID, String appSecret, String url) throws Exception {
		return WxJsapiTicketSupport_.sign(request, loadJsapiTicket(loadAccessToken(appID, appSecret)), url);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	public final static String KEY_ACCESS_TOKEN = "WEIXIN:access_token";
	public final static String KEY_JSAPI_TICKET = "WEIXIN:jsapi_ticket";

	// 获得access_token
	// https://jingyan.baidu.com/article/ed2a5d1f340dd409f7be177c.html
	public String loadAccessToken(String appID, String appSecret) throws Exception {
		String data = (String) myCacheService.getObject(KEY_ACCESS_TOKEN, false);
		if (EmptyHelper.isEmpty(data)) {
			data = WxJsapiTicketSupport_.loadAccessToken(appID, appSecret);
			if (EmptyHelper.isNotEmpty(data)) {
				logger.debug("loadAccessToken=====putObject>>>>>=====data>>>>>" + data);
				myCacheService.putObject(KEY_ACCESS_TOKEN, data, 3600, false);
			} else {
				throw new Exception(MESSAGE_ERROR_SYSTEM);
			}
		}
		return data;
	}

	/**
	 * 获得jsapi_ticket
	 * 
	 * @param [access_token]
	 * @return String
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	// 获得jsapi_ticket
	public String loadJsapiTicket(String access_token) throws Exception {
		String data = (String) myCacheService.getObject(KEY_JSAPI_TICKET, false);
		if (EmptyHelper.isEmpty(data)) {
			data = WxJsapiTicketSupport_.loadJsapiTicket(access_token);
			if (EmptyHelper.isNotEmpty(data)) {
				logger.debug("loadAccessToken=====putObject>>>>>=====data>>>>>" + data);
				myCacheService.putObject(KEY_JSAPI_TICKET, data, 3600, false);
			} else {
				throw new Exception(MESSAGE_ERROR_SYSTEM);
			}
		}
		return data;
	}
}
