package org.zmsoft.jfp.persistent.role.S902040GroupPermission;

import org.zmsoft.jfp.framework.support.ISDatabaseSupport;

import java.util.List;

/** 用户组菜单权限表*/
public interface S902040GroupPermissionDao extends ISDatabaseSupport<S902040GroupPermissionDBO> {

    List<S902040GroupPermissionDBO> doSelectPagePVO(S902040GroupPermissionDBO s902040GroupPermissionDBO_);

    void doDeleteAll(S902040GroupPermissionDBO s902040GroupPermissionDBO_);
}
