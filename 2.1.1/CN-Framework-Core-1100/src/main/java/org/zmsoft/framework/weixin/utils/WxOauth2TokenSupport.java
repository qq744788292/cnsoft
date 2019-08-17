package org.zmsoft.framework.weixin.utils;

import org.zmsoft.framework.support.MyBusinessLogicSupport;
import org.zmsoft.framework.utils.HttpServiceHelper;
import org.zmsoft.framework.weixin.bean.WxUserBean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 获取用户openid
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
public class WxOauth2TokenSupport extends MyBusinessLogicSupport {
	
	private static String ServerURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
	private static String ServerURL_GetUserInfo = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

	public String load(WxUserBean user) throws Exception {
		JSONObject wxmsg = loadAccessToken(user);
		logger.debug(wxmsg.toJSONString());
		return wxmsg.getString("openid");
	}

    /**
      * 获取access_token
      * @param [user]
      * @return JSONObject
      * @throws Exception
      * @author Zmsoft
      * @version 0.1.0 2018/4/2
      * @since 0.1.0 2018/4/2
    */
	public JSONObject loadAccessToken(WxUserBean user) throws Exception {
		String url = String.format(ServerURL, user.getAppid(), user.getSecret(), user.getCode());
		logger.debug("loadAccessToken=====>>>>>"+url);
		return JSON.parseObject(HttpServiceHelper.doHttpGET(url));
	}

    /**
      * 获取用户详细信息
      * @param [user]
      * @return JSONObject
      * @throws Exception
      * @author Zmsoft
      * @version 0.1.0 2018/4/2
      * @since 0.1.0 2018/4/2
    */
	public JSONObject loadWeixinUserInfo(WxUserBean user) throws Exception {
		String url = String.format(ServerURL_GetUserInfo, user.getAccess_token(), user.getOpenid());
		logger.debug("loadAccessToken=====>>>>>"+url);
		return JSON.parseObject(HttpServiceHelper.doHttpGET(url));
	}
}
