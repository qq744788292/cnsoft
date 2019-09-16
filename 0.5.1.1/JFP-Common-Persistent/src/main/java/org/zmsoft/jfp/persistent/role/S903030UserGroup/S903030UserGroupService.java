package org.zmsoft.jfp.persistent.role.S903030UserGroup;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;

import java.util.List;

/** 系统用户关联用户组信息*/
@Service("S903030UserGroupService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class S903030UserGroupService extends MyDataBaseOperateSupport2<S903030UserGroupDBO> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public S903030UserGroupDao getDao(){
        return getMySqlSession().getMapper(S903030UserGroupDao.class);
    }

    public List<S903030UserGroupPVO> doSelectPagePVO(S903030UserGroupDBO s903030UserGroupDBO_) {
        return  getDao().doSelectPagePVO(s903030UserGroupDBO_);
    }

    public void doDeleteAll(S903030UserGroupDBO s903030UserGroupDBO_) {
        getDao().doDeleteAll(s903030UserGroupDBO_);
    }
}
