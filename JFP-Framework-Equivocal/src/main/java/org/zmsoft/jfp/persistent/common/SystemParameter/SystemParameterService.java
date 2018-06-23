package org.zmsoft.jfp.persistent.common.SystemParameter;

import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 参数定义 */
@Service("SystemParameterService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemParameterService extends MyDataBaseOperateSupport2<SystemParameterDBO> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public SystemParameterDao getDao() {
		return getMySqlSession().getMapper(SystemParameterDao.class);
	}

}
