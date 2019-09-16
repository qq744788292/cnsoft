package org.zmsoft.jfp.persistent.role.S902040GroupPermission;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;

import java.util.List;

/** 用户组菜单权限表*/
@Service("S902040GroupPermissionService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S902040GroupPermissionService extends MyDataBaseOperateSupport2<S902040GroupPermissionDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S902040GroupPermissionDao getDao(){
        return getMySqlSession().getMapper(S902040GroupPermissionDao.class);
    }

    public List<S902040GroupPermissionDBO> doSelectPagePVO(S902040GroupPermissionDBO s902040GroupPermissionDBO_) {
        return  getDao().doSelectPagePVO(s902040GroupPermissionDBO_);
    }

    public void doDeleteAll(S902040GroupPermissionDBO s902040GroupPermissionDBO_) {
        getDao().doDeleteAll(s902040GroupPermissionDBO_);
    }
}
