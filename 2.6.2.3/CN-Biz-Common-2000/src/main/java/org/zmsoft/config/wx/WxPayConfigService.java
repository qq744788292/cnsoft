package org.zmsoft.config.wx;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;
import org.zmsoft.framework.common.ISConfig;

/**
 * 微信支付配置
 */
@Service("WxPayConfigService")
public class WxPayConfigService extends AConfigSupport implements ISConfig {

	private final static String TYPE = "wx.config";

	// 公众号AppID
	private String wxAppID = "1234567890";
	private String wxAppSecret = "123456789012345678901234567890";

	// 商户在网关系统上的商户号
	private String wxCustomid = "1234567890";
	private String wxCustomSecret = "123456789012345678901234567890";

	private String wxAttach = "wx";

	@Override
	public String loadType() {
		return TYPE;
	}

	/////////////////////////////////////////////////////////
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

	public String getWxCustomid() {
		return wxCustomid;
	}

	public void setWxCustomid(String wxCustomid) {
		this.wxCustomid = wxCustomid;
	}

	public String getWxCustomSecret() {
		return wxCustomSecret;
	}

	public void setWxCustomSecret(String wxCustomSecret) {
		this.wxCustomSecret = wxCustomSecret;
	}

	public String getWxAttach() {
		return wxAttach;
	}

	public void setWxAttach(String wxAttach) {
		this.wxAttach = wxAttach;
	}

}
