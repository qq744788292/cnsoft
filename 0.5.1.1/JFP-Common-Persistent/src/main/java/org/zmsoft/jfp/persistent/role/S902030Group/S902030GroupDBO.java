package org.zmsoft.jfp.persistent.role.S902030Group;

import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;

import java.sql.Date;
 
/** 系统用户组定义表*/
public class S902030GroupDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 用户组名称
     */
    private String groupName = null;
 
    /** 
     * 权限标识
     */
    private String state = null;
 
    /** 
     * 停用开关
     */
    private String useStatus = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
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
     * 获取权限标识
     *
     * @return State 权限标识
     */
    public String getState() {
        return this.state;
    }
 
    /** 
     * 获取停用开关
     *
     * @return Use_status 停用开关
     */
    public String getUseStatus() {
        return this.useStatus;
    }
 
    /** 
     * 设置用户组名称
     *
     * @param Group_name 用户组名称
     */
    public void setGroupName(String groupname) {
        this.groupName = groupname;
    }
 
    /** 
     * 设置权限标识
     *
     * @param State 权限标识
     */
    public void setState(String state) {
        this.state = state;
    }
 
    /** 
     * 设置停用开关
     *
     * @param Use_status 停用开关
     */
    public void setUseStatus(String usestatus) {
        this.useStatus = usestatus;
    }
 
}
