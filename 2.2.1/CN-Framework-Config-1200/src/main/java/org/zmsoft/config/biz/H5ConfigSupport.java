package org.zmsoft.config.biz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * ZM公众号定义
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
@Service("H5ConfigSupport")
public class H5ConfigSupport {
	
	@Value("${h5.config.webServerUrl}")
	public String webServerUrl = "1";
	// 前台接口服务器地址
	@Value("${h5.config.apiServerUrl}")
	public String apiServerUrl = "1";

	// 公众号AppID
	//@Value("${h5.config.wxAppID}")
	public String wxAppID = "wx8e26f5ce8a0690d6";
	//@Value("${h5.config.wxAppSecret}")
	public String wxAppSecret = "3c0576b9aa124274ac2c5b8266ba887e";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	// 商户在网关系统上的商户号
	public final static String customid = "1503477921";
	public final static String customSecret = "d7e5d8361ee758e6a344d314d035ba50";

	public String getWebServerUrl() {
		return webServerUrl;
	}

	public void setWebServerUrl(String webServerUrl) {
		this.webServerUrl = webServerUrl;
	}

	public String getApiServerUrl() {
		return apiServerUrl;
	}

	public void setApiServerUrl(String apiServerUrl) {
		this.apiServerUrl = apiServerUrl;
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

	public static String getCustomid() {
		return customid;
	}

	public static String getCustomsecret() {
		return customSecret;
	}

	////////////////////////////////////////////////////////////////////////////////////

}
