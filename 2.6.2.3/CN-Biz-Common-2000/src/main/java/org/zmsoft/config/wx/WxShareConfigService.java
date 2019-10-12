package org.zmsoft.config.wx;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;
import org.zmsoft.framework.common.ISConfig;
import org.zmsoft.framework.weixin.support.WxJSAccessKeyCacheService;

/**
 * 微信支付配置
 */
@Service("WxShareConfigService")
public class WxShareConfigService extends AConfigSupport implements ISConfig {

	private final static String TYPE = "wx.config";

	// 公众号AppID
	private String wxAppID = "1234567890";
	private String wxAppSecret = "123456789012345678901234567890";

	@Override
	public String loadType() {
		return TYPE;
	}

	public String getWxAppID() {
		return wxAppID;
	}

	public void setWxAppID(String wxAppID) {
		this.wxAppID = wxAppID;
	}

	public String getWxAppSecret() {
		return wxAppSecret;
	}

	public void setWxAppSecret(String wxAppSecret) {
		this.wxAppSecret = wxAppSecret;
	}

	/////////////////////////////////////////////////////////
	// 微信开通类别（0开通1关闭）
	public String wxUseType = "1";

	public String getWxUseType() {
		return wxUseType;
	}

	public void setWxUseType(String wxUseType) {
		this.wxUseType = wxUseType;
	}

	public String accessToken = "access_token";
	public String jsapiTicket = "jsapi_ticket";

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	/////////////////////////////////////////////////////////
	@Resource
	protected WxJSAccessKeyCacheService myWeixinCommonSupport;

	/**
	 * 
	 * @param reload
	 * @throws Exception
	 */
	public void otherWise() throws Exception {
		if (ONE.equals(wxUseType))
			return;
		try {
			accessToken = myWeixinCommonSupport.loadAccessToken(wxAppID, wxAppSecret);
			jsapiTicket = myWeixinCommonSupport.loadJsapiTicket(accessToken);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
