package org.zmsoft.jfp.persistent.common.ManagerMenu;

import java.util.List;

import org.zmsoft.jfp.framework.support.ISDatabaseSupport;

/** 系统菜单 */
public interface ManagerMenuDao extends ISDatabaseSupport<ManagerMenuDBO> {

	
	/**
	 * 获得用户权限
	 * @param _ManagerMenuDBO_
	 * @return
	 */
	List<ManagerMenuDBO> doSelectMenusByPermission(ManagerMenuDBO _ManagerMenuDBO_);

}
