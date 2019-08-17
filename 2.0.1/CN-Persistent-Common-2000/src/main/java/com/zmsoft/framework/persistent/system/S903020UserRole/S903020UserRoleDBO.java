package com.zmsoft.framework.persistent.system.S903020UserRole;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
 
/** 系统用户关联角色信息*/
public class S903020UserRoleDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 用户ID
     */
    private String userId = null;
 
    /** 
     * 角色ID
     */
    private String roleId = null;
 
    /** 
     * 用户姓名
     */
    private String userName = null;
 
    /** 
     * 角色名称
     */
    private String roleName = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
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
     * 获取角色ID
     *
     * @return Role_id 角色ID
     */
    public String getRoleId() {
        return this.roleId;
    }
 
    /** 
     * 获取用户姓名
     *
     * @return User_name 用户姓名
     */
    public String getUserName() {
        return this.userName;
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
     * 设置用户ID
     *
     * @param User_id 用户ID
     */
    public void setUserId(String userid) {
        this.userId = userid;
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
     * 设置用户姓名
     *
     * @param User_name 用户姓名
     */
    public void setUserName(String username) {
        this.userName = username;
    }
 
    /** 
     * 设置角色名称
     *
     * @param Role_name 角色名称
     */
    public void setRoleName(String rolename) {
        this.roleName = rolename;
    }
 
}
