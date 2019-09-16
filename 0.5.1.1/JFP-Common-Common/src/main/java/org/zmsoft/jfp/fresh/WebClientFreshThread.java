package org.zmsoft.jfp.fresh;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;

import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.utils.EmptyHelper;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.DefaultCredentialsProvider;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class WebClientFreshThread implements Runnable, IFrameworkConstants {

	protected String myKey;
	protected String[][] myRemoteURLs;
	protected ProxyConfigBean myProxyConfig;
	protected TotalNumThread myTotalNumThread;

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) throws Exception {
		WebClientFreshThread wcft = new WebClientFreshThread();
		wcft.setProxyConfig(new ProxyConfigBean());
		wcft.doFreshWeb("https://tv.2345.com/detail/54033.html?lm003292", ONE);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void run() {
		if (EmptyHelper.isNotEmpty(myRemoteURLs))
			for (String[] param : myRemoteURLs)
				doFreshWeb(param[0], param[1]);
	}

	public void doFreshWeb(String remoteURL, String count) {
		// 初始化
		String refer = "";
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		try {
			// 参数设置
			{
				webClient.getCookieManager().setCookiesEnabled(true);// 开启cookie管理
				webClient.setAjaxController(new NicelyResynchronizingAjaxController());
				webClient.getCookieManager().setCookiesEnabled(true);// 开启cookie管理
				
				webClient.getOptions().setJavaScriptEnabled(true);// 开启js解析。对于变态网页，这个是必须的
				webClient.getOptions().setCssEnabled(true);// 开启css解析。对于变态网页，这个是必须的。
				webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
				webClient.getOptions().setThrowExceptionOnScriptError(false);
				webClient.getOptions().setRedirectEnabled(true);
				webClient.getOptions().setTimeout(30000);
				webClient.getOptions().setUseInsecureSSL(true);

				webClient.waitForBackgroundJavaScript(10000);
			}
			// 设置代理
			if (myProxyConfig != null) {
				// 代理认证设置
				DefaultCredentialsProvider creds = new DefaultCredentialsProvider();
				creds.addCredentials(myProxyConfig.getProxyUser(), myProxyConfig.getProxyPass());
				webClient.setCredentialsProvider(creds);
				ProxyConfig webProxyConfig = webClient.getOptions().getProxyConfig();
				webProxyConfig.setProxyHost(myProxyConfig.getProxyServer());
				webProxyConfig.setProxyPort(myProxyConfig.getProxyPort());
			}

			URL link = new URL(remoteURL);
			WebRequest request = new WebRequest(link);
			// 设置请求报文头
			{
				request.setCharset(("UTF-8"));//Charset.forName
				request.setAdditionalHeader("Referer", refer);
				request.setAdditionalHeader("Proxy-Switch-Ip", "yes");
				request.setAdditionalHeader("Accept-Encoding", "gzip, deflate, sdch");
				request.setAdditionalHeader("Upgrade-Insecure-Requests", "1");
				request.setAdditionalHeader("Accept-Language", "Accept-Language:zh-CN,zh;q=0.8");
				request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.2372.400 QQBrowser/9.5.10548.400");
			}
			System.out.println("===remoteURL===" + remoteURL + "======");
			// 页面请求
			Page page = webClient.getPage(request);
			if (ONE.equals(count))
				if (page.isHtmlPage()) {// 准确性判断
					if (myTotalNumThread != null)
						myTotalNumThread.setNumSuccess();
					System.out.println(((HtmlPage) page).getUrl());
					System.out.println(((HtmlPage) page).asText());
				} else {
					if (myTotalNumThread != null)
						myTotalNumThread.setNumFail();
					WebResponse response = page.getWebResponse();
					System.out.println(response.getStatusCode() + "," + response.getStatusMessage());
				}
			Random r = new Random();
			Thread.sleep((5 + r.nextInt(15)) * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			webClient.close();
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	public String getKey() {
		return myKey;
	}

	public void setKey(String key) {
		this.myKey = key;
	}

	public String[][] getRemoteURLs() {
		return myRemoteURLs;
	}

	public void setRemoteURLs(String[][] remoteURLs) {
		this.myRemoteURLs = remoteURLs;
	}

	public ProxyConfigBean getProxyConfig() {
		return myProxyConfig;
	}

	public void setProxyConfig(ProxyConfigBean proxyConfig) {
		this.myProxyConfig = proxyConfig;
	}

	public TotalNumThread getTotalNumThread() {
		return myTotalNumThread;
	}

	public void setTotalNumThread(TotalNumThread totalNumThread) {
		this.myTotalNumThread = totalNumThread;
	}

}
