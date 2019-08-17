package com.zmsoft.framework.persistent.system.S902020RolePermission;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 用户角色操作权限表*/
@Repository("S902020RolePermissionDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S902020RolePermissionDao extends MyDataBaseOperateSupport2<S902020RolePermissionDBO> {
    @Resource
    private S902020RolePermissionMapper mapperS902020RolePermission;

    public S902020RolePermissionMapper getMapper(){
        return mapperS902020RolePermission;
    }

}
