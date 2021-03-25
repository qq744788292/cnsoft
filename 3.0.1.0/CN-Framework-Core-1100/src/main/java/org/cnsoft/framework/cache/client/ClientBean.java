package org.cnsoft.framework.cache.client;

import org.cnsoft.framework.constants.ICFrameworkConstants;
import org.cnsoft.framework.utils.HttpRequestHelper;

/**
 * 客户端请求地址
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class ClientBean extends RequestHeaderBean implements ICFrameworkConstants {

	public final static String CLIENT = "CLIENT:";

	/**
	 * 加密密钥
	 */
	public String getEncryptKey() {
		return super.loadAttribute("encryptKey");
	}

	public void setEncryptKey(String encryptKey) {
		super.addAttribute("encryptKey", encryptKey);
	}

	/**
	 * 用户登录识别ID
	 */
	public String getClientId() {
		return super.loadAttribute("clientId");
	}

	public void setClientId(String clientId) {
		super.addAttribute("clientId", clientId);
	}

	/**
	 * 登录时间
	 */
	public String getClientTimestamp() {
		return super.loadAttribute("clientTimestamp");
	}

	public void setClientTimestamp(String clientTimestamp) {
		super.addAttribute("clientTimestamp", clientTimestamp);
	}

	/**
	 * 用户IP地址
	 */
	public String getClientIp() {
		return super.loadAttribute("clientIp");
	}

	public String getClientIpFormat() {
		return HttpRequestHelper.ipFormat(getClientIp());
	}

	public void setClientIp(String clientIp) {
		super.addAttribute("clientIp", clientIp);
	}

	/**
	 * 访问域名路径
	 */
	public String getSourceAddress() {
		return super.loadAttribute("sourceAddress");
	}

	public void setSourceAddress(String sourceAddress) {
		super.addAttribute("sourceAddress", sourceAddress);
	}

	/**
	 * 推广链接
	 */
	public String getReferralLink() {
		return super.loadAttribute("referralLink");
	}

	public void setReferralLink(String referralLink) {
		super.addAttribute("referralLink", referralLink);
	}
}