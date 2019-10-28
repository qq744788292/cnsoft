package org.zmsoft.framework.beans.common;

import org.zmsoft.framework.ObjectBean;
import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 基底共通
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class FrameworkDataBean extends ObjectBean implements ICFrameworkConstants {

	/**
	 * 请求标识
	 */
	private String jobId;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * 登录标识
	 */
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 数据所属
	 */
	private String hdp;//SYSTEM

	public String getHdp() {
		return hdp;
	}

	public void setHdp(String hdp) {
		this.hdp = hdp;
	}

	/////////////////////////////////////////////////////////////////
	/**
	 * 页面1
	 */
	private String pv1 = null;
	/**
	 * 页面2
	 */
	private String pv2 = null;
	/**
	 * 页面3
	 */
	private String pv3 = null;
	/**
	 * 页面4
	 */
	private String pv4 = null;
	/**
	 * 页面5
	 */
	private String pv5 = null;

	public String getPv1() {
		return pv1;
	}

	public void setPv1(String pv1) {
		this.pv1 = pv1;
	}

	public String getPv2() {
		return pv2;
	}

	public void setPv2(String pv2) {
		this.pv2 = pv2;
	}

	public String getPv3() {
		return pv3;
	}

	public void setPv3(String pv3) {
		this.pv3 = pv3;
	}

	public String getPv4() {
		return pv4;
	}

	public void setPv4(String pv4) {
		this.pv4 = pv4;
	}

	public String getPv5() {
		return pv5;
	}

	public void setPv5(String pv5) {
		this.pv5 = pv5;
	}
}
