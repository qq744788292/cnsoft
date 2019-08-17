package com.zmsoft.framework.persistent.system.L908020ManagerOperation;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统管理用户操作日志表*/
@Repository("L908020ManagerOperationDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class L908020ManagerOperationDao extends MyDataBaseOperateSupport2<L908020ManagerOperationDBO> {
    @Resource
    private L908020ManagerOperationMapper mapperL908020ManagerOperation;

    public L908020ManagerOperationMapper getMapper(){
        return mapperL908020ManagerOperation;
    }

}
