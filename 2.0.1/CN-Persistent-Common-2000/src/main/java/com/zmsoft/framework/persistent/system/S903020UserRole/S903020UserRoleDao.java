package com.zmsoft.framework.persistent.system.S903020UserRole;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统用户关联角色信息*/
@Repository("S903020UserRoleDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S903020UserRoleDao extends MyDataBaseOperateSupport2<S903020UserRoleDBO> {
    @Resource
    private S903020UserRoleMapper mapperS903020UserRole;

    public S903020UserRoleMapper getMapper(){
        return mapperS903020UserRole;
    }

}
