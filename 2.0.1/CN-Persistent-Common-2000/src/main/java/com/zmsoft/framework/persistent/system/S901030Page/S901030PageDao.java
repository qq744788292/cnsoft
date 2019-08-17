package com.zmsoft.framework.persistent.system.S901030Page;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 业务画面*/
@Repository("S901030PageDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901030PageDao extends MyDataBaseOperateSupport2<S901030PageDBO> {
    @Resource
    private S901030PageMapper mapperS901030Page;

    public S901030PageMapper getMapper(){
        return mapperS901030Page;
    }

}
