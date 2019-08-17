package com.zmsoft.framework.persistent.system.S902020RolePermission;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
 
/** 用户角色操作权限表*/
public class S902020RolePermissionDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 角色ID
     */
    private String roleId = null;
 
    /** 
     * 权限标识
     */
    private String authId = null;
 
    /** 
     * 角色名称
     */
    private String roleName = null;
 
    /** 
     * 权限名称
     */
    private String authName = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
    }
 
    /** 
     * 获取角色ID
     *
     * @return Role_id 角色ID
     */
    public String getRoleId() {
        return this.roleId;
    }
 
    /** 
     * 获取权限标识
     *
     * @return Auth_id 权限标识
     */
    public String getAuthId() {
        return this.authId;
    }
 
    /** 
     * 获取角色名称
     *
     * @return Role_name 角色名称
     */
    public String getRoleName() {
        return this.roleName;
    }
 
    /** 
     * 获取权限名称
     *
     * @return Auth_name 权限名称
     */
    public String getAuthName() {
        return this.authName;
    }
 
    /** 
     * 设置角色ID
     *
     * @param Role_id 角色ID
     */
    public void setRoleId(String roleid) {
        this.roleId = roleid;
    }
 
    /** 
     * 设置权限标识
     *
     * @param Auth_id 权限标识
     */
    public void setAuthId(String authid) {
        this.authId = authid;
    }
 
    /** 
     * 设置角色名称
     *
     * @param Role_name 角色名称
     */
    public void setRoleName(String rolename) {
        this.roleName = rolename;
    }
 
    /** 
     * 设置权限名称
     *
     * @param Auth_name 权限名称
     */
    public void setAuthName(String authname) {
        this.authName = authname;
    }
 
}
