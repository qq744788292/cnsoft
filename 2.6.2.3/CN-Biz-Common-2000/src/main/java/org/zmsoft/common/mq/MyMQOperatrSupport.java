package org.zmsoft.common.mq;

import org.springframework.stereotype.Component;
import org.zmsoft.framework.cache.ISMQService;
import org.zmsoft.framework.support.MyTokenCommonSupport;

/**
 * 队列操作
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("MyMQOperatrSupport")
public class MyMQOperatrSupport extends MyTokenCommonSupport implements ISMQService {

	public boolean pushValue(String key, String value) {
		return myCacheService.addObjectInList(key, value, false);
	}

	public String popValue(String key) {
		return (String) myCacheService.pollFirstObjectInList(key);
	}
	
}
