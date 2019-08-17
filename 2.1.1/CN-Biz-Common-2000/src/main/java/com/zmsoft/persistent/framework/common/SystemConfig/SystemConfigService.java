package com.zmsoft.persistent.framework.common.SystemConfig;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统配置 */
@Service("SystemConfigService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemConfigService extends MyDataBaseOperateSupport2<SystemConfigDBO> {
	
	@Resource
	private SystemConfigDao daoSystemConfig;

	public SystemConfigDao getMapper() {
		return daoSystemConfig;
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
