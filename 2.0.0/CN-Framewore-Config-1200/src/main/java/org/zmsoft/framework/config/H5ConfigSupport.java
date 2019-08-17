package org.zmsoft.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 公众号定义
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
@Service("H5ConfigSupport")
public class H5ConfigSupport {
	
	@Value("${h5.config.webServerUrl}")
	public String webServerUrl = "https://uatuser.gtdreamlife.com";
	// 前台接口服务器地址
	@Value("${h5.config.apiServerUrl}")
	public String apiServerUrl = "https://uatuser.gtdreamlife.com";

	// 公众号AppID
	//@Value("${h5.config.wxAppID}")
	public String wxAppID = "wx2bdac37566bcc9f5";
	//@Value("${h5.config.wxAppSecret}")
	public String wxAppSecret = "67874702d115633203cb4dbe31a165a7";
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	// 商户在网关系统上的商户号
	public static final String customid = "";
	public static final String customSecret = "";

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
