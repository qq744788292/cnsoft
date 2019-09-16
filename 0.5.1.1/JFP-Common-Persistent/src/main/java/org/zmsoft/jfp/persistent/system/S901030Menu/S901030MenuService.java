package org.zmsoft.jfp.persistent.system.S901030Menu;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 系统菜单*/
@Service("S901030MenuService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901030MenuService extends MyDataBaseOperateSupport2<S901030MenuDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S901030MenuDao getDao(){
        return getMySqlSession().getMapper(S901030MenuDao.class);
    }

}
