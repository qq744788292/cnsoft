package com.zmsoft.framework.persistent.system.S901060Parameter;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 分类参数定义*/
@Repository("S901060ParameterDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901060ParameterDao extends MyDataBaseOperateSupport2<S901060ParameterDBO> {
    @Resource
    private S901060ParameterMapper mapperS901060Parameter;

    public S901060ParameterMapper getMapper(){
        return mapperS901060Parameter;
    }

}
