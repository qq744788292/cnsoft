package org.zmsoft.framework.beans.common;

import org.zmsoft.framework.beans.ObjectBean;
import org.zmsoft.framework.constants.IFrameworkConstants;

/**
 * 接口数据返回主体
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class HeaderBean extends ObjectBean implements IFrameworkConstants {

	/**
	 * 系统通信安全标识
	 */
	protected String token = null;
	/**
	 * 请求时间戳
	 */
	protected String jobId = null;
	/**
	 * 客户端版本标识
	 */
	protected String version = null;
	/**
	 * 渠道标识
	 */
	protected String hdp = null;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHdp() {
		return hdp;
	}

	public void setHdp(String hdp) {
		this.hdp = hdp;
	}

}
