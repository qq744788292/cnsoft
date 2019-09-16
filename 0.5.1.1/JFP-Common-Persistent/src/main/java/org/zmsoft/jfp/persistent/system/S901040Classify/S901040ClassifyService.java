package org.zmsoft.jfp.persistent.system.S901040Classify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;

/** 分类信息*/
@Service("S901040ClassifyService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S901040ClassifyService extends MyDataBaseOperateSupport2<S901040ClassifyDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S901040ClassifyDao getDao(){
        return getMySqlSession().getMapper(S901040ClassifyDao.class);
    }

}
