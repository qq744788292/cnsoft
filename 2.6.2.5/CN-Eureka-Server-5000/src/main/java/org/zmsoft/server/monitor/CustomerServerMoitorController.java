package org.zmsoft.server.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zmsoft.framework.beans.common.RESTResultBean;
import org.zmsoft.framework.beans.common.ServiceInstanceDBO;
import org.zmsoft.framework.constants.ECCodeMessageConstants;
import org.zmsoft.framework.constants.ICCacheConstants;
import org.zmsoft.framework.support.MyTokenCommonSupport;

/**
 * 服务监听
 * 
 * @author ZmSoft
 * @version 2.6.2.5 2018/10/22
 * @since 2.6.2.5 2018/10/22
 * @see <MyCunstomService><MyHeardTaskService><CustomerServerMoitorController>
 */
@RestController
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping(value = "/api/1.0/moitor", method = { RequestMethod.POST })
public class CustomerServerMoitorController extends MyTokenCommonSupport implements ICCacheConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	LoadBalancerClient loadBalancerClient;

	/**
	 * 服务监听
	 * @param name 服务名称ID
	 * @return
	 */
	@RequestMapping(value = "/server/online", method = RequestMethod.POST)
	public RESTResultBean<String> fileUploadApi(String name) {
		RESTResultBean<String> result = new RESTResultBean<String>();
		ServiceInstance serviceInstance = loadBalancerClient.choose(name);

		if (serviceInstance == null) {
			logger.warn(name + ",服务不存在");
			result.setResult(ECCodeMessageConstants.MESSAGE_SERVER_NONE);
			return result; // 返回result对象给前端
		}
		ServiceInstanceDBO serverData = new ServiceInstanceDBO();
		serverData.setHost(serviceInstance.getHost());
		serverData.setPort(serviceInstance.getPort());
		serverData.setServiceId(name);

		logger.debug("CustomerServiceKey=====>>>>>" + serverData);

		myCacheService.putObject(CACHE_KEY_SERVER + name, serverData.toString(), 3600, false);

		result.setData(serverData);

		return result; // 返回result对象给前端
	}

}
