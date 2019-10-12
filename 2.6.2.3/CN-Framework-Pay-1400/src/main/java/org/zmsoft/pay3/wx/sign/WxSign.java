package org.zmsoft.pay3.wx.sign;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.zmsoft.framework.utils.RandomHelper;

/**
 * 微信页面分享jsapi_ticket
 * @author Administrator
 *
 */
public class WxSign {

	public static Map<String, String> sign(String jsapi_ticket, HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = createNonceStr();
		String timestamp = createTimestamp();
		String string1;
		String signature = "";
		String url = request.getRequestURL() + "?" + request.getQueryString();
		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str + "&timestamp=" + timestamp + "&url=" + url;
		//System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
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

	public static String createNonceStr() {
		return RandomHelper.getRandomString(16);
	}

	public static String createTimestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
}
