package org.zmsoft.jfp.persistent.system.S901010System;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 系统配置*/
@Service("S901010SystemService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901010SystemService extends MyDataBaseOperateSupport2<S901010SystemDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S901010SystemDao getDao(){
        return getMySqlSession().getMapper(S901010SystemDao.class);
    }

}
