package org.cnsoft.persistent.common.SystemDictionary;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.cnsoft.framework.db.ISDatabaseSupport;

/** 数据字典 */
@Mapper
public interface SystemDictionaryMapper extends ISDatabaseSupport<SystemDictionaryDBO> {

	/**
	 * 数据查询
	 * 
	 * @param SystemDictionaryDBO
	 * @return
	 */
	public List<SystemDictionaryDBO> doSelectData(SystemDictionaryDBO param);

}
