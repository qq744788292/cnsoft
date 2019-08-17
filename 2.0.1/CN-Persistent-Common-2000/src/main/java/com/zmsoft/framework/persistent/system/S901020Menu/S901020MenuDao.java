package com.zmsoft.framework.persistent.system.S901020Menu;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 功能菜单*/
@Repository("S901020MenuDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901020MenuDao extends MyDataBaseOperateSupport2<S901020MenuDBO> {
    @Resource
    private S901020MenuMapper mapperS901020Menu;

    public S901020MenuMapper getMapper(){
        return mapperS901020Menu;
    }

}
