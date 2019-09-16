package org.zmsoft.jfp.fresh;

public class ProxyConfigBean {
	// 代理隧道验证信息
	String proxyUser = "H89452A55S112L1P";
	String proxyPass = "02007CB6FC6C53AE";
	// 代理服务器
	String proxyServer = "http-pro.abuyun.com";
	int proxyPort = 9010;

	public String getProxyUser() {
		return proxyUser;
	}

	public void setProxyUser(String proxyUser) {
		this.proxyUser = proxyUser;
	}

	public String getProxyPass() {
		return proxyPass;
	}

	public void setProxyPass(String proxyPass) {
		this.proxyPass = proxyPass;
	}

	public String getProxyServer() {
		return proxyServer;
	}

	public void setProxyServer(String proxyServer) {
		this.proxyServer = proxyServer;
	}

	public int getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

}
