package org.jfpc.base.utils;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jfpc.base.bean.ObjectBean;


/**
 * API请求通信
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 * 
 */
public class HttpServiceUtil {
	// 127.0.0.1:8888
	String serverIp = "127.0.0.1";
	String serverPort = "8888";

	public HttpServiceUtil() {
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	// 获得服务器地址
	// "http://127.0.0.1:8888/0002/1123213/20140526"
	public URI getServerURL(String serviceid) {
		StringBuilder uri = new StringBuilder();
		// 获得访问地址进行拼接
		uri.append("http://")// 通信协议
				.append(serverIp)// 获得服务器地址和端口
				.append(":")// 分隔符
				.append(serverPort)// 获得业务访问地址
				.append("/")// 分隔符
				.append(serviceid);

		return URI.create(uri.toString());
	}

	public String doHttpGET(String serviceid) throws Exception {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet request = new HttpGet(getServerURL(serviceid));
			CloseableHttpResponse response = httpclient.execute(request);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity);
			} else {
				throw new Exception("服务请求异常: " + status);
			}
		} finally {
			httpclient.close();
		}
		return "";
	}

	/**
	 * JOSN字符串形式发送参数名的常量定义
	 */
	public static final String BST_PARAM = "BST_PARAM";

	public static final String ENCODE_DEFAULT = "UTF-8";

	/**
	 * 以简单属性参数请求提交服务
	 * 
	 * @param serviceid
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String doHttpPOST(String serviceid, ObjectBean param) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {

			HttpPost httpPost = new HttpPost(getServerURL(serviceid));
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
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, ENCODE_DEFAULT));

			CloseableHttpResponse response = httpclient.execute(httpPost);

			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity);
			} else {
				throw new Exception("服务请求异常: " + status);
			}
		} finally {
			httpclient.close();
		}
		return "";
	}

	/**
	 * 将全部参数以JSON字符串形式发送，接口如直接接受
	 * 
	 * @see #BST_PARAM
	 * @param serviceid
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public String doHttpPOST(String serviceid, String jsonString) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(getServerURL(serviceid));
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();

			// 参数翻转
			nvps.add(new BasicNameValuePair(BST_PARAM, jsonString));

			// 设定传输编码
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

			System.out.println("业务请求 " + httpPost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				if (entity != null)
					return EntityUtils.toString(entity);
			} else {
				throw new Exception("服务请求异常: " + status);
			}
		} finally {
			httpclient.close();
		}
		return "";
	}
}
