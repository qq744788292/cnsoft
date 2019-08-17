package org.zmsoft.config.system;

import java.util.Map.Entry;

import org.zmsoft.framework.common.ISConfig;
import org.zmsoft.framework.support.MyBeanFactoryHelper;

/**
 * 配置信息同步
 */
public class MyConfigHelper {

	public static void prepareSystemConfig(boolean reload) throws Exception {
		for (Entry<String, ISConfig> item : MyBeanFactoryHelper.getBeans(ISConfig.class).entrySet()) {
			ISConfig config = item.getValue();
			config.setReload(reload);
			config.init();
		}
	}
}