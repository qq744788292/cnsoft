package com.zmsoft.framework.persistent.system.S903030UserGroup;
import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统用户关联用户组信息*/
@Repository("S903030UserGroupDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S903030UserGroupDao extends MyDataBaseOperateSupport2<S903030UserGroupDBO> {
    @Resource
    private S903030UserGroupMapper mapperS903030UserGroup;

    public S903030UserGroupMapper getMapper(){
        return mapperS903030UserGroup;
    }

}
