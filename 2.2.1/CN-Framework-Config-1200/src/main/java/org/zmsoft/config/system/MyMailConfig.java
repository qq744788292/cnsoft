package org.zmsoft.config.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.support.MyTokenCommonSupport;


@Service("MyMailConfig")
public class MyMailConfig extends MyTokenCommonSupport {

	/**
	 * 实现Bean业务名称
	 */
	@Value("${mail.support}")
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
	@Value("${mail.type}")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Value("${mail.username}")
	private String username;
	
	@Value("${mail.password}")
	private String password;
	
	@Value("${mail.host}")
	private String host;
	
	@Value("${mail.port}")
	private String port;
	
	@Value("${mail.defaultEncoding}")
	private String defaultEncoding;
	
	@Value("${mail.auth}")
	private String auth;
	
	@Value("${mail.timeout}")
	private String timeout;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDefaultEncoding() {
		return defaultEncoding;
	}

	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	
}
