package org.zmsoft.framework.config.biz;

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
	public String wxAppID = "wx7ef4303be1693d7c";
	//@Value("${h5.config.wxAppSecret}")
	public String wxAppSecret = "1283260a018e57e90b6f16b46e213f4a";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	// 商户在网关系统上的商户号
	public static final String customid = "1503477921";
	public static final String customSecret = "a5a5838594d8c31b5a5e949d1aec0d34";

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
