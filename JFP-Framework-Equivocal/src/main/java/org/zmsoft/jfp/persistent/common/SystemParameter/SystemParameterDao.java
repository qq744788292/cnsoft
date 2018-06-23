package org.zmsoft.jfp.persistent.common.SystemParameter;

import java.util.List;

import org.zmsoft.jfp.framework.support.ISDatabaseSupport;

/** 参数定义 */
public interface SystemParameterDao extends ISDatabaseSupport<SystemParameterDBO> {

	/**
	 * 数据查询
	 * 
	 * @param SystemParameterDBO
	 * @return
	 */
	public List<SystemParameterDBO> doSelectData(SystemParameterDBO param);

}
