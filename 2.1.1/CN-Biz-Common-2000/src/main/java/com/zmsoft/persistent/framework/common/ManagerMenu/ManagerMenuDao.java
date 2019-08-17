package com.zmsoft.persistent.framework.common.ManagerMenu;

import java.util.List;

import org.zmsoft.framework.db.ISDatabaseSupport;

/** 系统菜单 */
public interface ManagerMenuDao extends ISDatabaseSupport<ManagerMenuDBO> {

	
	/**
	 * 获得用户权限
	 * @param _ManagerMenuDBO_
	 * @return
	 */
	List<ManagerMenuDBO> doSelectMenusByPermission(ManagerMenuDBO _ManagerMenuDBO_);

}
