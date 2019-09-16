package org.zmsoft.jfp.persistent.common.ConfigData;

import java.util.List;

import org.zmsoft.jfp.framework.support.ISDatabaseSupport;

/** 参数配置 */
public interface ConfigDataDao extends ISDatabaseSupport<ConfigDataDBO> {

	/**
	 * 数据查询
	 * 
	 * @param ConfigDataDBO
	 * @return
	 */
	public List<ConfigDataDBO> doSelectData(ConfigDataDBO param);

}
