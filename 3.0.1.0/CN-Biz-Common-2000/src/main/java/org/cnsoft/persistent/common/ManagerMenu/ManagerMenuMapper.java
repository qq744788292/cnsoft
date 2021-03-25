package org.cnsoft.persistent.common.ManagerMenu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.cnsoft.framework.db.ISDatabaseSupport;

/** 系统菜单 */
@Mapper
public interface ManagerMenuMapper extends ISDatabaseSupport<ManagerMenuDBO> {

	
	/**
	 * 获得用户权限
	 * @param _ManagerMenuDBO_
	 * @return
	 */
	List<ManagerMenuDBO> doSelectMenusByPermission(ManagerMenuDBO _ManagerMenuDBO_);

}
