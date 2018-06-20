package org.zmsoft.jfp.framework.cache.mq;

import org.zmsoft.jfp.framework.cache.ISCacheService;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;

/**
 * 获得MQ
 * @author zmsoft
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
public class MQHelper {
	
	public static final String MQ_SERVICE_NAME = "myMqService";

	public static ISCacheService getMqService() {
		return BeanFactoryHelper.getBean(MQ_SERVICE_NAME);
	}

}
