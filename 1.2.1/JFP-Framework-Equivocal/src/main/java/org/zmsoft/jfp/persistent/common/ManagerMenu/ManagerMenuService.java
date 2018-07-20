package org.zmsoft.jfp.persistent.common.ManagerMenu;

import java.util.List;

import org.zmsoft.jfp.framework.support.MyDataBaseOperateSupport2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/** 系统菜单 */
@Service("ManagerMenuService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ManagerMenuService extends MyDataBaseOperateSupport2<ManagerMenuDBO> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	public ManagerMenuDao getDao() {
		return getMySqlSession().getMapper(ManagerMenuDao.class);
	}
	/**
	 * 一览查询
	 * @see doSelectData
	 */
	
	
	/**
	 * 权限查看
	 * @param _ManagerMenuDBO_
	 * @return
	 */
	public List<ManagerMenuDBO> doSelectMenusByPermission(ManagerMenuDBO _ManagerMenuDBO_) {
		return getDao().doSelectMenusByPermission(_ManagerMenuDBO_);
	}

}
