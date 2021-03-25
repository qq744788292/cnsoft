package org.zmsoft.framework.beans.common;

import org.zmsoft.framework.constants.ICFrameworkConstants;

/**
 * 接口数据返回主体
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class HeaderBean extends RequestBean implements ICFrameworkConstants {

	public String getToken() {
		return super.getAttribute("token");
	}

	public void setToken(String token) {
		super.addAttribute("token", token);
	}

	public String getJobId() {
		return super.getAttribute("jobId");
	}

	public void setJobId(String jobId) {
		super.addAttribute("jobId", jobId);
	}

	public String getVersion() {
		return super.getAttribute("version");
	}

	public void setVersion(String version) {
		super.addAttribute("version", version);
	}

	public String getHdp() {
		return super.getAttribute("hdp");
	}

	public void setHdp(String hdp) {
		super.addAttribute("hdp", hdp);
	}

}
