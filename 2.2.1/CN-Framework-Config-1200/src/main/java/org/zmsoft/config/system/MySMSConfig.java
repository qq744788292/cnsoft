package org.zmsoft.config.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.support.MyTokenCommonSupport;


@Service("MySMSConfig")
public class MySMSConfig extends MyTokenCommonSupport {

	/**
	 * 实现Bean业务名称
	 */
	@Value("${sms.support}")
	private String support;// 实体Bean
	
	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	/**
	 *  处理类别（1:队列/2:接口）
	 */
	@Value("${sms.type}")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

}
