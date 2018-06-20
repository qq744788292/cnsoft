package org.zmsoft.jfp.framework.client;

import org.zmsoft.jfp.framework.beans.common.FrameworkDataBean;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

/**
 * 业务请求Token数据算法
 * 
 * @author Spook
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @version 2.3.1 2015/06/23
 * @since 2.3.1 2015/06/23
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