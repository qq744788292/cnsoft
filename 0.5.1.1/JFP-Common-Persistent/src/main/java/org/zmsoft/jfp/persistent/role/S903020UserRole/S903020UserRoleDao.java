package org.zmsoft.jfp.persistent.role.S903020UserRole;

import org.zmsoft.jfp.framework.support.ISDatabaseSupport;

import java.util.List;

/** 系统用户关联角色信息*/
public interface S903020UserRoleDao extends ISDatabaseSupport<S903020UserRoleDBO> {

    List<S903020UserRolePVO> doSelectPagePVO(S903020UserRoleDBO s903020UserRoleDBO_);


    void doDeleteAll(S903020UserRoleDBO s903020UserRoleDBO_);
}
