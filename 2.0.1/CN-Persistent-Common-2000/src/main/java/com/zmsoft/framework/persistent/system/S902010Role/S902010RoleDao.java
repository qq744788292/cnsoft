package com.zmsoft.framework.persistent.system.S902010Role;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统角色定义表*/
@Repository("S902010RoleDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S902010RoleDao extends MyDataBaseOperateSupport2<S902010RoleDBO> {
    @Resource
    private S902010RoleMapper mapperS902010Role;

    public S902010RoleMapper getMapper(){
        return mapperS902010Role;
    }

}
