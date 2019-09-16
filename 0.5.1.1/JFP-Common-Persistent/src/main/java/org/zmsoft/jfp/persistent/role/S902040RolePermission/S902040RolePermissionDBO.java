package org.zmsoft.jfp.persistent.role.S902040RolePermission;
import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;
 
/** 用户角色操作权限表*/
public class S902040RolePermissionDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 角色ID
     */
    private String roleId = null;
 
    /** 
     * 用户ID
     */
    private String userId = null;
 
    /** 
     * 角色名称
     */
    private String roleName = null;
 
    /** 
     * 用户名称
     */
    private String userName = null;
 
    /** 
     * 权限标识
     */
    private String state = null;
 
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
     * 获取用户ID
     *
     * @return User_id 用户ID
     */
    public String getUserId() {
        return this.userId;
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
     * 获取用户名称
     *
     * @return User_name 用户名称
     */
    public String getUserName() {
        return this.userName;
    }
 
    /** 
     * 获取权限标识
     *
     * @return State 权限标识
     */
    public String getState() {
        return this.state;
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
     * 设置用户ID
     *
     * @param User_id 用户ID
     */
    public void setUserId(String userid) {
        this.userId = userid;
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
     * 设置用户名称
     *
     * @param User_name 用户名称
     */
    public void setUserName(String username) {
        this.userName = username;
    }
 
    /** 
     * 设置权限标识
     *
     * @param State 权限标识
     */
    public void setState(String state) {
        this.state = state;
    }
 
}
