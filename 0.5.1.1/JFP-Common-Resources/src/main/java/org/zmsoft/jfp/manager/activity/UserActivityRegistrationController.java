package org.zmsoft.jfp.manager.activity;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.persistent.user.U101010UserInfo.U101010UserInfoDBO;
import org.zmsoft.jfp.persistent.user.U101010UserInfo.U101010UserInfoService;

/**
 * 用户报名一览
 * 
 * @version 0.0.1 2018/05/08
 * @since 0.0.1 2018/05/08
 */
@Controller
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserActivityRegistrationController extends MyControllerSupport {

	@Resource
	U101010UserInfoService U101010UserInfoService_;// 用户基本信息
	
	/**
	 * 一览
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/10101010", method = RequestMethod.POST)
	public ModelAndView list70212010(U101010UserInfoDBO param, PageModel<U101010UserInfoDBO> pageModel, String message) throws Exception {
		ModelAndView model = super.getModelAndView("/customer/user/activity-registration-list");
		param.setDelFlag(ZERO);
		pageModel.setResultCountFlag(true);
		pageModel.setFormParamBean(param);
		pageModel.setOrderby("create_time DESC");
		// 获取菜单数据
		pageModel = U101010UserInfoService_.doSelectPage(pageModel);

		model.addObject("page", pageModel);
		model.addObject("param", param);
		model.addObject("message", message);

		return model;
	}
}
