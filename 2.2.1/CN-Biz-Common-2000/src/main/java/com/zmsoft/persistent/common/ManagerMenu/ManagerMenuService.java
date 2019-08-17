package com.zmsoft.persistent.common.ManagerMenu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.zmsoft.framework.db.MyDataBaseOperateSupport2;

/** 系统菜单 */
@Service("ManagerMenuService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ManagerMenuService extends MyDataBaseOperateSupport2<ManagerMenuDBO> {

	@Resource
	private ManagerMenuDao daoManagerMenu;

	public ManagerMenuDao getMapper() {
		return daoManagerMenu;
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
