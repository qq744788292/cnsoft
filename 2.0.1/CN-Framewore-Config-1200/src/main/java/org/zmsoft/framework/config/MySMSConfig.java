package org.zmsoft.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.support.MyTokenCommonSupport;


@Service("MySMSConfig")
public class MySMSConfig extends MyTokenCommonSupport {

	@Value("${sms.publicServer}")
	private String serviceURL = "http://api.china95059.net:8080/sms/send";
	@Value("${sms.publicServer}")
	private String source;// 接入号

	public String getServiceURL() {
		return serviceURL;
	}

	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
