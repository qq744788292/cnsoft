package org.zmsoft.framework.weixin.support;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.servlet.http.HttpServletRequest;

import org.zmsoft.framework.utils.HttpServiceHelper;
import org.zmsoft.framework.utils.RandomHelper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 微信分享参数获取
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
public class WxJsapiTicketSupport extends WxOauth2TokenSupport {

	/**
	 * 获得access_token
	 * 
	 * @param [appID,
	 *            appSecret]
	 * @return String
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	// https://jingyan.baidu.com/article/ed2a5d1f340dd409f7be177c.html
	public String loadWxAccessToken(String appID, String appSecret) throws Exception {
		logger.debug("loadAccessToken=====appID>>>>>" + appID + "=====appSecret>>>>>" + appSecret);
		// 异步获取
		// 有效期7200秒
		// https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid='+appid+'&secret='+appsecret
		String serviceURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID + "&secret=" + appSecret;
		String result = HttpServiceHelper.doHttpGET(serviceURL);
		logger.debug("loadAccessToken=====result>>>>>" + result);

		JSONObject access_token = JSON.parseObject(result);
		String data = access_token.getString("access_token");
		logger.debug("loadAccessToken=====access_token>>>>>" + data);

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
	public String loadWxJsapiTicket(String access_token) throws Exception {
		logger.debug("loadJsapiTicket=====access_token>>>>>" + access_token);
		// 异步获取
		// 有效期7200秒
		// https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=' +
		// token.access_token + '&type=jsapi
		String serviceURL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
		String result = HttpServiceHelper.doHttpGET(serviceURL);
		// JSONObject access_token = JSON.parseObject(result);
		JSONObject jsapi_ticket = JSON.parseObject(result);
		String data = jsapi_ticket.getString("ticket");
		logger.debug("loadJsapiTicket=====ticket>>>>>" + data);
		return data;
	}

	/**
	 * 获得数据签名
	 * 
	 * @param [request,
	 *            jsapi_ticket, url]
	 * @return JSONObject
	 * @throws Exception
	 * @author Zmsoft
	 * @version 0.1.0 2018/4/2
	 * @since 0.1.0 2018/4/2
	 */
	public JSONObject signJsapiTicket(HttpServletRequest request, String jsapi_ticket, String url) {
		logger.debug("sign=====jsapi_ticket>>>>>" + jsapi_ticket);
		logger.debug("sign=====url>>>>>" + url);

		JSONObject ret = new JSONObject();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String strTemp;
		String signature = "";
		// 注意这里参数名必须全部小写，且必须有序
		strTemp = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		// System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(strTemp.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		logger.debug("sign=====ret>>>>>" + ret);
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public static String create_nonce_str() {
		return RandomHelper.getRandomString(16);
	}

	public static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
