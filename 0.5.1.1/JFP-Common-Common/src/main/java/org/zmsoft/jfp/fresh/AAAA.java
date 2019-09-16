package org.zmsoft.jfp.fresh;

import java.util.Random;
import org.apache.http.impl.client.HttpClientBuilder;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.PKHelper;

public class AAAA implements IFrameworkConstants {
	public static void main(String[] args) throws Exception {
		// 想采集的网址
		String[][] remoteURLs = new String[][] {
				{ "https://tv.2345.com/detail/54033.html?lm003292&qq-pf-to=pcqq.group", ONE }, // 目标
				{ "http://www.gm9527.com", TWO } // 测试
		};
		ProxyConfigBean proxyConfig = new ProxyConfigBean();
		
		// 开始刷量
		while (true) 
		{
			WebClientFreshThread wcft = new WebClientFreshThread();
			wcft.setKey(PKHelper.creatPUKey());
			wcft.setRemoteURLs(remoteURLs);
			wcft.setProxyConfig(proxyConfig);
			new Thread(wcft).start();
			Random r = new Random();
			Thread.sleep((r.nextInt(5)) * 1000);
		}
	}
}
