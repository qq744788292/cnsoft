package org.zmsoft.jfp.persistent.role.S903030UserGroup;

import org.zmsoft.jfp.framework.support.ISDatabaseSupport;

import java.util.List;

/** 系统用户关联用户组信息*/
public interface S903030UserGroupDao extends ISDatabaseSupport<S903030UserGroupDBO> {

    List<S903030UserGroupPVO> doSelectPagePVO(S903030UserGroupDBO s903030UserGroupDBO_);

    void doDeleteAll(S903030UserGroupDBO s903030UserGroupDBO_);
}
