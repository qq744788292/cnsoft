package org.zmsoft.jfp.manager.system.menu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.constants.IFrameworkConstants;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.persistent.system.S901030Menu.S901030MenuDBO;
import org.zmsoft.jfp.persistent.system.S901030Menu.S901030MenuService;

@Controller
public class SystemMenuController extends MyControllerSupport implements IFrameworkConstants {
	@Resource
	S901030MenuService s901030MenuService;

	/**
	 * 菜单列表
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90102010", method = RequestMethod.POST)
	public ModelAndView selectSystemMenu90302010(S901030MenuDBO s901030MenuDBO, PageModel<S901030MenuDBO> pageModel, RESTResultBean<String> restResultBean) throws Exception {
		ModelAndView model = getModelAndView("/system/systemMenu/system-Menu-url-list");
		if (pageModel.getPageLimit() == 2147483647) {
			pageModel.setPageLimit(10);
		}
		s901030MenuDBO.setDelFlag(ZERO);// 有效标识
		pageModel.setFormParamBean(s901030MenuDBO);
		pageModel.setResultCountFlag(true);

		s901030MenuService.doSelectPage(pageModel);// 查询数据

		model.addObject("list", pageModel);
		model.addObject("searchCondition", s901030MenuDBO);
		model.addObject("message", restResultBean);
		return model;
	}

	/**
	 * 添加跳转页面
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90102020", method = RequestMethod.POST)
	public ModelAndView addSystemMenu90102020(S901030MenuDBO S901030MenuDBO) throws Exception {
		ModelAndView model = getModelAndView("/system/systemMenu/system-Menu-url-add");
		model.addObject("model", ONE);
		return model;
	}

	/**
	 * 编辑菜单页面跳转
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90102030", method = RequestMethod.POST)
	public ModelAndView editorSystemMenu90102030(S901030MenuDBO S901030MenuDBO) {
		ModelAndView model = getModelAndView("/system/systemMenu/system-Menu-url-modify");
		List<S901030MenuDBO> result = s901030MenuService.doSelectData(S901030MenuDBO);
		S901030MenuDBO = result.get(0);
		model.addObject("model", TWO);
		model.addObject("data", S901030MenuDBO);
		return model;
	}

	/**
	 * 删除菜单
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90102040", method = RequestMethod.POST)
	public ModelAndView deleteSystemMenu90102040(S901030MenuDBO S901030MenuDBO) throws Exception {
		s901030MenuService.doDelete(S901030MenuDBO);
		RESTResultBean<String> restResultBean = new RESTResultBean<String>();
		restResultBean.setCode(ONE);
		restResultBean.setMsg(MESSAGE_DB_DELETE);
		return selectSystemMenu90302010(new S901030MenuDBO(), new PageModel<S901030MenuDBO>(), restResultBean);
	}

	/**
	 * 添加保存
	 * 
	 * @author 李小锋
	 */
	@RequestMapping(value = "/90102050", method = RequestMethod.POST)
	public ModelAndView saveSystemMenu90102050(S901030MenuDBO S901030MenuDBO, String model, PageModel<S901030MenuDBO> pageModel) throws Exception {
		RESTResultBean<String> restResultBean = new RESTResultBean<String>();
		if (ONE.equals(model)) {
			restResultBean.setCode(ONE);
			restResultBean.setMsg(MESSAGE_DB_INSERT);
			s901030MenuService.doInsert(S901030MenuDBO);
		} else {
			restResultBean.setCode(ONE);
			restResultBean.setMsg(MESSAGE_DB_UPDATE);
			s901030MenuService.doUpdate(S901030MenuDBO);
		}
		return selectSystemMenu90302010(new S901030MenuDBO(), pageModel, restResultBean);
	}

	/*
	*//**
		 * 编辑菜单页面保存
		 * 
		 * @author 李小锋
		 *//*
		 * @RequestMapping(value = "/90102060",method=RequestMethod.POST) public
		 * ModelAndView saveEditorSystemMenu90102030(S901030MenuDBO
		 * S901030MenuDBO)throws Exception{ S901030MenuService
		 * s901030MenuService=BeanFactoryHelper.getBean("S901030MenuService");
		 * s901030MenuService.doUpdate(S901030MenuDBO);
		 * 
		 * 
		 * 
		 * RESTResultBean resultMessage = new RESTResultBean();
		 * resultMessage.setCode(ONE); resultMessage.setMsg("更新成功"); return
		 * selectSystemMenu90302010(new S901030MenuDBO(),new
		 * PageModel<S901030MenuDBO>(),resultMessage); }
		 */
}
