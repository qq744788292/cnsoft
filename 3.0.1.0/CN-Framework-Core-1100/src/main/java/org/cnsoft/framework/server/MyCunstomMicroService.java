package org.cnsoft.framework.server;

import java.util.Map;

import org.cnsoft.framework.beans.common.ServiceInstanceDBO;
import org.cnsoft.framework.constants.ICCacheConstants;
import org.cnsoft.framework.json.JSONObject;
import org.cnsoft.framework.support.MyServiceSupport;
import org.cnsoft.framework.utils.EmptyHelper;
import org.cnsoft.framework.utils.HttpServiceHelper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 微服务访问
 * 
 * @author CNSoft
 * @version 2.6.2.5 2018/10/22
 * @since 2.6.2.5 2018/10/22
 * @see <MyCunstomService><MyHeardTaskService><CustomerServerMoitorController>
 */
@Component("MyCunstomService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyCunstomMicroService extends MyServiceSupport implements ICCacheConstants {
	
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
		String o = myCacheService.getObject(CACHE_KEY_SERVER + name);
		if (EmptyHelper.isNotEmpty(o))
			return JSONObject.parseObject(o, ServiceInstanceDBO.class);
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