package org.zmsoft.jfp.common.job.log;

import java.util.List;

import org.zmsoft.jfp.common.constants.CustomConstants;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.user.UserBean;
import org.zmsoft.jfp.framework.support.MyBusinessSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.persistent.common.ManagerMenu.ManagerMenuDBO;
import org.zmsoft.jfp.persistent.common.ManagerMenu.ManagerMenuService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 任务配置
 * 
 * @version 0.0.1 2018/05/08
 * @since 0.0.1 2018/05/08
 */
@Service("JobLogTask")
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JobLogTask extends MyBusinessSupport<Object,List<ManagerMenuDBO>> implements CustomConstants {

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
		if (UserBean_.getAccount().equals("admin")) {
			menuDatas = _ManagerMenuService_.doSelectData(_ManagerMenuDBO_);// 如果管理员则查询全部菜单
			// 获取一级菜单
			for (ManagerMenuDBO menu : menuDatas) {
				_ManagerMenuDBO_.setMenuLevel(TWO);
				_ManagerMenuDBO_.setFatherMenuId(menu.getPuk());
				menu.setNextSubs(_ManagerMenuService_.doSelectData(_ManagerMenuDBO_));
			}
		} else {
			_ManagerMenuDBO_.setFb4(UserBean_.getAccount());
			menuDatas = _ManagerMenuService_.doSelectMenusByPermission(_ManagerMenuDBO_);// 根据权限读取菜单
			// 获取二级菜单
			for (ManagerMenuDBO menu : menuDatas) {
				_ManagerMenuDBO_.setMenuLevel(TWO);
				_ManagerMenuDBO_.setFatherMenuId(menu.getPuk());
				menu.setNextSubs(_ManagerMenuService_.doSelectMenusByPermission(_ManagerMenuDBO_));
			}
		}

		result.setData(menuDatas);

		logger.debug("RESTResultBean==>>>" + result);
		return result;
	}

}
