package org.zmsoft.jfp.persistent.common.ConfigData;

import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 参数配置 */
@Service("ConfigDataService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConfigDataService extends MyDataBaseOperateSupport2<ConfigDataDBO> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public ConfigDataDao getDao() {
		return getMySqlSession().getMapper(ConfigDataDao.class);
	}

}
