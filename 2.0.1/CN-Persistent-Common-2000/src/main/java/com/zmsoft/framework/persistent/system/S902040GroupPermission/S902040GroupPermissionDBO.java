package com.zmsoft.framework.persistent.system.S902040GroupPermission;
import org.zmsoft.framework.beans.db.MyDataBaseObjectSupport3;
 
/** 用户组菜单权限表*/
public class S902040GroupPermissionDBO extends MyDataBaseObjectSupport3
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
     * 所属画面ID
     */
    private String pageId = null;
 
    /** 
     * 所属画面名称
     */
    private String pageName = null;
 
    /** 
     * 菜单ID
     */
    private String menuId = null;
 
    /** 
     * 菜单名称
     */
    private String menuName = null;
 
    /** 
     * 菜单等级
     */
    private String menuLevel = null;
 
    /** 
     * 菜单排列顺序
     */
    private Long menuSortNum = null;
 
    /** 
     * 菜单图标
     */
    private String menuPicUrl = null;
 
    /** 
     * 菜单URL
     */
    private String menuUrl = null;
 
    /** 
     * 父菜单流水ID
     */
    private String fatherMenuId = null;
 
    /** 
     * 页面按钮权限组
     */
    private String menuIdArray = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
       //初始化菜单排列顺序
       if(this.menuSortNum == null)
            this.menuSortNum = 0L;
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
     * 获取所属画面ID
     *
     * @return Page_id 所属画面ID
     */
    public String getPageId() {
        return this.pageId;
    }
 
    /** 
     * 获取所属画面名称
     *
     * @return Page_name 所属画面名称
     */
    public String getPageName() {
        return this.pageName;
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
     * 获取菜单排列顺序
     *
     * @return Menu_sort_num 菜单排列顺序
     */
    public Long getMenuSortNum() {
        return this.menuSortNum;
    }
 
    /** 
     * 获取菜单图标
     *
     * @return Menu_pic_url 菜单图标
     */
    public String getMenuPicUrl() {
        return this.menuPicUrl;
    }
 
    /** 
     * 获取菜单URL
     *
     * @return Menu_url 菜单URL
     */
    public String getMenuUrl() {
        return this.menuUrl;
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
     * 获取页面按钮权限组
     *
     * @return Menu_id_array 页面按钮权限组
     */
    public String getMenuIdArray() {
        return this.menuIdArray;
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
 
    /** 
     * 设置所属画面ID
     *
     * @param Page_id 所属画面ID
     */
    public void setPageId(String pageid) {
        this.pageId = pageid;
    }
 
    /** 
     * 设置所属画面名称
     *
     * @param Page_name 所属画面名称
     */
    public void setPageName(String pagename) {
        this.pageName = pagename;
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
     * 设置菜单排列顺序
     *
     * @param Menu_sort_num 菜单排列顺序
     */
    public void setMenuSortNum(Long menusortnum) {
        this.menuSortNum = menusortnum;
    }
 
    /** 
     * 设置菜单图标
     *
     * @param Menu_pic_url 菜单图标
     */
    public void setMenuPicUrl(String menupicurl) {
        this.menuPicUrl = menupicurl;
    }
 
    /** 
     * 设置菜单URL
     *
     * @param Menu_url 菜单URL
     */
    public void setMenuUrl(String menuurl) {
        this.menuUrl = menuurl;
    }
 
    /** 
     * 设置父菜单流水ID
     *
     * @param Father_menu_id 父菜单流水ID
     */
    public void setFatherMenuId(String fathermenuid) {
        this.fatherMenuId = fathermenuid;
    }
 
    /** 
     * 设置页面按钮权限组
     *
     * @param Menu_id_array 页面按钮权限组
     */
    public void setMenuIdArray(String menuidarray) {
        this.menuIdArray = menuidarray;
    }
 
}
