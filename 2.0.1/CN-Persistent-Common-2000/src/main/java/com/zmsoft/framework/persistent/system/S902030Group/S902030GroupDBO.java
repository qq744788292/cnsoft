package com.zmsoft.framework.persistent.system.S902030Group;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
 
/** 系统用户组定义表*/
public class S902030GroupDBO extends MyDataBaseObjectSupport3
{
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
     * 获取用户组名称
     *
     * @return Group_name 用户组名称
     */
    public String getGroupName() {
        return this.groupName;
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
