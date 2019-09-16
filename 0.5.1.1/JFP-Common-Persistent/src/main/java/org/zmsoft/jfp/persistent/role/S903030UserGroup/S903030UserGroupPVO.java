package org.zmsoft.jfp.persistent.role.S903030UserGroup;

import org.zmsoft.jfp.persistent.role.S903010Manager.S903010ManagerDBO;

/** 系统用户关联用户组信息*/
public class S903030UserGroupPVO extends S903010ManagerDBO
{
 
    /** 
     * 用户组ID
     */
    private String groupId = null;

    /**
     * 用户组名称
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
     * 获取用户组ID
     *
     * @return Group_id 用户组ID
     */
    public String getGroupId() {
        return this.groupId;
    }

    /** 
     * 获取用户组名称
     *
     * @return Group_name 用户组名称
     */
    public String getGroupName() {
        return this.groupName;
    }

 
    /** 
     * 设置用户组ID
     *
     * @param Group_id 用户组ID
     */
    public void setGroupId(String groupid) {
        this.groupId = groupid;
    }
 
    /** 
     * 设置用户组名称
     *
     * @param Group_name 用户组名称
     */
    public void setGroupName(String groupname) {
        this.groupName = groupname;
    }
 
}
