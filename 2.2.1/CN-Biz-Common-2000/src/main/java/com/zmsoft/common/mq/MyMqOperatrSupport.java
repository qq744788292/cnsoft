package com.zmsoft.common.mq;

import java.util.List;

import org.springframework.stereotype.Component;
import org.zmsoft.framework.cache.ISMQService;
import org.zmsoft.framework.support.MyTokenCommonSupport;

import com.alibaba.fastjson.JSON;

/**
 * 消息队列
 * 
 * @author ZmSoft
 * @version 2.0.0 2018/10/10
 * @since 2.0.0 2018/10/10
 */
@Component("MyMqOperatrSupport")
public class MyMqOperatrSupport extends MyTokenCommonSupport implements ISMQService {

	@Override
	public boolean addListAll(String key, List<String> value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addObjectInList(String key, Object value) {
		return myCacheService.addObjectInList(key, JSON.toJSONString(value), false);
	}

	@Override
	public boolean addObjectInList(String key, Object value, boolean translation) {
		return myCacheService.addObjectInList(key, JSON.toJSONString(value), false);
	}

	@Override
	public Object pollFirstObjectInList(String key) {
		// TODO Auto-generated method stub
		return myCacheService.pollFirstObjectInList(key);
	}

	@Override
	public Object pollFirstObjectInList(String key, boolean translation) {
		// TODO Auto-generated method stub
		return myCacheService.pollFirstObjectInList(key, translation);
	}

	
}
