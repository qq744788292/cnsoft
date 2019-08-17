package com.zmsoft.framework.persistent.system.S902040GroupPermission;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 用户组菜单权限表*/
@Repository("S902040GroupPermissionDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S902040GroupPermissionDao extends MyDataBaseOperateSupport2<S902040GroupPermissionDBO> {
    @Resource
    private S902040GroupPermissionMapper mapperS902040GroupPermission;

    public S902040GroupPermissionMapper getMapper(){
        return mapperS902040GroupPermission;
    }

}
