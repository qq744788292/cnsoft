package org.zmsoft.jfp.framework.beans.pub;

import org.zmsoft.jfp.framework.beans.common.FrameworkDataBean;

/**
 * 短信接口Bean
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 *
 */
public class SMSBean extends FrameworkDataBean {
	/**
	 * 企业ID
	 */
	protected String companyId;

	/**
	 * 手机号码
	 */
	protected String phoneNum;
	/**
	 * 信息内容
	 */
	protected String message;

	/**
	 * 请求来源
	 */
	protected String sourceCmp;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSourceCmp() {
		return sourceCmp;
	}

	public void setSourceCmp(String sourceCmp) {
		this.sourceCmp = sourceCmp;
	}
}
