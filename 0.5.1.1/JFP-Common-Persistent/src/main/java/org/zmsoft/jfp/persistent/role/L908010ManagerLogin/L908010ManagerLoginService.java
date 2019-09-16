package org.zmsoft.jfp.persistent.role.L908010ManagerLogin;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 系统管理用户登录日志表*/
@Service("L908010ManagerLoginService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class L908010ManagerLoginService extends MyDataBaseOperateSupport2<L908010ManagerLoginDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public L908010ManagerLoginDao getDao(){
        return getMySqlSession().getMapper(L908010ManagerLoginDao.class);
    }

}
