package org.zmsoft.jfp.persistent.job.L804011JobLog;
import java.util.List;

import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 定时任务运行日志表*/
@Service("L804011JobLogService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class L804011JobLogService extends MyDataBaseOperateSupport2<L804011JobLogDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public L804011JobLogDao getDao(){
        return getMySqlSession().getMapper(L804011JobLogDao.class);
    }

	public List<L804011JobLogDBO> doSelectTotal(L804011JobLogDBO param) {
		return getDao().doSelectTotal(param);
	}

}
