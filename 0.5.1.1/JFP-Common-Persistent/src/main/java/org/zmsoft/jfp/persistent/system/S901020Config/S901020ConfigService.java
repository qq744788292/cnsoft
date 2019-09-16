package org.zmsoft.jfp.persistent.system.S901020Config;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 系统配置*/
@Service("S901020ConfigService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901020ConfigService extends MyDataBaseOperateSupport2<S901020ConfigDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S901020ConfigDao getDao(){
        return getMySqlSession().getMapper(S901020ConfigDao.class);
    }

}
