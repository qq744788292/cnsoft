package org.zmsoft.jfp.persistent.company.U201010CompanyInfo;

import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 企业基本信息 */
@Service("U201010CompanyInfoService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class U201010CompanyInfoService extends MyDataBaseOperateSupport2<U201010CompanyInfoDBO> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public U201010CompanyInfoDao getDao() {
		return getMySqlSession().getMapper(U201010CompanyInfoDao.class);
	}

}
