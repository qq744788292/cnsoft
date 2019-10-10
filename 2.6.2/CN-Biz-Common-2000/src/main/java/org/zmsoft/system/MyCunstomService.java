package org.zmsoft.system;

import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.common.ServiceInstanceDBO;
import org.zmsoft.framework.constants.ICCacheConstants;
import org.zmsoft.framework.support.MyFrameWorkSupport;
import org.zmsoft.framework.utils.EmptyHelper;
import org.zmsoft.framework.utils.HttpServiceHelper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 服务器寻址
 */
@Component("MyCunstomService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyCunstomService extends MyFrameWorkSupport implements ICCacheConstants {
	
	private String serviceHead = HTTP_URI;

	public String getServiceHead() {
		return serviceHead;
	}

	/**
	 * 使用Https模式
	 */
	public void setServiceHeadByHttps() {
		this.serviceHead = HTTPS_URI;
	}

	public ServiceInstanceDBO loadServer(String name) throws Exception {
		String o = (String) myCacheService.getObject(CACHE_KEY_SERVER + name);
		if (EmptyHelper.isNotEmpty(o))
			return JSON.parseObject(o, ServiceInstanceDBO.class);
		return null;
	}
	
	/**
	 * 请求远程接口
	 * @param name
	 * @param api
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String doServerApiPOST(String name, String api, JSONObject param) throws Exception {
		ServiceInstanceDBO server = loadServer(name);
		return HttpServiceHelper.doHttpPOST(serviceHead + server.getHost() + COLON + server.getPort() + api, param);
	}
	
	/**
	 * 请求远程接口
	 * @param name
	 * @param api
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String doServerApiPOST(String name, String api, Map<String, String> param) throws Exception {
		ServiceInstanceDBO server = loadServer(name);
		return HttpServiceHelper.doHttpPOST(serviceHead + server.getHost() + COLON + server.getPort() + api, param);
	}
	
	/**
	 * 请求远程接口
	 * @param name
	 * @param api
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public String doServerApiGET(String name, String api) throws Exception {
		ServiceInstanceDBO server = loadServer(name);
		return HttpServiceHelper.doHttpGET(serviceHead + server.getHost() + ":" + server.getPort() + api);
	}
}