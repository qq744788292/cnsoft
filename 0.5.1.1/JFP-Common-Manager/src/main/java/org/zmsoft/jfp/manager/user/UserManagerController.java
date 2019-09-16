package org.zmsoft.jfp.manager.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.security.value.MD5SecurityHelper;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.framework.utils.BeanFactoryHelper;
import org.zmsoft.jfp.framework.utils.EmptyHelper;
import org.zmsoft.jfp.persistent.role.S903010Manager.S903010ManagerDBO;
import org.zmsoft.jfp.persistent.role.S903010Manager.S903010ManagerService;

@Controller
public class UserManagerController extends MyControllerSupport implements IFrameworkConstants {
	@Resource
	S903010ManagerService s903010ManagerService;

	/**
	 * 系统用户列表
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90301010", method = RequestMethod.POST)
	public ModelAndView selectUserManager90301010(S903010ManagerDBO S903010ManagerDBO, PageModel<S903010ManagerDBO> pageModel, RESTResultBean<String> restResultBean) throws Exception {
		ModelAndView model = getModelAndView("/permission/user/permission-user-url-list");
		if (pageModel.getPageLimit() == 2147483647) {
			pageModel.setPageLimit(10);
		}
		S903010ManagerDBO.setDelFlag(ZERO);// 有效标识
		pageModel.setFormParamBean(S903010ManagerDBO);
		pageModel.setResultCountFlag(true);

		s903010ManagerService.doSelectPage(pageModel);// 查询数据

		model.addObject("list", pageModel);
		model.addObject("searchCondition", S903010ManagerDBO);
		model.addObject("message", restResultBean);
		return model;
	}

	/**
	 * 添加跳转
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90301020", method = RequestMethod.POST)
	public ModelAndView addUserManager90301020() {
		ModelAndView model = getModelAndView("/permission/user/permission-user-url-add");
		model.addObject("model", ONE);
		return model;
	}

	/**
	 * 编辑跳转
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90301030", method = RequestMethod.POST)
	public ModelAndView editorUserManager90301030(S903010ManagerDBO S903010ManagerDBO) {
		ModelAndView model = getModelAndView("/permission/user/permission-user-url-modify");
		List<S903010ManagerDBO> result = s903010ManagerService.doSelectData(S903010ManagerDBO);
		S903010ManagerDBO = result.get(0);
		model.addObject("model", TWO);
		model.addObject("data", S903010ManagerDBO);
		return model;
	}

	/**
	 * 删除
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90301040", method = RequestMethod.POST)
	public ModelAndView deleteUserManager90301040(S903010ManagerDBO S903010ManagerDBO) throws Exception {
		s903010ManagerService.doDelete(S903010ManagerDBO);
		RESTResultBean<String> resultMessage = new RESTResultBean<String>();
		resultMessage.setCode(ONE);
		resultMessage.setMsg(MESSAGE_DB_DELETE);
		return selectUserManager90301010(new S903010ManagerDBO(), new PageModel<S903010ManagerDBO>(), resultMessage);
	}

	/**
	 * 保存
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90301050", method = RequestMethod.POST)
	public ModelAndView saveUserManager90301050(S903010ManagerDBO S903010ManagerDBO, String model, PageModel<S903010ManagerDBO> pageModel) throws Exception {
		RESTResultBean<String> resultMessage = new RESTResultBean<String>();
		if (ONE.equals(model)) {
			if (EmptyHelper.isNotEmpty(S903010ManagerDBO.getUserPassword())) {
				S903010ManagerDBO.setUserPassword(MD5SecurityHelper.encrypt(S903010ManagerDBO.getUserPassword()));
			}
			s903010ManagerService.doInsert(S903010ManagerDBO);
			resultMessage.setCode(ONE);
			resultMessage.setMsg(MESSAGE_DB_INSERT);
		} else {
			if (EmptyHelper.isNotEmpty(S903010ManagerDBO.getUserPassword())) {
				S903010ManagerDBO.setUserPassword(MD5SecurityHelper.encrypt(S903010ManagerDBO.getUserPassword()));
			}
			s903010ManagerService.doUpdate(S903010ManagerDBO);
			resultMessage.setCode(ONE);
			resultMessage.setMsg(MESSAGE_DB_UPDATE);
		}
		return selectUserManager90301010(new S903010ManagerDBO(), pageModel, resultMessage);
	}

	/**
	 * 编辑保存
	 * 
	 * @author 李小锋
	 *//*
		 * @RequestMapping(value = "/90301070", method=RequestMethod.POST)
		 * public ModelAndView saveUserManager90301030(S903010ManagerDBO
		 * S903010ManagerDBO)throws Exception{ S903010ManagerService
		 * s903010ManagerService =
		 * BeanFactoryHelper.getBean("S903010ManagerService");
		 * s903010ManagerService.doUpdate(S903010ManagerDBO); RESTResultBean
		 * resultMessage = new RESTResultBean(); resultMessage.setCode(ONE);
		 * resultMessage.setMsg("修改成功"); return selectUserManager90301010(new
		 * S903010ManagerDBO(),new
		 * PageModel<S903010ManagerDBO>(),resultMessage); }
		 */

	/**
	 * 用户 启用/失效
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90301080", method = RequestMethod.POST)
	public ModelAndView enabledUserManager90301080(S903010ManagerDBO S903010ManagerDBO) throws Exception {
		S903010ManagerService s903010ManagerService = BeanFactoryHelper.getBean("S903010ManagerService");
		List<S903010ManagerDBO> result = s903010ManagerService.doSelectData(S903010ManagerDBO);
		S903010ManagerDBO = result.get(0);
		if (S903010ManagerDBO.getAllowLogin().equals("0")) {
			S903010ManagerDBO.setAllowLogin("1");
		} else {
			S903010ManagerDBO.setAllowLogin("0");
		}
		System.out.println(S903010ManagerDBO);
		s903010ManagerService.doUpdate(S903010ManagerDBO);
		RESTResultBean<String> resultMessage = new RESTResultBean<String>();
		resultMessage.setCode(ONE);
		resultMessage.setMsg("设置成功");
		return selectUserManager90301010(new S903010ManagerDBO(), new PageModel<S903010ManagerDBO>(), resultMessage);

	}
}
