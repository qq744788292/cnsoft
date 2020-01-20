
package org.zmsoft.config;

import org.springframework.stereotype.Service;
import org.zmsoft.framework.common.ISConfig;

/**
 * ZM系统参数定义
 * 
 * @see SystemConfig
 */
@Service("MySystemConfigService")
public class MySystemConfigService extends AConfigSupport implements ISConfig {

	private final static String TYPE = "system.config";

	@Override
	public String loadType() {
		return TYPE;
	}

	/**
	 * 初始化配置
	 */
	public void init() throws Exception {
		// 初始化
		loadMySystemConfigParameterSupport();
		// 加载系统配置
		prepareSystemConfigByclass(this);
	}

	// 系统名称
	private String systemName;
	// 系统密钥
	private String systemKey;
	// 超管管理员账户
	private String superAdmin;
	// 默认分页数目
	private String defaultPageLimit;
	// 技术支持电话
	private String customPhone;
	
	// 微信网页访问地址
	private String wxServerUrl;
	// 电脑网页访问地址
	private String pcServerUrl;
	// 开放平台
	private String openServerUrl;
	// 接口平台
	private String apiServerUrl;

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}

	public String getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(String superAdmin) {
		this.superAdmin = superAdmin;
	}

	public String getDefaultPageLimit() {
		return defaultPageLimit;
	}

	public void setDefaultPageLimit(String defaultPageLimit) {
		this.defaultPageLimit = defaultPageLimit;
	}

	public String getCustomPhone() {
		return customPhone;
	}

	public void setCustomPhone(String customPhone) {
		this.customPhone = customPhone;
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

	public String getOpenServerUrl() {
		return openServerUrl;
	}

	public void setOpenServerUrl(String openServerUrl) {
		this.openServerUrl = openServerUrl;
	}

	public String getApiServerUrl() {
		return apiServerUrl;
	}

	public void setApiServerUrl(String apiServerUrl) {
		this.apiServerUrl = apiServerUrl;
	}

	/////////////////////////////////////////////////////////////////
	
}
