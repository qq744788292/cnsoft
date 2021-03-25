package org.cnsoft.persistent.common.SystemParameter;

import javax.annotation.Resource;

import org.cnsoft.framework.db.support.ext.MyDataBaseOperateSupport2;
import org.springframework.stereotype.Repository;

/** 参数定义 */
@Repository("SystemParameterDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemParameterDao extends MyDataBaseOperateSupport2<SystemParameterDBO> {
	
	@Resource
	private SystemParameterMapper mapperSystemParameter;

	public SystemParameterMapper getMapper() {
		return mapperSystemParameter;
	}

}
