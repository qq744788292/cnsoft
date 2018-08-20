package org.zmsoft.jfp.framework.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.beans.ObjectBean;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;

import com.alibaba.fastjson.JSONObject;

/**
 * API请求通信 </br>
 * User-Agent: Apache-HttpClient/4.5 (Java/1.7.0_51) </br>
 * 不支持代理
 *
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 * @see <MyHttpServiceSupport>
 */
public class HttpServiceHelper implements IFrameworkConstants{
	private static Map<String, String> currentHeaders = new HashMap<String, String>();

	public HttpServiceHelper() {
		currentHeaders.put("Accept", "text/plain, */*; q=0.01");
		currentHeaders.put("Accept-Language", "Accept-Language:zh-CN,zh;q=0.8");
		currentHeaders.put("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 QQBrowser/9.3.6874.400");
		currentHeaders.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
	}

	private static Logger logger = LoggerFactory.getLogger(HttpServiceHelper.class);
	public static int waitTimeMinute = 15;

	public static int getWaitTimeMinute() {
		return waitTimeMinute;
	}

	public static void setWaitTimeMinute(int waitTimeMinute) {
		HttpServiceHelper.waitTimeMinute = waitTimeMinute;
	}

	/**
	 * 获取client对象
	 * 
	 * @return
	 */
	protected static CloseableHttpClient getCloseableHttpClient(String serviceURL) throws Exception {
		return getCloseableHttpClient(serviceURL, null);
	}

	/**
	 * 获取client对象
	 * 
	 * @return
	 * @throws Exception
	 */
	protected static CloseableHttpClient getCloseableHttpClient(String serviceURL, HttpHost httpProxy)
			throws Exception {
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
		requestConfigBuilder.setSocketTimeout(waitTimeMinute * 1000);
		requestConfigBuilder.setConnectTimeout(waitTimeMinute * 1000);
		requestConfigBuilder.setConnectionRequestTimeout(waitTimeMinute * 1000);
		// TODO PoolingHttpClientConnectionManager
		// requestConfigBuilder.setStaleConnectionCheckEnabled(true);
		// 代理httpProxy
		if (httpProxy != null) {
			requestConfigBuilder.setProxy(httpProxy);
		}

		httpClientBuilder.setDefaultRequestConfig(requestConfigBuilder.build());
		if (serviceURL.indexOf("https") != -1) {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有证书
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

			httpClientBuilder.setSSLSocketFactory(sslsf);
		}
		return httpClientBuilder.build();
	}

	/**
	 * JOSN字符串形式发送参数名的常量定义
	 */
	public static final String POST_PARAM = "jsonData";

	public static String doHttpGET(String serviceURL) throws Exception {
		return doHttpGET(serviceURL, currentHeaders);
	}

	public static String doHttpGET(String serviceURL, Map<String, String> headers) throws Exception {
		return doHttpGET(serviceURL, headers, null);
	}

