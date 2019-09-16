package org.zmsoft.jfp.persistent.job.C804010JobInfo;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 定时任务配置表*/
@Service("C804010JobInfoService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class C804010JobInfoService extends MyDataBaseOperateSupport2<C804010JobInfoDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public C804010JobInfoDao getDao(){
        return getMySqlSession().getMapper(C804010JobInfoDao.class);
    }

}
