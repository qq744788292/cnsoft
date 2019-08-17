package com.zmsoft.framework.persistent.system.S901040PageFunc;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 页面按钮*/
@Repository("S901040PageFuncDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901040PageFuncDao extends MyDataBaseOperateSupport2<S901040PageFuncDBO> {
    @Resource
    private S901040PageFuncMapper mapperS901040PageFunc;

    public S901040PageFuncMapper getMapper(){
        return mapperS901040PageFunc;
    }

}
