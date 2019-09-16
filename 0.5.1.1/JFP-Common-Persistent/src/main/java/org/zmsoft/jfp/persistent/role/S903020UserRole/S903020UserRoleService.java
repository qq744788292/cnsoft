package org.zmsoft.jfp.persistent.role.S903020UserRole;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;

import java.util.List;

/** 系统用户关联角色信息*/
@Service("S903020UserRoleService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S903020UserRoleService extends MyDataBaseOperateSupport2<S903020UserRoleDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S903020UserRoleDao getDao(){
        return getMySqlSession().getMapper(S903020UserRoleDao.class);
    }

    public List<S903020UserRolePVO> doSelectPagePVO(S903020UserRoleDBO s903020UserRoleDBO_) {
        return getDao().doSelectPagePVO(s903020UserRoleDBO_);
    }


    public void doDeleteAll(S903020UserRoleDBO s903020UserRoleDBO_) {
        getDao().doDeleteAll(s903020UserRoleDBO_);
    }
}
