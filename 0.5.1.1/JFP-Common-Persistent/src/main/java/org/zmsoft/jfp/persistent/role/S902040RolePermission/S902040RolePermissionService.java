package org.zmsoft.jfp.persistent.role.S902040RolePermission;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 用户角色操作权限表*/
@Service("S902040RolePermissionService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S902040RolePermissionService extends MyDataBaseOperateSupport2<S902040RolePermissionDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S902040RolePermissionDao getDao(){
        return getMySqlSession().getMapper(S902040RolePermissionDao.class);
    }

}
