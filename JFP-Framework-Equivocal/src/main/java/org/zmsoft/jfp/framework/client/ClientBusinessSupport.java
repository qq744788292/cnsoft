package org.zmsoft.jfp.framework.client;

import org.zmsoft.jfp.framework.beans.common.FrameworkDataBean;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

/**
 * 业务请求Token数据算法
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class ClientBusinessSupport extends FrameworkDataBean implements IFrameworkConstants {

	public final static String CLIENT = "CLIENT:";

	/**
	 * 用户IP地址
	 */
	private String clientIp;
	/**
	 * 访问域名路径
	 */
	private String sourceAddress;
	/**
	 * 推广链接
	 */
	private String referralLink;

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getSourceAddress() {
		return sourceAddress;
	}

	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}

	public String getReferralLink() {
		return referralLink;
	}

	public void setReferralLink(String referralLink) {
		this.referralLink = referralLink;
	}

}