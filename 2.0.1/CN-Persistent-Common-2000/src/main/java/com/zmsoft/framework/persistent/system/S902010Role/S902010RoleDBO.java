package com.zmsoft.framework.persistent.system.S902010Role;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
 
/** 系统角色定义表*/
public class S902010RoleDBO extends MyDataBaseObjectSupport3
{
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
     * 获取角色名称
     *
     * @return Role_name 角色名称
     */
    public String getRoleName() {
        return this.roleName;
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
