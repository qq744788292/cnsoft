package org.cnsoft.persistent.common.SystemDictionary;

import javax.annotation.Resource;

import org.cnsoft.framework.db.support.ext.MyDataBaseOperateSupport2;
import org.springframework.stereotype.Repository;

/** 数据字典 */
@Repository("SystemDictionaryDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemDictionaryDao extends MyDataBaseOperateSupport2<SystemDictionaryDBO> {
	
	@Resource
	private SystemDictionaryMapper mapperSystemParameter;

	public SystemDictionaryMapper getMapper() {
		return mapperSystemParameter;
	}

}
