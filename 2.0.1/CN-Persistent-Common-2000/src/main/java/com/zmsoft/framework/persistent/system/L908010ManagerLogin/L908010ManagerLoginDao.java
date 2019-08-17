package com.zmsoft.framework.persistent.system.L908010ManagerLogin;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统管理用户登录日志表*/
@Repository("L908010ManagerLoginDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class L908010ManagerLoginDao extends MyDataBaseOperateSupport2<L908010ManagerLoginDBO> {
    @Resource
    private L908010ManagerLoginMapper mapperL908010ManagerLogin;

    public L908010ManagerLoginMapper getMapper(){
        return mapperL908010ManagerLogin;
    }

}
