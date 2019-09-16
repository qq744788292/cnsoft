package org.zmsoft.jfp.persistent.role.S902020RolePermission;

import org.zmsoft.jfp.framework.support.ISDatabaseSupport;

import java.util.List;

/** 用户角色操作权限表*/
public interface S902020RolePermissionDao extends ISDatabaseSupport<S902020RolePermissionDBO> {

    List<S902020RolePermissionDBO> doReadPart(String puk);

    void doDeleteAll(S902020RolePermissionDBO S902020RolePermissionDBO_);
}
