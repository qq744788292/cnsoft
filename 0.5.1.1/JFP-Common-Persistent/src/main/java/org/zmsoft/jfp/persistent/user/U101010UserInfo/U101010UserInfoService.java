package org.zmsoft.jfp.persistent.user.U101010UserInfo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;

import java.util.List;

/** 玩家基本信息*/
@Service("U101010UserInfoService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class U101010UserInfoService extends MyDataBaseOperateSupport2<U101010UserInfoDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public U101010UserInfoDao getDao(){
        return getMySqlSession().getMapper(U101010UserInfoDao.class);
    }

    public PageModel<U101010UserInfoDBO> doSelectPageByHead(PageModel<U101010UserInfoDBO> pageModel) {
        changeTable(pageModel.currentFormParamBean(),DB_SELECT);
        pageModel.setPageListData(getDao().doSelectPageByHead(pageModel));
        return pageModel;
    }
}
