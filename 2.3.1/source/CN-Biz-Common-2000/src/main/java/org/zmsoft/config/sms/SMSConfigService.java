package org.zmsoft.config.sms;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;

/**
 * 阿里支付配置
 */
@Service("SMSConfigService")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SMSConfigService extends AConfigSupport {

	private final static String TYPE = "sms.config";

	@Override
	public String loadType() {
		return TYPE;
	}

	/**
	 * 实现Bean业务名称
	 */
	private String support;// 实体Bean

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	/**
	 * 处理类别（1:队列/2:接口）
	 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
