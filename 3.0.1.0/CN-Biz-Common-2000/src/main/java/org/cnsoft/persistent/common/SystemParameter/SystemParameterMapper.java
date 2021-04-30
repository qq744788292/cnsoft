package org.cnsoft.persistent.common.SystemParameter;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.cnsoft.framework.db.ISDatabaseSupport;

/** 参数定义 */
@Mapper
public interface SystemParameterMapper extends ISDatabaseSupport<SystemParameterDBO> {

	/**
	 * 数据查询
	 * 
	 * @param SystemDictionaryDBO
	 * @return
	 */
	public List<SystemParameterDBO> doSelectData(SystemParameterDBO param);

}
