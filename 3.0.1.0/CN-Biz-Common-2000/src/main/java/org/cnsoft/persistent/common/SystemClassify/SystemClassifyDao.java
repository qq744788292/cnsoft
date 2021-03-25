package org.cnsoft.persistent.common.SystemClassify;
import javax.annotation.Resource;

import org.cnsoft.framework.db.support.ext.MyDataBaseOperateSupport2;
import org.springframework.stereotype.Repository;

/** 分类信息*/
@Repository("SystemClassifyDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemClassifyDao extends MyDataBaseOperateSupport2<SystemClassifyDBO> {

	@Resource
	private SystemClassifyMapper mapperSystemClassify;

	public SystemClassifyMapper getMapper() {
		return mapperSystemClassify;
	}
}
