package org.zmsoft.persistent.common.SystemClassify;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

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