	public static String doHttpGET(String serviceURL, Map<String, String> headers, Map<String, String> cookies)
			throws Exception {
		logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		CloseableHttpClient httpclient = getCloseableHttpClient(serviceURL);
		try {
			HttpGet httpGet = new HttpGet(serviceURL);
			// 设定传输编码
			// 设定请求头
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpGet.setHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设定传输编码
			CloseableHttpResponse response;

			// 设置cookie
			if (cookies != null && cookies.size() > 0) {
				// 创建上下文环境
				HttpContext context = new BasicHttpContext();
				CookieStore cookieStore = new BasicCookieStore();
				for (Map.Entry<String, String> entry : cookies.entrySet()) {
					cookieStore.addCookie(new BasicClientCookie2(entry.getKey(), entry.getValue()));
				}
				context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
				response = httpclient.execute(httpGet, context);
			} else {
				response = httpclient.execute(httpGet);
			}

			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, SYSTEM_CHARSET);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
		} finally {
			httpclient.close();
		}
		return EMPTY;
	}
	public static String doHttpPOST(String serviceURL, JSONObject json) throws Exception {
        try {
            StringEntity se = new StringEntity(json.toString());
            se.setContentEncoding("UTF-8");
            se.setContentType("application/json");//发送json数据需要设置contentType
            CloseableHttpClient httpclient = getCloseableHttpClient(serviceURL);
    		HttpPost httpPost = new HttpPost(serviceURL);
    		 httpPost.setEntity(se);
            CloseableHttpResponse response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity he = response.getEntity();
				if (he != null)
					return EntityUtils.toString(he, SYSTEM_CHARSET);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
		return EMPTY;
	}
	
	/**
	 * 将全部参数以JSON字符串参数形式发送，接口如直接接受
	 * 
	 * @see #POST_PARAM
	 * @param serviceURL
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public static String doHttpPOST(String serviceURL, String jsonString) throws Exception {
		return doHttpPOST(null, serviceURL, jsonString, true);
	}

	public static String doHttpPOST(String serviceURL, String data, boolean josn) throws Exception {
		return doHttpPOST(null, serviceURL, data, true);
	}

	public static String doHttpPOST(CloseableHttpClient httpclient, String serviceURL, String data, boolean josn)
			throws Exception {
		logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		if (httpclient == null)
			httpclient = getCloseableHttpClient(serviceURL);

		try {
			HttpPost httpPost = new HttpPost(serviceURL);

			// 参数翻转
			if (josn == true) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair(POST_PARAM, data));// 设定传输编码
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, SYSTEM_CHARSET));
			} else {// 设定传输编码
				httpPost.setEntity(new StringEntity(data, SYSTEM_CHARSET));
			}

			CloseableHttpResponse response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, SYSTEM_CHARSET);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
		} finally {
			httpclient.close();
		}
		return EMPTY;
	}

	/**
	 * 以简单属性参数请求提交服务
	 * 
	 * @param serviceURL
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static String doHttpPOST(String serviceURL, ObjectBean param) throws Exception {
		// CloseableHttpClient httpclient = HttpClients.createDefault();

		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(waitTimeMinute * 1000)
				.setConnectTimeout(waitTimeMinute * 1000).setConnectionRequestTimeout(waitTimeMinute * 1000)
				.setStaleConnectionCheckEnabled(true).build();
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

		try {

			HttpPost httpPost = new HttpPost(serviceURL);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			// 参数翻转
			{
				// 获得所有属性名称
				for (Method m : param.getClass().getMethods()) {
					String methodName = m.getName();
					if (methodName.startsWith("get")) {// &&
														// methodName.indexOf("_")
														// > 0
						try {
							Object value = m.invoke(param);
							String name = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);

							if (value == null)
								nvps.add(new BasicNameValuePair(name, ""));
							else
								nvps.add(new BasicNameValuePair(name, "" + value));
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
					}
				}
			}

			// 设定传输编码
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, SYSTEM_CHARSET));

			CloseableHttpResponse response = httpclient.execute(httpPost);

			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, SYSTEM_CHARSET);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
		} finally {
			httpclient.close();
		}
		return "";
	}

	/**
	 * 将全部参数以JSON字符串形式发送，接口如直接接受
	 * 
	 * @see #POST_PARAM
	 * @param serviceid
	 * @param jsonString
	 * @param param
	 *            额外扩展参数定义
	 * @return
	 * @throws Exception
	 */
	public static String doHttpPOST(String serviceURL, String jsonString, Map<String, String> param) throws Exception {
		param.put(POST_PARAM, jsonString);
		return doHttpPOST(serviceURL, param);
	}

	public static String doHttpPOST(String serviceURL, Map<String, String> param) throws Exception {
		return doHttpPOST(serviceURL, param, currentHeaders);
	}

	public static String doHttpPOST(String serviceURL, Map<String, String> param, Map<String, String> headers)
			throws Exception {
		return doHttpPOST(serviceURL, param, headers, null);
	}

	public static String doHttpPOST(String serviceURL, Map<String, String> param, Map<String, String> headers,
			Map<String, String> cookies) throws Exception {
		logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		CloseableHttpClient httpclient = getCloseableHttpClient(serviceURL);

		try {
			HttpPost httpPost = new HttpPost(serviceURL);

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			// 额外参数
			if (param != null) {
				for (Map.Entry<String, String> entry : param.entrySet()) {
					nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}

			// 设定传输编码
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, SYSTEM_CHARSET));

			// 设定请求头
			if (headers != null) {
				for (Map.Entry<String, String> entry : headers.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}

			CloseableHttpResponse response;

			// 设置cookie
			if (cookies != null && cookies.size() > 0) {
				// 创建上下文环境
				HttpContext context = new BasicHttpContext();
				CookieStore cookieStore = new BasicCookieStore();
				for (Map.Entry<String, String> entry : param.entrySet()) {
					cookieStore.addCookie(new BasicClientCookie2(entry.getKey(), entry.getValue()));
				}
				context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
				response = httpclient.execute(httpPost, context);
			} else {
				response = httpclient.execute(httpPost);
			}
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity, SYSTEM_CHARSET);
			} else {
				throw new Exception("服务请求异常: " + status + ",【URL=" + serviceURL + "】");
			}
		} finally {
			httpclient.close();
		}
		return "";
	}

	/**
	 * 使用数据流的方式提交请求
	 * 
	 * @param serviceURL
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String doHttpPOST(String serviceURL, StringBuffer param) throws Exception {
		logger.debug("=====>>>>>接口请求<<<<<=====" + serviceURL);
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpsURLConnection conn = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(serviceURL);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod("POST");
			conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			// 当outputStr不为null时向输出流写数据
			if (null != param) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(param.toString().getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			inputStream = conn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			return buffer.toString();
		} finally {
			// 释放资源
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
			if (inputStream != null) {
				inputStream.close();
				inputStream = null;
			}

			if (conn != null) {
				conn.disconnect();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println(doHttpGET("http://www.baidu.com"));
	}
}
