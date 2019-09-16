package org.zmsoft.jfp.manager.material.wxdyh;

import javax.annotation.Resource;

import org.zmsoft.jfp.framework.beans.common.RESTResultBean;
import org.zmsoft.jfp.framework.beans.page.PageModel;
import org.zmsoft.jfp.framework.support.MyControllerSupport;
import org.zmsoft.jfp.persistent.material.W702110Menu.W702110MenuDBO;
import org.zmsoft.jfp.persistent.material.W702110Menu.W702110MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 日志查看
 * 
 * @version 0.0.1 2018/05/08
 * @since 0.0.1 2018/05/08
 */
@Controller
// @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WXMenuController extends MyControllerSupport {

	@Resource
	W702110MenuService W702110MenuService_;// 微信菜单栏目管理

	/**
	 * 一览
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70211010", method = RequestMethod.POST)
	public ModelAndView list70211010(W702110MenuDBO param, PageModel<W702110MenuDBO> pageModel, RESTResultBean<String> result) throws Exception {
		ModelAndView model = super.getModelAndView("/wx_material/menu/wx-menu-list");
		param.setDelFlag(ZERO);
		pageModel.setResultCountFlag(true);
		pageModel.setFormParamBean(param);
		// 获取菜单数据
		pageModel = W702110MenuService_.doSelectPage(pageModel);

		model.addObject("page", pageModel);
		model.addObject("param", param);
		model.addObject("result", result);

		return model;
	}

	/**
	 * 新建
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70211020", method = RequestMethod.POST)
	public ModelAndView add70211020(W702110MenuDBO param) throws Exception {
		ModelAndView model = getModelAndView("/wx_material/menu/wx-menu-modify");
		model.addObject("mode", ONE);// 新加
		model.addObject("data", param);
		return model;
	}

	/**
	 * 编辑
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70211030", method = RequestMethod.POST)
	public ModelAndView edit80401030(W702110MenuDBO param) throws Exception {
		ModelAndView model = getModelAndView("/wx_material/menu/wx-menu-modify");
		model.addObject("mode", TWO);// 编辑
		model.addObject("data", W702110MenuService_.doRead(param));
		return model;
	}

	/**
	 * 删除
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70211040", method = RequestMethod.POST)
	public ModelAndView delete80401040(W702110MenuDBO param, PageModel<W702110MenuDBO> pageModel, RESTResultBean<String> result) throws Exception {
		W702110MenuService_.doDelete(param);
		result.setMsg(MESSAGE_DB_DELETE);
		return list70211010(new W702110MenuDBO(), pageModel, result);
	}

	/**
	 * 保存
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/70211050", method = RequestMethod.POST)
	public ModelAndView save80401050(W702110MenuDBO param, String mode, PageModel<W702110MenuDBO> pageModel, RESTResultBean<String> result) throws Exception {
		if (ONE.equals(mode)) {
			W702110MenuService_.doInsert(param);
		} else {
			W702110MenuService_.doUpdate(param);
		}
		result.setMsg(MESSAGE_DB_SAVE);
		return list70211010(new W702110MenuDBO(), pageModel, result);
	}

	// /**
	// * 操作
	// *
	// * @param param
	// * @return
	// * @throws Exception
	// */
	// @RequestMapping(value = "/70211060", method = RequestMethod.POST)
	// public ModelAndView save80401060(W702110MenuDBO param) throws Exception {
	// return list80401010(new W702110MenuDBO(), "操作成功");
	// }
}
