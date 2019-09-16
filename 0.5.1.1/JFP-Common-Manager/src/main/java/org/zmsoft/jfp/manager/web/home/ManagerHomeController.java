package org.zmsoft.jfp.manager.web.home;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.user.UserBean;
import org.zmsoft.jfp.framework.cache.session.SessionHelper;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.DateHelper;
import org.zmsoft.jfp.persistent.common.ManagerMenu.ManagerMenuDBO;

/**
 * 运维管理人员菜单权限
 * 
 * @author zmsoft
 * @version 0.0.1 2017/03/15
 * @since 0.0.1 2017/03/15
 */
@Controller
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ManagerHomeController extends MyControllerSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/ManagerHome", method = RequestMethod.POST)
	public ModelAndView doManagerHomePOST(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = getModelAndView("/main/main-menu");// 带权限的main-menu2,不带权限main-menu

		UserBean currentUser = SessionHelper.currentUser();

		// 加载菜单数据
		ManagerHomeBiz _ManagerHomeBiz_ = BeanFactoryHelper.getBean("ManagerHomeBiz");
		RESTResultBean<List<ManagerMenuDBO>> result = _ManagerHomeBiz_.loadMenu(currentUser);

		model.addObject("menu", result.getData());

		model.addObject("DDD", DateHelper.currentTimeMillis2());
		model.addObject("MENU", result.getData());
		model.addObject("token", SessionHelper.currentToken());
		model.addObject("loginer", currentUser);

		return model; // 返回result对象给前端
	}
}
