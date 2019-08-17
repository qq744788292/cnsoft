package com.zmsoft.framework.persistent.system.S901050Classify;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 业务分类*/
@Repository("S901050ClassifyDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901050ClassifyDao extends MyDataBaseOperateSupport2<S901050ClassifyDBO> {
    @Resource
    private S901050ClassifyMapper mapperS901050Classify;

    public S901050ClassifyMapper getMapper(){
        return mapperS901050Classify;
    }

}
