package com.zmsoft.framework.persistent.system.S901010Config;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统配置*/
@Repository("S901010ConfigDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901010ConfigDao extends MyDataBaseOperateSupport2<S901010ConfigDBO> {
    @Resource
    private S901010ConfigMapper mapperS901010Config;

    public S901010ConfigMapper getMapper(){
        return mapperS901010Config;
    }

}
