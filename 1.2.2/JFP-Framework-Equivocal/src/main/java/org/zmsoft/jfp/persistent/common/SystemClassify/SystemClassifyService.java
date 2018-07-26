package org.zmsoft.jfp.persistent.common.SystemClassify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;

/** 分类信息*/
@Service("SystemClassifyService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SystemClassifyService extends MyDataBaseOperateSupport2<SystemClassifyDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public SystemClassifyDao getDao(){
        return getMySqlSession().getMapper(SystemClassifyDao.class);
    }

}
