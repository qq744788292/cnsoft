package org.zmsoft.jfp.persistent.role.S902020RolePermission;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;

import java.util.List;

/** 用户角色操作权限表*/
@Service("S902020RolePermissionService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S902020RolePermissionService extends MyDataBaseOperateSupport2<S902020RolePermissionDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S902020RolePermissionDao getDao(){
        return getMySqlSession().getMapper(S902020RolePermissionDao.class);
    }

    public List<S902020RolePermissionDBO> doReadPart(String puk) {
        return  getDao().doReadPart(puk);
    }

    public void doDeleteAll(S902020RolePermissionDBO S902020RolePermissionDBO_) {
        getDao().doDeleteAll(S902020RolePermissionDBO_);
    }
}
