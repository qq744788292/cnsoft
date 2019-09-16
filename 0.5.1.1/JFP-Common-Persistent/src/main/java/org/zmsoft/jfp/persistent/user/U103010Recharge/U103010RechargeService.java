package org.zmsoft.jfp.persistent.user.U103010Recharge;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;

/** 订单管理*/
@Service("U103010RechargeService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class U103010RechargeService extends MyDataBaseOperateSupport2<U103010RechargeDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public U103010RechargeDao getDao(){
        return getMySqlSession().getMapper(U103010RechargeDao.class);
    }

}
