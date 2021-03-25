package org.cnsoft.persistent.common.ManagerMenu;

import java.util.List;

import org.cnsoft.framework.db.support.ext.MyDataBaseObjectSupport2;

public class ManagerMenuDBO extends MyDataBaseObjectSupport2 {
	
	/**
	 * 用户ID
	 */
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private List<ManagerMenuDBO> nextSubs;

	/**
	 * 获取下级菜单
	 *
	 * @return Next_sub 下级菜单
	 */
	public List<ManagerMenuDBO> getNextSubs() {
		return nextSubs;
	}
	
	/**
	 * 设置下级菜单数目
	 *
	 * @param Next_sub
	 *            下级菜单
	 */
	public void setNextSubs(List<ManagerMenuDBO> nextSubs) {
		this.nextSubs = nextSubs;
	}
	///////////////////////////////////////////////////////////////////////

	/**
	 * 菜单名称
	 */
	private String menuName = null;

	/**
	 * 菜单版本号
	 */
	private String menuVer = null;

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
	 * 菜单等级
	 */
	private String menuLevel = null;

	/**
	 * 菜单类别
	 */
	private String menuType = null;

	/**
	 * 页面流水ID
	 */
	private String pageId = null;

	/**
	 * 页面名称
	 */
	private String pageName = null;

	/**
	 * 上级菜单流水ID
	 */
	private String fatherMenuId = null;

	/**
	 * 上级菜单名称
	 */
	private String fatherMenuName = null;

	/**
	 * 数值型变量初始化<br>
	 * 仅在插入场合默认调用
	 * 
	 * @see #loadDefauft()
	 */
	public void prepareNumeric() {
		// 初始化菜单排列顺序
		if (this.menuSortNum == null)
			this.menuSortNum = 0L;
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
	 * 获取页面流水ID
	 *
	 * @return Page_id 页面流水ID
	 */
	public String getPageId() {
		return this.pageId;
	}

	/**
	 * 获取页面名称
	 *
	 * @return Page_name 页面名称
	 */
	public String getPageName() {
		return this.pageName;
	}

	/**
	 * 获取上级菜单流水ID
	 *
	 * @return Father_menu_id 上级菜单流水ID
	 */
	public String getFatherMenuId() {
		return this.fatherMenuId;
	}

	/**
	 * 获取上级菜单名称
	 *
	 * @return Father_menu_name 上级菜单名称
	 */
	public String getFatherMenuName() {
		return this.fatherMenuName;
	}

	/**
	 * 设置菜单名称
	 *
	 * @param Menu_name
	 *            菜单名称
	 */
	public void setMenuName(String menuname) {
		this.menuName = menuname;
	}

	/**
	 * 设置菜单版本号
	 *
	 * @param Menu_ver
	 *            菜单版本号
	 */
	public void setMenuVer(String menuver) {
		this.menuVer = menuver;
	}

	/**
	 * 设置菜单排列顺序
	 *
	 * @param Menu_sort_num
	 *            菜单排列顺序
	 */
	public void setMenuSortNum(Long menusortnum) {
		this.menuSortNum = menusortnum;
	}

	/**
	 * 设置菜单图标
	 *
	 * @param Menu_pic_url
	 *            菜单图标
	 */
	public void setMenuPicUrl(String menupicurl) {
		this.menuPicUrl = menupicurl;
	}

	/**
	 * 设置菜单URL
	 *
	 * @param Menu_url
	 *            菜单URL
	 */
	public void setMenuUrl(String menuurl) {
		this.menuUrl = menuurl;
	}

	/**
	 * 设置菜单等级
	 *
	 * @param Menu_level
	 *            菜单等级
	 */
	public void setMenuLevel(String menulevel) {
		this.menuLevel = menulevel;
	}

	/**
	 * 设置菜单类别
	 *
	 * @param Menu_type
	 *            菜单类别
	 */
	public void setMenuType(String menutype) {
		this.menuType = menutype;
	}

	/**
	 * 设置页面流水ID
	 *
	 * @param Page_id
	 *            页面流水ID
	 */
	public void setPageId(String pageid) {
		this.pageId = pageid;
	}

	/**
	 * 设置页面名称
	 *
	 * @param Page_name
	 *            页面名称
	 */
	public void setPageName(String pagename) {
		this.pageName = pagename;
	}

	/**
	 * 设置上级菜单流水ID
	 *
	 * @param Father_menu_id
	 *            上级菜单流水ID
	 */
	public void setFatherMenuId(String fathermenuid) {
		this.fatherMenuId = fathermenuid;
	}

	/**
	 * 设置上级菜单名称
	 *
	 * @param Father_menu_name
	 *            上级菜单名称
	 */
	public void setFatherMenuName(String fathermenuname) {
		this.fatherMenuName = fathermenuname;
	}

}
