package com.zmsoft.framework.persistent.system.S903010Manager;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统管理用户表*/
@Repository("S903010ManagerDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S903010ManagerDao extends MyDataBaseOperateSupport2<S903010ManagerDBO> {
    @Resource
    private S903010ManagerMapper mapperS903010Manager;

    public S903010ManagerMapper getMapper(){
        return mapperS903010Manager;
    }

}
