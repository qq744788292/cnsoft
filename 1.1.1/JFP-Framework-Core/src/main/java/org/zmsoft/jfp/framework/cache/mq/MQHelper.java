package org.zmsoft.jfp.framework.cache.mq;

import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;

/**
 * 获得MQ
 * 
 * @author ZmSoft
 * @version 0.1.0 2018/2/8
 * @since 0.1.0 2018/2/8
 */
public class MQHelper {
	
	public static final String MQ_SERVICE_NAME = "myMqService";

	public static ISCacheService getMqService() {
		return BeanFactoryHelper.getBean(MQ_SERVICE_NAME);
	}

}
