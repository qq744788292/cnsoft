package org.zmsoft.framework.beans;

import org.zmsoft.framework.ObjectBean;

/**
 * 关联企业管理机构定义
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/08/08
 * @since 1.0.0 2018/02/02
 * @see <MyFrameWorkSupport>
 */
public class VersionBean extends ObjectBean {
	/**
	 * 适应渠道（1安卓2苹果）
	 */
	private String source;
	/**
	 * 当前版本
	 */
	private String version;
	/**
	 * 下载地址
	 */
	private String url;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
