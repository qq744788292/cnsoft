package org.zmsoft.jfp.persistent.role.L908020ManagerOperation;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 系统管理用户操作日志表*/
@Service("L908020ManagerOperationService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class L908020ManagerOperationService extends MyDataBaseOperateSupport2<L908020ManagerOperationDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public L908020ManagerOperationDao getDao(){
        return getMySqlSession().getMapper(L908020ManagerOperationDao.class);
    }

}
