package com.zmsoft.persistent.framework.common.SystemParameter;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 参数定义 */
@Service("SystemParameterService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemParameterService extends MyDataBaseOperateSupport2<SystemParameterDBO> {
	
	@Resource
	private SystemParameterDao daoSystemParameter;

	public SystemParameterDao getMapper() {
		return daoSystemParameter;
	}

}
