package org.zmsoft.jfp.persistent.common.SystemConfig;

import java.util.List;

import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 系统配置 */
@Service("SystemConfigService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemConfigService extends MyDataBaseOperateSupport2<SystemConfigDBO> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public SystemConfigDao getDao() {
		return getMySqlSession().getMapper(SystemConfigDao.class);
	}

	/**
	 * 一览查询
	 * 
	 * @see doSelectData
	 */
	public List<SystemConfigDBO> doSelectData(SystemConfigDBO _SystemDBO_) {
		return getDao().doSelectData(_SystemDBO_);
	}

	/**
	 *数据保存
	 * 
	 * @param _SystemDBO_
	 */
	public void saveSystem(SystemConfigDBO _SystemDBO_) {
		getDao().saveSystem(_SystemDBO_);
	}

}
