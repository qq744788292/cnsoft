package com.zmsoft.persistent.framework.common.SystemParameter;

import java.util.List;

import org.zmsoft.framework.db.ISDatabaseSupport;

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
