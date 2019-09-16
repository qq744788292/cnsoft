package org.zmsoft.jfp.persistent.system.S901030Menu;


import org.zmsoft.jfp.framework.support.MyDataBaseObjectSupport3;
 
/** 系统菜单*/
public class S901030MenuDBO extends MyDataBaseObjectSupport3
{
    /** 
     * 父菜单流水ID
     */
    private String fatherMenuId = null;
 
    /** 
     * 菜单名称
     */
    private String menuName = null;
 
    /** 
     * 菜单版本号
     */
    private String menuVer = null;
 
    /** 
     * 排列顺序
     */
    private Long sortNum = null;
 
    /** 
     * 菜单图标
     */
    private String menuPicUrl = null;
 
    /** 
     * 菜单URL
     */
    private String menuUrl = null;
 
    /** 
     * 菜单等级
     */
    private String menuLevel = null;
 
    /** 
     * 菜单类别
     */
    private String menuType = null;
 
    /** 
     * 下级菜单数目
     */
    private String nextSub = null;
 
    /** 
     * 数值型变量初始化<br>
     * 仅在插入场合默认调用
     * @see #loadDefauft()
     */
    public void prepareNumeric() {
       //初始化排列顺序
       if(this.sortNum == null)
            this.sortNum = 0L;
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
     * 获取菜单名称
     *
     * @return Menu_name 菜单名称
     */
    public String getMenuName() {
        return this.menuName;
    }
 
    /** 
     * 获取菜单版本号
     *
     * @return Menu_ver 菜单版本号
     */
    public String getMenuVer() {
        return this.menuVer;
    }
 
    /** 
     * 获取排列顺序
     *
     * @return Sort_num 排列顺序
     */
    public Long getSortNum() {
        return this.sortNum;
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
     * 获取菜单等级
     *
     * @return Menu_level 菜单等级
     */
    public String getMenuLevel() {
        return this.menuLevel;
    }
 
    /** 
     * 获取菜单类别
     *
     * @return Menu_type 菜单类别
     */
    public String getMenuType() {
        return this.menuType;
    }
 
    /** 
     * 获取下级菜单数目
     *
     * @return Next_sub 下级菜单数目
     */
    public String getNextSub() {
        return this.nextSub;
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
     * 设置菜单名称
     *
     * @param Menu_name 菜单名称
     */
    public void setMenuName(String menuname) {
        this.menuName = menuname;
    }
 
    /** 
     * 设置菜单版本号
     *
     * @param Menu_ver 菜单版本号
     */
    public void setMenuVer(String menuver) {
        this.menuVer = menuver;
    }
 
    /** 
     * 设置排列顺序
     *
     * @param Sort_num 排列顺序
     */
    public void setSortNum(Long sortnum) {
        this.sortNum = sortnum;
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
     * 设置菜单等级
     *
     * @param Menu_level 菜单等级
     */
    public void setMenuLevel(String menulevel) {
        this.menuLevel = menulevel;
    }
 
    /** 
     * 设置菜单类别
     *
     * @param Menu_type 菜单类别
     */
    public void setMenuType(String menutype) {
        this.menuType = menutype;
    }
 
    /** 
     * 设置下级菜单数目
     *
     * @param Next_sub 下级菜单数目
     */
    public void setNextSub(String nextsub) {
        this.nextSub = nextsub;
    }
 
}
