package org.zmsoft.jfp.persistent.role.S902030Group;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;

/** 系统用户组定义表*/
@Service("S902030GroupService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S902030GroupService extends MyDataBaseOperateSupport2<S902030GroupDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S902030GroupDao getDao(){
        return getMySqlSession().getMapper(S902030GroupDao.class);
    }

}
