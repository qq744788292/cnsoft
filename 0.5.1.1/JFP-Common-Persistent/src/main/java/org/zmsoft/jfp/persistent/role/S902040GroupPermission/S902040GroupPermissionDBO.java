package org.zmsoft.jfp.persistent.role.S902040GroupPermission;

import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;

import java.sql.Date;
 
/** 用户组菜单权限表*/
public class S902040GroupPermissionDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 用户组ID
     */
    private String groupId = null;
 
    /** 
     * 菜单ID
     */
    private String menuId = null;
 
    /** 
     * 用户组名称
     */
    private String groupName = null;
 
    /** 
     * 菜单名称
     */
    private String menuName = null;
 
    /** 
     * 菜单等级
     */
    private String menuLevel = null;
 
    /** 
     * 父菜单流水ID
     */
    private String fatherMenuId = null;
 
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
     * 获取菜单ID
     *
     * @return Menu_id 菜单ID
     */
    public String getMenuId() {
        return this.menuId;
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
     * 获取菜单名称
     *
     * @return Menu_name 菜单名称
     */
    public String getMenuName() {
        return this.menuName;
    }
 
    /** 
     * 获取菜单等级
     *
     * @return Menu_level 菜单等级
     */
    public String getMenuLevel() {
        return this.menuLevel;
    }
 
    /** 
     * 获取父菜单流水ID
     *
     * @return Father_menu_id 父菜单流水ID
     */
    public String getFatherMenuId() {
        return this.fatherMenuId;
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
     * 设置菜单ID
     *
     * @param Menu_id 菜单ID
     */
    public void setMenuId(String menuid) {
        this.menuId = menuid;
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
     * 设置菜单名称
     *
     * @param Menu_name 菜单名称
     */
    public void setMenuName(String menuname) {
        this.menuName = menuname;
    }
 
    /** 
     * 设置菜单等级
     *
     * @param Menu_level 菜单等级
     */
    public void setMenuLevel(String menulevel) {
        this.menuLevel = menulevel;
    }
 
    /** 
     * 设置父菜单流水ID
     *
     * @param Father_menu_id 父菜单流水ID
     */
    public void setFatherMenuId(String fathermenuid) {
        this.fatherMenuId = fathermenuid;
    }
 
}
