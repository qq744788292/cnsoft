package org.zmsoft.server.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zmsoft.framework.beans.common.ServiceInstanceDBO;
import org.zmsoft.framework.constants.ICCacheConstants;
import org.zmsoft.framework.task.AJobSupport;

@Component
public class CustomerServerMoitorTask extends AJobSupport implements ICCacheConstants {

	private static final String CustomerService = "XXXXX";

	@Autowired
	LoadBalancerClient loadBalancerClient;

	@Value("${model.type}")
	private String modelType;// 服务环境

	// 设置启动模式
	@Scheduled(cron = "0/5 * * * * ?")
	public void doQuartzService() throws Exception {
		// 激活父类启动方法
		doJobTaskProcess();
	}

	@Override
	public boolean doProcess() throws Exception {
		ServiceInstance serviceInstance = loadBalancerClient.choose(CustomerService);

		if (serviceInstance == null) {
			logger.error(CustomerService + ",服务不存在");
			return false;
		}
		ServiceInstanceDBO data = new ServiceInstanceDBO();
		data.setHost(serviceInstance.getHost());
		data.setPort(serviceInstance.getPort());
		data.setServiceId(CustomerService);

		String ZTCustomerServiceKey = CustomerService + modelType;

		logger.debug("CustomerServiceKey=====>>>>>" + data);

		putCacheService(CACHE_KEY_SERVER + ZTCustomerServiceKey, data.toString());

		return true;
	}

	/**
	 * 启动开始动作
	 */
	@Override
	public boolean beforeProcess() throws Exception {
		return true;
	}

	/**
	 * 启动结束动作
	 */
	@Override
	public boolean afterProcess() throws Exception {
		return true;
	}

}
