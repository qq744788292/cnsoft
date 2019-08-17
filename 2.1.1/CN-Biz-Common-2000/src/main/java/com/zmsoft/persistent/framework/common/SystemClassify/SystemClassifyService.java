package com.zmsoft.persistent.framework.common.SystemClassify;
import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 分类信息*/
@Service("SystemClassifyService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemClassifyService extends MyDataBaseOperateSupport2<SystemClassifyDBO> {

	@Resource
	private SystemClassifyDao daoSystemClassify;

	public SystemClassifyDao getMapper() {
		return daoSystemClassify;
	}
}
