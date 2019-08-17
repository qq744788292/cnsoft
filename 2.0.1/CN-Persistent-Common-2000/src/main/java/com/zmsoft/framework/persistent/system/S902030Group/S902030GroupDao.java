package com.zmsoft.framework.persistent.system.S902030Group;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统用户组定义表*/
@Repository("S902030GroupDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S902030GroupDao extends MyDataBaseOperateSupport2<S902030GroupDBO> {
    @Resource
    private S902030GroupMapper mapperS902030Group;

    public S902030GroupMapper getMapper(){
        return mapperS902030Group;
    }

}
