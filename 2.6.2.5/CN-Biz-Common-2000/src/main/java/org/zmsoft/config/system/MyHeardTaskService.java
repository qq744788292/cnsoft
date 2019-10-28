
package org.zmsoft.config.system;

import java.net.InetAddress;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zmsoft.common.mq.MyMQOperatrSupport;
import org.zmsoft.framework.beans.common.ServiceInstanceDBO;
import org.zmsoft.framework.cache.redis.master.MyCacheService;
import org.zmsoft.framework.constants.ICCacheConstants;
import org.zmsoft.framework.task.AJobSupport;

/**
 * 服务器心跳
 * 
 * @author ZmSoft
 * @version 2.6.2.5 2018/10/22
 * @since 2.6.2.5 2018/10/22
 * @see <MyCunstomServer><MyHeardTaskService><CustomerServerMoitorController>
 */
@Component("MyHeardTaskService")
public class MyHeardTaskService extends AJobSupport implements ICCacheConstants {

	// 设置启动模式-每分钟启动
	@Scheduled(cron = "0/15 * * * * ?")
	public void doQuartzService() throws Exception {
		// 激活父类启动方法
		doJobBizProcess();
	}

	public String getLocalHost() {
		try {
			InetAddress address = InetAddress.getLocalHost();// 获取的是本地的IP地址
			return address.getHostAddress();
		} catch (Exception e) {
			return "127.0.0.1";
		}
	}

	// 服务名称
	@Value("${spring.application.name}")
	private String name;
	// 服务端口
	@Value("${server.port}")
	private String port;
	// 服务器ID
	@Value("${model.id}")
	protected String myModelId;
	// 缓存中心
	@Resource
	protected MyCacheService MyServerCache;

	/**
	 * 业务逻辑
	 */
	@Override
	public boolean doProcess() throws Exception {
		// 服务器心跳

		ServiceInstanceDBO data = new ServiceInstanceDBO();
		data.setHost(getLocalHost());
		data.setPort(Integer.parseInt(port));
		data.setServiceId(myModelId);
		data.setName(name);

		logger.debug("CustomerServiceName=====>>>>>" + name);

		myMQ.set(CACHE_KEY_SERVER + name, data.toString());
		myMQ.set(CACHE_KEY_SERVER + data.getHost(), data.toString());

		return true;
	}

	@Resource
	MyMQOperatrSupport myMQ;

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