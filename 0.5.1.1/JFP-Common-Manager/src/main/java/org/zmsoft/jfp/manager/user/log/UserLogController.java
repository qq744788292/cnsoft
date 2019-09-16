package org.zmsoft.jfp.manager.user.log;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.persistent.role.L908010ManagerLogin.L908010ManagerLoginDBO;
import org.zmsoft.jfp.persistent.role.L908010ManagerLogin.L908010ManagerLoginService;

@Controller
public class UserLogController extends MyControllerSupport implements IFrameworkConstants {
	@Resource
	L908010ManagerLoginService l908010ManagerLoginService;

	/**
	 * 用户登陆日志一览
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90801010", method = RequestMethod.POST)
	public ModelAndView doSelectUserLog90801010(L908010ManagerLoginDBO L908010ManagerLoginDBO, PageModel<L908010ManagerLoginDBO> pageModel, RESTResultBean<String> message) throws Exception {
		ModelAndView model = getModelAndView("/permission/user_login_log/permission-user-log-list");
		L908010ManagerLoginDBO.setDelFlag(ZERO);
		pageModel.setFormParamBean(L908010ManagerLoginDBO);
		pageModel.setResultCountFlag(true);

		l908010ManagerLoginService.doSelectPage(pageModel);
		model.addObject("model", pageModel);
		model.addObject("message", message);
		return model;
	}
}
