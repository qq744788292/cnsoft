package org.zmsoft.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.zmsoft.common.cache.SystemConfigCacheService;
import org.zmsoft.common.cache.SystemParameterCacheService;
import org.zmsoft.framework.KeyValueBean;
import org.zmsoft.framework.support.MyServiceSupport;
import org.zmsoft.persistent.common.SystemConfig.SystemConfigDBO;
import org.zmsoft.persistent.common.SystemParameter.SystemParameterDBO;

/**
 * 系统参数加载
 * 
 * @author Zmsoft
 * @version 0.1.0 2018/4/2
 * @since 0.1.0 2018/4/2
 */
@Component("MySystemConfigParameterSupport")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MySystemConfigParameterSupport extends MyServiceSupport {

	@Resource
	private SystemConfigCacheService mySystemConfigCache;
	@Resource
	private SystemParameterCacheService mySystemParameterCache;
	
	boolean reload = false;

	public void setReload(boolean reload) {
		this.reload = reload;
	}

	public KeyValueBean loadCacheData(int classify, String type, String key) throws Exception {
		KeyValueBean kvb = new KeyValueBean();
		if (classify == 1) {
			SystemConfigDBO config = mySystemConfigCache.loadConfig(reload, key);
			kvb.setKey(config.getKey());
			kvb.setValue(config.getValue());
		} else if (classify == 2) {
			SystemParameterDBO parameter = mySystemParameterCache.loadParameter(reload, type, key);
			kvb.setKey(parameter.getKey());
			kvb.setValue(parameter.getValue());
		}

		return kvb;
	}

	public Map<String, String> loadCacheDatas(int classify, String type) throws Exception {
		Map<String, String> map = new HashMap<>();
		try {
			if (classify == 1) {
				for (SystemConfigDBO item : mySystemConfigCache.loadConfigs(reload)) {
					map.put(item.getKey(), item.getValue());
				}
			} else if (classify == 2) {
				for (SystemParameterDBO item : mySystemParameterCache.loadParameters(reload, type)) {
					map.put(item.getKey(), item.getValue());
				}
			}
		} catch (Exception e) {
		}

		return map;
	}

}
