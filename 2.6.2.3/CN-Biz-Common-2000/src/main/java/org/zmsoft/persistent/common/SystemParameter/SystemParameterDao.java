package org.zmsoft.persistent.common.SystemParameter;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

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
