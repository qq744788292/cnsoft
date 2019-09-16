package org.zmsoft.jfp.manager.web.home;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zmsoft.jfp.common.constants.CustomConstants;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.user.UserBean;
import org.zmsoft.jfp.framework.support.MyBusinessSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.persistent.common.ManagerMenu.ManagerMenuDBO;
import org.zmsoft.jfp.persistent.common.ManagerMenu.ManagerMenuService;

/**
 * 运维管理人员菜单权限
 * 
 * @author fucy
 * @version 0.0.1 2017/07/10
 * @since 0.0.1 2017/07/10
 */
@Service("ManagerHomeBiz")
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ManagerHomeBiz extends MyBusinessSupport<Object, List<ManagerMenuDBO>> implements CustomConstants {

	@Transactional
	public RESTResultBean<List<ManagerMenuDBO>> loadMenu(UserBean UserBean_) throws Exception {
		logger.debug("UserBean==>>>" + UserBean_);
		ManagerMenuService _ManagerMenuService_ = BeanFactoryHelper.getBean("ManagerMenuService");
		ManagerMenuDBO _ManagerMenuDBO_ = new ManagerMenuDBO();
		result = new RESTResultBean<List<ManagerMenuDBO>>();
		// TODO
		// 权限处理
		// _ManagerMenuDBO_.setFb5(userId);
		// _ManagerMenuDBO_.setDelFlag("0");
		//
		_ManagerMenuDBO_.setDelFlag(ZERO);
		// _ManagerMenuDBO_.setFb5(UserBean_);
		// 获取一级菜单
		_ManagerMenuDBO_.setMenuLevel(ONE);
		List<ManagerMenuDBO> menuDatas;

		// 用户角色判断
		// if (UserBean_.getAccount().equals("admin"))
		{
			menuDatas = _ManagerMenuService_.doSelectData(_ManagerMenuDBO_);// 如果管理员则查询全部菜单
			// 获取一级菜单
			for (ManagerMenuDBO menu : menuDatas) {
				_ManagerMenuDBO_.setMenuLevel(TWO);
				_ManagerMenuDBO_.setFatherMenuId(menu.getPuk());
				menu.setNextSubs(_ManagerMenuService_.doSelectData(_ManagerMenuDBO_));
			}
			// } else {
			// _ManagerMenuDBO_.setFb4(UserBean_.getAccount());
			// menuDatas =
			// _ManagerMenuService_.doSelectMenusByPermission(_ManagerMenuDBO_);//
			// 根据权限读取菜单
			// // 获取二级菜单
			// for (ManagerMenuDBO menu : menuDatas) {
			// _ManagerMenuDBO_.setMenuLevel(TWO);
			// _ManagerMenuDBO_.setFatherMenuId(menu.getPuk());
			// menu.setNextSubs(_ManagerMenuService_.doSelectMenusByPermission(_ManagerMenuDBO_));
			// }
		}

		result.setData(menuDatas);

		logger.debug("RESTResultBean==>>>" + result);
		return result;
	}

}
