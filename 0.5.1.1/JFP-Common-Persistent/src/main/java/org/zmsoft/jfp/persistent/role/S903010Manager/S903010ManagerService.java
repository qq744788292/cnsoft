package org.zmsoft.jfp.persistent.role.S903010Manager;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;

/** 系统管理用户表*/
@Service("S903010ManagerService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S903010ManagerService extends MyDataBaseOperateSupport2<S903010ManagerDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S903010ManagerDao getDao(){
        return getMySqlSession().getMapper(S903010ManagerDao.class);
    }

}
