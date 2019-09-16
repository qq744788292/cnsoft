package org.zmsoft.jfp.persistent.role.S903020UserRole;

import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;
import org.zmsoft.jfp.persistent.role.S903010Manager.S903010ManagerDBO;

/** 系统用户关联角色信息*/
public class S903020UserRolePVO extends S903010ManagerDBO
{

    /** 
     * 角色ID
     */
    private String groupId = null;
 

    /** 
     * 角色名称
     */
    private String groupName = null;
 
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
     * @return Group_id 角色ID
     */
    public String getGroupId() {
        return this.groupId;
    }
 

    /** 
     * 获取角色名称
     *
     * @return Group_name 角色名称
     */
    public String getGroupName() {
        return this.groupName;
    }
 

    /** 
     * 设置角色ID
     *
     * @param Group_id 角色ID
     */
    public void setGroupId(String groupid) {
        this.groupId = groupid;
    }
 
/**
     * 设置角色名称
     *
     * @param Group_name 角色名称
     */
    public void setGroupName(String groupname) {
        this.groupName = groupname;
    }
 
}
