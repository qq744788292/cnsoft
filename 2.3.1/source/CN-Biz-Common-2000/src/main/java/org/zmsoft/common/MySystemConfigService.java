
package org.zmsoft.common;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;
import org.zmsoft.framework.common.ISConfig;

/**
 * ZM系统参数定义
 * 
 * @see SystemConfig
 */
@Service("MySystemConfigService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MySystemConfigService extends AConfigSupport implements ISConfig {

	private final static String TYPE = "system.config";

	@Override
	public String loadType() {
		return TYPE;
	}

	// 系统名称
	private String systemName;
	// 微信网页访问地址
	private String wxServerUrl;
	// 网页访问地址
	private String pcServerUrl;
	// 开放平台
	private String apiServerUrl;

	///////////////////////////////////////////////////////////

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getWxServerUrl() {
		return wxServerUrl;
	}

	public void setWxServerUrl(String wxServerUrl) {
		this.wxServerUrl = wxServerUrl;
	}

	public String getPcServerUrl() {
		return pcServerUrl;
	}

	public void setPcServerUrl(String pcServerUrl) {
		this.pcServerUrl = pcServerUrl;
	}

	public String getApiServerUrl() {
		return apiServerUrl;
	}

	public void setApiServerUrl(String apiServerUrl) {
		this.apiServerUrl = apiServerUrl;
	}

}
