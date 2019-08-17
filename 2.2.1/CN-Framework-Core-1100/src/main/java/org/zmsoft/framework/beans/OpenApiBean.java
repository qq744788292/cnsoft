package org.zmsoft.framework.beans;

import org.zmsoft.framework.beans.common.FrameworkDataBean;

/**
 * 用户登录信息
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */

public class OpenApiBean extends FrameworkDataBean {
	/**
	 * 请求时间
	 */
	private String jobid;

	/**
	 * 企业应用识别ID
	 */
	private String appid;

	/**
	 * 通信数据签名
	 */
	private String sign;

	/**
	 * 通信数据签名
	 */
	private String data;

	public String getJobid() {
		return jobid;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
