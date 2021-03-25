package org.cnsoft.framework.cache.client;

import org.cnsoft.framework.beans.common.MapBean;
import org.cnsoft.framework.constants.ICFrameworkConstants;

/**
 * 接口数据返回主体
 * 
 * @author CNSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
public class RequestHeaderBean extends MapBean implements ICFrameworkConstants {

	/**
	 * 系统用户登录唯一标识
	 * 
	 * @return
	 */
	public String getToken() {
		return super.loadAttribute("token");
	}

	public void setToken(String token) {
		super.addAttribute("token", token);
	}

	/**
	 * 请求识别标识
	 * 
	 * @return
	 */
	public String getJobId() {
		return super.loadAttribute("jobId");
	}

	public void setJobId(String jobId) {
		super.addAttribute("jobId", jobId);
	}

	/**
	 * 版本ID
	 * 
	 * @return
	 */
	public String getVersion() {
		return super.loadAttribute("ver");
	}

	public void setVersion(String version) {
		super.addAttribute("ver", version);
	}

	/**
	 * 租户ID
	 * 
	 * @return
	 */
	public String getSid() {
		return super.loadAttribute("sid");
	}

	public void setSid(String sid) {
		super.addAttribute("sid", sid);
	}

	/**
	 * 项目ID
	 * 
	 * @return
	 */
	public String getPid() {
		return super.loadAttribute("pid");
	}

	public void setPid(String pid) {
		super.addAttribute("pid", pid);
	}
}
