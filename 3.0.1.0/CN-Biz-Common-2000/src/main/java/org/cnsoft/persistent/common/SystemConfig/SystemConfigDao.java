	package org.cnsoft.persistent.common.SystemConfig;

import java.util.List;

import javax.annotation.Resource;

import org.cnsoft.framework.db.support.ext.MyDataBaseOperateSupport2;
import org.springframework.stereotype.Repository;

/** 系统配置 */
@Repository("SystemConfigDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemConfigDao extends MyDataBaseOperateSupport2<SystemConfigDBO> {
	
	@Resource
	private SystemConfigMapper mapperSystemConfig;

	public SystemConfigMapper getMapper() {
		return mapperSystemConfig;
	}

	/**
	 * 一览查询
	 * 
	 * @see doSelectData
	 */
	public List<SystemConfigDBO> doSelectData(SystemConfigDBO _SystemDBO_) {
		return getMapper().doSelectData(_SystemDBO_);
	}

	/**
	 * 数据保存
	 * 
	 * @param _SystemDBO_
	 */
	public void saveSystem(SystemConfigDBO _SystemDBO_) {
		getMapper().saveSystem(_SystemDBO_);
	}

}
