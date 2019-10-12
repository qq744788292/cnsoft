package org.zmsoft.persistent.common.ManagerMenu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统菜单 */
@Repository("ManagerMenuDao")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ManagerMenuDao extends MyDataBaseOperateSupport2<ManagerMenuDBO> {

	@Resource
	private ManagerMenuMapper mapperManagerMenu;

	public ManagerMenuMapper getMapper() {
		return mapperManagerMenu;
	}

	/**
	 * 一览查询
	 * 
	 * @see doSelectData
	 */

	/**
	 * 权限查看
	 * 
	 * @param _ManagerMenuDBO_
	 * @return
	 */
	public List<ManagerMenuDBO> doSelectMenusByPermission(ManagerMenuDBO _ManagerMenuDBO_) {
		return getMapper().doSelectMenusByPermission(_ManagerMenuDBO_);
	}

}
