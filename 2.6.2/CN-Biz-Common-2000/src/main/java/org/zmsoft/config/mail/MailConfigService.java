package org.zmsoft.config.mail;

import org.springframework.stereotype.Service;
import org.zmsoft.config.AConfigSupport;
import org.zmsoft.framework.common.ISConfig;

/**
 * 阿里支付配置
 */
@Service("MailConfigService")
public class MailConfigService extends AConfigSupport implements ISConfig {

	private final static String TYPE = "mail.config";

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

	private String username;

	private String password;

	private String host;

	private String port;

	private String defaultEncoding;

	private String auth;

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
