package org.zmsoft.jfp.persistent.role.S902010Role;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;

import java.util.List;

/** 系统角色定义表*/
@Service("S902010RoleService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S902010RoleService extends MyDataBaseOperateSupport2<S902010RoleDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S902010RoleDao getDao(){
        return getMySqlSession().getMapper(S902010RoleDao.class);
    }

}
